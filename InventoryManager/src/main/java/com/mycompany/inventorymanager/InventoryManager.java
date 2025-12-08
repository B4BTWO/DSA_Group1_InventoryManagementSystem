/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventorymanager;

/**
 *
 * @author Jamil Abinal
 */

import java.util.LinkedList;
import java.util.List;

public class InventoryManager {

    private List<Product> productList; 
    public InventoryManager() {
        this.productList = new LinkedList<>(); 
    }

    public List<Product> getAllProducts() {
        return productList;
    }
    public void addProduct(String id, String supplierId, String name, String type, int quantity, double price) {
        Product newProduct = new Product(id, name, type, quantity, supplierId, price);
        productList.add(newProduct); 
    }

    public boolean updateProduct(int rowIndex, String newId, String newSupplierId, String newName, String newType, int newQuantity, double newPrice) {
        if (rowIndex >= 0 && rowIndex < productList.size()) {
            
            Product product = productList.get(rowIndex);
            
            product.setId(newId); 
            product.setName(newName);
            product.setSupplierId(newSupplierId); 
            product.setType(newType);
            product.setQuantity(newQuantity);
            product.setPrice(newPrice);
            
            return true;
        }
        return false;
    }

    public boolean deleteProduct(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < productList.size()) {
            productList.remove(rowIndex); 
            return true;
        }
        return false;
    }
    
    /* BST SUSUNOD*/
}
