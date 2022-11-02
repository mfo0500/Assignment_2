/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author krist
 */
public class OrderHistory {

    
    // The order history represents the grocery items the user of the AZGroceriesAccount pruchased in the past as well how much of each grocery item,
// the total cost of their purchse
    
    private HashMap<Cart, String> orderHistory;
    
    
    
    OrderHistory()
    {
        setOrderHistory(new HashMap<Cart, String>());
    }
    
    

    /**
     * @return the orderHistory
     */
    public HashMap<Cart, String> getOrderHistory() {
        return orderHistory;
    }

    /**
     * @param orderHistory the orderHistory to set
     */
    public void setOrderHistory(HashMap<Cart, String> orderHistory) {
        this.orderHistory = orderHistory;
    }

    
    
    
}
