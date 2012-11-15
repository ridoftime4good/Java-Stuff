//********************************************************************
//  Fiction.java       Author: J. Cross
//
//  Represents a work of fiction, which is a book.
//********************************************************************
 
    public class Fiction extends Book implements Comparable {
    
      protected String mainCharacter = new String("none");
   
   
       public Fiction() {
         super();
      }
   
   
       public Fiction(String theAuthor, String theTitle,  
       		int thePages, Double theValue, String theMainCharacter) {
         super(theAuthor, theTitle, thePages, theValue);
         mainCharacter = theMainCharacter;
      }
   
   
       public void setMainCharacter(String theMainCharacter) {
         mainCharacter = theMainCharacter;
      }
   
   
       public String getMainCharacter() { 
         return mainCharacter + "";
      }
   
   
       public String toString() { 
         return super.toString() + "\nMain Character: " + mainCharacter;
      }
   
   
       public int compareTo(Object obj) {
         Comparable cFiction = this;
         return (value.compareTo(((Fiction) obj).value));
      }
   }
