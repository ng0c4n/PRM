package com.example.practice;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;

import Adapter.StudentAdapter;
import DAO.StudentDatabase;
import model.Student;

public class SQLiteActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button buttonSave;
    private EditText inputId, inputName, inputEmail;
    private StudentAdapter studentAdapter;
    private List<Student> studentList = new ArrayList<>();
    private StudentDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recycler_view);
        inputId = findViewById(R.id.input_id);
        inputName = findViewById(R.id.input_name);
        inputEmail = findViewById(R.id.input_email);
        buttonSave = findViewById(R.id.button_add);

        // Database
        db = StudentDatabase.getInstance(this);

        // Set the LayoutManager to RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set the Adapter to RecyclerView
        studentAdapter = new StudentAdapter(studentList);
        recyclerView.setAdapter(studentAdapter);

        // Add a listener to RecyclerView Adapter
        studentAdapter.setOnItemClickListener(new StudentAdapter.OnItemClickListener() {


            @Override
            public void onDeleteClick(int position) {
                final Student student = studentList.get(position);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        db.studentDao().delete(student);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                studentList.remove(student);
                                studentAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }).start();
            }
        });

        // Add a listener to Save button
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = inputId.getText().toString();
                final String name = inputName.getText().toString();
                final String email = inputEmail.getText().toString();
                final Student student = new Student(id, name, email);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        db.studentDao().insert(student);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                loadStudents();
                                clearInputs();
                            }
                        });
                    }
                }).start();
            }
        });

        loadStudents();
    }

    // Refresh data
    @Override
    protected void onResume() {
        super.onResume();
        loadStudents();
    }

    private void loadStudents() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                studentList.clear();
                studentList.addAll(db.studentDao().getAllStudents());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        studentAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    private void clearInputs() {
        inputId.setText("");
        inputName.setText("");
        inputEmail.setText("");
    }
}