package com.example.a338project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity {

    Button createUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        createUserButton = findViewById(R.id.create_account_page_button);
        createUserButton.setOnClickListener(view -> {
            EditText usernameEditText = findViewById(R.id.username);
            EditText passwordEditText = findViewById(R.id.password);
            EditText retypePasswordEditText = findViewById(R.id.retype_password);

            // Extract text input from EditText fields

            final String username = usernameEditText.getText().toString();
            final String password = passwordEditText.getText().toString();
            final String retypedPassword = retypePasswordEditText.getText().toString();


            new Thread(() -> {
                // Get an instance of the UserDatabase
                UserDatabase db = UserDatabase.getDatabase(getApplicationContext());
                UserDAO userDao = db.userDao();
                // Check if the entered username already exists in the database
                final User existingUser = userDao.getUserByUsername(username);

                // if username alreadfy exist, showcase message, same with password.
                if (existingUser != null) {
                    runOnUiThread(() -> Toast.makeText(CreateAccountActivity.this, "Username already in use", Toast.LENGTH_SHORT).show());
                } else if (!password.equals(retypedPassword)) {
                    runOnUiThread(() -> Toast.makeText(CreateAccountActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show());
                } else {
                    User newUser = new User(username, password);
                    userDao.insert(newUser);
                    Runnable startMainActivity = () -> {
                        Toast.makeText(CreateAccountActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                        startActivity(intent);
                    };
                    runOnUiThread(startMainActivity);
                }
            }).start();
        });
    }
}
