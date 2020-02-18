package gui.chat;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author trink
 */
public class ChatServer {

    public static final String CLIENT_EXIT_MSG = "CLIENT_EXIT";

    public static void main(String[] args) {
        new ChatServer().start();
    }

    public void start() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (serverSocket == null) {
                return;
            }
            while (true) {
                Socket socket = serverSocket.accept();
                Client client = new Client(socket);
                new Thread(client).start();
            }
        } catch (Exception ignored) {
        }
    }

    private static class Client implements Runnable {

        private Socket socket;

        private DataInputStream dis;

        public Client(Socket socket) {
            this.socket = socket;
            try {
                this.dis = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String msg = dis.readUTF();
                    if (msg.equals(CLIENT_EXIT_MSG)) {
                        close();
                        break;
                    }
                    System.out.printf("%s: %s\n", socket.getInetAddress().getHostAddress(), msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void close() {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (socket != null) {
                    System.out.printf("%s: closed ...\n", socket.getInetAddress().getHostAddress());
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
