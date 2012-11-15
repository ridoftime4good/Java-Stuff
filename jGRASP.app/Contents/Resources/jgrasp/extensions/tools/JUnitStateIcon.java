
   import java.awt.Color;
   import java.awt.Component;
   import java.awt.GradientPaint;
   import java.awt.Graphics2D;
   import java.awt.Paint;
   import java.awt.RenderingHints;

   import java.awt.geom.GeneralPath;


   /** State icon for JUnit. **/
   public class JUnitStateIcon extends StateIcon {
   
      /** Reusable array. **/
      private static float[] xpoints = new float[3];
   
      /** Reusable array. **/
      private static float[] ypoints = new float[3];
   
   
      /** Creates a new JUnitStateIcon.
       *
       *  @param stateIn the icon state. **/
      public JUnitStateIcon(final State state) {
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
         g.setPaint(new GradientPaint(xoffs, yoffs, Color.red,
               xoffs + d * 2 / 5, yoffs + d * 2 / 5, Color.white));
         g.fillRect(xoffs, yoffs, d, d);
         g.setPaint(new GradientPaint(xoffs + d, yoffs + d, Color.green,
               xoffs + d - d * 2 / 5, yoffs + d - d * 2 / 5,
               Color.white));
         xpoints[0] = xoffs;
         xpoints[1] = xoffs + d;
         xpoints[2] = xpoints[1];
         ypoints[0] = yoffs + d;
         ypoints[1] = ypoints[0];
         ypoints[2] = yoffs;
         GeneralPath path = new GeneralPath();
         path.moveTo(xpoints[0], ypoints[0]);
         for (int i = 1; i < 3; i++) {
            path.lineTo(xpoints[i], ypoints[i]);
         }
         g.fill(path);
         g.setPaint(p);
         g.setColor(Color.black);
         g.drawRect(xoffs, yoffs, d, d);
         
         g.setRenderingHints(rh);
      }
   }

