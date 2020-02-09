package gui.tankwar;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankClient extends Frame {

    public static final int GAME_HEADER = 30;
    public static final int GAME_WIDTH  = 160;
    public static final int GAME_HEIGHT = 120 + GAME_HEADER;

    int tankX = 50, tankY = 50, tankW = 30, tankH = 30;
    int mainX = 100, mainY = 100;
    int timeSpace = 100;
    int speed = 5;

    boolean dir = true;

    Color defaultBgColor   = Color.CYAN;
    Color defaultTankColor = Color.MAGENTA;

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }

        // 双缓冲, 防止界面抖动的问题
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color oldColor = gOffScreen.getColor();
        gOffScreen.setColor(defaultBgColor);
        gOffScreen.fillRect(0, GAME_HEADER, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(oldColor);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(defaultTankColor);
        g.fillOval(tankX, tankY, tankW, tankH);
        g.setColor(color);

        if (tankY + tankH + speed >= GAME_HEIGHT) {
            dir = false;
        } else if (tankY <= GAME_HEADER) {
            dir = true;
        }
        if (dir) {
            tankY += speed;
        } else {
            tankY -= speed;
        }
    }

    public void launchFrame() {
        this.setLocation(mainX, mainY);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("TankWar");
        this.setBackground(defaultBgColor);

        new Thread(new PaintThread()).start();
    }

    public static void main(String[] args) {
        TankClient tankClient = new TankClient();
        tankClient.launchFrame();
    }

    private class PaintThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                repaint(); // 自己先会调用 update 后调用 paint 方法
                try {
                    Thread.sleep(timeSpace);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
