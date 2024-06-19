package com.example.exercise16;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private Context context;
    private List<Post> postList;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onEditClick(Post post);
        void onDeleteClick(Post post);
    }

    public PostAdapter(Context context, List<Post> postList, OnItemClickListener listener) {
        this.context = context;
        this.postList = postList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_item, parent,
                        false);
        return new PostViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder,
                                 int position) {
        Post post = postList.get(position);
        holder.tvTitle.setText(post.getTitle());
        holder.tvBody.setText(post.getBody());

        holder.btnEdit.setOnClickListener(v -> listener.onEditClick(post));
        holder.btnDelete.setOnClickListener(v -> listener.onDeleteClick(post));
    }
    @Override
    public int getItemCount() {
        return postList.size();
    }
    public static class PostViewHolder extends
            RecyclerView.ViewHolder {
        TextView tvTitle, tvBody;
        ImageButton btnEdit, btnDelete;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
