
   import java.awt.Color;
   import java.awt.Component;
   import java.awt.GradientPaint;
   import java.awt.Graphics2D;
   import java.awt.Paint;
   import java.awt.RenderingHints;


   /** State icon for Checkstyle. **/
   public class CheckstyleStateIcon extends StateIcon {
   
      /** Upper left color for background gradient. **/
      private static Color bg1 = new Color(255, 210, 0);
   
      /** Lower right color for background gradient. **/
      private static Color bg2 = new Color(208, 170, 0);
   
   
      /** Creates a new CheckstyleStateIcon.
       *
       *  @param stateIn the icon state. **/
      public CheckstyleStateIcon(final State state) {
         super(state);
      }
   
   
      /** {@inheritDoc} **/
      public void paintMainIcon(final Component c,
            final Graphics2D g, final int x, final int y) {
         RenderingHints rh = g.getRenderingHints();
         g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
               RenderingHints.VALUE_STROKE_PURE);
         int w = getIconWidth();
         int h = getIconHeight();
         int d = (Math.min(w - 1, h - 1) + 1) * 5 / 12 * 2;
         int xoffs = x + (w - d) / 2;
         int yoffs = y + h - 1 - (h - d) / 2 - d;
      
         Paint p = g.getPaint();
         g.setPaint(new GradientPaint(xoffs, yoffs, bg1,
               xoffs + d, yoffs + d, bg2));
         g.fillRect(xoffs, yoffs, d, d);
         g.setPaint(p);
         
         g.setColor(Color.black);
         g.drawRect(xoffs, yoffs, d, d);
         
         g.setRenderingHints(rh);
      }
   }

