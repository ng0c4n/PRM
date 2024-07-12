package com.example.pe_prm392_annhnde160591;
import android.content.IntentFilter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.net.wifi.WifiManager;
import android.util.Log;
import Receiver.WifiReceiver;

public class MainActivity extends AppCompatActivity {
    private WifiReceiver wifiReceiver = new WifiReceiver();
    private static final String TAG = "Receiver";
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    @Override
    protected void onStart() {
        super.onStart();
        try {
            // Register the receiver
            IntentFilter filter1 = new
                    IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
            registerReceiver(wifiReceiver, filter1);
            Log.d(TAG, "Wifi - Receiver registered successfully");
        } catch (Exception e) {
            Log.e(TAG, "Wifi - Failed to register receiver", e);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (wifiReceiver != null) {
            unregisterReceiver(wifiReceiver);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sql_activity) {
            Intent intent = new Intent(MainActivity.this, SQLiteActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.api_activity) {
            Intent intent = new Intent(MainActivity.this, APIActivity.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.exit) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
