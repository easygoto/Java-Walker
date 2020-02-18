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
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void connect() {
        try {
            socket = new Socket(IP_ADDRESS, PORT);
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatClient().launch();
    }

    private class SendMsgListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String msg = sendMsg.getText().trim();
            msgArea.setText(msgArea.getText() + msg + LINE_SEPARATOR);
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
