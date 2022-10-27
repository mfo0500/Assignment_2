/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

/**
 *
 * @author krist
 */
public class AdminShopModel  extends Observable implements IsValid{
    
    public AdminShopModel model;
    public ShopDatabase database;
    public ShopData data;
     private String state = "";
     
    
    AdminShopModel()
    {
        this.database = new ShopDatabase();
        this.database.databaseSetup();
        this.data = new ShopData();
    }
    
//    public void loadGroceries()
//    {
//        this.data.setListOfGroceries(this.database.loadGroceries());
//    }
    
    public void searchItem(String queryEntered)
    {
        if(IsValid(queryEntered))
        {
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
    
    public void viewCart()
    {
        
    }
    
    public void addGroceryItem(String enteredProductName, String enteredProductPrice, String enteredProductQuantity)
    {
        
    }

    @Override
    public boolean IsValid(String choice) {
         boolean valid = true;
        if(getState().equals("search"))
        {
            if(choice.isEmpty())
            {
                valid = false;
                String reasonSearchFailed = "Search bar cannot be empty. Please try agagin";
                data.setReasonSearchFailed(reasonSearchFailed);
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
