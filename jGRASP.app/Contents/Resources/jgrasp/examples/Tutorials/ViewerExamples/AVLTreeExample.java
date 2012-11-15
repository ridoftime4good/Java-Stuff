   import jgraspvex.AVLTree;


    /** This example is intended to illustrate the
     *  capabilities of the animating binary tree viewer
     *  in jGRASP when applied to an AVL tree. To see AVL rotations
     *  "all at once", set a breakpoint on the line that starts with
     *  "avl_tree.add", debug, and resume repeatedly. **/
   public class AVLTreeExample {
    
      private static int[][] values = {
         	// Show right rotation.
         	{ 10, 5, 15, 3, 7, 1 },
         	// Show left rotation.
         	{ 10, 15, 5, 20, 12, 30 },
         	// Show left-right rotation.
         	{ 10, 15, 2, 1, 4, 7 },
         	// Show right-left rotation.
         	{ 10, 15, 2, 20, 12, 11 },
         	// Larger example
         	{ 31, 19, 51, 59, 21, 34, 56, 12, 1, 2, 3, 94,
            	59, 91, 7, 9, 43, 32, 61, 63, 65, 64, 62, 4,
            	82, 79, 44, 45, 46, 48, 49, 50, 32, 24, 23, 26,
            	22, 25, 27, 29, 28 } };
   
   
      public static void main(String[] args) {
         while (true) {
            for (int s = 0; s < values.length; s++) {
               int[] set = values[s];
               AVLTree avlTree = new AVLTree();
               for (int i = 0; i < set.length; i++) {
                  avlTree.add(new Integer(set[i]));
               }
            }
         }
      }
   }