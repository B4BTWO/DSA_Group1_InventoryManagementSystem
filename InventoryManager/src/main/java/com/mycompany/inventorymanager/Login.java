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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Login extends JPanel implements ActionListener {

    private final JFrame parentFrame;
    private final CardLayout cardLayout;
    private final JPanel mainContentPanel;
    
    private JTextField loginUsernameField;
    private JPasswordField loginPasswordField;
    private JButton loginButton, registerButton, switchToRegisterButton, switchToLoginButton;
    private JLabel loginTitleLabel;

    private static List<User> users = new ArrayList<>();

    public Login(JFrame parentFrame, CardLayout cardLayout, JPanel mainContentPanel) {
        this.parentFrame = parentFrame;
        this.cardLayout = cardLayout;
        this.mainContentPanel = mainContentPanel;

        if (users.isEmpty()) {
            users.add(new User("admin", "pass"));
        }
        
        initLoginPanel();
        showLoginPanel();
    }

    private void initLoginPanel() {
        this.setLayout(null);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        loginTitleLabel = new JLabel("User Login", SwingConstants.CENTER);
        loginTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        loginTitleLabel.setBounds(100, 30, 200, 30);
        this.add(loginTitleLabel);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 80, 80, 25);
        loginUsernameField = new JTextField(15);
        loginUsernameField.setBounds(140, 80, 210, 25);
        this.add(lblUsername);
        this.add(loginUsernameField);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 120, 80, 25);
        loginPasswordField = new JPasswordField(15);
        loginPasswordField.setBounds(140, 120, 210, 25);

        this.add(lblPassword);
        this.add(loginPasswordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 170, 90, 30);
        loginButton.addActionListener(this);
        this.add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(210, 170, 100, 30);
        registerButton.addActionListener(this);
        this.add(registerButton);

        switchToRegisterButton = new JButton("New User? Register");
        switchToRegisterButton.setBounds(100, 220, 210, 25);
        switchToRegisterButton.addActionListener(this);
        this.add(switchToRegisterButton);

        switchToLoginButton = new JButton("Back to Login");
        switchToLoginButton.setBounds(100, 220, 210, 25);
        switchToLoginButton.addActionListener(this);
        this.add(switchToLoginButton);
    }

    public void showLoginPanel() {
        loginTitleLabel.setText("User Login");
        loginButton.setVisible(true);
        registerButton.setVisible(false);
        switchToRegisterButton.setVisible(true);
        switchToLoginButton.setVisible(false);
        clearFields();
    }

    private void showRegisterPanel() {
        loginTitleLabel.setText("User Registration");
        loginButton.setVisible(false);
        registerButton.setVisible(true);
        switchToRegisterButton.setVisible(false);
        switchToLoginButton.setVisible(true);
        clearFields();
    }

    private void clearFields() {
        loginUsernameField.setText("");
        loginPasswordField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            handleLogin();
        } else if (e.getSource() == registerButton) {
            handleRegistration();
        } else if (e.getSource() == switchToRegisterButton) {
            showRegisterPanel();
        } else if (e.getSource() == switchToLoginButton) {
            showLoginPanel();
        }
    }

    private void handleLogin() {
        String username = loginUsernameField.getText().trim();
        String password = new String(loginPasswordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean authenticated = false;
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                authenticated = true;
                break;
            }
        }

        if (authenticated) {
            JOptionPane.showMessageDialog(this, "Login successful! Welcome to the Inventory Manager.");

            parentFrame.setSize(1000, 650); 
            parentFrame.setLocationRelativeTo(null);
            cardLayout.show(mainContentPanel, "Inventory");

        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleRegistration() {
        String username = loginUsernameField.getText().trim();
        String password = new String(loginPasswordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty() || username.length() < 3 || password.length() < 3) {
            JOptionPane.showMessageDialog(this, "Username and password must be at least 3 characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                JOptionPane.showMessageDialog(this, "Username already exists. Please input again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        users.add(new User(username, password));
        JOptionPane.showMessageDialog(this, "Registration successful! You can now log in.");
        showLoginPanel();
    }
}
