package gui.tankwar;

import java.awt.*;

/**
 * @author trink
 */
public class Boom {
    int x, y, step = 0;
    int[] diameter = {4, 7, 12, 18, 26, 32, 49, 30, 14, 6};

    Color defaultBoomColor = Color.YELLOW;

    private boolean alive = true;

    private TankClient tc;

    public Boom(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Boom(int x, int y, TankClient tc) {
        this(x, y);
        this.tc = tc;
    }

    public void draw(Graphics g) {
        if (!alive || step == diameter.length) {
            return;
        }

        Color c = g.getColor();
        g.setColor(defaultBoomColor);
        g.fillOval(x, y, diameter[step], diameter[step]);
        step++;
        g.setColor(c);
    }

    public boolean isAlive() {
        return alive;
    }
}
