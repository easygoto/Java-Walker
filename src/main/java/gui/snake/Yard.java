package gui.snake;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Yard extends Frame {

    Egg egg = new Egg();

    Snake snake = new Snake(this);

    Image offScreenImage = null;

    PaintThread paintThread = new PaintThread();

    private int     score    = 0;
    private boolean gameOver = false;

    public static final int ROWS       = 40;
    public static final int COLS       = 60;
    public static final int BLOCK_SIZE = 10;

    /**
     * 因为 windows 下对话框头部会占大约 30 的距离
     */
    public static final int UNNECESSARY = (int) Math.ceil(((double) 30 / BLOCK_SIZE));

    /**
     * 游戏的主界面
     */
    public void launch() {
        this.setLocation(100, 100);
        this.setSize(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                snake.keyPressed(e);
            }
        });
        this.setVisible(true);
        new Thread(paintThread).start();
    }

    public static void main(String[] args) {
        new Yard().launch();
    }

    /**
     * 游戏结束
     */
    public void stop() {
        gameOver = true;
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();

        // 绘图主界面
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

        // 游戏结束
        if (gameOver) {
            g.setFont(new Font("宋体", Font.BOLD, 64));
            g.drawString("游戏结束", 10, 80);
            paintThread.gameOver();
        }
        g.setColor(c);

        snake.eat(egg);
        snake.draw(g);
        egg.draw(g);
    }

    @Override
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

        @Override
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

        public void gameOver() {
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
