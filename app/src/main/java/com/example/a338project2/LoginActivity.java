package com.example.a338project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button adminButton;
    Button signOutButton;
    Button productsButton;


    Button shoppingCartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        adminButton = findViewById(R.id.admin);
        signOutButton = findViewById(R.id.sign_out);
        productsButton = findViewById(R.id.products);
        shoppingCartButton = findViewById(R.id.shopping_cart);

        String username = getIntent().getStringExtra("username");
        boolean isAdmin = getIntent().getBooleanExtra("isAdmin", true);

        TextView welcome_message = findViewById(R.id.welcome_message);
        welcome_message.setText("Welcome, " + username + "!");



        if(!isAdmin) {
            adminButton.setVisibility(View.GONE);
        }
        signOutButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

//
        productsButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        adminButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
            startActivity(intent);
        });

        shoppingCartButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ShoppingCartActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });
    }
}