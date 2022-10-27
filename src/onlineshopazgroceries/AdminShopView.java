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
public class AdminShopView extends JFrame implements Observer {

    private JButton searchButton = new JButton("Search");
    private JTextField searchTextField = new JTextField(15);
    private JLabel welcomeMessage = new JLabel("");

    private JPanel titlePanel = new JPanel();
    private JLabel storeTitle = new JLabel("WELCOME TO THE AZGROCERIES ONLINE STORE", SwingConstants.CENTER);

    private JPanel adminOptionsPanel = new JPanel();
    private JButton viewCartButton = new JButton("View Cart");
    private JButton viewOrderHistoryButton = new JButton("View Order History");
    private JButton checkoutButton = new JButton("Checkout");
    private JButton addItemOptionButton = new JButton("Add an Item");
    private JButton removeItemOptionButton = new JButton("Remove an Item");
    private JButton modifyItemQuantityOptionButton = new JButton("Modify Item Quantity");

    private JPanel addGroceryItemPanel = new JPanel();
    private JLabel productNameLabel = new JLabel("Enter Product Name: ", SwingConstants.CENTER);
    private JLabel productPriceLabel = new JLabel("Enter Product Price: ", SwingConstants.CENTER);
    private JLabel productQuantityLabel = new JLabel("Enter Product Quantity: ", SwingConstants.CENTER);

    private JPanel searchListPanel = new JPanel();

    private JTextField productNameTextField = new JTextField();
    private JTextField productPriceTextField = new JTextField();
    private JTextField productQuantityTextField = new JTextField();

    private JScrollPane groceryItemDisplayPanel = new JScrollPane();
    
    private JScrollPane searchedGroceryItemsDisplayPanel = new JScrollPane();
    private JLabel searchResultsLabel = new JLabel();

    private JButton addItemButton = new JButton("Add Item");
    private ArrayList<JButton> allAddItemButtons = new ArrayList<>();

    AdminShopView() {

        setTitle("AZGroceries Admin Store");

        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       

        getStoreTitle().setFont(new Font("Times New Roman", Font.BOLD, 30));
        getWelcomeMessage().setFont(new Font("Arial", Font.PLAIN, 20));
        this.DisplayAdminShopOptions();

    }

    public void DisplayAdminShopOptions() {
        this.getContentPane().removeAll();
        this.setSize(800, 740);
         this.setLocationRelativeTo(null);
         
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.getContentPane().add(scrollPane);
        
        getTitlePanel().setLayout(new GridLayout(3, 1));
        getTitlePanel().add(getStoreTitle());
        setWelcomeMessage(new JLabel(getWelcomeMessage().getText(), SwingConstants.CENTER));
        getTitlePanel().add(getWelcomeMessage());
        getTitlePanel().setBackground(Color.CYAN.brighter());

        JPanel searchPanel = new JPanel();
        searchPanel.add(getSearchTextField());
        searchPanel.add(getSearchButton());
        searchPanel.setBackground(Color.blue.brighter());
        //  searchPanel.setBackground(Color.blue);

        getTitlePanel().add(searchPanel);
        add(getTitlePanel(), BorderLayout.NORTH);

     //   getAdminOptionsPanel().setLayout(new GridLayout(1, 6));
//        JPanel viewCartButtonPanel = new JPanel();
//        viewCartButtonPanel.add(getViewCartButton());
        getAdminOptionsPanel().add(getViewCartButton());
        getAdminOptionsPanel().setBackground(Color.CYAN.brighter());

//        JPanel viewOrderHistoryButtonPanel = new JPanel();
//        viewOrderHistoryButtonPanel.add(getViewOrderHistoryButton());
        getAdminOptionsPanel().add(getViewOrderHistoryButton());

//        JPanel checkoutButtonPanel = new JPanel();
//        checkoutButtonPanel.add(getCheckoutButton());
        getAdminOptionsPanel().add(getCheckoutButton());

//        JPanel addItemOptionButtonPanel = new JPanel();
//        addItemOptionButtonPanel.add(getAddItemOptionButton());
        getAdminOptionsPanel().add(getAddItemOptionButton());

//        JPanel removeItemOptionButtonPanel = new JPanel();
//        removeItemOptionButtonPanel.add(getRemoveItemOptionButton());
        getAdminOptionsPanel().add(getRemoveItemOptionButton());

//        JPanel modifyItemQuantityOptionButtonPanel = new JPanel();
//        modifyItemQuantityOptionButtonPanel.add(getModifyItemQuantityOptionButton());
        getAdminOptionsPanel().add(getModifyItemQuantityOptionButton());

        add(getAdminOptionsPanel(), BorderLayout.CENTER);

        setGroceryItemDisplayPanel(getGroceryItemDisplayPanel());
        add(getGroceryItemDisplayPanel(), BorderLayout.SOUTH);

        setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void DisplaySearchResults() {
        this.getContentPane().removeAll();
        getTitlePanel().setBackground(Color.CYAN.brighter());
        this.setLocationRelativeTo(null);
        this.setSize(800, 740);
        getTitlePanel().removeAll();
        getTitlePanel().setLayout(new GridLayout(5, 1));
        getTitlePanel().add(getStoreTitle());
        
        getTitlePanel().add(getWelcomeMessage());
        
        JPanel searchPanel = new JPanel();
        searchPanel.add(getSearchTextField());
        searchPanel.add(getSearchButton());
        searchPanel.setBackground(Color.blue.brighter());
        getTitlePanel().add(searchPanel);
        getTitlePanel().add(getAdminOptionsPanel());
        getTitlePanel().add(getSearchResultsLabel());
       
        add(getTitlePanel(), BorderLayout.NORTH);
        
        add(getSearchedGroceryItemsDisplayPanel(), BorderLayout.CENTER);
        
        add(new JPanel(), BorderLayout.SOUTH);
        

        setVisible(true);
        this.revalidate();
        this.repaint();

    }

    public void adminAddItemView() {
        this.getContentPane().removeAll();
        getTitlePanel().add(getStoreTitle());
        add(getTitlePanel(), BorderLayout.NORTH);

        getAddGroceryItemPanel().setLayout(new GridLayout(3, 2));
        getAddGroceryItemPanel().add(getProductNameLabel());
        getAddGroceryItemPanel().add(getProductNameTextField());
        getAddGroceryItemPanel().add(getProductPriceLabel());
        getAddGroceryItemPanel().add(getProductPriceTextField());
        getAddGroceryItemPanel().add(getProductQuantityLabel());
        getAddGroceryItemPanel().add(getProductQuantityTextField());
        add(getAddGroceryItemPanel(), BorderLayout.CENTER);

        JPanel addItemButtonPanel = new JPanel();
        addItemButtonPanel.add(getAddItemButton());

        add(addItemButtonPanel, BorderLayout.SOUTH);

        setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void addActionListener(ActionListener actionListener) {
        getSearchButton().addActionListener(actionListener);
        getViewCartButton().addActionListener(actionListener);
        getViewCartButton().addActionListener(actionListener);

        getCheckoutButton().addActionListener(actionListener);
        getAddItemOptionButton().addActionListener(actionListener);

        getRemoveItemOptionButton().addActionListener(actionListener);

        getModifyItemQuantityOptionButton().addActionListener(actionListener);
        for (JButton button : getAllAddItemButtons()) {
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
                      JButton addItemOptionButton = new JButton("Add " + g.getProductName());
                     
                      
                      AddItemPanel.add(addItemOptionButton);
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
     * @return the addItemOptionButton
     */
    public JButton getAddItemOptionButton() {
        return addItemOptionButton;
    }

    /**
     * @param addItemOptionButton the addItemOptionButton to set
     */
    public void setAddItemOptionButton(JButton addItemOptionButton) {
        this.addItemOptionButton = addItemOptionButton;
    }

    /**
     * @return the removeItemOptionButton
     */
    public JButton getRemoveItemOptionButton() {
        return removeItemOptionButton;
    }

    /**
     * @param removeItemOptionButton the removeItemOptionButton to set
     */
    public void setRemoveItemOptionButton(JButton removeItemOptionButton) {
        this.removeItemOptionButton = removeItemOptionButton;
    }

    /**
     * @return the modifyItemQuantityOptionButton
     */
    public JButton getModifyItemQuantityOptionButton() {
        return modifyItemQuantityOptionButton;
    }

    /**
     * @param modifyItemQuantityOptionButton the modifyItemQuantityOptionButton
     * to set
     */
    public void setModifyItemQuantityOptionButton(JButton modifyItemQuantityOptionButton) {
        this.modifyItemQuantityOptionButton = modifyItemQuantityOptionButton;
    }

    /**
     * @return the productNameLabel
     */
    public JLabel getProductNameLabel() {
        return productNameLabel;
    }

    /**
     * @param productNameLabel the productNameLabel to set
     */
    public void setProductNameLabel(JLabel productNameLabel) {
        this.productNameLabel = productNameLabel;
    }

    /**
     * @return the productPriceLabel
     */
    public JLabel getProductPriceLabel() {
        return productPriceLabel;
    }

    /**
     * @param productPriceLabel the productPriceLabel to set
     */
    public void setProductPriceLabel(JLabel productPriceLabel) {
        this.productPriceLabel = productPriceLabel;
    }

    /**
     * @return the productQuantityLabel
     */
    public JLabel getProductQuantityLabel() {
        return productQuantityLabel;
    }

    /**
     * @param productQuantityLabel the productQuantityLabel to set
     */
    public void setProductQuantityLabel(JLabel productQuantityLabel) {
        this.productQuantityLabel = productQuantityLabel;
    }

    /**
     * @return the productNameTextField
     */
    public JTextField getProductNameTextField() {
        return productNameTextField;
    }

    /**
     * @param productNameTextField the productNameTextField to set
     */
    public void setProductNameTextField(JTextField productNameTextField) {
        this.productNameTextField = productNameTextField;
    }

    /**
     * @return the productPriceTextField
     */
    public JTextField getProductPriceTextField() {
        return productPriceTextField;
    }

    /**
     * @param productPriceTextField the productPriceTextField to set
     */
    public void setProductPriceTextField(JTextField productPriceTextField) {
        this.productPriceTextField = productPriceTextField;
    }

    /**
     * @return the productQuantityTextField
     */
    public JTextField getProductQuantityTextField() {
        return productQuantityTextField;
    }

    /**
     * @param productQuantityTextField the productQuantityTextField to set
     */
    public void setProductQuantityTextField(JTextField productQuantityTextField) {
        this.productQuantityTextField = productQuantityTextField;
    }

    /**
     * @return the addItemButton
     */
    public JButton getAddItemButton() {
        return addItemButton;
    }

    /**
     * @param addItemButton the addItemButton to set
     */
    public void setAddItemButton(JButton addItemButton) {
        this.addItemButton = addItemButton;
    }

    /**
     * @return the adminOptionsPanel
     */
    public JPanel getAdminOptionsPanel() {
        return adminOptionsPanel;
    }

    /**
     * @param adminOptionsPanel the adminOptionsPanel to set
     */
    public void setAdminOptionsPanel(JPanel adminOptionsPanel) {
        this.adminOptionsPanel = adminOptionsPanel;
    }

    /**
     * @return the addGroceryItemPanel
     */
    public JPanel getAddGroceryItemPanel() {
        return addGroceryItemPanel;
    }

    /**
     * @param addGroceryItemPanel the addGroceryItemPanel to set
     */
    public void setAddGroceryItemPanel(JPanel addGroceryItemPanel) {
        this.addGroceryItemPanel = addGroceryItemPanel;
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
     * @return the searchListPanel
     */
    public JPanel getSearchListPanel() {
        return searchListPanel;
    }

    /**
     * @param searchListPanel the searchListPanel to set
     */
    public void setSearchListPanel(JPanel searchListPanel) {
        this.searchListPanel = searchListPanel;
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
