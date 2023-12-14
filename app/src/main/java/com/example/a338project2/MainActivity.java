package com.example.a338project2;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a338project2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Button createAccount;
    Button logIn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createAccount = binding.createAccountButton;
        createAccount.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CreateAccountActivity.class);
            startActivity(intent);
        });

        logIn = binding.loginButton;
        logIn.setOnClickListener(view -> {
            EditText usernameEditText = findViewById(R.id.username);
            EditText passwordEditText = findViewById(R.id.password);
            final String username = usernameEditText.getText().toString();
            final String password = passwordEditText.getText().toString();

            SharedPreferences.Editor editor = getSharedPreferences("MyApp", MODE_PRIVATE).edit();
            editor.putString("Username", username);
            editor.apply();


            new Thread(() -> {

                User user = UserDatabase.getDatabase(MainActivity.this).userDao().getUser(username, password);
                if (user != null) {
                    // Login successful
                    boolean isAdmin = user.getUsername().equals("admin2") && user.getPassword().equals("admin2");
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("isAdmin", isAdmin);
                    startActivity(intent);
                } else {
                    // otherwise, Login failed
                    runOnUiThread(() -> {
                        Toast toast = Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT);
                        toast.show();
                    });
                }
            }).start();
        });
    }
}