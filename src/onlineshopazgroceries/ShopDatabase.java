/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author krist
 */
public class ShopDatabase {

    Connection conn = null;
    String url = "jdbc:derby:AZGroceriesDatabase;create=true";  //url of the DB host

    String dbusername = "pdc";  //your DB username
    String dbpassword = "pdc";   //your DB password

    public void databaseSetup() {
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();

            if (!checkTableExisting("Table_of_Customer_Accounts")) {
                statement.addBatch("CREATE TABLE Table_of_Customer_Accounts (username VARCHAR(50), password VARCHAR(50))");
                statement.addBatch(" INSERT INTO Table_of_Customer_Accounts VALUES ('Rob21', 'Umbrella22'), \n"
                        + "('Eric23', 'Happy78'), \n"
                        + "('Water38', 'Queen67') \n");
                statement.executeBatch();

            }
            //statement.close();

            if (!checkTableExisting("Table_of_Admin_Accounts")) {
                statement.addBatch("CREATE TABLE Table_of_Admin_Accounts (username VARCHAR(50), password VARCHAR(50), employeeID VARCHAR(50))");
                statement.addBatch(" INSERT INTO Table_of_Admin_Accounts VALUES ('Angella63', 'Potatoes63', 'ex768p8'), \n"
                        + "('Trees56', 'Leaves29', 'ghd67'), \n"
                        + "('Fish55', 'Sand66', 'East87'), \n"
                        + "('Rats14', 'Cats87', 'TFS45') \n");
                statement.executeBatch();
            }

            //statement.close();
            if (!checkTableExisting("Table_of_Grocery_Items")) {
                statement.addBatch("CREATE TABLE Table_of_Grocery_Items (productName VARCHAR(50), price FLOAT(25), catagory VARCHAR(50), quantity INT)");
                statement.addBatch(" INSERT INTO Table_of_Grocery_Items VALUES ('Tomatoes', 4.99, 'Vegetables', 36), \n"
                        + "('Garlic', 10.0, 'Vegetables', 55), \n"
                        + "('Potatoes', 5.99, 'Vegetables', 20), \n"
                        + "('Apples', 2.99, 'Fruits', 26), \n"
                        + "('Peaches', 3.99, 'Fruits', 12), \n"
                        + "('Jelly', 2.5, 'Confectionery', 17), \n"
                        + "('Noodles', 1.5, 'Pasta', 15) \n");
                statement.executeBatch();
            }

            // transaction table 
            //  statement.close();
            if (!checkTableExisting("Transaction_table")) {
                statement.addBatch("CREATE TABLE Transaction_table (transactionID INT, purchaseDate VARCHAR(10), productName VARCHAR(50), productPrice FLOAT(25), quantityPurchased INT)");
                //statement.addBatch( " INSERT INTO Transaction table VALUES (1, , 'Vegetables', 36), \n");
                statement.executeBatch();
            }

            // transaction table 
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error - Setup of database");

        }

    }

    public ArrayList<CustomerAccount> getCustomerAccountsList() {

        ArrayList<CustomerAccount> customerAccounts = new ArrayList<CustomerAccount>();
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Table_of_Customer_Accounts");
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                CustomerAccount customerAccount = new CustomerAccount(username, password);
                customerAccounts.add(customerAccount);
            }

        } catch (Throwable e) {
            System.out.println("error Creating Customer accounts table ");

        }
        return customerAccounts;

    }

    public ArrayList<AdminAccount> getAdminAccountsList() {

        ArrayList<AdminAccount> adminAccounts = new ArrayList<AdminAccount>();
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Table_of_Admin_Accounts");
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String employeeID = rs.getString("employeeID");
                AdminAccount adminAccount = new AdminAccount(username, password, employeeID);
                adminAccounts.add(adminAccount);
            }

        } catch (Throwable e) {
            System.out.println("error Creating Customer accounts table ");

        }
        return adminAccounts;

    }
    
    public ListOfGroceries loadGroceries()
    {
        ListOfGroceries groceryList = new ListOfGroceries();
        try {
            conn = DriverManager.getConnection(url, dbusername, dbpassword);
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Table_of_Grocery_Items");
            while (rs.next()) {
                String productName = rs.getString("productName");
                double productPrice = rs.getDouble("price");
                String productCategory = rs.getString("catagory");
                int productQuantityAvailable = rs.getInt("quantity");
                
                GroceryItems newItem = new GroceryItems(productName, productPrice, productCategory, productQuantityAvailable);                
                groceryList.getGroceries().add(newItem);
                
            }

        } catch (Throwable e) {
            System.out.println("error Creating List of Groceries");

        }
        return groceryList;
    }

    private boolean checkTableExisting(String newTableName) {
        boolean flag = false;
        try {

            Statement statement = this.conn.createStatement();

            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);//types);
            while (rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if (tableName.compareToIgnoreCase(newTableName) == 0) {
                    flag = true;
                    //      statement.executeUpdate("Drop table " + tableName);
                }
            }
            if (rsDBMeta != null) {
                rsDBMeta.close();
            }
        } catch (SQLException ex) {
            System.out.println("SQL exception - Error");
        }
        return flag;
    }

    void addTransaction(int transactionID, String purchaseDate, String productName, double productPrice, int quantityPurchased) {

        Statement statement;
        try {
            statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO Transaction_table "
                    + "VALUES('" + transactionID + "', '" + purchaseDate + "', '" + productPrice + "', '" + quantityPurchased + "' )");
// + "VALUES('" + username + "', '" + password + "', 0)");
        } catch (SQLException ex) {
            Logger.getLogger(OnlineShopAZGroceries.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ShopData checkLogin(String username, String password, String employeeID) {
        ShopData data = new ShopData();
//        ResultSet customerResultSet = statement.executeQuery("SELECT username FROM Table_of_Customer_Accounts ");
//            if (customerResultSet.next()) {
//                String existingUsername = customerResultSet.getString("username");
//                if (existingUsername.compareTo(createdUsername) == 0) {
//                    accountExists = true;
//                    // invalid account
//                   // username already exists 
//                   // give user message that account already exists
//                } 
//            }
//             
        try {
            Statement statement = conn.createStatement();
            if (employeeID.equals("")) {
                ResultSet customerResultSet = statement.executeQuery("SELECT username, password FROM Table_of_Customer_Accounts "
                        + "WHERE username = '" + username + "'");
                if (customerResultSet.next()) {
                    String pass = customerResultSet.getString("password");
                    if (password.compareTo(pass) == 0) {

                        ArrayList<CustomerAccount> customerAccounts = getCustomerAccountsList();
                        for (CustomerAccount c : customerAccounts) {
                            if (c.getUsername().equals(username)) {
                                data.setUserAccount(c); // stores the customer account in the ShopData class
                                
                            }
                        }
                        data.setListOfGroceries(this.loadGroceries());
                        data.setSignedIn(true);

                        //      data.setUserAccount(userAccount);
                    } else {
                        data.setSignedIn(false);
                    }
                }
            }
            if (!employeeID.equals("")) {
                ResultSet adminResultSet = statement.executeQuery("SELECT username, password, employeeID FROM Table_of_Admin_Accounts "
                        + "WHERE username = '" + username + "'");

                if (adminResultSet.next()) {
                    String pass = adminResultSet.getString("password");
                    String correctEmployeeID = adminResultSet.getString("employeeID");
                    if (password.compareTo(pass) == 0 && employeeID.equals(correctEmployeeID)) {

                        ArrayList<AdminAccount> adminAccounts = getAdminAccountsList();
                        for (AdminAccount a : adminAccounts) {
                            if (a.getUsername().equals(username)) {
                                data.setUserAccount(a); // stores the customer account in the ShopData class
                            }
                        }
                        
                        data.setListOfGroceries(this.loadGroceries());
                        data.setSignedIn(true);
                    } else {
                        data.setSignedIn(false);
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(OnlineShopAZGroceries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public ShopData validateCreatedAdminAccount(String createdUsername, String createdPassword, String createdEmployeeID) {
        ShopData data = new ShopData();

        try {
            Statement statement = conn.createStatement();
            boolean accountExists = false;
            ResultSet customerResultSet = statement.executeQuery("SELECT username FROM Table_of_Customer_Accounts ");
            if (customerResultSet.next()) {
                String existingUsername = customerResultSet.getString("username");
                if (existingUsername.equals(createdUsername)) {
                    accountExists = true;
                    // invalid account
                   // username already exists 
                   // give user message that account already exists
                   data.setNeverFailedCreatingAccount(false);
                   data.setAccountCreated(false);
                   data.setReasonAccountCreationFailed("Username already exists");
                   
                }
                
            }
            ResultSet adminResultSet = statement.executeQuery("SELECT username FROM Table_of_Admin_Accounts ");
            if (adminResultSet.next()) {
                String existingUsername = adminResultSet.getString("username");
                if (existingUsername.equals(createdUsername) ) {
                    accountExists = true;
                    // invalid account
                   // username already exists 
                   // give user message that account already exists
                   data.setNeverFailedCreatingAccount(false);
                   data.setAccountCreated(false);
                   data.setReasonAccountCreationFailed("Username already exists");
                   
                }
                
            }
            if(accountExists == false)
            {
                statement.executeUpdate("INSERT INTO Table_of_Admin_Accounts VALUES ('" + createdUsername + "', '" + createdPassword + "', '" + createdEmployeeID + "')");
                    data.setAccountCreated(true);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OnlineShopAZGroceries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
    
    public ShopData validateCreatedCustomerAccount(String createdUsername, String createdPassword) {
        ShopData data = new ShopData();

        try {
            Statement statement = conn.createStatement();
            boolean accountExists = false;
            ResultSet customerResultSet = statement.executeQuery("SELECT username FROM Table_of_Customer_Accounts ");
            while (customerResultSet.next()) {
                String existingUsername = customerResultSet.getString("username");
                if (existingUsername.equals(createdUsername)) {
                    accountExists = true;
                    // invalid account
                   // username already exists 
                   // give user message that account already exists
                   data.setNeverFailedCreatingAccount(false);
                   data.setAccountCreated(false);
                   data.setReasonAccountCreationFailed("Username already exists");
                   
                }
                
            }
            
            ResultSet adminResultSet = statement.executeQuery("SELECT username FROM Table_of_Admin_Accounts ");
            while (adminResultSet.next()) {
                String existingUsername = adminResultSet.getString("username");
                if (existingUsername.equals(createdUsername)) {
                    accountExists = true;
                    // invalid account
                   // username already exists 
                   // give user message that account already exists
                   data.setNeverFailedCreatingAccount(false);
                   data.setAccountCreated(false);
                   data.setReasonAccountCreationFailed("Username already exists");
                   
                }
                
            }
            if(accountExists == false)
            {
                statement.executeUpdate("INSERT INTO Table_of_Customer_Accounts VALUES ('" + createdUsername + "', '" + createdPassword + "')");
                    data.setAccountCreated(true);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OnlineShopAZGroceries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
}

