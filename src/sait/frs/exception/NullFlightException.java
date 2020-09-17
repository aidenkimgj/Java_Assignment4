package sait.frs.exception;
/**
 * This this the NullFlightException class, it will return exception when there is no flight object.
 * 
 * @author Hong and Kim
 * @version July 09, 2020
 */
public class NullFlightException extends Exception{
   public NullFlightException(){
      super("Can not find Flight.");
   }
}