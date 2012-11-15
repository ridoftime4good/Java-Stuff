
#include <X11/Intrinsic.h>
#include <X11/Shell.h>
#include <sys/errno.h>
#include <sys/param.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

   /* Native client-server single instance shell for jGRASP. */

   /* Compile with something like: gcc -L/usr/X11/lib -lX11 linux_exec.c */

   extern int errno;

   #define NOT_RUNNING 0
   #define SENT 1
   #define NO_REPLY 2
   static int openInExisting_(char *filename, char lang, Window window,
   Display *display, int line, int wait);
   static int sendMessage_(char *command, Window win, Display *display,
         int wait);
   static void mainLoop_(Display *display, Window window, int child_pid,
         int out_fd);
   static int initGC_(Display *display, Window window);
   static void draw_(Display *display, Window window, XEvent *event);
   static void clientMessage_(Display *display, XEvent *event, int out_fd,
         int child_pid);

   static Atom GRASP_ACTIVE, GRASP_REPLY, WM_PROTOCOLS, WM_DELETE_WINDOW;

   static char *msg_ = "Closing this window will shut down jGRASP.";
   static GC gc_ = NULL;
   static int ascent_ = 20;

   static char *usage_ = "\n\nUsage:  %s  <-l line_number>  <-g language>  "
         "<-j jgrasp_home>\n"
         "  <-a jgrasp_java> <-e jgrasp_extensions (deprecated)> "
         "<-cp jgrasp_classpath> <-Jjvm_arg>*\n"
         "  <filename>\n\n"
         "   See the jGRASP HTML documentation on \"Running jGRASP\" for a\n"
         "   description of these flags.\n\n";


   int main(int argc, char *argv[]) {
      Display *display;
      int screen_num;
      Screen *screen;
      Window window, owner;
      int pid, pid2;
      char lang = ' ';
      int infd[2];
      int line = -1;
      int i;
      char *jgrasp_home = NULL;
      char *java_command = NULL;
      char *jgrasp_classpath = NULL;
      char *jgrasp_extensions = NULL;
      char *filename = NULL;
      char *jgrasp_home_from_shell = NULL;
      char *java_command_from_shell = NULL;
      char *jgrasp_classpath_from_shell = NULL;
      char *jgrasp_extensions_from_shell = NULL;
      char *java_bin_dir = NULL;
      int len;
      int last;
      char **java_args;
      char **jgrasp_args;
      int num_java_args = 0;
      int num_jgrasp_args = 0;
   
      jgrasp_home = getenv("JGRASP_HOME");
      java_command = getenv("JGRASP_JAVA");
      jgrasp_classpath = getenv("JGRASP_CLASSPATH");
      jgrasp_extensions = getenv("JGRASP_EXTENSIONS");
   
      java_args = malloc(sizeof(char *) * argc);
      jgrasp_args = malloc(sizeof(char *) * argc);
   
      /* Process arguments. The startup arguments will override corresponding
         environment variables. */
      for (i = 1; i < argc; i++) {
         last = (i == argc - 1);
         if (!last && !strcmp(argv[i], "-l")) {
            sscanf(argv[++i], "%d", &line);
         }
         else if (!last && !strcmp(argv[i], "-g")) {
            lang = argv[++i][0];
         }
         else if (!last && !strcmp(argv[i], "-j")) {
            jgrasp_home = argv[++i];
         }
         else if (!last && !strcmp(argv[i], "-a")) {
            java_command = argv[++i];
         }
         else if (!last && !strcmp(argv[i], "-cp")) {
            jgrasp_classpath = argv[++i];
         }
         else if (!last && !strcmp(argv[i], "-e")) {
            jgrasp_extensions = argv[++i];
         }
         else if (!last && !strcmp(argv[i], "-d")) {
            jgrasp_args[num_jgrasp_args++] = argv[i];
            jgrasp_args[num_jgrasp_args++] = argv[++i];
         }
         else if (!strcmp(argv[i], "-nosplash")) {
            jgrasp_args[num_jgrasp_args++] = argv[i];
         }
         else if (!last && !strcmp(argv[i], "-Ij")) {
            jgrasp_home_from_shell = argv[++i];
         }
         else if (!last && !strcmp(argv[i], "-Ia")) {
            java_command_from_shell = argv[++i];
         }
         else if (!last && !strcmp(argv[i], "-Icp")) {
            jgrasp_classpath_from_shell = argv[++i];
         }
         else if (!last && !strcmp(argv[i], "-Ie")) {
            jgrasp_extensions_from_shell = argv[++i];
         }
         else if ((len = strlen(argv[i])) > 2 && !strncmp(argv[i], "-J", 2)) {
            java_args[num_java_args++] = argv[i] + 2;
         }
         else if (!last && !strcmp(argv[i], "-IJ")) {
            // Java args until next -IJ.
            while (++i < argc && strcmp(argv[i], "-IJ")) {
               java_args[num_java_args++] = argv[i];
            }
         }
         else if (!strcmp(argv[i], "-h") || !strcmp(argv[i], "-help")) {
            fprintf(stderr, usage_, argv[0]);
            exit(0);
         }
         else if (last) {
            filename = argv[i];
         }
         else {
            fprintf(stderr, "\n\nBad argument \"%s\" to jgrasp.", argv[i]);
            fprintf(stderr, usage_, argv[0]);
            exit(1);
         }
      }
      /* Startup arguments from our startup shell override the others. */
      if (jgrasp_home_from_shell != NULL) {
         jgrasp_home = jgrasp_home_from_shell;
      }
      if (java_command_from_shell != NULL) {
         char *tmp;
         java_command = java_command_from_shell;
         java_bin_dir = strdup(java_command);
         tmp = strrchr(java_bin_dir, '/');
         if (tmp != NULL) {
            *tmp = '\0';
         }
         else {
            free(java_bin_dir);
            java_bin_dir = NULL;
         }
      }
      if (jgrasp_classpath_from_shell != NULL) {
         jgrasp_classpath = jgrasp_classpath_from_shell;
      }
      if (jgrasp_extensions_from_shell != NULL) {
         jgrasp_extensions = jgrasp_extensions_from_shell;
      }
   
      if (jgrasp_extensions != NULL) {
         jgrasp_args[num_jgrasp_args++] = "-e";
         jgrasp_args[num_jgrasp_args++] = jgrasp_extensions;
      }
   
   
      /* Open display. */
      if ((display = XOpenDisplay(NULL)) == NULL) {
         fprintf(stderr,
         		"\n\njGRASP exec error: Unable to open display.\n\n");
         free(java_args);
         free(jgrasp_args);
         exit(1);
      }
   
      /* Get screen info. */
      screen_num = DefaultScreen(display);
      screen = DefaultScreenOfDisplay(display);
   
      /* Create window. */
      window = XCreateSimpleWindow(display, RootWindow(display, screen_num),
            0, 0, 300, 50, 0, BlackPixel(display, screen_num),
            WhitePixel(display, screen_num));
      XSync(display, False);
   
      /* Set up to receive client messages. */
      XSelectInput(display, window, SubstructureRedirectMask | ExposureMask);
      XSync(display, False);
   
      if ((GRASP_ACTIVE = XInternAtom(display, "_JGRASP_ACTIVE", False))
            == None
            || (GRASP_REPLY = XInternAtom(display, "_JGRASP_REPLY", False))
            == None) {
         fprintf(stderr, "\n\njGRASP exec error: Unable to setup"
            " communications.\n\n");
         free(java_args);
         free(jgrasp_args);
         exit(1);
      }
   
      if ((owner = XGetSelectionOwner(display, GRASP_ACTIVE)) != None) {
         int response;
         if (filename == NULL) {
            if ((response = sendMessage_("raise:\n", window, display, 10))
                  == SENT) {
               free(java_args);
               free(jgrasp_args);
               exit(0);
            }
         }
         else if ((response =
               openInExisting_(filename, lang, window, display, line, 10))
               == SENT) {
            free(java_args);
            free(jgrasp_args);
            exit(0);
         }
         if (response == NO_REPLY) {
            fprintf(stderr, "\n\njGRASP exec: No response from jgrasp "
                  "session after 10 seconds.\n"
                  "   Taking over session.\n\n");
         }
      }
   
      if (jgrasp_home == NULL) {
         fprintf(stderr, "\n\njGRASP exec error: The directory where jGRASP "
               "is installed must be specified\n"
               "by   -j jgrasp_home   or by a   JGRASP_HOME   environment "
               "variable.\n\n");
         free(java_args);
         free(jgrasp_args);
         exit(1);
      }
   
      XSetSelectionOwner(display, GRASP_ACTIVE, window, CurrentTime);
   
      if (filename != NULL) { 
         /* Send a message to myself and don't wait for a response. */
         openInExisting_(filename, lang, window, display, line, 0);
      }
   
      if (pipe(infd) == -1) {
         fprintf(stderr, "\n\njGRASP exec error: Unable to open pipe.\n\n");
         free(java_args);
         free(jgrasp_args);
         exit(2);
      }
   
      if ((pid = fork()) < 0) {
         fprintf(stderr,
         		"\n\njGRASP exec error: Unable to fork process.\n\n");
         free(java_args);
         free(jgrasp_args);
         exit(1);
      }
   
      if (pid == 0) { /* Child process - start jGRASP. */
         char linebuf[20];
         struct stat stat_buffer;
         char **args;
         int arg;
         int num_extra_jgrasp_args;
      
         /* stdin becomes pipe. */
         close(0);
         if (dup(infd[0]) == -1) {
            fprintf(stderr,
            		"\n\njGRASP exec error: Unable to redirect stdin.");
            fprintf(stderr, "   error number %d.\n", errno);
            switch(errno) {
               case EBADF:
                  fprintf(stderr, "   File descriptor not valid.\n");
                  break;
               case EMFILE:
                  fprintf(stderr, "   Too many file descriptors in use.\n");
                  break;
            }
            exit(1);
         }
      
         sprintf(linebuf, "%d", line);
      
         if (chdir(jgrasp_home) != 0) {
            fprintf(stderr, "\n\njGRASP exec error: Unable to change working "
                  "directory to %s.\n\n", jgrasp_home);
            exit(1);
         }
      
         if (java_command == NULL) {
            java_command = "java";
         }
         if (jgrasp_classpath == NULL) {
            if (java_command[0] == '/') {
               // Full path to java command specified, but not classpath.
               char *tmp;
               jgrasp_classpath = malloc(strlen(java_command) + 50);
               sprintf(jgrasp_classpath, "jgrasp.jar:%s", java_command);
               tmp = strrchr(jgrasp_classpath, '/');
               sprintf(tmp, "/../lib/tools.jar");
               if (stat(jgrasp_classpath + 11, &stat_buffer) != 0) {
                  // tools.jar not found.
                  jgrasp_classpath="jgrasp.jar";
               }
            }
            else {
               jgrasp_classpath="jgrasp.jar";
            }
         }
      
         num_extra_jgrasp_args = (java_bin_dir == NULL)?0:2;
         args = malloc(sizeof(char *) * (6 + num_java_args + num_jgrasp_args +
            num_extra_jgrasp_args));
         arg = 0;
         args[arg++] = java_command;
         for (i = 0; i < num_java_args; i++) {
            args[arg++] = java_args[i];
         }
         free(java_args);
         args[arg++] = "-classpath";
         args[arg++] = jgrasp_classpath;
         args[arg++] = "Grasp";
         args[arg++] = "-i";
         for (i = 0; i < num_jgrasp_args; i++) {
            args[arg++] = jgrasp_args[i];
         }
         free(jgrasp_args);
         if (num_extra_jgrasp_args == 2) {
            args[arg++] = "-j";
            args[arg++] = java_bin_dir;
         }
         args[arg] = NULL;
         while (execvp(java_command, args) == -1 && errno == EINTR) {
         }
      
         // Exec failed, try our JRE distribution.
         if (stat("jdk/bin/java", &stat_buffer) == 0) {
            args[0] = "jdk/bin/java";
            while (execvp("jdk/bin/java", args) == -1 && errno == EINTR) {
            }
            fprintf(stderr, "\n\njGRASP exec error: errno %d.\n", errno);
         }
         else if (errno == ENOENT) {
            fprintf(stderr, "\n\njGRASP exec error: command \"%s\" not "
            		"found.\n", java_command);
         }
         else {
            fprintf(stderr, "\n\njGRASP exec error: errno %d.\n", errno);
         }
         fprintf(stderr, "Unable to run jgrasp.\n\n");
         exit(1);
      }
   
      /* Parent process. */
      free(java_args);
      free(jgrasp_args);
   
      /* Fork again and exit the parent. */
      if ((pid2 = fork()) < 0) {
         fprintf(stderr, "\n\njGRASP exec error: Unable to fork second "
               "process.\n\n");
         exit(1);
      }
   
      if (pid2 != 0) {
         exit(0);
      }
      /* Set up delete response. */
      WM_DELETE_WINDOW = XInternAtom(display, "WM_DELETE_WINDOW", False);
      XSetWMProtocols(display, window, &WM_DELETE_WINDOW, 1);
      WM_PROTOCOLS = XInternAtom(display, "WM_PROTOCOLS", False);
   
      XSetIconName(display, window, "jGRASP exec");
      XStoreName(display, window, "jGRASP exec");
      if (gc_ == NULL) {
         initGC_(display, window);
      }
      {
         XWMHints hints;
         hints.flags = StateHint;
         hints.initial_state = IconicState;
         XSetWMHints(display, window, &hints);
      }
      XMapWindow(display, window);
      XIconifyWindow(display, window, screen_num);
      mainLoop_(display, window, pid, infd[1]);
   
      return 0;
   }


   static void mainLoop_(Display *display, Window win, int child_pid,
         int out_fd) {
      XEvent event;
      int status;
   
      while (1) {
         while (XPending(display)) {
            XNextEvent(display, &event);
            if (event.type == ClientMessage) {
               clientMessage_(display, &event, out_fd, child_pid);
            }
            else if (event.type == Expose) {
               draw_(display, win, &event);
            }
         }
         waitpid(child_pid, &status, WNOHANG);
         if (kill(child_pid, 0) == 0) {
            usleep(10000);
         }
         else {
            break;  /* jGRASP has died. */
         }
      }
   }


   static int initGC_(Display *display, Window window) {
      XGCValues gcvals;
      XFontStruct *font_struct;
      gc_ = XCreateGC(display, window, 0, &gcvals);
      if (gc_ == NULL) {
         return 0;
      }
      if ((font_struct = XLoadQueryFont(display,
            "-*-helvetica-bold-r-*-*-*-120-*-*-*-*-*-1")) == NULL) {
         if ((font_struct = XLoadQueryFont(display,
               "-*-courier-bold-r-*-*-*-120-*-*-*-*-*-1")) == NULL) {
            if ((font_struct = XLoadQueryFont(display,
                  "-*-*-bold-r-*-*-*-120-*-*-*-*-*-1")) == NULL) {
               font_struct = XLoadQueryFont(display,
                     "-*-*-*-*-*-*-*-120-*-*-*-*-*-1");
            }
         }
      }
      if (font_struct) {
         int width;
         XSetFont(display, gc_, font_struct->fid);
         ascent_ = font_struct->ascent;
         width = XTextWidth(font_struct, msg_, strlen(msg_));
         XResizeWindow(display, window, width + 10, ascent_
               + font_struct->descent + 10);
         XFreeFont(display, font_struct);
      }
      return 1;
   }


   static void draw_(Display *display, Window window, XEvent *event) {
      //XExposeEvent *e = (XExposeEvent *)event;
      if (gc_ == NULL) {
         if (!initGC_(display, window)) {
            return;
         }
      }
      XDrawString(display, window, gc_, 5, ascent_ + 5, msg_, strlen(msg_));
   }


   static void clientMessage_(Display *display, XEvent *event, int out_fd,
         int child_pid) {
      XClientMessageEvent *e;
      XClientMessageEvent reply;
      Window sender;
      Atom ret_type;
      int ret_fmt;
      unsigned long length, bytes_to_go;
      unsigned char *data;
      int i;
   
      e = (XClientMessageEvent *)event;
      if (e->message_type == WM_PROTOCOLS) {
         if (*((int *)&(e->data)) == WM_DELETE_WINDOW) {
            if (write(out_fd, "exit\n", 5) == -1) {
               fprintf(stderr, "jGRASP exec: failed to send exit message "
                     "to jGRASP.\n");
            }
            for (i = 0; i < 5; i++) {
               if (kill(child_pid, 0) != 0) {
                  break;
               }
               sleep(1);
            }
            if (kill(child_pid, 0) == 0) {
               fprintf(stderr, "jGRASP exec: jGRASP still alive after five "
                     "seconds. Sending a hard kill.\n");
               kill(child_pid, SIGKILL);
            }
            exit(0);
         }
      }
      else if (e->message_type == GRASP_ACTIVE) {
         /* Request to open a file. */
         sender = (Window)(e->data.l[0]);
         if (XGetWindowProperty(display, sender, GRASP_ACTIVE, 0, 400,
               True, GRASP_ACTIVE, &ret_type, &ret_fmt, &length,
               &bytes_to_go, &data) == Success) {
            if (length > 0) {
               *((char *)data + (length - 1)) = '\0';
               if (write(out_fd, data, length - 1)) {
                  fprintf(stderr, "jGRASP exec: failed to send file open "
                        "message to jGRASP.\n");
               }
            }
            XFree((char *)data);
         }
      
         reply.type = ClientMessage;
         reply.send_event = True;
         reply.display = display;
         reply.window = sender;
         reply.message_type = GRASP_REPLY;
         reply.format = 32;
         XSendEvent(display, sender, False, SubstructureRedirectMask,
               (XEvent *)&reply);
      }
   }


   static int openInExisting_(char *filename, char lang, Window win,
         Display *display, int line, int wait) {
      char *command;
      char tmpdir[MAXPATHLEN + 1];
      int result;
   
      if (filename[0] == '/') {
         command = malloc(strlen(filename) + 50);
         sprintf(command, "open2:%c%d:%s\n", lang, line, filename);
      }
      else {
         if (!getcwd(tmpdir, MAXPATHLEN)) {
            sprintf(tmpdir, ".");
         }
         command = malloc(strlen(tmpdir) + strlen(filename) + 50);
         sprintf(command, "open2:%c%d:%s/%s\n", lang, line, tmpdir, filename);
      }
   
      result = sendMessage_(command, win, display, wait);
   
      free(command);
   
      return result;
   }


   static int sendMessage_(char *command, Window win, Display *display,
         int wait) {
      Window owner;
      XClientMessageEvent event;
      int reply, sl;
      XEvent event2;
   
      XChangeProperty(display, win, GRASP_ACTIVE, GRASP_ACTIVE, 8,
         PropModeReplace, (unsigned char *)command, strlen(command) + 1);
   
      XSync(display, False);
      if ((owner = XGetSelectionOwner(display, GRASP_ACTIVE)) == None) {
         return NOT_RUNNING;
      }
      event.type = ClientMessage;
      event.send_event = True;
      event.display = display;
      event.window = owner;
      event.message_type = GRASP_ACTIVE;
      event.format = 32;
      event.data.l[0] = (long)win;
      if (!XSendEvent(display, owner, False,
            SubstructureRedirectMask, (XEvent *)&event)) {
         return NOT_RUNNING;
      }
      XSync(display, False);
      if (wait == 0) {
         return SENT;
      }
      /* Wait for confirmation. */
      reply = 0;  sl = 0;
      while (reply == 0) {
         while (XPending(display)) {
            XNextEvent(display, &event2);
            if (event2.type != ClientMessage) {
               continue;
            }
            if (event2.xclient.message_type == GRASP_REPLY) {
               reply = 1;
            }
         }
         if (reply == 0) {
            if (sl >= wait) {
               break;
            }
            sleep(1);
            sl++;
         }
      }
   
      if (reply == 0) {
         return NO_REPLY;
      }
      return SENT;
   }
