/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author krist
 */
public class CheckoutServiceView  extends JFrame implements Observer {

    /**
     * @return the purchaseInformationPanel
     */
    public JPanel getPurchaseInformationPanel() {
        return purchaseInformationPanel;
    }

    /**
     * @param purchaseInformationPanel the purchaseInformationPanel to set
     */
    public void setPurchaseInformationPanel(JPanel purchaseInformationPanel) {
        this.purchaseInformationPanel = purchaseInformationPanel;
    }
private JLabel storeTitle = new JLabel("WELCOME TO THE AZCROCERIES ONLINE STORE", SwingConstants.CENTER);
    private JLabel pickupLabel;
    private JLabel deliveryLabel;
    private JCheckBox pickupCheckBox;
    private JCheckBox deliveryCheckBox;
    private JLabel cardnumberLabel;
    private JLabel cardholderNameLabel;
    private JLabel cardExpiryMonthLabel;
    private JLabel cardExpiryYearLabel;
    private JTextField cardNumberTextField;
    private JTextField cardholderNameTextField;
    private JButton purchaseButton;
    private JComboBox cardExpiryMonthCombobox;
    private JComboBox cardExpiryYearCombobox;
    private JLabel cardCVCLabel;
    private  JTextField cardCVCTextField;
    
    private JPanel titlePanel = new JPanel();
    private JPanel purchaseInformationPanel;

    
    CheckoutServiceView()
    {
        this.getContentPane().removeAll();
        
        this.setSize(600, 250);
        this.setLocationRelativeTo(null);
        
        setTitle("AZGroceries Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getStoreTitle().setFont(new Font("Times New Roman", Font.BOLD, 22));
        getTitlePanel().add(getStoreTitle());
        add(getTitlePanel(), BorderLayout.NORTH);
        
        setPurchaseInformationPanel(new JPanel());
        getPurchaseInformationPanel().setLayout(new GridLayout(6, 2));
        
        setPickupLabel(new JLabel("Pickup ", SwingConstants.CENTER));
        setDeliveryLabel(new JLabel("Delivery ", SwingConstants.CENTER));
        
        setPickupCheckBox(new JCheckBox());
        setDeliveryCheckBox(new JCheckBox());
        getPickupCheckBox().setSelected(true);
        getDeliveryCheckBox().setSelected(false);
        
        setCardnumberLabel(new JLabel("Enter Card Number (16 Digits): ",SwingConstants.CENTER ));
        setCardholderNameLabel(new JLabel("Enter Cardholder name: ",SwingConstants.CENTER ));
        
        setCardNumberTextField(new JTextField());
        setCardholderNameTextField(new JTextField());
        
        setCardExpiryMonthLabel(new JLabel("Select Card Expiry Month ", SwingConstants.CENTER));
        setCardExpiryMonthCombobox(new JComboBox());
        getCardExpiryMonthCombobox().addItem("January");
        getCardExpiryMonthCombobox().addItem("February");
        getCardExpiryMonthCombobox().addItem("March");
        getCardExpiryMonthCombobox().addItem("April");
        getCardExpiryMonthCombobox().addItem("May");
        getCardExpiryMonthCombobox().addItem("June");
        getCardExpiryMonthCombobox().addItem("July");
        getCardExpiryMonthCombobox().addItem("August");
        getCardExpiryMonthCombobox().addItem("September");
        getCardExpiryMonthCombobox().addItem("October");
        getCardExpiryMonthCombobox().addItem("Novemeber");
        getCardExpiryMonthCombobox().addItem("December");
        
        setCardExpiryYearLabel(new JLabel("Select Card Expiry Year ", SwingConstants.CENTER));
        setCardExpiryYearCombobox(new JComboBox());
        getCardExpiryYearCombobox().addItem("2022");
        getCardExpiryYearCombobox().addItem("2023");
        getCardExpiryYearCombobox().addItem("2024");
        getCardExpiryYearCombobox().addItem("2025");
        getCardExpiryYearCombobox().addItem("2026");
        getCardExpiryYearCombobox().addItem("2027");
        
        setCardCVCLabel(new JLabel("Enter Card CVC: ", SwingConstants.CENTER));
        setCardCVCTextField(new JTextField());
        
        setPurchaseButton(new JButton() );
        
        getPurchaseInformationPanel().add(getPickupLabel());
        getPurchaseInformationPanel().add(getPickupCheckBox());
        
        getPurchaseInformationPanel().add(getDeliveryLabel());
        getPurchaseInformationPanel().add(getDeliveryCheckBox());
        getPurchaseInformationPanel().add(getCardnumberLabel());
        getPurchaseInformationPanel().add(getCardNumberTextField());
        getPurchaseInformationPanel().add(getCardExpiryMonthLabel());
        getPurchaseInformationPanel().add(getCardExpiryMonthCombobox());
        getPurchaseInformationPanel().add(getCardExpiryYearLabel());
        getPurchaseInformationPanel().add(getCardExpiryYearCombobox());
        getPurchaseInformationPanel().add(getCardCVCLabel());
        getPurchaseInformationPanel().add(getCardCVCTextField());
        
        JPanel purchasePanel = new JPanel();
        setPurchaseButton(new JButton("Purchase"));
        purchasePanel.add(getPurchaseButton());
        
        add(getPurchaseInformationPanel(), BorderLayout.CENTER);
        add(purchasePanel,BorderLayout.SOUTH );
        
        
        

        setVisible(true);
        this.revalidate();
        this.repaint();
    }
    
    public static void main(String[] args) {
        CheckoutServiceView view = new CheckoutServiceView();
    }
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * @return the titlePanel
     */
    public JPanel getTitlePanel() {
        return titlePanel;
    }

    /**
     * @param titlePanel the titlePanel to set
     */
    public void setTitlePanel(JPanel titlePanel) {
        this.titlePanel = titlePanel;
    }

    /**
     * @return the storeTitle
     */
    public JLabel getStoreTitle() {
        return storeTitle;
    }

    /**
     * @param storeTitle the storeTitle to set
     */
    public void setStoreTitle(JLabel storeTitle) {
        this.storeTitle = storeTitle;
    }

    /**
     * @return the pickupLabel
     */
    public JLabel getPickupLabel() {
        return pickupLabel;
    }

    /**
     * @param pickupLabel the pickupLabel to set
     */
    public void setPickupLabel(JLabel pickupLabel) {
        this.pickupLabel = pickupLabel;
    }

    /**
     * @return the deliveryLabel
     */
    public JLabel getDeliveryLabel() {
        return deliveryLabel;
    }

    /**
     * @param deliveryLabel the deliveryLabel to set
     */
    public void setDeliveryLabel(JLabel deliveryLabel) {
        this.deliveryLabel = deliveryLabel;
    }

    /**
     * @return the prickupCheckBox
     */
    public JCheckBox getPickupCheckBox() {
        return pickupCheckBox;
    }

    /**
     * @param pickupCheckBox the prickupCheckBox to set
     */
    public void setPickupCheckBox(JCheckBox pickupCheckBox) {
        this.pickupCheckBox = pickupCheckBox;
    }

    /**
     * @return the deliveryCheckBox
     */
    public JCheckBox getDeliveryCheckBox() {
        return deliveryCheckBox;
    }

    /**
     * @param deliveryCheckBox the deliveryCheckBox to set
     */
    public void setDeliveryCheckBox(JCheckBox deliveryCheckBox) {
        this.deliveryCheckBox = deliveryCheckBox;
    }

    /**
     * @return the cardnumberLabel
     */
    public JLabel getCardnumberLabel() {
        return cardnumberLabel;
    }

    /**
     * @param cardnumberLabel the cardnumberLabel to set
     */
    public void setCardnumberLabel(JLabel cardnumberLabel) {
        this.cardnumberLabel = cardnumberLabel;
    }

    /**
     * @return the cardholderNameLabel
     */
    public JLabel getCardholderNameLabel() {
        return cardholderNameLabel;
    }

    /**
     * @param cardholderNameLabel the cardholderNameLabel to set
     */
    public void setCardholderNameLabel(JLabel cardholderNameLabel) {
        this.cardholderNameLabel = cardholderNameLabel;
    }

    /**
     * @return the cardNumberTextField
     */
    public JTextField getCardNumberTextField() {
        return cardNumberTextField;
    }

    /**
     * @param cardNumberTextField the cardNumberTextField to set
     */
    public void setCardNumberTextField(JTextField cardNumberTextField) {
        this.cardNumberTextField = cardNumberTextField;
    }

    /**
     * @return the cardholderNameTextField
     */
    public JTextField getCardholderNameTextField() {
        return cardholderNameTextField;
    }

    /**
     * @param cardholderNameTextField the cardholderNameTextField to set
     */
    public void setCardholderNameTextField(JTextField cardholderNameTextField) {
        this.cardholderNameTextField = cardholderNameTextField;
    }

    /**
     * @return the purchaseButton
     */
    public JButton getPurchaseButton() {
        return purchaseButton;
    }

    /**
     * @param purchaseButton the purchaseButton to set
     */
    public void setPurchaseButton(JButton purchaseButton) {
        this.purchaseButton = purchaseButton;
    }

    /**
     * @return the cardExpiryMonthCombobox
     */
    public JComboBox getCardExpiryMonthCombobox() {
        return cardExpiryMonthCombobox;
    }

    /**
     * @param cardExpiryMonthCombobox the cardExpiryMonthCombobox to set
     */
    public void setCardExpiryMonthCombobox(JComboBox cardExpiryMonthCombobox) {
        this.cardExpiryMonthCombobox = cardExpiryMonthCombobox;
    }

    /**
     * @return the cardYearExpiryCombobox
     */
    public JComboBox getCardExpiryYearCombobox() {
        return cardExpiryYearCombobox;
    }

    /**
     * @param cardExpiryYearCombobox the cardYearExpiryCombobox to set
     */
    public void setCardExpiryYearCombobox(JComboBox cardExpiryYearCombobox) {
        this.cardExpiryYearCombobox = cardExpiryYearCombobox;
    }

    /**
     * @return the cardExpiryMonthLabel
     */
    public JLabel getCardExpiryMonthLabel() {
        return cardExpiryMonthLabel;
    }

    /**
     * @param cardExpiryMonthLabel the cardExpiryMonthLabel to set
     */
    public void setCardExpiryMonthLabel(JLabel cardExpiryMonthLabel) {
        this.cardExpiryMonthLabel = cardExpiryMonthLabel;
    }

    /**
     * @return the cardExpiryYearLabel
     */
    public JLabel getCardExpiryYearLabel() {
        return cardExpiryYearLabel;
    }

    /**
     * @param cardExpiryYearLabel the cardExpiryYearLabel to set
     */
    public void setCardExpiryYearLabel(JLabel cardExpiryYearLabel) {
        this.cardExpiryYearLabel = cardExpiryYearLabel;
    }

    /**
     * @return the cardCVCLabel
     */
    public JLabel getCardCVCLabel() {
        return cardCVCLabel;
    }

    /**
     * @param cardCVCLabel the cardCVCLabel to set
     */
    public void setCardCVCLabel(JLabel cardCVCLabel) {
        this.cardCVCLabel = cardCVCLabel;
    }

    /**
     * @return the cardCVCTextField
     */
    public JTextField getCardCVCTextField() {
        return cardCVCTextField;
    }

    /**
     * @param cardCVCTextField the cardCVCTextField to set
     */
    public void setCardCVCTextField(JTextField cardCVCTextField) {
        this.cardCVCTextField = cardCVCTextField;
    }
    
    
    
}
