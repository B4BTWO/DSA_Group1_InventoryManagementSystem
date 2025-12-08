/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventorymanager;

/**
 *
 * @author Jamil Abinal
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

interface LoginListener {
    void onLoginSuccess(String username);
}

public class LoginPage extends JFrame implements ActionListener {

    private final Map<String, String> userDatabase = new HashMap<>();
    private LoginListener loginListener;
    private JPanel panel;
    private JLabel userLabel, passwordLabel;
    private JLabel messageLabel; 
    private JTextField usernameText;
    private JPasswordField passwordText; 
    private JButton loginButton, registerButton;

    public LoginPage(LoginListener listener) {
        this.loginListener = listener;
        
        userDatabase.put("admin", "password123");
        userDatabase.put("guest", "pass");

        setTitle("Inventory Manager Login");
        setSize(400, 250); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null); 

        userLabel = new JLabel("Username:");
        usernameText = new JTextField(); 
        passwordLabel = new JLabel("Password:");
        passwordText = new JPasswordField(); 
        messageLabel = new JLabel("Please Login or Register.");

        loginButton = new JButton("Login");
        loginButton.addActionListener(this); 
        
        registerButton = new JButton("Register");
        registerButton.addActionListener(this); 

        userLabel.setBounds(30, 30, 100, 25);
        usernameText.setBounds(140, 30, 220, 25);
        
        passwordLabel.setBounds(30, 65, 100, 25);
        passwordText.setBounds(140, 65, 220, 25);
        
        loginButton.setBounds(30, 110, 150, 30);
        registerButton.setBounds(210, 110, 150, 30);
        
        messageLabel.setBounds(30, 160, 330, 25);

        panel.add(userLabel);
        panel.add(usernameText);
        
        panel.add(passwordLabel);
        panel.add(passwordText);
        
        panel.add(loginButton);
        panel.add(registerButton);
        
        panel.add(messageLabel); 

        add(panel);
        setVisible(true); 
    }

    private boolean authenticate(String username, String password) {
        return userDatabase.containsKey(username) && userDatabase.get(username).equals(password);
    }

    private boolean register(String username, String password) {
        if (userDatabase.containsKey(username) || username.trim().isEmpty() || password.trim().isEmpty()) {
            return false;
        }
        userDatabase.put(username, password);
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String enteredUsername = usernameText.getText();
        String enteredPassword = new String(passwordText.getPassword());
        
        usernameText.setText("");
        passwordText.setText("");
        
        if (e.getSource() == loginButton) {
            handleLogin(enteredUsername, enteredPassword);
        } else if (e.getSource() == registerButton) {
            handleRegistration(enteredUsername, enteredPassword);
        }
    }

    private void handleLogin(String username, String password) {
        if (authenticate(username, password)) {
            messageLabel.setText("Login Successful! Opening Inventory...");
            
            if (loginListener != null) {
                this.dispose(); 
                loginListener.onLoginSuccess(username);
            }
        } else {
            messageLabel.setText("Login Failed. Invalid credentials.");
        }
    }

    private void handleRegistration(String username, String password) {
        if (register(username, password)) {
            messageLabel.setText("Registration Successful! Please log in.");
        } else {
            messageLabel.setText("Registration Failed. Username taken or fields empty.");
        }
    }
}