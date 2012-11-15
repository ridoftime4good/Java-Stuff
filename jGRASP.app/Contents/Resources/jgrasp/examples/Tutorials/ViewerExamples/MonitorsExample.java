

  /** MonitorsExample -- This example is intended to illustrate
   *  the capabilities of the jGRASP "Monitor Info" viewer. Set
	*  a breakpoint at the indicated line. After the program stops
	*  at the breakpoint, open a viewer on "static" / "sync"
   *  by "dragging" it out of the Debug tab and releasing it. **/
    public class MonitorsExample {
    
    	/** Synchronization object. **/
      private static Object sync = new Object();
    
    
       public static void main(final String[] args) {
       
         for (int i = 0; i < 10; i++) {
            Runnable r = 
                new Runnable() {
                   public void run() {
                     go();
                  }
               };
            Thread tr = new Thread(r);
            tr.setName("Thread " + i);
            tr.start();
         }
      }
    
   
       public static void go() {
       
         while (true) {
            synchronized (sync) {
               try {
               	// Set a breakpoint on the next line, open a
               	// viewer on "static" / "sync", and choose the
               	// "Monitor Info" view. Hit resume to see the
               	// owning thread change, hit it repeatedly and
               	// quickly to see the number of waiting threads
               	// decrease.
                  Thread.sleep(10);
               }
                   catch (InterruptedException e) {
                  }
            }
            try {
               for (int i = 0; i < 10 + Math.random() * 150; i++) {
                  Thread.sleep(100);
               }
            }
                catch (InterruptedException e) {
               }
         }
      }
   }