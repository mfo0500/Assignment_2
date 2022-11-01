/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

import java.util.ArrayList;
import onlineshopazgroceries.Controllers.AdminShopController;
import onlineshopazgroceries.Controllers.CustomerShopController;
import onlineshopazgroceries.Models.AdminShopModel;
import onlineshopazgroceries.Models.CustomerShopModel;
import onlineshopazgroceries.Views.AdminShopView;
import onlineshopazgroceries.Views.CustomerShopView;

/**
 *
 * @author krist
 */
public class ShopData {

    
    private boolean signedIn = false;
    private ListOfGroceries listOfGroceries;
    private AZGroceriesAccount userAccount;
    private boolean createAccountRequested = false;
    private boolean createCustomerAccountRequested = false;
    private boolean createAdminAccountRequested = false;
    private boolean neverFailedCreatingAccount = true; // boolean variable representing that user has never failed to create a AZGRoceriesAccount when opening the AZGroceries Online Store GUI
    private int numberOfReasonsAccountCreationFailed = 0;
    private boolean accountCreated = false;
    private String reasonAccountCreationFailed = "";
    private String reasonSearchFailed = "";
    private String reasonItemQuantityModificationFailed = "";
    private String reasonCartAdditionFailed = "";
    private String reasonCartUpdateFailed = "";
    private String searchQuery = "";
    private boolean requestingToAddItem = false;
    private boolean requestingToRemoveItem = false;
    private boolean requestingToModifyItemQuantityInShop = false;
    private boolean requestingToCheckout = false;
    private ArrayList<GroceryItems> searchedItems = new ArrayList<>();
    private boolean userSearching = false;
    private boolean attemptingSearch = false;
    private GroceryItems groceryItemRequestedForCart;
    private GroceryItems cartItemRequestedForUpdate; 
    private boolean itemRemovedFromCart = false;
    private boolean itemAddedToCart = false;
   
    private boolean mainMenuRequested = false;
    private boolean viewOrderHistoryRequested = false;
    private boolean viewCartRequested = false;
    private boolean cartUpdated = false;
    private int quantityOfProductAddedToCart = 0;
    
    
    private boolean quantityTextFieldUpdated = false;
    private int updatedQuantityTextFieldValue = 0;
    private int updatedCartItemQuantityPurchasedLabelNumber = 0;
    private int numberOfUpdatedQuantityTextField ;
    private boolean itemAdded = false;
    private boolean itemRemoved = false;
    private boolean itemQuantityModified = false;
    private String reasonItemAdditionFailed = "";
    private GroceryItems groceryItemAdded;
    private GroceryItems modifiedGroceryItem;
    
    private AdminShopModel adminShopModel;
    private AdminShopController adminShopController;
    private AdminShopView adminShopView;
    
    private CustomerShopView customerShopview;
    private CustomerShopController customerShopController;
    private CustomerShopModel customerShopModel;
    private boolean neverPressedMainMenuButton = true;
    
    private boolean purchaseRequested = false;
    private boolean purcahseSucessful = false;
    private String reasonPurchaseFailed = "";

    /**
     * @return the userAccount
     */
    public AZGroceriesAccount getUserAccount() {
        return userAccount;
    }

    /**
     * @param userAccount the userAccount to set
     */
    public void setUserAccount(AZGroceriesAccount userAccount) {
        this.userAccount = userAccount;
    }
    
    /**
     * @return the signedIn
     */
    public boolean isSignedIn() {
        return signedIn;
    }

    /**
     * @param signedIn the signedIn to set
     */
    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }

    /**
     * @return the createAccountRequested
     */
    public boolean isCreateAccountRequested() {
        return createAccountRequested;
    }

    /**
     * @param createAccountRequested the createAccountRequested to set
     */
    public void setCreateAccountRequested(boolean createAccountRequested) {
        this.createAccountRequested = createAccountRequested;
    }

    /**
     * @return the createCustomerAccountRequested
     */
    public boolean isCreateCustomerAccountRequested() {
        return createCustomerAccountRequested;
    }

    /**
     * @param createCustomerAccountRequested the createCustomerAccountRequested to set
     */
    public void setCreateCustomerAccountRequested(boolean createCustomerAccountRequested) {
        this.createCustomerAccountRequested = createCustomerAccountRequested;
    }

    /**
     * @return the createAdminAccountRequested
     */
    public boolean isCreateAdminAccountRequested() {
        return createAdminAccountRequested;
    }

    /**
     * @param createAdminAccountRequested the createAdminAccountRequested to set
     */
    public void setCreateAdminAccountRequested(boolean createAdminAccountRequested) {
        this.createAdminAccountRequested = createAdminAccountRequested;
    }

    /**
     * @return the accountCreated
     */
    public boolean isAccountCreated() {
        return accountCreated;
    }

    /**
     * @param accountCreated the accountCreated to set
     */
    public void setAccountCreated(boolean accountCreated) {
        this.accountCreated = accountCreated;
    }

    /**
     * @return the reasonAccountCreationFailed
     */
    public String getReasonAccountCreationFailed() {
        return reasonAccountCreationFailed;
    }

    /**
     * @param reasonAccountCreationFailed the reasonAccountCreationFailed to set
     */
    public void setReasonAccountCreationFailed(String reasonAccountCreationFailed) {
        this.reasonAccountCreationFailed = reasonAccountCreationFailed;
    }

    /**
     * @return the neverFailedCreatingAccount
     */
    public boolean neverFailedCreatingAccount() {
        return neverFailedCreatingAccount;
    }

    /**
     * @param neverFailedCreatingAccount the neverFailedCreatingAccount to set
     */
    public void setNeverFailedCreatingAccount(boolean neverFailedCreatingAccount) {
        this.neverFailedCreatingAccount = neverFailedCreatingAccount;
    }

    /**
     * @return the numberOfReasonsAccountCreationFailed
     */
    public int getNumberOfReasonsAccountCreationFailed() {
        return numberOfReasonsAccountCreationFailed;
    }

    /**
     * @param numberOfReasonsAccountCreationFailed the numberOfReasonsAccountCreationFailed to set
     */
    public void setNumberOfReasonsAccountCreationFailed(int numberOfReasonsAccountCreationFailed) {
        this.numberOfReasonsAccountCreationFailed = numberOfReasonsAccountCreationFailed;
    }

    /**
     * @return the listOfGroceries
     */
    public ListOfGroceries getListOfGroceries() {
        return listOfGroceries;
    }

    /**
     * @param listOfGroceries the listOfGroceries to set
     */
    public void setListOfGroceries(ListOfGroceries listOfGroceries) {
        this.listOfGroceries = listOfGroceries;
    }

    /**
     * @return the searchedItems
     */
    public ArrayList<GroceryItems> getSearchedItems() {
        return searchedItems;
    }

    /**
     * @param searchedItems the searchedItems to set
     */
    public void setSearchedItems(ArrayList<GroceryItems> searchedItems) {
        this.searchedItems = searchedItems;
    }

    /**
     * @return the userSearching
     */
    public boolean isUserSearching() {
        return userSearching;
    }

    /**
     * @param userSearching the userSearching to set
     */
    public void setUserSearching(boolean userSearching) {
        this.userSearching = userSearching;
    }

    /**
     * @return the reasonSearchFailed
     */
    public String getReasonSearchFailed() {
        return reasonSearchFailed;
    }

    /**
     * @param reasonSearchFailed the reasonSearchFailed to set
     */
    public void setReasonSearchFailed(String reasonSearchFailed) {
        this.reasonSearchFailed = reasonSearchFailed;
    }

    /**
     * @return the searchQuery
     */
    public String getSearchQuery() {
        return searchQuery;
    }

    /**
     * @param searchQuery the searchQuery to set
     */
    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    /**
     * @return the requestingToAddItem
     */
    public boolean isRequestingToAddItem() {
        return requestingToAddItem;
    }

    /**
     * @param requestingToAddItem the requestingToAddItem to set
     */
    public void setRequestingToAddItem(boolean requestingToAddItem) {
        this.requestingToAddItem = requestingToAddItem;
    }

    /**
     * @return the requestingToRemoveItem
     */
    public boolean isRequestingToRemoveItem() {
        return requestingToRemoveItem;
    }

    /**
     * @param requestingToRemoveItem the requestingToRemoveItem to set
     */
    public void setRequestingToRemoveItem(boolean requestingToRemoveItem) {
        this.requestingToRemoveItem = requestingToRemoveItem;
    }

    /**
     * @return the itemAdded
     */
    public boolean isItemAdded() {
        return itemAdded;
    }

    /**
     * @param itemAdded the itemAdded to set
     */
    public void setItemAdded(boolean itemAdded) {
        this.itemAdded = itemAdded;
    }

    /**
     * @return the requestingToModifyItemQuantityInShop
     */
    public boolean isRequestingToModifyItemQuantityInShop() {
        return requestingToModifyItemQuantityInShop;
    }

    /**
     * @param requestingToModifyItemQuantityInShop the requestingToModifyItemQuantityInShop to set
     */
    public void setRequestingToModifyItemQuantityInShop(boolean requestingToModifyItemQuantityInShop) {
        this.requestingToModifyItemQuantityInShop = requestingToModifyItemQuantityInShop;
    }

    /**
     * @return the reasonItemAdditionFailed
     */
    public String getReasonItemAdditionFailed() {
        return reasonItemAdditionFailed;
    }

    /**
     * @param reasonItemAdditionFailed the reasonItemAdditionFailed to set
     */
    public void setReasonItemAdditionFailed(String reasonItemAdditionFailed) {
        this.reasonItemAdditionFailed = reasonItemAdditionFailed;
    }

    /**
     * @return the groceryItemAdded
     */
    public GroceryItems getGroceryItemAdded() {
        return groceryItemAdded;
    }

    /**
     * @param groceryItemAdded the groceryItemAdded to set
     */
    public void setGroceryItemAdded(GroceryItems groceryItemAdded) {
        this.groceryItemAdded = groceryItemAdded;
    }

    /**
     * @return the itemRemoved
     */
    public boolean isItemRemoved() {
        return itemRemoved;
    }

    /**
     * @param itemRemoved the itemRemoved to set
     */
    public void setItemRemoved(boolean itemRemoved) {
        this.itemRemoved = itemRemoved;
    }

    /**
     * @return the attemptingSearch
     */
    public boolean isAttemptingSearch() {
        return attemptingSearch;
    }

    /**
     * @param attemptingSearch the attemptingSearch to set
     */
    public void setAttemptingSearch(boolean attemptingSearch) {
        this.attemptingSearch = attemptingSearch;
    }

    /**
     * @return the updatedQuantityTextFieldValue
     */
    public int getUpdatedQuantityTextFieldValue() {
        return updatedQuantityTextFieldValue;
    }

    /**
     * @param updatedQuantityTextFieldValue the updatedQuantityTextFieldValue to set
     */
    public void setUpdatedQuantityTextFieldValue(int updatedQuantityTextFieldValue) {
        this.updatedQuantityTextFieldValue = updatedQuantityTextFieldValue;
    }


    /**
     * @return the numberOfUpdatedQuantityTextField
     */
    public int getNumberOfUpdatedQuantityTextField() {
        return numberOfUpdatedQuantityTextField;
    }

    /**
     * @param numberOfUpdatedQuantityTextField the numberOfUpdatedQuantityTextField to set
     */
    public void setNumberOfUpdatedQuantityTextField(int numberOfUpdatedQuantityTextField) {
        this.numberOfUpdatedQuantityTextField = numberOfUpdatedQuantityTextField;
    }

    /**
     * @return the reasonItemQuantityModificationFailed
     */
    public String getReasonItemQuantityModificationFailed() {
        return reasonItemQuantityModificationFailed;
    }

    /**
     * @param reasonItemQuantityModificationFailed the reasonItemQuantityModificationFailed to set
     */
    public void setReasonItemQuantityModificationFailed(String reasonItemQuantityModificationFailed) {
        this.reasonItemQuantityModificationFailed = reasonItemQuantityModificationFailed;
    }

    /**
     * @return the modifiedGroceryItem
     */
    public GroceryItems getModifiedGroceryItem() {
        return modifiedGroceryItem;
    }

    /**
     * @param modifiedGroceryItem the modifiedGroceryItem to set
     */
    public void setModifiedGroceryItem(GroceryItems modifiedGroceryItem) {
        this.modifiedGroceryItem = modifiedGroceryItem;
    }

    /**
     * @return the groceryItemRequestedForCart
     */
    public GroceryItems getGroceryItemRequestedForCart() {
        return groceryItemRequestedForCart;
    }

    /**
     * @param groceryItemRequestedForCart the groceryItemRequestedForCart to set
     */
    public void setGroceryItemRequestedForCart(GroceryItems groceryItemRequestedForCart) {
        this.groceryItemRequestedForCart = groceryItemRequestedForCart;
    }

    /**
     * @return the itemAddedToCart
     */
    public boolean isItemAddedToCart() {
        return itemAddedToCart;
    }

    /**
     * @param itemAddedToCart the itemAddedToCart to set
     */
    public void setItemAddedToCart(boolean itemAddedToCart) {
        this.itemAddedToCart = itemAddedToCart;
    }

    /**
     * @return the viewCartRequested
     */
    public boolean isViewCartRequested() {
        return viewCartRequested;
    }

    /**
     * @param viewCartRequested the viewCartRequested to set
     */
    public void setViewCartRequested(boolean viewCartRequested) {
        this.viewCartRequested = viewCartRequested;
    }

    /**
     * @return the reasonCartAdditionFailed
     */
    public String getReasonCartAdditionFailed() {
        return reasonCartAdditionFailed;
    }

    /**
     * @param reasonCartAdditionFailed the reasonCartAdditionFailed to set
     */
    public void setReasonCartAdditionFailed(String reasonCartAdditionFailed) {
        this.reasonCartAdditionFailed = reasonCartAdditionFailed;
    }


    /**
     * @return the itemRemovedFromCart
     */
    public boolean isItemRemovedFromCart() {
        return itemRemovedFromCart;
    }

    /**
     * @param itemRemovedFromCart the itemRemovedFromCart to set
     */
    public void setItemRemovedFromCart(boolean itemRemovedFromCart) {
        this.itemRemovedFromCart = itemRemovedFromCart;
    }

    /**
     * @return the reasonCartUpdateFailed
     */
    public String getReasonCartUpdateFailed() {
        return reasonCartUpdateFailed;
    }

    /**
     * @param reasonCartUpdateFailed the reasonCartUpdateFailed to set
     */
    public void setReasonCartUpdateFailed(String reasonCartUpdateFailed) {
        this.reasonCartUpdateFailed = reasonCartUpdateFailed;
    }

    /**
     * @return the cartUpdated
     */
    public boolean isCartUpdated() {
        return cartUpdated;
    }

    /**
     * @param cartUpdated the cartUpdated to set
     */
    public void setCartUpdated(boolean cartUpdated) {
        this.cartUpdated = cartUpdated;
    }

    /**
     * @return the cartItemRequestedForUpdate
     */
    public GroceryItems getCartItemRequestedForUpdate() {
        return cartItemRequestedForUpdate;
    }

    /**
     * @param cartItemRequestedForUpdate the cartItemRequestedForUpdate to set
     */
    public void setCartItemRequestedForUpdate(GroceryItems cartItemRequestedForUpdate) {
        this.cartItemRequestedForUpdate = cartItemRequestedForUpdate;
    }

    /**
     * @return the updatedCartItemQuantityPurchasedLabelNumber
     */
    public int getUpdatedCartItemQuantityPurchasedLabelNumber() {
        return updatedCartItemQuantityPurchasedLabelNumber;
    }

    /**
     * @param updatedCartItemQuantityPurchasedLabelNumber the updatedCartItemQuantityPurchasedLabelNumber to set
     */
    public void setUpdatedCartItemQuantityPurchasedLabelNumber(int updatedCartItemQuantityPurchasedLabelNumber) {
        this.updatedCartItemQuantityPurchasedLabelNumber = updatedCartItemQuantityPurchasedLabelNumber;
    }

    
    /**
     * @return the quantityOfProductAddedToCart
     */
    public int getQuantityOfProductAddedToCart() {
        return quantityOfProductAddedToCart;
    }

    /**
     * @param quantityOfProductAddedToCart the quantityOfProductAddedToCart to set
     */
    public void setQuantityOfProductAddedToCart(int quantityOfProductAddedToCart) {
        this.quantityOfProductAddedToCart = quantityOfProductAddedToCart;
    }

    /**
     * @return the itemQuantityModified
     */
    public boolean isItemQuantityModified() {
        return itemQuantityModified;
    }

    /**
     * @param itemQuantityModified the itemQuantityModified to set
     */
    public void setItemQuantityModified(boolean itemQuantityModified) {
        this.itemQuantityModified = itemQuantityModified;
    }

    /**
     * @return the quantityTextFieldUpdated
     */
    public boolean isQuantityTextFieldUpdated() {
        return quantityTextFieldUpdated;
    }

    /**
     * @param quantityTextFieldUpdated the quantityTextFieldUpdated to set
     */
    public void setQuantityTextFieldUpdated(boolean quantityTextFieldUpdated) {
        this.quantityTextFieldUpdated = quantityTextFieldUpdated;
    }

    /**
     * @return the mainMenuRequested
     */
    public boolean isMainMenuRequested() {
        return mainMenuRequested;
    }

    /**
     * @param mainMenuRequested the mainMenuRequested to set
     */
    public void setMainMenuRequested(boolean mainMenuRequested) {
        this.mainMenuRequested = mainMenuRequested;
    }

    /**
     * @return the viewOrderHistoryRequested
     */
    public boolean isViewOrderHistoryRequested() {
        return viewOrderHistoryRequested;
    }

    /**
     * @param viewOrderHistoryRequested the viewOrderHistoryRequested to set
     */
    public void setViewOrderHistoryRequested(boolean viewOrderHistoryRequested) {
        this.viewOrderHistoryRequested = viewOrderHistoryRequested;
    }

    /**
     * @return the requestingToCheckout
     */
    public boolean isRequestingToCheckout() {
        return requestingToCheckout;
    }

    /**
     * @param requestingToCheckout the requestingToCheckout to set
     */
    public void setRequestingToCheckout(boolean requestingToCheckout) {
        this.requestingToCheckout = requestingToCheckout;
    }

    /**
     * @return the adminShopModel
     */
    public AdminShopModel getAdminShopModel() {
        return adminShopModel;
    }

    /**
     * @param adminShopModel the adminShopModel to set
     */
    public void setAdminShopModel(AdminShopModel adminShopModel) {
        this.adminShopModel = adminShopModel;
    }

    /**
     * @return the adminShopController
     */
    public AdminShopController getAdminShopController() {
        return adminShopController;
    }

    /**
     * @param adminShopController the adminShopController to set
     */
    public void setAdminShopController(AdminShopController adminShopController) {
        this.adminShopController = adminShopController;
    }

    /**
     * @return the adminShopView
     */
    public AdminShopView getAdminShopView() {
        return adminShopView;
    }

    /**
     * @param adminShopView the adminShopView to set
     */
    public void setAdminShopView(AdminShopView adminShopView) {
        this.adminShopView = adminShopView;
    }

    /**
     * @return the customerShopview
     */
    public CustomerShopView getCustomerShopview() {
        return customerShopview;
    }

    /**
     * @param customerShopview the customerShopview to set
     */
    public void setCustomerShopview(CustomerShopView customerShopview) {
        this.customerShopview = customerShopview;
    }

    /**
     * @return the customerShopController
     */
    public CustomerShopController getCustomerShopController() {
        return customerShopController;
    }

    /**
     * @param customerShopController the customerShopController to set
     */
    public void setCustomerShopController(CustomerShopController customerShopController) {
        this.customerShopController = customerShopController;
    }

    /**
     * @return the customerShopModel
     */
    public CustomerShopModel getCustomerShopModel() {
        return customerShopModel;
    }

    /**
     * @param customerShopModel the customerShopModel to set
     */
    public void setCustomerShopModel(CustomerShopModel customerShopModel) {
        this.customerShopModel = customerShopModel;
    }

    /**
     * @return the neverPressedMainMenuButton
     */
    public boolean isNeverPressedMainMenuButton() {
        return neverPressedMainMenuButton;
    }

    /**
     * @param neverPressedMainMenuButton the neverPressedMainMenuButton to set
     */
    public void setNeverPressedMainMenuButton(boolean neverPressedMainMenuButton) {
        this.neverPressedMainMenuButton = neverPressedMainMenuButton;
    }

    /**
     * @return the purchaseRequested
     */
    public boolean isPurchaseRequested() {
        return purchaseRequested;
    }

    /**
     * @param purchaseRequested the purchaseRequested to set
     */
    public void setPurchaseRequested(boolean purchaseRequested) {
        this.purchaseRequested = purchaseRequested;
    }

    /**
     * @return the purcahseSucessful
     */
    public boolean isPurcahseSucessful() {
        return purcahseSucessful;
    }

    /**
     * @param purcahseSucessful the purcahseSucessful to set
     */
    public void setPurcahseSucessful(boolean purcahseSucessful) {
        this.purcahseSucessful = purcahseSucessful;
    }

    /**
     * @return the reasonPurchaseFailed
     */
    public String getReasonPurchaseFailed() {
        return reasonPurchaseFailed;
    }

    /**
     * @param reasonPurchaseFailed the reasonPurchaseFailed to set
     */
    public void setReasonPurchaseFailed(String reasonPurchaseFailed) {
        this.reasonPurchaseFailed = reasonPurchaseFailed;
    }

}
