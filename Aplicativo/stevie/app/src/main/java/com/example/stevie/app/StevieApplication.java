package com.example.stevie.app;

import android.app.Application;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

public class StevieApplication extends Application {

    private BluetoothSocket mmSocket = null;
    private BluetoothDevice mmDevice = null;

    private static final String TAG = "StevieApplication";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void makeSocket(BluetoothDevice device, UUID uuid) {
        BluetoothSocket tmp = null;
        mmDevice = device;

        try {
            tmp = device.createRfcommSocketToServiceRecord(uuid);
            Log.i(TAG, "makeSocket: Socket criado");
        } catch (IOException e) {
            Log.i(TAG, "makeSocket: Não foi possível criar o socket");
        }
        
        mmSocket = tmp;
    }

    public BluetoothSocket getSocket() {return mmSocket;}
    public BluetoothDevice getDevice() {return mmDevice;}

}
