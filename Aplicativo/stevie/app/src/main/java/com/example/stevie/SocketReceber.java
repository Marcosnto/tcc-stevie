package com.example.stevie;

import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class SocketReceber {

    protected Object receive(Socket socketIn) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socketIn.getInputStream());
            return inputStream.readObject();
        } catch (IOException e) {
            Log.d("SOCKET_RECEBER", e.toString());
        } catch (ClassNotFoundException e) {
            Log.d("SOCKET_RECEBER", e.toString());
        }
        return null;
    }
}
