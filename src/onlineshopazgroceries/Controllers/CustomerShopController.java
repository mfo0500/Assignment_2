/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries.Controllers;

import onlineshopazgroceries.Models.CustomerShopModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import onlineshopazgroceries.Views.CustomerShopView;

/**
 *
 * @author krist
 */
public class CustomerShopController implements ActionListener {

    CustomerShopModel model;
    CustomerShopView view;
    
    public CustomerShopController(CustomerShopView view, CustomerShopModel model)
    {
        this.view = view;
        this.model = model;
        this.view.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        int quantityTextFieldNumber = 0;
         String valueOfSelectedTextfield = "";
          
          String productNameRequested = "";
        if (command.contains("Add ")&& !command.equals("Add an Item") && !command.equals("Add Product to Inventory"))
        {
            productNameRequested = command;
            String stringToDelete = "Add ";
            int startingIndex = productNameRequested.indexOf(stringToDelete);
            int endingIndex = startingIndex + stringToDelete.length();

            StringBuilder stringBuilderAdd = new StringBuilder(productNameRequested);
            stringBuilderAdd.delete(startingIndex, endingIndex);
            productNameRequested = stringBuilderAdd.toString();
            command = "Add Product to Cart";
        }
        switch(command)
                {
                    case "Search":
                        String userSearch = this.view.getSearchTextField().getText();
                        this.model.searchItem(userSearch);
                        break;
                        
                    case "View Cart":
                        this.model.viewCart();
                        break;
                    case "-":
                        quantityTextFieldNumber = view.getAllMinusButtons().indexOf(e.getSource() ) ;
                        valueOfSelectedTextfield = view.getAllQuantityTextFields().get(quantityTextFieldNumber).getText();
                        this.model.adjustQuantityToPurchase(command, valueOfSelectedTextfield, quantityTextFieldNumber);
                        break;
                    case "+":
                        
                        int buttonNumber = view.getAllPlusButtons().indexOf(e.getSource());
                        quantityTextFieldNumber = buttonNumber  ;
                        valueOfSelectedTextfield = view.getAllQuantityTextFields().get(quantityTextFieldNumber).getText();
                        this.model.adjustQuantityToPurchase(command, valueOfSelectedTextfield, quantityTextFieldNumber);
                        break;
                    case "Add Product to Cart":
                        
                        int indexNumber = this.view.getAllAddItemButtons().indexOf(e.getSource());
                        String numberOfProductRequested = this.view.getAllQuantityTextFields().get(indexNumber).getText();
                        
                        this.model.addItemToCart(indexNumber, numberOfProductRequested);
                        break;
                    case "View Order History":
                        this.model.requestToViewOrderHistory();
                        break;
                    case "Checkout":
                        this.model.requestToCheckout();
                        break;
                    case "Return Back To Main Menu":
                        this.model.requestToReturnToMainMenu();
                        break;
                    default:
                        break;
                }
        
    }
    
}
