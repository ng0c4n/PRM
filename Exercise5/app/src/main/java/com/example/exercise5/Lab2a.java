package com.example.exercise5;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Lab2a extends AppCompatActivity {

    public Button myButton1;
    public Button myButton2;
    public Button myButton3;
    public Button myButton4;
    public EditText number1;
    public EditText number2;
    public TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab2a);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lab2a), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        myButton1 = findViewById(R.id.button_add);
        myButton2 = findViewById(R.id.button_subtract);
        myButton3 = findViewById(R.id.button_multiply);
        myButton4 = findViewById(R.id.button_divide);
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        result = findViewById(R.id.result);
    }
    public void onAddClick(View view){
        double num1 = Double.parseDouble(number1.getText().toString());
        double num2 = Double.parseDouble(number2.getText().toString());
        double res = num1 + num2;
        result.setText(String.valueOf(res));
    }

    public void onSubtractClick(View view){
        double num1 = Double.parseDouble(number1.getText().toString());
        double num2 = Double.parseDouble(number2.getText().toString());
        double res = num1 - num2;
        result.setText(String.valueOf(res));
    }
    public void onMultiplyClick(View view){
        double num1 = Double.parseDouble(number1.getText().toString());
        double num2 = Double.parseDouble(number2.getText().toString());
        double res = num1 * num2;
        result.setText(String.valueOf(res));
    }
    public void onDivideClick(View view){
        double num1 = Double.parseDouble(number1.getText().toString());
        double num2 = Double.parseDouble(number2.getText().toString());
        double res = num1 / num2;
        result.setText(String.valueOf(res));
    }
}