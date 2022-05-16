import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketClass {
    int port;
    Socket socket = null;
    ServerSocket server = null;


    public ServerSocketClass(int port) {
        this.port = port;
        initializeServer();
    }

    private void initializeServer() {
        // starts server and waits for a connection
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");
        } catch (IOException i) {
            System.out.println(i);
        }
    }

}
