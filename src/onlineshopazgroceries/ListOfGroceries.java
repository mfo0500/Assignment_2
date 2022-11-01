/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author krist
 */
public class ListOfGroceries implements IsValid {
    
    private LinkedList<GroceryItems> Groceries;
    private String state;

    ListOfGroceries()
    {
        setGroceries(new LinkedList<GroceryItems>());
    }
    
    /**
     * @return the Groceries
     */
    public LinkedList<GroceryItems> getGroceries() {
        return this.Groceries;
    }

    /**
     * @param Groceries the Groceries to set
     */
    public void setGroceries(LinkedList<GroceryItems> Groceries) {
        this.Groceries = Groceries;
    }


//        while (!IsValid(shop, itemChosen)) {
//            itemChosen = scan.nextLine();
//        }
//        if (IsValid(shop, itemChosen)) {
//            int removedItem = Integer.parseInt(itemChosen) - 1;
//            shop.getGroceryList().getGroceries().remove(removedItem);
//            System.out.println("GroceryItem has been sucessfully removed");
//            updateGroceries();
//            shop.openStore(shop);
//        }
    
    
    public void modifyQuantity(OnlineShopAZGroceries shop)
    {
        System.out.println("Please choose the item you want to to modify the quantiry of");
        System.out.println(toString());
        Scanner scan = new Scanner(System.in);
        String itemChosen = scan.nextLine();
        setState("modify quantity");
//        while(!IsValid(shop, itemChosen))
//        {
//            itemChosen = scan.nextLine();
//        }
//        if(IsValid(shop, itemChosen))
//        {
//            int selectedItem = Integer.parseInt(itemChosen) - 1 ;
//            
//            System.out.println("Please enter the desired quantity");
//            setState("enter desired quantity");
//            String desiredQuantity = scan.nextLine();
//            while(!IsValid(shop, desiredQuantity))
//            {
//                System.out.println("Invalid input");
//                System.out.println("Please enter the desired quantity");
//                desiredQuantity = scan.nextLine();
//            }
//            if(IsValid(shop, desiredQuantity))
//            {
//                int selectedQuantity = Integer.parseInt(desiredQuantity);
//                GroceryItems modifiedItem = shop.getGroceryList().getGroceries().get(selectedItem);
//                modifiedItem.setQuantityAvailable(selectedQuantity);
//                updateGroceries();
//                System.out.println("The selected item has been sucessfully modified");
//                shop.openStore(shop);
//            }
//        }
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

    @Override
    public boolean IsValid(String choice) {

        boolean isValid = false;
         if (choice.equals("x") || choice.equals("X")) {
            System.exit(0);
        }
        if (choice.equals("/")) {
            if(getState().equals("enter product name"))
            {
              //   display store
            }
            
        }
        try {
            
            
            if (getState().contentEquals("enter product name")) {
                isValid = true;
                boolean isBlank = false;
                boolean alreadyExists = false;
                if (choice.isBlank() || choice.isEmpty()) {
                    
                    isBlank = true;
                    System.out.println("Input can not be null or blank. Please try again.");
                }
//                for (GroceryItems g : shop.getGroceryList().getGroceries()) {
//                    
//                    if (g.getProductName().equalsIgnoreCase(choice.trim())) {
//                        alreadyExists = true;
//                        System.out.println("Grocery item already exists. If you want to increase the quantity available for that grocery item go to \"Mofidy the quantity of an item\" Please try again.");
//                    }
//                }
                boolean tooShort = false;
                if (choice.trim().length() < 3) {
                    tooShort = true;
                    System.out.println("Product name entered is too short. Please try again");
                    
                }
                if (isBlank || alreadyExists || tooShort) {
                    isValid = false;
                }
                else
                {
                    
                }
            }
            if (getState().equals("enter product price")) {
                double price = Double.parseDouble(choice);
                if (price > 0.00) {
                    isValid = true;
                } else {
                    isValid = false;
                    System.out.println("Product price cannot be free or negative. Please try again");
                }
            }
            if (getState().equals("enter product category")) {
                boolean isBlank = false;
                isValid = true;
                if (choice.isBlank() || choice.isEmpty()) {
                    
                    isBlank = true;
                    System.out.println("Input can not be null or blank. Please try again.");
                }
                boolean tooShort = false;
                if (choice.trim().length() < 3) {
                    tooShort = true;
                    System.out.println("Product name entered is too short. Please try again");
                    
                }
                if (isBlank || tooShort) {
                    isValid = false;
                }
            }
            if (getState().equals("enter product quantity")) {
                int decision = Integer.parseInt(choice);
                if (decision > 0.00) {
                    isValid = true;
                } else {
                    isValid = false;
                    System.out.println("Product added cannot have no quantity available. Please try again");
                }
            }
            if(getState().equals("modify quantity") || getState().equals("remove item") )
            {
                int decision = Integer.parseInt(choice);
                if(decision >=1 && decision<= getGroceries().size())
                {
                    isValid = true;
                }
            }
            if(getState().equals("enter desired quantity"))
            {
                int decision = Integer.parseInt(choice);
                if(decision >=0)
                {
                    isValid = true;
                }
                else
                {
                    System.out.println("Desired quantity cannot be negative");

                }
            }
            
        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println("Invalid input. Please input again.");
            isValid = false;
        }
        return isValid;    }
    
}
