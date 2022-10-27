/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author krist
 */
public class CustomerShopView extends JFrame implements Observer{
   
    
    private JLabel storeTitle = new JLabel("WELCOME TO THE AZGROCERIES ONLINE STORE", SwingConstants.CENTER);
    private JPanel titlePanel = new JPanel();
    private JLabel welcomeMessage = new JLabel();
    private JPanel customerOptionsPanel = new JPanel();
    private JButton searchButton = new JButton("Search");
    private JTextField searchTextField = new JTextField(15);
    private JButton viewCartButton = new JButton("View Cart");
    private JButton checkoutButton = new JButton("Checkout");
    private JButton viewOrderHistoryButton = new JButton("View Order History");
    private JLabel searchResultsLabel = new JLabel();
    
    private JScrollPane groceryItemDisplayPanel = new JScrollPane() ;
    private JScrollPane searchedGroceryItemsDisplayPanel = new JScrollPane();
    private ArrayList<JButton> allAddItemButtons = new ArrayList<>();
    
    CustomerShopView()
    {
        
        this.getContentPane().removeAll();
        this.setLocationRelativeTo(null);
        setTitle("AZGroceries Customer Store");
        this.setSize(800, 300);
        setTitle("AZGroceries Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getStoreTitle().setFont(new Font("Times New Roman", Font.BOLD, 30));
        getWelcomeMessage().setFont(new Font("Arial", Font.PLAIN ,20));
        
        this.DisplayCustomerShopOptions();
        
       
        
        
        
        
    }

    public void DisplayCustomerShopOptions()
    {
        getTitlePanel().setBackground(Color.CYAN.brighter());
        this.setLocationRelativeTo(null);
        this.setSize(800, 740);
         getTitlePanel().setLayout(new GridLayout(4, 1));
        getTitlePanel().add(getStoreTitle());
        
        setWelcomeMessage(new JLabel(getWelcomeMessage().getText(), SwingConstants.CENTER));
        getTitlePanel().add(getWelcomeMessage());
        
        JPanel searchPanel = new JPanel();
        searchPanel.add(getSearchTextField());
        searchPanel.add(getSearchButton());
        searchPanel.setBackground(Color.blue.brighter());
        getTitlePanel().add(searchPanel);
         getTitlePanel().add(getCustomerOptionsPanel());
        
        
        
        
        getCustomerOptionsPanel().setBackground(Color.CYAN.brighter());
        getCustomerOptionsPanel().add(getViewCartButton());
        getCustomerOptionsPanel().add(getViewOrderHistoryButton());
        getCustomerOptionsPanel().add(getCheckoutButton());
       
        add(getTitlePanel(), BorderLayout.NORTH);
        
        add(getGroceryItemDisplayPanel(), BorderLayout.CENTER);
        
        add(new JPanel(), BorderLayout.SOUTH);
        
        
         setVisible(true);
        this.revalidate();
        this.repaint();
    }
    
    public void DisplaySearchResults()
    {
        this.getContentPane().removeAll();
        getTitlePanel().setBackground(Color.CYAN.brighter());
        this.setLocationRelativeTo(null);
        this.setSize(800, 740);
        getTitlePanel().removeAll();
         getTitlePanel().setLayout(new GridLayout(5, 1));
        getTitlePanel().add(getStoreTitle());
        
        setWelcomeMessage(new JLabel(getWelcomeMessage().getText(), SwingConstants.CENTER));
        getTitlePanel().add(getWelcomeMessage());
        
        JPanel searchPanel = new JPanel();
        searchPanel.add(getSearchTextField());
        searchPanel.add(getSearchButton());
        searchPanel.setBackground(Color.blue.brighter());
        getTitlePanel().add(searchPanel);
        getTitlePanel().add(getCustomerOptionsPanel());
        
        
       
        add(getTitlePanel(), BorderLayout.NORTH);
        
        add(getSearchedGroceryItemsDisplayPanel(), BorderLayout.CENTER);
        
        add(new JPanel(), BorderLayout.SOUTH);
        
        
         setVisible(true);
        this.revalidate();
        this.repaint();
    }
    
    public void addActionListener(ActionListener actionListener) {
        
         getSearchButton().addActionListener(actionListener);
        getViewCartButton().addActionListener(actionListener);
        getViewCartButton().addActionListener(actionListener);

        getCheckoutButton().addActionListener(actionListener);
        for (JButton button : getAllAddItemButtons())
        {
            button.addActionListener(actionListener);
        }
        
    }
    
    @Override
    public void update(Observable o, Object arg) {
         ShopData data = (ShopData) arg;
         
         if(data.isUserSearching())
         {
             JPanel SearchedGroceryItemsDisplayPanel = new JPanel();
             int numberOfRows = (int)data.getSearchedItems().size()/3+1;
                SearchedGroceryItemsDisplayPanel.setLayout(new GridLayout(numberOfRows, 3));
             for(GroceryItems g : data.getSearchedItems())
             {
                 JPanel SearchedGroceryItemContainer = new JPanel();
                      SearchedGroceryItemContainer.setLayout(new GridLayout(5, 1));
                      
                      JLabel productName = new JLabel(g.getProductName());
                      productName.setFont(new Font("Arial", Font.BOLD, 15));
                      SearchedGroceryItemContainer.add(productName);
                      
                      JLabel productCategory = new JLabel("Category: "+ g.getCategory());
                      SearchedGroceryItemContainer.add(productCategory);
                      
                      JLabel productPrice = new JLabel("Price: $"+g.getPrice());
                      SearchedGroceryItemContainer.add(productPrice);
                      
                      JLabel productQuantityAvailable = new JLabel("Quantity Avaialble: "+g.getQuantityAvailable());
                      SearchedGroceryItemContainer.add(productQuantityAvailable);
                      
                      JPanel AddItemPanel = new JPanel();
                      AddItemPanel.add(new JButton("-"));
                      AddItemPanel.add(new JTextField("0", 2));
                      AddItemPanel.add(new JButton("+"));
                      JButton addItemButton = new JButton("Add " + g.getProductName());
                     
                      
                      AddItemPanel.add(addItemButton);
                      SearchedGroceryItemContainer.add(AddItemPanel);
                      SearchedGroceryItemContainer.setSize(20, 30);
                      SearchedGroceryItemsDisplayPanel.add(SearchedGroceryItemContainer);
                 
             }
             getSearchResultsLabel().setText("Search results for: \"" + data.getSearchQuery() + "\""); 
             this.getSearchedGroceryItemsDisplayPanel().setViewportView(SearchedGroceryItemsDisplayPanel);
             
             DisplaySearchResults();
         }
         
    }
    
    

    /**
     * @return the welcomeMessage
     */
    public JLabel getWelcomeMessage() {
        return welcomeMessage;
    }

    /**
     * @param welcomeMessage the welcomeMessage to set
     */
    public void setWelcomeMessage(JLabel welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    /**
     * @return the searchButton
     */
    public JButton getSearchButton() {
        return searchButton;
    }

    /**
     * @param searchButton the searchButton to set
     */
    public void setSearchButton(JButton searchButton) {
        this.searchButton = searchButton;
    }

    /**
     * @return the searchTextField
     */
    public JTextField getSearchTextField() {
        return searchTextField;
    }

    /**
     * @param searchTextField the searchTextField to set
     */
    public void setSearchTextField(JTextField searchTextField) {
        this.searchTextField = searchTextField;
    }

    /**
     * @return the viewCartButton
     */
    public JButton getViewCartButton() {
        return viewCartButton;
    }

    /**
     * @param viewCartButton the viewCartButton to set
     */
    public void setViewCartButton(JButton viewCartButton) {
        this.viewCartButton = viewCartButton;
    }

    /**
     * @return the checkoutButton
     */
    public JButton getCheckoutButton() {
        return checkoutButton;
    }

    /**
     * @param checkoutButton the checkoutButton to set
     */
    public void setCheckoutButton(JButton checkoutButton) {
        this.checkoutButton = checkoutButton;
    }

    /**
     * @return the viewOrderHistoryButton
     */
    public JButton getViewOrderHistoryButton() {
        return viewOrderHistoryButton;
    }

    /**
     * @param viewOrderHistoryButton the viewOrderHistoryButton to set
     */
    public void setViewOrderHistoryButton(JButton viewOrderHistoryButton) {
        this.viewOrderHistoryButton = viewOrderHistoryButton;
    }

    /**
     * @return the titlePanel
     */
    public JPanel getTitlePanel() {
        return titlePanel;
    }

    /**
     * @param titlePanel the titlePanel to set
     */
    public void setTitlePanel(JPanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    /**
     * @return the storeTitle
     */
    public JLabel getStoreTitle() {
        return storeTitle;
    }

    /**
     * @param storeTitle the storeTitle to set
     */
    public void setStoreTitle(JLabel storeTitle) {
        this.storeTitle = storeTitle;
    }

    /**
     * @return the customerOptionsPanel
     */
    public JPanel getCustomerOptionsPanel() {
        return customerOptionsPanel;
    }

    /**
     * @param customerOptionsPanel the customerOptionsPanel to set
     */
    public void setCustomerOptionsPanel(JPanel customerOptionsPanel) {
        this.customerOptionsPanel = customerOptionsPanel;
    }


    /**
     * @return the allAddItemButtons
     */
    public ArrayList<JButton> getAllAddItemButtons() {
        return allAddItemButtons;
    }

    /**
     * @param allAddItemButtons the allAddItemButtons to set
     */
    public void setAllAddItemButtons(ArrayList<JButton> allAddItemButtons) {
        this.allAddItemButtons = allAddItemButtons;
    }

    /**
     * @return the groceryItemDisplayPanel
     */
    public JScrollPane getGroceryItemDisplayPanel() {
        return groceryItemDisplayPanel;
    }

    /**
     * @param groceryItemDisplayPanel the groceryItemDisplayPanel to set
     */
    public void setGroceryItemDisplayPanel(JScrollPane groceryItemDisplayPanel) {
        this.groceryItemDisplayPanel = groceryItemDisplayPanel;
    }

    /**
     * @return the searchedGroceryItemsDisplayPanel
     */
    public JScrollPane getSearchedGroceryItemsDisplayPanel() {
        return searchedGroceryItemsDisplayPanel;
    }

    /**
     * @param searchedGroceryItemsDisplayPanel the searchedGroceryItemsDisplayPanel to set
     */
    public void setSearchedGroceryItemsDisplayPanel(JScrollPane searchedGroceryItemsDisplayPanel) {
        this.searchedGroceryItemsDisplayPanel = searchedGroceryItemsDisplayPanel;
    }

    /**
     * @return the searchResultsLabel
     */
    public JLabel getSearchResultsLabel() {
        return searchResultsLabel;
    }

    /**
     * @param searchResultsLabel the searchResultsLabel to set
     */
    public void setSearchResultsLabel(JLabel searchResultsLabel) {
        this.searchResultsLabel = searchResultsLabel;
    }

}
