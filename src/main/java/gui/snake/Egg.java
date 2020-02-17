package gui.snake;

import java.awt.*;
import java.util.Random;

/**
 * @author trink
 */
public class Egg {
    private int row, col;
    private int w = Yard.BLOCK_SIZE;
    private int h = Yard.BLOCK_SIZE;

    private static Random r = new Random();

    public Egg(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Egg() {
        this(r.nextInt(Yard.ROWS), r.nextInt(Yard.COLS));
    }

    public Rectangle getRect() {
        return new Rectangle(Yard.BLOCK_SIZE * col, Yard.BLOCK_SIZE * row, w, h);
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GREEN);
        g.fillOval(Yard.GAME_LEFTER + Yard.BLOCK_SIZE * col, Yard.GAME_HEADER + Yard.BLOCK_SIZE * row, w, h);
        g.setColor(c);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void reAppear() {
        this.row = r.nextInt(Yard.ROWS);
        this.col = r.nextInt(Yard.ROWS);
    }
}
