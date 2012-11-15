//********************************************************************
//  PersonalLibrary.java       Author: J. Cross
//  Demonstrates inheritance.
//********************************************************************
    public class PersonalLibrary {
    
    
   //-----------------------------------------------------------------
   //  Instantiates a derived class and invokes its inherited and
   //  local methods. 
   //-----------------------------------------------------------------
       public static void main(String[] args) {
         Book hemingway = new Book("Hemingway",
            	"Green Hills of Africa", 234, 50.0);
         Fiction clancy = new Fiction("Clancy", 
         		"The Hunt for Red October", 
         		490, 39.0, "Sean");
         Novel grisham = new Novel("Grisham",
         		"The Firm", 550, 28.0, "Tom", 0); 
      
         System.out.println(hemingway);
         System.out.println(clancy);
         System.out.println("\n" + clancy.getMainCharacter());
      }
   }
