/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author krist
 */
public class SignInService implements IsValid {

    private String state = "";
    
    private ArrayList<AZGroceriesAccount> accounts ;
    private ArrayList<AdminAccount> AdminAccounts ;
    private String usernameCreated;
    private String passwordCreated;

    SignInService() {
        
    Accounts accountsObject = new Accounts();
    setAccounts(accountsObject.loadAccounts());
    setAdminAccounts(accountsObject.loadAdminAccounts());
    setUsernameCreated("");
    setPasswordCreated("");

    }

    public void signInProcess(OnlineShopAZGroceries shop) {
        Accounts accountsObject = new Accounts();
         setAccounts(accountsObject.loadAccounts());
    setAdminAccounts(accountsObject.loadAdminAccounts());
    
        System.out.println("Welcome to the AZGroceries Online Store");
        System.out.println("Would you like to:\n1. Sign In\n2. Create an account\n3. Continue as guest\nPress X or x to exit");
        setState("select option");
        Scanner scanner = new Scanner(System.in);
        String signInChoice = scanner.nextLine();
        while (!IsValid(shop, signInChoice)) {

            System.out.println("Would you like to:\n1. Sign In\n2. Sign in as administrator\n3. Create an account\n4. Continue as guest");
            signInChoice = scanner.nextLine();
        }
        if (IsValid(shop, signInChoice)) {
            int signInDecision = Integer.parseInt(signInChoice);
            switch (signInDecision) {
                case 1:
                    signIn(shop);
                case 2:
                    createAccountUsername(shop);
                    break;
                case 3:
                    continueAsGuest(shop);
            }
        }
    }
    public void signIn(OnlineShopAZGroceries shop) {
        System.out.println("Welcome to the sign in page");
        setState("sign in");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username:\nPress / to go back\nPress X or x  to exit");
        
        String usernameEntered = scanner.nextLine();
        
        System.out.println("Enter password:\nPress / to go back\nPress X or x  to exit");
        String passwordEntered = scanner.nextLine();
        boolean accountFound = false;
        for (AZGroceriesAccount a : getAccounts()) {

            if (a.getUsername().equals(usernameEntered) && a.getPassword().equals(passwordEntered) && a.getRole().equals("Customer")) {

                accountFound = true;
                shop.setUserAccount(a);
                shop.openStore(shop);

            }
        }
        for (AdminAccount a : getAdminAccounts()) {

            if (a.getUsername().equals(usernameEntered) && a.getPassword().equals(passwordEntered) && a.getRole().equals("Admin")) {

                accountFound = true;
                System.out.println("Please enter your employee ID:\nPress / to go back\nPress X or x  to exit");
                setState("entering employee ID");
                AdminAccount admin = a;
                Scanner scanEmployeeID = new Scanner(System.in);
                String inputEmployeeID = scanEmployeeID.nextLine();
                while (!IsValid(shop, inputEmployeeID)) {
                    inputEmployeeID = scanEmployeeID.nextLine();
                }
                if (IsValid(shop, inputEmployeeID)) {
                    
                    while(!admin.getEmployeeID().equals(inputEmployeeID) || !IsValid(shop, inputEmployeeID))
                    {
                        setState("incorrect employee ID");
                        System.out.println("The entered employee ID is not correct. Please try again\nPress / to go back\nPress X or x to exit");
                        inputEmployeeID = scanner.nextLine();
                    }
                    if(admin.getEmployeeID().equals(inputEmployeeID))
                    {
                        shop.setUserAccount(admin);
                        shop.openStore(shop);
                    }

                }

            }
            
        }
        if (!accountFound) {
            System.out.println("The username or password entered is not valid. Please try again");
            signIn(shop);
        }
         

    }

    public void createAccountUsername(OnlineShopAZGroceries shop) {
        Scanner scanner = new Scanner(System.in);
        setState("Create username");
        System.out.println("Create username:\nPress / to go back\nPress X or x  to exit");
        String createdUsername = scanner.nextLine();
        while (!IsValid(shop, createdUsername)) {
            System.out.println("Invalid username. Please try again");
            System.out.println("Create username:\nPress / to go back\nPress X or x  to exit");
            createdUsername = scanner.nextLine();
        }
        if (IsValid(shop, createdUsername)) {
            for (AZGroceriesAccount a : getAccounts()) {
                if (a.getUsername().equals(createdUsername)) {
                    System.out.println("Username already exists\nWould you like to sign in?\nYes - Y or y\nNo - N or n\nPress / to go back\nPress X or x  to exit");
                    String Decision = scanner.nextLine();
                    setState("Username already exists");
                    while (!IsValid(shop, Decision)) {
                        System.out.println("Not a valid option, please try again");
                        System.out.println("Username already exists\nWould you like to sign in?\nY - Yes\nN -no\nPress / to go back\nPress X or x  to exit");
                        Decision = scanner.nextLine();

                    }
                    if (Decision.equalsIgnoreCase("Y")) {
                        signIn(shop);
                    }
                    if (Decision.equalsIgnoreCase("N")) {
                        System.out.println("Since the username entered exists, a new account cannot be created with that username. Please try again");
                        createAccountUsername(shop);
                    }

                }
            }
            setUsernameCreated(createdUsername);
            createAccountPassword( shop);
        }//

    }
public void createAccountPassword(OnlineShopAZGroceries shop)
{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Create password:\nPress / to go back\nPress X or x  to exit");
            setState("Create password");
            String createdPassword = scanner.nextLine();
            while (!IsValid(shop, createdPassword)) {
                System.out.println("Invalid password. Please try again");
                System.out.println("Create password:\nPress / to go back\nPress X or x  to exit");
                createdPassword = scanner.nextLine();
            }
            if (IsValid(shop, createdPassword)) {
                System.out.println("Confirm password:\nPress / to go back\nPress X or x  to exit");
                String retypedPassword = scanner.nextLine();
                while (!createdPassword.equals(retypedPassword)) {
                    System.out.println("Passwords do not match, please try again");
                    createAccountPassword( shop);

                }
                if (createdPassword.equals(retypedPassword)) {
                    setPasswordCreated(createdPassword);
                    decidingAccountType( shop);

                }//
                

            }
}

public void decidingAccountType(OnlineShopAZGroceries shop)
{
                     setState("account choice");
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Would you like to create a administrator account? \nYes - Y or y\nNo - N or n\nPress / to go back\nPress X or x  to exit");
                    String accountChoice = scanner.nextLine();
                    while (!IsValid(shop, accountChoice)) {
                        System.out.println("Not a valid option, please try again");
                        System.out.println("Would you like to create a administrator account? \nYes - Y or y\nNo - N or n\nPress / to go back\nPress X or x  to exit");

                        accountChoice = scanner.nextLine();
                    }
                    if (IsValid(shop, accountChoice)) {

                        if (accountChoice.equalsIgnoreCase("Y")) {
                            
                             createAdminAccount(shop);

                        }
                        if (accountChoice.equalsIgnoreCase("N")) {
                            CustomerAccount newAccount = new CustomerAccount(getUsernameCreated(), getPasswordCreated());
                            System.out.println("Account created sucessfully");
                            try {
                                //override the file if exists
                                BufferedWriter outputStream = new BufferedWriter(new FileWriter("./resources/AccountList.txt", true));
                                outputStream.append(newAccount.getUsername() + " " + newAccount.getPassword());
                                outputStream.newLine();
                                outputStream.close();
                                getAccounts().add(newAccount);
                                 signInProcess(shop);

                            } catch (FileNotFoundException e) {
                                System.out.println("Error opening file");
                            } catch (IOException e) {
                                System.out.println("IO Exception");
                            }

                        }

                        
                    }
}
public void createAdminAccount(OnlineShopAZGroceries shop)
{
    System.out.println("Please enter your employee ID\nPress / to go back\nPress X or x  to exit");
                            setState("Create employee ID");
                            Scanner scanner = new Scanner(System.in);
                            String employeeID = scanner.nextLine();
                            while(!IsValid(shop, employeeID))
                            {
                                System.out.println("Invalid employee ID. Please try again.");
                                System.out.println("Please enter your employee ID:\nPress / to go back\nPress X or x  to exit");
                                employeeID = scanner.nextLine();
                            }
                            if(IsValid(shop, employeeID))
                            {
                                AdminAccount newAccount = new AdminAccount(getUsernameCreated(), getPasswordCreated(), employeeID);
                                System.out.println("Account created sucessfully");
                            try {
                                //override the file if exists
                                BufferedWriter outputStream = new BufferedWriter(new FileWriter("./resources/AccountList.txt", true));
                                outputStream.append(newAccount.getUsername() + " " + newAccount.getPassword() + " " + newAccount.getEmployeeID());
                                outputStream.newLine();
                                outputStream.close();
                                getAccounts().add(newAccount);
                                signInProcess( shop);

                            } catch (FileNotFoundException e) {
                                System.out.println("Error opening file");
                            } catch (IOException e) {
                                System.out.println("IO Exception");
                            }
                            
                            }
}
    public void continueAsGuest(OnlineShopAZGroceries shop) {
        shop.setUserAccount(null);
        shop.openStore(shop);
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
    public boolean IsValid(OnlineShopAZGroceries shop, String choice) {
        boolean isValid = false;
        if (choice.equals("x") || choice.equals("X")) {
            System.exit(0);
        }
        if (choice.equals("/")) {
            if (getState().equals("sign in")) {
                signInProcess(shop);
            }
            if(getState().equals("incorrect employee ID") ||getState().equals("entering employee ID"))
            {
                signIn(shop);
            }
            if(getState().equals("Create username"))
            {
                signInProcess(shop);
            }
            if(getState().equals("Create password") || getState().equals("Username already exists"))
            {
                createAccountUsername(shop);
            }
            if(getState().equals("Create employee ID"))
            {
                decidingAccountType(shop);
            }
            if(getState().equals("account choice"))
            {
                createAccountPassword(shop);
            }
            
        }
        try {
            if (getState().equals("Username already exists") || getState().equals("account choice")) {
                if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N")) {
                    isValid = true;
                }
            }
            if(getState().equals("select option"))
            {
                int decision = Integer.parseInt(choice);
                if (decision >= 1 && decision <= 3) {
                isValid = true;
            }
            }
            
            if (getState().equals("Create password") || getState().equals("Create username") || getState().equals("Create employee ID") || getState().equals("entering employee ID") || getState().equals("incorrect employee ID")) {
                isValid = true;
                boolean hasSpecialCharacter = false;
                for (int i = 0; i < choice.length(); i++) {
                    if (!Character.isLetter(choice.charAt(i)) && !Character.isDigit(choice.charAt(i))) {
                        isValid = false;
                        hasSpecialCharacter = true;
                    }
                }
                boolean tooShort = false;
                if(choice.length()<3)
                {
                    tooShort = true;
                    if (getState().equals("Create username")) {
                        
                       System.out.println("The entered username is too short.");
                    } 
                    if(getState().equals("Create password")){
                        System.out.println("The entered password is too short.");
                    }
                    if(getState().equals("enter employee ID") || getState().equals("Create employee ID") )
                    {
                         System.out.println("The entered employee ID is too short.");
                    }
                    
                }
                if (choice.isBlank() || choice.isEmpty() || hasSpecialCharacter || tooShort) {
                    isValid = false;
                    if (getState().equals("Create username")) {
                        
                        System.out.println("Username cannot be blank or contain special characters.");
                    } 
                    if(getState().equals("Create password")){
                        System.out.println("Password cannot be blank or contain special characters.");
                    }
                    if(getState().equals("enter employee ID")|| getState().equals("Create employee ID") )
                    {
                         System.out.println("Employee ID cannot be blank or contain special characters.");
                    }

                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please input again");
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
            isValid = false;
        }
        catch (InputMismatchException e) {
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
