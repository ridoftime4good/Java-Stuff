   import jgrasp.viewer.ViewerCreateData;
   import jgrasp.viewer.ViewerInfo;
   
   import jgrasp.viewer.gui.TreeViewer;


   /** The basic expandable tree object viewer. **/
    public class _X_BasicView extends TreeViewer {
   
   
      /** Creates a new basic viewer.
       *
       *  @param vcd creation data. **/
       public _X_BasicView(final ViewerCreateData vcd) {
         super();
      }
   
   
      /** {@inheritDoc} **/
       public String getViewName() {
         return "Basic";
      }
   
   
      /** {@inheritDoc} **/
       public int getPriority() {
         return 0;
      }
   
   
      /** {@inheritDoc} **/
       public void getInfo(final ViewerInfo vi) {
         vi.setShortDescription("Basic viewer");
         vi.setLongDescription("This viewer displays a value as an "
               + "expandable tree. See \"Java Debugging\" in the help "
               + "contents for an explanation of the colors and shapes "
               + "used in the display.");
      }
   }
