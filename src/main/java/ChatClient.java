import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Scanner;

public class ChatClient {
    private static RSAEncryption rsa;
    private static String username;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    private static HashMap<String, PublicKey> publicKeys = new HashMap<>();

    public static void main(String[] args) throws Exception {
        rsa = new RSAEncryption();
        Socket socket = new Socket("localhost", 1234);

        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        username = scanner.nextLine();
        out.writeObject(username);
        out.writeObject(rsa.getPublicKey());
    }
}
