
   import java.awt.BorderLayout;
   import java.awt.Color;
   import java.awt.Dimension;
   import java.awt.Graphics;
   import java.awt.GridBagConstraints;
   import java.awt.Insets;
   import java.awt.Rectangle;
  
   import java.awt.event.MouseAdapter;
   import java.awt.event.MouseEvent;
   import java.awt.event.MouseMotionAdapter;
   
   import javax.swing.BorderFactory;
   import javax.swing.JComponent;
   import javax.swing.JPanel;
   import javax.swing.JSplitPane;
   import javax.swing.JTextArea;

   import jgrasp.Viewer;
   
   import jgrasp.viewer.ViewerCreateData;
   import jgrasp.viewer.ViewerException;
   import jgrasp.viewer.ViewerInfo;
   import jgrasp.viewer.ViewerInitData;
   import jgrasp.viewer.ViewerUpdateData;
   import jgrasp.viewer.ViewerValueData;

   import jgrasp.viewer.jgrdi.DebugContext;
   import jgrasp.viewer.jgrdi.Method;
   import jgrasp.viewer.jgrdi.Value;


   /** A viewer that shows relative component positions and sizes. **/
    public class java__awt__Component_ComponentView
         extends JPanel implements Viewer {
   
   
      /** A component boundary description. **/
       private static class Bounds {
       
         /** The bounds. **/
         private Rectangle bounds = new Rectangle();
         
         /** Bounds of all the children. **/
         private Bounds[] children;
         
         /** The component class name. **/
         private String type;
         
      
         /** {@inheritDoc} **/
          public String toString() {
            StringBuilder sb = new StringBuilder();
            getDescription(sb, "");
            return sb.toString();
         }
          
          
          private void getDescription(final StringBuilder sb,
               final String indent) {
            sb.append(indent);
            sb.append(type);
            sb.append(" (");
            sb.append(bounds.x);
            sb.append(", ");
            sb.append(bounds.y);
            sb.append(") ");
            sb.append(bounds.width);
            sb.append("x");
            sb.append(bounds.height);
            sb.append("\n");
            if (children != null) {
               for (Bounds cb : children) {
                  cb.getDescription(sb, indent + "   ");
               }
            }
         }
      }
   
   
      /** The gui root panel. **/
      private JPanel mainPanel;
   
      /** The bounds display panel. **/
      private JPanel displayPanel;
      
      /** Text display (shows types of components under mouse). **/
      private JTextArea textArea;
   
      /** The boundary hierarchy. This will be accessed by both the
       *  debugger and gui threads, so it should be read only once
       *  wherever the value must remain consistent. **/
      private Bounds topBounds;
      
      /** True if the mouse is outside the component display,
       *  false otherwise. **/
      private boolean mouseOut = true;
   
   
      /** Creates a new component viewer.
       *
       *  @param vcd viewer creation data. **/
       public java__awt__Component_ComponentView(final ViewerCreateData vcd) {
         super(new BorderLayout());
      }
        
      
      /** {@inheritDoc} **/
       public void build(final ViewerInitData vid) {
         vid.setAutoUpdate(true);
      }
      
      
      /** Builds the user interface. **/
       private void buildGui() {
         mainPanel = 
             new JPanel(new BorderLayout()) {
                public void updateUI() {
                  super.updateUI();
                  BorderLayout layout = (BorderLayout) getLayout();
                  int gap = 1 + getFont().getSize() / 2;
                  layout.setHgap(gap);
                  layout.setVgap(gap);
                  setBorder(BorderFactory.createEmptyBorder(gap, gap,
                        gap, gap));
               }
            };
         displayPanel = 
             new JPanel() {
                public Dimension getPreferredSize() {
                  return new Dimension(0, 0);
               }
            
                public void paintComponent(final Graphics g) {
                  paintDisplay(g);
               }
            };
         displayPanel.addMouseMotionListener(
                new MouseMotionAdapter() {
                   public void mouseMoved(final MouseEvent e) {
                     handleMouseMove(e.getX(), e.getY());
                  }
               });
         displayPanel.addMouseListener(
                new MouseAdapter() {
                   public void mouseExited(final MouseEvent e) {
                     mouseOut = true;
                     if (topBounds == null) {
                        textArea.setText("");
                     }
                     else {
                        textArea.setText(topBounds.toString());
                     }
                  }
               });
         JSplitPane splitPane = new JSplitPane();
         splitPane.setResizeWeight(.5);
         mainPanel.add(splitPane, "Center");
         splitPane.setTopComponent(displayPanel);
         textArea = new JTextArea();
         textArea.setEditable(false);
         splitPane.setBottomComponent(textArea);
         
         GridBagConstraints constraints = new GridBagConstraints();
         Insets insets = constraints.insets;
         int spacing = 4;
         constraints.weightx = .001;
         constraints.weighty = .001;
         insets.top = spacing;
         insets.bottom = spacing;
         insets.right = spacing;
         insets.left = spacing;
         constraints.gridwidth = GridBagConstraints.REMAINDER;
         constraints.fill = GridBagConstraints.NONE;
         //constraints.anchor = GridBagConstraints.WEST;
      
         if (topBounds != null) {
            textArea.setText(topBounds.toString());
         }
      }
      
      
      /** {@inheritDoc} **/
       public void destroy() {
      }
   
   
      /** {@inheritDoc} **/
       public JComponent getComponent() {
         if (mainPanel == null) {
            buildGui();
         }
         return mainPanel;
      }
        
          
      /** {@inheritDoc} **/
       public String getViewName() {
         return "Component";
      }
   
   
      /** {@inheritDoc} **/
       public int getPriority() {
         return 10;
      }
   
   
      /** {@inheritDoc} **/
       public void setFrozen() {
      }
   
      
      /** {@inheritDoc} **/
       public void update(final ViewerValueData valueData,
            final ViewerUpdateData data, final DebugContext context)
            throws ViewerException {
         Value value = valueData.getValue();
         topBounds = getBounds(value, context);
         if (mouseOut) {
            textArea.setText(topBounds.toString());
         }
         if (displayPanel != null) {
            displayPanel.repaint();
         }
      }
         
   
      /** Gets component boundary information.
       *
       *  @param value the component.
       *
       *  @param context the current debugger context.
       *
       *  @return boundary information for the component.
       *
       *  @throws ViewerException if an error occurs while retrieving
       *  the bounds. **/
       private Bounds getBounds(final Value value, final DebugContext context)
            throws ViewerException {
         Bounds b = new Bounds();
         Method getBoundsMethod = value.getMethod(context,
               "getBounds", "java.awt.Rectangle", null);
         Value bounds = value.invokeMethod(context, getBoundsMethod,
               null);
         String type = value.getType(context).getName(context);
         int dot = type.lastIndexOf('.');
         if (dot >= 0) {
            type = type.substring(dot + 1);
         }
         b.type = type;
         
         try {
            Method getTextMethod = value.getMethod(context, "getText",
                  "java.lang.String", null);
            Value result = value.invokeMethod(context, getTextMethod,
                  null);
            if (!result.isNull()) {
               String str = result.toString(context);
               if (str.length() > 10) {
                  str = str.substring(0, 7) + "...";
               }
               b.type += " \"" + str + "\"";
            }
         }
             catch (ViewerException e) {
            }
         
         b.bounds.x = bounds.getFieldValue(context, "x").toInt(context);
         b.bounds.y = bounds.getFieldValue(context, "y").toInt(context);
         b.bounds.width = bounds.getFieldValue(context, "width").
               toInt(context);
         b.bounds.height = bounds.getFieldValue(context, "height").
               toInt(context);
         if (value.isInstanceOf(context, "java.awt.Container")) {
            Method getComponentsMethod = value.getMethod(context,
                  "getComponents", "java.awt.Component[]", null);
            Value children;
            try {
               children = value.invokeMethod(context,
                  getComponentsMethod, null);
            }
                catch (ViewerException e) {
                  b.children = new Bounds[0];
                  return b;
               }
            b.children = new Bounds[children.getArrayLength(context)];
            for (int c = 0; c < b.children.length; c++) {
               b.children[c] = getBounds(children.getArrayElement(context,
                  c), context);
            }
         }
         return b;      
      }
   
   
      /** {@inheritDoc} **/
       public void getInfo(final ViewerInfo vi) {
         vi.setShortDescription("Detail viewer for components");
         vi.setLongDescription("This viewer displays the component shape "
               + "and the shapes of its descendents.");
      }
      
      
      /** Paints the bounds rectangles on the display panel.
       *
       *  @param g graphics on which the rendering will be performed. **/
       public void paintDisplay(final Graphics g) {
         int width = displayPanel.getWidth() - 1;
         int height = displayPanel.getHeight() - 1;
         g.setColor(getBackground());
         g.fillRect(0, 0, width + 1, height + 1);
         Bounds bounds = topBounds;
         if (bounds == null) {
            return;
         }
          
         Rectangle b = bounds.bounds;
         double scale = Math.min(width / (double) b.width,
               height / (double) b.height);
         double xOffs = (width - b.width * scale) / 2.0;
         double yOffs = (height - b.height * scale) / 2.0;
         
         paintBounds(g, xOffs, yOffs, scale, bounds, 0);
      }
      
     
      /** Paints the bounds of a component and its children.
       *
       *  @param g graphics on which the rendering will be performed.
       *
       *  @param xOffset x offset of the parent.
       *
       *  @param yOffset y offset of the parent.
       *
       *  @param scale scale multiplier from bounds units to
       *  graphics units.
       *
       *  @param bounds bounds of the component that is being painted.
       *
       *  @param level the depth in the hierarchy. **/
       private void paintBounds(final Graphics g, final double xOffset,
            final double yOffset, final double scale, final Bounds bounds,
            final int level) {
         Rectangle b = bounds.bounds;
         
         double xOffs = xOffset;
         double yOffs = yOffset;
         if (level > 0) {
            xOffs += b.x * scale;
            yOffs += b.y * scale;
         }
            
         int i = (int) Math.max(.5, 255 * (1 - .05 * level));
         g.setColor(new Color(i, i, i));
         g.fillRect((int) xOffs, (int) yOffs,
               (int) (xOffs + b.width * scale) - (int) xOffs,
               (int) (yOffs + b.height * scale) - (int) yOffs);
         g.setColor(Color.black);
         g.drawRect((int) xOffs, (int) yOffs,
               (int) (xOffs + b.width * scale) - (int) xOffs,
               (int) (yOffs + b.height * scale) - (int) yOffs);
         for (int c = 0; c < bounds.children.length; c++) {
            paintBounds(g, xOffs, yOffs, scale, bounds.children[c],
                  level + 1);
         }
      }
   
   
      /** Responds to mouse motion.
       *
       *  @param x mouse x position.
       *
       *  @param y mouse y position. **/
       public void handleMouseMove(final int x, final int y) {
         int width = displayPanel.getWidth() - 1;
         int height = displayPanel.getHeight() - 1;
         Bounds bounds = topBounds;
         if (bounds == null) {
            mouseOut = true;
            textArea.setText("");
            return;
         }
          
         Rectangle b = bounds.bounds;
         double scale = Math.min(width / (double) b.width,
               height / (double) b.height);
         double xOffs = (width - b.width * scale) / 2.0;
         double yOffs = (height - b.height * scale) / 2.0;
         
         StringBuilder result = new StringBuilder();
         findComponents(x, y, xOffs, yOffs, scale, bounds, 0,
               result);
         String resultStr = result.toString();
         if (resultStr.length() > 0) {
            mouseOut = false;
            textArea.setText(resultStr);
         }
         else {
            mouseOut = true;
            textArea.setText(topBounds.toString());
         }
      }
      
      
      /** Gets a text description of the components at a certain position.
       *
       *  @param x x position of mouse.
       *
       *  @param y y position of mouse.
       *
       *  @param xOffset x offset from parent position.
       *
       *  @param yOffset y offset from parent position.
       *
       *  @param scale scale multiplier from bounds units to
       *  graphics units.
       *
       *  @param bounds bounds of the component that is being searched.
       *
       *  @param level the depth in the hierarchy.
       *
       *  @param textOut the text result. **/
       private void findComponents(final int x, final int y,
            final double xOffset, final double yOffset, final double scale,
            final Bounds bounds, final int level,
            final StringBuilder textOut) {
         Rectangle b = bounds.bounds;
      
         double xOffs = xOffset;
         double yOffs = yOffset;
         if (level > 0) {
            xOffs += b.x * scale;
            yOffs += b.y * scale;
         }
         
         Rectangle rect = new Rectangle((int) xOffs, (int) yOffs,
               (int) (xOffs + b.width * scale) - (int) xOffs,
               (int) (yOffs + b.height * scale) - (int) yOffs);
         if (!rect.contains(x, y)) {
            return;
         }
         for (int l = 0; l < level; l++) {
            textOut.append(" ");
         }
         textOut.append(bounds.type + " (" + b.x + ", " + b.y + ") "
               + b.width + "x" + b.height + "\n");
         for (int c = 0; c < bounds.children.length; c++) {
            findComponents(x, y, xOffs, yOffs, scale, bounds.children[c],
                  level + 1, textOut);
         }
      }
   }
