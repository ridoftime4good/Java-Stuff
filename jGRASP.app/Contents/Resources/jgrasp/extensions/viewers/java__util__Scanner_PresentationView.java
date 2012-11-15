
   import java.util.ArrayList;
   import java.util.HashMap;
   import java.util.List;
   import java.util.Map;

   import jgrasp.viewer.ViewerCreateData;
   import jgrasp.viewer.ViewerException;
   import jgrasp.viewer.ViewerInfo;

   import jgrasp.viewer.jgrdi.DebugContext;
   import jgrasp.viewer.jgrdi.Value;

   import jgrasp.viewer.presentation.PresentationListView;


   /** Presentation viewer for scanners. **/
   public class java__util__Scanner_PresentationView
         extends PresentationListView {
   
   
      /** Marker labels (one empty label). **/
      private static String[] indexLabels = new String[] { "" };
   
   
      /** Creates a new scanner viewer.
       *
       *  @param vcd creation data. **/
      public java__util__Scanner_PresentationView(final ViewerCreateData vcd) {
         super(0);
      }
   
   
      /** {@inheritDoc} **/
      public String getViewName() {
         return "Scanner Presentation View";
      }
   
   
      /** {@inheritDoc} **/
      public int getPriority() {
         return 1000;
      }
   
   
      /** {@inheritDoc} **/
      public Value getElement(final Value value, final int index,
            final DebugContext context, final Object elementContext)
            throws ViewerException {
         Value buf = value.getFieldValue(context, "buf");
         if (buf == null) {
            return null;
         }
         Value hb = buf.getFieldValue(context, "hb");
         if (hb == null) {
            return null;
         }
         return hb.getArrayElement(context, index);
      }
   
   
      /** {@inheritDoc} **/
      public boolean elementUsed(final Value value, final Value container,
            final Value element, final int index, final DebugContext context,
            final Object elementContext) throws ViewerException {
         Value buf = value.getFieldValue(context, "buf");
         if (buf == null) {
            return true;
         }
         int offs = buf.getFieldValue(context, "offset").toInt(context);
         if (index < offs) {
            return false;
         }
         int limit = buf.getFieldValue(context, "limit").toInt(context);
         return index < limit;
      }
   
   
      /** {@inheritDoc} **/
      public int getLength(final Value value, final DebugContext context)
            throws ViewerException {
         Value buf = value.getFieldValue(context, "buf");
         if (buf == null) {
            return 0;
         }
         Value hb = buf.getFieldValue(context, "hb");
         if (hb == null) {
            return 0;
         }
         return hb.getArrayLength(context);
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
      public String[] getIndexes() {
         return indexLabels;
      } 
   
   
      /** {@inheritDoc} **/
      public Map<Integer, String> getMarkers(final Value value,
            final DebugContext context) throws ViewerException {
         int pos = value.getFieldValue(context, "position").toInt(context);
         Map<Integer, String> result = new HashMap<Integer, String>();
         result.put(Integer.valueOf(pos), "");
         return result;
      }
   
   
      /** {@inheritDoc} **/
      public int getAutoscrollIndex(final Value value,
            final DebugContext context) throws ViewerException {
         int pos = value.getFieldValue(context, "position").toInt(context);
         return pos;
      }
   
   
      /** {@inheritDoc} **/
      public void getInfo(final ViewerInfo vi) {
         vi.setShortDescription("Presentation viewer for Scanners");
         vi.setLongDescription("This viewer displays the contents "
               + " of the Scanner's buffer.");
      }
   }
