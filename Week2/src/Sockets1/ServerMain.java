package Sockets1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) throws IOException {

        System.out.println("Sockets-1 Server");

        ServerSocket serverSocket = new ServerSocket(2001);

        while(true)
        {
            Socket socket = serverSocket.accept();
            System.out.println("Er is een verbinding");

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            int weight = dataInputStream.readInt();
            System.out.println("received weight: "+ weight);
            int height = dataInputStream.readInt();
            System.out.println("received height: "+ height);

            double BMI = weight/Math.pow((height/100.0),2);
            dataOutputStream.writeDouble(BMI);
            socket.close();
        }
    }
}
