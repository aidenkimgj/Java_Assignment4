package sait.frs.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

import sait.frs.problemdomain.Flight;

/**
 * This class manage flight information using some methods and generate flight
 * screen.
 * 
 * @author 703922
 * @version July 09, 2020
 */
public class FlightManager {

	// final variables indicate days.
	public static final String WEEKDAY_ANY = "Any";
	public static final String WEEKDAY_SUNDAY = "Sunday";
	public static final String WEEKDAY_MONDAY = "Monday";
	public static final String WEEKDAY_TUESDAY = "Tuesday";
	public static final String WEEKDAY_WEDNESDAY = "Wednesday";
	public static final String WEEKDAY_THURSDAY = "Thursday";
	public static final String WEEKDAY_FRIDAY = "Friday";
	public static final String WEEKDAY_SATURDAY = "Saturday";
	public static final String FLIGHTS_TEXT = "res/flights.csv";
	public static final String AIRPORTS_TEXT = "res/airports.csv";

	// declare flights and airports objects type of ArrayList.
	private ArrayList<Flight> flights;
	private ArrayList<String> airports;

	/**
	 * FlightManager's constructor. This creates flights and airports objects and
	 * generate populating methods.
	 * 
	 * @throws FileNotFoundException - if can not find file for the populateFlights
	 *                               method, it throws FileNotFoundException.
	 */
	public FlightManager() throws FileNotFoundException {

		flights = new ArrayList();
		airports = new ArrayList();
		populateAirports();
		populateFlights();
	}

	/**
	 * returns flights object.
	 * 
	 * @return flights
	 */
	public ArrayList<Flight> getFlights() {

		return flights;
	}

	/**
	 * returns airports object.
	 * 
	 * @return airports
	 */
	public ArrayList<String> getAirports() {

		return airports;
	}

	/**
	 * We do not use this method. Because we built is function in the other method.
	 * 
	 * @param code - airport code
	 * @return "" - nothing
	 */
	public String findAirportByCode(String code) {
		return "";
	}

	/**
	 * This method will find flight information based on the flight code.
	 * 
	 * @param code - flight code
	 * @return flight - flight object, if this program can't find code retrun null.
	 * 
	 */

	public Flight findFlightByCode(String code) {
		for (Flight flight : flights) {
			if (flight.getCode().equals(code))
				return flight;
		}
		return null;
	}

	/**
	 * This method load data from CSV file to flights(Flight type ArrayList).
	 * 
	 * @throws FileNotFoundException - if can not find file, it throws
	 *                               FileNotFoundException.
	 */
	private void populateFlights() throws FileNotFoundException {
		Scanner sc = new Scanner(new File(FLIGHTS_TEXT));

		String flightInfo = "";
		String[] line;
		while (sc.hasNext()) {
			flightInfo = sc.nextLine();
			line = flightInfo.split(",");
			// put flight information in flight's constructor.
			Flight flight = new Flight(line[0], line[1], line[2], line[3], line[4], line[5], Integer.parseInt(line[6]),
					Double.parseDouble(line[7]));
			flights.add(flight);
		}
		sc.close();

	}

	/**
	 * This method populate airport information from CSV file to the
	 * airports(airport type ArrayList). FileNotFoundException - if can not find
	 * file, it throws FileNotFoundException.
	 */

	private void populateAirports() {
		Scanner sc;
		try {
			sc = new Scanner(new File(AIRPORTS_TEXT));

			String airportInfo = "";
			String[] line;
			while (sc.hasNext()) {
				airportInfo = sc.next();
				line = airportInfo.split(",");
				airports.add(line[0]);
				sc.nextLine();
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}