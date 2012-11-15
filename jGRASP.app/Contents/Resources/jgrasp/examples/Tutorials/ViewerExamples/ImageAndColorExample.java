
   import java.awt.Color;
   import java.awt.Image;
   import java.awt.Toolkit;

   import java.util.Random;

   import javax.swing.ImageIcon;


  /** CollectionsExample -- This example is intended to
   *  illustrate the capabilities of the jGRASP viewers for
   *  java.awt.Image, javax.swing.ImageIcon, and java.awt.Color.
   *  Set a breakpoint inside the inner loop and launch the
   *  debugger. After program stops at the breakpoint, open
   *  viewers on one or more variables by "dragging" the variable
   *  out of the Debug tab and releasing it. Now hit resume to
   *  perform one execution of the loop. With AutoResume turned
   *  on, the debugger will repeatedly execute the loop when you
   *  click the Resume button. Turning off AutoResume returns the
   *  debugger to single resume mode. **/
    public class ImageAndColorExample {
      
      private static Image image1 = Toolkit.getDefaultToolkit().
            createImage("testimg.png");
      
      private static Image image2 = Toolkit.getDefaultToolkit().
            createImage("testimg2.png");
      
      private static Image image3 = Toolkit.getDefaultToolkit().
      		createImage("testimg3.png");
      
      private static Image[] images = new Image[] { image1, image2, image3 };
   
      static {
         Toolkit tk = Toolkit.getDefaultToolkit();
         for (int i = 0; i < images.length; i++) {
            tk.prepareImage(images[i], -1, -1, null);
         }
      }
   
      private static Random random = new Random();
   
   
       public static void main(String[] args) {
         Image image = images[0];
         ImageIcon ii = new ImageIcon(images[2]);
         Color color = new Color(random.nextInt());
      
         while (true) {
            for (int i = 0; i < images.length; i++) {
               image = images[(i + 1) % images.length];
               ii.setImage(images[i]);
               color = new Color(random.nextInt());
            }
         }
      }
   }