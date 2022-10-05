/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

/**
 *
 * @author krist
 */
// The AdminAccount object represents an administration account of the AZGroceries online shop.
// The AdminAccount extends the AZGroceriesAccount class, inheriting variables such as username, password, userCart, role and orderHistory.
// The username of an AdminAccount is a String that represents the username of the AdminAccount object.
// The password of an AdminAccount is a String that represents the password of the AdminAccount object.
// The userCart of an AdminAccount is a Cart object that represents the cart of the AdminAccount object. 
// The role of an AdminAccount is a String that represents the type of AZGroceriesAccount.
// The only role for an AdminAccount object is "Admin". "Admin" indicates that the AZGroceriesAccount belongs 
// to an employee of the AZGroceries online shop.
// The orderHistory of an AdminAccount is a OrderHistory object that represents the order history of the AdminAccount.
// Every AdminAccount object has an employeeID which is a String that represents the employee ID of the staff memebr working at 
// AZGroceries online shop.

public class AdminAccount extends AZGroceriesAccount {

    private String employeeID;

 // The public AdminAccount(String username, String Password, String employeeID) constructor 
// creates AdminAccount objects with 3 provided Strings (username, Password and employeeID)
//  super(username, Password, "Admin") uses the constructor of the AZGroceriesAccount super class to 
//  set the AdminAccount object's username to the provided String (username).
//  set the AdminAccount object's password to the provided String (Password).
// set the AdminAccount object's userCart to a new Cart object. 
// set the AdminAccount object's orderHistory a new OrderHistory object.
 // the role is set to "Admin" automatically.
 // the employeeID is set to the String provided (employeeID)
    AdminAccount(String username, String Password, String employeeID) {
        super(username, Password, "Admin");
        setEmployeeID(employeeID);

    }

    /**
     * @return the employeeID
     */
    
// The public String getEmployeeID() returns the AdminAccount object's employeeID in the form of a String.
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * @param employeeID the employeeID to set
     */
// The public void setEmployeeID(String employeeID) sets the AdminAccount object's employeeID to the provided String (employeeID).  
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

}
