package sait.frs.manager;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import sait.frs.exception.InvalidCitizenshipException;
import sait.frs.exception.InvalidNameException;
import sait.frs.exception.NullFlightException;
import sait.frs.problemdomain.Flight;
import sait.frs.problemdomain.Reservation;

/**
 * This class is ReservationManager class, it can manage about reservation
 * parts.
 * 
 * @author Hong and Kim
 * @version July 09, 2020
 *
 */
public class ReservationManager {
   // final constant
   public static final String RESERVATIONS_BINARY = "res/reservations.bin";
   // attribute for reservation type arraylist
   private ArrayList<Reservation> reservations;

   /**
    * This is constructor for ReservationManager class
    * 
    * @throws FileNotFoundException - can't find file from binary file.
    */
   public ReservationManager() throws FileNotFoundException {
      reservations = new ArrayList<>();
      populateFromBinary();
   }

   /**
    * This is makeReservation method that if user choose flight, this method make
    * reservation for client.
    * 
    * @param flight      - Flight type flight object
    * @param name        - client name
    * @param citizenship - client's citizenship
    * @return reservation - user's reservation code
    * @throws Exception - NullFlightException
    */
   public Reservation makeReservation(Flight flight, String name, String citizenship) throws Exception {
      // this block will check whether flight object has information or not.
      if (flight == null) {
         throw new NullFlightException();
         
         /* This block will be able to create reservation code and put reservation information in the reservation class and then
          * put reservation object in the arraylist. After that save it.
          */
      } else {
         String code = generateReservationCode(flight);
         Reservation reservation = new Reservation(flight, code, name, citizenship);
         reservations.add(reservation);
         persist();
         return reservation;
      }
   }
   
   /**
    * This method exists to access reservation arraylist 
    * @return reservations - reservation type arraylist 
    */
   public ArrayList<Reservation> getReservations() {
      return this.reservations;
   }
   
   /**
    * This is findReservations method however, this program cannot use this part because we already implement this part in the reservation tab 
    * 
    * @param code - reservation code
    * @param airline - airline name
    * @param name - client name
    * @return - null
    */
   public ArrayList<Reservation> findReservations(String code, String airline, String name) {

      return null;
   }

   /**
    * This method can find reservation code
    * 
    * @param code - reservation code which is user's reservation
    * @return reserve - return reservation object if this method can't find code it will return null.
    */
   public Reservation findReservationByCode(String code) {

      for (Reservation reserve : reservations) {
         if (reserve.getCode().equals(code))
            return reserve;
      }

      return null;
   }

   /**
    * This method exists to save reservation information into binary file.
    */
   public void persist() {

      try {
         RandomAccessFile randomFile = new RandomAccessFile(RESERVATIONS_BINARY, "rw");
         randomFile.setLength(0L);
         // find reservation info through reservation class type arraylist and then save into binary file.
         for (Reservation reserve : reservations) {
            // give space to this information in order to occupy a suitable position in the binary file.
            randomFile.writeUTF(String.format("%-20s", reserve.getCitizenship()));
            randomFile.writeUTF(reserve.getCode());
            randomFile.writeUTF(reserve.getFlightCode());
            randomFile.writeUTF(reserve.getAirline());
            // give space to this information in order to occupy a suitable position in the binary file.
            randomFile.writeUTF(String.format("%-30s", reserve.getName()));
            randomFile.writeDouble(reserve.getCost());
            randomFile.writeBoolean(reserve.isActive());
         }

         randomFile.close();
      } catch (IOException ex) {
         ex.printStackTrace();
      }

   }

   /**
    * This method takes reservation information from the binary file 
    * and puts it in the reservation class and places the reservation class in the arraylist.
    * 
    * @throws FileNotFoundException - exception for file.
    */
   private void populateFromBinary() throws FileNotFoundException {
      File file = new File(RESERVATIONS_BINARY);
      RandomAccessFile randomFile;
      try {
         randomFile = new RandomAccessFile(file, "rw");
         // in order to search end of file.
         boolean searchFile = true;
         while (searchFile) {
            // trim() method can eliminate unnecessary space 
            String citizenship = randomFile.readUTF().trim();
            String reservationCode = randomFile.readUTF();
            String flightCode = randomFile.readUTF();
            String airlineName = randomFile.readUTF();
            String name = randomFile.readUTF().trim();
            double cost = randomFile.readDouble();
            boolean isActive = randomFile.readBoolean();
            
            try {
               Reservation reservation = new Reservation(reservationCode, flightCode, name, airlineName,
                     citizenship, cost, isActive);
               
               reservations.add(reservation);
            } catch (InvalidNameException e) {
               e.printStackTrace();
            } catch (InvalidCitizenshipException e) {
               e.printStackTrace();
            }
         }
         //this exception will occur when searching reach at the end of binary file.
      } catch (EOFException e) {
         System.out.println("File loading done!");
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   /**
    * This method calculate the available flight seat.
    * 
    * @param flight - Flight object
    * @return flight seat
    */
   private int getAvailableSeats(Flight flight) {

      return flight.getSeat() - 1;
   }

   /**
    * This method generate reservation code depending on whether it's domestic or international
    * @param flight - Flight object
    * @return reservation code
    */
   private String generateReservationCode(Flight flight) {

      char firstLetter1 = 'D';
      char firstLetter2 = 'I';
      String codeNum = Integer.toString((int) (Math.random() * 9000 + 1000));
      if (flight.isDomestic()) {
         return firstLetter1 + codeNum;
      } else {
         return firstLetter2 + codeNum;
      }
   }

}