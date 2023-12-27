package es.ull.patrones.gui;

import es.ull.patrones.controller.VintedApiController;
import es.ull.patrones.model.VintedApiModel;
import es.ull.patrones.view.Observer;
import es.ull.patrones.view.PrintObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MVCFrame extends JFrame {
    // Panels
    private JPanel filtersPanel; // Panel to place the filter fields
    private JPanel buttonsPanel; // Panel to place the buttons
    private JPanel logoPanel;

    // Labels
    private JLabel minNoOfFavouritesLabel;
    private JLabel maxNumberOfFavouritesLabel;
    private JLabel minItemCountLabel;
    private JLabel maxItemCountLabel;
    // private JLabel isVisibleInListings;
    // private JLabel requiresAutenticityCheck;
    // private JLabel isLuxuryCheck;
    private JLabel vintedLogo;

    // Input fields
    private JTextField minNumberOfFavourites;
    private JTextField maxNumberOfFavourites;
    private JTextField minNumberOfItems;
    private JTextField maxNumberOfItems;

    // Buttons
    private JButton searchButton;

    // Constructor method
    public MVCFrame() {
        // Configuration of the main frame
        this.setSize(400, 300);
        this.getContentPane().setLayout(new BorderLayout());
        this.setBackground(new Color(190, 245, 255));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Vinted Scorecard");

        // Icon of the main frame
        ImageIcon vintedIcon = new javax.swing.ImageIcon("images/vinted_icon.png");
        Image vintedIconIcon = vintedIcon.getImage();
        this.setIconImage(vintedIconIcon);

        // Configure panel stuff
        filtersPanel = new JPanel();
        filtersPanel.setBackground(new Color(190, 245, 255));
        filtersPanel.setLayout(new GridLayout(7, 2));
        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(190, 245, 255));
        logoPanel = new JPanel();
        logoPanel.setSize(150, 100);
        logoPanel.setLayout(new BorderLayout());
        logoPanel.setBackground(new Color(190, 245, 255));

        // Add logo to the label that will be on the top of the window
        ImageIcon logo = new ImageIcon("images/Vinted_Logo.png");
        Image originalIcon = logo.getImage();
        Image escalatedLogo = originalIcon.getScaledInstance(75, 25, Image.SCALE_SMOOTH);
        logo = new ImageIcon(escalatedLogo);
        vintedLogo = new JLabel(logo);
        logoPanel.add(vintedLogo, BorderLayout.CENTER);

        // Search labels
        minNoOfFavouritesLabel = new JLabel("No. of favourites (min - max): ");
        // maxNumberOfFavouritesLabel;
        minItemCountLabel = new JLabel("No. of items (min - max): ");
        // maxItemCountLabel;

        // Search text fields
        minNumberOfFavourites = new JTextField(10);
        maxNumberOfFavourites = new JTextField(10);
        minNumberOfItems = new JTextField(10);
        maxNumberOfItems = new JTextField(10);

        // Search button
        // When pressed, the program will use the Vinted API
        searchButton = new JButton("Search items");
        // An action listener should be defined here
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clic event
                onSearchButtonClick();
            }
        });
        // Add stuff to the panels and main frame
        // Filters panel
        filtersPanel.add(minNoOfFavouritesLabel);
        filtersPanel.add(new JLabel());
        filtersPanel.add(minNumberOfFavourites);
        filtersPanel.add(maxNumberOfFavourites);
        filtersPanel.add(minItemCountLabel);
        filtersPanel.add(minNumberOfItems);
        filtersPanel.add(maxNumberOfItems);
        // Buttons panel
        buttonsPanel.add(searchButton);
        // Main frame
        this.add(logoPanel, BorderLayout.NORTH);
        this.add(filtersPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.SOUTH);

        // We assure the main frame is visible
        this.setVisible(true);
    }
    // Method to manage when the button is pressed
    Observer printObserver;
    private void onSearchButtonClick() {
        /**
        VintedApiModel model = new VintedApiModel();
        VintedApiController vintedApiSubject = new VintedApiController(model);
        if(printObserver!=null)
            printObserver.removepreviousFrame();
        printObserver = new PrintObserver();
        // Input fields values
        String keywordValue = keyword.getText();
        int noOfPageValue = (int) noOfpage.getSelectedItem();
        String countryValue = (String) country.getSelectedItem();
        int minPriceValue = Integer.parseInt(minPrice.getText());
        int maxPriceValue = Integer.parseInt(maxPrice.getText());
        int noOfFavouritesValue = (int) noOfFavourites.getSelectedItem();
        if (minPriceValue > maxPriceValue) {
            JOptionPane.showMessageDialog(this,"ERROR DE PRECIO");
        }
        vintedApiSubject.addObserver(printObserver);
        vintedApiSubject.fetchData(noOfPageValue, keywordValue, minPriceValue, maxPriceValue, noOfFavouritesValue);
        // We search the items with the search criteria
        String results = performSearch(keywordValue, noOfPageValue, countryValue, minPriceValue, maxPriceValue, noOfFavouritesValue);
        */
    }

    // Method to perform search with parameters
    private String performSearch(String keyword, int noOfPage, String country, int minPrice, int maxPrice, int noOfFavourites) {
        return String.format("Búsqueda con:\nKeyword: %s\nNo. of Page: %d\nCountry: %s\nMin. Price: %s\nMax. Price: %s\nNo. of Favourites: %d",
                keyword, noOfPage, country, minPrice, maxPrice, noOfFavourites);
    }
}