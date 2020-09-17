/**
 * import libraries and package.
 */
package sait.frs.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sait.frs.exception.InvalidCitizenshipException;
import sait.frs.exception.InvalidNameException;
import sait.frs.exception.NoMoreSeatsException;
import sait.frs.exception.NullFlightException;
import sait.frs.manager.FlightManager;
import sait.frs.manager.Manager;
import sait.frs.manager.ReservationManager;
import sait.frs.problemdomain.Flight;

/**
 * Holds the components for the flights tab.
 * 
 * @author Hong and Kim
 * @version July 09, 2020
 */
public class FlightsTab extends TabBase {
   /**
    * Instance of travel manager.
    */
   private FlightManager flightManager;
   private ReservationManager reservationManager;
   /**
    * Instance of travel flight.
    */
   private Flight flight;
   /**
    * Attributes for GUI.
    */
   private JTextField textField_Flight_Reserve;
   private JTextField textField_Airline_Reserve;
   private JTextField textField_Day_Reserve;
   private JTextField textField_Time_Reserve;
   private JTextField textField_Cost_Reserve;
   private JTextField textField_Name_Reserve;
   private JTextField textField_Citizenship_Reserve;
   private JLabel lblFlight_Reserve;
   private JLabel lblAirline_Reserve;
   private JLabel lblDay_Reserve;
   private JLabel lblTime_Reserve;
   private JLabel lblCost_Reserve;
   private JLabel lblName_Reserve;
   private JLabel lblCitizenship_Reserve;
   private JScrollPane scrollPane;
   private JLabel lblFinder;
   private JLabel lblFrom_Finder;
   private JLabel lblTo_Finder;
   private JLabel lblDay_Finder;
   private JComboBox comboBox_From;
   private JComboBox comboBox_To;
   private JComboBox comboBox_Day;

   /**
    * List of flights.
    */
   private JList flightsList;

   /**
    * Action listener for JList.
    */
   private MyListSelectionListener listSelectionListener;

   private DefaultListModel<Flight> flightsModel;

   /**
    * Creates the components for flights tab. Constructor to call FlightLoad()
    * method and AirportLoad() method.
    * 
    * @throws IOException - if the code inside main method doesn't work, it throws
    *                     IOException.
    * @param flightManager - flightManager Object.
    * @param reservationManager - reservationManager Object
    */
   public FlightsTab(FlightManager flightManager,ReservationManager reservationManager) throws IOException {
      this.flightManager = flightManager;
      this.reservationManager = reservationManager;
      
      panel.setLayout(null);

      JPanel northPanel = this.createNorthPanel();
      panel.add(northPanel, BorderLayout.NORTH);

      JPanel centerPanel = this.createCenterPanel();
      panel.add(centerPanel);

   }
   /**
    * Creates the north panel.
    * 
    * @return JPanel that goes in north.
    */
   private JPanel createNorthPanel() {
      JPanel panel = new JPanel();
      // set up panel size and location.
      panel.setBounds(0, 0, 700, 47);
      panel.setLayout(null);

      // set up label name, size, location and font.
      JLabel lblReservations = new JLabel("Flights");
      lblReservations.setBounds(107, 0, 475, 34);
      panel.add(lblReservations);
      lblReservations.setFont(new Font("Tahoma", Font.PLAIN, 24));
      lblReservations.setHorizontalAlignment(SwingConstants.CENTER);

      return panel;
   }

   /**
    * Creates the center panel.
    * 
    * @return JPanel that goes in center.
    */
   private JPanel createCenterPanel() {
      // set up panel size and location.
      JPanel panel = new JPanel();
      panel.setBounds(0, 49, 700, 452);

      panel.setLayout(null);
      // set up JList.
      flightsModel = new DefaultListModel<>();
      flightsList = new JList(flightsModel);
      flightsList.setBounds(36, 15, 378, 239);

      // User can only select one item at a time.
      flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      // Wrap JList in JScrollPane so it is scrollable.
      scrollPane = new JScrollPane(this.flightsList);
      scrollPane.setBounds(36, 15, 378, 239);
      this.listSelectionListener = new MyListSelectionListener();
      this.flightsList.addListSelectionListener(this.listSelectionListener);

      panel.add(scrollPane);

      // set up JLabel name, font, size and added to panel.
      JLabel lblNewLabel = new JLabel("Reserve");
      lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
      lblNewLabel.setBounds(517, 14, 83, 16);
      panel.add(lblNewLabel);

      // set up JTextField location, length, size and added to panel.
      textField_Flight_Reserve = new JTextField();
      textField_Flight_Reserve.setBounds(543, 54, 116, 22);
      panel.add(textField_Flight_Reserve);
      textField_Flight_Reserve.setColumns(10);
      // in order to not access JTextField by user as a default.
      textField_Flight_Reserve.setEditable(false);

      // set up JTextField location, length, size and added to panel.
      textField_Airline_Reserve = new JTextField();
      textField_Airline_Reserve.setColumns(10);
      textField_Airline_Reserve.setBounds(543, 76, 116, 22);
      panel.add(textField_Airline_Reserve);
      // in order to not access JTextField by user as a default.
      textField_Airline_Reserve.setEditable(false);

      // set up JTextField location, length, size and added to panel.
      textField_Day_Reserve = new JTextField();
      textField_Day_Reserve.setColumns(10);
      textField_Day_Reserve.setBounds(543, 99, 116, 22);
      panel.add(textField_Day_Reserve);
      // in order to not access JTextField by user as a default.
      textField_Day_Reserve.setEditable(false);

      // set up JTextField location, length, size and added to panel.
      textField_Time_Reserve = new JTextField();
      textField_Time_Reserve.setColumns(10);
      textField_Time_Reserve.setBounds(543, 123, 116, 22);
      panel.add(textField_Time_Reserve);
      // in order to not access JTextField by user as a default.
      textField_Time_Reserve.setEditable(false);

      // set up JTextField location, length, size and added to panel.
      textField_Cost_Reserve = new JTextField();
      textField_Cost_Reserve.setColumns(10);
      textField_Cost_Reserve.setBounds(543, 146, 116, 22);
      panel.add(textField_Cost_Reserve);
      // in order to not access JTextField by user as a default.
      textField_Cost_Reserve.setEditable(false);

      // set up JTextField location, length, size and added to panel.
      textField_Name_Reserve = new JTextField();
      textField_Name_Reserve.setColumns(10);
      textField_Name_Reserve.setBounds(543, 170, 116, 22);
      panel.add(textField_Name_Reserve);

      // set up JTextField location, length, size and added to panel.
      textField_Citizenship_Reserve = new JTextField();
      textField_Citizenship_Reserve.setColumns(10);
      textField_Citizenship_Reserve.setBounds(543, 193, 116, 22);
      panel.add(textField_Citizenship_Reserve);

      // set up JLabel name, font, size and added to panel.
      lblFlight_Reserve = new JLabel("Flight:");
      lblFlight_Reserve.setHorizontalAlignment(SwingConstants.RIGHT);
      lblFlight_Reserve.setBounds(486, 57, 56, 16);
      panel.add(lblFlight_Reserve);

      // set up JLabel name, font, size and added to panel.
      lblAirline_Reserve = new JLabel("Airline:");
      lblAirline_Reserve.setHorizontalAlignment(SwingConstants.RIGHT);
      lblAirline_Reserve.setBounds(486, 79, 56, 16);
      panel.add(lblAirline_Reserve);

      // set up JLabel name, font, size and added to panel.
      lblDay_Reserve = new JLabel("Day:");
      lblDay_Reserve.setHorizontalAlignment(SwingConstants.RIGHT);
      lblDay_Reserve.setBounds(486, 102, 56, 16);
      panel.add(lblDay_Reserve);

      // set up JLabel name, font, size and added to panel.
      lblTime_Reserve = new JLabel("Time:");
      lblTime_Reserve.setHorizontalAlignment(SwingConstants.RIGHT);
      lblTime_Reserve.setBounds(486, 126, 56, 16);
      panel.add(lblTime_Reserve);

      // set up JLabel name, font, size and added to panel.
      lblCost_Reserve = new JLabel("Cost:");
      lblCost_Reserve.setHorizontalAlignment(SwingConstants.RIGHT);
      lblCost_Reserve.setBounds(486, 150, 56, 16);
      panel.add(lblCost_Reserve);

      // set up JLabel name, font, size and added to panel.
      lblName_Reserve = new JLabel("Name:");
      lblName_Reserve.setHorizontalAlignment(SwingConstants.RIGHT);
      lblName_Reserve.setBounds(486, 173, 56, 16);
      panel.add(lblName_Reserve);

      // set up JLabel name, font, size and added to panel.
      lblCitizenship_Reserve = new JLabel("Citizenship:");
      lblCitizenship_Reserve.setHorizontalAlignment(SwingConstants.RIGHT);
      lblCitizenship_Reserve.setBounds(462, 196, 80, 16);
      panel.add(lblCitizenship_Reserve);

      // set up JButton name, location, size and create ActionListener as inner class for button.
      JButton btnNewButton = new JButton("Reserve");
      btnNewButton.setBounds(486, 228, 173, 25);
      panel.add(btnNewButton);
      btnNewButton.addActionListener(new ReserveBtnListener());

      // set up JLabel name, font, size and added to panel.
      lblFinder = new JLabel("Flight Finder");
      lblFinder.setHorizontalAlignment(SwingConstants.CENTER);
      lblFinder.setFont(new Font("Tahoma", Font.PLAIN, 21));
      lblFinder.setBounds(0, 301, 682, 35);
      panel.add(lblFinder);

      // set up JLabel name, font, size and added to panel.
      lblFrom_Finder = new JLabel("From:");
      lblFrom_Finder.setHorizontalAlignment(SwingConstants.RIGHT);
      lblFrom_Finder.setBounds(10, 349, 39, 25);
      panel.add(lblFrom_Finder);

      // set up JLabel name, font, size and added to panel.
      lblTo_Finder = new JLabel("To:");
      lblTo_Finder.setHorizontalAlignment(SwingConstants.RIGHT);
      lblTo_Finder.setBounds(10, 374, 39, 25);
      panel.add(lblTo_Finder);

      // set up JLabel name, font, size and added to panel.
      lblDay_Finder = new JLabel("Day:");
      lblDay_Finder.setHorizontalAlignment(SwingConstants.RIGHT);
      lblDay_Finder.setBounds(10, 399, 39, 25);
      panel.add(lblDay_Finder);

      // set up JComboBox location and create comboBox list using getAirports() method
      // from manager and added to panel.
      comboBox_From = new JComboBox(flightManager.getAirports().toArray());
      comboBox_From.setBounds(50, 349, 632, 24);
      panel.add(comboBox_From);
      // in order to not access JComboBox by user as a default.
      comboBox_From.setEditable(false);

      // set up JComboBox location and create comboBox list using getAirports() method
      // from manager and added to panel.
      comboBox_To = new JComboBox(flightManager.getAirports().toArray());
      comboBox_To.setBounds(50, 374, 632, 24);
      panel.add(comboBox_To);
      // in order to not access JTextField by user as a default.
      comboBox_To.setEditable(false);

      // create String type array using assets from manager and allocate it into the
      // JComboBox.
      String days[] = { flightManager.WEEKDAY_ANY, flightManager.WEEKDAY_SUNDAY, flightManager.WEEKDAY_MONDAY, flightManager.WEEKDAY_TUESDAY,
            flightManager.WEEKDAY_WEDNESDAY, flightManager.WEEKDAY_THURSDAY, flightManager.WEEKDAY_FRIDAY, flightManager.WEEKDAY_SATURDAY };
      comboBox_Day = new JComboBox(days);
      comboBox_Day.setBounds(50, 399, 632, 24);
      panel.add(comboBox_Day);
      // in order to not access JTextField by user as a default.
      comboBox_Day.setEditable(false);

      // set up JButton name, location, size and create ActionListener as inner class
      // for button.
      JButton btnNewButton_1 = new JButton("Find Flights");
      btnNewButton_1.setBounds(0, 427, 699, 25);
      panel.add(btnNewButton_1);
      btnNewButton_1.addActionListener(new FindBtnListener());

      return panel;
   }

   /**
    * Inner action listener class that listens for a find button to be clicked.
    * This is the inner class with the ActionListener required for GUI.
    * 
    * @author Hong and Kim
    * @version June 23, 2020
    */
   class FindBtnListener implements ActionListener {

      /**
       * Overrides actionPerformed() method.
       * 
       * @param e - ActionEvent e
       */
      @Override
      public void actionPerformed(ActionEvent e) {

         // clear all elements on the DefaultListModel in order to get new information.
         flightsModel.removeAllElements();

         // find flights list which based on user's want to know.
         for (int i = 0; i < flightManager.getFlights().size(); i++) {

            if (comboBox_Day.getSelectedItem().equals("Any")) {
               if ((flightManager.getFlights().get(i).getFrom().equals(comboBox_From.getSelectedItem())
                     && (flightManager.getFlights().get(i).getTo().equals(comboBox_To.getSelectedItem())))) {
                  flightsModel.addElement(flightManager.getFlights().get(i));

               }

            } else {

               if ((flightManager.getFlights().get(i).getFrom().equals(comboBox_From.getSelectedItem()))
                     && (flightManager.getFlights().get(i).getTo().equals(comboBox_To.getSelectedItem()))
                     && (flightManager.getFlights().get(i).getWeekday().equals(comboBox_Day.getSelectedItem()))) {

                  flightsModel.addElement(flightManager.getFlights().get(i));

               }
            }

         }

      }
   }

   /**
    * Inner action listener class that listens for a reserve button to be clicked.
    * This is the inner class with the ActionListener required for GUI.
    * 
    * @author Hong and Kim
    * @version June 23, 2020
    */
   class ReserveBtnListener implements ActionListener {
      /**
       * Overrides actionPerformed() method.
       * 
       * @param e - ActionEvent e
       */
      @Override
      public void actionPerformed(ActionEvent e) {

         // reserve flight which user wants and show reservation code up for a user.
         // in order to handle with exception
         
         
            
               try {
                  JOptionPane.showMessageDialog(null,
                        "Reseration created. Your code is "
                              + reservationManager.makeReservation(flightManager.findFlightByCode(textField_Flight_Reserve.getText()),
                                    textField_Name_Reserve.getText(), textField_Citizenship_Reserve.getText())
                              + ".");
               } catch (HeadlessException e1) {
                  e1.printStackTrace();
               } catch (Exception e1) {
                  e1.printStackTrace();
               }
               

      }

   }

   /**
    * Inner ListSelectionListener class that listens for a JList to be clicked.
    * This is the inner class with the ListSelectionListener required for GUI.
    * 
    * @author Hong and Kim
    * @version June 23, 2020
    */

   class MyListSelectionListener implements ListSelectionListener {
      /**
       * Called when user selects an item in the JList.
       * @param e - ListSelectionEvent e
       */
      @Override
      public void valueChanged(ListSelectionEvent e) {
         // avoid double selection automatically.
         if (!e.getValueIsAdjusting()) {
            // index of flightsList
            int selected = flightsList.getSelectedIndex();
            
            // avoid out of bounds exception.
            if (selected > -1) {
               //put flight's information which user want to reserve in the Textfield area's with formatted.
               textField_Flight_Reserve.setText(flightsModel.get(selected).getCode());
               textField_Airline_Reserve.setText(flightsModel.get(selected).getAirlineName());
               textField_Day_Reserve.setText(flightsModel.get(selected).getWeekday());
               textField_Time_Reserve.setText(flightsModel.get(selected).getTime());
               textField_Cost_Reserve
                     .setText((String.format("%s%.2f", "$ ", flightsModel.get(selected).getCostPerSeat())));

            }
         }

      }

   }

}