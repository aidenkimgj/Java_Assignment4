package sait.frs.exception;

/**
 * This this the InvalidNameException class, it will return exception when user put wrong information in the name box.
 * 
 * @author Hong and Kim
 * @version July 09, 2020
 */
public class InvalidNameException extends Exception{
   public InvalidNameException() {
      super("Can not find Name.");
   }
}