   import jgrasp.viewer.ViewerCreateData;
   import jgrasp.viewer.ViewerException;
   import jgrasp.viewer.ViewerInfo;
   import jgrasp.viewer.ViewerUpdateData;
   import jgrasp.viewer.ViewerValueData;
   
   import jgrasp.viewer.jgrdi.DebugContext;
   import jgrasp.viewer.jgrdi.Value;
   
   import jgrasp.viewer.text.StringListViewWSV;


   /** An array viewer that displays the elements as a list of
    *  strings. **/
   public class _X_A_ToStringView extends StringListViewWSV {
   
   
      /** Creates a new string list viewer for arrays.
       *
       *  @param vcd viewer creation data. **/
      public _X_A_ToStringView(final ViewerCreateData vcd) {
         super(false);
      }
         
   
      /** {@inheritDoc} **/
      public String getViewName() {
         return "Array Elements";
      }
   
   
      /** {@inheritDoc} **/
      public int getPriority() {
         return 10;
      }
   
   
      /** {@inheritDoc} **/
      public int update(final ViewerValueData valueData,
            final ViewerUpdateData data, final DebugContext context,
            final int viewOffset, final int numItemsShown, final int selected,
            final String[] textOut, final Value[] selectedValueOut,
            final String[] errorOut) throws ViewerException {
      
         Value value = valueData.getValue();
         int len = value.getArrayLength(context);
         
         for (int i = 0; i < numItemsShown; i++) {
            if (i + viewOffset < len) {
               Value element = value.getArrayElement(context, i + viewOffset);
               if (i == selected) {
                  selectedValueOut[0] = element;
               }
               String str = element.toString(context);
               if (str.length() > 100) {
                  str = str.substring(0, 96) + " ...";
               }
               textOut[i] = "[" + (i + viewOffset) + "] = " + str;
            }
            else {
               textOut[i] = " ";
            }
         }
      
         return len;
      }
      
   
      /** {@inheritDoc} **/
      public String getSubviewerLabel(final int index,
            final String viewerLabel, final int itemIndex) {
         return viewerLabel + " [" + itemIndex + "]";
      }
   
   
      /** {@inheritDoc} **/
      public String getSubviewerTreeLabel(final int index,
            final String viewerLabel, final int itemIndex) {
         return "[" + itemIndex + "]";
      }
   
   
      /** {@inheritDoc} **/
      public void getInfo(final ViewerInfo vi) {
         vi.setShortDescription("Array \"toString()\" viewer");
         vi.setLongDescription("This viewer displays the toString() value "
               + "for array elements. Selecting an element "
               + "will cause its value to be displayed in a subviewer.");
      }
   
   }
