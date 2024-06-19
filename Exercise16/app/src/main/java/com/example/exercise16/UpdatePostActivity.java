package com.example.exercise16;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePostActivity extends AppCompatActivity {
    private EditText etTitle, etBody;
    private Button btnSave;
    private ApiService apiService;
    private int postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_post);

        etTitle = findViewById(R.id.etTitle);
        etBody = findViewById(R.id.etBody);
        btnSave = findViewById(R.id.btnSave);
        apiService = ApiClient.getApiService();

        Intent intent = getIntent();
        postId = intent.getIntExtra("postId", -1);
        String title = intent.getStringExtra("title");
        String body = intent.getStringExtra("body");

        etTitle.setText(title);
        etBody.setText(body);

        btnSave.setOnClickListener(v -> {
            String updatedTitle = etTitle.getText().toString();
            String updatedBody = etBody.getText().toString();
            if (updatedTitle.isEmpty() || updatedBody.isEmpty()) {
                Toast.makeText(UpdatePostActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            updatePost(new Post(postId, updatedTitle, updatedBody));
        });
    }

    private void updatePost(Post post) {
        apiService.updatePost(post.getId(), post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(UpdatePostActivity.this, "Post updated successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(UpdatePostActivity.this, "Failed to update post", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
