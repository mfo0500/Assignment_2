/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

/**
 *
 * @author krist
 */
// The GroceryItems object represents a grocery item sold in the AZGroceries Online shop
// Every GroceryItem object has a price, productName, category and quantityAvailable. 
// The price of the grocery item is stored in a double variable called price.
// The product name of the grocery item is stored in a String variable called productName.
// The category of the grocery item is stored in a String variable called category.
// The avaiability of the grocery item is stored in a integer variable called quantityAvailable.
public class GroceryItems implements Comparable<GroceryItems> {

    private double price;
    private String productName;
    private String category;
    private int quantityAvailable;

    /**
     * @return the price
     * 
     */
    // The public double getPrice() method returns the GroceryItems object's price in the form of a double
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    // The public void method setPrice( double price) method sets the GroceryItems object's price to the double (price) provided
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the productName
     */
    // The public String getProductName() returns the GroceryItems object's productName
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
     // The public void setProductName(String productName) sets the GroceryItems object's productName using the productName string provided;
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    
    /**
     * @return the Category
     */
    // The public String getCategory() method returns the GroceryItems object's category in the form of a String
    public String getCategory() {
        return category;
    }

    /**
     * @param Catagory the Category to set
     */
    // The public void setCategory(String Catagory) method sets the GroceryItems object's category using the provided String (Category)
    public void setCategory(String Catagory) {
        this.category = Catagory;
    }

    /**
     * @return the quantityAvailable
     */
    // The public int getQuantityAvailable() method returns the GroceryItems object's quantityAvailable in the form of an integer
    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    /**
     * @param quantityAvailable the quantityAvailable to set
     */
    // The public void setQuantityAvailable(int quantityAvailable) method sets the
    // GroceryItems object's quantityAvailable to the prodived integer (quantityAvailable)
    public void setQuantityAvailable(int quantityAvailable) {
        if (quantityAvailable >= 0) {
            this.quantityAvailable = quantityAvailable;
        } else {
            this.quantityAvailable = 0;
        }

    }

    // The GroceryItems(String name, double price, String category, int quantity) constructor 
    // creates GroceryItems objects with 2 provided Strings (name and  category), 1 provided double (price), and one provided integer (quantity)
    // setProductName(name) sets the GroceryItems object's productName to the provided string (name).
    // setPrice(price) sets the GroceryItems object's price to the provided double (price).
    // setCategory(category)sets the GroceryItems object's category to the provided String (category).
    // setQuantityAvailable(quantity) sets the GroceryItems object's quantityAvailable to the prodived integer (quantity)
    
    GroceryItems(String name, double price, String category, int quantity) {
        setProductName(name);
        setPrice(price);
        setCategory(category);
        setQuantityAvailable(quantity);
    }

    // The public int compareTo(GroceryItems o) method compares the price of GroceryItems objects
    // This method returns -1 if a GroceryItems object is cheaper than the other
    // This method returns 0 if the GroceryItems object is the same price as the other
    // This method returns 1 if the GroceryItems object is more expensive than the other 
    // This method assists in the ordering of GroceryItem objects in terms of their price
    @Override
    public int compareTo(GroceryItems o) {
        int c = 0;
        if (getPrice() < o.getPrice()) {
            c = -1;
        }
        if (getPrice() == o.getPrice()) {
            c = 0;
        }
        if (getPrice() > o.getPrice()) {
            c = 1;
        }
        return c;
    }


}
