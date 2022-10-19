/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author krist
 */
// The Cart is an object that stores a HashMap of GroceryItems with a certain quantity. 
// The Cart object also stores the total price of all the GroceryItems the user wants to buy. 
// It is essentially all the items the user is considering to buy and how much of each GroceryItem they want to buy
// A cart is a collection of items the user is planning to purchase as well as the amount of each grocery item they wish to purchase.
// A cart also contains the total cost of all the grocery items the user of the AZGroceriesAccount
public class Cart implements IsValid {

    private double total;
    private HashMap<GroceryItems, Integer> itemsAdded;
    private GroceryItems[] items;
    private GroceryItems item;
    private String state;

    Cart() {
        setTotal(0.00);
        setItemsAdded(new HashMap<GroceryItems, Integer>());

    }
//
//    public void displayCart(OnlineShopAZGroceries shop) {
//        //       GroceryItems[] userItems = new GroceryItems[a.getUserCart().getItemsAdded().size()];
//
//        System.out.println(shop.getUserAccount().getUserCart().toString(shop));
//    }

    public String toString(OnlineShopAZGroceries shop) {
        int i = 1;
        String cartString = "";
        //       GroceryItems[] userItems = new GroceryItems[a.getUserCart().getItemsAdded().size()];
        for (GroceryItems g : shop.getUserAccount().getUserCart().getItemsAdded().keySet()) {
//            userItems[i] = g;
            String quantity = getItemsAdded().get(g).toString();
            cartString += i + ". Product name: " + g.getProductName() + ", Price: $" + g.getPrice() + ", Quantity: " + quantity + "\n";

            i++;

        }
        cartString += "Total: $" + shop.getUserAccount().getUserCart().getTotal();

        return cartString;
    }

    public void addItem(OnlineShopAZGroceries shop, GroceryItems g, int quantity) {
        if(shop.getUserAccount().getUserCart().getItemsAdded().containsKey(g))
            {
                shop.getUserAccount().getUserCart().getItemsAdded().replace(shop.getSelectedItem(),  quantity + shop.getUserAccount().getUserCart().getItemsAdded().get(g));
                shop.getUserAccount().getUserCart().setTotal(shop.getUserAccount().getUserCart().getTotal() + g.getPrice() * (double) quantity);
            }
        else
        {
            shop.getUserAccount().getUserCart().getItemsAdded().put(g, quantity);
        shop.getUserAccount().getUserCart().setTotal(shop.getUserAccount().getUserCart().getTotal() + g.getPrice() * (double) quantity);
        
        }
        System.out.println("Item sucessfully added to cart");
        System.out.println("You will be directed to the home page");
        shop.openStore(shop);
    }

    public void cartOptions(OnlineShopAZGroceries shop) {
        if(shop.getUserAccount().getUserCart().getTotal() == 0.0)
        {
            System.out.println("Your cart is empty");
            System.out.println("You will be delivered to the homepage");
            shop.openStore(shop);
        }
        System.out.println(shop.getUserAccount().getUserCart().toString(shop));
        System.out.println("Would you like to:\n1. Remove an item\n2. Empty cart\n3. Change the quantity of an item\n4. Checkout\n5. Continue Shopping\nPress x or X to exit\nPress / to go back");
        setState("choose cart options");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        while (!IsValid( choice)) {
            
            choice = scan.nextLine();

        }
        if (IsValid(choice)) {
            int preference = Integer.parseInt(choice);
            switch (preference) {
                case 1:
                    removeItem(shop);
                    break;
                case 2:
                    shop.getUserAccount().getUserCart().getItemsAdded().clear();
                    shop.getUserAccount().getUserCart().setTotal(0.0);

                    System.out.println("Your cart has been emptied");
                    shop.openStore(shop);
                    break;
                case 3:
                    chooseItemToModify(shop);
                    break;
                case 4:

                    shop.getCheckout().pickupOrDelivery(shop);
                    break;
                case 5:
                    shop.openStore(shop);
                    break;

            }
        }

    }

    public void chooseItemToModify(OnlineShopAZGroceries shop) {
        System.out.println("Which item quantity would you like to modify?");
        setState("change quantity");
        System.out.println(shop.getUserAccount().getUserCart().toString(shop));
        
        GroceryItems[] userItems =  new GroceryItems[shop.getUserAccount().getUserCart().getItemsAdded().size()];
        int i = 0;
        for(GroceryItems g : shop.getUserAccount().getUserCart().getItemsAdded().keySet())
        {
            userItems[i] = g;
            i++;
        }
        Scanner scanner = new Scanner(System.in);
        String itemChosen = scanner.nextLine();
        while (!IsValid( itemChosen)) {
            itemChosen = scanner.nextLine();
        }
        if (IsValid( itemChosen)) {
            chooseDesiredQuantity(shop, itemChosen);
        }

    }
    
    public void chooseDesiredQuantity(OnlineShopAZGroceries shop, String itemChosen)
    {
         Scanner scanner = new Scanner(System.in);
         GroceryItems[] userItems =  new GroceryItems[shop.getUserAccount().getUserCart().getItemsAdded().size()];
        int i = 0;
        for(GroceryItems g : shop.getUserAccount().getUserCart().getItemsAdded().keySet())
        {
            userItems[i] = g;
            i++;
        }
        
        int chosenItem = Integer.parseInt(itemChosen) - 1;
            System.out.println("How much of this item would you like to purchase?");
            setState("chosen quantity");
            String quantityChosen = scanner.nextLine();
            while (!IsValid(quantityChosen)) {
                quantityChosen = scanner.nextLine();

            }
            if (IsValid(quantityChosen)) {
                int chosenQuantity = Integer.parseInt(quantityChosen);
                if (chosenQuantity == 0) {

                    shop.getUserAccount().getUserCart().setTotal(shop.getUserAccount().getUserCart().getTotal() - userItems[chosenItem].getPrice() * (double) shop.getUserAccount().getUserCart().getItemsAdded().get(userItems[chosenItem]) );
            shop.getUserAccount().getUserCart().getItemsAdded().remove(userItems[chosenItem]);

                    System.out.println("Your item has been sucessfully removed");
                    shop.openStore(shop);
                } else {
                    int changeInItemQuntity = chosenQuantity - shop.getUserAccount().getUserCart().getItemsAdded().get(userItems[chosenItem]);
                    shop.getUserAccount().getUserCart().getItemsAdded().replace(userItems[chosenItem], chosenQuantity);
                    shop.getUserAccount().getUserCart().setTotal(shop.getUserAccount().getUserCart().getTotal() + (double)changeInItemQuntity * userItems[chosenItem].getPrice()  );

                    System.out.println("Your item has been sucessfully modified");
                    shop.openStore(shop);
                }

            }
    }

    public void removeItem(OnlineShopAZGroceries shop) {
        System.out.println("Which item would you like removed?");
        setState("remove item");
        System.out.println(shop.getUserAccount().getUserCart().toString(shop));
        
        GroceryItems[] userItems =  new GroceryItems[shop.getUserAccount().getUserCart().getItemsAdded().size()];
        int i = 0;
        for(GroceryItems g : shop.getUserAccount().getUserCart().getItemsAdded().keySet())
        {
            userItems[i] = g;
            i++;
        }
        Scanner scanner = new Scanner(System.in);
        String itemChosen = scanner.nextLine();
        while (!IsValid( itemChosen)) {
            System.out.println("Invalid input. Please try again");
             System.out.println("Which item would you like removed?");
             System.out.println(shop.getUserAccount().getUserCart().toString(shop));
             System.out.println("\nPress x or X to exit\nPress / to go back");
            itemChosen = scanner.nextLine();
        }
        if (IsValid( itemChosen)) {
            int chosenItem = Integer.parseInt(itemChosen) - 1;
            shop.getUserAccount().getUserCart().setTotal(shop.getUserAccount().getUserCart().getTotal() - userItems[chosenItem].getPrice() * shop.getUserAccount().getUserCart().getItemsAdded().get(userItems[chosenItem]) );
            shop.getUserAccount().getUserCart().getItemsAdded().remove(userItems[chosenItem]);
            
            System.out.println("Your item has been sucessfully removed");
            shop.openStore(shop);

        }

    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the itemsAdded
     */
    public HashMap<GroceryItems, Integer> getItemsAdded() {
        return itemsAdded;
    }

    /**
     * @param itemsAdded the itemsAdded to set
     */
    public void setItemsAdded(HashMap<GroceryItems, Integer> itemsAdded) {
        this.itemsAdded = itemsAdded;
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

    /**
     * @return the items
     */
    public GroceryItems[] getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(GroceryItems[] items) {
        this.items = items;
    }

    /**
     * @return the item
     */
    public GroceryItems getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(GroceryItems item) {
        this.item = item;
    }

     // The public boolean IsValid(OnlineShopAZGroceries shop, String choice) is the implementation of the interface IsValidusing the 
    //  public boolean IsValid(OnlineShopAZGroceries shop, String choice) function prototype
    // This function checks the validity of the users reponse provided (String choice) to every situation where a user is asked for a response 
    

    @Override
    public boolean IsValid(String choice) {
// Exit the shop if the user inputs 'x' or 'X'.
        if (choice.equals("x") || choice.equals("X")) {       
            System.exit(0);
        }
        if (choice.equals("/")) {
            if(getState().equals("choose cart options"))
            {
                // open store 
            }
            if(getState().equals("remove item") || getState().equals("change quantity") )
            {
               // display  cart options
            }
            if(getState().equals("chosen quantity"))
            {
               // choose item to modify
            }
        }
        boolean isValid = false;
        try {

            
            if (getState().equals("choose cart options")) {
                int decision = Integer.parseInt(choice);
                if (decision <= 5 && decision >= 1) {
                    isValid = true;
                }
            }
                if (getState().equals("change quantity") || getState().equals("remove item")) {
                    int decision = Integer.parseInt(choice);
                    if (decision <= getItemsAdded().size() && decision >= 1) {
                        isValid = true;
                    }

                }
                if (getState().equals("chosen quantity")) {
                    int decision = Integer.parseInt(choice);
//                    if (decision >= 0 && decision <=  shop.getSelectedItem().getQuantityAvailable()) {
//                        isValid = true;
//                    }

                }

            

        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println("Invalid input. Please input again.");
            isValid = false;
        }
        return isValid;    }

}
