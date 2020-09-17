package sait.frs.problemdomain;

import sait.frs.manager.ReservationManager;
/**
 * This class use to store flight information.
 * @author 703922
 * @version July 09, 2020
 */
public class Flight {
   
   private String code;
   private String airlineName;
   private String from;
   private String to;
   private String weekday;
   private String time;
   private int seat;
   private double costPerSeat;

   /**
    * This is a constructor to store flight information.
    * @param code - flight code
    * @param airlineName - airline name
    * @param from - departure place
    * @param to - destination
    * @param weekday - day
    * @param time - time
    * @param seat - available seat
    * @param costPerSeat - cost
    */
   public Flight(String code, String airlineName, String from, String to, String weekday, String time,int seat, double costPerSeat) {
      
      this.code = code;
      this.airlineName = airlineName;
      this.from = from;
      this.to = to;
      this.weekday = weekday;
      this.time = time;
      this.seat = seat;
      this.costPerSeat = costPerSeat;
   }

   /**
    * This method returns flight code.
    * @return code - flight code 
    */
   public String getCode() {
      return code;
   }

   /**
    * This method returns airline name.
    * @return airlineName - airline name
    */
   public String getAirlineName() {
      return airlineName;
   }

   /**
    * This method returns departure.
    * @return from - departure place
    */
   public String getFrom() {
      return from;
   }

   /**
    * This method returns destination.
    * @return to - destination
    */
   public String getTo() {
      return to;
   }

   /**
    * This method returns weekday.
    * @return weekday - day
    */ 
   public String getWeekday() {
      return weekday;
   }

   /**
    * This method returns time.
    * @return time - time
    */
   public String getTime() {
      return time;
   }

   /**
    * We never used this method (We did not have function to calculate seats.
    * @return seat - available seat
    */
   public int getSeat() {
      return seat;
   }

   /**
    * This method returns the cost per seat.
    * @return costPerSeat - cost
    */
   public double getCostPerSeat() {
      return costPerSeat;
   }
   /**
    * This is boolean method to identify the flight is domestic or not.
    * @return boolean - true or false
    */
   public boolean isDomestic() {

      return ((getFrom().charAt(0) == 'Y') && (getTo().charAt(0) == 'Y'));
   }
   /**
    * We never use this method. ( We did not have to use)
    * @param code - code
    */
   private void parseCode(String code) {
   }
   /**
    * This method returns String value to display list on the JList.
    * @return str - this class information
    */
   public String toString() {
      String str = String.format("%s, From: %s, To: %s, Day: %s, Cost: %.2f", getCode(), getFrom(), getTo(), getWeekday(), getCostPerSeat());
      return str;
      
   }
   
   
}