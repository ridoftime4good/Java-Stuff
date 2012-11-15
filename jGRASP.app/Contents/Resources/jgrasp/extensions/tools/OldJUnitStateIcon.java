
   import java.awt.Color;
   import java.awt.Component;
   import java.awt.Graphics;
   import java.awt.Graphics2D;
   import java.awt.Rectangle;
   import java.awt.RenderingHints;
   
   import java.awt.font.GlyphVector;

   import java.awt.geom.GeneralPath;
   
   import javax.swing.Icon;


   /** Per-project per-file state for JUnit plugin. These
    *  are different colors of T-shaped icons. **/
   public class OldJUnitStateIcon implements Icon {
   
      /** Current icon width. **/
      private int iconW;
      
      /** Current icon height. **/
      private int iconH;
   
      /** The fill color. **/
      private Color iconColor;
   
      /** Reusable array. **/
      private static float[] xpoints = new float[9];
   
      /** Reusable array. **/
      private static float[] ypoints = new float[9];
   
   
      /** Creates a new JUnitStateIcon.
       *
       *  @param h the icon height.
       *
       *  @param color the icon color. **/
      public OldJUnitStateIcon(final int h, final Color color) {
         iconH = h * 2 / 3;
         iconW = h * 2 / 3;
         iconColor = color;
      }
   
   
      /** {@inheritDoc} **/
      public int getIconHeight() {
         return iconH;
      }
   
   
      /** {@inheritDoc} **/
      public int getIconWidth() {
         return iconW;
      }
   
   
      /** {@inheritDoc} **/
      public void paintIcon(final Component c, final Graphics graphics,
            final int x, final int y) {
         Graphics2D g = (Graphics2D) graphics;
      
         RenderingHints rh = g.getRenderingHints();
         g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
               RenderingHints.VALUE_STROKE_PURE);
      
         int d = Math.min(iconW, iconH);
         int w = d - 4;
         int thirdW = w / 3;
         int wingW = (w - thirdW) / 2;
         
         xpoints[0] = ((iconW - (wingW * 2 + thirdW + 4) + 1) / 2) + .5f;
         xpoints[7] = xpoints[0];
         xpoints[1] = xpoints[0] + wingW * 2 + thirdW + 3;
         xpoints[2] = xpoints[1];
         xpoints[3] = xpoints[1] - wingW - 1;
         xpoints[4] = xpoints[3];
         xpoints[5] = xpoints[0] + wingW + 1;
         xpoints[6] = xpoints[5];
         xpoints[8] = xpoints[0];
         ypoints[0] = ((iconH - d + 1) / 2) + .5f;
         ypoints[1] = ypoints[0];
         ypoints[2] = ypoints[0] + thirdW + 1;
         ypoints[3] = ypoints[2];
         ypoints[6] = ypoints[2];
         ypoints[7] = ypoints[2];
         ypoints[4] = ypoints[0] + d - 1;
         ypoints[5] = ypoints[4];
         ypoints[8] = ypoints[0];
      
         GeneralPath path = new GeneralPath();
         path.moveTo(xpoints[0], ypoints[0]);
         for (int i = 1; i < 8; i++) {
            path.lineTo(xpoints[i], ypoints[i]);
         }
         g.setColor(iconColor);
         g.fill(path);
         path.lineTo(xpoints[8], ypoints[8]);
         g.setColor(Color.black);
         g.draw(path);
      
         g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
               RenderingHints.VALUE_ANTIALIAS_ON);
         GlyphVector gv = g.getFont().createGlyphVector(
               g.getFontRenderContext(), "S");
            
         Rectangle bounds = gv.getPixelBounds(
               g.getFontRenderContext(), 0.0f, 0.0f);
         int maxYGap = (iconH - 3 - (int) bounds.getHeight() + 1) / 2;
         int yoffs = iconH - (int) bounds.getMaxY()
               - Math.min((iconH + 3) / 6, maxYGap) - 1;
            
         int xoffs = (int) ((iconW - bounds.getWidth()) / 2.0
               - bounds.getMinX() - .5);
         g.drawGlyphVector(gv, x + xoffs, y + yoffs);
      
      
         g.setRenderingHints(rh);
      }
   
   }
