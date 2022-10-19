/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

import onlineshopazgroceries.Model.ShopModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author krist
 */
public class ShopController implements ActionListener{
    SignInServiceModel model;
    ShopView view;
    
    public ShopController(ShopView view, SignInServiceModel model)
    {
        this.view = view;
        this.model = model;
        this.view.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(e.getSource() == this.view.getOfficialCreateCustomerAccountButton())
        {
            command += " (official customer)";
        }
        if(e.getSource() == this.view.getOfficialCreateAdminAccountButton())
        {
            command += " (official admin)";
        }
                switch(command)
                {
                    case "Sign in":
                        String username = this.view.getUsernameTextField().getText();
                        String password = this.view.getPasswordTextField().getText();
                        String employeeID = this.view.getEmployeeIDTextField().getText();
                        this.model.signIn(username, password, employeeID);
                        break;
                        
                    case "Create Account":
                        this.model.createAccount();
                        break;
                    case "Continue as Guest":
                        
                        this.model.continueAsGuest();
                        break;    
                    case "Create Customer Account":
                        
                        this.model.createCustomerAccount();
                        break;    
                    case "Create Admin Account":
                        
                        this.model.createAdminAccount();
                        break;    
                    case "Create Account (official admin)":
                        String createdAdminUsername = this.view.getCreateUsernameTextField().getText();
                        String createdAdminPassword = this.view.getCreatePasswordTextField().getText();
                        String confirmedAdminPassword = this.view.getConfirmPasswordTextField().getText();
                        String createdAdminEmployeeID = this.view.getCreateEmployeeIDTextField().getText();
                        this.model.checkCreatedAdminAccount(createdAdminUsername, createdAdminPassword, confirmedAdminPassword, createdAdminEmployeeID);
                        break;
                    case "Create Account (official customer)":
                        String createdCustomerUsername = this.view.getCreateUsernameTextField().getText();
                        String createdCustomerPassword = this.view.getCreatePasswordTextField().getText();
                        String confirmedCustomerPassword = this.view.getConfirmPasswordTextField().getText();
                        this.model.checkCreatedCustomerAccount(createdCustomerUsername, createdCustomerPassword, confirmedCustomerPassword);
                        break;
                    default:
                        break;
                }
        
    }
    
}
