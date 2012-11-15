
   import jgraspvex.LinkedList;


    /** This example is intended to illustrate the
     *  capabilities of the animating linked list viewer
     *  in jGRASP. Set a breakpoint, debug, drag "list"
     *  from the debug variables window to pop up a viewer,
     *  and step in repeatedly. **/
    public class LinkedListExample {
    
       public static void main(String[] args) {
       
         while (true) {
            LinkedList list = new LinkedList();
            for (int i = 0; i < 3; i++) {
               list.add(String.valueOf(i));
            }
            list.add(null);
            
            for (int i = 3; i >= 0; i--) {
               list.insert("x" + i, i);
            }
         
            for (int i = 0; i < 3; i++) {
               list.remove(i);
            }
         }
      }
   }