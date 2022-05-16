import java.io.IOException;

public class Main {

    public static void main(String args[]) {
        try {
            new Client("127.0.0.1", 5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
