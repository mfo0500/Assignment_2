/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries.Controllers;

import onlineshopazgroceries.Views.CartView;
import onlineshopazgroceries.Models.CartModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author krist
 */
public class CartController implements ActionListener {

    CartModel model;
    CartView view;

    public CartController(CartView view, CartModel model) {
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
        if (command.contains("Remove ")) {
            productNameToRemove = command;
            String stringToDelete = "Remove ";
            int startingIndex = productNameToRemove.indexOf(stringToDelete);
            int endingIndex = startingIndex + stringToDelete.length();

            StringBuilder stringBuilder = new StringBuilder(productNameToRemove);
            stringBuilder.delete(startingIndex, endingIndex);

            productNameToRemove = stringBuilder.toString();
            command = "Remove Product from Cart";
        }
        switch (command) {
            case "-":
                quantityTextFieldNumber = view.getAllMinusButtons().indexOf(e.getSource());
                valueOfSelectedTextfield = view.getAllQuantityToPurchaseTextFields().get(quantityTextFieldNumber).getText();
                this.model.adjustQuantityToPurchase(command, valueOfSelectedTextfield, quantityTextFieldNumber);
                break;
            case "+":

                int buttonNumber = view.getAllPlusButtons().indexOf(e.getSource());
                quantityTextFieldNumber = buttonNumber;
                valueOfSelectedTextfield = view.getAllQuantityToPurchaseTextFields().get(quantityTextFieldNumber).getText();
                this.model.adjustQuantityToPurchase(command, valueOfSelectedTextfield, quantityTextFieldNumber);
                break;
            case "Remove Product from Cart":

                this.model.removeItemFromCart(productNameToRemove);
                break;
            case "Save":
                int saveButtonNumber = view.getAllSaveButtons().indexOf(e.getSource());
                String textOfCorrespondingRemoveButton = view.getAllRemoveProductButtons().get(saveButtonNumber).getText();
                String stringToDelete = "Remove ";
                int startingIndex = textOfCorrespondingRemoveButton.indexOf(stringToDelete);
                int endingIndex = startingIndex + stringToDelete.length();

                StringBuilder stringBuilder = new StringBuilder(textOfCorrespondingRemoveButton);
                stringBuilder.delete(startingIndex, endingIndex);

                String productNameOfCartItemRequestedToBeUpdated = stringBuilder.toString();
                String updatedQuantityToPurchase = view.getAllQuantityToPurchaseTextFields().get(saveButtonNumber).getText();
               this.model.saveCartQuantityChanges(saveButtonNumber, productNameOfCartItemRequestedToBeUpdated, updatedQuantityToPurchase);

                break;
            case "Return Back To Main Menu":
                    this.model.requestToReturnToMainMenu();
                break;
            default:
                break;
        }

    }

}
