package Sockets0_example;

public class Sockets0{
    public static void main(String[] args) {
        String hostURL = "127.0.0.1";
        int port = 1337;

        new Thread(new Server(port)).start();
        new Thread(new Client(hostURL, port)).start();
    }
}
