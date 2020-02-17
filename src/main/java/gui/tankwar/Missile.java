package gui.tankwar;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author trink
 */
public class Missile {
    public static final int WIDTH     = 10;
    public static final int HEIGHT    = 10;
    public static final int TANK_HURT = 20;

    private static Toolkit toolkit = Toolkit.getDefaultToolkit();

    private static Map<String, Image> images = new HashMap<>();

    private boolean robot;

    private boolean alive = true;

    private TankClient tc;

    int xSpeed = 8, ySpeed = 8;
    int x, y;
    Direction dir;

    Color defaultRobotMissileColor = Color.GREEN;

    static {
        images.put("L", toolkit.getImage("images/tankWar/missile/L.gif"));
        images.put("R", toolkit.getImage("images/tankWar/missile/R.gif"));
        images.put("U", toolkit.getImage("images/tankWar/missile/U.gif"));
        images.put("D", toolkit.getImage("images/tankWar/missile/D.gif"));
        images.put("LU", toolkit.getImage("images/tankWar/missile/LU.gif"));
        images.put("LD", toolkit.getImage("images/tankWar/missile/LD.gif"));
        images.put("RU", toolkit.getImage("images/tankWar/missile/RU.gif"));
        images.put("RD", toolkit.getImage("images/tankWar/missile/RD.gif"));
    }

    public Missile(int x, int y, Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public Missile(int x, int y, Direction dir, TankClient tc) {
        this(x, y, dir);
        this.tc = tc;
    }

    public Missile(int x, int y, Direction dir, boolean robot, TankClient tc) {
        this(x, y, dir, tc);
        this.robot = robot;
    }

    public void draw(Graphics g) {
        if (!alive) {
            return;
        }
        Color c = g.getColor();
        if (robot) {
            g.setColor(defaultRobotMissileColor);
            g.fillOval(x, y, WIDTH, HEIGHT);
        } else {
            Image image = null;
            switch (dir) {
                default:
                    break;
                case L:
                case R:
                case U:
                case D:
                case LU:
                case LD:
                case RU:
                case RD:
                    image = images.get(dir.toString());
                    break;
            }
            if (image != null) {
                g.drawImage(image, x, y, null);
            }
        }
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
            int tankLife = tank.getLife() - TANK_HURT;
            if (!tank.isRobot() && tankLife > 0) {
                tank.setLife(tankLife);
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
