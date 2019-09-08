package servidor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketEnviar implements Runnable {

    private Object message;
    private String ip;
    private int port;

    public SocketEnviar(Object message, String ip, int port) {
        this.message = message;
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        send();
    }

    protected void send() {
        try {
            Socket socketOut = new Socket(ip, port);
            ObjectOutputStream outputStream = new ObjectOutputStream(socketOut.getOutputStream());
            outputStream.writeObject(message);
            socketOut.close();
        } catch (IOException e) {
            System.out.println("SOCKET_ENVIAR"+ e.toString());
        }
    }
}