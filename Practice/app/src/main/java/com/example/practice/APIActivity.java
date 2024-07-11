package com.example.practice;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import API.ApiClient;
import API.ApiService;
import Adapter.UserAdapter;
import model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
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
        apiService.getUser().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    userAdapter = new UserAdapter(APIActivity.this, response.body());
                    recyclerView.setAdapter(userAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(APIActivity.this, "Failed to fetch users", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
