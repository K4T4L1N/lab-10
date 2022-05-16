import java.io.IOException;

public class Main {
    public static void main(String args[]) {
        try {
            new Server(5000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
