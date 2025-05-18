import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.PublicKey;
import java.util.HashMap;

public class ChatServer {
    private static final int PORT = 1234;
    private static HashMap<String, ObjectOutputStream> clients = new HashMap<>();
    private static HashMap<String, PublicKey>  publicKeys = new HashMap<>();

    public static void main(String[] args) throws IOException{

    }

    static class ClientHandler extends Thread {
        private Socket socket;
        private ObjectOutputStream out;
        private ObjectInputStream in;
        private String username;

        public ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
        }

        public void run(){
            try {
                username = (String) in.readObject();
                PublicKey publicKey = (PublicKey) in.readObject();

                clients.put(username, out);
                publicKeys.put(username, publicKey);

                System.out.println("✅ " + username + " connected.");
                broadcastUserList();

                String[] data;
                while ((data = (String[]) in.readObject()) != null) {
                    String recipient = data[0];
                    String encryptedMessage = data[1];

                    System.out.println("🔒 Encrypted message from " + username + " to " + recipient + ": " + encryptedMessage);

                    sendToClient(recipient, encryptedMessage, username);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    clients.remove(username);
                    publicKeys.remove(username);
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void broadcastUserList() {
            try {
                for(ObjectOutputStream clientOut : clients.values()) {
                    clientOut.writeObject(new HashMap<>(publicKeys));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void sendToClient(String recipient, String encryptedMessage, String sender) {
            try {
                if(clients.containsKey(recipient)) {
                    clients.get(recipient).writeObject(sender + ": " + encryptedMessage);
                    System.out.println("📩 Message from " + sender + " to " + recipient + " sent successfully.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
