   import org.junit.Assert;
   import org.junit.Before;
   import org.junit.Test;

/** JUnit test file for class Triangle. **/
   public class TriangleTest {
   
      /** Fixture initialization (common initialization
       *  for all tests). **/
      @Before public void setUp() {  
      }
   
      /** Test 1 - for bad arguments. **/
      @Test(expected = IllegalArgumentException.class)
      public void argumentTest1() {
         Triangle t = new Triangle(-12, 5, 10);
      }
      /** Test 2 - for bad arguments. **/	 
      @Test
      public void argumentTest2() {
         boolean thrown = false; 
      
         try {
            Triangle t = new Triangle(2, -5, 10);
         } 
            catch (IllegalArgumentException e) {
               thrown = true;
            }
      
         Assert.assertTrue(thrown);
      }
   
      /** Test 3 - for bad arguments. **/	 
      @Test
      public void argumentTest3() {
         boolean thrown = false;
      
         try {
            Triangle t = new Triangle(2, 5, -10);
         } 
            catch (IllegalArgumentException e) {
               thrown = true;
            }
      
         Assert.assertTrue(thrown);
      }
   
     	/** Test 4 - for "Not a triangle". **/
      @Test (expected = RuntimeException.class)
      public void notTriangleTest() {
         Triangle t = new Triangle(2, 5, 10);
      } 
   
      /** Test 5 - for "Equilateral" triangle. **/
      @Test 
      public void equilateralTest1() {
         Triangle t = new Triangle(12, 12, 12);
         Assert.assertEquals("\nSides: " + 12 + " " + 12 + " " + 12, 
            		"equilateral", 
            		t.classify()); 
      } 
   
      /** Test 6 - for "Isosceles" triangle. **/
      @Test public void isoscelesTest1() {
         Triangle t = new Triangle(12, 12, 13);
         String result = t.classify();
         Assert.assertEquals("\nSides: 12, 12, 13", 
            						"isosceles",
            						result);
      } 
   
      /** Test 7 - for "Scalene" triangle.  **/ 
      @Test public void scaleneTest1() {
      
         Triangle t = new Triangle(1, 2, Math.sqrt(2));
         Assert.assertEquals("\nSides: " + 1 + " " + 2 + " " + Math.sqrt(2), 
            				"scalene",
               			t.classify());
      }
   
      /** Test 8 - for "Equilateral" triangle. **/
      @Test public void equilateralTest2() 
      {
         Triangle t = new Triangle(24, 24, 24);
         Assert.assertEquals("\nSides: " + 24 + " " + 24 + " " + 24, 
            				"equilateral",
               			t.classify());
      }
   
      /** Test 9 - for "Isosceles" triangle.  **/
      @Test public void isoscelesTest2() {
      
         Triangle t = new Triangle(1, 1, Math.sqrt(2));
         Assert.assertEquals("\nSides: " + 1 + " " + 1 + " " + Math.sqrt(2), 
            				"isosceles",
               			t.classify());
      }
   }
