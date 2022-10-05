/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author krist
 */
// The Categories object represents categories of the AZGroceries online shop.
// The Categories of a Categories object is a HashSet of Strings representing all the categories of the AZGroceries online shop.
// The Groceries of a Categories object is a LinkedList of GroceryItems representing all the groceries items of the AZGroceries online shop.
// The selectedCategory of a Categories object represents the category the user of the website the user has chosen to search in. 
public class Categories 
{
    
    private HashSet<String> Categories; ;
    private LinkedList<GroceryItems> Groceries;
    private String selectedCategory;
    
// The public Categories() default constructor 
// creates Categories objects 
// Categories is set to a new HashSetof strings
//   selectedCategory is set to ""
    public Categories()
    {
        setCategories(new HashSet<String>());
        setSelectedCategory("");
    }
    /**
     * @return the Categories
     */
    // The public HashSet<String> getCategories() returns the Categories object's Categories in the form of a HashSet of Strings.
    public HashSet<String> getCategories() {
        return Categories;
    }

    /**
     * @param Categories the Categories to set
     */
    // The public void setCategories(HashSet<String> Categories) sets the Categories object's Categories to the provided
    // HashSet of Strings (Categories)
    public void setCategories(HashSet<String> Categories) {
        this.Categories = Categories;
    }
    
    // The public void ListCategories () creates a new ListOfGroceries object and loads all the grocery items and adds the category 
    // of each grocery item to the Categories object's Categories. Since Categories is a HashSet, only different categories will be stored
    // All categories are then numbered and displayed on the console
    public void ListCategories ()
    {
        ListOfGroceries groceryList = new ListOfGroceries();
        groceryList.loadGroceries();
        
        for (GroceryItems g: groceryList.getGroceries())
        {
            getCategories().add(g.getCategory());
        }
                
        String categoryList = "";
        int i = 1;
        for  (String e: getCategories() )
        {
            categoryList += i + ". " + e +"\n";
            i++;
        }
        System.out.println(categoryList);
        
                
    }
     

    /**
     * @return the Groceries
     */
    // The public LinkedList<GroceryItems> getGroceries() returns the Categories object's Groceries in the form of a 
    // LinkedList of GroceryItems
    public LinkedList<GroceryItems> getGroceries() {
        return Groceries;
    }

    /**
     * @param Groceries the Groceries to set
     */
    // The public void setGroceries(LinkedList<GroceryItems> Groceries) sets the Categories object's 
    // Groceries to the LinkedList of GroceryItems provided (Groceries)
    
    public void setGroceries(LinkedList<GroceryItems> Groceries) {
        this.Groceries = Groceries;
    }

    /**
     * @return the selectedCategory
     */
    // The public String getSelectedCategory() returns the Categories object's selectedCategory in the form of a String
    public String getSelectedCategory() {
        return selectedCategory;
    }

    /**
     * @param selectedCategory the selectedCategory to set
     */
    // The public void setSelectedCategory(String selectedCategory) sets the Categories object's 
    // selectedCategory to the String provided (selectedCategory)
    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }
    
    
}
