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
public class OrderHistory implements IsValid{
    
    // The order history represents the grocery items the user of the AZGroceriesAccount pruchased in the past as well how much of each grocery item,
// the total cost of their purchse
    
    private HashMap<Cart, String> orderHistory;
    
    OrderHistory()
    {
        setOrderHistory(new HashMap<Cart, String>());
    }
    
    
    public void displayOrderHistory( OnlineShopAZGroceries shop)
    {
        String orderHistoryString = "";
        if(getOrderHistory().isEmpty())
        {
            System.out.println("You have no previous purchases");
            System.out.println("You will be delivered to the homepage");
            shop.openStore(shop);
        }
        else
        {
            for ( Cart c : getOrderHistory().keySet())
            {
                orderHistoryString += c.toString(shop);
                orderHistoryString += ". Purchased on: " + shop.getUserAccount().getOrderHistory().getOrderHistory().get(c);
            }
             
        }
        System.out.println(orderHistoryString);
        System.out.println("\nPress / to go back\nPress x or X to exit");
        Scanner scanChoice = new Scanner(System.in);
        String inputChoice = scanChoice.nextLine();
        while(!IsValid(shop, inputChoice))
        {
            inputChoice = scanChoice.nextLine();
        }
        if(IsValid(shop, inputChoice))
        {
            shop.openStore(shop);
        }
        
        
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

    // The public boolean IsValid(OnlineShopAZGroceries shop, String choice) is the implementation of the interface IsValidusing the 
    //  public boolean IsValid(OnlineShopAZGroceries shop, String choice) function prototype
    // This function checks the validity of the users reponse provided (String choice) to every situation where a user is asked for a response 
    @Override
    public boolean IsValid(OnlineShopAZGroceries shop, String choice) {
          boolean isValid = false;
        
        try {
            if (choice.equals("x") || choice.equals("X")) {
            System.exit(0);
        }
        if(choice.equals("/"))
        {
           isValid = true;
        }


        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println("Invalid input. Please input again.");
            isValid = false;
        }
        return isValid; 
    }
    
    
    
}
