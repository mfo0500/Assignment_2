/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshopazgroceries;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author krist
 */
public class AZGroceriesOnlineShopView extends JFrame {
    
    // componants needed for sign in page 
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel employeeIDLabel;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JTextField employeeIDTextField;
    private JButton signInButton;
    private JLabel loginStatus;
    
    public AZGroceriesOnlineShopView()
    {
        setUsernameLabel(new JLabel("Enter Username:", SwingConstants.RIGHT));
        setPasswordLabel(new JLabel("Enter Password:", SwingConstants.RIGHT));
        setEmployeeIDLabel(new JLabel("Enter Employee ID:", SwingConstants.RIGHT));
        setUsernameTextField(new JTextField());
        setPasswordTextField(new JTextField());
        setEmployeeIDTextField(new JTextField());
        
        JButton logout = new JButton("Log out");

        //How to organize the components in JPannel: 
        //1. set layout
        //2. add components by giving the position
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(3, 2));
        northPanel.add(getUsernameLabel());
        northPanel.add(getUsernameTextField());
        northPanel.add(getPasswordLabel());
        northPanel.add(getPasswordTextField());
        northPanel.add(getEmployeeIDLabel());
        northPanel.add(getEmployeeIDTextField());

        add(northPanel, BorderLayout.NORTH);

        JPanel centrePanel = new JPanel();
        setSignInButton(new JButton("sign in"));
        centrePanel.add(getSignInButton());
        
        add(centrePanel, BorderLayout.CENTER);

        //add button to append text into the text area
        JPanel southPanel = new JPanel();
        setLoginStatus(new JLabel("", SwingConstants.CENTER));

        southPanel.add(getLoginStatus());

        //ananymous listener -> more commonly used
        getSignInButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                setLoginStatus(new JLabel("The button works", SwingConstants.CENTER));
            }
        });

        add(southPanel, BorderLayout.SOUTH);
        pack();
    }
    
      public static void main(String[] args) {
          AZGroceriesOnlineShopView shopView = new AZGroceriesOnlineShopView();

        JFrame frame = shopView;
        frame.setTitle("Text Component Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * @return the usernameTextField
     */
    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    /**
     * @param usernameTextField the usernameTextField to set
     */
    public void setUsernameTextField(JTextField usernameTextField) {
        this.usernameTextField = usernameTextField;
    }

    /**
     * @return the passwordTextField
     */
    public JTextField getPasswordTextField() {
        return passwordTextField;
    }

    /**
     * @param passwordTextField the passwordTextField to set
     */
    public void setPasswordTextField(JTextField passwordTextField) {
        this.passwordTextField = passwordTextField;
    }

    /**
     * @return the employeeIDTextField
     */
    public JTextField getEmployeeIDTextField() {
        return employeeIDTextField;
    }

    /**
     * @param employeeIDTextField the employeeIDTextField to set
     */
    public void setEmployeeIDTextField(JTextField employeeIDTextField) {
        this.employeeIDTextField = employeeIDTextField;
    }

    /**
     * @return the signInButton
     */
    public JButton getSignInButton() {
        return signInButton;
    }

    /**
     * @param signInButton the signInButton to set
     */
    public void setSignInButton(JButton signInButton) {
        this.signInButton = signInButton;
    }

    /**
     * @return the loginStatus
     */
    public JLabel getLoginStatus() {
        return loginStatus;
    }

    /**
     * @param loginStatus the loginStatus to set
     */
    public void setLoginStatus(JLabel loginStatus) {
        this.loginStatus = loginStatus;
    }

    /**
     * @return the usernameLabel
     */
    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    /**
     * @param usernameLabel the usernameLabel to set
     */
    public void setUsernameLabel(JLabel usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    /**
     * @return the passwordLabel
     */
    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    /**
     * @param passwordLabel the passwordLabel to set
     */
    public void setPasswordLabel(JLabel passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    /**
     * @return the employeeIDLabel
     */
    public JLabel getEmployeeIDLabel() {
        return employeeIDLabel;
    }

    /**
     * @param employeeIDLabel the employeeIDLabel to set
     */
    public void setEmployeeIDLabel(JLabel employeeIDLabel) {
        this.employeeIDLabel = employeeIDLabel;
    }
    
    
    
}
