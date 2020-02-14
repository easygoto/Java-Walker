package gui.tankwar;

import java.awt.*;
import java.util.List;

/**
 * @author trink
 */
public class Missile {
    public static final int WIDTH  = 10;
    public static final int HEIGHT = 10;

    private boolean robot;

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

    public Missile(int x, int y, Tank.Direction dir, boolean robot, TankClient tc) {
        this(x, y, dir, tc);
        this.robot = robot;
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
        if (alive && tank.isAlive() && this.getRect().intersects(tank.getRect()) && tank.isRobot() != robot) {
            if (!tank.isRobot() && tank.getLife() > 0) {
                tank.setLife(tank.getLife() - 20);
            } else {
                tank.setAlive(false);
                Boom boom = new Boom(tank.x + Tank.WIDTH / 2, tank.y + Tank.HEIGHT / 2, tc);
                tc.booms.add(boom);
            }
            this.alive = false;
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

    public boolean isRobot() {
        return robot;
    }

    public boolean hitTanks(List<Tank> tanks) {
        return tanks.stream().anyMatch(this::hitTank);
    }

    public boolean hitWall(Wall wall) {
        if (this.alive && this.getRect().intersects(wall.getRect())) {
            this.alive = false;
            return true;
        }
        return false;
    }
}
