// A Java program for a Server

import java.io.IOException;

public class Server {

    public static boolean serverRunning = true;

    public Server(int port) throws IOException {

        ServerSocketClass serverSocketClass = new ServerSocketClass(port);
        while (serverRunning) {
            serverSocketClass.socket = serverSocketClass.server.accept();
            System.out.println("Client accepted");
            ClientCommand clientCommand = new ClientCommand(serverSocketClass.socket);
            Thread client = new Thread(clientCommand);
            client.start();
        }
    }
}