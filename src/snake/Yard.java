package snake;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class Yard extends Frame {

    PaintThread paintThread = new PaintThread();
    private boolean gameOver = false;
    private int score = 0;
    public static final int ROWS = 40;
    public static final int COLS = 60;
    public static final int BLOCK_SIZE = 10;

    Snake s = new Snake(this);
    Egg e = new Egg();
    Image offScreenImage = null;

    public void launch() {
        this.setLocation(100, 100);
        this.setSize(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                s.keyPressed(e);
            }
        });
        this.setVisible(true);
        new Thread(paintThread).run();
    }

    public static void main(String[] args) {
        new Yard().launch();
    }

    public void stop() {
        gameOver = true;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
        g.setColor(Color.DARK_GRAY);
        for (int i = 1; i < ROWS; i++) {
            g.drawLine(0, BLOCK_SIZE * i, BLOCK_SIZE * COLS, BLOCK_SIZE * i);
        }
        for (int i = 1; i < COLS; i++) {
            g.drawLine(BLOCK_SIZE * i, 0, BLOCK_SIZE * i, BLOCK_SIZE * COLS);
        }
        g.setColor(Color.YELLOW);
        g.drawString("score: " + score, 10, 60);
        if (gameOver) {
            g.setFont(new Font("宋体", Font.BOLD, 64));
            g.drawString("游戏结束", 10, 80);
            paintThread.gameover();
        }
        g.setColor(c);

        s.eat(e);
        s.draw(g);
        e.draw(g);
    }

    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
        }
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public class PaintThread implements Runnable {
        private boolean flag = true;

        public void run() {
            while (flag) {
                repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void gameover() {
            flag = false;
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
