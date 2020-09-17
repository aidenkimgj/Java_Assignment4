package sait.frs.exception;
/**
 * This this the InvalidCitizenshipExceptio class, it will return exception when user put wrong information in the citizenship box.
 * 
 *  @author Hong and Kim
 *  @version July 09, 2020
 */
public class InvalidCitizenshipException extends Exception{
   public InvalidCitizenshipException(){
      super("Can not find Citizenship.");
   }
}