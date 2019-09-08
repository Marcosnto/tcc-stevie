package com.example.stevie;

import android.util.Log;

import java.io.IOException;
import java.net.Socket;


class SocketConexao implements Runnable {

    private Socket socketIn;
    private String mensagem;

    public SocketConexao(Socket socket, String mensagem) {
        this.socketIn = socket;
        this.mensagem = mensagem;
    }

    @Override
    public void run() {

        try {
            String command = (String) new SocketReceber().receive(socketIn);

            switch (command) {
                case "localizacaoAtual":

                    break;
                case "rota":
                    new Thread(new SocketEnviar("", socketIn.getInetAddress().toString(), 7500)).start();
                    break;
            }
            socketIn.close();
        } catch (IOException e) {
            Log.d("SERVER_RUN", e.toString());
        }
    }
}