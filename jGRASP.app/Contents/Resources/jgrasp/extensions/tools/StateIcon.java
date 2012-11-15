
   import java.awt.AlphaComposite;
   import java.awt.Color;
   import java.awt.Component;
   import java.awt.Composite;
   import java.awt.Graphics;
   import java.awt.Graphics2D;
   import java.awt.RenderingHints;
   
   import java.awt.geom.GeneralPath;
   
   import jgrasp.ResizableIcon;


   /** Base class for arbitrary-size icons that show a pass/fail state. **/
   public abstract class StateIcon implements ResizableIcon {
   
   
      /** Possible states. **/
      public enum State {
      
         /** Indicates a failed test. **/
         FAIL,
         
         /** Indicates a successful test. **/
         PASS,
         
         /** Indicates that a test needs to be performed. **/
         NEEDS_TESTING,
         
         /** Indicates that testing is not expected. **/
         UNUSED
      }
      
   
      /** Composite for smoothing edges. **/
      private static Composite blendComposite =
            AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .4f);
   
      /** Reusable array. **/
      private static float[] xpoints = new float[12];
   
      /** Reusable array. **/
      private static float[] ypoints = new float[12];
      
      /** Current icon width. **/
      private int iconW;
      
      /** Current icon height. **/
      private int iconH;
      
      /** Current icon state. **/
      private State state;
   
   
      /** Creates a new StateIcon.
       *
       *  @param stateIn the icon state. **/
      public StateIcon(final State stateIn) {
         state = stateIn;
      }
   
   
      /** {@inheritDoc} **/
      public void setHeight(final int h) {
         iconH = h;
         iconW = h;
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
         if (state == State.UNUSED) {
            return;
         }
         Graphics2D g = (Graphics2D) graphics;
         paintMainIcon(c, g, x, y);
         if (state == State.PASS) {
            paintCheck(g, x, y);
         }
         else if (state == State.FAIL) {
            paintX(g, x, y);
         }
      }
   
   
      /** Paints a green check mark.
       *
       *  @param g the graphics on which to paint.
       *
       *  @param x x position of the icon.
       *
       *  @param y y position of the icon. **/
      private void paintCheck(final Graphics2D g, final int x, final int y) {
         RenderingHints rh = g.getRenderingHints();
         g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
               RenderingHints.VALUE_STROKE_PURE);
      
         //int d = Math.min(iconW - 1, iconH - 1);
         int d = (Math.min(iconW - 1, iconH - 1) + 1) * 4 / 12 * 2;
         if (d < 1) {
            d = 1;
         }
         int sixth = (d + 4) / 6;
         int l = d * 2 / 3;
         int s = d - l;
         int extraW = iconW - d;
         int extraH = iconH - l - sixth;
         xpoints[0] = x + extraW / 2 + .5f;
         xpoints[1] = xpoints[0] + s;
         xpoints[4] = xpoints[1];
         xpoints[3] = xpoints[0] + d - sixth;
         xpoints[2] = xpoints[0] + d;
         xpoints[5] = xpoints[0] + sixth;
         
         ypoints[1] = y + iconH - extraH / 2 - .5f;
         ypoints[0] = ypoints[1] - s;
         ypoints[5] = ypoints[0] - sixth;
         ypoints[4] = ypoints[5] + s - sixth;
         ypoints[2] = ypoints[1] - l;
         ypoints[3] = ypoints[2] - sixth;
         
         GeneralPath path = new GeneralPath();
         path.moveTo(xpoints[0], ypoints[0]);
         for (int i = 1; i < 6; i++) {
            path.lineTo(xpoints[i], ypoints[i]);
         }
         g.setColor(Color.green);
         g.fill(path);
         path.lineTo(xpoints[0], ypoints[0]);
         g.setColor(Color.black);
         g.draw(path);
         g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
               RenderingHints.VALUE_ANTIALIAS_ON);
         Composite comp = g.getComposite();
         g.setComposite(blendComposite);
         g.draw(path);
         g.setComposite(comp);
      
         g.setRenderingHints(rh);
      }
   
   
      /** Paints a red X.
       *
       *  @param g the graphics on which to paint.
       *
       *  @param x x position of the icon.
       *
       *  @param y y position of the icon. **/
      private void paintX(final Graphics2D g, final int x, final int y) {
         RenderingHints rh = g.getRenderingHints();
         g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
               RenderingHints.VALUE_STROKE_PURE);
      
         int d = (Math.min(iconW - 1, iconH - 1) + 1) * 4 / 12 * 2;
         if (d < 1) {
            d = 1;
         }
         int d1 = (d + 4) / 6;
         int d2 = d / 2 - d1;
      
         int extraW = iconW - (d1 * 2 + d2 * 2);
         int extraH = iconH - (d1 * 2 + d2 * 2);
         
         xpoints[1] = x + extraW / 2 + .5f;
         xpoints[11] = xpoints[1];
         xpoints[0] = xpoints[1] + d2;
         xpoints[2] = xpoints[1] + d1;
         xpoints[10] = xpoints[2];
         xpoints[3] = xpoints[1] + d1 + d2;
         xpoints[9] = xpoints[3];
         xpoints[4] = xpoints[3] + d2;
         xpoints[8] = xpoints[4];
         xpoints[5] = xpoints[4] + d1;
         xpoints[7] = xpoints[5];
         xpoints[6] = xpoints[3] + d1;
         
         ypoints[2] = x + iconH - extraH / 2 - .5f;
         ypoints[4] = ypoints[2];
         ypoints[1] = ypoints[2] - d1;
         ypoints[5] = ypoints[1];
         ypoints[3] = ypoints[2] - d2;
         ypoints[0] = ypoints[3] - d1;
         ypoints[6] = ypoints[0];
         ypoints[9] = ypoints[0] - d1;
         ypoints[11] = ypoints[0] - d2;
         ypoints[7] = ypoints[11];
         ypoints[10] = ypoints[11] - d1;
         ypoints[8] = ypoints[10];
         
         GeneralPath path = new GeneralPath();
         path.moveTo(xpoints[0], ypoints[0]);
         for (int i = 1; i < 12; i++) {
            path.lineTo(xpoints[i], ypoints[i]);
         }
         g.setColor(Color.red);
         g.fill(path);
         path.lineTo(xpoints[0], ypoints[0]);
         g.setColor(Color.black);
         g.draw(path);
         g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
               RenderingHints.VALUE_ANTIALIAS_ON);
         Composite comp = g.getComposite();
         g.setComposite(blendComposite);
         g.draw(path);
         g.setComposite(comp);
      
         g.setRenderingHints(rh);
      }
   
   
      /** Paints the main part of the icon. Pass/fail indicators
       *  will be painted on top of this.
       *
       *  @param c the component on which the icon will be painted.
       *
       *  @param g the graphics on which to paint.
       *
       *  @param x x position of the icon.
       *
       *  @param y y position of the icon. **/
      public abstract void paintMainIcon(final Component c,
            final Graphics2D g, final int x, final int y);
   }
