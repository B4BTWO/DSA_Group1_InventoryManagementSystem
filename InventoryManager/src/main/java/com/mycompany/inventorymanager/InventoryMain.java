/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.inventorymanager;

/**
 *
 * @author Jamil Abinal
 */

import javax.swing.SwingUtilities;

public class InventoryMain {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InventoryManagerApp app = new InventoryManagerApp();

            LoginPage loginPage = new LoginPage(app);
            loginPage.setVisible(true); 
        });
    }
}
