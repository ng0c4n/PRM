package com.example.exercise20;

import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.bluetooth.BluetoothAdapter;
import android.os.BatteryManager;
import android.util.Log;
public class MainActivity extends AppCompatActivity {
    private WifiReceiver wifiReceiver = new WifiReceiver();
    private BlueToothReceiver bluetoothReceiver = new BlueToothReceiver();
    private BatteryReceiver batteryReceiver = new BatteryReceiver();
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            IntentFilter filter1 = new
                    IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
            registerReceiver(wifiReceiver, filter1);
            Log.d(TAG, "Wifi - Receiver registered successfully");
        } catch (Exception e) {
            Log.e(TAG, "Wifi - Failed to register receiver", e);
        }
        try {
            IntentFilter filter2 = new
                    IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(bluetoothReceiver, filter2);
            Log.d(TAG, "Bluetooth - Receiver registered successfully");
        } catch (Exception e) {
            Log.e(TAG, "Bluetooth - Failed to register receiver",
                    e);
        }
        try {
            IntentFilter filter3 = new
                    IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            registerReceiver(batteryReceiver, filter3);
            Log.d(TAG, "Battery - Receiver registered successfully");
        } catch (Exception e) {
            Log.e(TAG, "Battery - Failed to register receiver",
                    e);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (wifiReceiver != null) {
            unregisterReceiver(wifiReceiver);
        }
        if (bluetoothReceiver != null) {
            unregisterReceiver(bluetoothReceiver);
        }
        if (batteryReceiver != null) {
            unregisterReceiver(batteryReceiver);
        }
    }
}