/**
 * Search class with binarySearch method.
 */
   public class Search {
   
      public static void main(String[] args) {
      
         int[] ia = {12, 34, 56, 65, 73, 81, 97};
      
         System.out.println("Index of 12 is: " + binarySearch(12, ia));
         System.out.println("Index of 65 is: " + binarySearch(65, ia));
         System.out.println("Index of 97 is: " + binarySearch(97, ia));
         System.out.println("Index of 100 is: " + binarySearch(100, ia));
      }
   
      public static int binarySearch(int key, int[] intArray)
      {
         int low, middle, high;
         low = 0;
         high = intArray.length - 1;
         while (low <= high)
         {
            middle = (low + high) / 2;
            if (key < intArray[middle]) {
               high = middle - 1;
            }
            else if (key > intArray[middle]) {
               low = middle + 1;
            }
            else {
               return middle;
            }
         }
         return -1;
      }
   
   }