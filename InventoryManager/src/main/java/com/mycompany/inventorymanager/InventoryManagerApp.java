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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class InventoryManagerApp extends JFrame implements ActionListener, LoginListener {

    private JTable overview;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnDelete, btnUpdate, btnStock, btnOrder, btnSupplier, btnProduct, btnSearch, btnLowStockReport;
    private JLabel lbProdPage, lblId, lblName, lblCat, lblQty, lblPrice, lbSupp;
    private JTextField suppField, idField, nameField, catField, qtyField, priceField, searchField;
    
    private JPanel mainContentPanel;
    private CardLayout cardLayout;
   
    private InventoryManager inventoryManager;

    public InventoryManagerApp() {
        setTitle("Inventory Management System");
        setSize(100, 100); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        inventoryManager = new InventoryManager();
        
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);
        add(mainContentPanel);

        initInventoryPanel();
    }

    @Override
    public void onLoginSuccess(String username) {
        JOptionPane.showMessageDialog(this, "Welcome, " + username + "!", "Login Success", JOptionPane.INFORMATION_MESSAGE);
        showInventoryPanel();
    }

    private void initInventoryPanel() {
        JPanel inventoryPanel = new JPanel(null);

        lbProdPage = new JLabel("PRODUCTS");
        lbProdPage.setBounds(200, 60, 300, 30);
        lbProdPage.setFont(new Font("Arial", Font.BOLD, 25));
        inventoryPanel.add(lbProdPage);

        searchField = new JTextField("");
        searchField.setBounds(200, 20, 370, 30);
        inventoryPanel.add(searchField);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(580, 20, 100, 30);
        btnSearch.addActionListener(this);
        inventoryPanel.add(btnSearch);

        btnLowStockReport = new JButton("Low Stock Report");
        btnLowStockReport.setBounds(700, 20, 240, 30);
        btnLowStockReport.addActionListener(this);
        inventoryPanel.add(btnLowStockReport);

        String[] columnNames = {"ID", "Name", "Type", "Quantity", "Supplier ID", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        overview = new JTable(tableModel);

        overview.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && overview.getSelectedRow() != -1) {
                    populateFieldsFromTable();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(overview);
        scrollPane.setBounds(200, 100, 480, 430);
        inventoryPanel.add(scrollPane);

        btnProduct = new JButton("Products");
        btnProduct.setBounds(50, 100, 120, 35);
        btnProduct.addActionListener(this);
        inventoryPanel.add(btnProduct);

        btnOrder = new JButton("Orders");
        btnOrder.setBounds(50, 200, 120, 35);
        btnOrder.addActionListener(this);
        inventoryPanel.add(btnOrder);

        btnStock = new JButton("Stocks");
        btnStock.setBounds(50, 300, 120, 35);
        btnStock.addActionListener(this);
        inventoryPanel.add(btnStock);

        btnSupplier = new JButton("Suppliers");
        btnSupplier.setBounds(50, 400, 120, 35);
        btnSupplier.addActionListener(this);
        inventoryPanel.add(btnSupplier);

        JLabel lbProdDetail = new JLabel("PRODUCT DETAILS");
        lbProdDetail.setBounds(700, 60, 240, 30);
        inventoryPanel.add(lbProdDetail);

        lbSupp = new JLabel("Supplier ID:");
        lbSupp.setBounds(700, 145, 80, 30);
        inventoryPanel.add(lbSupp);

        lblId = new JLabel("ID:");
        lblId.setBounds(700, 190, 80, 30);
        inventoryPanel.add(lblId);

        lblName = new JLabel("Name:");
        lblName.setBounds(700, 235, 80, 30);
        inventoryPanel.add(lblName);

        lblCat = new JLabel("Type:");
        lblCat.setBounds(700, 280, 80, 30);
        inventoryPanel.add(lblCat);

        lblQty = new JLabel("Quantity:");
        lblQty.setBounds(700, 325, 80, 30);
        inventoryPanel.add(lblQty);

        lblPrice = new JLabel("Price:");
        lblPrice.setBounds(700, 370, 80, 30);
        inventoryPanel.add(lblPrice);

        suppField = new JTextField();
        suppField.setBounds(780, 145, 160, 30);
        inventoryPanel.add(suppField);

        idField = new JTextField();
        idField.setBounds(780, 190, 160, 30);
        inventoryPanel.add(idField);

        nameField = new JTextField();
        nameField.setBounds(780, 235, 160, 30);
        inventoryPanel.add(nameField);

        catField = new JTextField();
        catField.setBounds(780, 280, 160, 30);
        inventoryPanel.add(catField);

        qtyField = new JTextField();
        qtyField.setBounds(780, 325, 160, 30);
        inventoryPanel.add(qtyField);

        priceField = new JTextField();
        priceField.setBounds(780, 370, 160, 30);
        inventoryPanel.add(priceField);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(870, 450, 70, 30);
        btnDelete.addActionListener(this);
        inventoryPanel.add(btnDelete);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(780, 450, 75, 30);
        btnUpdate.addActionListener(this);
        inventoryPanel.add(btnUpdate);

        btnAdd = new JButton("Add");
        btnAdd.setBounds(700, 450, 70, 30);
        btnAdd.addActionListener(this);
        inventoryPanel.add(btnAdd);

        mainContentPanel.add(inventoryPanel, "Inventory");
    }
    
    private void showInventoryPanel() {
        this.setVisible(true); 
        cardLayout.show(mainContentPanel, "Inventory");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Product product : inventoryManager.getAllProducts()) {
            tableModel.addRow(product.toObjectArray());
        }
    }

    private void populateFieldsFromTable() {
        int selectedRow = overview.getSelectedRow();
        if (selectedRow != -1) {
            idField.setText(tableModel.getValueAt(selectedRow, 0).toString());
            nameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
            catField.setText(tableModel.getValueAt(selectedRow, 2).toString());
            qtyField.setText(tableModel.getValueAt(selectedRow, 3).toString());
            suppField.setText(tableModel.getValueAt(selectedRow, 4).toString());
            priceField.setText(tableModel.getValueAt(selectedRow, 5).toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
             try {
                 String id = idField.getText().trim();
                 String supplierId = suppField.getText().trim();
                 String name = nameField.getText().trim();
                 String type = catField.getText().trim();
                 String qtyText = qtyField.getText();
                 String priceText = priceField.getText();

                 if (id.isEmpty() || supplierId.isEmpty() || name.isEmpty() || type.isEmpty() || qtyText.isEmpty() || priceText.isEmpty()) {
                     JOptionPane.showMessageDialog(this, "Input all Product details.");
                     return;
                 }

                 int qty = Integer.parseInt(qtyField.getText());
                 double price = Double.parseDouble(priceField.getText());

                 inventoryManager.addProduct(id, supplierId, name, type, qty, price);
                 refreshTable();

                 JOptionPane.showMessageDialog(this, "Product added successfully!");
                 clearFields();

             } catch (NumberFormatException ex) {
                 JOptionPane.showMessageDialog(this, "Invalid Input! Quantity and Price must be numbers.");
             }
        }

        else if (e.getSource() == btnUpdate) {
             int selectedRow = overview.getSelectedRow();

             if (selectedRow == -1) {
                 JOptionPane.showMessageDialog(null, "Please select a Product to update.");
                 return;
             }

             try {
                 String id = idField.getText().trim();
                 String supplierId = suppField.getText().trim();
                 String name = nameField.getText().trim();
                 String type = catField.getText().trim();

                 if (id.isEmpty() || supplierId.isEmpty() || name.isEmpty() || type.isEmpty() || qtyField.getText().isEmpty() || priceField.getText().isEmpty()) {
                     JOptionPane.showMessageDialog(this, "All fields must be filled!");
                     return;
                 }

                 int qty = Integer.parseInt(qtyField.getText().trim());
                 double price = Double.parseDouble(priceField.getText().trim());

                 if(inventoryManager.updateProduct(selectedRow, id, supplierId, name, type, qty, price)) {
                     refreshTable();
                     JOptionPane.showMessageDialog(this, "Product updated successfully!");
                     clearFields();
                 } else {
                     JOptionPane.showMessageDialog(this, "Error updating product in manager.", "Error", JOptionPane.ERROR_MESSAGE);
                 }

             } catch (NumberFormatException ex) {
                 JOptionPane.showMessageDialog(this, "Quantity and Price must be valid numbers.");
             }
        }

        else if (e.getSource() == btnDelete) {
             int selectedRow = overview.getSelectedRow();

             if (selectedRow == -1) {
                 JOptionPane.showMessageDialog(null, "Please select a Product to delete.");
                 return;
             }

             if (inventoryManager.deleteProduct(selectedRow)) {
                 refreshTable();
                 JOptionPane.showMessageDialog(null, "Product deleted!");
                 clearFields();
             } else {
                 JOptionPane.showMessageDialog(this, "Error deleting product from manager.", "Error", JOptionPane.ERROR_MESSAGE);
             }
        }

        else if (e.getSource() == btnStock){
             JOptionPane.showMessageDialog(this, "Not yet implemented! (Stocks)");
        }
        else if (e.getSource() == btnOrder){
             JOptionPane.showMessageDialog(this, "Not yet implemented! (Orders)");
        }
        else if (e.getSource() == btnSupplier){
             JOptionPane.showMessageDialog(this, "Not yet implemented! (Suppliers)");
        }
        else if (e.getSource() == btnLowStockReport ){
             JOptionPane.showMessageDialog(this, "Not yet implemented! (Low Stock Report)");
        }
        else if (e.getSource() == btnSearch){
             JOptionPane.showMessageDialog(this, "Not yet implemented! (Search)");
        }
        
    }

    private void clearFields() {
        idField.setText("");
        suppField.setText("");
        nameField.setText("");
        catField.setText("");
        qtyField.setText("");
        priceField.setText("");
    }
    
}