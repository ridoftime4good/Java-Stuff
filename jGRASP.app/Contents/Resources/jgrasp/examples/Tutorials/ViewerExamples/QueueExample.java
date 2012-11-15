   import jgraspvex.Queue;


    /** This example is intended to illustrate the
     *  capabilities of the structure identifier viewer
     *  when applied to queues. Set a breakpoint, debug,
     *  drag "queue" from the debug variables window to
     *  pop up a viewer, and step repeatedly. **/
    public class QueueExample {
    
       public static void main(String[] args) {
       
         while (true) {
            Queue queue = new Queue();
            for (int i = 0; i < 30; i++) {
               queue.add(new Integer(i * i));
               if ((i % 2) == 1) {
                  queue.remove();
               }
            }
            queue.clear();
         }
      }
   }