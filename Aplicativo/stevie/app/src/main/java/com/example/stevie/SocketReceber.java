package com.example.stevie;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketReceber implements Runnable {

    Context context;
    TextToSpeech ttobj;


    public SocketReceber(Context context) {
        this.context = context;
        this.falar();
    }

    @Override
    public void run() {
        String mensagem;
        try {
            ServerSocket serverSocket = new ServerSocket(7500);
            while (true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                mensagem = (String) inputStream.readObject();

                String[] comandos = mensagem.split(":");

                switch (comandos[0]) {
                    case "localizacaoAtual":
                        ttobj.speak("Você está em " + comandos[1], TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case "direcao":
                        ttobj.speak(comandos[1], TextToSpeech.QUEUE_FLUSH, null);
                        break;
                    case "ip":
                        break;
                }socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void falar() {
        ttobj = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                ttobj.setLanguage(ttobj.getLanguage());
            }
        });
    }
}
