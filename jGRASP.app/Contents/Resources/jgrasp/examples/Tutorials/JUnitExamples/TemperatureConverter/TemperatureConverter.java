/**  Converts temperatures between Celsius and Farenheit. */    
   public class TemperatureConverter {
   /**  Count for conversions to Celsius.*/
      private int conversionsToCelsius;
   /**  Count for conversions to Farenheit.*/
      private int conversionsToFahrenheit;
      
   /**  Constructor - initializes fields to zero. */   
      public TemperatureConverter() {
         conversionsToCelsius = 0;
         conversionsToFahrenheit = 0;
      }
      
   /**  Getter for conversionsToCelsius. 
    *   @return count for conversions to Celsius. */    
      public double getConversionsToCelsius() {
         return conversionsToCelsius;
      }
      
   /**  Setter for conversionsToCelsius. 
    *   @param count  number of converstions to Celsius. */   
      public void setConversionsToCelsius(int count) {
         conversionsToCelsius = count;
      }
   
   /**  Getter for conversionsToFahrenheit. 
    *   @return count for conversions to Fahrenheit. */     
      public double getConversionsToFahrenheit() {
         return conversionsToFahrenheit;
      }
   
   /**  Setter for conversionsToFahrenheit.   
     *   @param count  number of converstions to Fahrenheit. */      
      public void setConversionsToFahrenheit(int count) {
         conversionsToFahrenheit = count;
      }
      
   /**  Initializes fields to zero. */    
      public void resetCounters() {
         conversionsToCelsius = 0;
         conversionsToFahrenheit = 0;
      }
      
   /**  Convert temperature in Farenheit to Celsius. 
    *   @param f  temperature in Fahrenheit. 
    *   @return temperature in Celsius. */       
      public double convertToCelsius(double f) {
         double c = (f - 32) * (5.0 / 9);
         conversionsToCelsius++;
         return c;
      }
   
   /**  Convert temperature in Celsius to Farenheit. 
    *   @param c  temperature in Celsius. 
    *   @return temperature in Farenheit. */       
      public double convertToFarenheit(double c) {
         double f = c * (9.0 / 5) + 32;
         conversionsToFahrenheit++;
         return f;
      }
   }