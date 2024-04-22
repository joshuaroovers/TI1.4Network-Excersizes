package Sockets0_example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Client implements Runnable {

    private final String host;
    private final int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(host, port);
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            while (socket.isConnected()) {
                System.out.println("Client:\t" + ois.readObject().toString());
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
