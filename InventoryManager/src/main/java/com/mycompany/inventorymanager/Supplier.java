/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventorymanager;

/**
 *
 * @author Jamil Abinal
 */
public class Supplier {
    private String id;
    private String name;
    private String contactInfo;
    
    public Supplier(String id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }
    
    public String getId(){return id;}
    public String getName(){return name;}
    public String getContactInfo(){return contactInfo;}
    
    public void setName(String name) {this.name = name;}
    public void setContactInfo(String contactInfo) {this.contactInfo = contactInfo;}
}
