package com.example.pe_prm392_annhnde160591;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import android.widget.Toast;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;

import Adapter.ProductAdapter;
import DAO.ProductDatabase;
import models.Product;

public class SQLiteActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button buttonSave;
    private EditText inputId, inputName, inputPrice, inputCategory;
    private ProductAdapter productAdapter;
    private List<Product> productList = new ArrayList<>();
    private ProductDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recycler_view);
        inputId = findViewById(R.id.input_id);
        inputName = findViewById(R.id.input_name);
        inputPrice = findViewById(R.id.input_price);
        inputCategory = findViewById(R.id.input_category);
        buttonSave = findViewById(R.id.button_add);

        // Database
        db = ProductDatabase.getInstance(this);

        // Set the LayoutManager to RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set the Adapter to RecyclerView
        productAdapter = new ProductAdapter(productList);
        recyclerView.setAdapter(productAdapter);

        // Add a listener to RecyclerView Adapter
        productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {


            @Override
            public void onDeleteClick(int position) {
                final Product product = productList.get(position);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        db.productDao().delete(product);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                productList.remove(product);
                                productAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }).start();
            }
        });

        // Add a listener to Save button
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = inputId.getText().toString();
                final String name = inputName.getText().toString();
                final double price;
                try {
                    price = Double.parseDouble(inputPrice.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(SQLiteActivity.this, "Invalid price format", Toast.LENGTH_SHORT).show();
                    return;
                }
                final String category = inputCategory.getText().toString();
                final Product product = new Product(id, name, price, category);
                if (!product.isPriceValid()) {
                    Toast.makeText(SQLiteActivity.this, "Price must be non-negative", Toast.LENGTH_SHORT).show();
                    return;
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        db.productDao().insert(product);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                loadProducts();
                                clearInputs();
                            }
                        });
                    }
                }).start();
            }
        });

        loadProducts();
    }

    // Refresh data
    @Override
    protected void onResume() {
        super.onResume();
        loadProducts();
    }

    private void loadProducts() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                productList.clear();
                productList.addAll(db.productDao().getAllProducts());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        productAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    private void clearInputs() {
        inputId.setText("");
        inputName.setText("");
        inputPrice.setText("");
        inputCategory.setText("");
    }
}