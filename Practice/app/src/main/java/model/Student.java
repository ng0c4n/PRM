package model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "students") // Set Table name
public class Student {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String email;
    public Student(@NonNull String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    // Getters and Setters
    @NonNull
    public String getId() {
        return id;
    }
    public void setId(@NonNull String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
