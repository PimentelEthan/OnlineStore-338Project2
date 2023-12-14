package com.example.a338project2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//  Define the entity for the shopping cart table in the Room database need 3 tables

@Entity(tableName = "products")
public class Product {
    @PrimaryKey(autoGenerate = true)

    private int id;
    private String productName;
    private double productPrice;

    private int productQuantity;

    private String productDetails;

    // To put image next, to the profuct name in the store
//    @ColumnInfo(name = "image_resource")
//    private String imageResource; // or String imagePath if you're storing the path



    public Product(String productName, double productPrice, int productQuantity, String productDetails) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productDetails = productDetails;
//        this.imageResource = imageResource;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public String getProductDetails() {
        return productDetails;
    }

    @Override
    public String toString() {
        return productName + "     $" + productPrice;
    }
}