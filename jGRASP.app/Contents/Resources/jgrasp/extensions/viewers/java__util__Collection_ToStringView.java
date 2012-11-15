   import jgrasp.viewer.ViewerCreateData;
   import jgrasp.viewer.ViewerException;
   import jgrasp.viewer.ViewerInfo;
   import jgrasp.viewer.ViewerUpdateData;
   import jgrasp.viewer.ViewerValueData;

   import jgrasp.viewer.jgrdi.DebugContext;
   import jgrasp.viewer.jgrdi.Method;
   import jgrasp.viewer.jgrdi.Value;

   import jgrasp.viewer.text.StringListViewWSV;


   /** An java.util.Collection viewer that displays the elements as a
    *  list of strings. **/
    public class java__util__Collection_ToStringView extends StringListViewWSV {
   
      /** True if the most recently updated value is a java.util.List,
       *  false otherwise. **/
      private boolean isList;
   
   
      /** Creates a new string list viewer for collections.
       *
       *  @param vcd viewer creation data. **/
       public java__util__Collection_ToStringView(final ViewerCreateData vcd) {
         super(false);
      }
       
       
      /** {@inheritDoc} **/
       public String getViewName() {
         return "Collection Elements";
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
         isList = value.isInstanceOf(context, "java.util.List");
         
         Method sizeMethod = value.getMethod(context, "size", "int", null);
         int size = value.invokeMethod(context, sizeMethod, null).
               toInt(context);
         
         if (!isList && size > 1500) {
            errorOut[0] = "Collection contains " + size + " elements.\n\n"
                  + "For performance reasons, this view can display a "
                  + "maximum of 1500 elements.";
            return 0;
         }
         
         Method getMethod = null;
         Value array = null;
         String lb;
         String rb;
         if (isList) {
            getMethod = value.getMethod(context, "get",
                  "java.lang.Object", new String[] { "int" });
            lb = "<";
            rb = ">";
         }
         else {
            Method toArrayMethod = value.getMethod(context, "toArray",
                  "java.lang.Object[]", null);
            array = value.invokeMethod(context, toArrayMethod, null);
            lb = "(";
            rb = ")";
         }
         
         for (int i = 0; i < numItemsShown; i++) {
            if (i + viewOffset < size) {
               Value element;
               if (isList) {
                  element = value.invokeMethod(context, getMethod,
                        new Value[] {
                           context.createPrimitiveValue("int",
                           String.valueOf(i + viewOffset)) });
               }
               else {
                  element = array.getArrayElement(context,
                        i + viewOffset);
               }
               if (i == selected) {
                  selectedValueOut[0] = element;
               }
               String str = element.toString(context);
               if (str.length() > 100) {
                  str = str.substring(0, 96) + " ...";
               }
               textOut[i] = lb + (i + viewOffset) + rb + " = " + str;
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
         return "Element " + (isList? "<" : "(") + itemIndex
               + (isList? ">" : ")");
      }
   
   
      /** {@inheritDoc} **/
       public String getSubviewerTreeLabel(final int index,
            final String viewerLabel, final int itemIndex) {
         return (isList? "<" : "(") + itemIndex + (isList? ">" : ")");
      }
   
   
      /** {@inheritDoc} **/
       public void getInfo(final ViewerInfo vi) {
         vi.setShortDescription("collections \"toString()\" viewer");
         vi.setLongDescription("This viewer lists the toString() value "
               + "of each collection element. Selecting an element will "
               + "cause it to be displayed in a subviewer.\n\nNote that "
               + "for identification purposes, indices are shown "
               + "even for collections for which the elements have no "
               + "specific order.");
      }
   }
