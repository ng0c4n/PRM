package DAO;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import models.Product;
@Database(entities = {Product.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {
    private static ProductDatabase instance;
    public abstract ProductDAO productDao();
    public static synchronized ProductDatabase getInstance(Context
                                                                   context) {
        if (instance == null) {
            instance =
                    Room.databaseBuilder(context.getApplicationContext(),
                                    ProductDatabase.class, "ProductDB")
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return instance;
    }
}