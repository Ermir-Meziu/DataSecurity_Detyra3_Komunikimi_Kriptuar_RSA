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

        new Thread(() -> {
            try {
                while (true) {
                    Object response = in.readObject();
                    if (response instanceof HashMap) {
                        publicKeys = (HashMap<String, PublicKey>) response;
                        System.out.println("Public Keys Updated: " + publicKeys.keySet());
                    } else {
                        String encryptedMessage = (String) response;
                        String sender = encryptedMessage.split(": ")[0];
                        String message = encryptedMessage.split(": ")[1];

                        try {
                            String decryptedMessage = rsa.decrypt(message);
                            System.out.println(sender + " (Decrypted): " + decryptedMessage);
                        } catch (Exception e) {
                            System.out.println(sender + " (Encrypted): " + message);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        while (true) {
            System.out.println("Choose a recipient: " + publicKeys.keySet());
            String recipient = scanner.nextLine();

            if (!publicKeys.containsKey(recipient)) {
                System.out.println("User not found!");
                continue;
            }

            System.out.print("Message: ");
            String message = scanner.nextLine();
            PublicKey recipientKey = publicKeys.get(recipient);

            String encryptedMessage = rsa.encrypt(message, recipientKey);
            System.out.println("Encrypted Message: " + encryptedMessage);

            out.writeObject(new String[]{recipient, encryptedMessage});
        }
    }
}
