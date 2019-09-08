package com.example.stevie;

import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;

class Servidor implements Runnable {

    private ServerSocket serverSocket;

    {
        try {
            serverSocket = new ServerSocket(7000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {

            while (true) {
                new Thread(new SocketConexao(serverSocket.accept(), "servidor")).start();
            }

        } catch (IOException e) {
            Log.d("SERVER_RUN", e.toString());
        }
    }
}