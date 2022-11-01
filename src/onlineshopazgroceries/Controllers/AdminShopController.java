/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries.Controllers;

import onlineshopazgroceries.Views.AdminShopView;
import onlineshopazgroceries.Models.AdminShopModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author krist
 */
public class AdminShopController implements ActionListener {

    AdminShopModel model;
    AdminShopView view;
    
    public AdminShopController(AdminShopView view, AdminShopModel model)
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
        String productNameToRemove = "";
        String productNameRequested = "";
        if(this.view.getAllModifyItemQuantityButtons().contains(e.getSource()))
        {
            
            command = "Modify Item Quantity (official)";
        }
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
        if (command.contains("Remove ")&& !command.equals("Remove an Item"))
        {
            productNameToRemove = command;
            String stringToDelete = "Remove ";
            int startingIndex = productNameToRemove.indexOf(stringToDelete) ;
            int endingIndex = startingIndex + stringToDelete.length();

            StringBuilder stringBuilder = new StringBuilder(productNameToRemove);
            stringBuilder.delete(startingIndex, endingIndex);
            
            productNameToRemove = stringBuilder.toString();
            command = "Remove Product from Inventory";
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
                    case "View Order History":
                        this.model.requestToViewOrderHistory();
                        break;
                    case "Checkout":
                        this.model.requestToCheckout();
                        break;
                    case "Add an Item":
                        this.model.requestToAddItem();
                        break;
                    case "Remove an Item":
                        this.model.requestToRemoveItem();
                        break;
                    case "Modify Item Quantity":
                        this.model.requestToModifyItemQuantity();
                        break;
                    case "Modify Item Quantity (official)":
                       
                        int modifyQuantityTextFieldNumber = view.getAllModifyItemQuantityButtons().indexOf(e.getSource() ) ;
                        String adjustedQuantityAvailable = view.getAllModifyItemQuantityTextFields().get(modifyQuantityTextFieldNumber).getText();
                        this.model.modifyQuantityOfGroceryItem(adjustedQuantityAvailable, modifyQuantityTextFieldNumber);
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
                    case "Remove Product from Inventory":
                        this.model.removeItem(productNameToRemove);
                        break;
                    case "Add Product to Inventory":
                        String productName = this.view.getProductNameTextField().getText();
                        String productCategory = this.view.getProductCategoryTextField().getText();
                        String productPrice = this.view.getProductPriceTextField().getText();
                        String productQuantity = this.view.getProductQuantityTextField().getText();
                        this.model.checkItemAdded(productName, productCategory, productPrice, productQuantity);
                        
                     break;
                    case "Return Back To Main Menu":
                        this.model.requestToReturnToMainMenu();
                    break;
                    default:
                    break;
                }
        
    }
    
}
