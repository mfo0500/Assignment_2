/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

import java.util.ArrayList;
import java.util.InputMismatchException;
import onlineshopazgroceries.Model.ShopModel;
import java.util.Observable;

/**
 *
 * @author krist
 */
public class SignInServiceModel extends Observable implements IsValid {

    private String state = "";

    private ArrayList<AZGroceriesAccount> accounts;
    private ArrayList<AdminAccount> AdminAccounts;
    private String usernameCreated;
    private String passwordCreated;
    public ShopModel model;
    public ShopDatabase database;
    public ShopData data;

    // private 
    SignInServiceModel() {

        this.database = new ShopDatabase();
        this.database.databaseSetup();
        this.data = new ShopData();

    }

    public void signIn(String username, String password, String employeeID) {

        setState("sign in");

        this.data = this.database.checkLogin(username, password, employeeID);

        this.setChanged();
        this.notifyObservers(this.data);

    }

    public void createAccount() {
        data.setCreateAccountRequested(true); // throws an exception
        this.setChanged();
        this.notifyObservers(this.data);
    }

    public void checkCreatedAdminAccount(String createdUsername, String createdPassword, String confirmedPassword, String createdEmployeeID) {
        setState("Create username");
        if (!IsValid(createdUsername)) {
            
            data.setNeverFailedCreatingAccount(false);
            data.setAccountCreated(false);
            // invalid username 
            // provide option to sign in

        } else {
            setState("Create password");
            if (!IsValid(createdPassword) || !IsValid(confirmedPassword)) {
                
                data.setNeverFailedCreatingAccount(false);
                data.setAccountCreated(false);
                // password is not valid

            } else {
                if (!createdPassword.equals(confirmedPassword)) {
                    data.setReasonAccountCreationFailed(data.getReasonAccountCreationFailed()+"Passwords do not match.\n");
                    data.setNeverFailedCreatingAccount(false);
                    data.setAccountCreated(false);
                    
                    // passwords do not match 
                } else {
                    // differentiate between creating admin account and customer account, customers can have employee ID blank 
                    setState("Create employee ID");
                    if (!IsValid(createdEmployeeID)) {
                        data.setNeverFailedCreatingAccount(false);
                        data.setAccountCreated(false);
                        

                    } else {
                        this.data = this.database.validateCreatedAdminAccount(createdUsername, createdPassword, createdEmployeeID);

                    }
                }

            }
        }

        this.setChanged();
        this.notifyObservers(this.data);

    }
    
    public void checkCreatedCustomerAccount(String createdUsername, String createdPassword, String confirmedPassword) {
        setState("Create username");
        if (!IsValid(createdUsername)) {
            data.setNeverFailedCreatingAccount(false);
            data.setAccountCreated(false);
            
            
            // invalid username 
            // provide option to sign in

        } else {
            setState("Create password");
            if (!IsValid(createdPassword) || !IsValid(confirmedPassword)) {
                data.setNeverFailedCreatingAccount(false);
                data.setAccountCreated(false);
                
                // password is not valid

            } else {
                if (!createdPassword.equals(confirmedPassword)) {
                    data.setNeverFailedCreatingAccount(false);
                    data.setReasonAccountCreationFailed(data.getReasonAccountCreationFailed()+"Passwords do not match.\n");
                    data.setAccountCreated(false);
                    // passwords do not match 
                } else {
                    // differentiate between creating admin account and customer account, customers can have employee ID blank 
                  
                        this.data = this.database.validateCreatedCustomerAccount(createdUsername, createdPassword);

                    
                }

            }
        }

        this.setChanged();
        this.notifyObservers(this.data);

    }

    public void createAdminAccount() {
        data.setCreateAdminAccountRequested(true);
        data.setCreateCustomerAccountRequested(false);
        this.setChanged();
        this.notifyObservers(this.data);
    }

    public void createCustomerAccount() {
        data.setCreateCustomerAccountRequested(true);
        data.setCreateAdminAccountRequested(false);
        this.setChanged();
        this.notifyObservers(this.data);
    }

    public void continueAsGuest() {

        data.setUserAccount(null);
        data.setSignedIn(true);
        this.setChanged();
        this.notifyObservers(this.data);
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the accounts
     */
    public ArrayList<AZGroceriesAccount> getAccounts() {
        return accounts;
    }

    /**
     * @param accounts the accounts to set
     */
    public void setAccounts(ArrayList<AZGroceriesAccount> accounts) {
        this.accounts = accounts;
    }

    // The public boolean IsValid(OnlineShopAZGroceries shop, String choice) is the implementation of the interface IsValidusing the 
    //  public boolean IsValid(OnlineShopAZGroceries shop, String choice) function prototype
    // This function checks the validity of the users reponse provided (String choice) to every situation where a user is asked for a response    
    @Override
    public boolean IsValid(String choice) {
        boolean isValid = false;
        try {
             data.setReasonAccountCreationFailed("");
             data.setNumberOfReasonsAccountCreationFailed(0);

            if (getState().equals("Create password") || getState().equals("Create username") || getState().equals("Create employee ID") || getState().equals("entering employee ID") || getState().equals("incorrect employee ID")) {
                isValid = true;
                for (int i = 0; i < choice.length(); i++) {
                    if (!Character.isLetter(choice.charAt(i)) && !Character.isDigit(choice.charAt(i))) {
                        isValid = false;
                        
                        if (getState().equals("Create username")) {

                       // System.out.println("The entered username is too short.");
                        data.setReasonAccountCreationFailed(data.getReasonAccountCreationFailed()+ " Username cannot have special characters.");
                    }
                    if (getState().equals("Create password")) {
                    //    System.out.println("The entered password is too short.");
                        data.setReasonAccountCreationFailed(data.getReasonAccountCreationFailed()+ " Password cannot contain special characters.");
                    }
                    if (getState().equals("enter employee ID") || getState().equals("Create employee ID\n")) {
                     //   System.out.println("The entered employee ID is too short.");
                        data.setReasonAccountCreationFailed(data.getReasonAccountCreationFailed()+ " Employee ID cannot contain special characters.");
                    }
                    }
                }
                if (choice.length() < 3) {
                    if (getState().equals("Create username")) {

                       // System.out.println("The entered username is too short.");
                        data.setReasonAccountCreationFailed(data.getReasonAccountCreationFailed()+" The entered username is too short.");
                    }
                    if (getState().equals("Create password")) {
                    //    System.out.println("The entered password is too short.");
                        data.setReasonAccountCreationFailed(data.getReasonAccountCreationFailed()+" The entered password is too short.");
                    }
                    if (getState().equals("enter employee ID") || getState().equals("Create employee ID")) {
                     //   System.out.println("The entered employee ID is too short.");
                        data.setReasonAccountCreationFailed(data.getReasonAccountCreationFailed()+" The entered employee ID is too short.");
                    }

                }
                if (choice.isBlank() || choice.isEmpty()) {
                    isValid = false;
                    if (getState().equals("Create username")) {

                    //    System.out.println("Username cannot be blank or contain special characters.");
                        data.setReasonAccountCreationFailed(data.getReasonAccountCreationFailed()+" Username cannot be blank or empty.");
                    }
                    if (getState().equals("Create password")) {
                       // System.out.println("Password cannot be blank or contain special characters.");
                        data.setReasonAccountCreationFailed(data.getReasonAccountCreationFailed()+" Password cannot be blank or empty.");
                    }
                    if (getState().equals("enter employee ID") || getState().equals("Create employee ID")) {
                 //       System.out.println("Employee ID cannot be blank or contain special characters.");
                        data.setReasonAccountCreationFailed(data.getReasonAccountCreationFailed()+" Employee ID cannot be blank or empty.");
                    }

                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please input again");
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
            isValid = false;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please input again");
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
            isValid = false;
        }
        return isValid;
    }

    /**
     * @return the AdminAccounts
     */
    public ArrayList<AdminAccount> getAdminAccounts() {
        return AdminAccounts;
    }

    /**
     * @param AdminAccounts the AdminAccounts to set
     */
    public void setAdminAccounts(ArrayList<AdminAccount> AdminAccounts) {
        this.AdminAccounts = AdminAccounts;
    }

    /**
     * @return the username
     */
    public String getUsernameCreated() {
        return usernameCreated;
    }

    /**
     * @param usernameCreated the username to set
     */
    public void setUsernameCreated(String usernameCreated) {
        this.usernameCreated = usernameCreated;
    }

    /**
     * @return the password
     */
    public String getPasswordCreated() {
        return passwordCreated;
    }

    /**
     * @param passwordCreated the password to set
     */
    public void setPasswordCreated(String passwordCreated) {
        this.passwordCreated = passwordCreated;
    }
}
