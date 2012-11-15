   
/** Determines if three doubles can be sides of triangle. 
*/
   public class Triangle 
   {
      /** side 1. */
      private double a; 
   	/** side 2. */
      private double b; 
      /** side 3. */
      private double c;  
   
   /** Constructor for takes in  a triangle based on lengths of three sides. 
   		@param aIn side 1
   		@param bIn side 2
   		@param cIn side 3 
   */
      public Triangle(double aIn, double bIn, double cIn) 
      {
         a = aIn;
         b = bIn;    
         c = cIn;  
         if (a <= 0 || b <= 0 || c <= 0) { 
            throw new IllegalArgumentException("Sides: " + a + " " + b 
               + " " + c 
               + " -- each must be greater than zero.");
         }
         if ((a >= b + c) || (b >= a + c) || (c >= a + b)) {
            throw new RuntimeException("Sides: " + a + " " + b + " " + c 
               + " -- not a triangle."); 	
         }
      }
      
      /** Classifies a triangle based on lengths of three sides. 
   		@return classification of triangle
   	 */
      public String classify() {  
         String result;  
         if ((a == b) && (b == c)) {  
            result = "equilateral";   
         } 
         else if ((a != b) && (a != c) && (b != c)) { 
            result = "scalene";
         } 
         else { 
            result = "isosceles";
         } 
          
         return result; 
      } 
   
   }
