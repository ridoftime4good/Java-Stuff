
   import java.awt.BorderLayout;
   import java.awt.Color;
   import java.awt.Dimension;
   
   import javax.swing.BorderFactory;
   import javax.swing.JComponent;
   import javax.swing.JPanel;
   import javax.swing.JScrollPane;
   import javax.swing.JTextArea;
   import javax.swing.SwingUtilities;

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


   /** A viewer that displays a java.awt.Color with a color
    *  swatch and RGB components. **/
   public class java__awt__Color_ColorView implements Viewer {
   
      /** The gui root panel. **/
      private JPanel mainPanel;
   
      /** The color swatch panel. **/
      private JPanel colorPanel;
      
      /** The text display. **/
      private JTextArea textArea;
   
   
      /** Creates a new color viewer.
       *
       *  @param vcd viewer creation data. **/
      public java__awt__Color_ColorView(final ViewerCreateData vcd) {
      }
        
         
      /** {@inheritDoc} **/
      public void build(final ViewerInitData vid) {
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
                  setBorder(BorderFactory.createEmptyBorder(gap,
                        gap, gap, gap));
               }
            };
         colorPanel = 
            new JPanel() {
               public Dimension getPreferredSize() {
                  int ht = getFont().getSize();
                  return new Dimension(ht * 2, ht * 2);
               }
            };
         colorPanel.setBorder(BorderFactory.createEtchedBorder());
         mainPanel.add(colorPanel, "North");
         textArea = new JTextArea();
         textArea.setEditable(false);
         mainPanel.add(new JScrollPane(textArea), "Center");
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
         return "Color";
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
         Method getRgbMethod = value.getMethod(context, "getRGB", "int",
               null);
         final int rgb = value.invokeMethod(context, getRgbMethod, null).
               toInt(context);
         
         StringBuilder textBuild = new StringBuilder();
         textBuild.append("red = ");
         textBuild.append((rgb & 0xff0000) >> 16);
         textBuild.append("\ngreen = ");
         textBuild.append((rgb & 0xff00) >> 8);
         textBuild.append("\nblue = ");
         textBuild.append(rgb & 0xff);
         int alpha = rgb >>> 24;
         if (alpha < 255) {
            textBuild.append("\nalpha = ");
            textBuild.append(alpha);
         }
         final String text = textBuild.toString();
      
         SwingUtilities.invokeLater(
               new Runnable() {
                  public void run() {
                     if (mainPanel == null) {
                        buildGui();
                     }
                     updateGui(rgb, text);
                  }
               });
      }
   
   
      /** Updates the user interface with new display values.
       *
       *  @param rgb the new rgb color value.
       *
       *  @param text the new display text. **/
      private void updateGui(final int rgb, final String text) {
         Color color = new Color(rgb);
         colorPanel.setBackground(color);
         textArea.setText(text);
      }
       
       
      /** {@inheritDoc} **/
      public void getInfo(final ViewerInfo vi) {
         vi.setShortDescription("Detail viewer for colors");
         vi.setLongDescription("This viewer displays the RGBA "
               + "components of a color, and displays a square "
               + "of the color.");
      }
   }
