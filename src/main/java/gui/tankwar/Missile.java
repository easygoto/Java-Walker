package gui.tankwar;

import java.awt.*;

/**
 * @author trink
 */
public class Missile {
    public static final int WIDTH  = 10;
    public static final int HEIGHT = 10;

    private boolean alive = true;

    private TankClient tc;

    int xSpeed = 20, ySpeed = 20;
    int x, y;
    Tank.Direction dir;

    Color defaultMissileColor = Color.BLACK;

    public Missile(int x, int y, Tank.Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public Missile(int x, int y, Tank.Direction dir, TankClient tc) {
        this(x, y, dir);
        this.tc = tc;
    }

    public void draw(Graphics g) {
        if (!alive) {
            return;
        }
        Color c = g.getColor();
        g.setColor(defaultMissileColor);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(c);

        move();
    }

    /**
     * 移动
     */
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
        checkAlive();
    }

    /**
     * 撞击坦克
     *
     * @return boolean
     */
    public boolean hitTank(Tank tank) {
        if (tank.isAlive() && this.getRect().intersects(tank.getRect())) {
            this.alive = false;
            tank.setAlive(false);
            Boom boom = new Boom(x, y, tc);
            tc.booms.add(boom);
            return true;
        }
        return false;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    /**
     * 检查是否存在
     */
    public void checkAlive() {
        if (x >= TankClient.MAIN_WIDTH + TankClient.GAME_LEFTER || x <= TankClient.GAME_LEFTER - WIDTH) {
            alive = false;
        }
        if (y >= TankClient.MAIN_HEIGHT + TankClient.GAME_HEADER || y <= TankClient.GAME_HEADER - HEIGHT) {
            alive = false;
        }
    }

    public boolean isAlive() {
        return alive;
    }
}
