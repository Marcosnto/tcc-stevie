package com.example.stevie;

import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import android.os.Handler;
import android.os.Looper;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.TimerTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class DataThread extends Thread {
    private BluetoothSocket mmSocket;
    private InputStream mmInStream;
    OutputStream tmpOut = null;
    String ultimoComando;


    public InputStream getMmInStream() {
        return mmInStream;
    }

    public OutputStream mmOutStream;
    private byte[] mmBuffer; // mmBuffer store for the stream
    private Handler handler;

    private static final String TAG = "DataThread";

    public DataThread(BluetoothSocket socket) {
        mmSocket = socket;
        InputStream tmpIn = null;
//        OutputStream tmpOut = null;

        // Get the input and output streams; using temp objects because
        // member streams are final.
        try {
            tmpIn = socket.getInputStream();
        } catch (IOException e) {
            Log.e(TAG, "Error occurred when creating input stream", e);
        }
        try {
            tmpOut = socket.getOutputStream();
        } catch (IOException e) {
            Log.e(TAG, "Error occurred when creating output stream", e);
        }
        mmInStream = tmpIn;
        mmOutStream = tmpOut;
    }

    public void run() {
        // Continua ouvindo até ocorrer uma excessão
        try {
            InputStream inputStream = mmSocket.getInputStream();
            while (true) {
                try {
                    Log.i(TAG, "run: escuta ativada");
                    ultimoComando = receberDados(inputStream);
                    Log.i(TAG, "run: recebido no run " + ultimoComando);
                } catch (Exception e) {
                    Log.d(TAG, "Input stream was disconnected", e);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Call this from the main activity to send data to the remote device.
    public void enviarDados(Object object) {
        try {
            OutputStream outputStream = mmSocket.getOutputStream();
            new ObjectOutputStream(outputStream).writeObject(object);
//            outputStream.close();
            System.out.println("Enviei!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        //TROCAR PRA OBJETO
    public String receberDados(InputStream inputStream){
        String st = "";
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            st = (String) objectInputStream.readObject();
            Log.i(TAG, "run: Recebi" + st);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return st;
    }


    // Call this method from the main activity to shut down the connection.
    public void cancel() {
        try {
            mmSocket.close();
        } catch (IOException e) {
            Log.e(TAG, "Não foi possível encerrar a conexão", e);
        }
    }

}

