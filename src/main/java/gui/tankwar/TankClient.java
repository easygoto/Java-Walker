package gui.tankwar;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author trink
 */
public class TankClient extends Frame {

    public static final int GAME_HEADER  = 60;
    public static final int GAME_LEFTER  = 30;
    public static final int GAME_RIGHTER = 30;
    public static final int GAME_FOOTER  = 30;
    public static final int MAIN_X       = 100;
    public static final int MAIN_Y       = 100;
    public static final int MAIN_WIDTH   = 800;
    public static final int MAIN_HEIGHT  = 600;
    public static final int GAME_WIDTH   = MAIN_WIDTH + GAME_LEFTER + GAME_RIGHTER;
    public static final int GAME_HEIGHT  = MAIN_HEIGHT + GAME_HEADER + GAME_FOOTER;

    int timeSpace = 100, robotNum = 10;

    Tank myTank = new Tank(GAME_LEFTER + 50, GAME_HEADER + 50, false, Tank.Direction.STOP, this);

    List<Missile> missiles = new ArrayList<>();
    List<Boom>    booms    = new ArrayList<>();
    List<Tank>    tanks    = new ArrayList<>();

    Color defaultBgColor   = Color.CYAN;
    Color defaultLineColor = Color.LIGHT_GRAY;
    Image offScreenImage   = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }

        // 双缓冲, 防止界面抖动的问题
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color oldColor = gOffScreen.getColor();
        gOffScreen.setColor(defaultBgColor);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(oldColor);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        this.paintMainZone(g);
        myTank.draw(g);

        for (Iterator<Boom> iterator = booms.iterator(); iterator.hasNext(); ) {
            Boom boom = iterator.next();
            if (boom.isAlive()) {
                boom.draw(g);
            } else {
                iterator.remove();
            }
        }

        for (Iterator<Tank> iterator = tanks.iterator(); iterator.hasNext(); ) {
            Tank tank = iterator.next();
            if (tank.isAlive()) {
                tank.draw(g);
            } else {
                if (tank.isRobot()) {
                    iterator.remove();
                }
            }
        }

        for (Iterator<Missile> iterator = missiles.iterator(); iterator.hasNext(); ) {
            Missile missile = iterator.next();
            if (missile != null && missile.isAlive()) {
                missile.hitTank(myTank);
                missile.hitTanks(tanks);
                missile.draw(g);
            } else {
                iterator.remove();
            }
        }
    }

    public void paintMainZone(Graphics g) {
        Color color = g.getColor();
        g.setColor(defaultLineColor);
        g.drawLine(GAME_LEFTER, GAME_HEADER, GAME_LEFTER + MAIN_WIDTH, GAME_HEADER);
        g.drawLine(GAME_LEFTER, GAME_HEADER + MAIN_HEIGHT, GAME_LEFTER + MAIN_WIDTH, GAME_HEADER + MAIN_HEIGHT);
        g.drawLine(GAME_LEFTER, GAME_HEADER, GAME_LEFTER, GAME_HEADER + MAIN_HEIGHT);
        g.drawLine(GAME_LEFTER + MAIN_WIDTH, GAME_HEADER, GAME_LEFTER + MAIN_WIDTH, GAME_HEADER + MAIN_HEIGHT);
        g.setColor(color);
    }

    public void launchFrame() {
        for (int i = 0; i < robotNum; i++) {
            tanks.add(new Tank(GAME_LEFTER + 50 + 60 * (i + 1), GAME_HEADER + 50, true, Tank.Direction.STOP, this));
        }

        this.setLocation(MAIN_X, MAIN_Y);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setResizable(false);
        this.setTitle("TankWar");
        this.setBackground(defaultBgColor);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyMonitor());

        setVisible(true);
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
                // 自己先会调用 update 后调用 paint 方法
                repaint();
                try {
                    Thread.sleep(timeSpace);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class KeyMonitor extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);
        }
    }
}
