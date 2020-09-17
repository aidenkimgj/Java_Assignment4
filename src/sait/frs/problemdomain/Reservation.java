package sait.frs.problemdomain;



import sait.frs.exception.InvalidCitizenshipException;
import sait.frs.exception.InvalidNameException; 

/**
 * This class use to store reservation info.
 * @author 703922
 * @version July 09, 2020
 */
public class Reservation {
   private String code;
   private String flightCode;
   private String airline;
   private String name;
   private String citizenship;
   private double cost;
   private boolean active;

   /**
    * This is a constructor to store updated customer's information.
    * @param flight - flight object
    * @param code - reservation code
    * @param name - client name
    * @param citizenship - client citizenship
    * @throws InvalidNameException - case of invalid name
    * @throws InvalidCitizenshipException -  case of invalid citizenship
    */
   public Reservation(Flight flight, String code, String name, String citizenship) throws InvalidNameException, InvalidCitizenshipException {
      this.code = code;
      this.flightCode = flight.getCode();
      this.airline = flight.getAirlineName();
      setName(name);
      setCitizenship(citizenship);
      this.cost = flight.getCostPerSeat();
      this.active = true;
   }
   
   /**
    * This is a constructor to store reservation info.
    * @param reservationCode - reservation code
    * @param flightCode - flight code
    * @param name - client name 
    * @param airlineName - flight name
    * @param citizenship - client citizenship
    * @param cost - seat cost
    * @param isActive - reservation code available
    * @throws InvalidNameException - case of invalid name
    * @throws InvalidCitizenshipException - case of invalid citizenship
    */
    public Reservation(String reservationCode, String flightCode, String name, String airlineName, String citizenship, double cost, boolean isActive) 
              throws InvalidNameException, InvalidCitizenshipException
          {
              this.code = reservationCode;
              this.flightCode = flightCode;
              setName(name);
              setCitizenship(citizenship);
              this.airline = airlineName;
              this.cost = cost;
              setActive(isActive);
          }

    /**
     * This method returns name
     * @return name - client name
     */
   public String getName() {
      return name;
   }

   /**
    * This method get name data from user's input. Use this method when user reserve flight and update user's info.
    * @param name - client name 
    * @throws InvalidNameException - case of invalid name
    */ 
   public void setName(String name) throws InvalidNameException {
      if(name == null || name == " ") {
         throw new InvalidNameException();
      }
      this.name = name;
   }

   /**
    * This method returns citizenship.
    * @return citizenship - client citizenship
    */
   public String getCitizenship() {
      return citizenship;
   }

   /**
    * This method get citizenship data from user's input. Use this method when user reserve flight and update user's info.
    * @param citizenship - client citizenship
    * @throws InvalidCitizenshipException - case of invalid citizenship
    */
   public void setCitizenship(String citizenship) throws InvalidCitizenshipException {
      
      if(citizenship == null || citizenship == " ") {
         throw new InvalidCitizenshipException();
      }
      this.citizenship = citizenship;
   }

   /**
    * This method return boolean data. Can figure the reservation is active or inactive.
    * @return active - true or false.
    */
   public boolean isActive() {
      return active;
   }

   /**
    * This method get boolean value called active or inactive. when client's info is changed, would be used this method.
    * @param active - ture or false
    */
   public void setActive(boolean active) {
      this.active = active;
   }

   /**
    * This method get reservation code value.
    * @return code - reservation code
    */
   public String getCode() {
      return code;
   }

   /**
    * This method returns flight code.
    * @return flightCode - flight code
    */
   public String getFlightCode() {
      return flightCode;
   }

   /**
    * This method returns airline name.
    * @return airline - airline name
    */
   public String getAirline() {
      return airline;
   }

   /**
    * This method returns flight cost.
    * @return cost - seat cost
    */
   public double getCost() {
      return cost;
   }

   /**
    * This method returns reservation number. It uploads on the JList, when client search this code using name, code or airline name.
    * @return getCode() - reservation code
    */
   @Override
   public String toString() {
      return getCode();
   }

}