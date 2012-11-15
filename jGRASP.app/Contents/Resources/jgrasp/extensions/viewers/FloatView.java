
   import java.awt.GridBagConstraints;
   import java.awt.GridBagLayout;
   import java.awt.Insets;
   
   import javax.swing.BorderFactory;
   import javax.swing.JComponent;
   import javax.swing.JLabel;
   import javax.swing.JPanel;
   import javax.swing.JScrollPane;
   import javax.swing.JTextField;
   import javax.swing.SwingUtilities;

   import jgrasp.Viewer;
   
   import jgrasp.viewer.ViewerException;
   import jgrasp.viewer.ViewerInitData;
   import jgrasp.viewer.ViewerUpdateData;
   import jgrasp.viewer.ViewerValueData;

   import jgrasp.viewer.jgrdi.DebugContext;
   import jgrasp.viewer.jgrdi.Value;


   /** Base class for floating-point detail viewers. **/
    public abstract class FloatView implements Viewer {
   
      /** Scroll pane that contiains the viewer gui components. **/
      private JScrollPane scroll;
   
      /** The top label, which displays the value in decimal. **/
      private JLabel topLabel;
      
      /** Labels for showing the sign, exponent, and mantissa
       *  in binary, hex, and decimal, and the header labels
       *  for those categories. **/
      private JLabel[] textSegmentLabels = new JLabel[12];
      
      /** Labels which display the value computations. **/
      private JLabel[] computationLabel = new JLabel[7];
   
      /** Index of the sign bit for the float type that this
       *  viewer displays. **/
      private int signBit;
      
      /** Index of the first exponent bit for the float type that this
       *  viewer displays. **/
      private int firstExponentBit;
      
      /** Index of the last exponent bit for the float type that this
       *  viewer displays. **/
      private int lastExponentBit;
      
      /** Index of the first mantissa bit for the float type that this
       *  viewer displays. **/
      private int firstMantissaBit;
      
      /** Index of the last mantissa bit for the float type that this
       *  viewer displays. **/
      private int lastMantissaBit;
      
      /** Bias to be subtracted from the exponent bits value to get the
       *  actual exponent. **/
      private int exponentBias;
   
   
      /** Creates a new FloatView.
       *
       *  @param signBitIndex the of the sign bit for the float type that this
       *  viewer displays.
       *
       *  @param firstExponentBitIndex the index of the first exponent bit for
       *  the float type that this viewer displays.
       *
       *  @param lastExponentBitIndex the index of the last exponent bit for
       *  the float type that this viewer displays.
       *
       *  @param firstMantissaBitIndex the index of the first mantissa bit for
       *  the float type that this viewer displays.
       *
       *  @param lastMantissaBitIndex the index of the last mantissa bit for
       *  the float type that this viewer displays.
       *
       *  @param exponentBiasValue the bias to be subtracted from the exponent
       *  bits value to get the actual exponent. **/
       public FloatView(final int signBitIndex,
            final int firstExponentBitIndex,
            final int lastExponentBitIndex, final int firstMantissaBitIndex,
            final int lastMantissaBitIndex, final int exponentBiasValue) {
         super();
         
         signBit = signBitIndex;
         firstExponentBit = firstExponentBitIndex;
         lastExponentBit = lastExponentBitIndex;
         firstMantissaBit = firstMantissaBitIndex;
         lastMantissaBit = lastMantissaBitIndex;
         exponentBias = exponentBiasValue;
      }
   
   
      /** {@inheritDoc} **/
       public void build(final ViewerInitData vid) {
      }
       
       
      /** {@inheritDoc} **/
       public void destroy() {
      }
   
   
      /** {@inheritDoc} **/
       public JComponent getComponent() {
         if (scroll == null) {
            buildGui();
         }
         return scroll;
      }
     
          
      /** {@inheritDoc} **/
       public String getViewName() {
         return "Detail";
      }
   
   
      /** {@inheritDoc} **/
       public int getPriority() {
         return -1;
      }
   
   
      /** {@inheritDoc} **/
       public void setFrozen() {
      }
   
   
      /** {@inheritDoc} **/
       public void update(final ViewerValueData valueData,
            final ViewerUpdateData data, final DebugContext context)
            throws ViewerException {
         final String valueText = getValueText(valueData.getValue(), context);
         final long bits = getBits(valueData.getValue(), context);
         SwingUtilities.invokeLater(
                new Runnable() {
                   public void run() {
                     if (scroll == null) {
                        buildGui();
                     }
                     update(valueText, bits);
                  }
               });
      }
      
   
      /** Updates the display.
       *
       *  @param valueText the value text in decimal.
       *
       *  @param bits the value bits. **/
       private void update(final String valueText, final long bits) {
         topLabel.setText("value = " + valueText);
         
         long signMask = 1L << signBit;
         computationLabel[0].setText("sign = "
               + (((bits & signMask) == 0L)? '+' : '-'));
         
         textSegmentLabels[3].setText(((bits & signMask) == 0L)? " 0" : " 1");
         textSegmentLabels[4].setText(" " + bitString(bits, firstExponentBit,
               lastExponentBit));
         textSegmentLabels[5].setText(" " + bitString(bits, firstMantissaBit,
               lastMantissaBit));
      
         long exponentMask = (2L << firstExponentBit)
               - (1L << lastExponentBit);
         long exponent = (bits & exponentMask) >> lastExponentBit;
         
         long mantissaMask = (2L << firstMantissaBit)
               - (1L << lastMantissaBit);
         long mantissa = (bits & mantissaMask) >> lastMantissaBit;
         double mantDouble = (double) mantissa
               / (2L << (firstMantissaBit - lastMantissaBit));
         
         textSegmentLabels[6].setText(((bits & signMask) == 0L)? " 0" : " 1");
         textSegmentLabels[7].setText("0x" + Long.toString(exponent, 16));
         textSegmentLabels[8].setText("0x" + Long.toString(mantissa, 16));
         
         textSegmentLabels[9].setText(((bits & signMask) == 0L)? " 0" : " 1");
         textSegmentLabels[10].setText(Long.toString(exponent));
         textSegmentLabels[11].setText(Long.toString(mantissa));
         
         if (exponent == ((2L << (firstExponentBit
               - lastExponentBit)) - 1)) {
            if (mantissa == 0L) {
               computationLabel[1].setText("value = "
                     + (((bits & signMask) == 0L)? '+' : '-')
                     + "infinity (exponent all 1s and mantissa 0 indicates "
                     + "infinity)");
            }
            else {
               computationLabel[0].setText("value = NaN (exponent all 1s and "
                     + "mantissa not 0 indicates NaN)");
               computationLabel[1].setText("");
            }
            for (int i = 2; i < 7; i++) {
               computationLabel[i].setText("");
            }
            return;
         }
         
         if (exponent == 0L) {
            computationLabel[1].setText("exponent = " + (-exponentBias + 1)
                  + " (zero exponent indicates denormalized form)");
         }
         else {
            computationLabel[1].setText("exponent = " + exponent + " - bias of "
                  + exponentBias + " = " + (exponent - exponentBias));
         }
         if (exponent == 0L) {
            computationLabel[2].setText("mantissa = " + mantissa + " / 2^"
               + (firstMantissaBit - lastMantissaBit + 1));
            computationLabel[3].setText("      = (approximately) "
                  + getMantissaText(mantDouble));
         }
         else {
            computationLabel[2].setText("mantissa = assumed 1 + " + mantissa
                  + " / 2^" + (firstMantissaBit - lastMantissaBit + 1));
            computationLabel[3].setText("      = (approximately) "
                  + getMantissaText(mantDouble + 1));
         }
         computationLabel[4].setText("value = (sign) mantissa * 2 ^ exponent");
         if (exponent == 0L) {
            computationLabel[5].setText("      = "
                  + (((bits & signMask) == 0L)? '+' : '-')
                  + getMantissaText(mantDouble) + " * 2 ^ "
                  + (-exponentBias + 1));
         }
         else {
            computationLabel[5].setText("      = "
                  + (((bits & signMask) == 0L)? '+' : '-')
                  + getMantissaText(mantDouble + 1) + " * 2 ^ "
                  + (exponent - exponentBias));
         }
         computationLabel[6].setText("      = " + valueText);
      }
   
   
      /** Gets a bit string from a segment of a long.
       *
       *  @param bits the bits.
       *
       *  @param firstBit index of the first bit.
       *
       *  @param lastBit index of the last bit.
       *
       *  @return a string representation of the bits in <code>bits</code>
       *  from <code>firstBit</code> to <code>lastBit</code>. **/
       private String bitString(final long bits, final int firstBit,
            final int lastBit) {
         StringBuilder buffer = new StringBuilder();
         long bit = 1L << firstBit;
         for (int i = firstBit; i >= lastBit; i--, bit >>= 1) {
            buffer.append(((bits & bit) == 0L)? '0' : '1');
            if (i > lastBit && ((i - lastBit) % 4) == 0) {
               buffer.append(' ');
            }
         }
         return buffer.toString();
      }
      
      
      /** Gets text for the value. This will be called from the debugger
       *  thread.
       *
       *  @param value the value.
       *
       *  @param context the current debugger context.
       *
       *  @return a string representing <code>value</code> in decimal.
       *
       *  @throws ViewerException if an exception occurs while
       *  accessing the value. **/
       public abstract String getValueText(Value value, DebugContext context)
            throws ViewerException;
      
      
      /** Gets bit representation of the value. This will be called from
       *  the debugger thread.
       *
       *  @param value the value.
       *
       *  @param context the current debugger context.
       *
       *  @return the bit representation of <code>value</code>.
       *
       *  @throws ViewerException if an exception occurs while
       *  accessing the value. **/
       public abstract long getBits(Value value, DebugContext context)
            throws ViewerException;
       
   
      /** Gets the mantissa text.
       *
       *  @param mantissa the mantissa or an intermediate value
       *  for mantissa computation.
       *
       *  @return a string representing the decimal value of
       *  <code>mantissa</code>, with an appropriate number of
       *  digits for the type that this viewer displays. **/
       public abstract String getMantissaText(double mantissa);
   
   
      /** Builds the user interface. **/
       private void buildGui() {
         JPanel content = new JPanel(false);
         GridBagLayout layout = new GridBagLayout();
         content.setLayout(layout);
         GridBagConstraints constraints = new GridBagConstraints();
         Insets insets = constraints.insets;
        
         int spacing = 8;
         constraints.weightx = .001;
         constraints.weighty = .001;
         insets.bottom = 0;
         insets.top = spacing;
         insets.left = spacing;
         insets.right = spacing;
         constraints.gridwidth = GridBagConstraints.REMAINDER;
         constraints.fill = GridBagConstraints.HORIZONTAL;
         constraints.anchor = GridBagConstraints.WEST;
      
         topLabel = new JLabel(" ");
         content.add(topLabel);
         layout.setConstraints(topLabel, constraints);
      
         constraints.gridwidth = 1;
         int seg = 0;
         for (int row = 0; row < 4; row++) {
            if (row == 1) {
               insets.top = 0;
            }
            for (int col = 0; col < 3; col++) {
               JLabel segment = new JLabel(" ");
               segment.setBorder(BorderFactory.createEtchedBorder());
               if (row >= 1) {
                  segment.setHorizontalAlignment(JTextField.RIGHT);
               }
               textSegmentLabels[seg++] = segment;
               insets.left = (col == 0)? spacing : 0;
               insets.right = (col == 2)? spacing : 0;
               content.add(segment);
               layout.setConstraints(segment, constraints);
               if (col == 2) {
                  JPanel spacer = new JPanel(false);
                  constraints.gridwidth = GridBagConstraints.REMAINDER;
                  constraints.weightx = 1.0;
                  content.add(spacer);
                  layout.setConstraints(spacer, constraints);
                  constraints.gridwidth = 1;
                  constraints.weightx = .001;
               }
            }
         }
         textSegmentLabels[0].setText(" Sign");
         textSegmentLabels[1].setText(" Exponent");
         textSegmentLabels[2].setText(" Mantissa");
      
         insets.left = spacing;
         insets.right = spacing;
         insets.top = spacing;
         constraints.gridwidth = GridBagConstraints.REMAINDER;
         for (int row = 0; row < 7; row++) {
            if (row == 1) {
               insets.top = 0;
            }
            computationLabel[row] = new JLabel(" ");
            content.add(computationLabel[row]);
            layout.setConstraints(computationLabel[row], constraints);
         }
         constraints.weighty = 1.0;
         constraints.fill = GridBagConstraints.BOTH;
         JPanel spacer = new JPanel(false);
         content.add(spacer);
         layout.setConstraints(spacer, constraints);
         
         scroll = new JScrollPane(content);
      }
   
   }
