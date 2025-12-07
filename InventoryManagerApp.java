/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventorymanager;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Jamil Abinal
 */
public class InventoryManagerApp extends JFrame implements ActionListener{
    
    private JTable overview;
    private DefaultTableModel tableModel;
    private JButton btnAdd, btnDelete, btnUpdate, btnStock, btnOrder, btnSupplier, btnProduct ,btnSearch, btnLowStockReport;;
    private JLabel lbProdPage, lblId, lblName, lblCat, lblQty, lblPrice, lbSupp;
    private JTextField suppField, idField, nameField, catField, qtyField, priceField, searchField;
    
    public InventoryManagerApp() {
        
        
        
        setTitle("Inventory Management System");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        lbProdPage = new JLabel("PRODUCTS");
        lbProdPage.setBounds(200, 60, 300, 30);
        lbProdPage.setFont(new Font("Arial", Font.BOLD, 25));
        add(lbProdPage);
        
        searchField = new JTextField("");
        searchField.setBounds(200, 20, 370, 30);
        add(searchField);
        
        btnSearch = new JButton("Search");
        btnSearch.setBounds(580, 20, 100, 30);
        btnSearch.addActionListener(this);
        add(btnSearch);
        
        btnLowStockReport = new JButton("Low Stock Report");
        btnLowStockReport.setBounds(700, 20, 240, 30);
        btnLowStockReport.addActionListener(this);
        add(btnLowStockReport);
        
        String[] columnNames = {"ID", "Name", "Type", "Quantity", "Supplier ID", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        overview = new JTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(overview);
        scrollPane.setBounds(200, 100, 480, 430);
        add(scrollPane);
        
        btnProduct = new JButton("Products");
        btnProduct.setBounds(50, 100, 120, 35);
        btnProduct.addActionListener(this); 
        add(btnProduct);

        btnOrder = new JButton("Orders");
        btnOrder.setBounds(50, 200, 120, 35);
        btnOrder.addActionListener(this);
        add(btnOrder);

        btnStock = new JButton("Stocks");
        btnStock.setBounds(50, 300, 120, 35);
        btnStock.addActionListener(this);
        add(btnStock);

        btnSupplier = new JButton("Suppliers");
        btnSupplier.setBounds(50, 400, 120, 35);
        btnSupplier.addActionListener(this);
        add(btnSupplier);
        
        JLabel lbProdDetail = new JLabel("PRODUCT DETAILS");
        lbProdDetail.setBounds(700, 60, 240, 30);
        add(lbProdDetail);

        lbSupp = new JLabel("Supplier ID:");
        lbSupp.setBounds(700, 145, 80, 30);
        add(lbSupp);

        lblId = new JLabel("ID:");
        lblId.setBounds(700, 190, 80, 30);
        add(lblId);

        lblName = new JLabel("Name:");
        lblName.setBounds(700, 235, 80, 30);
        add(lblName);

        lblCat = new JLabel("Type:");
        lblCat.setBounds(700, 280, 80, 30);
        add(lblCat);

        lblQty = new JLabel("Quantity:");
        lblQty.setBounds(700, 325, 80, 30);
        add(lblQty);

        lblPrice = new JLabel("Price:");
        lblPrice.setBounds(700, 370, 80, 30);
        add(lblPrice);

        suppField = new JTextField();
        suppField.setBounds(780, 145, 160, 30);
        add(suppField);

        idField = new JTextField();
        idField.setBounds(780, 190, 160, 30);
        add(idField);

        nameField = new JTextField();
        nameField.setBounds(780, 235, 160, 30);
        add(nameField);

        catField = new JTextField();
        catField.setBounds(780, 280, 160, 30);
        add(catField);

        qtyField = new JTextField();
        qtyField.setBounds(780, 325, 160, 30);
        add(qtyField);

        priceField = new JTextField();
        priceField.setBounds(780, 370, 160, 30);
        add(priceField);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(870, 450, 70, 30);
        btnDelete.addActionListener(this);
        add(btnDelete);
        
        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(780, 450, 75, 30);
        btnUpdate.addActionListener(this);
        add(btnUpdate);
        
        btnAdd = new JButton("Add");
        btnAdd.setBounds(700, 450, 70, 30);
        btnAdd.addActionListener(this);
        add(btnAdd);
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
                
                tableModel.addRow(new Object[]{id, supplierId, name, type, qty, price});

                JOptionPane.showMessageDialog(this, "Product added successfully!");
                clearFields();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Input. Quantity and Price must be numbers.");
            }
        } 
        
        else if (e.getSource() == btnUpdate) {
            int selectedRow = overview.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please enter a Product to update.");
                return;
            }

            try {
                String id = idField.getText().trim();
                String supplierId = suppField.getText().trim();
                String name = nameField.getText().trim();
                String type = catField.getText().trim();
                int qty = Integer.parseInt(qtyField.getText().trim());
                double price = Double.parseDouble(priceField.getText().trim());

            if (id.isEmpty() || supplierId.isEmpty() || name.isEmpty() || type.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!");
            return;
        }
            
            tableModel.setValueAt(id, selectedRow, 0);
            tableModel.setValueAt(name, selectedRow, 1);
            tableModel.setValueAt(type, selectedRow, 2);
            tableModel.setValueAt(qty, selectedRow, 3);
            tableModel.setValueAt(supplierId, selectedRow, 4);
            tableModel.setValueAt(price, selectedRow, 5);

            JOptionPane.showMessageDialog(this, "Product updated successfully!");
            clearFields();
            
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantity and Price must be valid numbers.");
            }
        } 
        
        else if (e.getSource() == btnDelete) {
            int selectedRow = overview.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please enter a Product to delete.");
                return;
            }

            {
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(null, "Product deleted!");
                clearFields();
            }
        }
        
        else if (e.getSource() == btnStock){
            JOptionPane.showMessageDialog(this, "Not yet implemented!");
                }
        else if (e.getSource() == btnOrder){
            JOptionPane.showMessageDialog(this, "Not yet implemented!");
        }
        else if (e.getSource() == btnSupplier){
            JOptionPane.showMessageDialog(this, "Not yet implemented!");
        }
        else if (e.getSource() == btnLowStockReport ){
            JOptionPane.showMessageDialog(this, "Not yet implemented!");
        }
        else if (e.getSource() == btnSearch){
            JOptionPane.showMessageDialog(this, "Not yet implemented!");
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