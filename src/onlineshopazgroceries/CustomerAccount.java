/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

/**
 *
 * @author krist
 */
// The CustomerAccount object represents a customer account of the AZGroceries online shop.
// The CustomerAccount extends the AZGroceriesAccount class, inheriting variables such as username, password, userCart, role and orderHistory.
// The username of an CustomerAccount is a String that represents the username of the CustomerAccount object.
// The password of an CustomerAccount is a String that represents the password of the CustomerAccount object.
// The userCart of an CustomerAccount is a Cart object that represents the cart of the CustomerAccount object. 
// The role of an CustomerAccount is a String that represents the type of AZGroceriesAccount.
// The only role for an CustomerAccount object is "Customer". "Customer" indicates that the AZGroceriesAccount belongs 
// to an customer of the AZGroceries online shop.
// The orderHistory of an CustomerAccount is a OrderHistory object that represents the order history of the CustomerAccount.
// Every CustomerAccount object has an employeeID which is a String that represents the employee ID of the staff memebr working at 
// AZGroceries online shop.
public class CustomerAccount extends AZGroceriesAccount {

 // The public CustomerAccount(String username, String Password, String employeeID) constructor 
// creates AdminAccount objects with 3 provided Strings (username, Password and "Customer")
//  super(username, Password, "Customer") uses the constructor of the AZGroceriesAccount super class to 
//  set the CustomerAccount object's username to the provided String (username).
//  set the CustomerAccount object's password to the provided String (Password).
// set the CustomerAccount object's userCart to a new Cart object. 
// set the CustomerAccount object's orderHistory a new OrderHistory object.
 // the role is set to "Customer" automatically.
    CustomerAccount(String username, String Password) {
        super(username, Password, "Customer");

    }


}
