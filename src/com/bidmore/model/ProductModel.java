/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidmore.model;

/**
 *
 * @author 23056546 Nirdesh Subedi
 */
public class ProductModel {
    private int productId;
    private String name;
    private String type;
    private double basePrice;
    private String description;
    private String auctioneerName;
    private String status; // UPCOMING, ACTIVE, CLOSED, SOLD
    
    
    // Constructor

    public ProductModel(int productId, String name, String type, double basePrice, String description, String auctioneerName, String status) {
        this.productId = productId;
        this.name = name;
        this.type = type;
        this.basePrice = basePrice;
        this.description = description;
        this.auctioneerName = auctioneerName;
        this.status = status;
       
    }
    
    
    
    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuctioneerName() {
        return auctioneerName;
    }

    public void setAuctioneerName(String auctioneerName) {
        this.auctioneerName = auctioneerName;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

    
}

