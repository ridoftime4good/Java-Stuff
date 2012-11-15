
   import java.util.ArrayList;
   import java.util.HashMap;
   import java.util.Hashtable;
   import java.util.LinkedList;
   import java.util.List;
   import java.util.Map;
   import java.util.Stack;
   import java.util.TreeMap;
   import java.util.Vector;


  /** CollectionsExample -- This example is intended to
   *  illustrate the capabilities of the jGRASP viewers for
   *  the classes in the Java Collections Framework.
   *  Set a breakpoint inside the loop and launch the debugger.
   *  After program stops at the breakpoint, open viewers on
   *  one or more variables by "dragging" the variable out of the
   *  Debug tab and releasing it. Now step through the program
   *  and observe the viewers. With AutoStep turned on, the
   *  debugger will begin stepping at the indicated interval
   *  when you click the Step button. Turning off AutoStep
   *  returns the debugger to single step mode. **/
   public class CollectionsExample {
    
    
      public static void main(String[] args) {
         Integer[] integerList = {new Integer(1), new Integer(2),
               new Integer(9), new Integer(7), new Integer(4),
               new Integer(8), new Integer(10), new Integer(5),
               new Integer(3), new Integer(6) };
         String[] stringList = {"cat", "dog", "mouse",
               "ant", "monkey", "flea", "gnu", "lizard",
               "frog", "horse" };
         Comparable[] compList = stringList;
         int[] myint = new int[10];
         List myVector = new Vector();
         List myArrayList = new ArrayList();
         List myLinkedList = new LinkedList();
         Stack myStack = new Stack();
         Map myHashMap = new HashMap(7, 1.5f);
         Map myHashtable = new Hashtable(7, 1.5f);
         myHashMap.put(null, new Integer(0));
         Map myTreeMap = new TreeMap();
      
         while (true) {
            for (int i = 0; i < integerList.length; i++) {
               myVector.add(stringList[i]);
               myArrayList.add(stringList[i]);
               myLinkedList.add(stringList[i]);
               myStack.push(stringList[i]);
               myHashMap.put(stringList[i], integerList[i]);
               myHashtable.put(stringList[i], integerList[i]);
               myTreeMap.put(stringList[i], integerList[i]);
               myint[i] = integerList[i].intValue();
            }
            for (int i = integerList.length - 1; i >= 0; i--) {
               myHashMap.remove(stringList[i]);
            }
            myArrayList.remove(3);
            myArrayList.add(3, "rat");
            myStack.pop();
            myVector.clear();
            myArrayList.clear();
            myLinkedList.clear();
            myHashMap.clear();
            myHashtable.clear();
            myTreeMap.clear();
         }
      }
   }