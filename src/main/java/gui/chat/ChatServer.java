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
        ServerSocket serverSocket = null;
        Socket client = null;
        DataInputStream dis = null;

        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (serverSocket != null) {
                while (true) {
                    client = serverSocket.accept();
                    dis = new DataInputStream(client.getInputStream());

                    while (true) {
                        String msg = dis.readUTF();
                        if (msg.equals(CLIENT_EXIT_MSG)) {
                            close(dis, client);
                            break;
                        }
                        System.out.printf("%s: %s\n", client.getInetAddress().getHostAddress(), msg);
                    }
                }
            }
        } catch (Exception ignored) {
        } finally {
            close(dis, client);
        }
    }

    public static void close(DataInputStream dis, Socket socket) {
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
