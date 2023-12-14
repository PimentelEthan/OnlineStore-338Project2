package com.example.a338project2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
//similar to other daos
@Dao
public interface ShoppingCartDAO {

    @Insert
    void insert(ShoppingCart item);

//    void insert(ShoppingCart item);

    @Update
    void update(ShoppingCart item);


    @Query("SELECT * FROM shopping_cart")
    List<ShoppingCart> getAllCartItems();

}