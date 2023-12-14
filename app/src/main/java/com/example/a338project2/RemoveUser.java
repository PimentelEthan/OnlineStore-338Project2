package com.example.a338project2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RemoveUser extends AppCompatActivity {

    private List<User> userList;
    private ArrayAdapter<User> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_remove_user);

        ListView listView = findViewById(R.id.users_in_database);

        new Thread(() -> {
            userList = new ArrayList<>();
            userList = UserDatabase.getDatabase(RemoveUser.this).userDao().getAllUsers();
            adapter = new ArrayAdapter<>(RemoveUser.this, android.R.layout.simple_list_item_1, userList);
            listView.setAdapter(adapter);
        }).start();

        //alert message / call similar to product one
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            User userSelected = userList.get(i);
            AlertDialog.Builder builder = new AlertDialog.Builder(RemoveUser.this);
            builder.setMessage("By removing " + userSelected.getUsername() + " from the database, they will no longer be able to login.")
                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            new Thread(() -> {
                                UserDatabase db = UserDatabase.getDatabase(getApplicationContext());
                                UserDAO userDao = db.userDao();
                                userDao.delete(userSelected);
                                userList.remove(userSelected);

                                runOnUiThread(() -> {
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(RemoveUser.this, userSelected.getUsername() + " no longer in database.", Toast.LENGTH_SHORT).show();
                                });
                            }).start();
                        }
                    })
                    .setNegativeButton("Go Back", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        });

    }
}