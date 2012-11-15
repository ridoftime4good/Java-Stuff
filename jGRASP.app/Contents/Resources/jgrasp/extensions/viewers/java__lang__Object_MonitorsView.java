
   import com.sun.jdi.ObjectReference;
   import com.sun.jdi.ThreadReference;

   import java.util.List;

   import jgrasp.PluginOptOut;

   import jgrasp.viewer.ViewerCreateData;
   import jgrasp.viewer.ViewerException;
   import jgrasp.viewer.ViewerInfo;
   import jgrasp.viewer.ViewerInitData;

   import jgrasp.viewer.jgrdi.DebugContext;
   import jgrasp.viewer.jgrdi.Value;

   import jgrasp.viewer.text.TextAreaView;


   /** A view of Object monitor info (JDI only). **/
    public class java__lang__Object_MonitorsView extends TextAreaView {
      
   
      /** Creates a new toString() viewer.
       *
       *  @param vcd creation data. **/
       public java__lang__Object_MonitorsView(
            final ViewerCreateData vcd) {
         if (!vcd.getNativeValueTypeName().equals("com.sun.jdi.Value")) {
            throw new PluginOptOut();
         }
      }
        
      
      /** {@inheritDoc} **/
       public void build(final ViewerInitData vid) {
         vid.setAutoUpdate(true);
      }
   
   
      /** {@inheritDoc} **/
       public String getViewName() {
         return "Monitor Info";
      }
      
          
      /** {@inheritDoc} **/
       public int getPriority() {
         return -10;
      }
     
         
      /** {@inheritDoc} **/
       public String getDisplayText(final Value value,
            final DebugContext context) throws ViewerException {
         try {
            ObjectReference or = (ObjectReference) value.getNativeValue();
            ThreadReference owner = or.owningThread();
            List<ThreadReference> waiting = or.waitingThreads();
            if (owner == null && (waiting == null || waiting.size() == 0)) {
               return "<No owning thread or waiting threads.>";
            }
            StringBuilder result = new StringBuilder();
            if (owner != null) {
               result.append("Monitor owner:\n");
               result.append("   ");
               result.append(owner.name());
               result.append(" id = ");
               result.append(owner.uniqueID());
               result.append("\n");
            }
            if (waiting != null && waiting.size() > 0) {
               result.append("Threads waiting on monitor:\n");
               for (ThreadReference tr : waiting) {
                  result.append("   ");
                  result.append(tr.name());
                  result.append(" id = ");
                  result.append(tr.uniqueID());
                  result.append("\n");
               }
            }
            return result.toString();
         }
             catch (Exception e) {
               return "<" + e + ">";
            }
      }
      
      
      /** {@inheritDoc} **/
       public void getInfo(final ViewerInfo vi) {
         vi.setShortDescription("Monitor info viewer");
         vi.setLongDescription("This viewer displays the threads, "
               + "if any, that are holding and waiting on the Object's "
               + "monitor.");
      }
   }
