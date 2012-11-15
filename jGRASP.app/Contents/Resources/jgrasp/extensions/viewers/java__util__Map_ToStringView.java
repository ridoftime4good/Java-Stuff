   import jgrasp.viewer.ViewerCreateData;
   import jgrasp.viewer.ViewerException;
   import jgrasp.viewer.ViewerInfo;
   import jgrasp.viewer.ViewerUpdateData;
   import jgrasp.viewer.ViewerValueData;

   import jgrasp.viewer.jgrdi.DebugContext;
   import jgrasp.viewer.jgrdi.Method;
   import jgrasp.viewer.jgrdi.Value;

   import jgrasp.viewer.text.StringListViewWSV;


   /** An java.util.Collection viewer that displays the entries as a
    *  list of key/value strings. **/
    public class java__util__Map_ToStringView extends StringListViewWSV {
   
   
      /** Creates a new string list viewer for maps.
       *
       *  @param vcd viewer creation data. **/
       public java__util__Map_ToStringView(final ViewerCreateData vcd) {
         super(true);
      }
         
         
      /** {@inheritDoc} **/
       public String getViewName() {
         return "Key/Value";
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
         Method sizeMethod = value.getMethod(context, "size", "int", null);
         int size = value.invokeMethod(context, sizeMethod, null).
               toInt(context);
         if (size > 1500) {
            errorOut[0] = "Map contains " + size + " entries.\n\n"
                  + "For performance reasons, this view can display a "
                  + "maximum of 1500 entries.";
            return 0;
         }
      
         Method entrySetMethod = value.getMethod(context, "entrySet",
               "java.util.Set", null);
         Value entrySet = value.invokeMethod(context, entrySetMethod,
               null);
         
         Method toArrayMethod = entrySet.getMethod(context, "toArray",
               "java.lang.Object[]", null);
         Value array = entrySet.invokeMethod(context, toArrayMethod, null);
      
         for (int i = 0; i < numItemsShown; i++) {
            if (i + viewOffset < size) {
               Value element = array.getArrayElement(context, i + viewOffset);
               Value keyVal = element.getFieldValue(context, "key");
               Value valueVal = element.getFieldValue(context, "value");
            
               if (i == selected) {
                  selectedValueOut[0] = keyVal;
                  selectedValueOut[1] = valueVal;
               }
               String keyStr = keyVal.toString(context);
               if (keyStr.length() > 25) {
                  keyStr = keyStr.substring(0, 21) + " ...";
               }
               String valueStr = valueVal.toString(context);
               if (valueStr.length() > 25) {
                  valueStr = valueStr.substring(0, 21) + " ...";
               }
               textOut[i] = "_" + (i + viewOffset) + "_ = "
                     + keyStr + ": " + valueStr;
            }
            else {
               textOut[i] = " ";
            }
         }
      
         return size;
      }
      
      
      /** {@inheritDoc} **/
       public String getSubviewerLabel(final int index,
            final String viewerLabel, final int itemIndex) {
         return (index == 0? "Key" : "Value") + " (" + itemIndex + ")";
      }
   
   
      /** {@inheritDoc} **/
       public String getSubviewerTreeLabel(final int index,
            final String viewerLabel, final int itemIndex) {
         return (index == 0? "key" : "value") + " (" + itemIndex + ")";
      }
   
   
      /** {@inheritDoc} **/
       public void getInfo(final ViewerInfo vi) {
         vi.setShortDescription("Map \"toString()\" viewer");
         vi.setLongDescription("This viewer displays the toString() value "
               + "for each key and value in the map. Selecting an entry "
               + "will cause the key and value for that entry to be "
               + "displayed in a subviewer.\n\nNote that for identification "
               + "purposes, indices are assigned to the entries, "
               + "although they have no specific order.");
      }
   
   }
