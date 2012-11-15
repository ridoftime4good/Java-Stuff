
   import java.awt.BorderLayout;
   import java.awt.Dimension;
   import java.awt.Graphics;
   import java.awt.Image;
   import java.awt.Insets;
   
   import java.awt.image.BufferedImage;
   
   import java.util.List;
   
   import javax.swing.BorderFactory;
   import javax.swing.JComponent;
   import javax.swing.JPanel;
   import javax.swing.JTextField;
   import javax.swing.SwingUtilities;

   import javax.swing.border.EtchedBorder;

   import jgrasp.Viewer;
   
   import jgrasp.viewer.ViewerCreateData;
   import jgrasp.viewer.ViewerException;
   import jgrasp.viewer.ViewerInfo;
   import jgrasp.viewer.ViewerInitData;
   import jgrasp.viewer.ViewerUpdateData;
   import jgrasp.viewer.ViewerValueData;

   import jgrasp.viewer.jgrdi.Constructor;
   import jgrasp.viewer.jgrdi.DebugContext;
   import jgrasp.viewer.jgrdi.Field;
   import jgrasp.viewer.jgrdi.Method;
   import jgrasp.viewer.jgrdi.Type;
   import jgrasp.viewer.jgrdi.Value;


   /** A base class for image viewers. **/
   public abstract class ImageView extends JPanel implements Viewer {
   
      /** Maximum image display width. **/
      private static final int MAX_WIDTH = 250;
   
      /** Maximum image display height. **/
      private static final int MAX_HEIGHT = 250;
   
      /** The gui root panel. **/
      private JPanel mainPanel;
   
      /** The display version of the image. **/
      private BufferedImage displayImage;
   
      /** The panel on which the image is displayed. **/
      private JPanel imagePanel;
      
      /** Text field for describing the image size. **/
      private JTextField sizeTF;
   
      /** Text field that can be used by subclasses to display
       *  additional image information. **/
      private JTextField clientTF;
   
      /** Border for the image panel. **/
      private EtchedBorder imagePanelBorder;
   
      /** Display text describing image size. **/
      private String displaySizeText;
   
      /** True if displayImage_ is a scaled version of the target image,
       *  false if it is actual size. **/
      private boolean scaledAtUpdate;
   
      /** True if the image was complete when captured, false otherwise. **/
      private boolean isComplete;
   
   
      /** Creates a new color viewer.
       *
       *  @param vcd viewer creation data. **/
      public ImageView(final ViewerCreateData vcd) {
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
                  setBorder(BorderFactory.createEmptyBorder(gap, gap,
                        gap, gap));
               }
            };
         imagePanelBorder  = new EtchedBorder();
         imagePanel = 
            new JPanel() {
               public Dimension getPreferredSize() {
                  int ht = getFont().getSize();
                  return new Dimension(Math.max(ht * 20, MAX_WIDTH),
                        Math.max(ht * 20, MAX_HEIGHT));
               }
            
               public void paintComponent(final Graphics g) {
                  int width = getWidth();
                  int height = getHeight();
                  g.setColor(getBackground());
                  g.fillRect(0, 0, width, height);
                  if (displayImage == null) {
                     sizeTF.setText(displaySizeText);
                     return;
                  }
                  Insets insets = imagePanelBorder.getBorderInsets(this);
                  int borderXOffs = insets.left;
                  int borderYOffs = insets.top;
                  width -= insets.left + insets.right;
                  height -= insets.top + insets.bottom;
                  if (width < 0) {
                     width = 0;
                  }
                  if (height < 0) {
                     height = 0;
                  }
                  int imageWidth = displayImage.getWidth();
                  int imageHeight = displayImage.getHeight();
                  Image drawImage = displayImage;
                  boolean scaled = false;
                  if (imageWidth > width || imageHeight > height) {
                     scaled = true;
                     double scale = Math.min((double) width / imageWidth,
                           (double) height / imageHeight);
                     imageWidth = (int) (imageWidth * scale);
                     imageHeight = (int) (imageHeight * scale);
                     if (imageWidth > 0 && imageHeight > 0) {
                        drawImage =
                              displayImage.getScaledInstance(imageWidth,
                              imageHeight, Image.SCALE_DEFAULT);
                     }
                     else {
                        drawImage = null;
                     }
                  }
                  String text = displaySizeText;
                  if (scaled || scaledAtUpdate) {
                     text += " (shown at " + imageWidth + " x "
                           + imageHeight + ")";
                  }
                  if (!isComplete) {
                     text += " (image is currently incomplete)";
                  }
                  sizeTF.setText(text);
                        
                  int xoffs = borderXOffs + (width - imageWidth) / 2;
                  int yoffs = borderYOffs + (height - imageHeight) / 2;
                  if (drawImage != null) {
                     g.drawImage(drawImage, xoffs, yoffs, null);
                  }
                  imagePanelBorder.paintBorder(this, g, xoffs - insets.left,
                        yoffs - insets.top, imageWidth + insets.left
                        + insets.right, imageHeight + insets.top
                        + insets.bottom);
               }
            };
         mainPanel.add(imagePanel, "Center");
         clientTF = new JTextField();
         clientTF.setEditable(false);
         clientTF.setVisible(false);
         mainPanel.add(clientTF, "North");
         sizeTF = new JTextField();
         sizeTF.setEditable(false);
         mainPanel.add(sizeTF, "South");
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
         return "Image";
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
         String[] clientTextOut = new String[1];
         Value value = getImage(valueData.getValue(), context,
               clientTextOut);
         if (value.isNull()) {
            update("Image is null.", clientTextOut[0], null, 0, 0,
                  false, false);
            return;
         }
         Method getWidthMethod = value.getMethod(context,
               "getWidth", "int", null);
         int width = value.invokeMethod(context, getWidthMethod,
               null).toInt(context);
         Method getHeightMethod = value.getMethod(context,
               "getHeight", "int", null);
         int height = value.invokeMethod(context, getHeightMethod,
               null).toInt(context);
      
         String dsText = String.valueOf(width) + " x " + height;
      
         boolean scaled = false;
         int scaledWidth = width;
         int scaledHeight = height;
         if (width > MAX_WIDTH || height > MAX_HEIGHT) {
            double scale = Math.min((double) MAX_WIDTH / width,
                  (double) MAX_HEIGHT / height);
            scaledWidth = (int) (width * scale);
            scaledHeight = (int) (height * scale);
            
            scaled = true;
         }
         if (scaledWidth <= 0 || scaledHeight <= 0) {
            update(dsText, clientTextOut[0], null, 0, 0, false, scaled);
            return;
         }
         Value scaledWidthV = context.createPrimitiveValue("int",
               String.valueOf(scaledWidth));
         Value scaledHeightV = context.createPrimitiveValue("int",
               String.valueOf(scaledHeight));
         Value zeroV = context.createPrimitiveValue("int", "0");
         Value nullV = context.createNullValue();
      
         Value bufferedImage = null;
         boolean complete = true;
         boolean createdImage = false;
         int type = BufferedImage.TYPE_INT_ARGB;
         if (value.isInstanceOf(context, "java.awt.image.BufferedImage")
               && !scaled) {
            type = value.getFieldValue(context, "imageType").
                  toInt(context);
            if (type == BufferedImage.TYPE_INT_ARGB
                  || type == BufferedImage.TYPE_INT_RGB
                  || type == BufferedImage.TYPE_INT_BGR) {
               bufferedImage = value;
            }
         }
         if (bufferedImage == null) {
            createdImage = true;
            Type bufferedImageType = context.getType(
                  "java.awt.image.BufferedImage");
            bufferedImageType.validate(context);
            Constructor biCons = bufferedImageType.getConstructor(
                  context, new String[] { "int", "int", "int" });
            bufferedImage = bufferedImageType.createInstance(context,
                  biCons, new Value[] {
                  scaledWidthV, scaledHeightV,
                  context.createPrimitiveValue("int", "2" /* TYPE_INT_ARGB */)
                  });
            Method getGraphicsMethod = bufferedImageType.getMethod(
                  context, "getGraphics", "java.awt.Graphics", null);
            Value graphics = bufferedImage.invokeMethod(context,
                  getGraphicsMethod, null);
            Method drawImage = graphics.getMethod(context, "drawImage",
                  "boolean", new String[] { "java.awt.Image", "int", "int",
                  "int", "int", "java.awt.image.ImageObserver" });
            Value result = graphics.invokeMethod(context, drawImage,
                  new Value[] { value, zeroV, zeroV, scaledWidthV,
                  scaledHeightV, nullV });
            complete = result.toBoolean(context);
            Method dispose = graphics.getMethod(context,
                  "dispose", "void", null);
            graphics.invokeMethod(context, dispose, null);
            Field[] gFields = graphics.getType(context).
                  getFields(Type.INSTANCE);
            for (int f = 0; f < gFields.length; f++) {
               Type t = gFields[f].getType(context);
               if (t != null && t.isObject()) {
                  graphics.setFieldValue(context, gFields[f], nullV);
               }
            }
         }         
         if (bufferedImage == null || scaledWidth <= 0
               || scaledHeight <= 0) {
            update(dsText, clientTextOut[0], null, 0, 0, false, scaled);
            return;
         }
         List<Value> pixels = null;
         Value raster = bufferedImage.getFieldValue(context, "raster");
         if (!raster.isNull()) {
            Value rgbData = raster.getFieldValue(context, "data");
            if (!rgbData.isNull()) {
               pixels = rgbData.getArrayElements(context);
            }
         }   
         if (pixels == null || pixels.size() != scaledWidth * scaledHeight) {
            update(dsText, clientTextOut[0], null, 0, 0, false, scaled);
            return;
         }
         int[] pixelData = new int[pixels.size()];
         for (int i = 0; i < pixels.size(); i++) {
            int pixel = pixels.get(i).toInt(context);
            switch (type) {
               case BufferedImage.TYPE_INT_ARGB:
                  break;
               case BufferedImage.TYPE_INT_RGB:
                  pixel |= 0xff000000;
                  break;
               case BufferedImage.TYPE_INT_BGR:
                  pixel = 0xff000000 | ((pixel & 0xff0000) >> 16)
                        | (pixel & 0xff00) | ((pixel & 0xff) << 16);
                  break;
            }
            pixelData[i] = pixel;
         }
      
         if (createdImage) {
            Method flush = bufferedImage.getMethod(context, "flush", "void",
                  null);
            bufferedImage.invokeMethod(context, flush, null);
            bufferedImage.setFieldValue(context, "raster", nullV);
            bufferedImage.setFieldValue(context, "surfaceManager", nullV);
         }
         update(dsText, clientTextOut[0], pixelData, scaledWidth,
               scaledHeight, complete, scaled);
      }
   
   
      /** Updates the user interface with from the debugger thread.
       *
       *  @param dsText the display size description.
       *
       *  @param clientText text supplied by the subclass.
       *
       *  @param pixelData the pixel data, or null if the image is
       *  not available.
       *
       *  @param w the width of the image supplied in
       *  <code>pixelData</code>.
       *
       *  @param h the height of the image supplied in
       *  <code>pixelData</code>.
       *
       *  @param complete true if the image data is complete,
       *  false otherwise.
       *
       *  @param scaled true if the pixel data is scaled as
       *  supplied, false otherwise. **/
      private void update(final String dsText, final String clientText,
            final int[] pixelData, final int w, final int h,
            final boolean complete, final boolean scaled) {
         SwingUtilities.invokeLater(
               new Runnable() {
                  public void run() {
                     if (mainPanel == null) {
                        buildGui();
                     }
                     updateGui(dsText, clientText, pixelData, w, h,
                           complete, scaled);
                  }
               });
      }
       
       
      /** Updates the user interface.
       *
       *  @param dsText the display size description.
       *
       *  @param clientText text supplied by the subclass.
       *
       *  @param pixelData the pixel data, or null if the image is
       *  not available.
       *
       *  @param w the width of the image supplied in
       *  <code>pixelData</code>.
       *
       *  @param h the height of the image supplied in
       *  <code>pixelData</code>.
       *
       *  @param complete true if the image data is complete,
       *  false otherwise.
       *
       *  @param scaled true if the pixel data is scaled as
       *  supplied, false otherwise. **/
      private void updateGui(final String dsText, final String clientText,
            final int[] pixelData, final int w, final int h,
            final boolean complete, final boolean scaled) {
         displaySizeText = dsText;
         clientTF.setVisible(clientText != null);
         if (clientText != null) {
            clientTF.setText(clientText);
         }
         if (pixelData == null) {
            displayImage = null;
         }
         else {
            displayImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            displayImage.setRGB(0, 0, w, h, pixelData, 0, w);
         }
         isComplete = complete;
         scaledAtUpdate = scaled;
         
         imagePanel.repaint();
      }
            
            
      /** {@inheritDoc} **/
      public void getInfo(final ViewerInfo vi) {
         vi.setShortDescription("Detail viewer for images");
         vi.setLongDescription("This viewer displays the image, "
               + "and its width and height.");
      }
   
   
      /** Gets the image to be displayed.
       *
       *  @param value the value being viewed.
       *
       *  @param context the current debugger context.
       *
       *  @param clientTextOut the first element of this array is
       *  used to return display text, if any.
       *
       *  @throws ViewerException if an error occurs while retrieving
       *  the image.
       *
       *  @return the image value. **/
      public abstract Value getImage(Value value, DebugContext context,
            String[] clientTextOut) throws ViewerException;
   }
