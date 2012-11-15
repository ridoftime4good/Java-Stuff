//********************************************************************
//  NonFiction.java       Author: J. Cross
//  Represents a work of nonfiction, which is a book.
//********************************************************************
    public class NonFiction extends Book {
      
      protected String topic = new String("TBD");
   
   
       public NonFiction() {
         super();
      }
   
   
       public NonFiction(String theAuthor, String theTitle, 
       		int thePages, Double theValue) {
         super(theAuthor, theTitle, thePages, theValue);
      }
   
   
       public NonFiction(String theAuthor, String theTitle, 
       		int thePages, Double theValue, String theTopic) {
         super(theAuthor, theTitle, thePages, theValue);   
         
         topic = theTopic;
      }
   
   
       public String getTopic() {
         return topic;
      }
   
   
       public String toString() {
         return super.toString() + "\nTopic: " + topic;
      }
   }
