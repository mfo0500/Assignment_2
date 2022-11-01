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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import onlineshopazgroceries.Controllers.AdminShopController;
import onlineshopazgroceries.Controllers.CustomerShopController;
import onlineshopazgroceries.GroceryItems;
import onlineshopazgroceries.Models.AdminShopModel;
import onlineshopazgroceries.Models.CustomerShopModel;
import onlineshopazgroceries.ShopData;

/**
 *
 * @author krist
 */
public class CheckoutServiceView extends JFrame implements Observer {

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

    private JLabel storeTitle = new JLabel("WELCOME TO THE AZCROCERIES ONLINE STORE", SwingConstants.CENTER);
    private JLabel pickupLabel = new JLabel("Pickup ", SwingConstants.CENTER);
    private JLabel deliveryLabel = new JLabel("Delivery ", SwingConstants.CENTER);
    private JCheckBox pickupCheckBox = new JCheckBox();
    private JCheckBox deliveryCheckBox = new JCheckBox();
    private JLabel cartTotalLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel cardnumberLabel = new JLabel("Enter Card Number (16 Digits): ", SwingConstants.CENTER);
    private JLabel cardholderNameLabel = new JLabel("Enter Cardholder name: ", SwingConstants.CENTER);
    private JLabel cardExpiryMonthLabel = new JLabel("Select Card Expiry Month ", SwingConstants.CENTER);
    private JLabel cardExpiryYearLabel = new JLabel("Select Card Expiry Year ", SwingConstants.CENTER);
    private JTextField cardNumberTextField = new JTextField();
    private JTextField cardholderNameTextField = new JTextField();
    private JButton purchaseButton = new JButton("Purchase");
    private JComboBox cardExpiryMonthCombobox = new JComboBox();
    private JComboBox cardExpiryYearCombobox = new JComboBox();
    private JLabel cardCVCLabel = new JLabel("Enter Card CVC: ", SwingConstants.CENTER);
    private JTextField cardCVCTextField = new JTextField();
    
    private JButton returnBackToMainMenuButton = new JButton("Return Back To Main Menu");

    private JPanel titlePanel = new JPanel();
    private JPanel purchaseInformationPanel = new JPanel();

    public CheckoutServiceView() {
        this.getContentPane().removeAll();

      
       

        setTitle("AZGroceries Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getStoreTitle().setFont(new Font("Times New Roman", Font.BOLD, 22));
        displayCheckoutRequirements();
        
    }
    
    public void displayCheckoutRequirements()
    {
         Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        double halfWidth = size.getWidth() / 2;
        int halfWidthInteger = (int) halfWidth;
        double halfHeight = size.getHeight() / 2;
        int halfHeightInteger = (int) halfHeight;
        this.setLocation(halfWidthInteger - 300, halfHeightInteger - 175);
        
        this.setSize(600, 350);
        getTitlePanel().setBackground(Color.CYAN.brighter());
        getTitlePanel().removeAll();
        getTitlePanel().setLayout(new GridLayout(3, 1));
        getTitlePanel().add(getStoreTitle());
        
        JPanel backToMainMenuButtonPanel = new JPanel();
        
        backToMainMenuButtonPanel.add(getReturnBackToMainMenuButton());
        
         getTitlePanel().add(backToMainMenuButtonPanel);
        
        
        getTitlePanel().add(getCartTotalLabel());
                
        add(getTitlePanel(), BorderLayout.NORTH);

        getPurchaseInformationPanel().setLayout(new GridLayout(6, 2));

        getPickupCheckBox().setSelected(true);
        getDeliveryCheckBox().setSelected(false);

        getCardExpiryMonthCombobox().addItem("January");
        getCardExpiryMonthCombobox().addItem("February");
        getCardExpiryMonthCombobox().addItem("March");
        getCardExpiryMonthCombobox().addItem("April");
        getCardExpiryMonthCombobox().addItem("May");
        getCardExpiryMonthCombobox().addItem("June");
        getCardExpiryMonthCombobox().addItem("July");
        getCardExpiryMonthCombobox().addItem("August");
        getCardExpiryMonthCombobox().addItem("September");
        getCardExpiryMonthCombobox().addItem("October");
        getCardExpiryMonthCombobox().addItem("Novemeber");
        getCardExpiryMonthCombobox().addItem("December");

        getCardExpiryYearCombobox().addItem("2022");
        getCardExpiryYearCombobox().addItem("2023");
        getCardExpiryYearCombobox().addItem("2024");
        getCardExpiryYearCombobox().addItem("2025");
        getCardExpiryYearCombobox().addItem("2026");
        getCardExpiryYearCombobox().addItem("2027");

        getPurchaseInformationPanel().add(getPickupLabel());
        getPurchaseInformationPanel().add(getPickupCheckBox());

        getPurchaseInformationPanel().add(getDeliveryLabel());
        getPurchaseInformationPanel().add(getDeliveryCheckBox());
        getPurchaseInformationPanel().add(getCardnumberLabel());
        getPurchaseInformationPanel().add(getCardNumberTextField());
        getPurchaseInformationPanel().add(getCardExpiryMonthLabel());
        getPurchaseInformationPanel().add(getCardExpiryMonthCombobox());
        getPurchaseInformationPanel().add(getCardExpiryYearLabel());
        getPurchaseInformationPanel().add(getCardExpiryYearCombobox());
        getPurchaseInformationPanel().add(getCardCVCLabel());
        getPurchaseInformationPanel().add(getCardCVCTextField());

        JPanel purchasePanel = new JPanel();
        purchasePanel.add(getPurchaseButton());

        add(getPurchaseInformationPanel(), BorderLayout.CENTER);
        add(purchasePanel, BorderLayout.SOUTH);

        setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void addActionListener(ActionListener actionListener) {

        getPurchaseButton().addActionListener(actionListener);
        getReturnBackToMainMenuButton().addActionListener(actionListener);
    }

    @Override
    public void update(Observable o, Object arg) {
        ShopData data = (ShopData)arg;
        if(data.isMainMenuRequested())
        {
            data.setMainMenuRequested(false);
            JPanel GroceryItemDisplayPanel = new JPanel();
            ArrayList<JButton> addButtons = new ArrayList<>();
            ArrayList<JButton> plusButtons = new ArrayList<>();
            ArrayList<JButton> minusButtons = new ArrayList<>();
            ArrayList<JTextField> quantityTextFields = new ArrayList<>();

            if (data.getUserAccount().getRole().equals("Customer")) {

                int numberOfRows = (int) data.getListOfGroceries().getGroceries().size() / 3 + 1;
                GroceryItemDisplayPanel.setLayout(new GridLayout(numberOfRows, 3));
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
                    JButton addItemButton = new JButton("Add " + g.getProductName());

                    addButtons.add(addItemButton);
                    plusButtons.add(plusButton);
                    minusButtons.add(minusButton);
                    quantityTextFields.add(quantityTextField);

                    AddItemPanel.add(addItemButton);
                    GroceryItemContainer.add(AddItemPanel);

                    GroceryItemDisplayPanel.add(GroceryItemContainer);

                }

                this.setVisible(false);
                CustomerShopView view = data.getCustomerShopview();
                view.getWelcomeMessage().setText(data.getUserAccount().getUsername()  + ", you are ready to shop!");
                view.setAllAddItemButtons(addButtons);
                view.setAllMinusButtons(minusButtons);
                view.setAllPlusButtons(plusButtons);
                view.setAllQuantityTextFields(quantityTextFields);
                view.getGroceryItemDisplayPanel().setViewportView(GroceryItemDisplayPanel);
                if (data.getUserAccount().getUserCart().getItemsAdded().isEmpty()) {
                    view.getCheckoutButton().setEnabled(false);
                }
                CustomerShopModel model = data.getCustomerShopModel();
                model.data = data;
                CustomerShopController controller = new CustomerShopController(view, model);
                view.DisplayCustomerShopOptions();
            }
            if (data.getUserAccount().getRole().equals("Admin")) {
                
                
                int numberOfRows = (int) data.getListOfGroceries().getGroceries().size() / 3 + 1;
                GroceryItemDisplayPanel.setLayout(new GridLayout(numberOfRows, 3));
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
                    JButton addItemButton = new JButton("Add " + g.getProductName());

                    addButtons.add(addItemButton);
                    plusButtons.add(plusButton);
                    minusButtons.add(minusButton);
                    quantityTextFields.add(quantityTextField);

                    AddItemPanel.add(addItemButton);
                    GroceryItemContainer.add(AddItemPanel);

                    GroceryItemDisplayPanel.add(GroceryItemContainer);

                }
                
                
                    JPanel removableGroceryItemsDisplayPanel = new JPanel();
                    JPanel modifiableGroceryItemsDisplayPanel = new JPanel();
                    ArrayList<JButton> removeButtons = new ArrayList<>();
                    ArrayList<JButton> modifyItemQuantityButtons = new ArrayList<>();
                    ArrayList<JTextField> modifyItemQuantityTextFields = new ArrayList<>();

                    numberOfRows = (int) data.getListOfGroceries().getGroceries().size() / 3 + 1;
                    removableGroceryItemsDisplayPanel.setLayout(new GridLayout(numberOfRows, 3));
                    modifiableGroceryItemsDisplayPanel.setLayout(new GridLayout(numberOfRows, 3));
                    for (GroceryItems g : data.getListOfGroceries().getGroceries()) {
                        JPanel removableGroceryItemContainer = new JPanel();
                        removableGroceryItemContainer.setLayout(new GridLayout(5, 1));

                        JPanel modifiableGroceryItemContainer = new JPanel();
                        modifiableGroceryItemContainer.setLayout(new GridLayout(5, 1));
                        
                        JLabel removableProductName =  new JLabel("Product Name : " + g.getProductName());
                        JLabel removableProductCategory = new JLabel("Category: " + g.getCategory());
                        JLabel removableProductPrice = new JLabel("Price: $" + g.getPrice());

                        JLabel productName = new JLabel(g.getProductName());
                        productName.setFont(new Font("Arial", Font.BOLD, 15));
                        removableGroceryItemContainer.add(removableProductName);
                        modifiableGroceryItemContainer.add(productName);

                        JLabel productCategory = new JLabel("Category: " + g.getCategory());
                        removableGroceryItemContainer.add(removableProductCategory);
                        modifiableGroceryItemContainer.add(productCategory);

                        JLabel productPrice = new JLabel("Price: $" + g.getPrice());
                        removableGroceryItemContainer.add(removableProductPrice);
                        modifiableGroceryItemContainer.add(productPrice);

                        JLabel productQuantityAvailable = new JLabel("Quantity Avaialble: " + g.getQuantityAvailable());
                        JLabel originalProductQuantityAvailable = new JLabel("Original Quantity Avaialble: " + g.getQuantityAvailable());
                        removableGroceryItemContainer.add(productQuantityAvailable);
                        modifiableGroceryItemContainer.add(originalProductQuantityAvailable);

                        JPanel removeItemPanel = new JPanel();
                        JPanel modifyItemPanel = new JPanel();
                        JButton removeGroceryItemOptionButton = new JButton("Remove " + g.getProductName());

                        JTextField modifyItemQuantityTextField = new JTextField("" + g.getQuantityAvailable(), 3);
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
                    
                    this.setVisible(false);
                    AdminShopView view = data.getAdminShopView();
                    view.getWelcomeMessage().setText(data.getUserAccount().getUsername() + ", you are ready to shop!");
                    view.setAllAddItemButtons(addButtons);
                    view.setAllMinusButtons(minusButtons);
                    view.setAllPlusButtons(plusButtons);
                    view.setAllQuantityTextFields(quantityTextFields);
                    view.getGroceryItemDisplayPanel().setViewportView(GroceryItemDisplayPanel);
                    view.getRemoveGroceryItemDisplayPanel().setViewportView(removableGroceryItemsDisplayPanel);
                    view.getModifiableGroceryItemDisplayPanel().setViewportView(modifiableGroceryItemsDisplayPanel);
                    view.setAllRemoveItemButtons(removeButtons);
                    view.setAllModifyItemQuantityButtons(modifyItemQuantityButtons);
                    view.setAllModifyItemQuantityTextFields(modifyItemQuantityTextFields);
                    if(data.getUserAccount().getUserCart().getItemsAdded().isEmpty())
                    {
                        view.getCheckoutButton().setEnabled(false);
                    }
                    
                    AdminShopModel model = data.getAdminShopModel();
                    model.data = data;
                    AdminShopController controller = new AdminShopController(view, model);
                    model.addObserver(view);
                    
                    view.DisplayAdminShopOptions();
                

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
     * @return the pickupLabel
     */
    public JLabel getPickupLabel() {
        return pickupLabel;
    }

    /**
     * @param pickupLabel the pickupLabel to set
     */
    public void setPickupLabel(JLabel pickupLabel) {
        this.pickupLabel = pickupLabel;
    }

    /**
     * @return the deliveryLabel
     */
    public JLabel getDeliveryLabel() {
        return deliveryLabel;
    }

    /**
     * @param deliveryLabel the deliveryLabel to set
     */
    public void setDeliveryLabel(JLabel deliveryLabel) {
        this.deliveryLabel = deliveryLabel;
    }

    /**
     * @return the prickupCheckBox
     */
    public JCheckBox getPickupCheckBox() {
        return pickupCheckBox;
    }

    /**
     * @param pickupCheckBox the prickupCheckBox to set
     */
    public void setPickupCheckBox(JCheckBox pickupCheckBox) {
        this.pickupCheckBox = pickupCheckBox;
    }

    /**
     * @return the deliveryCheckBox
     */
    public JCheckBox getDeliveryCheckBox() {
        return deliveryCheckBox;
    }

    /**
     * @param deliveryCheckBox the deliveryCheckBox to set
     */
    public void setDeliveryCheckBox(JCheckBox deliveryCheckBox) {
        this.deliveryCheckBox = deliveryCheckBox;
    }

    /**
     * @return the cardnumberLabel
     */
    public JLabel getCardnumberLabel() {
        return cardnumberLabel;
    }

    /**
     * @param cardnumberLabel the cardnumberLabel to set
     */
    public void setCardnumberLabel(JLabel cardnumberLabel) {
        this.cardnumberLabel = cardnumberLabel;
    }

    /**
     * @return the cardholderNameLabel
     */
    public JLabel getCardholderNameLabel() {
        return cardholderNameLabel;
    }

    /**
     * @param cardholderNameLabel the cardholderNameLabel to set
     */
    public void setCardholderNameLabel(JLabel cardholderNameLabel) {
        this.cardholderNameLabel = cardholderNameLabel;
    }

    /**
     * @return the cardNumberTextField
     */
    public JTextField getCardNumberTextField() {
        return cardNumberTextField;
    }

    /**
     * @param cardNumberTextField the cardNumberTextField to set
     */
    public void setCardNumberTextField(JTextField cardNumberTextField) {
        this.cardNumberTextField = cardNumberTextField;
    }

    /**
     * @return the cardholderNameTextField
     */
    public JTextField getCardholderNameTextField() {
        return cardholderNameTextField;
    }

    /**
     * @param cardholderNameTextField the cardholderNameTextField to set
     */
    public void setCardholderNameTextField(JTextField cardholderNameTextField) {
        this.cardholderNameTextField = cardholderNameTextField;
    }

    /**
     * @return the purchaseButton
     */
    public JButton getPurchaseButton() {
        return purchaseButton;
    }

    /**
     * @param purchaseButton the purchaseButton to set
     */
    public void setPurchaseButton(JButton purchaseButton) {
        this.purchaseButton = purchaseButton;
    }

    /**
     * @return the cardExpiryMonthCombobox
     */
    public JComboBox getCardExpiryMonthCombobox() {
        return cardExpiryMonthCombobox;
    }

    /**
     * @param cardExpiryMonthCombobox the cardExpiryMonthCombobox to set
     */
    public void setCardExpiryMonthCombobox(JComboBox cardExpiryMonthCombobox) {
        this.cardExpiryMonthCombobox = cardExpiryMonthCombobox;
    }

    /**
     * @return the cardYearExpiryCombobox
     */
    public JComboBox getCardExpiryYearCombobox() {
        return cardExpiryYearCombobox;
    }

    /**
     * @param cardExpiryYearCombobox the cardYearExpiryCombobox to set
     */
    public void setCardExpiryYearCombobox(JComboBox cardExpiryYearCombobox) {
        this.cardExpiryYearCombobox = cardExpiryYearCombobox;
    }

    /**
     * @return the cardExpiryMonthLabel
     */
    public JLabel getCardExpiryMonthLabel() {
        return cardExpiryMonthLabel;
    }

    /**
     * @param cardExpiryMonthLabel the cardExpiryMonthLabel to set
     */
    public void setCardExpiryMonthLabel(JLabel cardExpiryMonthLabel) {
        this.cardExpiryMonthLabel = cardExpiryMonthLabel;
    }

    /**
     * @return the cardExpiryYearLabel
     */
    public JLabel getCardExpiryYearLabel() {
        return cardExpiryYearLabel;
    }

    /**
     * @param cardExpiryYearLabel the cardExpiryYearLabel to set
     */
    public void setCardExpiryYearLabel(JLabel cardExpiryYearLabel) {
        this.cardExpiryYearLabel = cardExpiryYearLabel;
    }

    /**
     * @return the cardCVCLabel
     */
    public JLabel getCardCVCLabel() {
        return cardCVCLabel;
    }

    /**
     * @param cardCVCLabel the cardCVCLabel to set
     */
    public void setCardCVCLabel(JLabel cardCVCLabel) {
        this.cardCVCLabel = cardCVCLabel;
    }

    /**
     * @return the cardCVCTextField
     */
    public JTextField getCardCVCTextField() {
        return cardCVCTextField;
    }

    /**
     * @param cardCVCTextField the cardCVCTextField to set
     */
    public void setCardCVCTextField(JTextField cardCVCTextField) {
        this.cardCVCTextField = cardCVCTextField;
    }

    /**
     * @return the purchaseInformationPanel
     */
    public JPanel getPurchaseInformationPanel() {
        return purchaseInformationPanel;
    }

    /**
     * @param purchaseInformationPanel the purchaseInformationPanel to set
     */
    public void setPurchaseInformationPanel(JPanel purchaseInformationPanel) {
        this.purchaseInformationPanel = purchaseInformationPanel;
    }

    /**
     * @return the cartTotalLabel
     */
    public JLabel getCartTotalLabel() {
        return cartTotalLabel;
    }

    /**
     * @param cartTotalLabel the cartTotalLabel to set
     */
    public void setCartTotalLabel(JLabel cartTotalLabel) {
        this.cartTotalLabel = cartTotalLabel;
    }

}
