/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries.Model;

import java.util.Observable;
import onlineshopazgroceries.ShopData;
import onlineshopazgroceries.ShopDatabase;

/**
 *
 * @author krist
 */
public class ShopModel extends Observable{
     public ShopDatabase database;
     public ShopData data;
     
     
     public ShopModel()
    {
        this.database = new ShopDatabase();
        this.database.databaseSetup();
        
    }
     
//      public void checkName(String username, String password,)
//    {
//        this.data.username = username; // stores username
//        
//        this.data = this.database.checkLogin(username, password);
//        
//        if(data.isSignedIn())
//        {
//          //  this.view.displayShop();
//            
//        }
//        this.setChanged();
//        this.notifyObservers(this.data);
//        
//        
//        
//    }
}
