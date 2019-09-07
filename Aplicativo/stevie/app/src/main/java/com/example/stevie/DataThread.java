package com.example.stevie;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataThread extends Thread {
    private BluetoothSocket mmSocket;
    private InputStream is;
    private OutputStream os;

    private static final String TAG = "DataThread";

    public DataThread(BluetoothSocket socket) {
        mmSocket = socket;
        try {
            is = mmSocket.getInputStream();
            os = mmSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Log.i(TAG, "run: Ouvindo o Servidor");
        while (true) {
            try {
                enviarDados("Hi, baby");
                String msg = receberDados();
                System.out.println(msg);
                is.close();
                os.close();
            } catch (Exception e) {
                Log.d(TAG, "Input foi desconectado", e);
                break;
            }
        }
    }

    public String receberDados(){
        byte[] buffer = new byte[1024];
        try {
            is.read(buffer);
        } catch (Exception e) {
            Log.d(TAG, "Input foi desconectado", e);
        } return msgConvertida(buffer);
    }

    public void enviarDados(String mensagem){
        try {
            os.write(mensagem.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String msgConvertida(byte[] buffer) {
        int cont = 0;
        String mensagem = "";
        while (buffer[cont] != 0) {
            mensagem += Character.toString((char) buffer[cont++]);
        }
        return mensagem;
    }
}
