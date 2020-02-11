package gui.tankwar;

import java.awt.*;

import static gui.tankwar.TankClient.*;
import static gui.tankwar.TankClient.GAME_HEADER;

/**
 * @author trink
 */
public class Missile {
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;

    int xSpeed = 20, ySpeed = 20;
    int x, y;
    Tank.Direction dir;

    Color defaultMissileColor = Color.BLACK;

    public Missile(int x, int y, Tank.Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(defaultMissileColor);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(c);

        move();
    }

    public void move() {
        switch (dir) {
            default:
                break;
            case L:
                x -= xSpeed;
                break;
            case R:
                x += xSpeed;
                break;
            case U:
                y -= ySpeed;
                break;
            case D:
                y += ySpeed;
                break;
            case LU:
                x -= xSpeed;
                y -= ySpeed;
                break;
            case LD:
                x -= xSpeed;
                y += ySpeed;
                break;
            case RU:
                x += xSpeed;
                y -= ySpeed;
                break;
            case RD:
                x += xSpeed;
                y += ySpeed;
                break;
        }
    }
}
