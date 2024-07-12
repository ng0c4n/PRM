package Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.example.pe_prm392_annhnde160591.R;
import models.Product;
public class ProductAdapter extends
        RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;
    private OnItemClickListener listener;
    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public static class ProductViewHolder extends
            RecyclerView.ViewHolder {
        public TextView textViewId;
        public TextView textViewName;
        public TextView textViewPrice;
        public TextView textViewCategory;
        public ImageButton editButton;
        public ImageButton deleteButton;
        public ProductViewHolder(View itemView, final
        OnItemClickListener listener) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.text_view_id);
            textViewName = itemView.findViewById(R.id.text_view_product_name);
            textViewPrice =itemView.findViewById(R.id.text_view_product_price);
            textViewCategory =itemView.findViewById(R.id.text_view_product_category);
            deleteButton = itemView.findViewById(R.id.button_delete);
            deleteButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }
    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int
            viewType) {
        View itemView =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,
                        parent, false);
        return new ProductViewHolder(itemView, listener);
    }
    @Override
    public void onBindViewHolder(ProductViewHolder holder, int
            position) {
        Product currentProduct = productList.get(position);
        holder.textViewId.setText(currentProduct.getId());
        holder.textViewName.setText(currentProduct.getName());
        holder.textViewPrice.setText(Double.toString(currentProduct.getPrice()));
        holder.textViewCategory.setText(currentProduct.getCategory());
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }
}