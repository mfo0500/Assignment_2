/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author krist
 */
// The CheckoutService object represents the check out process of the AZGroceries online shop.
// The deliveryFee of an CheckoutService is a double that represents the delivery fee of the AZGroceries online shop.
// The pickupFee of an CheckoutService is a double that represents the pickup fee of the AZGroceries online shop.
// The state of an CheckoutService is a String object that represents a particular stage of the Checkout process. 
// The deliveryFeeAdded of an CheckoutService represents if the user was already charged for delivery.
// The pickupFeeAdded of an CheckoutService represents if the user was already charged for pickup.
public class CheckoutService implements IsValid {

    private double deliveryFee = 7.50;
    private double pickupFee = 0.50;
    private String state;
    private boolean deliveryFeeAdded;
    private boolean pickupFeeAdded;

    // When creating a CheckoutService object, deliveryFeeAdded is set to false and pickupFeeAdded is set to false
    CheckoutService() {
        setPickupFeeAdded(false);
        setDeliveryFeeAdded(false);

    }
// The public void pickupOrDelivery(OnlineShopAZGroceries shop) asks the user if they want pick up or delivery and adds a delivery fee or pick
// up fee to the user's cart total depending on their response.
    // They will also be given the option to go back or exit. The user is then directed to the 
// public void collectingCardNumber(OnlineShopAZGroceries shop)

    public void pickupOrDelivery(OnlineShopAZGroceries shop) {

        if (shop.getUserAccount().getUserCart().getTotal() == 0.0) // checks if the total of the user's cart is $0
        {
            System.out.println("Your cart is empty. Unable to checkout"); // if the totatl of the user's cart is $0. The user will be told that
            System.out.println("You will be delivered to the homepage"); // their cart is empty and will be told that their cart is empty 
            shop.openStore(shop);                                        // user will be directed to the home page of the AZGroceries online store
        }

        System.out.println("Would you prefer delivery or pickup ?\n1. Pickup\n2. Delivery\nPress / to go back\nPress X or x  to exit"); // If the user's cart is not empty, 
        //The user will be asked if they prefer to pick up their grocery items or have it delivered to them.
        Scanner optionSelect = new Scanner(System.in); // The user's cart will be displayed and a new scanner object will be created
        String pickupOrDelivery = optionSelect.nextLine(); // The user's input will be stored in a String called "pickupOrDElivery"
        setState("pickup or delivery"); // the checkout service's state will be set to "pickup or delivery"
        while (!IsValid(shop, pickupOrDelivery)) { // if the user's input not valid when the checkout service's state is "pickup or delivery"
            System.out.println("Invalid input.Please try again"); // they are asked to try again and the user's response will be recorded 
            // they will also be given the option to go back or exit
            System.out.println("Would you prefer delivery or pickup ?\n1. Pickup\n2. Delivery\nPress / to go back\nPress X or x  to exit"); // untill they provide a valid response
            pickupOrDelivery = optionSelect.nextLine();
        }
        if (IsValid(shop, pickupOrDelivery)) { // if the user provides a valid response "1" or "2"
            int decision = Integer.parseInt(pickupOrDelivery); // their response will be converted to an integer, 1 or 2
            if (decision == 1) { // if the user's response was "1", it will be checked if a delivery fee was added. If this is the case,
                if (shop.getCheckout().isDeliveryFeeAdded()) // the delivery fee will be subtracted from the user's cart total
                {                                           // and the CheckoutService object's deliveryFeeAdded will be set to false
                    shop.getUserAccount().getUserCart().setTotal(shop.getUserAccount().getUserCart().getTotal() - getDeliveryFee());
                    shop.getCheckout().setDeliveryFeeAdded(false);
                }
                if (!shop.getCheckout().isPickupFeeAdded()) // if a pickup fee was not added before, 
                {                                    // the pickup fee will be subtracted from the user's cart total
                    // and the CheckoutService object's pickupFeeAdded will be set to true
                    shop.getUserAccount().getUserCart().setTotal(shop.getUserAccount().getUserCart().getTotal() + getPickupFee());
                    shop.getCheckout().setPickupFeeAdded(true);
                    collectingCardNumber(shop);

                }
                if (shop.getCheckout().isPickupFeeAdded()) // if a pickup fee was not added before, 
                {                                        // the user's cart total will not be modified and the user will be directed to the 
                    collectingCardNumber(shop);         // public void collectingCardNumber( OnlineShopAZGroceries shop) function
                }

            }
            if (decision == 2) {  // if the user's response was "2", it will be checked if a pickup fee was added. If this is the case,
                if (shop.getCheckout().isPickupFeeAdded()) // the pickup fee will be subtracted from the user's cart total
                {                                         // and the CheckoutService object's pickupFeeAdded will be set to false
                    shop.getUserAccount().getUserCart().setTotal(shop.getUserAccount().getUserCart().getTotal() - getPickupFee());
                    shop.getCheckout().setPickupFeeAdded(false);
                }
                if (!shop.getCheckout().isDeliveryFeeAdded()) // if a delivery fee was not added before, 
                {                                        // the delivery fee will be subtracted from the user's cart total
                    // and the CheckoutService object's deliveryFeeAdded will be set to true 
                    shop.getUserAccount().getUserCart().setTotal(shop.getUserAccount().getUserCart().getTotal() + getDeliveryFee());
                    shop.getCheckout().setDeliveryFeeAdded(true);
                    collectingCardNumber(shop);
                }
                if (shop.getCheckout().isDeliveryFeeAdded()) // if a delivery fee was not added before, 
                {                            // the user's cart total will not be modified and the user will be directed to the 
                    collectingCardNumber(shop);   // public void collectingCardNumber( OnlineShopAZGroceries shop) function
                }

            }
        }

    }
// The  public void collectingCardNumber(OnlineShopAZGroceries shop) function asks the user for their credit cart number
// and proceeds to validate the card number provided by the user. If the card number is not valid, the function will promt the user to try again
    // until they provide a valid card number. 
    // they will also be given the option to go back or exit

    public void collectingCardNumber(OnlineShopAZGroceries shop) {
        System.out.println("Enter card number (must be 16 digits)\nPress / to go back\nPress X or x  to exit");
        Scanner scanDetails = new Scanner(System.in);
        String cardNumber = scanDetails.nextLine();
        setState("card number verification");
        while (!IsValid(shop, cardNumber)) {
            System.out.println("Invalid card number. Please try again");
            System.out.println("Enter card number (must be 16 digits)\nPress / to go back\nPress X or x  to exit");
            cardNumber = scanDetails.nextLine();
        } // if the user provides a valid card number, they will be directed to the
        if (IsValid(shop, cardNumber)) {  // public void collectingCardExpiryDateMonth( OnlineShopAZGroceries shop)
            collectingCardExpiryDateMonth(shop);
        }
    }

    // The public void collectingCardExpiryDateMonth(OnlineShopAZGroceries shop) function prompts the user to select the month of the expiry date
    // using numbers between 1 and 12. Each number between 1 and 12 represent a month of the year
    // they will also be given the option to go back or exit
    public void collectingCardExpiryDateMonth(OnlineShopAZGroceries shop) {
        Scanner scanDetails = new Scanner(System.in);
        System.out.println("Please provide the month of your card expiry date:\nPress / to go back\nPress X or x  to exit");
        System.out.println("1. January");
        System.out.println("2. February");
        System.out.println("3. March");
        System.out.println("4. April");
        System.out.println("5. May");
        System.out.println("6. June");
        System.out.println("7. July");
        System.out.println("8. August");
        System.out.println("9. September");
        System.out.println("10. October");
        System.out.println("11. November");
        System.out.println("12. December");
        setState("expiry date month");

        String expiryDateMonthInput = scanDetails.nextLine(); // if the user provides an invalid input, they will be prompted to try again 
        while (!IsValid(shop, expiryDateMonthInput)) { // untill they provide a valid month
            System.out.println("Invalid input. Please try again"); // they will also be given the option to go back or exit
            System.out.println("Please provide the month of your card expiry date:\nPress / to go back\nPress X or x  to exit");
            expiryDateMonthInput = scanDetails.nextLine(); // if the user provides a valid month, they will be directed to the 
        }                                                //  public void collectingCardExpiryDateYear( OnlineShopAZGroceries shop) function
        if (IsValid(shop, expiryDateMonthInput)) {
            collectingCardExpiryDateYear(shop);

        }
    }

    // The public void collectingCardExpiryDateYear( OnlineShopAZGroceries shop) function asks the user to provide the year of the expiry date
    // they will also be given the option to go back or exit
    public void collectingCardExpiryDateYear(OnlineShopAZGroceries shop) {
        System.out.println("Please provide the year of your card expiry date:\nPress / to go back\nPress X or x  to exit");
        setState("expiry date year");
        Scanner scan = new Scanner(System.in);
        String expiryDateYearInput = scan.nextLine(); // if the user provides an invalid input, they will be prompted to try again 
        while (!IsValid(shop, expiryDateYearInput)) { // until they provide a valid year
            expiryDateYearInput = scan.nextLine();  // they will also be given the option to go back or exit
        }
        if (IsValid(shop, expiryDateYearInput)) { // if the user provides a valid year, they will directed to the 
            //  public void collectingCVC(OnlineShopAZGroceries shop) function
            collectingCVC(shop);
        }
    }

// The public void collectingCVC(OnlineShopAZGroceries shop) prompts the user t enter the CVC value of their credit card
    // they will also be given the option to go back or exit
    public void collectingCVC(OnlineShopAZGroceries shop) {
        Scanner scanDetails = new Scanner(System.in);
        System.out.println("Please enter CVC (must be 3 digits)\nPress / to go back\nPress X or x  to exit");
        setState("cvc verification");
        String cvcInput = scanDetails.nextLine(); //if the user provides an invalid input, they will be prompted to try again 
        while (!IsValid(shop, cvcInput)) { //until they provide a valid CVC
            System.out.println("Invalid input. Please try again"); // they will also be given the option to go back or exit
            System.out.println("Please enter CVC (must be 3 digits)\nPress / to go back\nPress X or x  to exit");
            cvcInput = scanDetails.nextLine();
        }
        if (IsValid(shop, cvcInput)) {// if the user provides a valid CVC, they will be directed to the 
            collectingCardHolderName(shop); //   public void collectingCardHolderName(OnlineShopAZGroceries shop) function

        }//
    }

    // The public void collectingCardHolderName(OnlineShopAZGroceries shop) function prompts the user to provide the cardholder name of 
    // their credit card.
    // they will also be given the option to go back or exit
    public void collectingCardHolderName(OnlineShopAZGroceries shop) {
        Scanner scanDetails = new Scanner(System.in);
        // if the user provides an invalid card holder name, they will be prompted to try again until they provide a valid card holder name
        // they will also be given the option to go back or exit
        setState("card holder verification");
        System.out.println("Please enter card holder name\nPress / to go back\nPress X or x  to exit");
        String cardHolderName = scanDetails.nextLine();
        while (!IsValid(shop, cardHolderName)) {
            System.out.println("Invalid input. Please try again");
            System.out.println("Please enter card holder name\nPress / to go back\nPress X or x  to exit");
            cardHolderName = scanDetails.nextLine();
        }
        if (IsValid(shop, cardHolderName)) {  // if the user provides a valid cardholder name 
            int i = 0;                        // the ListOfGroceries will be loaded. Every grocery item in the list of groceries will be modified
            // by having the quantity the user pruchased for that particular grocery item subtracted
            // The quantity available for each product the user purchased decrease 
            // by the amount of the grocery item the user purchased
            for (GroceryItems g : shop.getGroceryList().getGroceries()) {

                if (shop.getUserAccount().getUserCart().getItemsAdded().containsKey(g)) {
                    int oldQuantityGroceryItem = shop.getGroceryList().getGroceries().get(i).getQuantityAvailable();
                    int quantityUserPurchases = shop.getUserAccount().getUserCart().getItemsAdded().get(g);
                    shop.getGroceryList().getGroceries().get(i).setQuantityAvailable(oldQuantityGroceryItem - quantityUserPurchases);
                }
                i++;
            }
            // Today's date will be generated and be stored in a string called "dateOfPurchase"
            // The "purchaseDateString" String will store a user firendly message ("Purchased on: ") with dateOfPurchase added
            // purchaseDateString will become the value of the Order History HashMap entry
            // the cart of the user will become the key of the Order History HashMap entry
            // This is how a new order history entry is added.

            Calendar calender = Calendar.getInstance();
            Date purchaseDate = calender.getTime();
            calender.setTime(purchaseDate);
            String dateOfPurchase = new SimpleDateFormat("dd/MM/yyyy").format(purchaseDate);
            String purchaseDateString = "Purchased on: " + dateOfPurchase;
            Cart cartToAdd = shop.getUserAccount().getUserCart();
            shop.getUserAccount().getOrderHistory().getOrderHistory().put(cartToAdd, purchaseDateString);
            shop.getGroceryList().updateGroceries();  // The shop's grocery list will be updated after the purchase by re writting the Grocery list file
            System.out.println("Purchase Successful");  // the user will be tolde that their purchase is sucessful
            shop.openStore(shop);  // the user will be directed to the home page after their purchase

        }//
    }

    // The public boolean IsValid(OnlineShopAZGroceries shop, String choice) is the implementation of the interface IsValidusing the 
    //  public boolean IsValid(OnlineShopAZGroceries shop, String choice) function prototype
    // This function checks the validity of the users reponse provided (String choice) to every situation where a user is asked for a response 
    @Override
    public boolean IsValid(OnlineShopAZGroceries shop, String choice) {
        boolean valid = false;
        if (choice.equals("x") || choice.equals("X")) // if the user inputs "X" or "x" 
        {                                           // the system exits
            System.exit(0);
        }
        if (choice.equals("/")) {
            if (getState().equals("card holder verification")) // if the CheckoutService object's state is "cvc verification" and
            {                                                // they press "/", they will be directed back to 
                shop.getCheckout().collectingCVC(shop); // public void collectingCVC(OnlineShopAZGroceries shop)
            }
            if (getState().equals("cvc verification")) // if the CheckoutService object's state is "cvc verification" and
            {                                        // they press "/", they will be directed back to 
                shop.getCheckout().collectingCardExpiryDateYear(shop); // public void collectingCardExpiryDateYear(OnlineShopAZGroceries shop)
            }
            if (getState().equals("expiry date year")) // if the CheckoutService object's state is "expiry date year" and
            {                                         // they press "/", they will be directed back to 
                // public void collectingCardExpiryDateMonth(OnlineShopAZGroceries shop)
                shop.getCheckout().collectingCardExpiryDateMonth(shop);
            }
            if (getState().equals("expiry date month")) // if the CheckoutService object's state is "card number verification" and
            {                                              // they press "/", they will be directed back to 
                shop.getCheckout().collectingCardNumber(shop);  // the public void collectingCardNumber( OnlineShopAZGroceries shop) function
            }
            if (getState().equals("card number verification")) // if the CheckoutService object's state is "card number verification" and
            {                                                  // they press "/", they will be directed back to 
                shop.getCheckout().pickupOrDelivery(shop);    // the public void pickupOrDelivery( OnlineShopAZGroceries shop) function
            }
            if (getState().equals("pickup or delivery")) // if the CheckoutService object's state is "pickup or delivery" and
            {                                            // they press "/", they will be directed back to 
                shop.getUserAccount().getUserCart().cartOptions(shop);  // the public void cartOptions( OnlineShopAZGroceries shop) function
            }

        }
        try {
            if (getState().equals("pickup or delivery")) { // if the CheckoutService object's state is "pickup or delivery"
                int decision = Integer.parseInt(choice); // the user can only type an integer between 1 and 2 to have a valid response
                if (decision >= 1 && decision <= 2) {
                    valid = true;
                }

            }
            if (getState().equals("card number verification")) {  // if the CheckoutService object's state is "card number verification"
                // the user can only type a 16 digit integer to have a valid response
                if (choice.length() != 16) {
                    System.out.println("Card number must have 16 digits. Please enter card number again");
                    valid = false;
                } else {
                    try {
                        int cardNumber = (int) Long.parseLong(choice);

                        valid = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Card number must have 16 digits. Please enter card number again");
                        valid = false;
                    }

                }
            }
            if (getState().equals("expiry date month")) { // if the CheckoutService object's state is "expiry date month"
                int decision = Integer.parseInt(choice); // the user can only type an integer between 1 and 12 to have a valid response
                if (decision >= 1 && decision <= 12) {
                    valid = true;
                }

            }
            if (getState().equals("expiry date year")) { // if the CheckoutService object's state is "expiry date year"
                int decision = Integer.parseInt(choice); // the user can only type an integer between 2022 and 2027 to have a valid response
                if (decision < 2022) {
                    valid = false;
                    System.out.println("your credit card is expired. Please try another card");
                    shop.getCheckout().collectingCardNumber(shop);
                }
                if (decision > 2027) {
                    valid = false;
                    System.out.println("Invalid credit card number. Credit cards take a maximum of 5 years to expire");
                }
                if (decision <= 2027 && decision >= 2022) {
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
                        System.out.println("All names or initials must have a capital letter. Please try again");
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
                    System.out.println("Input contains too many special characters");
                }
                if (choice.isBlank() || choice.isEmpty() || hasDigit || noCapitalFirstLetter || hasSpecialCharacter || tooManySpecialCharacters) // if the user provided a blank answer, null response, contains a digit, has too many valid special 
                {                        // characters, has inappropriate special characters,    
                    valid = false;          // or no uppercase letter per initial or name, valid becomes false 
                } else {
                    valid = true;
                }

            }

        } catch (NumberFormatException | InputMismatchException e) {  // if there are number format exceptions thrown or input mismatch exceptions thrown
            System.out.println("Number format exception or InputMistmatchException. Please try again"); // the user's response will not be valid

            valid = false;
        }
        return valid;
    }

// The public double getDeliveryFee() returns the CheckoutService object's deliveryFee variable in the form of a double
    /**
     * @return the deliveryFee
     */
    public double getDeliveryFee() {
        return deliveryFee;
    }

    // The public void setDeliveryFee(double deliveryFee) sets the CheckoutService object's deliveryFee variable to 
// double provided (deliveryFee).
    /**
     * @param deliveryFee the deliveryFee to set
     */
    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    /**
     * @return the pickupFee
     */
    // The public double getPickupFee() returns the CheckoutService object's pickupFee variable in the form of a double
    public double getPickupFee() {
        return pickupFee;
    }

    /**
     * @param pickupFee the pickupFee to set
     */
    // The public void setPickupFee(double pickupFee) sets the CheckoutService object's deliveryFee variable to 
// double provided (pickupFee).
    public void setPickupFee(double pickupFee) {
        this.pickupFee = pickupFee;
    }

    /**
     * @return the state
     */
    // The public String getState() returns the CheckoutService object's state variable in the form of a String
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
// The  public void setState(String state) sets the CheckoutService object's state variable to 
// String provided (state)
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the deliveryFeeAdded
     */
    // The public boolean isDeliveryFeeAdded() returns the CheckoutService object's deliveryFeeAdded variable in the form of a boolean variable
    public boolean isDeliveryFeeAdded() {
        return deliveryFeeAdded;
    }

    /**
     * @param deliveryFeeAdded the deliveryFeeAdded to set
     */
    // The  public void setDeliveryFeeAdded(boolean deliveryFeeAdded) sets the CheckoutService object's deliveryFeeAdded variable to 
// boolean provided (deliveryFeeAdded)
    public void setDeliveryFeeAdded(boolean deliveryFeeAdded) {
        this.deliveryFeeAdded = deliveryFeeAdded;
    }

    /**
     * @return the pickupFeeAdded
     */
// The public boolean isPickupFeeAdded() returns the CheckoutService object's pickupFeeAdded variable in the form of a boolean variable
    public boolean isPickupFeeAdded() {
        return pickupFeeAdded;
    }

    /**
     * @param pickupFeeAdded the pickupFeeAdded to set
     */
    // The public void setPickupFeeAdded(boolean pickupFeeAdded) sets the CheckoutService object's pickupFeeAdded variable to 
// boolean provided (pickupFeeAdded)
    public void setPickupFeeAdded(boolean pickupFeeAdded) {
        this.pickupFeeAdded = pickupFeeAdded;
    }

}
