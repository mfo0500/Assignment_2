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
                statement.addBatch("CREATE TABLE Transaction_table (customerUsername VARCHAR(50), purchaseDate VARCHAR(10), productName VARCHAR(50), productPrice FLOAT(25), productCategory VARCHAR(50), productQuantityAvailable INT, quantityPurchased INT)");
                //statement.addBatch( " INSERT INTO Transaction_table VALUES ('customerUsername', '17/08/2022' , 'Apples',  36.0, 'Vegetables', 15, 2 )");
                statement.executeBatch();
            }

            // transaction table 
            if (!checkTableExisting("Existing_Cart_Entries")) {
                statement.addBatch("CREATE TABLE Existing_Cart_Entries (customerUsername VARCHAR(50), productName VARCHAR(50), productPrice FLOAT(25), productCategory VARCHAR(50), productQuantityAvailable INT, quantityPurchased INT)");
                //statement.addBatch( " INSERT INTO Existing_Cart_Entries VALUES ('customerUsername', 'Apples',  36.0, 'Vegetables', 15, 2 )");
                statement.executeBatch();
            }
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
    
    public ListOfGroceries loadGroceries() {
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
    
    public void addTransaction(String usernameOfCustomer, String purchaseDate, GroceryItems groceryItemPurchased, int quantityPurchased) {
        
        Statement statement;
        try {
            statement = conn.createStatement();
            String productName = groceryItemPurchased.getProductName();
            double productPrice = groceryItemPurchased.getPrice();
            String productCategory = groceryItemPurchased.getCategory();
            int quantityAvailable = groceryItemPurchased.getQuantityAvailable();
            statement.executeUpdate("INSERT INTO Transaction_table "
                    + "VALUES('" + usernameOfCustomer + "', '" + purchaseDate + "', " + productName + "', " + productPrice + ", " + productCategory + ", " + quantityAvailable + ", " + quantityPurchased + " )");
            
        } catch (SQLException ex) {
            Logger.getLogger(OnlineShopAZGroceries.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ShopData addGroceryItem(ShopData originalData, String productName, double productPrice, String productCategory, int quantityAvailable) {
        
        ShopData data = originalData;
        Statement statement;
        try {
            statement = conn.createStatement();
            
            boolean groceryItemExists = false;
            ResultSet productNameResultSet = statement.executeQuery("SELECT productName FROM Table_of_Grocery_Items ");
            while (productNameResultSet.next()) {
                String existingProductName = productNameResultSet.getString("productName");
                if (existingProductName.equals(productName)) {
                    groceryItemExists = true;
                    // invalid account
                    // username already exists 
                    // give user message that account already exists
                    data.setItemAdded(false);
                    data.setReasonItemAdditionFailed("Product already exists");
                    
                }
                
            }
            if (groceryItemExists == false) {
                statement.executeUpdate("INSERT INTO Table_of_Grocery_Items "
                        + "VALUES('" + productName + "', " + productPrice + ", '" + productCategory + "', " + quantityAvailable + " )");
                
                GroceryItems newItem = new GroceryItems(productName, productPrice, productCategory, quantityAvailable);
                data.getListOfGroceries().getGroceries().add(newItem);
                data.setGroceryItemAdded(newItem);
                data.setItemAdded(true);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OnlineShopAZGroceries.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
        
    }
    
    public ShopData removeGroceryItem(ShopData originalData, String productName) {
        ShopData data = originalData;
        Statement statement;
        try {
            statement = conn.createStatement();
            
            statement.executeUpdate("DELETE FROM TABLE_OF_GROCERY_ITEMS WHERE PRODUCTNAME = '" + productName + "'");
            
            data.setItemRemoved(true);
        } catch (SQLException ex) {
            Logger.getLogger(OnlineShopAZGroceries.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
        
    }
    
    public ShopData modifyGroceryItemQuantity(ShopData originalData, String productNameOfModifiedGroceryItem, int modifiedQuantityAvailable, int numberOfGroceryItemModified) {
        ShopData data = originalData;
        Statement statement;
        
        try {
            statement = conn.createStatement();
            
            statement.executeUpdate("UPDATE TABLE_OF_GROCERY_ITEMS SET \"QUANTITY\" = " + modifiedQuantityAvailable + " WHERE PRODUCTNAME = '" + productNameOfModifiedGroceryItem + "'");
            
            data.getListOfGroceries().getGroceries().get(numberOfGroceryItemModified).setQuantityAvailable(modifiedQuantityAvailable);
            data.setModifiedGroceryItem(data.getListOfGroceries().getGroceries().get(numberOfGroceryItemModified));
            data.setItemQuantityModified(true);
        } catch (SQLException ex) {
            Logger.getLogger(OnlineShopAZGroceries.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
        // UPDATE TABLE_OF_GROCERY_ITEMS SET "QUANTITY" = 20 WHERE PRODUCTNAME = 'Potatoes';

    }
    
    public ShopData loadUserCart(ShopData originalData) {
        ShopData data = originalData;
        Statement statement;
        
        try {
            statement = conn.createStatement();
            data.getUserAccount().getUserCart().getItemsAdded().clear();
            data.getUserAccount().getUserCart().setTotal(0);
            ResultSet cartEntryResultSet = statement.executeQuery("SELECT * FROM Existing_Cart_Entries WHERE customerUsername = '" + data.getUserAccount().getUsername() + "'");
            while (cartEntryResultSet.next()) {
// statement.addBatch("CREATE TABLE Existing_Cart_Entries (customerUsername VARCHAR(50), productName VARCHAR(50), productPrice FLOAT(25), productCategory VARCHAR(50), productQuantityAvailable INT, quantityPurchased INT)");

                String productName = cartEntryResultSet.getString("productName");
                double productPrice = cartEntryResultSet.getDouble("productPrice");
                String productCategory = cartEntryResultSet.getString("productCategory");                
                int productQuantityAvailable = cartEntryResultSet.getInt("productQuantityAvailable");
                int quantityAdded = cartEntryResultSet.getInt("quantityPurchased");
                GroceryItems groceryItemAddedToCart = new GroceryItems(productName, productPrice, productCategory, productQuantityAvailable);
                
                GroceryItems originalGroceryItem = null;
                for (GroceryItems g :data.getUserAccount().getUserCart().getItemsAdded().keySet() )
                {
                    if(g.getProductName().equals(groceryItemAddedToCart.getProductName()))
                    {
                        originalGroceryItem = g;
                    }
                }
                if (data.getUserAccount().getUserCart().getItemsAdded().containsKey(originalGroceryItem)) {
                    
                int originalQuantity =  data.getUserAccount().getUserCart().getItemsAdded().get(originalGroceryItem);
                int updatedQuantity = quantityAdded + originalQuantity;
                
                    data.getUserAccount().getUserCart().getItemsAdded().replace( originalGroceryItem, updatedQuantity);
                    data.getUserAccount().getUserCart().setTotal(data.getUserAccount().getUserCart().getTotal() + quantityAdded * groceryItemAddedToCart.getPrice());
                } else {
                    data.getUserAccount().getUserCart().getItemsAdded().put(groceryItemAddedToCart, quantityAdded);
                    data.getUserAccount().getUserCart().setTotal(data.getUserAccount().getUserCart().getTotal() + quantityAdded * groceryItemAddedToCart.getPrice());
                    
                }
                
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(OnlineShopAZGroceries.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
        
    }
    
    public ShopData addGroceryItemToCart(ShopData originalData, String customerUsername, GroceryItems groceryItemRequested, int quantityAddedToCart) {
        ShopData data = originalData;
        Statement statement;
        try {
            statement = conn.createStatement();
            
            String productName = groceryItemRequested.getProductName();
            double productPrice = groceryItemRequested.getPrice();
            String productCategory = groceryItemRequested.getCategory();
            int quantityAvailable = groceryItemRequested.getQuantityAvailable();
            statement.executeUpdate("INSERT INTO Existing_Cart_Entries "
                    + "VALUES('" + customerUsername + "', '" + productName + "', " + productPrice + ", '" + productCategory + "', " + quantityAvailable + ", " + quantityAddedToCart + " )");
//            
//            data.getUserAccount().getUserCart().getItemsAdded().put(groceryItemRequested, quantityAddedToCart);
//            data.getUserAccount().getUserCart().setTotal(data.getUserAccount().getUserCart().getTotal() + quantityAddedToCart * groceryItemRequested.getPrice());
//            
            
            if (data.getUserAccount().getUserCart().getItemsAdded().containsKey(groceryItemRequested)) {
                data.getUserAccount().getUserCart().getItemsAdded().replace(groceryItemRequested, quantityAddedToCart + data.getUserAccount().getUserCart().getItemsAdded().get(groceryItemRequested));
                data.getUserAccount().getUserCart().setTotal(data.getUserAccount().getUserCart().getTotal() + quantityAddedToCart * groceryItemRequested.getPrice());
            } else {
                data.getUserAccount().getUserCart().getItemsAdded().put(groceryItemRequested, quantityAddedToCart);
                data.getUserAccount().getUserCart().setTotal(data.getUserAccount().getUserCart().getTotal() + quantityAddedToCart * groceryItemRequested.getPrice());
                
            }
            data.setQuantityOfProductAddedToCart(quantityAddedToCart);
            data.setItemAddedToCart(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error - adding Cart entry to database");
        }
        
        return data;
        
    }
    
    public ShopData removeGroceryItemFromCart(ShopData originalData, String customerUsername, GroceryItems groceryItemRequestedToRemove, int quantityAddedToCart) {
        ShopData data = originalData;
        Statement statement;
        try {
            statement = conn.createStatement();
            
            String productName = groceryItemRequestedToRemove.getProductName();
            statement.executeUpdate("DELETE FROM EXISTING_CART_ENTRIES WHERE CUSTOMERUSERNAME = '"+ customerUsername+"' AND PRODUCTNAME = '" + productName+"'");
            
            // DELETE FROM PDC.EXISTING_CART_ENTRIES WHERE CUSTOMERUSERNAME = 'Water38' AND PRODUCTNAME = 'Apples' AND PRODUCTPRICE = 2.99 AND PRODUCTCATEGORY = 'Fruits' AND PRODUCTQUANTITYAVAILABLE = 26 AND QUANTITYPURCHASED = 1;

           data.getUserAccount().getUserCart().setTotal(data.getUserAccount().getUserCart().getTotal() - groceryItemRequestedToRemove.getPrice() * quantityAddedToCart);
           data.getUserAccount().getUserCart().getItemsAdded().remove(groceryItemRequestedToRemove);
            
            data.setItemRemovedFromCart(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error - removing Cart entry from database");
        }
        
        return data;
        
    }
    
    public ShopData updateCart(ShopData originalData, String customerUsername ,  GroceryItems updatedCartItem, int updatedQuantityToPurchase)
    {
          ShopData data = originalData;
           Statement statement;
          try {
            statement = conn.createStatement();
            
            String productName = updatedCartItem.getProductName();
            double productPrice = updatedCartItem.getPrice();
            String productCategory = updatedCartItem.getCategory();
            int quantityAvailable = updatedCartItem.getQuantityAvailable();
            int originalProductPurchaseQuantity = data.getUserAccount().getUserCart().getItemsAdded().get(updatedCartItem);
            int changeInProductQuantity = updatedQuantityToPurchase - originalProductPurchaseQuantity;
            
            
           statement.executeUpdate("DELETE FROM EXISTING_CART_ENTRIES WHERE CUSTOMERUSERNAME = '"+ customerUsername+"' AND PRODUCTNAME = '" + productName+"'");
           statement.executeUpdate("INSERT INTO Existing_Cart_Entries "
                    + "VALUES('" + customerUsername + "', '" + productName + "', " + productPrice + ", '" + productCategory + "', " + quantityAvailable + ", " + updatedQuantityToPurchase + " )");
            // DELETE FROM PDC.EXISTING_CART_ENTRIES WHERE CUSTOMERUSERNAME = 'Water38' AND PRODUCTNAME = 'Apples' AND PRODUCTPRICE = 2.99 AND PRODUCTCATEGORY = 'Fruits' AND PRODUCTQUANTITYAVAILABLE = 26 AND QUANTITYPURCHASED = 1;

         data.getUserAccount().getUserCart().setTotal(data.getUserAccount().getUserCart().getTotal() + updatedCartItem.getPrice() * changeInProductQuantity);
         data.getUserAccount().getUserCart().getItemsAdded().replace(updatedCartItem, updatedQuantityToPurchase );
            
            data.setCartUpdated(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error - updating Cart entry from database");
        }
          
         return data; 
    }

    public ShopData checkLogin(String username, String password, String employeeID) {
        ShopData data = new ShopData();
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
                        data = this.loadUserCart(data);
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
                        data = this.loadUserCart(data);
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
            if (accountExists == false) {
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
            if (accountExists == false) {
                statement.executeUpdate("INSERT INTO Table_of_Customer_Accounts VALUES ('" + createdUsername + "', '" + createdPassword + "')");
                data.setAccountCreated(true);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OnlineShopAZGroceries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
}
