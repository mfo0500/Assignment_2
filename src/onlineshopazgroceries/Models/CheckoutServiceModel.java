/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries.Models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class CheckoutServiceModel extends Observable implements IsValid {

    public CheckoutServiceModel model;
    public ShopDatabase database;
    public ShopData data;
    private String state = "";

    public CheckoutServiceModel() {
        this.database = new ShopDatabase();
        this.database.databaseSetup();
        this.data = new ShopData();
    }

    public void requestToPurchase(boolean pickupSelected, boolean deliverySelected, String CardNumber, int CardExpiryMonth, int CardExpiryYear, String CVC, String CardHolderName) {
        //                 this.model.requestToPurchase(pickupSelected, deliverySelected, CardNumber, CardExpiryMonth, CardExpiryYear, CVC, CardHolderName );

        data.setReasonPurchaseFailed("")
                ;
        data.setPurchaseRequested(true);
        if (pickupSelected && deliverySelected) {
            data.setPurcahseSucessful(false);
            data.setReasonPurchaseFailed("Pick either delivery or Pickup");
             

        }
        if (!pickupSelected && !deliverySelected) {
            data.setPurcahseSucessful(false);
            data.setReasonPurchaseFailed("Pick either delivery or Pickup");
             
        }
        if ((!pickupSelected && deliverySelected) || (pickupSelected && !deliverySelected)) // if either pickup or delivery is seleccted
        {
            setState("card number verification");
            if (!IsValid(CardNumber)) {
                data.setPurcahseSucessful(false);
                data.setReasonPurchaseFailed("card number not 16 digits");
                 
            }
            if (IsValid(CardNumber)) {

                if (CardExpiryMonth < 11 && CardExpiryYear == 2022) {
                    data.setPurcahseSucessful(false);
                    data.setReasonPurchaseFailed("card is expired");
                     
                } else {
                    setState("cvc verification");

                    if (!IsValid(CVC)) {
                        data.setPurcahseSucessful(false);
                        data.setReasonPurchaseFailed("CVC is invalid");
                         
                    }
                    if (IsValid(CVC)) {

                        setState("card holder verification");
                        if (!IsValid(CardHolderName)) {
                            data.setPurcahseSucessful(false);
                            data.setReasonPurchaseFailed("invalid cardholder name");
                             
                        }
                        if (IsValid(CardHolderName)) {
                            boolean requestCanBeMet = true;
                            for (GroceryItems g : data.getUserAccount().getUserCart().getItemsAdded().keySet()) {
                                int productQuantityAvailable = g.getQuantityAvailable();
                                int quantityCustomerrequests = data.getUserAccount().getUserCart().getItemsAdded().get(g);

                                if (productQuantityAvailable < quantityCustomerrequests) {
                                    requestCanBeMet = false;
                                    data.setPurcahseSucessful(false);
                                    data.setReasonPurchaseFailed("quantity of product requested exceeds quantity available");
                                     
                                }
                            }

                            if (requestCanBeMet) {
                                Calendar calender = Calendar.getInstance();
                                Date purchaseDate = calender.getTime();
                                calender.setTime(purchaseDate);
                                String dateOfPurchase = new SimpleDateFormat("dd/MM/yyyy").format(purchaseDate);
                                System.out.println(dateOfPurchase);
                                for (GroceryItems g : data.getUserAccount().getUserCart().getItemsAdded().keySet()) {
                                    System.out.println("Product Name Purchased: " + g.getProductName());
                                    System.out.println("Product quantity Available : " + g.getQuantityAvailable());
                                    System.out.println("Product quantity purchased : " + data.getUserAccount().getUserCart().getItemsAdded().get(g));
                                       this.data =  this.database.addTransaction(this.data, data.getUserAccount().getUsername(),dateOfPurchase, g, data.getUserAccount().getUserCart().getItemsAdded().get(g));

                                }
                               data.setPurcahseSucessful(true);

                            }
                        }

                    }
                }
            }
        }
        
         this.setChanged();
        this.notifyObservers(this.data);

    }

    public void requestToReturnToMainMenu() {
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
        this.data.setPurchaseRequested(false);
        this.data.setPurcahseSucessful(false);

        this.setChanged();
        this.notifyObservers(this.data);
    }

    @Override
    public boolean IsValid(String choice) {

        boolean valid = false;
        try {
            if (getState().equals("card number verification")) {
                // the user can only type a 16 digit integer to have a valid response
                if (choice.length() != 16) {
                    valid = false;
                } else {
                    int cardNumber = (int) Long.parseLong(choice);

                    valid = true;

                }
                
            }
            
            if (getState().equals("cvc verification")) { // if the CheckoutService object's state is "cvc verification"
                // the user can only type a 3 didgit integer to have a valid response
                if (choice.length() != 3) {
                    valid = false;
                } else {
                    int cardNumber = Integer.parseInt(choice);
                    valid = true;

                }
            }
            if (getState().equals("card holder verification")) { // if the CheckoutService object's state is "card holder verification"
                if (!choice.trim().contains(" ")) // the user can type Capital letters or a sequence of letters with the first letter 
                {                                                // being a capital letter seperated with a space to have a valid response
                    // the user's response can contain ' or - but not two of either in a row
                    System.out.println("Cardholder name must contain a \" \" between names");
                }
                String[] names = choice.split(" ");
                if (names.length < 2) {
                    valid = false;
                }
                boolean noCapitalFirstLetter = false;
                for (String s : names) {
                    char firstLetter = s.toCharArray()[0];
                    if (!Character.isUpperCase(firstLetter)) {
                        valid = false;
                        noCapitalFirstLetter = true;
                        
                    }
                }
                char[] choiceCharArray = choice.toCharArray();
                boolean hasDigit = false;
                boolean hasSpecialCharacter = false;
                for (char c : choiceCharArray) {
                    if (Character.isDigit(c)) {
                        hasDigit = true;
                    }
                    if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c) && c != '\'' && c != '-') {
                        hasSpecialCharacter = true;
                    }
                }
                if (hasSpecialCharacter) {
                    System.out.println("Input contains an inappropriate special character");
                }
                boolean tooManySpecialCharacters = false;
                if (choice.contains("\n'-") || choice.contains("-\n'") || choice.contains("--") || choice.contains("\n'\n'")) {
                    tooManySpecialCharacters = true;
                    
                }
                if (choice.isEmpty() || hasDigit || noCapitalFirstLetter || hasSpecialCharacter || tooManySpecialCharacters) // if the user provided a blank answer, null response, contains a digit, has too many valid special 
                {                        // characters, has inappropriate special characters,    
                    valid = false;          // or no uppercase letter per initial or name, valid becomes false 
                } else {
                    valid = true;
                }

            }

            
        } catch (NumberFormatException | InputMismatchException e) {
            valid = false;
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
