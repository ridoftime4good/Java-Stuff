//********************************************************************
//  Book.java       Author: J. Cross
//  Represents a book.
//********************************************************************

   public class Book {
    
      private String author = new String("no title");
      private String title = new String("none");
      protected int pages = 0;
      protected Double value = 0.0; 
   
      public Book() {
      }
   
   
      public Book(String theAuthor, String theTitle, int thePages,
       		Double theValue) {
         author = theAuthor; 
         title = theTitle;
         pages = thePages;
         value = theValue;  
      }
   
   
      public String toString() {
         return ("\nAuthor: " + author +  "\nTitle: " + title
            	+ "\nPages: " + pages + "\nValue: " + value);
      }
   }