   //********************************************************************
   //  Novel.java       Author: J. Cross
   //
   //  Represents a novel, which is a work of fiction, which is a book.
   //********************************************************************

    public class Novel extends Fiction {
    
      private int sequels = 0;
   
   
       public Novel() {
         super();
      }
   
   
       public Novel(String theAuthor, String theTitle, int thePages,
       		Double theValue, String theMainCharacter, int sqls) {
         super(theAuthor, theTitle, thePages, theValue, theMainCharacter);
         sequels = sqls; 
      }
   
   
       public String toString() {
         return super.toString() + "\nNumber of sequels: " + sequels;
      }
   }
