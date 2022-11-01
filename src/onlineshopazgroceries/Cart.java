/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author krist
 */
// The Cart is an object that stores a HashMap of GroceryItems with a certain quantity. 
// The Cart object also stores the total price of all the GroceryItems the user wants to buy. 
// It is essentially all the items the user is considering to buy and how much of each GroceryItem they want to buy
// A cart is a collection of items the user is planning to purchase as well as the amount of each grocery item they wish to purchase.
// A cart also contains the total cost of all the grocery items the user of the AZGroceriesAccount
public class Cart  {

    private double total;
    private HashMap<GroceryItems, Integer> itemsAdded;
    private GroceryItems[] items;
    private String state;

    Cart() {
        setTotal(0.00);
        setItemsAdded(new HashMap<GroceryItems, Integer>());

    }
    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the itemsAdded
     */
    public HashMap<GroceryItems, Integer> getItemsAdded() {
        return itemsAdded;
    }

    /**
     * @param itemsAdded the itemsAdded to set
     */
    public void setItemsAdded(HashMap<GroceryItems, Integer> itemsAdded) {
        this.itemsAdded = itemsAdded;
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
     * @return the items
     */
    public GroceryItems[] getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(GroceryItems[] items) {
        this.items = items;
    }



}
