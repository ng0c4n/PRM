package com.example.pe_prm392_annhnde160591;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import API.ApiClient;
import API.ApiService;
import Adapter.PhotoAdapter;
import models.Photo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PhotoAdapter photoAdapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apiactivity);

        recyclerView = findViewById(R.id.recyclerView);
        apiService = ApiClient.getApiService();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchUsers();
    }

    private void fetchUsers() {
        apiService.getPhoto().enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    photoAdapter = new PhotoAdapter(APIActivity.this, response.body());
                    recyclerView.setAdapter(photoAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Toast.makeText(APIActivity.this, "Failed to fetch users", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
