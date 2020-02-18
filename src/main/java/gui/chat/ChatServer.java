package gui.chat;

import java.io.*;
import java.net.*;
import java.util.*;

import static gui.chat.Constant.*;

/**
 * @author trink
 */
public class ChatServer {

    List<Client> clientList = new ArrayList<>();

    public static void main(String[] args) {
        new ChatServer().start();
    }

    public void start() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
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
                clientList.add(client);

                Thread thread = new Thread(client);
                thread.join();
                thread.start();
            }
        } catch (Exception ignored) {
        }
    }

    private class Client implements Runnable {

        private String ipAddress = "";

        private Socket socket;

        private DataInputStream dis;

        private DataOutputStream dos;

        public Client(Socket socket) {
            this.socket = socket;
            try {
                this.dis = new DataInputStream(socket.getInputStream());
                this.dos = new DataOutputStream(socket.getOutputStream());
                this.ipAddress = socket.getInetAddress().getHostAddress();
                System.out.println(this.ipAddress + ": connected ...");
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
                    String serverMsg = this.ipAddress + ": " + msg;
                    clientList.forEach((Client c) -> c.send(serverMsg));
                    System.out.println(serverMsg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void send(String msg) {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void close() {
            clientList.remove(this);
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (socket != null) {
                        System.out.println(this.ipAddress + ": closed ...");
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
