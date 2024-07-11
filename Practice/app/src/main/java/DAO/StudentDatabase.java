package DAO;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import model.Student;

@Database(entities = {Student.class}, version = 1)
public abstract class StudentDatabase extends RoomDatabase {
    private static StudentDatabase instance;
    public abstract StudentDAO studentDao();
    public static synchronized StudentDatabase getInstance(Context
                                                                  context) {
        if (instance == null) {
            instance =
                    Room.databaseBuilder(context.getApplicationContext(),
                                    StudentDatabase.class, "StudentDB")
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return instance;
    }
}
