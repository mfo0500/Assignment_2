/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries.Models;

import java.util.Observable;
import onlineshopazgroceries.IsValid;
import onlineshopazgroceries.ShopData;
import onlineshopazgroceries.ShopDatabase;

/**
 *
 * @author krist
 */
public class CheckoutServiceModel extends Observable implements IsValid{

       public CheckoutServiceModel model;
    public ShopDatabase database;
    public ShopData data;
    private String state = "";
    
    public CheckoutServiceModel()
    {
        this.database = new ShopDatabase();
        this.database.databaseSetup();
        this.data = new ShopData();
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
        this.data.setViewOrderHistoryRequested(false);
        this.data.setRequestingToAddItem(false);
        this.data.setUserSearching(false);
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
