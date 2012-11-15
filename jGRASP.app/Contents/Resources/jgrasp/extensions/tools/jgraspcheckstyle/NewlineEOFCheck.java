   package jgraspcheckstyle;


   import com.puppycrawl.tools.checkstyle.api.AbstractFileSetCheck;
   import com.puppycrawl.tools.checkstyle.api.Utils;
   
   import java.io.File;
   import java.io.IOException;
   import java.io.RandomAccessFile;
   
   import java.util.List;


   /** Newline-at-end-of-file check that allows \r or \n at
    *  end of line. **/
    public class NewlineEOFCheck extends AbstractFileSetCheck {
    
    
       protected void processFiltered(File aFile, List<String> aLines) {
      
         RandomAccessFile randomAccessFile = null;
         try {
            randomAccessFile = new RandomAccessFile(aFile, "r");
            if (!endsWithNewline(randomAccessFile)) {
               log(aLines.size(), "File does not end with a newline.",
                     aFile.getPath());
            }
         }
             catch (IOException e) {
               log(0, "unable.open", aFile.getPath());
            }
         finally {
            Utils.closeQuietly(randomAccessFile);
         }
      }
   
   
       private boolean endsWithNewline(RandomAccessFile aRandomAccessFile)
        throws IOException {
         if (aRandomAccessFile.length() < 1) {
            return false;
         }
         aRandomAccessFile.seek(aRandomAccessFile.length() - 1);
         final byte[] lastByte = new byte[1];
         final int readBytes = aRandomAccessFile.read(lastByte);
         if (readBytes != 1) {
            throw new IOException("Unable to read one byte, got "
                    + readBytes);
         }
         return lastByte[0] == '\r' || lastByte[0] == '\n';
      }
   }
