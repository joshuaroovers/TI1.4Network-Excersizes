package Sockets1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Sockets1 {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello Sockets-1!");


        System.out.println("Sockets-1 Client");

        Socket socket = new Socket("localhost", 2001);

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        Scanner scanner = new Scanner(System.in);

        System.out.println("Typ the weight (kg)");
        dos.writeInt(Integer.parseInt(scanner.nextLine()));

        System.out.println("Typ the height (cm)");
        dos.writeInt(Integer.parseInt(scanner.nextLine()));

        double result = dis.readDouble();

        System.out.println("BMI is " + result);
    }
}