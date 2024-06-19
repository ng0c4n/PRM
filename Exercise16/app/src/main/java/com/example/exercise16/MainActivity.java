package com.example.exercise16;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private FloatingActionButton fab;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);
        apiService = ApiClient.getApiService();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddPostActivity.class);
            startActivity(intent);
        });

        fetchPosts();
    }

    private void fetchPosts() {
        apiService.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    postAdapter = new PostAdapter(MainActivity.this, response.body(), new PostAdapter.OnItemClickListener() {
                        @Override
                        public void onEditClick(Post post) {
                            Intent intent = new Intent(MainActivity.this, UpdatePostActivity.class);
                            intent.putExtra("postId", post.getId());
                            intent.putExtra("title", post.getTitle());
                            intent.putExtra("body", post.getBody());
                            startActivity(intent);
                        }

                        @Override
                        public void onDeleteClick(Post post) {
                            deletePost(post.getId());
                        }
                    });
                    recyclerView.setAdapter(postAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to fetch posts", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deletePost(int postId) {
        apiService.deletePost(postId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Post deleted successfully", Toast.LENGTH_SHORT).show();
                    fetchPosts(); // Refresh the list
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to delete post", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
