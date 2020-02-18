package gui.chat;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author trink
 */
public class ChatClient extends Frame {

    public static final int WIN_LEFTER  = 30;
    public static final int WIN_RIGHTER = 30;
    public static final int WIN_HEADER  = 100;
    public static final int WIN_FOOTER  = 30;
    public static final int MAIN_WIDTH  = 300;
    public static final int MAIN_HEIGHT = 300;
    public static final int WIN_WIDTH   = WIN_LEFTER + MAIN_WIDTH + WIN_RIGHTER;
    public static final int WIN_HEIGHT  = WIN_HEADER + MAIN_HEIGHT + WIN_FOOTER;

    public static final String LINE_SEPARATOR = "\n";
    public static final String CLIENT_EXIT_MSG = "CLIENT_EXIT";

    TextField sendMsg = new TextField();
    TextArea  msgArea = new TextArea();

    Socket           socket = null;
    DataOutputStream dos    = null;

    public void launch() {
        this.setLocation(100, 100);
        this.setSize(WIN_WIDTH, WIN_HEIGHT);
        this.setResizable(false);
        this.setTitle("聊天小程序");
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

    public void receive() {

    }

    public void connect() {
        try {
            socket = new Socket("0.0.0.0", 8888);
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
            sendMsg.setText("");

            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
