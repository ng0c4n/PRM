package com.example.exercise10a11;

import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.widget.Toast;

public class Exercise10 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_CONTACTS) ==
                PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission already granted",
                    Toast.LENGTH_SHORT).show();
        } else {
            requestPermissionLauncher.launch(android.Manifest.permission.READ_CONTACTS);
        }
    }
    // Request the permission
    private final ActivityResultLauncher<String>
            requestPermissionLauncher =
            registerForActivityResult(new
                    ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {

                    Toast.makeText(Exercise10.this, "Permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Exercise10.this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            });
}