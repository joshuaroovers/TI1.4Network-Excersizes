package Sockets0_example;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    private final int port;

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            ServerSocket listenSocket = new ServerSocket(port);
            while (true) {
                Socket client = listenSocket.accept();
                new Thread(() -> handelClientOnServer(client)).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handelClientOnServer(Socket client) {
        try {
            int counter = 0;
            System.out.println("Got connnection from " + client.getInetAddress().getHostAddress());
            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());

            MyData myData = new MyData("Hallo", 0);

            while (client.isConnected()) {
                System.out.println("Server:\t" + myData.toString());
                oos.reset();
                oos.writeObject(myData);
                oos.flush();
                Thread.sleep(1000);
                myData.setCounter(myData.getCounter() + 1);
            }

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
