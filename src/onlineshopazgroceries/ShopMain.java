/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

import onlineshopazgroceries.Model.ShopModel;

/**
 *
 * @author krist
 */
public class ShopMain {
    
    public static void main(String[] args) {
        ShopView view = new ShopView();
        SignInServiceModel model = new SignInServiceModel();
        ShopController controller = new ShopController(view, model);
        
        model.addObserver(view);
    }
    
}
