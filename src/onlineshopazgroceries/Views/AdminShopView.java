/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries.Views;

import onlineshopazgroceries.Models.CartModel;
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
import onlineshopazgroceries.Controllers.AdminShopController;
import onlineshopazgroceries.Controllers.CartController;
import onlineshopazgroceries.Controllers.CheckoutServiceController;
import onlineshopazgroceries.Controllers.OrderHistoryController;
import onlineshopazgroceries.GroceryItems;
import onlineshopazgroceries.Models.AdminShopModel;
import onlineshopazgroceries.Models.CheckoutServiceModel;
import onlineshopazgroceries.Models.OrderHistoryModel;
import onlineshopazgroceries.ShopData;

/**
 *
 * @author krist
 */
public class AdminShopView extends JFrame implements Observer {

    public ShopData data; 
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
    private JLabel productCategoryLabel = new JLabel("Enter Product Category: ", SwingConstants.CENTER);
    private JLabel productPriceLabel = new JLabel("Enter Product Price: ", SwingConstants.CENTER);
    private JLabel productQuantityLabel = new JLabel("Enter Product Quantity: ", SwingConstants.CENTER);

    private JPanel searchListPanel = new JPanel();

    private JTextField productNameTextField = new JTextField();
    private JTextField productCategoryTextField = new JTextField();
    private JTextField productPriceTextField = new JTextField();
    private JTextField productQuantityTextField = new JTextField();

    private JScrollPane groceryItemDisplayPanel = new JScrollPane();
    private JScrollPane removeGroceryItemDisplayPanel = new JScrollPane();
    private JScrollPane modifiableGroceryItemDisplayPanel = new JScrollPane();

    private JScrollPane searchedGroceryItemsDisplayPanel = new JScrollPane();
    private JLabel searchResultsLabel = new JLabel();

    private JButton addItemButton = new JButton("Add Item");
    private JButton officialAddItemButton = new JButton("Add Product to Inventory");
    private JLabel addItemStatus = new JLabel("", SwingConstants.CENTER);

    private JLabel addItemSuccessMessage = new JLabel("", SwingConstants.CENTER);
    private JLabel removeItemSuccessMessage = new JLabel("", SwingConstants.CENTER);
    private JLabel itemModificationSuccessMessage = new JLabel("", SwingConstants.CENTER);
    private JLabel cartAdditionSuccessMessage = new JLabel("", SwingConstants.CENTER);
    private JPanel itemAddedPanel = new JPanel();
    private JPanel modifiedItemPanel = new JPanel();
    private JPanel cartItemAddedPanel = new JPanel();

    private ArrayList<JButton> allAddItemButtons = new ArrayList<>();
    private ArrayList<JButton> allPlusButtons = new ArrayList<>();
    private ArrayList<JButton> allMinusButtons = new ArrayList<>();
    private ArrayList<JTextField> allQuantityTextFields = new ArrayList<>();
    private ArrayList<JButton> allAddItemButtonsAfterSearch = new ArrayList<>();
    private ArrayList<JButton> allPlusButtonsAfterSearch = new ArrayList<>();
    private ArrayList<JButton> allMinusButtonsAfterSearch = new ArrayList<>();
    private ArrayList<JTextField> allQuantityTextFieldsAfterSearch = new ArrayList<>();
    private ArrayList<JButton> allRemoveItemButtons = new ArrayList<>();
    private ArrayList<JButton> allModifyItemQuantityButtons = new ArrayList<>();
    private ArrayList<JTextField> allModifyItemQuantityTextFields = new ArrayList<>();
    private JButton returnBackToMainMenuButton = new JButton("Return Back To Main Menu");

    public AdminShopView() {

        setTitle("AZGroceries Admin Store");

        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getStoreTitle().setFont(new Font("Times New Roman", Font.BOLD, 30));
        getWelcomeMessage().setFont(new Font("Arial", Font.PLAIN, 20));
        this.DisplayAdminShopOptions();

    }

    public void DisplayAdminShopOptions() {

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        double halfWidth = size.getWidth() / 2;
        int halfWidthInteger = (int) halfWidth;
        this.setLocation(halfWidthInteger - 500, 0);

        this.setSize(1000, 740);

        this.getContentPane().removeAll();

        getTitlePanel().removeAll();
        getTitlePanel().setLayout(new GridLayout(4, 1));
        getTitlePanel().add(getStoreTitle());
        setWelcomeMessage(new JLabel(getWelcomeMessage().getText(), SwingConstants.CENTER));
        getTitlePanel().add(getWelcomeMessage());
        getTitlePanel().setBackground(Color.CYAN.brighter());

        JPanel searchPanel = new JPanel();
        searchPanel.add(getSearchTextField());
        searchPanel.add(getSearchButton());
        searchPanel.add(getSearchResultsLabel());
        searchPanel.setBackground(Color.blue.brighter());
        //  searchPanel.setBackground(Color.blue);

        getTitlePanel().add(searchPanel);

        getAdminOptionsPanel().add(getViewCartButton());
        getAdminOptionsPanel().setBackground(Color.CYAN.brighter());

        getAdminOptionsPanel().add(getViewOrderHistoryButton());

        getAdminOptionsPanel().add(getCheckoutButton());

        getAdminOptionsPanel().add(getAddItemOptionButton());
        getAdminOptionsPanel().add(getRemoveItemOptionButton());

        getAdminOptionsPanel().add(getModifyItemQuantityOptionButton());

        getTitlePanel().add(getAdminOptionsPanel());
        add(getTitlePanel(), BorderLayout.NORTH);

        setGroceryItemDisplayPanel(getGroceryItemDisplayPanel());
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

        getTitlePanel().add(getWelcomeMessage());

        JPanel searchPanel = new JPanel();
        searchPanel.add(getSearchTextField());
        searchPanel.add(getSearchButton());
        searchPanel.setBackground(Color.blue.brighter());
        getTitlePanel().add(searchPanel);
        getTitlePanel().add(getAdminOptionsPanel());

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

    public void adminAddItemView() {

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        double halfWidth = size.getWidth() / 2;
        int halfWidthInteger = (int) halfWidth;
        double halfHeight = size.getHeight() / 2;
        int halfHeightInteger = (int) halfHeight;
        this.setLocation(halfWidthInteger - 800, halfHeightInteger - 150);

        this.getContentPane().removeAll();

        this.setSize(800, 300);
        this.setLocationRelativeTo(null);
        getTitlePanel().setBackground(Color.CYAN.brighter());
        getTitlePanel().removeAll();
        getTitlePanel().setLayout(new GridLayout(2, 1));
        getTitlePanel().add(getStoreTitle());

        JPanel messagePanel = new JPanel();
        messagePanel.add(new JLabel("Please Enter the details of the Grocery Item to be added", SwingConstants.CENTER));

        getTitlePanel().add(messagePanel);
        add(getTitlePanel(), BorderLayout.NORTH);

        getAddGroceryItemPanel().setLayout(new GridLayout(4, 2));
        getAddGroceryItemPanel().add(getProductNameLabel());
        getAddGroceryItemPanel().add(getProductNameTextField());
        getAddGroceryItemPanel().add(getProductCategoryLabel());
        getAddGroceryItemPanel().add(getProductCategoryTextField());
        getAddGroceryItemPanel().add(getProductPriceLabel());
        getAddGroceryItemPanel().add(getProductPriceTextField());
        getAddGroceryItemPanel().add(getProductQuantityLabel());
        getAddGroceryItemPanel().add(getProductQuantityTextField());
        add(getAddGroceryItemPanel(), BorderLayout.CENTER);

        JPanel SouthPanel = new JPanel();

        SouthPanel.setLayout(new GridLayout(2, 1));
        JPanel buttonPanel = new JPanel();

        buttonPanel.add(getOfficialAddItemButton());
        buttonPanel.add(getReturnBackToMainMenuButton());
        SouthPanel.add(buttonPanel);

        SouthPanel.add(getAddItemStatus());

        add(SouthPanel, BorderLayout.SOUTH);

        setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void adminRemoveItemView() {
        this.getContentPane().removeAll();
        this.setSize(900, 740);
        this.setLocationRelativeTo(null);
        getTitlePanel().setBackground(Color.CYAN.brighter());
        getTitlePanel().removeAll();
        getTitlePanel().setLayout(new GridLayout(3, 1));
        getTitlePanel().add(getStoreTitle());

        JPanel messagePanel = new JPanel();
        messagePanel.add(new JLabel("Please Select the Grocery Item you wish to remove", SwingConstants.CENTER));

        getTitlePanel().add(messagePanel);

        JPanel backToMainMenuButtonPanel = new JPanel();

        backToMainMenuButtonPanel.add(getReturnBackToMainMenuButton());

        getTitlePanel().add(backToMainMenuButtonPanel);

        add(getTitlePanel(), BorderLayout.NORTH);

        setRemoveGroceryItemDisplayPanel(getRemoveGroceryItemDisplayPanel());
        add(getRemoveGroceryItemDisplayPanel(), BorderLayout.CENTER);

        add(new JPanel(), BorderLayout.SOUTH);

        setVisible(true);
        this.revalidate();
        this.repaint();

    }

    public void adminModifyItemQuantityView() {
        this.getContentPane().removeAll();
        this.setSize(900, 740);
        this.setLocationRelativeTo(null);
        getTitlePanel().setBackground(Color.CYAN.brighter());
        getTitlePanel().removeAll();
        getTitlePanel().setLayout(new GridLayout(3, 1));
        getTitlePanel().add(getStoreTitle());

        JPanel messagePanel = new JPanel();
        messagePanel.add(new JLabel("Please Adjust the quantity of the desired Grocery Item", SwingConstants.CENTER));

        getTitlePanel().add(messagePanel);

        JPanel backToMainMenuButtonPanel = new JPanel();

        backToMainMenuButtonPanel.add(getReturnBackToMainMenuButton());

        getTitlePanel().add(backToMainMenuButtonPanel);

        add(getTitlePanel(), BorderLayout.NORTH);

        add(getModifiableGroceryItemDisplayPanel(), BorderLayout.CENTER);

        add(new JPanel(), BorderLayout.SOUTH);

        setVisible(true);
        this.revalidate();
        this.repaint();

    }

    public void displayItemAddedSucessfully() {
        this.getContentPane().removeAll();
        this.setSize(800, 275);
        this.setLocationRelativeTo(null);
        getTitlePanel().setBackground(Color.CYAN.brighter());
        getTitlePanel().removeAll();
        getTitlePanel().setLayout(new GridLayout(2, 1));
        getTitlePanel().add(getStoreTitle());

        JPanel messagePanel = new JPanel();
        messagePanel.add(getAddItemSuccessMessage());

        getTitlePanel().add(messagePanel);

        add(getTitlePanel(), BorderLayout.NORTH);

        add(getItemAddedPanel(), BorderLayout.CENTER);

        JPanel SouthPanel = new JPanel();

        JPanel backToMainMenuButtonPanel = new JPanel();

        backToMainMenuButtonPanel.add(getReturnBackToMainMenuButton());

        SouthPanel.add(backToMainMenuButtonPanel);
        add(SouthPanel, BorderLayout.SOUTH);

        setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void displayItemRemovedSucessfully() {
        this.getContentPane().removeAll();
        this.setSize(800, 275);
        this.setLocationRelativeTo(null);
        getTitlePanel().setBackground(Color.CYAN.brighter());
        getTitlePanel().removeAll();
        getTitlePanel().setLayout(new GridLayout(2, 1));
        getTitlePanel().add(getStoreTitle());

        JPanel messagePanel = new JPanel();
        messagePanel.add(getRemoveItemSuccessMessage());

        getTitlePanel().add(messagePanel);
        add(getTitlePanel(), BorderLayout.NORTH);

        JPanel CenterPanel = new JPanel();

        JPanel backToMainMenuButtonPanel = new JPanel();

        backToMainMenuButtonPanel.add(getReturnBackToMainMenuButton());

        CenterPanel.add(backToMainMenuButtonPanel);
        add(CenterPanel, BorderLayout.CENTER);

        setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void displayItemModifiedSucessfully() {
        this.getContentPane().removeAll();
        this.setSize(800, 275);
        this.setLocationRelativeTo(null);
        getTitlePanel().setBackground(Color.CYAN.brighter());
        getTitlePanel().removeAll();
        getTitlePanel().setLayout(new GridLayout(2, 1));
        getTitlePanel().add(getStoreTitle());

        JPanel messagePanel = new JPanel();
        messagePanel.add(getItemModificationSuccessMessage());

        getTitlePanel().add(messagePanel);
        add(getTitlePanel(), BorderLayout.NORTH);

        add(getModifiedItemPanel(), BorderLayout.CENTER);

        JPanel SouthPanel = new JPanel();

        JPanel backToMainMenuButtonPanel = new JPanel();

        backToMainMenuButtonPanel.add(getReturnBackToMainMenuButton());

        SouthPanel.add(backToMainMenuButtonPanel);
        add(SouthPanel, BorderLayout.SOUTH);

        setVisible(true);
        this.revalidate();
        this.repaint();

    }

    public void displayItemAddedToCartSucessfully() {

        this.getContentPane().removeAll();
        this.setSize(800, 275);
        this.setLocationRelativeTo(null);
        getTitlePanel().setBackground(Color.CYAN.brighter());
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
        
        if (this.data.isNeverPressedMainMenuButton())
        {
            getSearchButton().addActionListener(actionListener);
        getViewCartButton().addActionListener(actionListener);

        getCheckoutButton().addActionListener(actionListener);
        getAddItemOptionButton().addActionListener(actionListener);

        getRemoveItemOptionButton().addActionListener(actionListener);

        getModifyItemQuantityOptionButton().addActionListener(actionListener);
        getViewOrderHistoryButton().addActionListener(actionListener);

        getReturnBackToMainMenuButton().addActionListener(actionListener);
        getOfficialAddItemButton().addActionListener(actionListener);
        
        }
        

        for (JButton addGroceryItemButton : getAllAddItemButtons()) {
            addGroceryItemButton.addActionListener(actionListener);
        }
        for (JButton minusButton : getAllMinusButtons()) {
            minusButton.addActionListener(actionListener);
        }

        for (JButton plusButton : getAllPlusButtons()) {
            plusButton.addActionListener(actionListener);
        }
        for (JButton removeButton : getAllRemoveItemButtons()) {
            removeButton.addActionListener(actionListener);
        }
        for (JButton modifyItemQuantityButton : getAllModifyItemQuantityButtons()) {
            modifyItemQuantityButton.addActionListener(actionListener);

        }

        

    }

    @Override
    public void update(Observable o, Object arg) {
        ShopData data = (ShopData) arg;
        if (data.isUserSearching() && data.isAttemptingSearch()) {
            
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
                JButton addGroceryItemOptionButton = getAllAddItemButtonsAfterSearch().get(data.getSearchedItems().indexOf(g));

                AddItemPanel.add(addGroceryItemOptionButton);
                SearchedGroceryItemContainer.add(AddItemPanel);
                SearchedGroceryItemsDisplayPanel.add(SearchedGroceryItemContainer);

            }
            getSearchResultsLabel().setForeground(Color.black);
            getSearchResultsLabel().setText("Search results for: \"" + data.getSearchQuery() + "\"");
            this.getSearchedGroceryItemsDisplayPanel().setViewportView(SearchedGroceryItemsDisplayPanel);

            DisplaySearchResults();
        }
        if (!data.isUserSearching() && data.isAttemptingSearch()) {
            getSearchResultsLabel().setText(data.getReasonSearchFailed());
            getSearchResultsLabel().setForeground(Color.white);
            DisplayAdminShopOptions();

        }
        if (data.isMainMenuRequested()) {
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

            JPanel removableGroceryItemsDisplayPanel = new JPanel();
            JPanel modifiableGroceryItemsDisplayPanel = new JPanel();
            ArrayList<JButton> removeButtons = new ArrayList<>();
            ArrayList<JButton> modifyItemQuantityButtons = new ArrayList<>();
            ArrayList<JTextField> modifyItemQuantityTextFields = new ArrayList<>();

            int numberOfRows = (int) data.getListOfGroceries().getGroceries().size() / 3 + 1;
            removableGroceryItemsDisplayPanel.setLayout(new GridLayout(numberOfRows, 3));
            modifiableGroceryItemsDisplayPanel.setLayout(new GridLayout(numberOfRows, 3));
            for (GroceryItems groceries : data.getListOfGroceries().getGroceries()) {
                JPanel removableGroceryItemContainer = new JPanel();
                removableGroceryItemContainer.setLayout(new GridLayout(5, 1));

                JPanel modifiableGroceryItemContainer = new JPanel();
                modifiableGroceryItemContainer.setLayout(new GridLayout(5, 1));

                JLabel removableProductName = new JLabel("Product Name : " + groceries.getProductName());
                JLabel removableProductCategory = new JLabel("Category: " + groceries.getCategory());
                JLabel removableProductPrice = new JLabel("Price: $" + groceries.getPrice());

                JLabel GroceryItemName = new JLabel(groceries.getProductName());
                GroceryItemName.setFont(new Font("Arial", Font.BOLD, 15));
                removableGroceryItemContainer.add(removableProductName);
                modifiableGroceryItemContainer.add(GroceryItemName);

                JLabel GroceryItemCategory = new JLabel("Category: " + groceries.getCategory());
                removableGroceryItemContainer.add(removableProductCategory);
                modifiableGroceryItemContainer.add(GroceryItemCategory);

                JLabel GroceryItemPrice = new JLabel("Price: $" + groceries.getPrice());
                removableGroceryItemContainer.add(removableProductPrice);
                modifiableGroceryItemContainer.add(GroceryItemPrice);

                JLabel productQuantityAvailableLabel = new JLabel("Quantity Avaialble: " + groceries.getQuantityAvailable());
                JLabel originalProductQuantityAvailable = new JLabel("Original Quantity Avaialble: " + groceries.getQuantityAvailable());
                removableGroceryItemContainer.add(productQuantityAvailableLabel);
                modifiableGroceryItemContainer.add(originalProductQuantityAvailable);

                JPanel removeItemPanel = new JPanel();
                JPanel modifyItemPanel = new JPanel();
                JButton removeGroceryItemOptionButton = new JButton("Remove " + groceries.getProductName());

                JTextField modifyItemQuantityTextField = new JTextField("" + groceries.getQuantityAvailable(), 3);
                JButton modifyGroceryItemOptionButton = new JButton("Modify Item Quantity");

                modifyItemQuantityButtons.add(modifyGroceryItemOptionButton);
                modifyItemQuantityTextFields.add(modifyItemQuantityTextField);
                removeButtons.add(removeGroceryItemOptionButton);

                removeItemPanel.add(removeGroceryItemOptionButton);
                modifyItemPanel.add(modifyItemQuantityTextField);
                modifyItemPanel.add(modifyGroceryItemOptionButton);

                modifiableGroceryItemContainer.add(modifyItemPanel);
                removableGroceryItemContainer.add(removeItemPanel);

                modifiableGroceryItemsDisplayPanel.add(modifiableGroceryItemContainer);
                removableGroceryItemsDisplayPanel.add(removableGroceryItemContainer);

            }

            AdminShopView view = data.getAdminShopView();

            getWelcomeMessage().setText(data.getUserAccount().getUsername() + ", you are ready to shop!");
            setAllAddItemButtons(addButtons);
            setAllMinusButtons(minusButtons);
            setAllPlusButtons(plusButtons);
            setAllQuantityTextFields(quantityTextFields);
            getGroceryItemDisplayPanel().setViewportView(GroceryItemDisplayPanel);
            getRemoveGroceryItemDisplayPanel().setViewportView(removableGroceryItemsDisplayPanel);
            getModifiableGroceryItemDisplayPanel().setViewportView(modifiableGroceryItemsDisplayPanel);
            setAllRemoveItemButtons(removeButtons);
            setAllModifyItemQuantityButtons(modifyItemQuantityButtons);
            setAllModifyItemQuantityTextFields(modifyItemQuantityTextFields);
            getSearchResultsLabel().setText("");
            if (data.getUserAccount().getUserCart().getItemsAdded().isEmpty()) {
                getCheckoutButton().setEnabled(false);
            }

            AdminShopModel model = data.getAdminShopModel();
            model.data = data;
            AdminShopController controller = new AdminShopController(view, model);
            model.addObserver(view);

            view.DisplayAdminShopOptions();

        }
        if (data.isRequestingToAddItem()) {
            adminAddItemView();
        }
        if (data.isRequestingToRemoveItem()) {

            adminRemoveItemView();
        }
        if (data.isRequestingToModifyItemQuantityInShop() && !data.isItemQuantityModified()) {

            adminModifyItemQuantityView();
        }
        if (data.isItemRemoved()) {
            getRemoveItemSuccessMessage().setText("Your Item has been removed sucessfully!");
            displayItemRemovedSucessfully();
        }
        if (data.isItemAdded()) {
            getAddItemSuccessMessage().setText("Your Item has been added sucessfully!");
            getItemAddedPanel().setLayout(new GridLayout(4, 1));
            getItemAddedPanel().add(new JLabel("Product name: " + data.getGroceryItemAdded().getProductName(), SwingConstants.CENTER));
            getItemAddedPanel().add(new JLabel("Product category: " + data.getGroceryItemAdded().getCategory(), SwingConstants.CENTER));
            getItemAddedPanel().add(new JLabel("Product price: " + data.getGroceryItemAdded().getPrice(), SwingConstants.CENTER));
            getItemAddedPanel().add(new JLabel("Product quantity available: " + data.getGroceryItemAdded().getQuantityAvailable(), SwingConstants.CENTER));
            displayItemAddedSucessfully();
        }
        if (data.isItemQuantityModified()) {
            getItemModificationSuccessMessage().setText("Your Item has been modified sucessfully!");
            getModifiedItemPanel().setLayout(new GridLayout(4, 1));
            getModifiedItemPanel().add(new JLabel("Product name: " + data.getModifiedGroceryItem().getProductName(), SwingConstants.CENTER));
            getModifiedItemPanel().add(new JLabel("Product category: " + data.getModifiedGroceryItem().getCategory(), SwingConstants.CENTER));
            getModifiedItemPanel().add(new JLabel("Product price: " + data.getModifiedGroceryItem().getPrice(), SwingConstants.CENTER));
            getModifiedItemPanel().add(new JLabel("Product quantity available: " + data.getModifiedGroceryItem().getQuantityAvailable(), SwingConstants.CENTER));
            displayItemModifiedSucessfully();

        }
        if (!data.isItemAdded() && data.isRequestingToAddItem()) {
            getAddItemStatus().setText(data.getReasonItemAdditionFailed());
            adminAddItemView();
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
                ArrayList<JButton> cartPlusButtons = new ArrayList<>();
                ArrayList<JButton> cartMinusButtons = new ArrayList<>();
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
                    cartMinusButtons.add(minusButton);
                    cartPlusButtons.add(plusButton);
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
                view.setAllMinusButtons(cartMinusButtons);
                view.setAllPlusButtons(cartPlusButtons);
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
     * @return the officialAddItemButton
     */
    public JButton getOfficialAddItemButton() {
        return officialAddItemButton;
    }

    /**
     * @param officialAddItemButton the officialAddItemButton to set
     */
    public void setOfficialAddItemButton(JButton officialAddItemButton) {
        this.officialAddItemButton = officialAddItemButton;
    }

    /**
     * @return the addItemSuccessMessage
     */
    public JLabel getAddItemSuccessMessage() {
        return addItemSuccessMessage;
    }

    /**
     * @param addItemSuccessMessage the addItemSuccessMessage to set
     */
    public void setAddItemSuccessMessage(JLabel addItemSuccessMessage) {
        this.addItemSuccessMessage = addItemSuccessMessage;
    }

    /**
     * @return the itemAddedPanel
     */
    public JPanel getItemAddedPanel() {
        return itemAddedPanel;
    }

    /**
     * @param itemAddedPanel the itemAddedPanel to set
     */
    public void setItemAddedPanel(JPanel itemAddedPanel) {
        this.itemAddedPanel = itemAddedPanel;
    }

    /**
     * @return the addItemStatus
     */
    public JLabel getAddItemStatus() {
        return addItemStatus;
    }

    /**
     * @param addItemStatus the addItemStatus to set
     */
    public void setAddItemStatus(JLabel addItemStatus) {
        this.addItemStatus = addItemStatus;
    }

    /**
     * @return the productCategoryTextField
     */
    public JTextField getProductCategoryTextField() {
        return productCategoryTextField;
    }

    /**
     * @param productCategoryTextField the productCategoryTextField to set
     */
    public void setProductCategoryTextField(JTextField productCategoryTextField) {
        this.productCategoryTextField = productCategoryTextField;
    }

    /**
     * @return the productCategoryLabel
     */
    public JLabel getProductCategoryLabel() {
        return productCategoryLabel;
    }

    /**
     * @param productCategoryLabel the productCategoryLabel to set
     */
    public void setProductCategoryLabel(JLabel productCategoryLabel) {
        this.productCategoryLabel = productCategoryLabel;
    }

    /**
     * @return the removeGroceryItemDisplayPanel
     */
    public JScrollPane getRemoveGroceryItemDisplayPanel() {
        return removeGroceryItemDisplayPanel;
    }

    /**
     * @param removeGroceryItemDisplayPanel the removeGroceryItemDisplayPanel to
     * set
     */
    public void setRemoveGroceryItemDisplayPanel(JScrollPane removeGroceryItemDisplayPanel) {
        this.removeGroceryItemDisplayPanel = removeGroceryItemDisplayPanel;
    }

    /**
     * @return the allRemoveItemButtons
     */
    public ArrayList<JButton> getAllRemoveItemButtons() {
        return allRemoveItemButtons;
    }

    /**
     * @param allRemoveItemButtons the allRemoveItemButtons to set
     */
    public void setAllRemoveItemButtons(ArrayList<JButton> allRemoveItemButtons) {
        this.allRemoveItemButtons = allRemoveItemButtons;
    }

    /**
     * @return the removeItemSuccessMessage
     */
    public JLabel getRemoveItemSuccessMessage() {
        return removeItemSuccessMessage;
    }

    /**
     * @param removeItemSuccessMessage the removeItemSuccessMessage to set
     */
    public void setRemoveItemSuccessMessage(JLabel removeItemSuccessMessage) {
        this.removeItemSuccessMessage = removeItemSuccessMessage;
    }

    /**
     * @return the allModifyItemQuantityButtons
     */
    public ArrayList<JButton> getAllModifyItemQuantityButtons() {
        return allModifyItemQuantityButtons;
    }

    /**
     * @param allModifyItemQuantityButtons the allModifyItemQuantityButtons to
     * set
     */
    public void setAllModifyItemQuantityButtons(ArrayList<JButton> allModifyItemQuantityButtons) {
        this.allModifyItemQuantityButtons = allModifyItemQuantityButtons;
    }

    /**
     * @return the allModifyItemQuantityTextFields
     */
    public ArrayList<JTextField> getAllModifyItemQuantityTextFields() {
        return allModifyItemQuantityTextFields;
    }

    /**
     * @param allModifyItemQuantityTextFields the
     * allModifyItemQuantityTextFields to set
     */
    public void setAllModifyItemQuantityTextFields(ArrayList<JTextField> allModifyItemQuantityTextFields) {
        this.allModifyItemQuantityTextFields = allModifyItemQuantityTextFields;
    }

    /**
     * @return the modifiableGroceryItemDisplayPanel
     */
    public JScrollPane getModifiableGroceryItemDisplayPanel() {
        return modifiableGroceryItemDisplayPanel;
    }

    /**
     * @param modifiableGroceryItemDisplayPanel the
     * modifiableGroceryItemDisplayPanel to set
     */
    public void setModifiableGroceryItemDisplayPanel(JScrollPane modifiableGroceryItemDisplayPanel) {
        this.modifiableGroceryItemDisplayPanel = modifiableGroceryItemDisplayPanel;
    }

    /**
     * @return the itemModificationSuccessMessage
     */
    public JLabel getItemModificationSuccessMessage() {
        return itemModificationSuccessMessage;
    }

    /**
     * @param itemModificationSuccessMessage the itemModificationSuccessMessage
     * to set
     */
    public void setItemModificationSuccessMessage(JLabel itemModificationSuccessMessage) {
        this.itemModificationSuccessMessage = itemModificationSuccessMessage;
    }

    /**
     * @return the modifiedItemPanel
     */
    public JPanel getModifiedItemPanel() {
        return modifiedItemPanel;
    }

    /**
     * @param modifiedItemPanel the modifiedItemPanel to set
     */
    public void setModifiedItemPanel(JPanel modifiedItemPanel) {
        this.modifiedItemPanel = modifiedItemPanel;
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
