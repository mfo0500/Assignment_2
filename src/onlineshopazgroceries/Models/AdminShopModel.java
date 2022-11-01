/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries.Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Observable;
import onlineshopazgroceries.GroceryItems;
import onlineshopazgroceries.IsValid;
import onlineshopazgroceries.ShopData;
import onlineshopazgroceries.ShopDatabase;

/**
 *
 * @author krist
 */
public class AdminShopModel extends Observable implements IsValid {

    public AdminShopModel model;
    public ShopDatabase database;
    public ShopData data;
    private String state = "";

    public AdminShopModel() {
        this.database = new ShopDatabase();
        this.database.databaseSetup();
        this.data = new ShopData();
    }

//    public void loadGroceries()
//    {
//        this.data.setListOfGroceries(this.database.loadGroceries());
//    }
    public void searchItem(String queryEntered) {
        setState("search");

        data.setAttemptingSearch(true);

        if (IsValid(queryEntered)) {
            ArrayList<GroceryItems> searchedItems = new ArrayList<>();
            for (GroceryItems g : data.getListOfGroceries().getGroceries()) {
                if (g.getProductName().toLowerCase().contains(queryEntered.toLowerCase()) || g.getCategory().toLowerCase().contains(queryEntered.toLowerCase())) {
                    searchedItems.add(g);
                }
            }
            Collections.sort(searchedItems);
            data.setSearchedItems(searchedItems);
            data.setSearchQuery(queryEntered);
            data.setUserSearching(true);
        }

        this.setChanged();
        this.notifyObservers(this.data);
    }

    public void viewCart() {
        
        this.data = this.database.loadUserCart(this.data);
        data.setViewCartRequested(true);
        
        this.setChanged();
        this.notifyObservers(this.data);
    }

    public void requestToAddItem() {
        data.setRequestingToAddItem(true);

        this.setChanged();
        this.notifyObservers(this.data);
    }

    public void requestToRemoveItem() {
        data.setRequestingToRemoveItem(true);

        this.setChanged();
        this.notifyObservers(this.data);
    }
    
    public void requestToModifyItemQuantity()
    {
        data.setRequestingToModifyItemQuantityInShop(true);
        
        this.setChanged();
        this.notifyObservers(this.data);
        
    }
    
    public void modifyQuantityOfGroceryItem(String adjustedValueOfQuantityAvilable, int numberOfGroceryItemModified)
    {
        setState("validifying adjusted quantity avilable");
        if(!IsValid(adjustedValueOfQuantityAvilable))
        {
            data.setItemQuantityModified(false);
        }
        else
        {
            
            int adjustedQuantityAvilable = Integer.parseInt(adjustedValueOfQuantityAvilable);
            String productNameOfSelectedGroceryItem = data.getListOfGroceries().getGroceries().get(numberOfGroceryItemModified).getProductName();
//            System.out.println("Product Name of modified item : "+ productNameOfSelectedGroceryItem);
//            System.out.println("Modified Quantity of modified item : "+ adjustedQuantityAvilable);
            this.data = this.database.modifyGroceryItemQuantity(this.data, productNameOfSelectedGroceryItem, adjustedQuantityAvilable, numberOfGroceryItemModified);
        }
        
        this.setChanged();
        this.notifyObservers(this.data);
    }

    public void adjustQuantityToPurchase(String addOrSubtract, String textFieldValue, int quantityTextFieldNumber) {

        setState("checking TextField value");
        int updatedValue = 0;
        if (!IsValid(textFieldValue)) {
            if (addOrSubtract.equals("+")) { // if the + button has been pushed and the corresponding TextField value is not a number

                updatedValue = 1; // the value of the corresponding textfield will be set to 1
                data.setNumberOfUpdatedQuantityTextField(quantityTextFieldNumber);
                data.setUpdatedQuantityTextFieldValue(updatedValue);

            } else if (addOrSubtract.equals("-")) { // if the - button has been pushed
                updatedValue = 0; // the value of the corresponding textfield will be set to 1
                data.setNumberOfUpdatedQuantityTextField(quantityTextFieldNumber);
                data.setUpdatedQuantityTextFieldValue(updatedValue);
            }
        } 
        if(IsValid(textFieldValue))// if the corresponding TextField contains an integer 
        {
            if (addOrSubtract.equals("+")) { // if the + button has been pushed
                updatedValue = Integer.parseInt(textFieldValue) + 1; // the value of the corresponding textfield will be increased by 1

                data.setNumberOfUpdatedQuantityTextField(quantityTextFieldNumber);
                data.setUpdatedQuantityTextFieldValue(updatedValue);
            } else if (addOrSubtract.equals("-")) {

                if (Integer.parseInt(textFieldValue) <= 0) // if the corresponding TextField value is less than or equal to 0 and the - button has been pushed
                {
                    updatedValue = 0; // the value of the corresponding textfield will be set to 0
                    data.setNumberOfUpdatedQuantityTextField(quantityTextFieldNumber);
                    data.setUpdatedQuantityTextFieldValue(updatedValue);
                } else if (Integer.parseInt(textFieldValue) > 0) {
                    updatedValue = Integer.parseInt(textFieldValue) - 1; // the value of the corresponding textfield will be decreased by 1
                    data.setNumberOfUpdatedQuantityTextField(quantityTextFieldNumber);
                    data.setUpdatedQuantityTextFieldValue(updatedValue);
                }

            }
        }

        data.setQuantityTextFieldUpdated(true);

        this.setChanged();
        this.notifyObservers(this.data);

    }

    public void checkItemAdded(String productName, String productCategory, String productPrice, String quantityAvailable) {
        setState("Check product name");

        if (!IsValid(productName)) {
            data.setItemAdded(false);

            // entered product name is not valid
        } else {
            setState("Check product price");
            if (!IsValid(productPrice)) {
                data.setItemAdded(false);

                // product price is not valid
            } else {
                double priceOfProduct = Double.parseDouble(productPrice);
                setState("Check product category");
                if (!IsValid(productCategory)) {
                    data.setItemAdded(false);

                    // enetered product category is not valid
                } else {
                    setState("Check quantity available");
                    if (!IsValid(quantityAvailable)) {
                        data.setItemAdded(false);
                    } else {
                        int quantityOfProduct = Integer.parseInt(quantityAvailable);
                        this.data = this.database.addGroceryItem(this.data, productName, priceOfProduct, productCategory, quantityOfProduct);
                    }

                }

            }
        }

        this.setChanged();
        this.notifyObservers(this.data);

    }

    public void removeItem(String productName) {
        this.data = this.database.removeGroceryItem(this.data, productName);

        this.setChanged();
        this.notifyObservers(this.data);
    }
    
    public void addItemToCart(int indexNumberOfProductRequested, String numberOfProductRequested)
    {
        setState("verifying quantity being added to cart");
        GroceryItems groceryItemRequested = data.getListOfGroceries().getGroceries().get(indexNumberOfProductRequested);
        data.setGroceryItemRequestedForCart(groceryItemRequested);
        if(IsValid(numberOfProductRequested))
        {
            int quantityAdded = Integer.parseInt(numberOfProductRequested);
           
        String usernameOfCustomer = data.getUserAccount().getUsername();
        this.data = this.database.addGroceryItemToCart(this.data, usernameOfCustomer, groceryItemRequested, quantityAdded);
        }
        if(!IsValid(numberOfProductRequested))
        {
            this.data.setItemAdded(false);
        }
        
        this.setChanged();
        this.notifyObservers(this.data);
    }
    
    public void requestToCheckout()
    {
        data.setRequestingToCheckout(true);
        
        this.setChanged();
        this.notifyObservers(this.data);
    }
    
    public void requestToReturnToMainMenu()
    {
        this.data.setNeverPressedMainMenuButton(false);
        this.data.setListOfGroceries(this.database.loadGroceries());
        this.data = this.database.loadUserCart(data);
        this.data.setCartUpdated(false);
        this.data.setAttemptingSearch(false);
        this.data.setItemQuantityModified(false);
        this.data.setItemRemoved(false);
        this.data.setItemRemovedFromCart(false);
        this.data.setMainMenuRequested(true);
        this.data.setQuantityTextFieldUpdated(false);
        this.data.setRequestingToAddItem(false);
        this.data.setUserSearching(false);
        this.data.setRequestingToCheckout(false);
        this.data.setItemAdded(false);
         this.data.setViewOrderHistoryRequested(false);
        this.data.setRequestingToModifyItemQuantityInShop(false);
        this.data.setRequestingToRemoveItem(false);
        this.data.setViewCartRequested(false);
        this.data.setItemAddedToCart(false);
        this.data.setPurchaseRequested(false);
        this.data.setPurcahseSucessful(false);
            
       this.setChanged();
        this.notifyObservers(this.data);     
    }
    public void requestToViewOrderHistory()
    {
        this.data = this.database.loadUserCart(this.data);
        data.setViewOrderHistoryRequested(true);
        
         this.setChanged();
        this.notifyObservers(this.data);
    }

    @Override
    public boolean IsValid(String choice) {
        boolean valid = true;
        data.setReasonItemAdditionFailed("");
        data.setReasonItemQuantityModificationFailed("");

        try
        {
            
        
        if (getState().equals("search")) {
            if (choice.isEmpty()) {
                valid = false;
                String reasonSearchFailed = "Search bar cannot be empty. Please try agagin";
                data.setUserSearching(false);
                data.setReasonSearchFailed(reasonSearchFailed);
            }
        }
        if (getState().equals("Check product name") || getState().equals("Check product category")) {
            if (choice.isEmpty()) {
                valid = false;
                if (getState().equals("Check product name")) {
                    data.setReasonItemAdditionFailed("Product name cannot be blank");
                }
                if (getState().equals("Check product category")) {
                    data.setReasonItemAdditionFailed(data.getReasonItemAdditionFailed() + ". Product category cannot be blank");
                }

            }
            if (choice.length() < 3) {

                valid = false;
                if (getState().equals("Check product name")) {
                    data.setReasonItemAdditionFailed(data.getReasonItemAdditionFailed() + ". Product name is too short");
                }
                if (getState().equals("Check product category")) {
                    data.setReasonItemAdditionFailed(data.getReasonItemAdditionFailed() + ". Product category is too short");
                }

            }
            if (choice.length() > 50) {
                valid = false;
                if (getState().equals("Check product name")) {
                    data.setReasonItemAdditionFailed(data.getReasonItemAdditionFailed() + ". Product name is too long");
                }
                if (getState().equals("Check product category")) {
                    data.setReasonItemAdditionFailed(data.getReasonItemAdditionFailed() + ". Product category is too long");
                }
            }

        }
        if(getState().equals("validifying adjusted quantity avilable"))
        {
             int modifiedQuantity = Integer.parseInt(choice);
            if (modifiedQuantity >= 0) {
                valid = true;
            } else {
                valid = false;
                data.setReasonItemQuantityModificationFailed( " Quantity available cannot be zero negative. Please try again");
            }
            
        }
        if (getState().equals("Check product price")) {
           
                 double price = Double.parseDouble(choice);
                
                if (price > 0.00) {
                valid = true;
            } else {
                valid = false;
                data.setReasonItemAdditionFailed(data.getReasonItemAdditionFailed() + ". Product price cannot be free or negative. Please try again");
            }

           
            
        }
        if (getState().equals("Check quantity available")) {
            
                int quantity = Integer.parseInt(choice);
            if (quantity > 0) {
                valid = true;
            } else {
                valid = false;
                data.setReasonItemAdditionFailed(data.getReasonItemAdditionFailed() + " Quantity available cannot be zero negative. Please try again");
            }

        }
        if (getState().equals("checking TextField value")) {
           
                int quantity = Integer.parseInt(choice);
                valid = true;
        }
        if(getState().equals("verifying quantity being added to cart"))
        {
            int quantity = Integer.parseInt(choice);
            if(quantity<=0)
            {
                
                valid = false;
                data.setReasonCartAdditionFailed("Product quantity needs to be greater than 0");
            }
            if(quantity> data.getGroceryItemRequestedForCart().getQuantityAvailable())
            {
                valid = false;
                data.setReasonCartAdditionFailed("Product quantity needs be be less than or equal to the quantity available");
            }
            if(quantity<=data.getGroceryItemRequestedForCart().getQuantityAvailable() && quantity>0)
            {
                valid = true;
            }
        }
        }
        catch(NumberFormatException | InputMismatchException e)
        {
            valid = false;
            if (getState().equals("Check quantity available")) {
                    data.setReasonItemAdditionFailed(data.getReasonItemAdditionFailed() + ". The product price needs to be a number");
                }
            if (getState().equals("Check product price")) {
                    data.setReasonItemAdditionFailed(data.getReasonItemAdditionFailed() + ". The product price needs to be a number");
                }
            if(getState().equals("validifying adjusted quantity avilable"))
            {
                data.setReasonItemQuantityModificationFailed("The quantity available needs to be a number");
            }
            if(getState().equals("verifying quantity being added to cart"))
                    {
                        data.setReasonCartAdditionFailed("Product quantity needs to be an Integer");
                    }
        }

        return valid;
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
}
