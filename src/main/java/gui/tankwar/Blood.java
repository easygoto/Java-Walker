package gui.tankwar;

import java.awt.*;

/**
 * @author trink
 */
public class Blood {
    int x, y, w = 20, h = 20, k = 50;

    TankClient tc;

    private int[][] pos = {{100, 200}, {360, 300}, {375, 275}, {400, 200}, {360, 270}, {365, 295}, {340, 280}};

    private int step = 0;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    private boolean alive = true;

    public Blood() {
        x = pos[0][0];
        y = pos[0][1];
    }

    public void draw(Graphics g) {
        if (!alive) {
            return;
        }
        Color c = g.getColor();
        g.setColor(Color.MAGENTA);
        g.fillOval(x, y, w, h);
        g.setColor(c);

        move();
    }

    private void move() {
        step = (step == (pos.length - 1) * k) ? 0 : (step + 1);
        x = pos[step / k][0];
        y = pos[step / k][1];
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, w, h);
    }
}
