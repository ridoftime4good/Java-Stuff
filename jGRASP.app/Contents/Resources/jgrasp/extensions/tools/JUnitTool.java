
   import java.awt.Color;

   import java.io.BufferedWriter;
   import java.io.File;
   import java.io.FileWriter;
   import java.io.IOException;
   import java.io.Writer;

   import java.net.URL;

   import java.util.ArrayList;
   import java.util.HashMap;
   import java.util.Map;
   import java.util.HashSet;
   import java.util.List;
   import java.util.Set;
   
   import java.util.regex.Matcher;
   import java.util.regex.Pattern;

   import javax.swing.Icon;

   import jgrasp.FileData;
   import jgrasp.Language;
   import jgrasp.ResizableIcon;
   import jgrasp.Tool;
   
   import jgrasp.tool.ActionContext;
   import jgrasp.tool.CompilerEnvironmentLocation;
   import jgrasp.tool.Conditions;
   import jgrasp.tool.DateTestType;
   import jgrasp.tool.DirType;
   import jgrasp.tool.ExecType;
   import jgrasp.tool.Highlightable;
   import jgrasp.tool.OutputListener;
   import jgrasp.tool.PostExecAction;
   import jgrasp.tool.PostExecData;
   import jgrasp.tool.ProjectState;
   import jgrasp.tool.StateStorage;
   import jgrasp.tool.ToolAction;
   import jgrasp.tool.ToolCreateData;
   import jgrasp.tool.ToolState;
   import jgrasp.tool.ToolUtil;


   /** JUnit tool interface. **/
   public class JUnitTool implements Tool {
   
   
      /** Class for holding file test data. **/
      private static class FileInfo {
      
         /** The test file. **/
         private File fl;
      
         /** The test class name. **/
         private String testClass;
      
         /** The test source file, or null if there is no single
          *  source associated with the test. **/
         private File source;
      
      
         /** Creates a new FileInfo.
          *
          *  @param file the source or test source file.
          *
          *  @param isTest true if <code>file</code> is a test source
          *  file, false if it is a normal source file.
          *
          *  @param context the current conditions or context. **/
         public FileInfo(final File file, final boolean isTest,
               final Conditions context) {
            if (isTest) {
               fl = file;
            }
            else {
               source = file;
               fl = getTestFile(file, false, context);
            }
            init();
         }
      
      
         /** Creates a new FileInfo.
          *
          *  @param file the test source file.
          *
          *  @param src the corresponding source file.
          *
          *  @param context the current conditions or context. **/
         public FileInfo(final File file, final File src,
               final Conditions context) {
            fl = file;
            source = src;
            init();
         }
         
      
         /** Initialization to be perfomed by all constructors. **/
         private void init() {
            if (fl != null) {
               String pack = ToolUtil.getJavaPackage(fl);
               testClass = getTestClassName(fl, true);
               if (pack.length() > 0) {
                  testClass = pack + "." + testClass;
               }
            }
         }
      }
   
   
      /** Action for running after a compile. **/
      private class RunLaterAction implements PostExecAction {
      
         /** Info on the files to be processed. **/
         private List<FileInfo> files;
         
         /** True if the tests will be run in debug mode, false
          *  otherwise. **/
         private boolean debug;
         
         /** Item to be highlighted during execution. This may
          *  be null. **/
         private Highlightable highlightItem;
      
      
         /** Creates a new RunLaterAction.
          *
          *  @param filesIn info on the files to be processed.
          *
          *  @param debugIn true if the tests will be run in debug mode,
          *  false otherwise.
          *
          *  @param h item to be highlighted during execution. This
          *  may be null. **/
         public RunLaterAction(final List<FileInfo> filesIn,
               final boolean debugIn, final Highlightable h) {
            files = filesIn;
            debug = debugIn;
            highlightItem = h;
         }
      
      
         /** {@inheritDoc} **/
         public void execute(final ActionContext context,
               final PostExecData data) {
            if (data.hasErrors() || data.getExitValue() != 0) {
               String extra = data.hasErrors()? ""
                     : " (non-zero exit value from compiler)";
               creationData.showMessage(context.getDialogParent(),
                     "Run aborted because compilation appears to have failed"
                           + extra + ".",
                     "jGRASP JUnit Plugin");
               return;
            }
            if (data.wasAborted()) {
               return;
            }
            runTestFiles(context, files, false, true, debug, highlightItem);
         }
      };
    
     
      /** Action to be run after a test completes. **/
      private class AfterExec implements PostExecAction,
            OutputListener {
      
         /** Info for the files that were checked. **/
         private List<FileInfo> files;
      
         /** Classes for which there were error messages. **/
         private Set<String> failedClasses = new HashSet<String>();
      
      
         /** Creates a new AfterExec.
          *
          *  @param fileTested info for the files that were checked. **/
         public AfterExec(final List<FileInfo> filesTested) {
            files = filesTested;
         }
      
      
         /** {@inheritDoc} **/
         public void execute(final ActionContext context,
               final PostExecData data) {
            if (data.getExitValue() != 0 || data.wasAborted()) {
               return;
            }
            processResults(context, data, files, failedClasses);
         }
         
         
         /** {@inheritDoc} **/
         public void processLine(final String line) {
            Matcher m = errPattern.matcher(line);
            try {
               if (!m.matches()) {
                  return;
               }
            }
               catch (StackOverflowError e) {
                  return;
               }
            String className = m.group(1);
            if (className == null) {
               return;
            }
            failedClasses.add(className);
         }
      };
   
   
      /** Identifer for storing and retrieving per-project per-file
       *  state data. **/
      private static String dataId = "jgrJUnit0xr";
   
      /** Pattern for matching class names in JUnit error message. **/
      private static Pattern errPattern = Pattern.compile(
            "\\s*\\d*\\) \\w+\\((.+)\\).*");
   
      /** Arbitrary ID for tool storage. **/
      private static final int SETTINGS_ID = 342;
      
      /** Reusable empty set. **/
      private static Set<File> emptySet = new HashSet<File>();
    
      /** The "about" action. **/
      private ToolAction aboutAction = 
         new ToolAction() {
          
            public boolean isProjectAction() {
               return false;
            }
          
            public String getMenuName() {
               return "About";
            }
             
            public boolean showMenuItem() {
               return true;
            }
             
            public boolean showToolbarItem() {
               return false;
            }
             
            public URL getToolbarIconURL() {
               return null;
            }
             
            public String getToolbarLabel() {
               return null;
            }
             
            public String getToolbarHint() {
               return null;
            }
             
            public void execute(final ActionContext context,
                  final Highlightable h) {
               about(context);
            }
         };
    
      /** The configure action. **/
      private ToolAction configureAction = 
         new ToolAction() {
          
            public boolean isProjectAction() {
               return false;
            }
          
            public String getMenuName() {
               return "Configure";
            }
             
            public boolean showMenuItem() {
               return true;
            }
             
            public boolean showToolbarItem() {
               return false;
            }
             
            public URL getToolbarIconURL() {
               return null;
            }
             
            public String getToolbarLabel() {
               return null;
            }
             
            public String getToolbarHint() {
               return null;
            }
             
            public void execute(final ActionContext context,
                  final Highlightable h) {
               configure(context);
            }
         };
    
      /** Menu separator. **/
      private ToolAction sepAction = 
         new ToolAction() {
          
            public boolean isProjectAction() {
               return false;
            }
          
            public String getMenuName() {
               return null;
            }
             
            public boolean showMenuItem() {
               return createTestFileAction.showMenuItem()
                     || compileTestFileAction.showMenuItem()
                     || runTestFileAction.showMenuItem()
                     || compileAndRunProjectAction.showMenuItem();
            }
             
            public boolean showToolbarItem() {
               return false;
            }
             
            public URL getToolbarIconURL() {
               return null;
            }
             
            public String getToolbarLabel() {
               return null;
            }
             
            public String getToolbarHint() {
               return null;
            }
             
            public void execute(final ActionContext context,
                  final Highlightable h) {
            }
         };
   
      /** The create test file action. **/
      private ToolAction createTestFileAction = 
         new ToolAction() {
          
            public boolean isProjectAction() {
               return false;
            }
          
            public String getMenuName() {
               return "Create Test File";
            }
             
            public boolean showMenuItem() {
               if (settings.getJUnitHome() == null) {
                  return false;
               }
               return activeJavaFile && !isTestFile && !haveTestFile;
            }
             
            public boolean showToolbarItem() {
               if (settings.hideToolbarItems()) {
                  return false;
               }
               return showMenuItem();
            }
             
            public URL getToolbarIconURL() {
               return JUnitTool.class.getResource("junit_create.png");
            }
             
            public String getToolbarLabel() {
               return "Create Test";
            }
             
            public String getToolbarHint() {
               return "Create JUnit test file";
            }
                 
            public void execute(final ActionContext context,
                  final Highlightable h) {
               createTestFile(context);
            }
         };
   
      /** The edit test file action. **/
      private ToolAction editTestFileAction = 
         new ToolAction() {
          
            public boolean isProjectAction() {
               return false;
            }
          
            public String getMenuName() {
               return "Edit Test File";
            }
             
            public boolean showMenuItem() {
               return haveTestFile && !isTestFile;
            }
             
            public boolean showToolbarItem() {
               if (settings.hideToolbarItems()) {
                  return false;
               }
               return showMenuItem();
            }
             
            public URL getToolbarIconURL() {
               return JUnitTool.class.getResource("junit_create.png");
            }
             
            public String getToolbarLabel() {
               return "Edit Test";
            }
             
            public String getToolbarHint() {
               return "Edit JUnit test file";
            }
                 
            public void execute(final ActionContext context,
                  final Highlightable h) {
               editTestFile(context);
            }
         };
   
      /** The compile test file action. **/
      private ToolAction compileTestFileAction = 
         new ToolAction() {
          
            public boolean isProjectAction() {
               return false;
            }
          
            public String getMenuName() {
               return "Compile Tests";
            }
             
            public boolean showMenuItem() {
               if (settings.getJUnitHome() == null) {
                  return false;
               }
               return haveTestFile || isTestFile;
            }
             
            public boolean showToolbarItem() {
               if (settings.hideToolbarItems()) {
                  return false;
               }
               if (!isTestFile) {
                  return false;
               }
               return showMenuItem();
            }
             
            public URL getToolbarIconURL() {
               return JUnitTool.class.getResource("junit_compile.png");
            }
             
            public String getToolbarLabel() {
               return "Compile Tests";
            }
             
            public String getToolbarHint() {
               return "Compile JUnit tests";
            }
                 
            public void execute(final ActionContext context,
                  final Highlightable h) {
               if (settings.getJUnitHome() == null) {
                  return;
               }
               List<FileInfo> fi = getActiveFileInfo(context);
               compileTestFiles(context, fi, null, false, h);
            }
         };
   
      /** The run test file action. **/
      private ToolAction runTestFileAction = 
         new ToolAction() {
          
            public boolean isProjectAction() {
               return false;
            }
          
            public String getMenuName() {
               return "Run Tests";
            }
             
            public boolean showMenuItem() {
               if (settings.getJUnitHome() == null) {
                  return false;
               }
               //*** haveTestClassFile
               return haveTestFile || isTestFile;
            }
             
            public boolean showToolbarItem() {
               if (settings.hideToolbarItems()) {
                  return false;
               }
               return showMenuItem();
            }
             
            public URL getToolbarIconURL() {
               return null;
            }
             
            public String getToolbarLabel() {
               return "Run Tests";
            }
             
            public String getToolbarHint() {
               return "Run JUnit tests";
            }
                 
            public void execute(final ActionContext context,
                  final Highlightable h) {
               if (settings.getJUnitHome() == null) {
                  return;
               }
               runTestFiles(context, getActiveFileInfo(context), false, false,
                     false, h);
            }
         };
   
      /** The deubg test file action. **/
      private ToolAction debugTestFileAction = 
         new ToolAction() {
          
            public boolean isProjectAction() {
               return false;
            }
          
            public String getMenuName() {
               return "Debug Tests";
            }
             
            public boolean showMenuItem() {
               if (settings.getJUnitHome() == null) {
                  return false;
               }
               //*** haveTestClassFile
               return haveTestFile || isTestFile;
            }
             
            public boolean showToolbarItem() {
               if (settings.hideToolbarItems()) {
                  return false;
               }
               return showMenuItem();
            }
             
            public URL getToolbarIconURL() {
               return null;
            }
             
            public String getToolbarLabel() {
               return "Debug Tests";
            }
             
            public String getToolbarHint() {
               return "Debug JUnit tests";
            }
                 
            public void execute(final ActionContext context,
                  final Highlightable h) {
               if (settings.getJUnitHome() == null) {
                  return;
               }
               runTestFiles(context, getActiveFileInfo(context), false, false,
                     true, h);
            }
         };
   
      /** The compile and run test file action. **/
      private ToolAction compileAndRunTestFileAction = 
         new ToolAction() {
          
            public boolean isProjectAction() {
               return false;
            }
          
            public String getMenuName() {
               return "Compile and Run Tests";
            }
             
            public boolean showMenuItem() {
               if (settings.getJUnitHome() == null) {
                  return false;
               }
               return haveTestFile || isTestFile;
            }
             
            public boolean showToolbarItem() {
               if (settings.hideToolbarItems()) {
                  return false;
               }
               return showMenuItem();
            }
             
            public URL getToolbarIconURL() {
               return JUnitTool.class.getResource("junit_run.png");
            }
             
            public String getToolbarLabel() {
               return "Run Tests";
            }
             
            public String getToolbarHint() {
               return "Compile and run JUnit tests";
            }
                 
            public void execute(final ActionContext context,
                  final Highlightable h) {
               if (settings.getJUnitHome() == null) {
                  return;
               }
               runTestFiles(context, getActiveFileInfo(context), true, false,
                     false, h);
            }
         };
   
      /** The compile and debug test file action. **/
      private ToolAction compileAndDebugTestFileAction = 
         new ToolAction() {
          
            public boolean isProjectAction() {
               return false;
            }
          
            public String getMenuName() {
               return "Compile and Debug Tests";
            }
             
            public boolean showMenuItem() {
               if (settings.getJUnitHome() == null) {
                  return false;
               }
               return haveTestFile || isTestFile;
            }
             
            public boolean showToolbarItem() {
               if (settings.hideToolbarItems()) {
                  return false;
               }
               return showMenuItem();
            }
             
            public URL getToolbarIconURL() {
               return JUnitTool.class.getResource("junit_debug.png");
            }
             
            public String getToolbarLabel() {
               return "Debug Tests";
            }
             
            public String getToolbarHint() {
               return "Compile and debug JUnit tests";
            }
                 
            public void execute(final ActionContext context,
                  final Highlightable h) {
               if (settings.getJUnitHome() == null) {
                  return;
               }
               runTestFiles(context, getActiveFileInfo(context), true, false,
                     true, h);
            }
         };
    
      /** The compile and run project action. **/
      private ToolAction compileAndRunProjectAction = 
         new ToolAction() {
          
            public boolean isProjectAction() {
               return true;
            }
          
            public String getMenuName() {
               return "Compile and Run Project Tests";
            }
             
            public boolean showMenuItem() {
               if (settings.getJUnitHome() == null) {
                  return false;
               }
               return haveProjectTestFile;
            }
             
            public boolean showToolbarItem() {
               if (settings.hideToolbarItems()) {
                  return false;
               }
               return showMenuItem();
            }
             
            public URL getToolbarIconURL() {
               return JUnitTool.class.getResource("junit_run.png");
            }
             
            public String getToolbarLabel() {
               return "Compile and Run Project Tests";
            }
             
            public String getToolbarHint() {
               return "Compile and run JUnit tests for all files in project";
            }
                 
            public void execute(final ActionContext context,
                  final Highlightable h) {
               if (settings.getJUnitHome() == null) {
                  return;
               }
               FileData[] testFds = context.getProjectTestFiles();
               Map<File, FileData> testFileToFd =
                     new HashMap<File, FileData>();
               for (FileData fd : testFds) {
                  if (!fd.getFile().getName().endsWith(".java")) {
                     continue;
                  }
                  testFileToFd.put(fd.getFile(), fd);
               }
            
               List<FileInfo> fi = new ArrayList<FileInfo>();
               FileData[] sourceFds = context.getProjectFiles();
               for (FileData fd : sourceFds) {
                  if (!fd.getFile().getName().endsWith(".java")) {
                     continue;
                  }
                  File f = getTestFile(fd.getFile(), false, context);
                  if (testFileToFd.remove(f) != null) {
                     fi.add(new FileInfo(f, fd.getFile(), context));
                  }
               }
               
               for (FileData fd : testFileToFd.values()) {
                  fi.add(new FileInfo(fd.getFile(), true, context));
               }
               runTestFiles(context, fi, true, false, false, h);
            }
         };
    
      /** The configuration dialog. **/
      private JUnitSettingsDialog configureDialog;
    
      /** Current JUnit settings. **/
      private JUnitSettings settings;
   
      /** Creation data. **/
      private ToolCreateData creationData;
    
      /** True if current conditions indicate an active Java file,
       *  false otherwise. **/
      private boolean activeJavaFile;
    
      /** True if there is an active Java file and a test file
       *  that corresponds to it, false otherwise. **/
      private boolean haveTestFile;
    
      /** True if the active file is a JUnit test case source file,
       *  false otherwise. **/
      private boolean isTestFile;
    
      /** True if there is a test file corresponding to at least
       *  one source file in the current project, or a lone test
       *  file in the test directory if it exists, false otherwise. **/
      private boolean haveProjectTestFile;
    
      /** Creates a new JUnit tool.
       *
       *  @param tcd tool creation data. **/
      public JUnitTool(final ToolCreateData tcd) {
         creationData = tcd;
         settings = new JUnitSettings(tcd.getData(JUnitTool.class,
               SETTINGS_ID));
      }
   
   
      /** Gets file info for the active file.
       *
       *  @param context the current context.
       *
       *  @return a list containing the file info (one element) for
       *  the active file. **/
      private static List<FileInfo> getActiveFileInfo(final ActionContext context) {
         FileData fd = context.getActiveFile();
         
         List<FileInfo> fi = new ArrayList<FileInfo>();
         File srcFile = null;
         if (ToolUtil.isTestFile(fd.getFile(), context)) {
            srcFile = getSourceForTest(context, fd.getFile());
            fi.add(new FileInfo(fd.getFile(), srcFile, context));
         }
         else {
            fi.add(new FileInfo(fd.getFile(), false, context));
         }
         return fi;
      }
   
   
      /** Gets the source file, if any, corresponding to a specified
       *  test file.
       *
       *  @param conditions the current conditions.
       *
       *  @param testFile the test file of interest.
       *
       *  @return the source file corresponding to the specified test
       *  file, or null if there is no project, or there is no associated
       *  source file. **/
      private static File getSourceForTest(final Conditions conditions,
            final File testFile) {
         FileData[] sourceFds = conditions.getProjectFiles();
         if (sourceFds == null) {
            return null;
         }
         for (FileData fd : sourceFds) {
            if (!fd.getFile().getName().endsWith(".java")) {
               continue;
            }
            File f = getTestFile(fd.getFile(), false, conditions);
            if (f.equals(testFile)) {
               return fd.getFile();
            }
         }
         return null;
      }
   
   
      /** Gets the test file corresponding to a source file.
       *
       *  @param f the source or test file.
       *
       *  @param isTest true if <code>f</code> is a test source file,
       *  false if it is a regular source file.
       *
       *  @param conditions the current conditions or context.
       *
       *  @return the test file corresponding to <code>f</code>,
       *  or null if the correct file can not be determined. **/
      private static File getTestFile(final File f, final boolean isTest,
            final Conditions conditions) {
         if (isTest) {
            return f;
         }
         return getTestOrClassFile(f, conditions, ".java");
      }
   
   
      /** Gets the test class file corresponding to a source file
       *  or test source file.
       *
       *  @param f the source or test file.
       *
       *  @param isTest true if <code>f</code> is a test source file,
       *  false if it is a regular source file.
       *
       *  @param conditions the current conditions or context.
       *
       *  @return the test class file corresponding to <code>f</code>,
       *  or null if the correct file can not be determined. **/
      private static File getTestClassFile(final File f,
            final boolean isTest, final Conditions conditions) {
         if (isTest) {
            File parentDir = f.getParentFile();
            String name = f.getName();
            if (name.endsWith(".java")) {
               name = name.substring(0, name.length() - 5);
            }
            name += ".class";
            return new File(parentDir, name);
         }
         return getTestOrClassFile(f, conditions, ".class");
      }
      
      
      /** Gets the test source or class file corresponding to a source file.
       *
       *  @param f the source or file.
       *
       *  @param context the current conditions or context.
       *
       *  @param ext extension to use for the file (".java" for a test source
       *  file, ".class" for a text class file.
       *
       *  @return the test file corresponding to <code>f</code>,
       *  or null if the correct file can not be determined. **/
      private static File getTestOrClassFile(final File f,
            final Conditions conditions, final String ext) {
         File dir = conditions.getProjectDir(DirType.TEST, true);
         if (dir == null) {
            String dirStr = ToolUtil.getPackageRoot(f);
            if (dirStr == null) {
               return null;
            }
            dir = new File(dirStr);
         }
      
         String pack = ToolUtil.getJavaPackage(f);
         if (pack != null && pack.length() > 0) {
            dir = new File(dir, pack.replace('.', File.separatorChar));
         }
         
         return new File(dir, getTestClassName(f, false) + ext);
      }
   
   
      /** Gets the test class name for a source or test file.
       *
       *  @param f the source or test file of interest.
       *
       *  @param isTest true if <code>f</code> is a test source file,
       *  false if it is a regular source file.
       *
       *  @return a class name for the test class corresponding
       *  to <code>srcFile</code>. **/
      private static String getTestClassName(final File f,
            final boolean isTest) {
         String name = f.getName();
         int dotLoc = name.lastIndexOf('.');
         if (dotLoc >= 0) {
            if (isTest) {
               return name.substring(0, dotLoc);
            }
            return name.substring(0, dotLoc) + "Test";
         }
         if (isTest) {
            return name;
         }
         return name + "Test";
      }
   
   
      /** {@inheritDoc} **/
      public String getMenuName() {
         return "JUnit";
      }
      
      
      /** {@inheritDoc} **/
      public ToolAction[] getToolActions() {
         return new ToolAction[] { createTestFileAction,
               editTestFileAction, compileTestFileAction,
               runTestFileAction, debugTestFileAction,
               compileAndRunTestFileAction,
               compileAndDebugTestFileAction, compileAndRunProjectAction,
               sepAction, configureAction,
               aboutAction };
      }
      
    
      /** {@inheritDoc} **/
      public List<CompilerEnvironmentLocation> getCompilerEnvironments() {
         URL index = JUnitTool.class.getResource("junit_index.jav");
         URL data = JUnitTool.class.getResource("junit_setup.jav");
         if (index == null || data == null) {
            return null;
         }
         CompilerEnvironmentLocation loc =
               new CompilerEnvironmentLocation(index, data);
         ArrayList<CompilerEnvironmentLocation> result =
              new ArrayList<CompilerEnvironmentLocation>();
         result.add(loc);
         return result;
      }
      
      
      /** Configures this tool.
       *
       *  @param context action context. **/
      private void configure(final ActionContext context) {
       
         if (configureDialog == null) {
            configureDialog = new JUnitSettingsDialog(this);
         }
         configureDialog.showDialog(context, settings);
      }
      
      
      /** Shows about text.
       *
       *  @param context action context. **/
      private void about(final ActionContext context) {
         String text; 
         if (settings.getJUnitHome() == null) {
            text = "jGRASP plugin for the JUnit testing tool.\n"
                  + "A JUnit distribution was not found. Use "
                  + "\"Configure\" to choose the JUnit home directory "
                  + "if JUnit is installed.\n"
                  + "JUnit and information about JUnit are "
                  + "available at http://www.junit.org .";
         }
         else {
            text = "jGRASP plugin for the JUnit development tool.\n"
                  + "JUnit and information about JUnit are "
                  + "available at http://www.junit.org .";
         }
         creationData.showMessage(context.getDialogParent(), text,
               "About jGRASP JUnit Plugin");
      }
      
   
      /** Creates a test file corresponding to the current source file.
       *
       *  @param context action context. **/
      private void createTestFile(final ActionContext context) {
         if (settings.getJUnitHome() == null) {
            return;
         }
         FileData fd = context.getActiveFile();
         if (fd == null) {
            return;
         }
         File testFile = getTestFile(fd.getFile(), false, context);
         if (testFile.exists()) {
            String result = creationData.showQuestion(
                  context.getDialogParent(), "JUnit Plugin",
                  "File \""
                  + testFile.getAbsolutePath() + "\" exists.\n\n",
                  "Use Existing File~Cancel", "Use Existing File");
            if (!result.equals("Use Existing File")) {
               return;
            }
            ToolUtil.setCompilerEnvironment(testFile, "Java", "Java (Test)");
            if (context.isInProject()) {
               ToolUtil.addToProject(testFile, true, context);
            }
            creationData.guiChanged();
            creationData.editFile(testFile);
            return;
         }
         
         File parent = testFile.getParentFile();
         if (!parent.exists()) {
            if (!ToolUtil.mkdirs(parent)) {
               creationData.showError(context.getDialogParent(),
                     "Could not create directory \""
                     + parent.getAbsolutePath() + "\".",
                     "JUnit Plugin Error");
               return;
            }
         }
         
         String pkg = ToolUtil.getJavaPackage(fd.getFile());
         StringBuilder testSrc = new StringBuilder();
         if (pkg != null && pkg.length() > 0) {
            testSrc.append("   package " + pkg);
            testSrc.append(";\n\n");
         }
      
         testSrc.append("   import org.junit.Assert;\n");
         testSrc.append("   import org.junit.Before;\n");
         testSrc.append("   import org.junit.Test;\n\n\n");
      
         String className = getTestClassName(fd.getFile(), false);
         testSrc.append("   public class " + className + " {\n\n\n");
         
         testSrc.append("      /** Fixture initialization (common "
               + "initialization\n"
               + "       *  for all tests). **/\n");
         testSrc.append("      @Before public void setUp() {\n");
         testSrc.append("      }\n\n\n");
         
         testSrc.append("     /** A test that always fails. **/\n");
         testSrc.append("      @Test public void defaultTest() {\n");
         testSrc.append("         Assert.assertEquals(\"Default test added by "
               + "jGRASP. Delete \"\n");
         testSrc.append("               + \"this test once you have "
               + "added your own.\", 0, 1);\n");
         testSrc.append("      }\n   }\n");
      
         Writer w;
         try {
            w = new BufferedWriter(new FileWriter(testFile));
         }
            catch (IOException e) {
               creationData.showMessage(context.getDialogParent(),
                     "Error creating test file \""
                     + testFile.getAbsolutePath() + "\": "
                     + e.getMessage(), "jGRASP JUnit Plugin");
               return;
            }
         try {
            w.write(testSrc.toString());
         }
            catch (IOException e) {
               creationData.showMessage(context.getDialogParent(),
                     "Error writing to test file \""
                     + testFile.getAbsolutePath() + "\": "
                     + e.getMessage(), "jGRASP JUnit Plugin");
            }
         try {
            w.close();
         }
            catch (IOException e) {
            }
         ToolUtil.setCompilerEnvironment(testFile, "Java", "Java (Test)");
         if (context.isInProject()) {
            ToolUtil.addToProject(testFile, true, context);
         }
         creationData.guiChanged();
         creationData.parentChanged(testFile);
         creationData.editFile(testFile);
      }
               
   
      /** Opens a test file corresponding to the current source file.
       *
       *  @param context action context. **/
      private void editTestFile(final ActionContext context) {
         FileData fd = context.getActiveFile();
         if (fd == null) {
            return;
         }
         File testFile = getTestFile(fd.getFile(), false, context);
         if (!testFile.exists()) {
            return;
         }
         creationData.editFile(testFile);
      }
               
   
      /** Runs JUnit test files.
       *
       *  @param context action context.
       *
       *  @param files info for the files that will be processed.
       *
       *  @param compileIfNecessary if true, the test file will
       *  be compiled if it is out of date.
       *
       *  @param afterCompile true if the file should be up to
       *  date, so that an error message should be given if it
       *  is not, false if no such test should be performed.
       *
       *  @param debug true if this will be a debug command,
       *  false otherwise.
       *
       *  @param h item to be highlighted during execution. This
       *  may be null. **/
      private void runTestFiles(final ActionContext context,
            final List<FileInfo> files, final boolean compileIfNecessary,
            final boolean afterCompile, final boolean debug,
            final Highlightable h) {
         // Check for edits on all project source files.
         Set<File> srcFiles = new HashSet<File>();
         FileData[] projFiles = context.getProjectFiles();
         if (projFiles != null) {
            for (FileData fd : projFiles) {
               File f = fd.getFile();
               if (f.getName().endsWith(".java")) {
                  srcFiles.add(f);
               }
            }
         }
      
         File workingDir = null;
         List<FileInfo> needCompiling = new ArrayList<FileInfo>();
         List<String> testClassNames = new ArrayList<String>();
         List<File> testFiles = new ArrayList<File>();
         for (FileInfo fi : files) {
            File f = fi.fl;
            if (f == null) {
               //*** Warning message.
               continue;
            }
            testFiles.add(f);
            testClassNames.add(fi.testClass);
            File testClassFile = getTestClassFile(f, true,  context);
            if (testClassFile == null || ToolUtil.isModified(f)
                  || f.lastModified() > testClassFile.lastModified()
                  || (fi.source != null && (fi.source.lastModified() >
                        testClassFile.lastModified()
                        || ToolUtil.isModified(fi.source)))) {
               needCompiling.add(fi);
            }
            if (workingDir == null) {
               String wkdir = ToolUtil.getPackageRoot(f);
               if (wkdir == null) {
                  //*** Error message.
                  return;
               }
               workingDir = new File(wkdir);
            }
            
            if (fi.source != null) {
               srcFiles.add(fi.source);
            }
         }
         if (testClassNames.size() == 0) {
            return;
         }
      
         if (needCompiling.size() == 0 && srcFiles.size() > 0
               && !context.checkDates(DateTestType.SOURCES,
               "JUnit", srcFiles)) {
            return;
         }
        
         if (needCompiling.size() > 0) {
            if (afterCompile) {
               creationData.showMessage(context.getDialogParent(),
                     "Run aborted because compilation appears to have failed"
                      + " (no errors, but class files are not up to date).",
                     "jGRASP JUnit Plugin");
               return;
            }
            if (compileIfNecessary) {
               compileTestFiles(context, needCompiling, files, debug, h);
               return;
            }
            else {
               //*** Warning message.
            }
         }
       
         File jarFile = settings.getJUnitJar();
      
         File cpDir = context.getProjectDir(DirType.CLASSES, true);
         String cpStr;
         if (cpDir != null) {
            cpStr = "CLASSPATH+=" + cpDir + "%;\n";
         }
         else {
            cpStr = "";
         }
      
         StringBuilder tcn = new StringBuilder();
         for (String c : testClassNames) {
            tcn.append(" \"");
            tcn.append(c);
            tcn.append("\"");
         }
         AfterExec runAction = new AfterExec(files);
         String cmd;
         ExecType type;
         if (debug) {
            cmd = "java -Xnoagent -Djava.compiler=NONE -Xdebug "
                  + "-Xrunjdwp:transport=%<TRANSPORT>,suspend=y,server=y "
                  + "org.junit.runner.JUnitCore";
            type = ExecType.DEBUG;
         }
         else {
            cmd = "java org.junit.runner.JUnitCore";
            type = ExecType.RUN;
         }
         
         context.executeCommand(cmd + tcn, type,
               "cu1-\\s*at (\\S+)\\.[^.]+\\(([^:]+):(\\d+)\\)",
               workingDir.getAbsolutePath(),
               "PATH+=%<JAVA_BIN_DIR>%;\n"
               + "PATH+=%<JGRASP_PATHS>%;\n"
               + "CLASSPATH+=%<JGRASP_CLASSPATHS>%;\n"
               + cpStr
               + "CLASSPATH+=%<REL_DEF_CLASSES_DIR>%;\n"
               + "CLASSPATH+=" + jarFile.getAbsolutePath() + "%;\n"
               + "PATH+=%<JAVA_BIN_DIR>%:\n"
               + "PATH+=%<JGRASP_PATHS>%:",
               "JUnit", h, runAction, runAction, "\\s*at\\s*"
               + "(sun\\.reflect|java\\.lang\\.reflect|org\\.junit).*",
               DateTestType.TARGETS, testFiles);
      }
   
   
      /** Compiles JUnit test files.
       *
       *  @param context action context.
       *
       *  @parma files info on files to be compiled.
       *
       *  @param runAfterFiles files to be run after compilation, or
       *  null if this will only compile files.
       *
       *  @param debugAfter true if <code>runAfterFiles</code> (if any)
       *  will be debugged after compliling, false if they will be run
       *  after compiling.
       *
       *  @param h item to be highlighted during compilation. This
       *  may be null. **/
      private void compileTestFiles(final ActionContext context,
            final List<FileInfo> files, final List<FileInfo> runAfterFiles,
            final boolean debugAfter, final Highlightable h) {
        
         // Check dates on all project source files.
         Set<File> srcFiles = new HashSet<File>();
         FileData[] projFiles = context.getProjectFiles();
         if (projFiles != null) {
            for (FileData fd : projFiles) {
               File f = fd.getFile();
               if (f.getName().endsWith(".java")) {
                  srcFiles.add(f);
               }
            }
         }
        
         File workingDir = null;
         StringBuilder filenames = new StringBuilder();
         boolean haveFiles = false;
         List<File> compileFiles = new ArrayList<File>();
         for (FileInfo fi : files) {
            if (fi.fl == null) {
               continue;
            }
            if (workingDir == null) {
               String wkdir = ToolUtil.getPackageRoot(fi.fl);
               if (wkdir == null) {
                  //*** Error message.
                  return;
               }
               workingDir = new File(wkdir);
            }
            haveFiles = true;
            String fn = ToolUtil.getSensibleFilename(fi.fl.getAbsolutePath(),
                  workingDir);
            filenames.append(" \"");
            filenames.append(fn);
            filenames.append("\"");
            
            compileFiles.add(fi.fl);
            
            if (fi.source != null) {
               srcFiles.add(fi.source);
            }
         
            creationData.autoCSD(fi.fl);
         }
         if (!haveFiles) {
            return;
         }
       
         if (srcFiles.size() > 0 && !context.checkDates(DateTestType.STRICT,
               "JUnit", srcFiles)) {
            return;
         }
      
         File jarFile = settings.getJUnitJar();
         
         RunLaterAction runAction = null;
         if (runAfterFiles != null) {
            runAction = new RunLaterAction(runAfterFiles, debugAfter, h);
         }
         
         File cpDir = context.getProjectDir(DirType.CLASSES, true);
         String cpStr;
         if (cpDir != null) {
            cpStr = "CLASSPATH+=" + cpDir + "%;\n";
         }
         else {
            cpStr = "";
         }
         context.executeCommand("javac -g " + filenames, ExecType.COMPILE,
               "f1-(\\S(?:\\s*\\S)*):(\\d+):.*",
               workingDir.getAbsolutePath(),
               "PATH+=%<JAVA_BIN_DIR>%;\n"
               + "PATH+=%<JGRASP_PATHS>%;\n"
               + "CLASSPATH+=%<JGRASP_CLASSPATHS>%;\n"
               + cpStr
               + "CLASSPATH+=%<REL_DEF_CLASSES_DIR>%;\n"
               + "CLASSPATH+=" + jarFile.getAbsolutePath() + "%;",
               "JUnit", h, runAction, null, null, DateTestType.SOURCES,
               compileFiles);
      }
   
   
      /** {@inheritDoc} **/
      public void applyConditions(final Conditions conditions) {
         activeJavaFile = false;
         FileData fd = conditions.getActiveFile();
         if (fd != null) {
            activeJavaFile = fd.hasLanguage("Java");
         }
         
         isTestFile = false;
         haveTestFile = false;
         if (activeJavaFile) {
            isTestFile = ToolUtil.isTestFile(fd.getFile(), conditions);
            File testFile = getTestFile(fd.getFile(), isTestFile, conditions);
            haveTestFile = !isTestFile && testFile != null
                  && testFile.exists()
                  && ToolUtil.isTestFile(testFile, conditions);
         }
      
         if (!conditions.isInProject()) {
            haveProjectTestFile = false;
            return;
         }
         // Check for test file corresponding to project file.
         
         haveProjectTestFile = false;
         for (FileData f : conditions.getProjectTestFiles()) {
            if (f.getFile().getName().endsWith(".java")) {
               haveProjectTestFile = true;
               break;
            }
         }
      }
          
   
      /** Called when the user has changed the settings.
       *
       *  @param newSettings the new settings. **/
      public void settingsChanged(final JUnitSettings newSettings) {
         settings = newSettings;
         creationData.storeData(JUnitTool.class, SETTINGS_ID,
               settings.getData());
         creationData.guiChanged();
      }
      
      
      /** Called after a check command successfully executes.
       *
       *  @param context context under which the results were
       *  determined.
       *
       *  @param data command post-execution data.
       *
       *  @param files the files that were tested.
       *
       *  @param failedClasses the files for which there were errors. **/
      private void processResults(final ActionContext context,
            final PostExecData data, final List<FileInfo> files,
            final Set<String> failedClasses) {
         long time = System.currentTimeMillis();
         StateStorage ss = StateStorage.getInstance();
         for (FileInfo f : files) {
            boolean failed = failedClasses.contains(f.testClass);
            JUnitStateData pfd = new JUnitStateData(time, failed, f.source);
            ss.storeData(context, dataId, f.fl, pfd);
            if (f.source != null) {
               pfd = new JUnitStateData(time, failed, f.fl);
               ss.storeData(context, dataId, f.source, pfd);
            }
         }
      }
      
   
      /** {@inheritDoc} **/
      public ResizableIcon getStateIcon(final ProjectState ps,
            final File fl, final boolean getSpacer) {
         if (getSpacer) {
            return new JUnitStateIcon(StateIcon.State.UNUSED);
         }
         JUnitStateData pfd = (JUnitStateData) StateStorage.getInstance().
               retrieveData(ps, dataId, fl);
         if (pfd == null) {
            return null;
         }
         File altFile = pfd.getAltFile();
         long lastMod = fl.lastModified();
         if (ToolUtil.isModified(fl) || lastMod > pfd.getTime()
               || (altFile != null
               && (ToolUtil.isModified(altFile)
               || altFile.lastModified() > pfd.getTime()))) {
            return new JUnitStateIcon(StateIcon.State.NEEDS_TESTING);
         }
         List<File> classes = ToolUtil.getClassFiles(fl, ps);
         for (File c : classes) {
            long cLastMod = c.lastModified();
            if (lastMod > cLastMod || cLastMod > pfd.getTime()) {
               return new JUnitStateIcon(StateIcon.State.NEEDS_TESTING);
            }
         }
         if (altFile != null) {
            long altMod = altFile.lastModified();
            classes = ToolUtil.getClassFiles(altFile, ps);
            for (File c : classes) {
               long cLastMod = c.lastModified();
               if (altMod > c.lastModified() || cLastMod > pfd.getTime()) {
                  return new JUnitStateIcon(StateIcon.State.NEEDS_TESTING);
               }
            }
         }
         return new JUnitStateIcon(pfd.failed()? StateIcon.State.FAIL
               : StateIcon.State.PASS);
      }
      
      
      /** {@inheritDoc} **/
      public String getClasspath() {
         File jarFile = settings.getJUnitJar();
         if (jarFile == null) {
            return null;
         }
         return jarFile.getAbsolutePath();
      }
      
      
      /** {@inheritDoc} **/
      public String getDataId() {
         return dataId;
      }
      
      
      /** {@inheritDoc} **/
      public ToolState createState() {
         return new JUnitStateData();
      }
   
   
      /** {@inheritDoc} **/
      public void fileRemoved(final Conditions conditions, final File fl,
            final boolean isTest) {
         if (!isTest) {
            return;
         }
         File srcFile = getSourceForTest(conditions, fl);
         if (srcFile == null) {
            return;
         }
         StateStorage ss = StateStorage.getInstance();
         ss.storeData(conditions, dataId, srcFile, null);
      }
   }

