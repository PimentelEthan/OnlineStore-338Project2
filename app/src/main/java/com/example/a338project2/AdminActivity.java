package com.example.a338project2;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

// Admin Activity - should let the admin have at least 1 more activity than regular user.

public class AdminActivity extends AppCompatActivity {
    Button removeUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_landing_page);

        removeUser = findViewById(R.id.delete_user_button);


        removeUser.setOnClickListener(view -> {
            Intent intent = new Intent(AdminActivity.this, RemoveUser.class);
            startActivity(intent);
        });
    }
}