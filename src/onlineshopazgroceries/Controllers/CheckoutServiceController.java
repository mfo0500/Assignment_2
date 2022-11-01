/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries.Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import onlineshopazgroceries.Models.CheckoutServiceModel;
import onlineshopazgroceries.Views.CheckoutServiceView;

/**
 *
 * @author krist
 */
public class CheckoutServiceController implements ActionListener {

    public CheckoutServiceView view;
    public CheckoutServiceModel model;

    public CheckoutServiceController(CheckoutServiceView view, CheckoutServiceModel model) {
        this.view = view;
        this.model = model;
        this.view.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Return Back To Main Menu":

                this.model.requestToReturnToMainMenu();

                break;
            default:
                break;
        }
    }
}
