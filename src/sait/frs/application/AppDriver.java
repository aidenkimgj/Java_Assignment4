package sait.frs.application;

import java.io.IOException;

import sait.frs.gui.*;

/**
 * Application driver
 * This program is call MainWindow class's method called display();
 * This class have main method to generate this program.
 * 
 * @author Hong and Kim
 * @version June 23, 2020
 */
public class AppDriver {

   /**
    * Entry point to Java application.
    * @param args.
    * @throws IOException if the code inside main method doesn't work, it throws IOException. 
    */
   public static void main(String[] args) throws IOException {
	   
      MainWindow mainWindow = new MainWindow();
      mainWindow.display();
   }

}