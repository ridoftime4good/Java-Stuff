
   import java.io.File;

   import java.util.regex.Matcher;
   import java.util.regex.Pattern;


   /** Class for storing a tool home directory and jar file. **/
   public class HomeAndJar implements Comparable<HomeAndJar> {
    
      /** The home directory. **/
      private File home;
      
      /** The jar file. **/
      private File jar;
      
      /** Version number components. **/
      private Integer[] version;
      
   
      /** Creates a new HomeAndJar.
       *
       *  @param homeDir the home directory.
       *
       *  @param jarFile the jar file.
       *
       *  @param pattern text of regular expression used
       *  to determine version number. The matched groups
       *  will be assumed to be version number components
       *  in order of importance. If this is null, there
       *  will be no version number recorded.
       *
       *  @param versionInJar true if the version number
       *  is to be found in the jar file name, false if it
       *  is to be found in the home directory name. **/
      public HomeAndJar(final File homeDir, final File jarFile,
            final String pattern, final boolean versionInJar) {
         home = homeDir;
         jar = jarFile;
         
         if (pattern == null) {
            version = new Integer[0];
            return;
         }
         
         Pattern verPattern = Pattern.compile(pattern);
         Matcher verMatcher;
         if (versionInJar) {
            String jarName = jarFile.getName();
            verMatcher = verPattern.matcher(jarName);
         }
         else {
            String homeName = homeDir.getName();
            verMatcher = verPattern.matcher(homeName);
         }
         if (verMatcher.matches()) {
            int count = verMatcher.groupCount();
            if (count == 1 && verMatcher.group(1).indexOf('.') >= 0) {
               String[] components = verMatcher.group(1).split("\\.");
               version = new Integer[components.length];
               for (int i = 0; i < components.length; i++) {
                  try {
                     version[i] = Integer.valueOf(components[i]);
                  }
                     catch (NumberFormatException e) {
                     }
               }
            }
            else {
               version = new Integer[count];
               for (int i = 0; i < count; i++) {
                  String versionComponent = verMatcher.group(i + 1);
                  try {
                     version[i] = Integer.valueOf(versionComponent);
                  }
                     catch (NumberFormatException e) {
                     }
               }
            }
         }
         else {
            version = new Integer[0];
         }
         // System.out.println("jar " + jarFile);
         // for (Integer i : version) {
            // System.out.println("   " + i);
         // }
      }
      
      
      /** Gets the home directory.
       *
       *  @return the home directory. **/
      public File getHomeDirectory() {
         return home;
      }
      
    
      /** Gets the jar file.
       *
       *  @return the jar file. **/
      public File getJarFile() {
         return jar;
      }
   
   
      /** {@inheritDoc}
       *
       *  <P>Overridden to return the home directory path string. **/
      public String toString() {
         return home.getAbsolutePath();
      }
      
      
      /** {@inheritDoc}
       *
       *  <P>Sorts from highest to lowest version number,
       *  jar file name, then home directory name. **/
      public int compareTo(final HomeAndJar haj) {
         int i;
         for (i = 0; i < version.length && i < haj.version.length; i++) {
            int result = version[i].compareTo(haj.version[i]);
            if (result != 0) {
               return -result;
            }
         }
         if (i < version.length) {
            return -1;
         }
         if (i < haj.version.length) {
            return 1;
         }
      
         int result = jar.getName().compareTo(haj.jar.getName());
         if (result != 0) {
            return result;
         }
         result = home.getName().compareTo(haj.home.getName());
         if (result != 0) {
            return result;
         }
         return home.compareTo(haj.home);
      }
      
    
      /** {@inheritDoc} **/
      public boolean equals(final Object o) {
         if (!(o instanceof HomeAndJar)) {
            return false;
         }
         HomeAndJar haj = (HomeAndJar) o;
         if (version.length != haj.version.length) {
            return false;
         }
         for (int i = 0; i < version.length; i++) {
            if (!version[i].equals(haj.version[i])) {
               return false;
            }
         }
         return jar.equals(haj.jar) && home.equals(haj.home);
      }
    
    
      /** {@inheritDoc} **/
      public int hashCode() {
         return getJarFile().hashCode();
      }
      
   
      /** Gets the version number info.
       *
       *  @return an array containing the version number
       *  segments in order, or an empty array if there is
       *  no known version number. **/
      public Integer[] getVersion() {
         return version.clone();
      }
   }
