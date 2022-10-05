/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

import java.util.HashMap;

/**
 *
 * @author krist
 */

// The AZGroceriesAccount object represents an account of the AZGroceries online shop.
// Every AZGroceriesAccount object has a username, password, userCart, role and orderHistory.
// The username of an AZGroceriesAccount is a String that represents the username of the AZGroceriesAccount object.
// The password of an AZGroceriesAccount is a String that represents the password of the AZGroceriesAccount object.
// The userCart of an AZGroceriesAccount is a Cart object that represents the cart of the AZGroceriesAccount object. 
// The role of an AZGroceriesAccount is a String that represents the type of AZGroceriesAccount.
// There are two roles, "Customer" and "Admin". "Customer" indicates that the AZGroceriesAccount belongs to a customer of the AZGroceries
// online shop. "Admin" indicates that the AZGroceriesAccount belongs to an employee of the AZGroceries online shop.
// The orderHistory of an AZGroceriesAccount is a OrderHistory object that represents the order history of the AZGroceriesAccount.

public class AZGroceriesAccount 
{
    
    private String username;
    private String password;
    private Cart userCart;
    private String role;
    private OrderHistory orderHistory;
    

// The public AZGroceriesAccount(String username, String Password, String role) constructor 
// creates AZGroceriesAccount objects with 3 provided Strings (username, Password and role)
// setUsername(username) sets the AZGroceriesAccount object's username to the provided String (username).
// setPassword(password) sets the AZGroceriesAccount object's password to the provided String (Password).
// setUserCart(new Cart()) sets the AZGroceriesAccount object's userCart to the provided Cart object. 
// In this case, it is a new empty Cart object.
// setOrderHistory(new OrderHistory()) sets the AZGroceriesAccount object's orderHistory to the provided OrderHistory object.
// In this case, it is a new empty OrderHistory object.
// setRole(role) sets the AZGroceriesAccount object's role to the prodived String (role)
public AZGroceriesAccount(String username, String Password, String role)
{
    setUsername(username);
    setPassword(Password);
    setUserCart(new Cart());
    setOrderHistory(new OrderHistory());
    setRole(role);
}
    /**
     * @return the username
     */
// The public String getUsername() returns the AZGroceriesAccount object's username in the form of a String.
    public String getUsername() {
        return username;
    }
    

    /**
     * @param username the username to set
     */
// The public void setUsername(String username) sets the AZGroceriesAccount object's username to the provided String (username).
    public void setUsername(String username) {
        if(username != null)
        {
            this.username = username;
        }
        
    }

    /**
     * @return the password
     */
// The public String getPassword() returns the AZGroceriesAccount object's password in the form of a String.
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
// The public void setPassword(String password) sets the AZGroceriesAccount object's password to the provided String (password).
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the userCart
     */
// The public Cart getUserCart() returns the AZGroceriesAccount object's userCart in the form of a Cart object.
    public Cart getUserCart() {
        return userCart;
    }

    /**
     * @param userCart the userCart to set
     */
// The public void setUserCart(Cart userCart) sets the AZGroceriesAccount object's userCart to the provided Cart object (userCart).

    public void setUserCart(Cart userCart) {
        this.userCart = userCart;
    }

    /**
     * @return the orderHistory
     */
 // The public OrderHistory getOrderHistory() returns the AZGroceriesAccount object's orderHistory in the form of a OrderHistory object.
    public OrderHistory getOrderHistory() {
        return orderHistory;
    }

    /**
     * @param orderHistory the orderHistory to set
     */
    // The public void setOrderHistory(OrderHistory orderHistory) sets the AZGroceriesAccount object's orderHistory 
    // to the provided OrderHistory object (orderHistory).
    public void setOrderHistory(OrderHistory orderHistory) {
        this.orderHistory = orderHistory;
    }

    /**
     * @return the role
     */
// The public String getRole() returns the AZGroceriesAccount object's role in the form of a String.
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
// The public void setRole(String role) sets the AZGroceriesAccount object's role to the provided String (role).
    public void setRole(String role) {
        this.role = role;
    }
    
}
