   import jgraspvex.IntStack;


    /** This example is intended to illustrate the
     *  capabilities of the structure identifier viewer
     *  when applied to stacks. Set a breakpoint, debug,
     *  drag "stack" from the debug variables window to
     *  pop up a viewer, and step repeatedly. **/
    public class IntStackExample {
   
       public static void main(String[] args) {
      
         while (true) {
            IntStack stack = new IntStack();
            for (int i = 0; i < 12; i++) {
               stack.push(i * i);
            }
            for (int i = 0; i < 12; i++) {
               stack.pop();
            }
         }
      }
   }