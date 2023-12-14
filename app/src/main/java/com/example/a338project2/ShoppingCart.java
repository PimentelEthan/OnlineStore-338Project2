package com.example.a338project2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

// Define the entity for the shopping cart table in the Room database need 3 tables
@Entity(tableName = "shopping_cart")
public class ShoppingCart {

    @PrimaryKey(autoGenerate = true)
    private int id;
    //    @ColumnInfo(name = "product_name")
    private String name;
    private int quantity;
    private double price;


// getter nsetters
    public ShoppingCart(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "  " + "   $" + price;
    }






}