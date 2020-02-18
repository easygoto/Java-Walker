package gui.chat;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import static gui.chat.Constant.*;

/**
 * @author trink
 */
public class ChatClient extends Frame {

    TextField sendMsg = new TextField();
    TextArea  msgArea = new TextArea();

    Socket socket = null;

    Thread thread = null;

    DataInputStream dis = null;

    DataOutputStream dos = null;

    public void launch() {
        this.setLocation(LOCATION_X, LOCATION_Y);
        this.setSize(WIN_WIDTH, WIN_HEIGHT);
        this.setResizable(false);
        this.setTitle(TITLE + " - " + VERSION);
        this.add(sendMsg, BorderLayout.SOUTH);
        this.add(msgArea, BorderLayout.NORTH);
        this.pack();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
                System.exit(0);
            }
        });
        sendMsg.addActionListener(new SendMsgListener());
        this.setVisible(true);

        connect();
    }

    public void close() {
        try {
            if (dos != null) {
                dos.writeUTF(CLIENT_EXIT_MSG);
                dos.flush();
                dos.close();
                dos = null;
            }
            if (dis != null) {
                dis.close();
                dis = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                    socket = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void connect() {
        try {
            socket = new Socket(IP_ADDRESS, PORT);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());

            Server server = new Server();
            thread = new Thread(server);
            thread.join();
            thread.start();
        } catch (Exception ignored) {
        }
    }

    public static void main(String[] args) {
        new ChatClient().launch();
    }

    private class Server implements Runnable {

        @Override
        public void run() {
            try {
                while (dis != null) {
                    String msg = dis.readUTF();
                    msgArea.setText(msgArea.getText() + msg + LINE_SEPARATOR);
                }
            } catch (SocketException e) {
                thread.interrupt();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class SendMsgListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String msg = sendMsg.getText().trim();
            sendMsg.setText(BLANK_MSG);

            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
