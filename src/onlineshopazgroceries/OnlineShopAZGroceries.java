/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package onlineshopazgroceries;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author krist
 */
// OnlineShopAZGroceries objects represent the online store for AZGroceries
// Every OnlineShopAZGroceries object contains a ListOfGroceries object called groceryList
// The OnlineShopAZGroceries object represent the online store for AZGroceries
// Every OnlineShopAZGroceries object has a groceryList, userAccount, categoryList, state, searched, selectedItem, checkout, and signInService.
// The groceryList of an OnlineShopAZGroceries object is a ListOfGroceries object that represents a list of all the grocery items in the AZGroceries online store.
// The userAccount of an OnlineShopAZGroceries object is a AZGroceriesAccount object that representsthe account that is logged in
// The categoryList of an OnlineShopAZGroceries object List of all the categories in the AZGroceries online store.
// The state of an OnlineShopAZGroceries object is a String that represents stage of the AZGroceries online store's service 
// The searched of an OnlineShopAZGroceries object is an array list of all the items that matches the user's search
// The selectedItem of an OnlineShopAZGroceries object is the item the user has selected from searching through the AZGroceries online store.
// The checkout of an OnlineShopAZGroceries object is the checkout ervice the store uses to conduct the checkout process for the user.

public class OnlineShopAZGroceries implements IsValid {

    private ListOfGroceries groceryList = new ListOfGroceries();
    //  private LinkedList<GroceryItems> Groceries = getGroceryList().loadGroceries();
    private AZGroceriesAccount userAccount;
    private Categories categoryList;
    private String state;
    private ArrayList<GroceryItems> searched = new ArrayList<>();
    private GroceryItems selectedItem;
    private CheckoutService checkout;
    private SignInService signInService;

    /**
     * @return the Groceries
     */
    // When creating a OnlineShopAZGroceries object using a provided AZGroceriesAccount:
    // userAccount is set to the AZGroceriesAccount object provided userAccount
    // groceryList is set to a new ListOfGroceries oject
    // categoryList is set to a new Categories object
    // checkout is set to a new CheckoutService object 
    // signInService is set to a new SignInService object 
    OnlineShopAZGroceries(AZGroceriesAccount userAccount) {
        // setGroceries(Groceries);
        setGroceryList(getGroceryList().loadGroceries());
        setCategoryList(new Categories());
        setUserAccount(userAccount);
        setCheckout(new CheckoutService());
        setSignInService(new SignInService());
    }

    // When creating a OnlineShopAZGroceries object using its default constructor
    // userAccount is set to null
    // groceryList is set to a new ListOfGroceries oject
    // categoryList is set to a new Categories object
    // checkout is set to a new CheckoutService object 
    // signInService is set to a new SignInService object 
    OnlineShopAZGroceries() {
        //  setGroceries(Groceries);
        setGroceryList(getGroceryList().loadGroceries());
        setCategoryList(new Categories());
        setUserAccount(null);
        setCheckout(new CheckoutService());
        setSignInService(new SignInService());
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        OnlineShopAZGroceries shop = new OnlineShopAZGroceries(); // a new OnlineShopAZGroceries object is created
        shop.getSignInService().signInProcess(shop); // the OnlineShopAZGroceries's signInService directs the user to its public void signInProcess(OnlineShopAZGroceries shop)
                                                     // function
    }

    public void openStore(OnlineShopAZGroceries shop) { // the public void openStore(OnlineShopAZGroceries shop) provides the user with the option to 
  // either search from all items, search by Catagory, view their Cart, view Order History or checkout as well as exit or go pack to the sign in page, if the user is a guest or customer 
// if the user is an administrator, they are given the option to either search from all items, search by Catagory, view their Cart, view Order History, checkout, add an item, remove an item or modify the quantity of an item as well as exit or go back to sign in page
        if (shop.getUserAccount() != null) {
            System.out.println("Welcome, " + shop.getUserAccount().getUsername() + "\nWould you like to:");
            if (shop.getUserAccount().getRole().equals("Admin")) {
                System.out.println("1. Search from all items\n2. Search by Catagory\n3. View Cart\n4. View Order History\n5. Checkout\n6. Add an item\n7. Remove an item\n8. Modify the quantity of an item\nPress X or x to exit\nPress / to go back to sign in page");

            }
            if (shop.getUserAccount().getRole().equals("Customer")) {
                System.out.println("1. Search from all items\n2. Search by Catagory\n3. View Cart\n4. View Order History\n5. Checkout\nPress X or x to exit\nPress / to go back to sign in page");

            }
        }
        if (shop.getUserAccount() == null) {
            System.out.println("Welcome User,\nWould you like to:");
            System.out.println("1. Search from all items\n2. Search by Catagory\n3. View Cart\n4. View Order History\n5. Checkout\nPress X or x to exit\nPress / to go back to sign in page");

        }
        Scanner scanOption = new Scanner(System.in);
        String choice = scanOption.nextLine();
        shop.setState("choose option");
        while (!IsValid(shop, choice)) {  // if the user prodives a valid response 
            choice = scanOption.nextLine();
        }
        if (IsValid(shop, choice)) {
            int decision = Integer.parseInt(choice);

            switch (decision) {
                case 1:
                        search(shop); // a customer, guest or administrator pressing 1 will be directed to the public void search(OnlineShopAZGroceries shop) function
                    break;
                case 2:
                    System.out.println("Please select category"); // a customer, guest or administrator pressing 2 will be asked to select a category
                    shop.getCategoryList().ListCategories(); // from a list of all categories displayed on the colsole
                    shop.setState("select category");
                    String[] categoryArray = new String[shop.getCategoryList().getCategories().size()];
                    int i = 0;
                    for (String s : shop.getCategoryList().getCategories()) {
                        categoryArray[i] = s;
                        i++;
                    }
                    String inputSelectedItem = scanOption.nextLine();
                    while (!IsValid(shop, inputSelectedItem)) {
                        inputSelectedItem = scanOption.nextLine();
                    }
                    if (IsValid(shop, inputSelectedItem)) { // if the user's response is valid, all the items that match their search will be added to 
                        int chosenItem = Integer.parseInt(inputSelectedItem); // searched

                        shop.getCategoryList().setSelectedCategory(categoryArray[chosenItem - 1]);
                        ArrayList<GroceryItems> searchedItems = new ArrayList<>();
                        for (GroceryItems g : shop.getGroceryList().getGroceries()) {
                            if (g.getCategory().equals(shop.getCategoryList().getSelectedCategory())) {
                                searchedItems.add(g);
                            }
                        }
                        shop.setSearched(searchedItems);
                        setState("choose item (categories)"); // the shop's state will be set to "choose item (categories)"
                        shop.searchList(shop); // the user will be directed to the public void searchList(OnlineShopAZGroceries shop) function

                    }
                    break;
                case 3:
                    if (shop.getUserAccount() == null) { // if a guest presses 3, they will be told that they need an AZGRoceriesAccount to view their cart and be asked wheather they want to go back create an account or sign in or exit
                        System.out.println("You must have a AZGroceries Acccount to view cart.\nWould you like to:\n1. Create an account\n2. Sign in\nPress / to go back\nPress x or X to exit");
                        shop.setState("no account");
                        String noAccount = scanOption.nextLine();
                        while (!IsValid(shop, noAccount)) {
                            noAccount = scanOption.nextLine();
                        }
                        if (!IsValid(shop, noAccount)) { // if the guest presses 1, they will be in the process of creating an AZGRoceriesAccount
                            int noAccountChoice = Integer.parseInt(noAccount);
                            SignInService s = new SignInService();
                            if (noAccountChoice == 1) {

                                s.createAccountUsername(shop);
                            }
                            if (noAccountChoice == 2) { // if the guest presses 2, they will be in the process of signing in
                                s.signIn(shop);
                            }
                        }
                    } else {
                        shop.getUserAccount().getUserCart().cartOptions(shop); // if the user is an admin or customer,, they will have their cart displayed if it is not empty
                    }

                    break;
                case 4:
// view order history
                    if (shop.getUserAccount() == null) { // if a guest presses 3, they will be told that they need an AZGRoceriesAccount to view their cart and be asked wheather they want to go back create an account or sign in or exit
                        System.out.println("You must have a AZGroceries Acccount to view order history.\nWould you like to:\n1. Create an account\n2. Sign in\nPress / to go back\nPress x or X to exit");
                        shop.setState("no account");
                        String noAccount = scanOption.nextLine();
                        while (!IsValid(shop, noAccount)) {
                            noAccount = scanOption.nextLine();
                        }
                        if (!IsValid(shop, noAccount)) {
                            int noAccountChoice = Integer.parseInt(noAccount);
                            SignInService s = new SignInService();
                            if (noAccountChoice == 1) {// if the guest presses 1, they will be in the process of creating an AZGRoceriesAccount

                                s.createAccountUsername(shop);
                            }
                            if (noAccountChoice == 2) { // if the guest presses 2, they will be in the process of signing in
                                s.signIn(shop);
                            }
                        }
                    } else {
                        // check if order history is empty in pickupOrDelivery(shop)
                        shop.getUserAccount().getOrderHistory().displayOrderHistory(shop); // if the user is a customer or admin, they will view their order histoery if it is not empty
                    }
                    break;
                case 5:
                    if (shop.getUserAccount() == null) { // if a guest presses 3, they will be told that they need an AZGRoceriesAccount to check out and be asked wheather they want to go back create an account or sign in or exit
                        System.out.println("You must have a AZGroceries Acccount to check out.\nWould you like to:\n1. Create an account\n2. Sign in\nPress / to go back\nPress x or X to exit");
                        shop.setState("no account");
                        String noAccount = scanOption.nextLine();
                        while (!IsValid(shop, noAccount)) {
                            noAccount = scanOption.nextLine();
                        }
                        if (!IsValid(shop, noAccount)) {
                            int noAccountChoice = Integer.parseInt(noAccount);
                            SignInService s = new SignInService();
                            if (noAccountChoice == 1) {// if the guest presses 1, they will be in the process of creating an AZGRoceriesAccount

                                s.createAccountUsername(shop);
                            }
                            if (noAccountChoice == 2) { // if the guest presses 2, they will be in the process of signing in
                                s.signIn(shop);
                            }
                        }
                    }
                    else{
                        shop.getCheckout().pickupOrDelivery(shop); // is the user is a customer or admin, they will begin the checkout process if their cart is not empty

                    }

                    break;
                case 6:
                    // add an item
                    shop.getGroceryList().addnewProduct(shop); // if the user is an admin, the process of adding a grocery item will begin

                    break;
                case 7:
                    // remove an item
                    // display all groceries 
                    shop.getGroceryList().removeGroceryItem(shop); // if the user is an admin the process of removing a grocery item will begin

                    break;
                case 8:
                    // modify the quantity of an item
                    shop.getGroceryList().modifyQuantity(shop); // if the user is an admin, the process of modifying the quantity of a grocery item will begin
                    break;
            }

        }
    }
// the public void search(OnlineShopAZGroceries shop) function allows user to search from all the items in the AZGroceries online store
    public void search(OnlineShopAZGroceries shop) {
        System.out.println("What would you like to search:\nPress x or X to exit\nPress / to go back"); // they are asked what they want to search
        Scanner searchScan = new Scanner(System.in);
        String search = searchScan.nextLine();
        shop.setState("search");
        while (!IsValid(shop, search)) { /// if the user's response is valid, all the items that match their search will be added to searched 
            System.out.println("Invalid input. Please try again"); // otherwise they are prompted to try again
            System.out.println("What would you like to search:\nPress x or X to exit\nPress / to go back");
            search = searchScan.nextLine(); 
        }
        if (IsValid(shop, search)) {
            ArrayList<GroceryItems> searchedItems = new ArrayList<>();
            for (GroceryItems g : shop.getGroceryList().getGroceries()) {
                if (g.getProductName().toLowerCase().contains(search.toLowerCase()) || g.getCategory().toLowerCase().contains(search.toLowerCase())) {
                    searchedItems.add(g);
                }
            }
            Collections.sort(searchedItems);
            shop.setSearched(searchedItems);
            setState("choose item (search)");
            shop.searchList(shop); // user will be directed to the seearchlist function
        }

    }
// the search list function displays all the items that matched the user's search and directs them to the chooseItem function
    public void searchList(OnlineShopAZGroceries shop) {
        System.out.println("Select an item:\nPress / to go back\nPress X or x to exit");
        String searchlist = "";
        int i = 1;
        if (shop.getSearched().isEmpty()) { // if searched is empty, they will be prompted to either go back or exit
            System.out.println("There are no items that match your search\nPress / to go back\nPress X or x to exit");
            shop.setState("no results");
            Scanner scan = new Scanner(System.in);
            String choice = scan.nextLine();
            while (!IsValid(shop, choice)) { // user goes back to searching for an item if they provide a valid response
                choice = scan.nextLine();
            }
        } else {
            for (GroceryItems e : shop.getSearched()) { // list of items matching the user's search is displayed
                searchlist += i + ". Product Name: " + e.getProductName() + ", Price: $" + e.getPrice() + ", Quantity Available: " + e.getQuantityAvailable() + "\n";
                i++;
            }
            System.out.println(searchlist);
            shop.chooseItem(shop); // user is directed to the chooseItem function
        }

    }
// user is asked to select from a list of items that matched their search
    public void chooseItem(OnlineShopAZGroceries shop) {

        Scanner selectOption = new Scanner(System.in);
        String select = selectOption.nextLine();
        while (!IsValid(shop, select)) {
            System.out.println("Invalid input. Please try again");
            select = selectOption.nextLine();
        }
        if (IsValid(shop, select)) { // if the user provides a valid response they will be directed to the addCartChoice function
            int decision = Integer.parseInt(select);
            GroceryItems chosenItem = shop.getSearched().get(decision - 1);
            shop.setSelectedItem(chosenItem);
            shop.addCartChoice(shop);
        }
    }
// searched item is displayed on the console
// user is asked if they want to add their searched item to the cart or not 
    public void addCartChoice(OnlineShopAZGroceries shop) {
        System.out.println("You have chosen:\nProduct Name: " + shop.getSelectedItem().getProductName() + ", Price: $" + shop.getSelectedItem().getPrice()
                + ", Category: " + shop.getSelectedItem().getCategory() + ", Quantity in stock: " + shop.getSelectedItem().getQuantityAvailable() + "\n");
        System.out.println("Would you like to add item to cart:\nYes - Y or y\nNo - N or n\nPress x or X to exit\nPress / to go back");
        setState("chosing to add to cart");
        Scanner selectOption = new Scanner(System.in);
        String addToCartChoice = selectOption.nextLine();
        while (!IsValid(shop, addToCartChoice)) { // if user provides a valid response  they will either select the amount of the item they want to purchase or go back to choosing an item from the items that matched their previous search
            System.out.println("Invalid input. Please try again");
            System.out.println("Would you like to add item to cart:\nYes - Y or y\nNo - N or n\nPress x or X to exit\nPress / to go back");
            addToCartChoice = selectOption.nextLine();
        }
        if (IsValid(shop, addToCartChoice)) {
            if (addToCartChoice.equalsIgnoreCase("Y")) {
                shop.selectQuantityToPurchase(shop); // users who choose to add item to cart will be directed to the selectQuantityToPurchase function

            }
            if (addToCartChoice.equalsIgnoreCase("N")) { // user will be directed to the searchList function if they do not wish to add the selected item to cart
                System.out.println("You will be directed back to the results of your most recent search list");
                chooseItem(shop);
            }

        }
    }

    public void selectQuantityToPurchase(OnlineShopAZGroceries shop) { // user will be asked how much of the selected item they want to buy
        System.out.println("How much of this item would you like added?\nPress X or x to exit\nPress / to go back");
        setState("select quantity");
        Scanner selectOption = new Scanner(System.in);
        String quantitySelect = selectOption.nextLine();
        while (!IsValid(shop, quantitySelect)) {
            quantitySelect = selectOption.nextLine();
        }
        if (IsValid(shop, quantitySelect)) { // if the user's response is valid, the item will be added to their cart alongside the quantity of the item they wish to purchase
            int quantity = Integer.parseInt(quantitySelect); // 

            shop.getUserAccount().getUserCart().addItem(shop, shop.getSelectedItem(), quantity);

        }
    }

    // The public boolean IsValid(OnlineShopAZGroceries shop, String choice) is the implementation of the interface IsValidusing the 
    //  public boolean IsValid(OnlineShopAZGroceries shop, String choice) function prototype
    // This function checks the validity of the users reponse provided (String choice) to every situation where a user is asked for a response 
    @Override
    public boolean IsValid(OnlineShopAZGroceries shop, String choice) {
        // Exit the shop if the user inputs 'x' or 'X'.
        boolean isValid = false;
        if (choice.equals("x") || choice.equals("X")) {  // if the user enters x or X, the program terminates
            System.exit(0);
        }
        if (choice.equals("/")) {
            if (shop.getState().equals("search")) { // if the state of the OnlineShopAZGroceries object is "search" and the user presses /
                shop.openStore(shop);                // the user is directed to the public void openStore(OnlineShopAZGroceries shop) function
            }
            if (shop.getState().equals("no results")) { // if the state of the OnlineShopAZGroceries object is "no results" and the user presses /
                shop.search(shop);                      // the user is directed to the  public void search(OnlineShopAZGroceries shop) function
            }
            if (shop.getState().equals("choose item (search)")) { // if the state of the OnlineShopAZGroceries object is "choose item (search)" and the user presses /
                shop.search(shop);                               // the user is directed to the  public void search(OnlineShopAZGroceries shop) function
            }
            if (shop.getState().equals("chosing to add to cart")) { // if the state of the OnlineShopAZGroceries object is "chosing to add to cart" and the user presses /
                shop.searchList(shop);                              // the user is directed to the public void searchList(OnlineShopAZGroceries shop)) function
            }
            if (shop.getState().equals("select quantity")) {  // if the state of the OnlineShopAZGroceries object is "select quantity" and the user presses /
                shop.addCartChoice(shop);                  // the user is directed to the public void addCartChoice(OnlineShopAZGroceries shop) function
            }
            if (shop.getState().equals("choose option")) {  // if the state of the OnlineShopAZGroceries object is "choose option" and the user presses /
                shop.getSignInService().signInProcess(shop);  // the user is directed to the public void signInProcess(OnlineShopAZGroceries shop) function
            }
        }
        try {
            if (shop.getState().equals("choose option")) { // if the state of the OnlineShopAZGroceries object is "choose option": 
                                                           //  if the user is an Administrator, they can type a number between 1 and 8 to have
                if (shop.getUserAccount().getRole().equals("Admin")) { // a valid response
                    int decision = Integer.parseInt(choice);   // if the user is a customer or guest, they can type a number between 1 and 5
                    if (decision <= 8 && decision >= 1) {       // to have a valid response
                        isValid = true;
                    }
                }
                if (shop.getUserAccount().getRole().equals("Customer") || shop.getUserAccount() == null) {
                    int decision = Integer.parseInt(choice);
                    if (decision <= 5 && decision >= 1) {
                        isValid = true;
                    }
                }
            }
            if (shop.getState().equals("select category")) { // if the state of the OnlineShopAZGroceries object is "select category"
                int decision = Integer.parseInt(choice);     // the user can type a number between 1 and the number of categories the shop has
                if (decision >= 1 && decision <= shop.getCategoryList().getCategories().size()) { // to have a valid response
                    isValid = true;
                } else {  
                    System.out.println("Input out of bounds. Please try again");
                }
            }
            if (shop.getState().equals("search")) { // if the state of the OnlineShopAZGroceries object is "search"
                if (choice.isEmpty()) {            // the user can type anything but a null response to have a valid response
                    isValid = false;               // otherwise the console will print "No input detected. Please try agagin"
                    System.out.println("No input detected. Please try agagin");
                } else {
                    isValid = true;
                }

            }
            if (shop.getState().equals("User visiting cart")) { // if the state of the OnlineShopAZGroceries object is "User visiting cart"
                int decision = Integer.parseInt(choice);  // the user can type a number between 1 and 4 to have a valid response
                if (decision <= 4 && decision >= 1) {
                    isValid = true;
                }
            }
            if (shop.getState().equals("choose item (search)") || shop.getState().equals("choose item (categories)")) {
                int decision = Integer.parseInt(choice); // if the state of the OnlineShopAZGroceries object is "User visiting cart" or "choose item (search)"
                if (decision <= getSearched().size() && decision >= 1) { //the user can type a number between 1 and the number of items that matched their
                    isValid = true;                                    // search to have a valid response
                }
            }

            if (shop.getState().equals("no results")) { // if the state of the OnlineShopAZGroceries object is "no results"
                if (choice.equals("/")) {              // "/" is the only valid response
                    isValid = true;
                }
            }
            if (shop.getState().equals("no account")) {  // if the state of the OnlineShopAZGroceries object is "no account"
                int decision = Integer.parseInt(choice); // the user can type a number between 1 and 2 to have a valid response
                if (decision <= 2 && decision >= 1) {
                    isValid = true;
                }
            }
            if (shop.getState().equals("chosing to add to cart")) { // if the state of the OnlineShopAZGroceries object is "chosing to add to cart"
                if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N")) { // they can type Y, y, N or n to have a valid response
                    isValid = true;
                } else {
                    System.out.println("Invalid input. Please type \"Y\", \"N\", \"y\" or \"n\"");
                }
            }
            if (shop.getState().equals("select quantity")) { // if the state of the OnlineShopAZGroceries object is "select quantity"
                int decision = Integer.parseInt(choice);  // the user can type a number between 1 and the quantity of the searched item available
                if (decision <= shop.getSelectedItem().getQuantityAvailable() && decision >= 1) { // to have a valid response

                    isValid = true;
                } else { // if they provide an invalid response, they will be told why
                    if (decision > shop.getSelectedItem().getQuantityAvailable()) {
                        System.out.println("Invalid quantity. There is only " + shop.getSelectedItem().getQuantityAvailable() + " avaiable. Please try again");
                    }
                    if (decision <= 0) {
                        System.out.println("Invalid quantity. Atleast 1 item must be added");
                    }
                }
            }

        } catch (NumberFormatException | InputMismatchException e) { // if there are number format exceptions thrown or input mismatch exceptions thrown
            System.out.println("Invalid input. Please input again."); // the user's response will not be valid
            isValid = false;
        }
        return isValid; //
    }

    /**
     * @return the state
     */
// The public String getState() returns the OnlineShopAZGroceries object's state in the form of a String.
    public String getState() {
        return this.state;
    }

    /**
     * @param s the state to set
     */
 // The public void setState(String s) sets the OnlineShopAZGroceries object's state to the provided String (s).
    public void setState(String s) {
        this.state = s;
    }

    /**
     * @return the userAccount
     */
// The  public AZGroceriesAccount getUserAccount() returns the OnlineShopAZGroceries object's userAccount in the form of a AZGroceriesAccount.
    public AZGroceriesAccount getUserAccount() {
        return this.userAccount;
    }

    /**
     * @param aUserAccount the userAccount to set
     */
 // The public void setUserAccount(AZGroceriesAccount aUserAccount) sets the OnlineShopAZGroceries object's userAccount to the 
    // provided AZGroceriesAccount (aUserAccount).
    public void setUserAccount(AZGroceriesAccount aUserAccount) {
        this.userAccount = aUserAccount;
    }

    /**
     * @return the searched
     */
 // The public ArrayList<GroceryItems> getSearched() returns the OnlineShopAZGroceries object's searched in the form of 
 // an ArrayList of GroceryItems.
    public ArrayList<GroceryItems> getSearched() {
        return this.searched;
    }

    /**
     * @param aSearched the searched to set
     */
  // The public void setSearched(ArrayList<GroceryItems> aSearched) sets the OnlineShopAZGroceries object's searched to the provided 
 // ArrayList of GroceryItems (aSearched).
    public void setSearched(ArrayList<GroceryItems> aSearched) {
        this.searched = aSearched;
    }

    /**
     * @return the selectedItem
     */
// The public GroceryItems getSelectedItem() returns the OnlineShopAZGroceries object's selectedItem in the form of a GroceryItems object.
    public GroceryItems getSelectedItem() {
        return this.selectedItem;
    }

    /**
     * @param aSelectedItem the selectedItem to set
     */
   // The public void setSelectedItem(GroceryItems aSelectedItem) sets the OnlineShopAZGroceries object's selectedItem to the provided 
 // GroceryItems object (aSelectedItem).
    public void setSelectedItem(GroceryItems aSelectedItem) {
        this.selectedItem = aSelectedItem;
    }

    /**
     * @return the Categories
     */
// The public Categories getCategoryList() returns the OnlineShopAZGroceries object's categoryList in the form of a Categories object.
    public Categories getCategoryList() {
        return categoryList;
    }

    /**
     * @param categoryList the Categories to set
     */
 // The public void setCategoryList(Categories categoryList) sets the OnlineShopAZGroceries object's categoryList to the provided 
 // Categories object (categoryList).
    public void setCategoryList(Categories categoryList) {
        this.categoryList = categoryList;
    }

    /**
     * @return the groceryList
     */
// The public ListOfGroceries getGroceryList() returns the OnlineShopAZGroceries object's groceryList in the form of a ListOfGroceries object.
    public ListOfGroceries getGroceryList() {
        return groceryList;
    }

    /**
     * @param groceryList the groceryList to set
     */
 // The public void setGroceryList(ListOfGroceries groceryList) sets the OnlineShopAZGroceries object's groceryList to the provided 
 // ListOfGroceries object (groceryList).
    public void setGroceryList(ListOfGroceries groceryList) {
        this.groceryList = groceryList;
    }

    /**
     * @return the checkout
     */
 // The public CheckoutService getCheckout() returns the OnlineShopAZGroceries object's checkout in the form of a CheckoutService object.
    public CheckoutService getCheckout() {
        return checkout;
    }

    /**
     * @param checkout the checkout to set
     */
 // The public void setCheckout(CheckoutService checkout) sets the OnlineShopAZGroceries object's checkout to the provided 
 // CheckoutService object (checkout).
    public void setCheckout(CheckoutService checkout) {
        this.checkout = checkout;
    }

    /**
     * @return the signInService
     */
  // The public SignInService getSignInService() returns the OnlineShopAZGroceries object's signInService in the form of a SignInService object.
    public SignInService getSignInService() {
        return signInService;
    }

    /**
     * @param signInService the signInService to set
     */
    // The public void setSignInService(SignInService signInService) sets the OnlineShopAZGroceries object's signInService to the provided 
 // SignInService object (signInService).
    public void setSignInService(SignInService signInService) {
        this.signInService = signInService;
    }
}
