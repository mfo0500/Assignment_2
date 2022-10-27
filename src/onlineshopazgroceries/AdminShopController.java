/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author krist
 */
public class AdminShopController implements ActionListener {

    AdminShopModel model;
    AdminShopView view;
    
    AdminShopController(AdminShopView view, AdminShopModel model)
    {
        this.view = view;
        this.model = model;
        this.view.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        switch(command)
                {
                    case "Search":
                        String userSearch = this.view.getSearchTextField().getText();
                        this.model.searchItem(userSearch);
                        break;
                        
                    case "View Cart":
                        this.model.viewCart();
                        break;
                    case "View Order History":
                        break;
                    case "Checkout":
                        break;
                    case "Add an Item":
                        break;
                    case "Remove an Item":
                        break;
                    case "Modify Item Quantity":
                        break;
                    default:
                        break;
                }
        
    }
    
}
