/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventorymanager;

/**
 *
 * @author Jamil Abinal
 */
public class Product {
    private String id;
    private String name;
    private String type;
    private int quantity;
    private String supplierId;
    private double price;
    
    public Product (String id, String name, String type, int quantity, String supplierId, double price){
        this.id = id;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.supplierId = supplierId;
        this.price = price;
    }
    
    public String getId(){return id;}
    public String getName(){return name;}
    public String getType(){return type;}
    public int getQuantity(){return quantity;}
    public String getSupplierId(){return supplierId;}
    public double getPrice(){return price;}
    
    public void setName(String name) {this.name = name;}
    public void setType(String type) {this.type = type;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
    public void setSupplierId(String supplierId) {this.supplierId = supplierId;}
    public void setPrice(int price) {this.price = price;}
    
}
