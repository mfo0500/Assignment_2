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
public class CartView extends JFrame implements Observer {

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

    private JPanel titlePanel = new JPanel();
    private JLabel storeTitle = new JLabel("WELCOME TO THE AZGROCERIES ONLINE STORE", SwingConstants.CENTER);
    private JScrollPane cartItemPanel = new JScrollPane();
    private ArrayList<JButton> allRemoveProductButtons = new ArrayList<>();
    private ArrayList<JButton> allMinusButtons = new ArrayList<>();
    private ArrayList<JButton> allPlusButtons = new ArrayList<>();
    private ArrayList<JTextField> allQuantityToPurchaseTextFields = new ArrayList<>();
    private ArrayList<JLabel> allUpdatedCartPurchaseQuantityLabels = new ArrayList<>();
    private ArrayList<JLabel> allUpdatedCartPurchasePriceForQuantityLabels = new ArrayList<>();
    private ArrayList<JLabel> allUpdatedCartTotalLabels = new ArrayList<>();
     private ArrayList<JButton> allSaveButtons = new ArrayList<>();
    private JLabel viewingCartMessage = new JLabel("", SwingConstants.CENTER);
    private JLabel cartTotalLabel = new JLabel("", SwingConstants.CENTER);
    private JLabel removeItemSuccessMessage = new JLabel("", SwingConstants.CENTER);
    private JLabel updateItemStatus = new JLabel("", SwingConstants.CENTER);
    
    private JButton returnBackToMainMenuButton = new JButton("Return Back To Main Menu");

    public CartView() {
        setTitle("AZGroceries Admin Store");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getStoreTitle().setFont(new Font("Times New Roman", Font.BOLD, 30));

        this.DisplayCart();

    }

    public void DisplayCart() {

       Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        double halfWidth = size.getWidth()/2;
        int halfWidthInteger = (int)halfWidth;
        this.setLocation(halfWidthInteger - 400, 0);
        
        this.setSize(800, 740);
        this.getContentPane().removeAll();
        getTitlePanel().setBackground(Color.CYAN.brighter());
        getTitlePanel().removeAll();
        getTitlePanel().setLayout(new GridLayout(4, 1));
        getTitlePanel().add(getStoreTitle());
        getTitlePanel().add(getViewingCartMessage());
        getTitlePanel().add(getCartTotalLabel());
        
        JPanel backButtonPanelAndMessagePanel = new JPanel();
        
        
        backButtonPanelAndMessagePanel.add(getUpdateItemStatus());
        backButtonPanelAndMessagePanel.add(getReturnBackToMainMenuButton());
        
        getTitlePanel().add(backButtonPanelAndMessagePanel);
        add(getTitlePanel(), BorderLayout.NORTH);

        add(getCartItemPanel(), BorderLayout.CENTER);

        add(new JPanel(), BorderLayout.SOUTH);

        setVisible(true);
        this.revalidate();
        this.repaint();
    }
    
     public void displayCartRemovedSuccessfully() {
        this.getContentPane().removeAll();
        this.setSize(800, 275);
        this.setLocationRelativeTo(null);
        getTitlePanel().removeAll();
        getTitlePanel().setLayout(new GridLayout(2, 1));
        getTitlePanel().add(getStoreTitle());

        JPanel messagePanel = new JPanel();
        messagePanel.add(getRemoveItemSuccessMessage());

        getTitlePanel().add(messagePanel);
        add(getTitlePanel(), BorderLayout.NORTH);
        
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

        for (JButton removeProductButton : getAllRemoveProductButtons()) {
            removeProductButton.addActionListener(actionListener);

        }
        for (JButton minusButton : getAllMinusButtons()) {
            minusButton.addActionListener(actionListener);

        }
        for (JButton plusButton : getAllPlusButtons()) {
            plusButton.addActionListener(actionListener);

        }
        for(JButton saveButton : getAllSaveButtons()){
            saveButton.addActionListener(actionListener);
        }
        getReturnBackToMainMenuButton().addActionListener(actionListener);

    }

    @Override
    public void update(Observable o, Object arg) {

        ShopData data = (ShopData) arg;
        if (data.isQuantityTextFieldUpdated()) {
            this.getAllQuantityToPurchaseTextFields().get(data.getNumberOfUpdatedQuantityTextField()).setText("" + data.getUpdatedQuantityTextFieldValue());
        }
        if(data.isItemRemovedFromCart()) {
            getRemoveItemSuccessMessage().setText("Your Item has been removed sucessfully!");
            this.displayCartRemovedSuccessfully();

        }
        if(data.isCartUpdated())
        {
            this.getUpdateItemStatus().setText("Your Item has been updated successfully");
            // change numbers 
            GroceryItems groceryItemUpdated = data.getCartItemRequestedForUpdate();
             int quantityOfProductPurchased = data.getUserAccount().getUserCart().getItemsAdded().get(groceryItemUpdated);
             
             this.getAllUpdatedCartPurchasePriceForQuantityLabels().get(data.getUpdatedCartItemQuantityPurchasedLabelNumber()).setText("Price for " + quantityOfProductPurchased + ": $" + quantityOfProductPurchased * groceryItemUpdated.getPrice());
             this.getCartTotalLabel().setText("Cart Total: $" + data.getUserAccount().getUserCart().getTotal());
             this.getAllUpdatedCartPurchaseQuantityLabels().get(data.getUpdatedCartItemQuantityPurchasedLabelNumber()).setText("Quantity of Product added to Cart: " + quantityOfProductPurchased);
            
            this.DisplayCart();
            
        }
        if(!data.isCartUpdated()&&!data.isItemRemovedFromCart())
        {
            this.getUpdateItemStatus().setText(data.getReasonCartUpdateFailed());
            this.DisplayCart();
        }
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
                view.getWelcomeMessage().setText(data.getUserAccount().getUsername() + ", you are ready to shop!");
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
     * @return the cartItemPanel
     */
    public JScrollPane getCartItemPanel() {
        return cartItemPanel;
    }

    /**
     * @param cartItemPanel the cartItemPanel to set
     */
    public void setCartItemPanel(JScrollPane cartItemPanel) {
        this.cartItemPanel = cartItemPanel;
    }

    /**
     * @return the allRemoveProductButtons
     */
    public ArrayList<JButton> getAllRemoveProductButtons() {
        return allRemoveProductButtons;
    }

    /**
     * @param allRemoveProductButtons the allRemoveProductButtons to set
     */
    public void setAllRemoveProductButtons(ArrayList<JButton> allRemoveProductButtons) {
        this.allRemoveProductButtons = allRemoveProductButtons;
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
     * @return the viewingCartMessage
     */
    public JLabel getViewingCartMessage() {
        return viewingCartMessage;
    }

    /**
     * @param viewingCartMessage the viewingCartMessage to set
     */
    public void setViewingCartMessage(JLabel viewingCartMessage) {
        this.viewingCartMessage = viewingCartMessage;
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

    /**
     * @return the allQuantityToPurchaseTextFields
     */
    public ArrayList<JTextField> getAllQuantityToPurchaseTextFields() {
        return allQuantityToPurchaseTextFields;
    }

    /**
     * @param allQuantityToPurchaseTextFields the
     * allQuantityToPurchaseTextFields to set
     */
    public void setAllQuantityToPurchaseTextFields(ArrayList<JTextField> allQuantityToPurchaseTextFields) {
        this.allQuantityToPurchaseTextFields = allQuantityToPurchaseTextFields;
    }

    /**
     * @return the allSaveButtons
     */
    public ArrayList<JButton> getAllSaveButtons() {
        return allSaveButtons;
    }

    /**
     * @param allSaveButtons the allSaveButtons to set
     */
    public void setAllSaveButtons(ArrayList<JButton> allSaveButtons) {
        this.allSaveButtons = allSaveButtons;
    }

    /**
     * @return the updateItemStatus
     */
    public JLabel getUpdateItemStatus() {
        return updateItemStatus;
    }

    /**
     * @param updateItemStatus the updateItemStatus to set
     */
    public void setUpdateItemStatus(JLabel updateItemStatus) {
        this.updateItemStatus = updateItemStatus;
    }

    /**
     * @return the allUpdatedCartPurchaseQuantityLabels
     */
    public ArrayList<JLabel> getAllUpdatedCartPurchaseQuantityLabels() {
        return allUpdatedCartPurchaseQuantityLabels;
    }

    /**
     * @param allUpdatedCartPurchaseQuantityLabels the allUpdatedCartPurchaseQuantityLabels to set
     */
    public void setAllUpdatedCartPurchaseQuantityLabels(ArrayList<JLabel> allUpdatedCartPurchaseQuantityLabels) {
        this.allUpdatedCartPurchaseQuantityLabels = allUpdatedCartPurchaseQuantityLabels;
    }

    /**
     * @return the allUpdatedCartPurchasePriceForQuantityLabels
     */
    public ArrayList<JLabel> getAllUpdatedCartPurchasePriceForQuantityLabels() {
        return allUpdatedCartPurchasePriceForQuantityLabels;
    }

    /**
     * @param allUpdatedCartPurchasePriceForQuantityLabels the allUpdatedCartPurchasePriceForQuantityLabels to set
     */
    public void setAllUpdatedCartPurchasePriceForQuantityLabels(ArrayList<JLabel> allUpdatedCartPurchasePriceForQuantityLabels) {
        this.allUpdatedCartPurchasePriceForQuantityLabels = allUpdatedCartPurchasePriceForQuantityLabels;
    }

    /**
     * @return the allUpdatedCartTotalLabels
     */
    public ArrayList<JLabel> getAllUpdatedCartTotalLabels() {
        return allUpdatedCartTotalLabels;
    }

    /**
     * @param allUpdatedCartTotalLabels the allUpdatedCartTotalLabels to set
     */
    public void setAllUpdatedCartTotalLabels(ArrayList<JLabel> allUpdatedCartTotalLabels) {
        this.allUpdatedCartTotalLabels = allUpdatedCartTotalLabels;
    }

}
