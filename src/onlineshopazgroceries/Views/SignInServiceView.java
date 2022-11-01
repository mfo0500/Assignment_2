/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries.Views;

import onlineshopazgroceries.Views.CustomerShopView;
import onlineshopazgroceries.Controllers.CustomerShopController;
import onlineshopazgroceries.Controllers.AdminShopController;
import onlineshopazgroceries.Views.AdminShopView;
import onlineshopazgroceries.Models.CustomerShopModel;
import onlineshopazgroceries.Models.AdminShopModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import onlineshopazgroceries.GroceryItems;
import onlineshopazgroceries.ShopData;

/**
 *
 * @author krist
 */
public class SignInServiceView extends JFrame implements Observer {

    // componants needed for sign in page 
    private JPanel titlePanel = new JPanel();
    private JLabel storeTitle = new JLabel("WELCOME TO THE AZGROCERIES ONLINE STORE", SwingConstants.CENTER);
    private JLabel usernameLabel = new JLabel("Enter Username:", SwingConstants.CENTER);
    private JLabel passwordLabel = new JLabel("Enter Password:", SwingConstants.CENTER);
    private JLabel employeeIDLabel = new JLabel("Enter Employee ID:", SwingConstants.CENTER);
    private JTextField usernameTextField = new JTextField();
    private JTextField passwordTextField = new JTextField();
    private JTextField employeeIDTextField = new JTextField();
    private JButton signInButton = new JButton("Sign in");
    private JButton createAccountButton = new JButton("Create Account");
    private JButton continueAsGuestButton = new JButton("Continue as Guest");
    private JPanel signInPanel = new JPanel();

    private JLabel loginStatus;

    private JLabel welcomeMessage;

    private JButton createCustomerAccountButton = new JButton("Create Customer Account");
    private JButton createAdminAccountButton = new JButton("Create Admin Account");

    private JLabel createUsernameLabel = new JLabel("Create Username: ", SwingConstants.CENTER);
    private JLabel createPasswordLabel = new JLabel("Create Password: ", SwingConstants.CENTER);
    private JLabel confirmPasswordLabel = new JLabel("Confirm Password: ", SwingConstants.CENTER);
    private JLabel createEmployeeIDLabel = new JLabel("Create Employee ID: ", SwingConstants.CENTER);
    private JTextField createUsernameTextField = new JTextField();
    private JTextField createPasswordTextField = new JTextField();
    private JTextField confirmPasswordTextField = new JTextField();
    private JTextField createEmployeeIDTextField = new JTextField();
    private JButton officialCreateAdminAccountButton = new JButton("Create Account");
    private JButton officialCreateCustomerAccountButton = new JButton("Create Account");

    private JLabel createAccountStatus = new JLabel("Press \"Create Account\" to continue.", SwingConstants.CENTER);

    private JButton signInButton_afterAccountCreation = new JButton("Go to Sign In page");

    public SignInServiceView() {
        

        setTitle("AZGroceries Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getStoreTitle().setFont(new Font("Times New Roman", Font.BOLD, 30));

        this.DisplaySigninService();

    }

    public void DisplaySigninService() {
        this.setSize(800, 200);
Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        double halfWidth = size.getWidth()/2;
        double halfHeight = size.getHeight()/2;
        int halfWidthInteger = (int)halfWidth;
        int halfHeightInteger = (int)halfHeight;
        this.setLocation(halfWidthInteger - 400, halfHeightInteger - 100);


     //   this.setLocationRelativeTo(null);
        this.getContentPane().removeAll();
        getTitlePanel().setBackground(Color.CYAN.brighter());
        getTitlePanel().add(getStoreTitle());
        add(getTitlePanel(), BorderLayout.NORTH);
        getSignInPanel().setLayout(new GridLayout(3, 2));

        getSignInPanel().add(getUsernameLabel());
        getSignInPanel().add(getUsernameTextField());
        getSignInPanel().add(getPasswordLabel());
        getSignInPanel().add(getPasswordTextField());
        getSignInPanel().add(getEmployeeIDLabel());
        getSignInPanel().add(getEmployeeIDTextField());
        add(getSignInPanel(), BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.add(getSignInButton());
        southPanel.add(getCreateAccountButton());
        southPanel.add(getContinueAsGuestButton());

        setLoginStatus(new JLabel("Press \"Sign in\" to continue.", SwingConstants.CENTER));

        southPanel.add(getLoginStatus());
        add(southPanel, BorderLayout.SOUTH);
        setVisible(true);
        revalidate();
        repaint();
    }

    public void DisplayGuestShop() {
        this.getContentPane().removeAll();
        getTitlePanel().setLayout(new GridLayout(2, 1));
        getTitlePanel().add(getStoreTitle());
        getTitlePanel().add(getWelcomeMessage());
        add(getTitlePanel(), BorderLayout.NORTH);

        //   add(getGroceryItemDisplayPanel(), BorderLayout.SOUTH);
        setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void DisplayCreateAccountOptionsPage() {
        
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        double halfWidth = size.getWidth()/2;
        double halfHeight = size.getHeight()/2;
        int halfWidthInteger = (int)halfWidth;
        int halfHeightInteger = (int)halfHeight;
        this.setLocation(halfWidthInteger - 800, halfHeightInteger - 200);
        
        this.getContentPane().removeAll();
        getTitlePanel().add(getStoreTitle());
        add(getTitlePanel(), BorderLayout.NORTH);

        JLabel confirmAccountType = new JLabel("Would you like to create a Admin Account or Customer Account?");
        JPanel choicePanel = new JPanel();
        choicePanel.add(confirmAccountType);
        choicePanel.add(getCreateCustomerAccountButton());
        choicePanel.add(getCreateAdminAccountButton());

        add(choicePanel, BorderLayout.CENTER);
        setVisible(true);
        this.revalidate();
        this.repaint();

        this.revalidate();
        this.repaint();
    }

    public void DisplayCreateAdminAccountPage() {
        this.getContentPane().removeAll();

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        double halfWidth = size.getWidth()/2;
        double halfHeight = size.getHeight()/2;
        int halfWidthInteger = (int)halfWidth;
        int halfHeightInteger = (int)halfHeight;
        this.setLocation(halfWidthInteger - 400, halfHeightInteger - 125);
        this.setSize(800, 250);

        getTitlePanel().add(getStoreTitle());
        add(getTitlePanel(), BorderLayout.NORTH);

        //How to organize the components in JPannel: 
        //1. set layout
        //2. add components by giving the position
        JPanel createAdminAccountPanel = new JPanel();
        createAdminAccountPanel.setLayout(new GridLayout(4, 2));

        createAdminAccountPanel.add(getCreateUsernameLabel());
        createAdminAccountPanel.add(getCreateUsernameTextField());
        createAdminAccountPanel.add(getCreatePasswordLabel());
        createAdminAccountPanel.add(getCreatePasswordTextField());
        createAdminAccountPanel.add(getConfirmPasswordLabel());
        createAdminAccountPanel.add(getConfirmPasswordTextField());
        createAdminAccountPanel.add(getCreateEmployeeIDLabel());
        createAdminAccountPanel.add(getCreateEmployeeIDTextField());
        add(createAdminAccountPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        JPanel createAccountButtonContainer = new JPanel();

        createAccountButtonContainer.add(getOfficialCreateAdminAccountButton());

        southPanel.setLayout(new GridLayout(2, 1));
        southPanel.add(createAccountButtonContainer);
        southPanel.add(getCreateAccountStatus());

        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void DisplayCreateCustomerAccountPage() {
        
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        double halfWidth = size.getWidth()/2;
        double halfHeight = size.getHeight()/2;
        int halfWidthInteger = (int)halfWidth;
        int halfHeightInteger = (int)halfHeight;
        this.setLocation(halfWidthInteger - 400 , halfHeightInteger - 125);
        
        this.getContentPane().removeAll();
        this.setSize(800, 225);
        getTitlePanel().add(getStoreTitle());
        add(getTitlePanel(), BorderLayout.NORTH);

        //How to organize the components in JPannel: 
        //1. set layout
        //2. add components by giving the position
        JPanel createCustomerAccountPanel = new JPanel();
        createCustomerAccountPanel.setLayout(new GridLayout(3, 2));

        createCustomerAccountPanel.add(getCreateUsernameLabel());
        createCustomerAccountPanel.add(getCreateUsernameTextField());
        createCustomerAccountPanel.add(getCreatePasswordLabel());
        createCustomerAccountPanel.add(getCreatePasswordTextField());
        createCustomerAccountPanel.add(getConfirmPasswordLabel());
        createCustomerAccountPanel.add(getConfirmPasswordTextField());
        add(createCustomerAccountPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        JPanel buttonContainer = new JPanel();

        buttonContainer.add(getOfficialCreateCustomerAccountButton());

        southPanel.setLayout(new GridLayout(2, 1));
        //  getOfficialCreateCustomerAccountButton().setSize(50, 10);
        southPanel.add(buttonContainer);

        southPanel.add(getCreateAccountStatus());

        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void DisplayAccountSucessfullyCreatedPage(String accountType) {
        this.getContentPane().removeAll();
        
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        double halfWidth = size.getWidth()/2;
        double halfHeight = size.getHeight()/2;
        int halfWidthInteger = (int)halfWidth;
        int halfHeightInteger = (int)halfHeight;
        this.setLocation(halfWidthInteger, halfHeightInteger);
        
        getTitlePanel().add(getStoreTitle());
        add(getTitlePanel(), BorderLayout.NORTH);

        JPanel accountCreationSucessfulPanel = new JPanel();

        JLabel successfulAccountCreationMessage = new JLabel("Your AZGroceries " + accountType + " Account has sucessfully been created!", SwingConstants.CENTER);

        accountCreationSucessfulPanel.add(successfulAccountCreationMessage);

        add(accountCreationSucessfulPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();

        southPanel.add(getSignInButton_afterAccountCreation());

        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
        this.revalidate();
        this.repaint();
    }

    public void addActionListener(ActionListener actionListener) {
        getSignInButton().addActionListener(actionListener);
        getCreateAccountButton().addActionListener(actionListener);
        getContinueAsGuestButton().addActionListener(actionListener);

        getCreateCustomerAccountButton().addActionListener(actionListener);
        getCreateAdminAccountButton().addActionListener(actionListener);

        getOfficialCreateAdminAccountButton().addActionListener(actionListener);
        getOfficialCreateCustomerAccountButton().addActionListener(actionListener);

        getSignInButton_afterAccountCreation().addActionListener(actionListener);
    }

    @Override
    public void update(Observable o, Object arg) {
        ShopData data = (ShopData) arg;
        if (!data.isSignedIn()) // if the user types incorrect username or password
        {
            this.getUsernameTextField().setText("");
            this.getPasswordTextField().setText("");
            this.getEmployeeIDTextField().setText("");

            this.getLoginStatus().setText("Incorrect username or password.");

        }
        if (data.isCreateAccountRequested()) {
            this.DisplayCreateAccountOptionsPage();
            if (data.isCreateCustomerAccountRequested()) {
                this.DisplayCreateCustomerAccountPage();
            }
            if (data.isCreateAdminAccountRequested()) {
                this.DisplayCreateAdminAccountPage();
            }

        }
        if (!data.isAccountCreated() && !data.neverFailedCreatingAccount()) {
            this.createAccountStatus.setText(data.getReasonAccountCreationFailed() + " Press \"Create Account \" to try again.");
            // confirm acccount is created sucessfully
            // show sign in button for user to sign in again
        }
        if (data.isAccountCreated()) {
            if (data.isCreateAdminAccountRequested()) {
                this.DisplayAccountSucessfullyCreatedPage("Admin");
            }
            if (data.isCreateCustomerAccountRequested()) {
                this.DisplayAccountSucessfullyCreatedPage("Customer");
            }

            // confirm acccount is created sucessfully
            // show sign in button for user to sign in again
        }
        if (data.isSignedIn()) {

            this.setVisible(false);
            if (data.getUserAccount() != null) {
                setWelcomeMessage(new JLabel("Welcome, " + data.getUserAccount().getUsername(), SwingConstants.CENTER));
                JPanel GroceryItemDisplayPanel = new JPanel();
                ArrayList<JButton> addButtons = new ArrayList<>();
                ArrayList<JButton> plusButtons = new ArrayList<>();
                ArrayList<JButton> minusButtons = new ArrayList<>();
                ArrayList<JTextField> quantityTextFields = new ArrayList<>();
                if (data.getListOfGroceries().getGroceries().isEmpty()) {

                } else {

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
                }

                if (data.getUserAccount().getRole().equals("Customer")) {

                    //        this.DisplayCustomerShop();
                    CustomerShopView view = new CustomerShopView();
                     view.data = data;
                    view.getWelcomeMessage().setText(getWelcomeMessage().getText() + ", you are ready to shop!");
                    view.setAllAddItemButtons(addButtons);
                    view.setAllMinusButtons(minusButtons);
                    view.setAllPlusButtons(plusButtons);
                    view.setAllQuantityTextFields(quantityTextFields);
                    view.getGroceryItemDisplayPanel().setViewportView(GroceryItemDisplayPanel);
                    if(data.getUserAccount().getUserCart().getItemsAdded().isEmpty())
                    {
                        view.getCheckoutButton().setEnabled(false);
                    }

                    CustomerShopModel model = new CustomerShopModel();
                    model.data = data;
                    CustomerShopController controller = new CustomerShopController(view, model);

                    model.addObserver(view);
                    data.setCustomerShopController(controller);
                    data.setCustomerShopModel(model);
                    data.setCustomerShopview(view);

                }
                if (data.getUserAccount().getRole().equals("Admin")) {
                    

                    JPanel removableGroceryItemsDisplayPanel = new JPanel();
                    JPanel modifiableGroceryItemsDisplayPanel = new JPanel();
                    ArrayList<JButton> removeButtons = new ArrayList<>();
                    ArrayList<JButton> modifyItemQuantityButtons = new ArrayList<>();
                    ArrayList<JTextField> modifyItemQuantityTextFields = new ArrayList<>();

                    int numberOfRows = (int) data.getListOfGroceries().getGroceries().size() / 3 + 1;
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
                        
                        JLabel productName = new JLabel("Product Name : " + g.getProductName());
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
                    
                    AdminShopView view = new AdminShopView();
                    view.data = data;
                    view.getWelcomeMessage().setText(getWelcomeMessage().getText() + ", you are ready to shop!");
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
                    
                    AdminShopModel model = new AdminShopModel();
                    model.data = data;
                    AdminShopController controller = new AdminShopController(view, model);

                    model.addObserver(view);
                    
                    data.setAdminShopController(controller);
                    data.setAdminShopModel(model);
                    data.setAdminShopView(view);
                }
            } else {
                setWelcomeMessage(new JLabel("Welcome, User", SwingConstants.CENTER));

                this.DisplayGuestShop();
            }
        }
    }

    /**
     * @return the usernameTextField
     */
    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    /**
     * @param usernameTextField the usernameTextField to set
     */
    public void setUsernameTextField(JTextField usernameTextField) {
        this.usernameTextField = usernameTextField;
    }

    /**
     * @return the passwordTextField
     */
    public JTextField getPasswordTextField() {
        return passwordTextField;
    }

    /**
     * @param passwordTextField the passwordTextField to set
     */
    public void setPasswordTextField(JTextField passwordTextField) {
        this.passwordTextField = passwordTextField;
    }

    /**
     * @return the employeeIDTextField
     */
    public JTextField getEmployeeIDTextField() {
        return employeeIDTextField;
    }

    /**
     * @param employeeIDTextField the employeeIDTextField to set
     */
    public void setEmployeeIDTextField(JTextField employeeIDTextField) {
        this.employeeIDTextField = employeeIDTextField;
    }

    /**
     * @return the signInButton
     */
    public JButton getSignInButton() {
        return signInButton;
    }

    /**
     * @param signInButton the signInButton to set
     */
    public void setSignInButton(JButton signInButton) {
        this.signInButton = signInButton;
    }

    /**
     * @return the loginStatus
     */
    public JLabel getLoginStatus() {
        return loginStatus;
    }

    /**
     * @param loginStatus the loginStatus to set
     */
    public void setLoginStatus(JLabel loginStatus) {
        this.loginStatus = loginStatus;
    }

    /**
     * @return the usernameLabel
     */
    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    /**
     * @param usernameLabel the usernameLabel to set
     */
    public void setUsernameLabel(JLabel usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    /**
     * @return the passwordLabel
     */
    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    /**
     * @param passwordLabel the passwordLabel to set
     */
    public void setPasswordLabel(JLabel passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    /**
     * @return the employeeIDLabel
     */
    public JLabel getEmployeeIDLabel() {
        return employeeIDLabel;
    }

    /**
     * @param employeeIDLabel the employeeIDLabel to set
     */
    public void setEmployeeIDLabel(JLabel employeeIDLabel) {
        this.employeeIDLabel = employeeIDLabel;
    }

    /**
     * @return the signInPanel
     */
    public JPanel getSignInPanel() {
        return signInPanel;
    }

    /**
     * @param signInPanel the signInPanel to set
     */
    public void setSignInPanel(JPanel signInPanel) {
        this.signInPanel = signInPanel;
    }

    /**
     * @return the title
     */
    public JLabel getStoreTitle() {
        return storeTitle;
    }

    /**
     * @param title the title to set
     */
    public void setStoreTitle(JLabel title) {
        this.storeTitle = title;
    }

    /**
     * @return the createAccount
     */
    public JButton getCreateAccountButton() {
        return createAccountButton;
    }

    /**
     * @param createAccountButton the createAccount to set
     */
    public void setCreateAccountButton(JButton createAccountButton) {
        this.createAccountButton = createAccountButton;
    }

    /**
     * @return the continueAsGuestButton
     */
    public JButton getContinueAsGuestButton() {
        return continueAsGuestButton;
    }

    /**
     * @param continueAsGuestButton the continueAsGuestButton to set
     */
    public void setContinueAsGuestButton(JButton continueAsGuestButton) {
        this.continueAsGuestButton = continueAsGuestButton;
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
     * @return the createCustomerAccountButton
     */
    public JButton getCreateCustomerAccountButton() {
        return createCustomerAccountButton;
    }

    /**
     * @param createCustomerAccountButton the createCustomerAccountButton to set
     */
    public void setCreateCustomerAccountButton(JButton createCustomerAccountButton) {
        this.createCustomerAccountButton = createCustomerAccountButton;
    }

    /**
     * @return the createAdminAccountButton
     */
    public JButton getCreateAdminAccountButton() {
        return createAdminAccountButton;
    }

    /**
     * @param createAdminAccountButton the createAdminAccountButton to set
     */
    public void setCreateAdminAccountButton(JButton createAdminAccountButton) {
        this.createAdminAccountButton = createAdminAccountButton;
    }

    /**
     * @return the createUsernameLabel
     */
    public JLabel getCreateUsernameLabel() {
        return createUsernameLabel;
    }

    /**
     * @param createUsernameLabel the createUsernameLabel to set
     */
    public void setCreateUsernameLabel(JLabel createUsernameLabel) {
        this.createUsernameLabel = createUsernameLabel;
    }

    /**
     * @return the createPasswordLabel
     */
    public JLabel getCreatePasswordLabel() {
        return createPasswordLabel;
    }

    /**
     * @param createPasswordLabel the createPasswordLabel to set
     */
    public void setCreatePasswordLabel(JLabel createPasswordLabel) {
        this.createPasswordLabel = createPasswordLabel;
    }

    /**
     * @return the createConfirmPasswordLabel
     */
    public JLabel getConfirmPasswordLabel() {
        return confirmPasswordLabel;
    }

    /**
     * @param confirmPasswordLabel the createConfirmPasswordLabel to set
     */
    public void setConfirmPasswordLabel(JLabel confirmPasswordLabel) {
        this.confirmPasswordLabel = confirmPasswordLabel;
    }

    /**
     * @return the createEmployeeIDLabel
     */
    public JLabel getCreateEmployeeIDLabel() {
        return createEmployeeIDLabel;
    }

    /**
     * @param createEmployeeIDLabel the createEmployeeIDLabel to set
     */
    public void setCreateEmployeeIDLabel(JLabel createEmployeeIDLabel) {
        this.createEmployeeIDLabel = createEmployeeIDLabel;
    }

    /**
     * @return the createUsernameTextField
     */
    public JTextField getCreateUsernameTextField() {
        return createUsernameTextField;
    }

    /**
     * @param createUsernameTextField the createUsernameTextField to set
     */
    public void setCreateUsernameTextField(JTextField createUsernameTextField) {
        this.createUsernameTextField = createUsernameTextField;
    }

    /**
     * @return the createPasswordTextField
     */
    public JTextField getCreatePasswordTextField() {
        return createPasswordTextField;
    }

    /**
     * @param createPasswordTextField the createPasswordTextField to set
     */
    public void setCreatePasswordTextField(JTextField createPasswordTextField) {
        this.createPasswordTextField = createPasswordTextField;
    }

    /**
     * @return the createConfirmPasswordTextField
     */
    public JTextField getConfirmPasswordTextField() {
        return confirmPasswordTextField;
    }

    /**
     * @param confirmPasswordTextField the createConfirmPasswordTextField to set
     */
    public void setConfirmPasswordTextField(JTextField confirmPasswordTextField) {
        this.confirmPasswordTextField = confirmPasswordTextField;
    }

    /**
     * @return the createEmployeeIDTextField
     */
    public JTextField getCreateEmployeeIDTextField() {
        return createEmployeeIDTextField;
    }

    /**
     * @param createEmployeeIDTextField the createEmployeeIDTextField to set
     */
    public void setCreateEmployeeIDTextField(JTextField createEmployeeIDTextField) {
        this.createEmployeeIDTextField = createEmployeeIDTextField;
    }

    /**
     * @return the officialCreateAdminAccountButton
     */
    public JButton getOfficialCreateAdminAccountButton() {
        return officialCreateAdminAccountButton;
    }

    /**
     * @param officialCreateAdminAccountButton the
     * officialCreateAdminAccountButton to set
     */
    public void setOfficialCreateAdminAccountButton(JButton officialCreateAdminAccountButton) {
        this.officialCreateAdminAccountButton = officialCreateAdminAccountButton;
    }

    /**
     * @return the officialCreateCustomerAccountButton
     */
    public JButton getOfficialCreateCustomerAccountButton() {
        return officialCreateCustomerAccountButton;
    }

    /**
     * @param officialCreateCustomerAccountButton the
     * officialCreateCustomerAccountButton to set
     */
    public void setOfficialCreateCustomerAccountButton(JButton officialCreateCustomerAccountButton) {
        this.officialCreateCustomerAccountButton = officialCreateCustomerAccountButton;
    }

    /**
     * @return the createAccountStatus
     */
    public JLabel getCreateAccountStatus() {
        return createAccountStatus;
    }

    /**
     * @param createAccountStatus the createAccountStatus to set
     */
    public void setCreateAccountStatus(JLabel createAccountStatus) {
        this.createAccountStatus = createAccountStatus;
    }

    /**
     * @return the signInButton_afterAccountCreation
     */
    public JButton getSignInButton_afterAccountCreation() {
        return signInButton_afterAccountCreation;
    }

    /**
     * @param signInButton_afterAccountCreation the
     * signInButton_afterAccountCreation to set
     */
    public void setSignInButton_afterAccountCreation(JButton signInButton_afterAccountCreation) {
        this.signInButton_afterAccountCreation = signInButton_afterAccountCreation;
    }

}
