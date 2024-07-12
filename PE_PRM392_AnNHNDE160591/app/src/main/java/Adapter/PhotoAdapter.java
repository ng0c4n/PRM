package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pe_prm392_annhnde160591.R;

import java.util.List;

import models.Photo;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    private Context context;
    private List<Photo> photoList;

    public PhotoAdapter(Context context, List<Photo> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.photo_item, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = photoList.get(position);
        holder.tvAlbumId.setText("AlbumId: " + photo.getAlbumId());
        holder.tvId.setText("Id: " + photo.getId());
        holder.tvTitle.setText("Title: " + photo.getTitle());

        // Load image using Glide
        Glide.with(context)
                .load(photo.getThumbnailUrl())
                .into(holder.imgThumbnail);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        TextView tvAlbumId, tvId, tvTitle;
        ImageView imgThumbnail;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAlbumId = itemView.findViewById(R.id.tvAlbumId);
            tvId = itemView.findViewById(R.id.tvId);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            imgThumbnail = itemView.findViewById(R.id.imgThumbnail);
        }
    }
}
