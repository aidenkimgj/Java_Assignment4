/**
 * import libraries and package.
 */
package sait.frs.gui;

import java.awt.Font; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


import sait.frs.manager.ReservationManager;
import sait.frs.problemdomain.Reservation;

/**
 * Holds the components for the reservations tab.
 * 
 * @author Hong and Kim
 * @version June 23, 2020
 */


public class ReservationsTab extends TabBase {

   private DefaultListModel<Reservation> reserveModel;

   /**
    * Instance of travel manager.
    */

   private ReservationManager reservationManager;
   /**
    * Attributes for GUI.
    */
   private JScrollPane scrollPane;
   private JList list;

   private JLabel lblNewLabel;

   private JLabel lblCode_Reserve;
   private JLabel lblFlight_Reserve;
   private JLabel lblAirline_Reserve;
   private JLabel lblCost_Reserve;
   private JLabel lblName_Reserve;
   private JLabel lblCitizenship_Reserve;
   private JLabel lblStatus_Reserve;

   private JTextField txtField_Code_Reserve;
   private JTextField txtField_Flight_Reserve;
   private JTextField txtField_Airline_Reserve;
   private JTextField txtField_Cost_Reserve;
   private JTextField txtField_Name_Reserve;
   private JTextField txtField_Citizen_Reserve;

   private JLabel lblSearch;

   private JLabel lblCode_Search;
   private JTextField txtfieldCode_Search;

   private JComboBox cboxStatus_Reserve;

   private JLabel lblAirline_Search;
   private JTextField txtfieldAirline_Search;

   private JLabel lblName_Search;
   private JTextField txtfieldName_Search;

   private JButton btnNewButton_1;

   private MyListSelectionListener listSelectionListener;

   private Reservation reserve;

   /**
    * Creates the components for reservations tab.
    * 
    * @param reservationManager - reservationManager Object.
    */
   public ReservationsTab(ReservationManager reservationManager) {
      this.reservationManager = reservationManager;
      this.panel.setLayout(null);

      JPanel northPanel = this.createNorthPanel();

      this.panel.add(northPanel);

      JPanel eastPanel = this.createCenterPanel();
      this.panel.add(eastPanel);

   }

   /**
    * Creates the north panel.
    * 
    * @return JPanel that goes in north.
    */
   private JPanel createNorthPanel() {
      JPanel panel = new JPanel();
      panel.setBounds(0, 0, 700, 47);
      panel.setLayout(null);

      JLabel lblReservations = new JLabel("Reservations");
      lblReservations.setBounds(107, 0, 475, 34);
      panel.add(lblReservations);
      lblReservations.setFont(new Font("Tahoma", Font.PLAIN, 24));
      lblReservations.setHorizontalAlignment(SwingConstants.CENTER);

      return panel;
   }

   /**
    * create the center panel.
    * 
    * @return panel that goes in center.
    */
   private JPanel createCenterPanel() {

      // set up panel size and location.
      JPanel panel = new JPanel();
      panel.setBounds(0, 49, 700, 452);
      panel.setLayout(null);

      reserveModel = new DefaultListModel<>();

      list = new JList(reserveModel);
      list.setBounds(36, 15, 378, 239);
      // User can only select one item at a time.
      list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

      // Wrap JList in JScrollPane so it is scrollable.
      scrollPane = new JScrollPane(list);
      scrollPane.setBounds(36, 15, 378, 239);

      this.listSelectionListener = new MyListSelectionListener();
      this.list.addListSelectionListener(this.listSelectionListener);

      panel.add(scrollPane);

      // set up JLabel name, font, size and added to panel.
      lblNewLabel = new JLabel("Reserve");
      lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
      lblNewLabel.setBounds(517, 14, 83, 16);
      panel.add(lblNewLabel);

      // set up JTextField location, length, size and added to panel.
      txtField_Code_Reserve = new JTextField();
      txtField_Code_Reserve.setEditable(false);
      txtField_Code_Reserve.setBounds(543, 54, 116, 22);
      panel.add(txtField_Code_Reserve);
      // in order to not access JTextField by user as a default.
      txtField_Code_Reserve.setColumns(10);

      // set up JTextField location, length, size and added to panel.
      txtField_Flight_Reserve = new JTextField();
      txtField_Flight_Reserve.setEditable(false);
      txtField_Flight_Reserve.setColumns(10);
      txtField_Flight_Reserve.setBounds(543, 76, 116, 22);
      panel.add(txtField_Flight_Reserve);

      // set up JTextField location, length, size and added to panel.
      txtField_Airline_Reserve = new JTextField();
      txtField_Airline_Reserve.setEditable(false);
      txtField_Airline_Reserve.setColumns(10);
      txtField_Airline_Reserve.setBounds(543, 99, 116, 22);
      panel.add(txtField_Airline_Reserve);

      // set up JTextField location, length, size and added to panel.
      txtField_Cost_Reserve = new JTextField();
      txtField_Cost_Reserve.setEditable(false);
      txtField_Cost_Reserve.setColumns(10);
      txtField_Cost_Reserve.setBounds(543, 123, 116, 22);
      panel.add(txtField_Cost_Reserve);

      // set up JTextField location, length, size and added to panel.
      txtField_Name_Reserve = new JTextField();
      txtField_Name_Reserve.setColumns(10);
      txtField_Name_Reserve.setBounds(543, 146, 116, 22);
      panel.add(txtField_Name_Reserve);

      // set up JTextField location, length, size and added to panel.
      txtField_Citizen_Reserve = new JTextField();
      txtField_Citizen_Reserve.setColumns(10);
      txtField_Citizen_Reserve.setBounds(543, 170, 116, 22);
      panel.add(txtField_Citizen_Reserve);

      // set up JLabel name, font, size and added to panel.
      lblCode_Reserve = new JLabel("Code:");
      lblCode_Reserve.setHorizontalAlignment(SwingConstants.RIGHT);
      lblCode_Reserve.setBounds(486, 57, 56, 16);
      panel.add(lblCode_Reserve);

      // set up JLabel name, font, size and added to panel.
      lblFlight_Reserve = new JLabel("Flight:");
      lblFlight_Reserve.setHorizontalAlignment(SwingConstants.RIGHT);
      lblFlight_Reserve.setBounds(486, 79, 56, 16);
      panel.add(lblFlight_Reserve);

      // set up JLabel name, font, size and added to panel.
      lblAirline_Reserve = new JLabel("Airline:");
      lblAirline_Reserve.setHorizontalAlignment(SwingConstants.RIGHT);
      lblAirline_Reserve.setBounds(486, 102, 56, 16);
      panel.add(lblAirline_Reserve);

      // set up JLabel name, font, size and added to panel.
      lblCost_Reserve = new JLabel("Cost:");
      lblCost_Reserve.setHorizontalAlignment(SwingConstants.RIGHT);
      lblCost_Reserve.setBounds(486, 126, 56, 16);
      panel.add(lblCost_Reserve);

      // set up JLabel name, font, size and added to panel.
      lblName_Reserve = new JLabel("Name:");
      lblName_Reserve.setHorizontalAlignment(SwingConstants.RIGHT);
      lblName_Reserve.setBounds(486, 150, 56, 16);
      panel.add(lblName_Reserve);

      // set up JLabel name, font, size and added to panel.
      lblCitizenship_Reserve = new JLabel("Citizenship:");
      lblCitizenship_Reserve.setHorizontalAlignment(SwingConstants.RIGHT);
      lblCitizenship_Reserve.setBounds(486, 173, 56, 16);
      panel.add(lblCitizenship_Reserve);

      // set up JLabel name, font, size and added to panel.
      lblStatus_Reserve = new JLabel("Status:");
      lblStatus_Reserve.setHorizontalAlignment(SwingConstants.RIGHT);
      lblStatus_Reserve.setBounds(462, 196, 80, 16);
      panel.add(lblStatus_Reserve);

      // set up JButton name, location, size and create ActionListener as inner class
      // for button.
      JButton updateButton_Reserve = new JButton("Update");
      updateButton_Reserve.setBounds(486, 228, 173, 25);
      panel.add(updateButton_Reserve);
      updateButton_Reserve.addActionListener(new UpdateListener());

      // create String type array using assets from manager and allocate it into the
      // JComboBox.
      String status_action[] = { "Active", "Inactive" };
      cboxStatus_Reserve = new JComboBox(status_action);

      cboxStatus_Reserve.setEditable(false);
      cboxStatus_Reserve.setBounds(543, 193, 116, 22);
      panel.add(cboxStatus_Reserve);

      // set up JLabel name, font, size and added to panel.
      lblSearch = new JLabel("Search");
      lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
      lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 21));
      lblSearch.setBounds(0, 301, 682, 35);
      panel.add(lblSearch);

      // set up JLabel name, font, size and added to panel.
      lblAirline_Search = new JLabel("Airline:");
      lblAirline_Search.setHorizontalAlignment(SwingConstants.RIGHT);
      lblAirline_Search.setBounds(10, 374, 39, 25);
      panel.add(lblAirline_Search);

      // set up JTextField location, length, size and added to panel.
      txtfieldAirline_Search = new JTextField();
      txtfieldAirline_Search.setBounds(50, 374, 632, 24);
      panel.add(txtfieldAirline_Search);
      txtfieldAirline_Search.setColumns(10);

      // set up JLabel name, font, size and added to panel.
      lblName_Search = new JLabel("Name:");
      lblName_Search.setHorizontalAlignment(SwingConstants.RIGHT);
      lblName_Search.setBounds(10, 399, 39, 25);
      panel.add(lblName_Search);

      // set up JTextField location, length, size and added to panel.
      txtfieldName_Search = new JTextField();
      txtfieldName_Search.setColumns(10);
      txtfieldName_Search.setBounds(50, 399, 632, 24);
      panel.add(txtfieldName_Search);

      // set up JLabel name, font, size and added to panel.
      lblCode_Search = new JLabel("Code:");
      lblCode_Search.setHorizontalAlignment(SwingConstants.RIGHT);
      lblCode_Search.setBounds(10, 349, 39, 25);
      panel.add(lblCode_Search);

      // set up JTextField location, length, size and added to panel.
      txtfieldCode_Search = new JTextField();
      txtfieldCode_Search.setColumns(10);
      txtfieldCode_Search.setBounds(50, 349, 632, 24);
      panel.add(txtfieldCode_Search);

      // set up JButton name, location, size and create ActionListener as inner class
      // for button.
      btnNewButton_1 = new JButton("Find Reservation");
      btnNewButton_1.setBounds(0, 427, 699, 25);
      panel.add(btnNewButton_1);
      btnNewButton_1.addActionListener(new FindReserveListener());

      return panel;
   }

   /**
    * Inner action listener class that listens for a find button to be clicked.
    * This is the inner class with the ActionListener required for GUI.
    * 
    * @author Hong and Kim
    * @version June 23, 2020
    */

   class FindReserveListener implements ActionListener {
      /**
       * Overrides actionPerformed() method.
       * 
       * @param e -ActionEvent e
       */
      @Override
      public void actionPerformed(ActionEvent e) {

         // clear all elements on the DefaultListModel in order to get new information.
         reserveModel.removeAllElements();
         for (int i = 0; i < reservationManager.getReservations().size(); i++) {

               if (!txtfieldName_Search.getText().equals("") && !txtfieldAirline_Search.getText().equals("")
                     && !txtfieldCode_Search.getText().equals("")) {
                  if (reservationManager.getReservations().get(i).getName().equals(txtfieldName_Search.getText())
                        && reservationManager.getReservations().get(i).getCode().equals(txtfieldCode_Search.getText())
                        && reservationManager.getReservations().get(i).getAirline()
                              .equals(txtfieldAirline_Search.getText())) {
                     reserveModel.addElement(reservationManager.getReservations().get(i));
                  }
               }

               else if (!txtfieldName_Search.getText().equals("") || !txtfieldAirline_Search.getText().equals("")
                     || !txtfieldCode_Search.getText().equals("")) {
                  if (reservationManager.getReservations().get(i).getName().equals(txtfieldName_Search.getText())
                        || reservationManager.getReservations().get(i).getCode().equals(txtfieldCode_Search.getText())
                        || reservationManager.getReservations().get(i).getAirline()
                              .equals(txtfieldAirline_Search.getText())) {
                     reserveModel.addElement(reservationManager.getReservations().get(i));
                  }

               }
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
       * Overrides valueChanged() method.
       * 
       * @param e -ListSelectionEvent e
       */
      @Override
      public void valueChanged(ListSelectionEvent e) {

         // avoid double selection automatically.
         if (!e.getValueIsAdjusting()) {
            // index of flightsList
            int selected = list.getSelectedIndex();
            // avoid out of bounds exception.
            if (selected > -1) {

               reserve = reservationManager.findReservationByCode(reserveModel.get(selected).getCode());

               txtField_Code_Reserve.setText(reserve.getCode());
               txtField_Flight_Reserve.setText(reserve.getFlightCode());
               txtField_Airline_Reserve.setText(reserve.getAirline());
               txtField_Cost_Reserve.setText(
                     (String.format("%s%.2f", "$ ", reserve.getCost())));
               txtField_Name_Reserve.setText(reserve.getName());
               txtField_Citizen_Reserve.setText(reserve.getCitizenship());
               if (reserve.isActive()) {
                  cboxStatus_Reserve.setSelectedIndex(0);
               } else {
                  cboxStatus_Reserve.setSelectedIndex(1);
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
   class UpdateListener implements ActionListener {

      /**
       * Overrides actionPerformed() method.
       * 
       * @param e - ActionEvent e
       */
      @Override
      public void actionPerformed(ActionEvent e) {
         // This code modify user's information that updated.

         if (reserve.getCode().equals(txtField_Code_Reserve.getText())) {

            if (cboxStatus_Reserve.getSelectedItem().equals("Inactive")) {

               reserve.setActive(false);

            } else {
               reserve.setActive(true);
            }
            
            try {
               reserve.setCitizenship(txtField_Citizen_Reserve.getText());
            } catch (InvalidCitizenshipException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
            try {
               reserve.setName(txtField_Name_Reserve.getText());
            } catch (InvalidNameException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
         //update information save to binary file.
         reservationManager.persist();
         
      }

}
}