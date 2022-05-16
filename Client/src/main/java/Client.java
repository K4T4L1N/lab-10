
// A Java program for a Client

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public Client(String address, int port) throws IOException {
        Socket socket = null;
        DataInputStream read = null;
        DataOutputStream write = null;

        socket = new Socket(address, port);
        read = new DataInputStream(socket.getInputStream());
        write = new DataOutputStream(socket.getOutputStream());
        System.out.println("Connected");

        String line = "";
        String response = "";
        Scanner scanner = new Scanner(System.in);
        while (!line.equals("exit")) {
            line = scanner.nextLine();
            write.writeUTF(line);
            response = read.readUTF();
            System.out.println(response);
            System.out.println(read.readUTF());
            if ("Server stopped".equals(response)) {
                break;
            }
            if ("Connection timed out".equals(response)) {
                break;
            }
        }

        write.close();
        read.close();
        socket.close();

    }
}