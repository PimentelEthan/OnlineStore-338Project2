package com.example.a338project2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    private List<Product> productList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products);

        // Get the return button from the layout
        Button returnButton = findViewById(R.id.return_button);



        String username = getIntent().getStringExtra("username");

        ListView listView = findViewById(R.id.products_in_inventory);

        returnButton.setOnClickListener(view -> finish());
//https://stackoverflow.com/questions/34065656/how-i-can-change-the-style-of-built-in-list-item-layout-in-android-studio
        new Thread(() -> {
            productList = new ArrayList<>();
            productList = UserDatabase.getDatabase(ProductActivity.this).ProductDao().getAllProducts();
            // Create an ArrayAdapter to display the product list in the ListView
            ArrayAdapter<Product> adapter = new ArrayAdapter<>(ProductActivity.this, android.R.layout.simple_list_item_1, productList);
            listView.setAdapter(adapter);
        }).start();

        // Detail in depth when the user or admin clicks on a product *add to their cart*
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Product productSelected = productList.get(i);
            int productSelectedId = productSelected.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(ProductActivity.this);
            builder.setMessage(productSelected.toString())
                    .setPositiveButton("Add to cart", (dialogInterface, i1) -> new Thread(() -> {
                        UserDatabase db = UserDatabase.getDatabase(getApplicationContext());
                        ShoppingCartDAO shoppingCartDAO = db.shoppingCartDAO();
                        Product product = db.ProductDao().getProductById(productSelectedId);
//
                        ShoppingCart shoppingCartItem = new ShoppingCart(productSelected.getProductName(), productSelected.getProductQuantity(), productSelected.getProductPrice());
                        shoppingCartDAO.insert(shoppingCartItem);
                        runOnUiThread(() -> Toast.makeText(ProductActivity.this, productSelected.getProductName() + " added to shopping cart.", Toast.LENGTH_SHORT).show());
                    }).start())
                    // Use a negative button in case user wants to go back
                    .setNegativeButton("Return", null);
            //call
            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }
}