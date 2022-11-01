/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries.Views;

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
public class OrderHistoryView extends JFrame implements Observer {

    private JButton returnBackToMainMenuButton = new JButton("Return Back To Main Menu");
    private JPanel titlePanel = new JPanel();
    private JLabel storeTitle = new JLabel("WELCOME TO THE AZGROCERIES ONLINE STORE", SwingConstants.CENTER);
    private JScrollPane orderHistoryPanel = new JScrollPane();
    private JLabel viewingOrderHistoryMessage = new JLabel("", SwingConstants.CENTER);

    public OrderHistoryView() {
        setTitle("AZGroceries Admin Store");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getStoreTitle().setFont(new Font("Times New Roman", Font.BOLD, 30));

        this.DisplayOrderHistory();
    }

    public void DisplayOrderHistory() {
        this.setSize(800, 740);
        this.getContentPane().removeAll();
        this.setLocationRelativeTo(null);
        getTitlePanel().setBackground(Color.CYAN.brighter());
        getTitlePanel().removeAll();
        getTitlePanel().setLayout(new GridLayout(3, 1));
        getTitlePanel().add(getStoreTitle());
        getTitlePanel().add(getViewingOrderHistoryMessage());

        JPanel backButtonPanel = new JPanel();

        backButtonPanel.add(getReturnBackToMainMenuButton());

        getTitlePanel().add(backButtonPanel);

        add(getTitlePanel(), BorderLayout.NORTH);

        add(getOrderHistoryPanel(), BorderLayout.CENTER);

        add(new JPanel(), BorderLayout.SOUTH);

        setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void addActionListener(ActionListener actionListener) {

        getReturnBackToMainMenuButton().addActionListener(actionListener);
    }

    @Override
    public void update(Observable o, Object arg) {
        ShopData data = (ShopData) arg;
        
        if (data.isMainMenuRequested()) {
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
     * @return the orderHistoryPanel
     */
    public JScrollPane getOrderHistoryPanel() {
        return orderHistoryPanel;
    }

    /**
     * @param orderHistoryPanel the orderHistoryPanel to set
     */
    public void setOrderHistoryPanel(JScrollPane orderHistoryPanel) {
        this.orderHistoryPanel = orderHistoryPanel;
    }

    /**
     * @return the viewingOrderHistoryMessage
     */
    public JLabel getViewingOrderHistoryMessage() {
        return viewingOrderHistoryMessage;
    }

    /**
     * @param viewingOrderHistoryMessage the viewingOrderHistoryMessage to set
     */
    public void setViewingOrderHistoryMessage(JLabel viewingOrderHistoryMessage) {
        this.viewingOrderHistoryMessage = viewingOrderHistoryMessage;
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
