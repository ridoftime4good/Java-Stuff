   // OddEven - J. Cross
   // Generate the CSD for this example to see how class, method, 
   // loop, and if statements are diagrammed.
   // Click "View"--"Generate CSD" on the jGRASP menu.
   
   //-----------------------------------------------------------------
   //  Prints integer values from 1 to LIMIT, counting and summing 
   //  odd and even integers.
   //-----------------------------------------------------------------
   
    public class OddEven {
    
       public static void main(String[] args) {
         int limit = 15;
         int odd = 0;
         int even = 0;
         int oddSum = 0;
         int evenSum = 0;
         for (int count = 1; count <= limit; count++) {
            System.out.print("\t" + count + " ");
            if (count % 2 != 0) {
               odd++;
               oddSum += count;
            }
            else {
               even++;
               evenSum += count;   
            }
            if (count % 5 == 0) {
               System.out.println();
            }                   
         }   
         System.out.println("\n# Even = " + even + "  Sum = " + evenSum);
         System.out.println("# Odd  = " + odd + "  Sum = " + oddSum);
      }
   }
