package sait.frs.gui;



import java.awt.BorderLayout; 
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sait.frs.manager.FlightManager;
import sait.frs.manager.Manager;
import sait.frs.manager.ReservationManager;



/**
 * The main window (JFrame).
 * 
 * @author Hong and Kim
 * @version June 23, 2020
 */
public class MainWindow {
	// final constants
   private static final String TAB_FLIGHTS = "flights";
   private static final String TAB_RESERVATIONS = "reservations";
   
   /**
    * Holds the flight and reservation manager.
    */
    private ReservationManager reservationManager;
    private FlightManager flightManager;
    /**
     * The main JFrame window.
     */
   private JFrame frame;
   
   /**
    * Card layout to display tab content.
    */
   private CardLayout cardLayout;
   
   /**
    * North panel.
    */
   private JPanel northPanel;
   
   /**
    * Center panel.
    */
   private JPanel centerPanel;
   
   /**
    * Flights tab button.
    */
   private JButton flightsButton;
   
   /**
    * Reservations tab button.
    */
   private JButton reservationsButton;
   
   /**
    * Flights tab panel.
    */
   private TabBase flightsTab;
   
   /**
    * Reservations tab panel.
    */
   private TabBase reservationsTab;
   
   /**
    * Creates the JFrame and any components inside it.
    * @throws IOException - if the code inside main method doesn't work, it throws
    *                     IOException.
    */
   public MainWindow() throws IOException {
      this.reservationManager = new ReservationManager();
      this.flightManager = new FlightManager();
      
      this.frame = new JFrame("Ticket to Ride Travel");
      
      this.frame.setSize(700, 600);
      this.frame.setLayout(new BorderLayout());
      this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      this.northPanel = this.createNorthPanel();
      this.frame.add(northPanel, BorderLayout.NORTH);
      
      this.centerPanel = this.createCenterPanel();
      this.frame.add(centerPanel, BorderLayout.CENTER);
      this.frame.setLocationRelativeTo(null);
   
      this.frame.setResizable(false);
   }
   
   /**
    * Creates the north panel.
    * @return JPanel that goes in north.
    */
   private JPanel createNorthPanel() {
      JPanel panel = new JPanel();
      
      panel.setLayout(new BorderLayout());
      
      JLabel title = new JLabel("Ticket to Ride Travel", SwingConstants.CENTER);
      title.setFont(new Font("serif", Font.PLAIN, 29));
      panel.add(title, BorderLayout.NORTH);
      
      JPanel tabPanel = this.createTabPanel();
      panel.add(tabPanel, BorderLayout.SOUTH);
      
      return panel;
   }
   
   /**
    * Creates the center panel.
    * @return JPanel that goes in center.
    * @throws IOException - if the code inside main method doesn't work, it throws
    *                     IOException.
    */
   private JPanel createCenterPanel() throws IOException {
      JPanel panel = new JPanel();
      
      this.cardLayout = new CardLayout();
      
      this.flightsTab = new FlightsTab(flightManager,reservationManager);
      this.reservationsTab = new ReservationsTab(reservationManager);
      
      panel.setLayout(this.cardLayout);
      
      panel.add(this.flightsTab.getPanel(), TAB_FLIGHTS);
      panel.add(this.reservationsTab.getPanel(), TAB_RESERVATIONS);
      
      this.cardLayout.first(panel);
      
      return panel;
   }
   
   /**
    * Creates the tab buttons.
    * @return JPanel containing tab buttons.
    */
   private JPanel createTabPanel() {
      JPanel tabPanel = new JPanel();
      
      tabPanel.setLayout(new GridLayout(1, 2));
      
      this.flightsButton = new JButton("Flights");
      this.reservationsButton = new JButton("Reservations");
      
      TabButtonActionListener actionListener = new TabButtonActionListener();
      this.flightsButton.addActionListener(actionListener);
      this.reservationsButton.addActionListener(actionListener);
      
      tabPanel.add(flightsButton);
      tabPanel.add(reservationsButton);
      
      return tabPanel;
   }
   
   /**
    * Displays the JFrame window.
    */
   public void display() {
      this.frame.setVisible(true);
   }
   
   /**
    * Inner action listener class that listens for a tab button to be clicked.
    * 
    * @author Hong and Kim
    * @version June 23, 2020
    */
   class TabButtonActionListener implements ActionListener {
      /**
       * Overrides actionPerformed() method.
       * 
       * @param e - ActionEvent e
       */
      @Override
      public void actionPerformed(ActionEvent e) {
         if (e.getSource() == MainWindow.this.flightsButton) {
            MainWindow.this.cardLayout.show(MainWindow.this.centerPanel, TAB_FLIGHTS);
         } else if (e.getSource() == MainWindow.this.reservationsButton) {
            MainWindow.this.cardLayout.show(MainWindow.this.centerPanel, TAB_RESERVATIONS);
         }
      }
      
   }
}