package DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

import model.Student;

@Dao
public interface StudentDAO {
    // Insert data
    @Insert
    void insert(Student student);
    // Update data
    @Update
    void update(Student student);
    // Delete data
    @Delete
    void delete(Student student);
    // Get all data
    @Query("SELECT * FROM students")
    List<Student> getAllStudents();
    // Get data by id
    @Query("SELECT * FROM students WHERE id = :id")
    Student getStudentById(int id);
}
