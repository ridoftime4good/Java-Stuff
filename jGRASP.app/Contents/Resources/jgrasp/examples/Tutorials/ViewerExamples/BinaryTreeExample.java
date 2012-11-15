   import jgraspvex.BinaryTree;


    /** This example is intended to illustrate the
     *  capabilities of the animating binary tree viewer
     *  in jGRASP. Set a breakpoint, debug, drag "bt" from
     *  the debug variables window to pop up a viewer,
     *  and step in repeatedly. **/
   public class BinaryTreeExample {
    
    	/** Unsorted test values. **/
      private static int[] values = { 5, 9, 12, 3, 22, 1, 17,
            4, 8, 11 };
   			
   	/** Sorted test values. **/
      private static int[] sortedValues = { 1, 3, 4, 5, 8, 9,
            11, 12, 17, 22 };
    
   
   	/** Runs the example.
   	 *
   	 *  @param args command line arguments, which are ignored. **/
      public static void main(String[] args) {
         // To view the following int array as a binary tree
         // using the structure identifier viewer, choose the binary tree
         // structure and use expressions:
         //   Root Node:  (_tree_.length > 0)?0:-1
         //   Left Node:  _tree_[_node_]
         //   Right Node:  _tree_[_node_ + 1]
         //   Value: _tree_[_node_ + 2]
         int[] ia = new int[] { 3, 6, 0, -1, -1, 1, 9, 12,
               7, -1, -1, 5, -1, -1, 10 };
         ia = new int[] { 3, 6, 0, -1, -1, 1, 12, 9,
               7, -1, -1, 5, -1, -1, 10 };
       
         while (true) {
            BinaryTree bt = new BinaryTree();
            for (int i = 0; i < values.length; i++) {
               bt.add(new Integer(values[i]));
            }
            for (int i = 0; i < sortedValues.length; i++) {
               bt.remove(new Integer(sortedValues[i]));
            }
         }
      }
   }
