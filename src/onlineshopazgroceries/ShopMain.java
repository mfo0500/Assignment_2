/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

import onlineshopazgroceries.Views.SignInServiceView;
import onlineshopazgroceries.Controllers.SignInServiceController;
import onlineshopazgroceries.Models.SignInServiceModel;


/**
 *
 * @author krist
 */
public class ShopMain {
    
    public static void main(String[] args) {
        SignInServiceView view = new SignInServiceView();
        
        SignInServiceModel model = new SignInServiceModel();
        SignInServiceController controller = new SignInServiceController(view, model);
        
        model.addObserver(view);
    }
    
}
