package com.example.a338project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    List<ShoppingCart> cartItems;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_shopping_cart);

        SharedPreferences prefs = getSharedPreferences("MyApp", MODE_PRIVATE);
        userId = prefs.getString("UserId", "defaultUserId");

        ListView listView = findViewById(R.id.cart_items);


        Button returnButton = findViewById(R.id.return_button);

        String username = getIntent().getStringExtra("username");

        new Thread(() -> {
            UserDatabase db = UserDatabase.getDatabase(getApplicationContext());
            UserDAO userDao = db.userDao();
            User currentUser = userDao.getUserByUsername(username);
            int currentUserId = currentUser.getId();
//                cartItems = db.shoppingCartDAO().getAllCartItems(currentUserId);
            cartItems = db.shoppingCartDAO().getAllCartItems();

            runOnUiThread(() -> {
                ArrayAdapter<ShoppingCart> adapter = new ArrayAdapter<>(ShoppingCartActivity.this, android.R.layout.simple_list_item_1, cartItems);
                listView.setAdapter(adapter);
            });
        }).start();

        //return button to go back page
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


}