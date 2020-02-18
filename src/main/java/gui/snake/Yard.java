package gui.snake;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.stream.IntStream;

/**
 * @author trink
 */
public class Yard extends Frame {

    public static final int ROWS         = 60;
    public static final int COLS         = 80;
    public static final int BLOCK_SIZE   = 10;
    public static final int GAME_HEADER  = 100;
    public static final int GAME_FOOTER  = 30;
    public static final int GAME_LEFTER  = 30;
    public static final int GAME_RIGHTER = 30;
    public static final int MAIN_WIDTH   = COLS * BLOCK_SIZE;
    public static final int MAIN_HEIGHT  = ROWS * BLOCK_SIZE;
    public static final int GAME_WIDTH   = GAME_LEFTER + MAIN_WIDTH + GAME_RIGHTER;
    public static final int GAME_HEIGHT  = GAME_HEADER + MAIN_HEIGHT + GAME_FOOTER;

    Egg egg = new Egg();

    Snake snake = new Snake(this);

    Image offScreenImage = null;

    PaintThread paintThread = new PaintThread();

    private int     score    = 0;
    private boolean gameOver = false;

    /**
     * 游戏的主界面
     */
    public void launch() {
        this.setLocation(100, 100);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
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
        this.setTitle("贪吃蛇");
        this.setResizable(false);
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
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        g.setColor(Color.DARK_GRAY);
        IntStream.rangeClosed(0, ROWS).forEach(i -> g.drawLine(GAME_LEFTER, GAME_HEADER + BLOCK_SIZE * i, GAME_LEFTER + MAIN_WIDTH, GAME_HEADER + BLOCK_SIZE * i));
        IntStream.rangeClosed(0, COLS).forEach(i -> g.drawLine(GAME_LEFTER + BLOCK_SIZE * i, GAME_HEADER, GAME_LEFTER + BLOCK_SIZE * i, GAME_HEADER + MAIN_HEIGHT));

        g.setColor(Color.YELLOW);
        g.drawString("score: " + score, GAME_LEFTER, GAME_HEADER - 5);

        // 游戏结束
        if (gameOver) {
            g.setFont(new Font("宋体", Font.BOLD, 64));
            g.drawString("游戏结束", 300, 80);
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
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
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
