/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

/**
 *
 * @author krist
 */
public class ShopData {

    
    private boolean signedIn = false;
    public String username;
    private AZGroceriesAccount userAccount;
    private boolean createAccountRequested = false;
    private boolean createCustomerAccountRequested = false;
    private boolean createAdminAccountRequested = false;
    private boolean neverFailedCreatingAccount = true; // boolean variable representing that user has never failed to create a AZGRoceriesAccount when opening the AZGroceries Online Store GUI
    private int numberOfReasonsAccountCreationFailed = 0;
    private boolean accountCreated = false;
    private String reasonAccountCreationFailed = "";
    

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
    
}
