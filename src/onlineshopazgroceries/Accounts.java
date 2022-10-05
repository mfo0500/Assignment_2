/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author krist
 */

// Accounts objects represents a collection of AZGroceriesAccount objects.In this case, it is an ArrayList of AZGroceriesAccount objects
// Every Accounts object has an Accounts variable. The Accounts variable represents an ArrayList of AZGroceriesAccount objects.
// Every Accounts object has an AdminAccounts variable. The Accounts variable represents an ArrayList of AdminAccount objects.
public class Accounts {

    private ArrayList<AZGroceriesAccount> Accounts;
    private ArrayList<AdminAccount> AdminAccounts;

// The public Accounts() default constructor is a method that creates Accounts objects.
// setAccounts(new ArrayList<AZGroceriesAccount>()) method sets the Accounts object's Accounts to the provided
// Arraylist of AZGroceries accounts ( new ArrayList<AZGroceriesAccount>()). In this case, it is a new empty Arraylist of AZGroceries accounts.
    public Accounts() {
        setAccounts(new ArrayList<AZGroceriesAccount>());
        setAdminAccounts(new ArrayList<AdminAccount>());
    }

// The public ArrayList<AZGroceriesAccount> loadAccounts() is a method that reads a file that stores all the AZGroceriesAccount objects created
// and returns an ArrayList of AZGroceriesAccount objects.
// In this case, the file is called "AccountList.txt" located in the resources folder stored inside the OnlineShopAZGroceries project folder.
// The file is read from a BufferedReader called buffedReader.
// When reading each line of the "AccountList.txt" file, Line.split(" ") is used to extract the username of the AZGroceriesAccount,
// the password of the AZGroceriesAccount, the role of the AZGroceriesAccount, and sometimes the employeeID of the AZGroceriesAccount.
// This depends on the number of Strings extracted from the Line.split(" ") method. If two strings are extracted from the Line.split(" ")
// method, the username of the AZGroceriesAccount, and the password of the AZGroceriesAccount is extracted.
// This indicates that the AZGroceriesAccount is a CustomerAccount since there are only two parameters needed for a CustomerAccount object to be 
// created. If three strings are extracted from the Line.split(" ") method, the username of the AZGroceriesAccount, 
//  the password of the AZGroceriesAccount, and the employeeID of the AZGroceriesAccount is extracted.
// This indicates that the AZGroceriesAccount is a AdminAccount since there are only 3 parameters needed for a AdminAccount object to be created
// AdminAccounts and CustomerAccounts are created using the corresponding number of Strings extracted by the Line.split(" ") method. 
// All types of AZGroceriesAccount are added to Accounts variable
    public ArrayList<AZGroceriesAccount> loadAccounts() {

        FileReader reader;
        Accounts accounts = new Accounts();
        try {
            reader = new FileReader("./resources/AccountList.txt");
            BufferedReader buffedReader = new BufferedReader(reader);
            String Line = "";

            while ((Line = buffedReader.readLine()) != null) {
                String[] UsernamePasswordRole = Line.split(" ");
                if (UsernamePasswordRole.length == 2) {
                    CustomerAccount newAccount = new CustomerAccount((String) UsernamePasswordRole[0], (String)UsernamePasswordRole[1]);
                    accounts.getAccounts().add(newAccount);
                }
                if (UsernamePasswordRole.length == 3) {
                    AdminAccount newAccount = new AdminAccount((String) UsernamePasswordRole[0], UsernamePasswordRole[1], (String) UsernamePasswordRole[2]);
                    accounts.getAccounts().add(newAccount);
                    accounts.getAdminAccounts().add(newAccount);
                }

            }

        } catch (FileNotFoundException e) // if the "AccountList.txt" file is not found, a FileNotFoundException will be through and 
        {                               // the console will print "File not found"
            System.out.println("File not found");
        } catch (IOException e) // if an IOException is thrown, the console will print "IO Exception"
        {
            System.out.println("IO Exception");
        }
        return accounts.getAccounts(); // the Accounts object's Accounts varaible will be returned if there are no issues with the file provided 
    }
    
    // the public ArrayList<AdminAccount> loadAdminAccounts() reads the "Accounts.txt" file the same way 
    // as public ArrayList<AZGroceriesAccount> loadAccounts() to collect AdminAccounts and CustomerAccounts;
    
    public ArrayList<AdminAccount> loadAdminAccounts() {

        FileReader reader;
        Accounts accounts = new Accounts();
        try {
            reader = new FileReader("./resources/AccountList.txt");
            BufferedReader buffedReader = new BufferedReader(reader);
            String Line = "";

            while ((Line = buffedReader.readLine()) != null) {
                String[] UsernamePasswordRole = Line.split(" ");
                if (UsernamePasswordRole.length == 2) {
                    CustomerAccount newAccount = new CustomerAccount((String) UsernamePasswordRole[0], (String)UsernamePasswordRole[1]);
                    accounts.getAccounts().add(newAccount);
                }
                if (UsernamePasswordRole.length == 3) {
                    AdminAccount newAccount = new AdminAccount((String) UsernamePasswordRole[0], UsernamePasswordRole[1], (String) UsernamePasswordRole[2]);
                    accounts.getAccounts().add(newAccount);
                    accounts.getAdminAccounts().add(newAccount);
                }

            }

        } catch (FileNotFoundException e) // if the "AccountList.txt" file is not found, a FileNotFoundException will be through and 
        {                               // the console will print "File not found"
            System.out.println("File not found");
        } catch (IOException e) // if an IOException is thrown, the console will print "IO Exception"
        {
            System.out.println("IO Exception");
        }
        return accounts.getAdminAccounts(); // the Accounts object's AdminAccounts varaible will be returned if there are no issues with the file provided 
    }

    /**
     * @return the Accounts
     */
// The public ArrayList<AZGroceriesAccount> getAccounts() returns the Accounts object's Accounts variable in the form of an ArrayList of 
// AZGroceriesAccount objects.
    public ArrayList<AZGroceriesAccount> getAccounts() {
        return Accounts;
    }

    /**
     * @param Accounts the Accounts to set
     */
// The public void setAccounts(ArrayList<AZGroceriesAccount> Accounts) sets the Accounts object's Accounts variable to 
// the ArrayList of AZGroceriesAccount objects provided (Accounts).
    public void setAccounts(ArrayList<AZGroceriesAccount> Accounts) {
        this.Accounts = Accounts;
    }

    /**
     * @return the AdminAccounts
     */
// The public ArrayList<AdminAccount> getAdminAccounts() returns the Accounts object's AdminAccounts variable in the form of an ArrayList of 
// AdminAccount objects.
    public ArrayList<AdminAccount> getAdminAccounts() {
        return AdminAccounts;
    }

    /**
     * @param AdminAccounts the AdminAccounts to set
     */
 // The public void setAdminAccounts(ArrayList<AdminAccount> AdminAccounts) sets the Accounts object's AdminAccounts variable to 
// the ArrayList of AdminAccount objects provided (AdminAccounts).
    public void setAdminAccounts(ArrayList<AdminAccount> AdminAccounts) {
        this.AdminAccounts = AdminAccounts;
    }
}
