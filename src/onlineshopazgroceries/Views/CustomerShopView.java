/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
import onlineshopazgroceries.Cart;
import onlineshopazgroceries.Controllers.CartController;
import onlineshopazgroceries.Controllers.CheckoutServiceController;
import onlineshopazgroceries.Controllers.CustomerShopController;
import onlineshopazgroceries.Controllers.OrderHistoryController;
import onlineshopazgroceries.GroceryItems;
import onlineshopazgroceries.Models.CartModel;
import onlineshopazgroceries.Models.CheckoutServiceModel;
import onlineshopazgroceries.Models.CustomerShopModel;
import onlineshopazgroceries.Models.OrderHistoryModel;
import onlineshopazgroceries.ShopData;

/**
 *
 * @author krist
 */
public class CustomerShopView extends JFrame implements Observer {

    ShopData data;
    private JLabel storeTitle = new JLabel("WELCOME TO THE AZGROCERIES ONLINE STORE", SwingConstants.CENTER);
    private JPanel titlePanel = new JPanel();
    private JLabel welcomeMessage = new JLabel();
    private JPanel customerOptionsPanel = new JPanel();
    private JButton searchButton = new JButton("Search");
    private JTextField searchTextField = new JTextField(15);
    private JButton viewCartButton = new JButton("View Cart");
    private JButton checkoutButton = new JButton("Checkout");
    private JButton viewOrderHistoryButton = new JButton("View Order History");
    private JLabel searchResultsLabel = new JLabel("");

    private JScrollPane groceryItemDisplayPanel = new JScrollPane();
    private JScrollPane searchedGroceryItemsDisplayPanel = new JScrollPane();
    private ArrayList<JButton> allAddItemButtons = new ArrayList<>();
    private ArrayList<JButton> allPlusButtons = new ArrayList<>();
    private ArrayList<JButton> allMinusButtons = new ArrayList<>();
    private ArrayList<JTextField> allQuantityTextFields = new ArrayList<>();
    private ArrayList<JButton> allAddItemButtonsAfterSearch = new ArrayList<>();
    private ArrayList<JButton> allPlusButtonsAfterSearch = new ArrayList<>();
    private ArrayList<JButton> allMinusButtonsAfterSearch = new ArrayList<>();
    private ArrayList<JTextField> allQuantityTextFieldsAfterSearch = new ArrayList<>();

    private JPanel cartItemAddedPanel = new JPanel();
    private JLabel cartAdditionSuccessMessage = new JLabel("", SwingConstants.CENTER);

    private JButton returnBackToMainMenuButton = new JButton("Return Back To Main Menu");

    public CustomerShopView() {

        this.getContentPane().removeAll();
        this.setSize(800, 300);
        setTitle("AZGroceries Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getStoreTitle().setFont(new Font("Times New Roman", Font.BOLD, 30));
        getWelcomeMessage().setFont(new Font("Arial", Font.PLAIN, 20));

        this.DisplayCustomerShopOptions();

    }

    public void DisplayCustomerShopOptions() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        double halfWidth = size.getWidth() / 2;
        int halfWidthInteger = (int) halfWidth;
        this.setLocation(halfWidthInteger - 500, 0);

        this.setSize(1000, 740);

        this.getContentPane().removeAll();
        getTitlePanel().setBackground(Color.CYAN.brighter());
        getTitlePanel().removeAll();
        getTitlePanel().setLayout(new GridLayout(4, 1));
        getTitlePanel().add(getStoreTitle());

        setWelcomeMessage(new JLabel(getWelcomeMessage().getText(), SwingConstants.CENTER));
        getTitlePanel().add(getWelcomeMessage());

        JPanel searchPanel = new JPanel();
        searchPanel.add(getSearchTextField());
        searchPanel.add(getSearchButton());
        searchPanel.add(getSearchResultsLabel());
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

    public void DisplaySearchResults() {
        
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        double halfWidth = size.getWidth() / 2;
        int halfWidthInteger = (int) halfWidth;
        this.setLocation(halfWidthInteger - 500, 0);

        this.getContentPane().removeAll();
        this.setSize(1000, 740);
        
        getTitlePanel().setBackground(Color.CYAN.brighter());

        
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

        JPanel searchResultsPanel = new JPanel();
        searchResultsPanel.add(getSearchResultsLabel());
        searchResultsPanel.add(getReturnBackToMainMenuButton());
        getTitlePanel().add(searchResultsPanel);

        add(getTitlePanel(), BorderLayout.NORTH);

        add(getSearchedGroceryItemsDisplayPanel(), BorderLayout.CENTER);

        add(new JPanel(), BorderLayout.SOUTH);

        setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void displayItemAddedToCartSucessfully() {

        this.getContentPane().removeAll();
        this.setSize(800, 275);
        this.setLocationRelativeTo(null);
        getTitlePanel().removeAll();
        getTitlePanel().setLayout(new GridLayout(2, 1));
        getTitlePanel().add(getStoreTitle());

        JPanel messagePanel = new JPanel();
        messagePanel.add(getCartAdditionSuccessMessage());

        getTitlePanel().add(messagePanel);
        add(getTitlePanel(), BorderLayout.NORTH);

        add(getCartItemAddedPanel(), BorderLayout.CENTER);

        JPanel SouthPanel = new JPanel();

        JPanel backToMainMenuButtonPanel = new JPanel();

        backToMainMenuButtonPanel.add(getReturnBackToMainMenuButton());

        SouthPanel.add(backToMainMenuButtonPanel);
        add(SouthPanel, BorderLayout.SOUTH);

        setVisible(true);
        this.revalidate();
        this.repaint();

    }

    public void addActionListener(ActionListener actionListener) {

        if (this.data.isNeverPressedMainMenuButton()) {
            getSearchButton().addActionListener(actionListener);
            getViewCartButton().addActionListener(actionListener);
            getViewOrderHistoryButton().addActionListener(actionListener);

            getCheckoutButton().addActionListener(actionListener);
            getReturnBackToMainMenuButton().addActionListener(actionListener);
        }

        for (JButton addItemButton : getAllAddItemButtons()) {
            addItemButton.addActionListener(actionListener);
        }

        for (JButton minusButton : getAllMinusButtons()) {
            minusButton.addActionListener(actionListener);
        }

        for (JButton plusButton : getAllPlusButtons()) {
            plusButton.addActionListener(actionListener);
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        ShopData data = (ShopData) arg;

        if (data.isUserSearching() && data.isAttemptingSearch()) {
            getSearchResultsLabel().setForeground(Color.black);
            JPanel SearchedGroceryItemsDisplayPanel = new JPanel();
            int numberOfRows = (int) data.getSearchedItems().size() / 3 + 1;
            SearchedGroceryItemsDisplayPanel.setLayout(new GridLayout(numberOfRows, 3));
            for (GroceryItems g : data.getSearchedItems()) {
                JPanel SearchedGroceryItemContainer = new JPanel();
                SearchedGroceryItemContainer.setLayout(new GridLayout(5, 1));

                JLabel productName = new JLabel(g.getProductName());
                productName.setFont(new Font("Arial", Font.BOLD, 15));
                SearchedGroceryItemContainer.add(productName);

                JLabel productCategory = new JLabel("Category: " + g.getCategory());
                SearchedGroceryItemContainer.add(productCategory);

                JLabel productPrice = new JLabel("Price: $" + g.getPrice());
                SearchedGroceryItemContainer.add(productPrice);

                JLabel productQuantityAvailable = new JLabel("Quantity Avaialble: " + g.getQuantityAvailable());
                SearchedGroceryItemContainer.add(productQuantityAvailable);


                int getIndex = data.getListOfGroceries().getGroceries().indexOf(g);

                
                getAllMinusButtonsAfterSearch().add(getAllMinusButtons().get(getIndex));
                getAllQuantityTextFieldsAfterSearch().add(getAllQuantityTextFields().get(getIndex));
                getAllPlusButtonsAfterSearch().add(getAllPlusButtons().get(getIndex));
                getAllAddItemButtonsAfterSearch().add(getAllAddItemButtons().get(getIndex));
                
                 JPanel AddItemPanel = new JPanel();

                
                AddItemPanel.add(getAllMinusButtonsAfterSearch().get(data.getSearchedItems().indexOf(g)));
                AddItemPanel.add(getAllQuantityTextFieldsAfterSearch().get(data.getSearchedItems().indexOf(g)));
                AddItemPanel.add(getAllPlusButtonsAfterSearch().get(data.getSearchedItems().indexOf(g)));
                
                JButton addGroceryItemOptionButton = getAllAddItemButtonsAfterSearch().get(getIndex);

                AddItemPanel.add(addGroceryItemOptionButton);
                SearchedGroceryItemContainer.add(AddItemPanel);
                SearchedGroceryItemsDisplayPanel.add(SearchedGroceryItemContainer);

            }
            getSearchResultsLabel().setText("Search results for: \"" + data.getSearchQuery() + "\"");
            this.getSearchedGroceryItemsDisplayPanel().setViewportView(SearchedGroceryItemsDisplayPanel);

            DisplaySearchResults();
        }
        if (!data.isUserSearching() && data.isAttemptingSearch()) {
            getSearchResultsLabel().setText(data.getReasonSearchFailed());
            getSearchResultsLabel().setForeground(Color.white);
            DisplayCustomerShopOptions();
        }
        if(data.isMainMenuRequested())
        {
            data.setMainMenuRequested(false);
            JPanel GroceryItemDisplayPanel = new JPanel();
            ArrayList<JButton> addButtons = new ArrayList<>();
            ArrayList<JButton> plusButtons = new ArrayList<>();
            ArrayList<JButton> minusButtons = new ArrayList<>();
            ArrayList<JTextField> quantityTextFields = new ArrayList<>();

            int numberOfRowsGroceryPanel = (int) data.getListOfGroceries().getGroceries().size() / 3 + 1;
            GroceryItemDisplayPanel.setLayout(new GridLayout(numberOfRowsGroceryPanel, 3));
            for (GroceryItems g : data.getListOfGroceries().getGroceries()) {
                JPanel GroceryItemContainer = new JPanel();
                GroceryItemContainer.setLayout(new GridLayout(5, 1));

                JLabel productName = new JLabel(g.getProductName());
                productName.setFont(new Font("Arial", Font.BOLD, 15));
                GroceryItemContainer.add(productName);

                JLabel productCategory = new JLabel("Category: " + g.getCategory());
                GroceryItemContainer.add(productCategory);

                JLabel productPrice = new JLabel("Price: $" + g.getPrice());
                GroceryItemContainer.add(productPrice);

                JLabel productQuantityAvailable = new JLabel("Quantity Avaialble: " + g.getQuantityAvailable());
                GroceryItemContainer.add(productQuantityAvailable);

                JPanel AddItemPanel = new JPanel();
                JButton minusButton = new JButton("-");
                JButton plusButton = new JButton("+");
                JTextField quantityTextField = new JTextField("0", 2);

                AddItemPanel.add(minusButton);

                AddItemPanel.add(quantityTextField);
                AddItemPanel.add(plusButton);
                JButton addItemToCartButton = new JButton("Add " + g.getProductName());

                addButtons.add(addItemToCartButton);
                plusButtons.add(plusButton);
                minusButtons.add(minusButton);
                quantityTextFields.add(quantityTextField);

                AddItemPanel.add(addItemToCartButton);
                GroceryItemContainer.add(AddItemPanel);

                GroceryItemDisplayPanel.add(GroceryItemContainer);
            }
             CustomerShopView view = data.getCustomerShopview();
              view.data = data;
                    view.getWelcomeMessage().setText(data.getUserAccount().getUsername() + ", you are ready to shop!");
                    view.setAllAddItemButtons(addButtons);
                    view.setAllMinusButtons(minusButtons);
                    view.setAllPlusButtons(plusButtons);
                    view.setAllQuantityTextFields(quantityTextFields);
                    view.getGroceryItemDisplayPanel().setViewportView(GroceryItemDisplayPanel);
                    getSearchResultsLabel().setText("");
                    if(data.getUserAccount().getUserCart().getItemsAdded().isEmpty())
                    {
                        view.getCheckoutButton().setEnabled(false);
                    }

                    CustomerShopModel model = data.getCustomerShopModel();
                    model.data = data;
                    CustomerShopController controller = new CustomerShopController(view, model);

                    model.addObserver(view);
                    
                    view.DisplayCustomerShopOptions();

        }
        if (data.isQuantityTextFieldUpdated()) {
            this.getAllQuantityTextFields().get(data.getNumberOfUpdatedQuantityTextField()).setText("" + data.getUpdatedQuantityTextFieldValue());
        }
        if (data.isItemAddedToCart()) {
            getCartAdditionSuccessMessage().setText("Item has been added sucessfully");
            getCartItemAddedPanel().setLayout(new GridLayout(3, 1));
            getCartItemAddedPanel().add(new JLabel("Product name: " + data.getGroceryItemRequestedForCart().getProductName(), SwingConstants.CENTER));
            getCartItemAddedPanel().add(new JLabel("Product price: " + data.getGroceryItemRequestedForCart().getPrice(), SwingConstants.CENTER));
            getCartItemAddedPanel().add(new JLabel("Product quantity purchased: " + data.getQuantityOfProductAddedToCart(), SwingConstants.CENTER));

            displayItemAddedToCartSucessfully();

        }
        if (data.isViewCartRequested()) {

            if (data.getUserAccount().getUserCart().getItemsAdded().isEmpty()) {
                this.setVisible(false);
                CartView view = new CartView();
                view.getViewingCartMessage().setText(data.getUserAccount().getUsername() + ", your cart is empty");
                CartModel model = new CartModel();
                model.data = data;
                CartController controller = new CartController(view, model);

                model.addObserver(view);
            } else {

                JPanel cartPanel = new JPanel();
                ArrayList<JButton> removeProductButtons = new ArrayList<>();
                ArrayList<JButton> plusButtons = new ArrayList<>();
                ArrayList<JButton> minusButtons = new ArrayList<>();
                ArrayList<JButton> saveButtons = new ArrayList<>();
                ArrayList<JLabel> currentPriceForQuantityLabels = new ArrayList<>();
                ArrayList<JLabel> currentCartPurchaseQuantityLabels = new ArrayList<>();
                ArrayList<JTextField> quantityToPurchaseTextFields = new ArrayList<>();
                int numberOfRows = data.getUserAccount().getUserCart().getItemsAdded().keySet().size();
                cartPanel.setLayout(new GridLayout(numberOfRows, 1));
                for (GroceryItems g : data.getUserAccount().getUserCart().getItemsAdded().keySet()) {

                    JPanel cartPanelContainer = new JPanel();
                    cartPanelContainer.setLayout(new GridLayout(4, 1));

                    cartPanelContainer.add(new JLabel("Product Name : " + g.getProductName(), SwingConstants.LEFT));
                    JPanel cartPanelProductPriceAndTotalContainer = new JPanel();
                    cartPanelProductPriceAndTotalContainer.setLayout(new GridLayout(1, 2));
                    cartPanelProductPriceAndTotalContainer.add(new JLabel("Unit Price of Product: $" + g.getPrice(), SwingConstants.LEFT));
                    int quantityOfProductPurchased = data.getUserAccount().getUserCart().getItemsAdded().get(g);

                    JLabel priceForQuantityLabel = new JLabel("Price for " + quantityOfProductPurchased + ": $" + quantityOfProductPurchased * g.getPrice(), SwingConstants.LEFT);
                    cartPanelProductPriceAndTotalContainer.add(priceForQuantityLabel);

                    cartPanelContainer.add(cartPanelProductPriceAndTotalContainer);
                    JLabel cartPurchaseQuantityLabel = new JLabel("Quantity of Product added to Cart: " + quantityOfProductPurchased, SwingConstants.LEFT);
                    cartPanelContainer.add(cartPurchaseQuantityLabel);

                    JPanel cartOptionsPanel = new JPanel();
                    JButton removeProductButton = new JButton("Remove " + g.getProductName());
                    JButton minusButton = new JButton("-");
                    JButton plusButton = new JButton("+");
                    JTextField quantityTextField = new JTextField("" + quantityOfProductPurchased, 3);
                    JButton saveButton = new JButton("Save");

                    cartOptionsPanel.add(removeProductButton);
                    cartOptionsPanel.add(new JLabel(" "));
                    cartOptionsPanel.add(minusButton);
                    cartOptionsPanel.add(quantityTextField);
                    cartOptionsPanel.add(plusButton);
                    cartOptionsPanel.add(saveButton);

                    currentCartPurchaseQuantityLabels.add(cartPurchaseQuantityLabel);
                    removeProductButtons.add(removeProductButton);
                    minusButtons.add(minusButton);
                    plusButtons.add(plusButton);
                    saveButtons.add(saveButton);
                    quantityToPurchaseTextFields.add(quantityTextField);
                    currentPriceForQuantityLabels.add(priceForQuantityLabel);

                    cartPanelContainer.add(cartOptionsPanel);
                    cartPanel.add(cartPanelContainer);

                }
                this.setVisible(false);
                CartView view = new CartView();
                view.getViewingCartMessage().setText("Viewing " + data.getUserAccount().getUsername() + "'s cart");
                view.getCartTotalLabel().setText("Cart Total: $" + data.getUserAccount().getUserCart().getTotal());
                view.setAllRemoveProductButtons(removeProductButtons);
                view.setAllUpdatedCartPurchaseQuantityLabels(currentCartPurchaseQuantityLabels);
                view.setAllUpdatedCartPurchasePriceForQuantityLabels(currentPriceForQuantityLabels);
                view.setAllMinusButtons(minusButtons);
                view.setAllPlusButtons(plusButtons);
                view.setAllSaveButtons(saveButtons);
                view.setAllQuantityToPurchaseTextFields(quantityToPurchaseTextFields);
                view.getCartItemPanel().setViewportView(cartPanel);

                CartModel model = new CartModel();
                model.data = data;
                CartController controller = new CartController(view, model);

                model.addObserver(view);
            }

        }
        if (!data.getUserAccount().getUserCart().getItemsAdded().isEmpty()) {
            this.getCheckoutButton().setEnabled(true);

        }
        if (data.isRequestingToCheckout()) {
            this.setVisible(false);
            CheckoutServiceView view = new CheckoutServiceView();
            view.getCartTotalLabel().setText("Cart Total: $" + data.getUserAccount().getUserCart().getTotal());
            CheckoutServiceModel model = new CheckoutServiceModel();

            model.data = data;
            CheckoutServiceController controller = new CheckoutServiceController(view, model);

            model.addObserver(view);
        }
        if (data.isViewOrderHistoryRequested()) {
            if (data.getUserAccount().getOrderHistory().getOrderHistory().isEmpty()) {
                this.setVisible(false);
                OrderHistoryView view = new OrderHistoryView();
                view.getViewingOrderHistoryMessage().setText(data.getUserAccount().getUsername() + ", your order history is empty");
                OrderHistoryModel model = new OrderHistoryModel();
                model.data = data;
                OrderHistoryController controller = new OrderHistoryController(view, model);

                model.addObserver(view);
            } else {
                JPanel orderHistoryPanel = new JPanel();
                int numberOfRows = data.getUserAccount().getOrderHistory().getOrderHistory().size(); // number of carts customer had ;
                orderHistoryPanel.setLayout(new GridLayout(numberOfRows, 1));
                for (Cart c : data.getUserAccount().getOrderHistory().getOrderHistory().keySet()) {

                    JPanel cartPanel = new JPanel();
                   int numberOfGroceries = c.getItemsAdded().size(); // number of carts customer had ;

                    cartPanel.setLayout(new GridLayout(numberOfGroceries, 1));
                    for (GroceryItems g : c.getItemsAdded().keySet()) {

                        JPanel cartPanelContainer = new JPanel();
                        cartPanelContainer.setLayout(new GridLayout(4, 1));

                        cartPanelContainer.add(new JLabel("Purchase Date : " + data.getUserAccount().getOrderHistory().getOrderHistory().get(c), SwingConstants.CENTER));

                        int quantityPurchased = c.getItemsAdded().get(g);
                        cartPanelContainer.add(new JLabel("Product Name " + g.getProductName(), SwingConstants.CENTER));
                        cartPanelContainer.add(new JLabel("Product Price " + g.getPrice(), SwingConstants.CENTER));
                        cartPanelContainer.add(new JLabel("Quantity Purcahsed " + quantityPurchased, SwingConstants.CENTER));

                        cartPanel.add(cartPanelContainer);

                    }
                    orderHistoryPanel.add(cartPanel);

                }
                this.setVisible(false);
                OrderHistoryView view = new OrderHistoryView();
                view.getViewingOrderHistoryMessage().setText("Viewing " + data.getUserAccount().getUsername() + "'s order history");
                view.getOrderHistoryPanel().setViewportView(orderHistoryPanel);
                OrderHistoryModel model = new OrderHistoryModel();
                model.data = data;
                OrderHistoryController controller = new OrderHistoryController(view, model);

                model.addObserver(view);

            }

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
     * @param searchedGroceryItemsDisplayPanel the
     * searchedGroceryItemsDisplayPanel to set
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

    /**
     * @return the allPlusButtons
     */
    public ArrayList<JButton> getAllPlusButtons() {
        return allPlusButtons;
    }

    /**
     * @param allPlusButtons the allPlusButtons to set
     */
    public void setAllPlusButtons(ArrayList<JButton> allPlusButtons) {
        this.allPlusButtons = allPlusButtons;
    }

    /**
     * @return the allMinusButtons
     */
    public ArrayList<JButton> getAllMinusButtons() {
        return allMinusButtons;
    }

    /**
     * @param allMinusButtons the allMinusButtons to set
     */
    public void setAllMinusButtons(ArrayList<JButton> allMinusButtons) {
        this.allMinusButtons = allMinusButtons;
    }

    /**
     * @return the allQuantityTextFields
     */
    public ArrayList<JTextField> getAllQuantityTextFields() {
        return allQuantityTextFields;
    }

    /**
     * @param allQuantityTextFields the allQuantityTextFields to set
     */
    public void setAllQuantityTextFields(ArrayList<JTextField> allQuantityTextFields) {
        this.allQuantityTextFields = allQuantityTextFields;
    }

    /**
     * @return the allAddItemButtonsAfterSearch
     */
    public ArrayList<JButton> getAllAddItemButtonsAfterSearch() {
        return allAddItemButtonsAfterSearch;
    }

    /**
     * @param allAddItemButtonsAfterSearch the allAddItemButtonsAfterSearch to
     * set
     */
    public void setAllAddItemButtonsAfterSearch(ArrayList<JButton> allAddItemButtonsAfterSearch) {
        this.allAddItemButtonsAfterSearch = allAddItemButtonsAfterSearch;
    }

    /**
     * @return the allPlusButtonsAfterSearch
     */
    public ArrayList<JButton> getAllPlusButtonsAfterSearch() {
        return allPlusButtonsAfterSearch;
    }

    /**
     * @param allPlusButtonsAfterSearch the allPlusButtonsAfterSearch to set
     */
    public void setAllPlusButtonsAfterSearch(ArrayList<JButton> allPlusButtonsAfterSearch) {
        this.allPlusButtonsAfterSearch = allPlusButtonsAfterSearch;
    }

    /**
     * @return the allMinusButtonsAfterSearch
     */
    public ArrayList<JButton> getAllMinusButtonsAfterSearch() {
        return allMinusButtonsAfterSearch;
    }

    /**
     * @param allMinusButtonsAfterSearch the allMinusButtonsAfterSearch to set
     */
    public void setAllMinusButtonsAfterSearch(ArrayList<JButton> allMinusButtonsAfterSearch) {
        this.allMinusButtonsAfterSearch = allMinusButtonsAfterSearch;
    }

    /**
     * @return the allQuantityTextFieldsAfterSearch
     */
    public ArrayList<JTextField> getAllQuantityTextFieldsAfterSearch() {
        return allQuantityTextFieldsAfterSearch;
    }

    /**
     * @param allQuantityTextFieldsAfterSearch the
     * allQuantityTextFieldsAfterSearch to set
     */
    public void setAllQuantityTextFieldsAfterSearch(ArrayList<JTextField> allQuantityTextFieldsAfterSearch) {
        this.allQuantityTextFieldsAfterSearch = allQuantityTextFieldsAfterSearch;
    }

    /**
     * @return the cartItemAddedPanel
     */
    public JPanel getCartItemAddedPanel() {
        return cartItemAddedPanel;
    }

    /**
     * @param cartItemAddedPanel the cartItemAddedPanel to set
     */
    public void setCartItemAddedPanel(JPanel cartItemAddedPanel) {
        this.cartItemAddedPanel = cartItemAddedPanel;
    }

    /**
     * @return the cartAdditionSuccessMessage
     */
    public JLabel getCartAdditionSuccessMessage() {
        return cartAdditionSuccessMessage;
    }

    /**
     * @param cartAdditionSuccessMessage the cartAdditionSuccessMessage to set
     */
    public void setCartAdditionSuccessMessage(JLabel cartAdditionSuccessMessage) {
        this.cartAdditionSuccessMessage = cartAdditionSuccessMessage;
    }

    /**
     * @return the returnBackToMainMenuButton
     */
    public JButton getReturnBackToMainMenuButton() {
        return returnBackToMainMenuButton;
    }

    /**
     * @param returnBackToMainMenuButton the returnBackToMainMenuButton to set
     */
    public void setReturnBackToMainMenuButton(JButton returnBackToMainMenuButton) {
        this.returnBackToMainMenuButton = returnBackToMainMenuButton;
    }

}
