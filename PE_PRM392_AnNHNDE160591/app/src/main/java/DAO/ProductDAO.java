package DAO;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

import models.Product;

@Dao
public interface ProductDAO {
    @Insert
    void insert(Product product);
    // Update data
    @Update
    void update(Product product);
    // Delete data
    @Delete
    void delete(Product product);
    // Get all data
    @Query("SELECT * FROM products")
    List<Product> getAllProducts();
    // Get data by id
    @Query("SELECT * FROM products WHERE id = :id")
    Product getProductById(int id);
}
