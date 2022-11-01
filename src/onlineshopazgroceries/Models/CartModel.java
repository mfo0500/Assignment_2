/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries.Models;

import java.util.ArrayList;
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
public class CartModel extends Observable implements IsValid {

    public CartModel model;
    public ShopDatabase database;
    public ShopData data;
    private String state = "";

    public CartModel() {
        this.database = new ShopDatabase();
        this.database.databaseSetup();
        this.data = new ShopData();

    }

    public void removeItemFromCart(String productNameToRemove) {
        GroceryItems originalItem = null;

        ArrayList<GroceryItems> cartGroceryItems = new ArrayList<>();
        for (GroceryItems g : data.getUserAccount().getUserCart().getItemsAdded().keySet()) {
            cartGroceryItems.add(g);
        }

        for (GroceryItems g : cartGroceryItems) {
            if (productNameToRemove.equals(g.getProductName())) {
                originalItem = g;
            }
        }

        System.out.println("Product Name of product to remove: " + originalItem.getProductName());
        System.out.println("Product Price of product to remove: " + originalItem.getPrice());
        System.out.println("Quantity of product to remove: " + data.getUserAccount().getUserCart().getItemsAdded().get(originalItem));

        int numberOfProductToRemove = data.getUserAccount().getUserCart().getItemsAdded().get(originalItem);

        this.data = this.database.removeGroceryItemFromCart(this.data, data.getUserAccount().getUsername(), originalItem, numberOfProductToRemove);

        this.setChanged();
        this.notifyObservers(this.data);
    }

    public void saveCartQuantityChanges(int saveButtonNumber, String productNameOfUpdatedCartItem, String updatedValueToPurchase) {
        setState("checking saved number of items");
        GroceryItems originalItem = null;
        
         ArrayList<GroceryItems> cartGroceryItems = new ArrayList<>();
            for (GroceryItems g : data.getUserAccount().getUserCart().getItemsAdded().keySet()) {
                cartGroceryItems.add(g);
            }

            for (GroceryItems g : cartGroceryItems) {
                if (productNameOfUpdatedCartItem.equals(g.getProductName())) {
                    originalItem = g;
                }
            }
            data.setCartItemRequestedForUpdate(originalItem);
        
        if (!IsValid(updatedValueToPurchase)) {
            data.setCartUpdated(false);

            this.setChanged();
            this.notifyObservers(this.data);
        }
        if (IsValid(updatedValueToPurchase)) {
            
            
            
            int updatedQuantityToPurchase = Integer.parseInt(updatedValueToPurchase);
            System.out.println("Product Name of updated Item: " + originalItem.getProductName());
            System.out.println("Product Price of updated Item: " + originalItem.getPrice());
            System.out.println("Updated value of updated Item: " + updatedQuantityToPurchase);
            if (updatedQuantityToPurchase == 0) {
                        removeItemFromCart( productNameOfUpdatedCartItem);
            }
            if (updatedQuantityToPurchase > 0) {
                
               this.data = this.database.updateCart(this.data, data.getUserAccount().getUsername(), originalItem, updatedQuantityToPurchase);
               data.setUpdatedCartItemQuantityPurchasedLabelNumber(saveButtonNumber);

            }

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
        if (IsValid(textFieldValue))// if the corresponding TextField contains an integer 
        {

            if (addOrSubtract.equals("+")) { // if the + button has been pushed
                updatedValue = Integer.parseInt(textFieldValue) + 1; // the value of the corresponding textfield will be increased by 1

                data.setNumberOfUpdatedQuantityTextField(quantityTextFieldNumber);
                data.setUpdatedQuantityTextFieldValue(updatedValue);
            } else if (addOrSubtract.equals("-")) {

                if (Integer.parseInt(textFieldValue) <= 0) // if the corresponding TextField value is less than or equal to 0 and the - button has been pushed
                {
                    updatedValue = 1; // the value of the corresponding textfield will be set to 0
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
         this.data.setViewOrderHistoryRequested(false);
        this.data.setRequestingToCheckout(false);
        this.data.setItemAdded(false);
        this.data.setRequestingToModifyItemQuantityInShop(false);
        this.data.setRequestingToRemoveItem(false);
        this.data.setViewCartRequested(false);
        this.data.setItemAddedToCart(false);
            
       this.setChanged();
        this.notifyObservers(this.data);     
    }
    
    @Override
    public boolean IsValid(String choice) {

        boolean valid = true;
        try {

            if (getState().equals("checking saved number of items")) {
                data.setReasonCartUpdateFailed("");

                int quantity = Integer.parseInt(choice);
                if (quantity < 0) {
                    valid = false;
                    data.setReasonCartUpdateFailed("updated quantity to purchase must atleast 0");
                }
                if (quantity > data.getCartItemRequestedForUpdate().getQuantityAvailable() ) {
                    valid = false;
                    data.setReasonCartUpdateFailed("updated quantity to purchase must greater than 0 and less than the quantity available");

                }
                if(quantity>=0 && quantity <= data.getCartItemRequestedForUpdate().getQuantityAvailable())
                {
                    valid = true;
                }

            }
        } catch (NumberFormatException | InputMismatchException e) {

            valid = false;
            data.setReasonCartUpdateFailed("updated quantity to purchase must be an Integer");
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
