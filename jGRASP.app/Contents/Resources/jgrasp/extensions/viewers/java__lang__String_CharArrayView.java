
   import jgrasp.viewer.ViewerCreateData;
   import jgrasp.viewer.ViewerException;
   import jgrasp.viewer.ViewerInfo;

   import jgrasp.viewer.jgrdi.DebugContext;
   import jgrasp.viewer.jgrdi.Method;
   import jgrasp.viewer.jgrdi.Value;

   import jgrasp.viewer.presentation.PresentationListView;


   /** Presentation viewer for Strings. **/
   public class java__lang__String_CharArrayView extends PresentationListView {
   
   
      /** Creates a new presentation String viewer.
       *
       *  @param vcd creation data. **/
      public java__lang__String_CharArrayView(final ViewerCreateData vcd) {
         super(0);
      }
   
   
      /** {@inheritDoc} **/
      public Value getElement(final Value value, final int index,
            final DebugContext context, final Object elementContext)
            throws ViewerException {
         Method caMethod = value.getMethod(context, "charAt",
               "char", new String[] {"int"});
         return value.invokeMethod(context, caMethod,
               new Value[] { context.createPrimitiveValue("int",
                     String.valueOf(index)) });
      }
   
   
      /** {@inheritDoc} **/
      public int getLength(final Value value, final DebugContext context)
            throws ViewerException {
         Method glMethod = value.getMethod(context, "length",
               "int", null);
         Value result = value.invokeMethod(context, glMethod, null);
         return result.toInt(context);
      }
   
   
      /** {@inheritDoc} **/
      public boolean isObjectList(final Value value,
            final DebugContext context) throws ViewerException {
         return false;
      }
   
   
      /** {@inheritDoc} **/
      public int getFullLimit() {
         return 500;
      }
   
   
      /** {@inheritDoc} **/
      public String getLeftIndexMarker() {
         return "[";
      }
       
       
      /** {@inheritDoc} **/
      public String getRightIndexMarker() {
         return "]";
      }
       
         
      /** {@inheritDoc} **/
      public Value getContainer(final Value value, final int index,
            final DebugContext context, final Object elementContext)
            throws ViewerException {
         return null;
      }
   
   
      /** {@inheritDoc} **/
      public String getViewName() {
         return "Presentation";
      }
   
   
      /** {@inheritDoc} **/
      public int getPriority() {
         return 2;
      }
         
         
      /** {@inheritDoc} **/
      public void getInfo(final ViewerInfo vi) {
         vi.setShortDescription("Character array viewer for Strings");
         vi.setLongDescription("This viewer displays a String "
               + "as an array of characters. Note that this is not "
               + "necessarily the same as the character array that "
               + "the String uses to hold its characters (which may "
               + "be a larger array, where the Strings offset and "
               + "length limit the range).");
      }     
   }
