package gui.tankwar;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author trink
 */
public class Tank {
    public static final int WIDTH      = 50;
    public static final int HEIGHT     = 50;
    public static final int FULL_BLOOD = 100;

    public static Toolkit toolkit = Toolkit.getDefaultToolkit();

    public static Map<String, Image> images = new HashMap<>();

    int xSpeed = 4, ySpeed = 4;
    int x, y, oldX, oldY;
    boolean bL = false, bU = false, bR = false, bD = false;

    private TankClient tc;

    private boolean robot;

    private boolean alive = true;

    private static Random random = new Random();

    private int step = random.nextInt(12) + 3;

    private int life = 100;

    public int getLife() {
        return life;
    }

    public Tank setLife(int life) {
        this.life = life;
        return this;
    }

    private BloodBar bloodBar = new BloodBar();

    Direction dir = Direction.STOP;

    Direction shootDir = Direction.U;

    Color bloodColor = Color.RED;

    static {
        images.put("L", toolkit.getImage("images/tankWar/tank/L.png"));
        images.put("R", toolkit.getImage("images/tankWar/tank/R.png"));
        images.put("U", toolkit.getImage("images/tankWar/tank/U.png"));
        images.put("D", toolkit.getImage("images/tankWar/tank/D.png"));
        images.put("LU", toolkit.getImage("images/tankWar/tank/LU.png"));
        images.put("LD", toolkit.getImage("images/tankWar/tank/LD.png"));
        images.put("RU", toolkit.getImage("images/tankWar/tank/RU.png"));
        images.put("RD", toolkit.getImage("images/tankWar/tank/RD.png"));
    }

    public Tank(int x, int y, boolean robot) {
        this.x = oldX = x;
        this.y = oldY = y;
        this.robot = robot;
    }

    public Tank(int x, int y, boolean robot, TankClient tc) {
        this(x, y, robot);
        this.tc = tc;
    }

    public Tank(int x, int y, boolean robot, Direction dir, TankClient tc) {
        this(x, y, robot, tc);
        this.dir = dir;
    }

    public void draw(Graphics g) {
        if (!alive) {
            return;
        }

        if (!robot) {
            bloodBar.draw(g);
        }

        Image image = null;
        switch (shootDir) {
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
                image = images.get(shootDir.toString());
                break;
        }
        if (image != null) {
            g.drawImage(image, x, y, null);
        }

        move();
    }

    public void move() {
        oldX = x;
        oldY = y;

        switch (dir) {
            default:
            case STOP:
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

        int tankMaxX = TankClient.GAME_WIDTH - TankClient.GAME_RIGHTER - WIDTH;
        int tankMaxY = TankClient.GAME_HEIGHT - TankClient.GAME_FOOTER - HEIGHT;
        if (x >= tankMaxX) {
            x = tankMaxX;
        } else if (x <= TankClient.GAME_LEFTER) {
            x = TankClient.GAME_LEFTER;
        }
        if (y >= tankMaxY) {
            y = tankMaxY;
        } else if (y <= TankClient.GAME_HEADER) {
            y = TankClient.GAME_HEADER;
        }

        if (this.dir != Direction.STOP) {
            this.shootDir = this.dir;
        }

        if (robot) {
            Direction[] dirs = Direction.values();
            if (step == 0) {
                step = random.nextInt(12) + 3;
                dir = dirs[random.nextInt(dirs.length)];
            }
            step--;

            if (random.nextInt(100) > 95) {
                tc.missiles.add(fire());
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            default:
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                bR = true;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                bL = true;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                bU = true;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                bD = true;
                break;
        }
        locateDirection();
    }

    public Missile fire() {
        return fire(shootDir);
    }

    public Missile fire(Direction shootDir) {
        if (!alive) {
            return null;
        }
        return new Missile(x + WIDTH / 2 - Missile.WIDTH / 2, y + HEIGHT / 2 - Missile.HEIGHT / 2, shootDir, robot, tc);
    }

    public void superFire() {
        Direction[] dirs = Direction.values();
        for (int i = 0; i < 8; i++) {
            tc.missiles.add(fire(dirs[i]));
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            default:
                break;
            case KeyEvent.VK_F2:
                this.alive = true;
                this.life = FULL_BLOOD;
                break;
            case KeyEvent.VK_Q:
            case KeyEvent.VK_ENTER:
                superFire();
                break;
            case KeyEvent.VK_CONTROL:
            case KeyEvent.VK_SPACE:
                tc.missiles.add(fire());
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                bR = false;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                bL = false;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                bU = false;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                bD = false;
                break;
        }
        locateDirection();
    }

    public void locateDirection() {
        if (bL && !bU && !bR && !bD) {
            dir = Direction.L;
        } else if (!bL && !bU && bR && !bD) {
            dir = Direction.R;
        } else if (!bL && bU && !bR && !bD) {
            dir = Direction.U;
        } else if (!bL && !bU && !bR && bD) {
            dir = Direction.D;
        } else if (bL && bU && !bR && !bD) {
            dir = Direction.LU;
        } else if (!bL && bU && bR && !bD) {
            dir = Direction.RU;
        } else if (bL && !bU && !bR && bD) {
            dir = Direction.LD;
        } else if (!bL && !bU && bR && bD) {
            dir = Direction.RD;
        } else {
            dir = Direction.STOP;
        }
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isRobot() {
        return robot;
    }

    public Tank setAlive(boolean alive) {
        setLife(0);
        this.alive = alive;
        return this;
    }

    private void stay() {
        x = oldX;
        y = oldY;
    }

    public boolean collideWithWall(Wall wall) {
        if (alive && this.getRect().intersects(wall.getRect())) {
            stay();
            return true;
        }
        return false;
    }

    public boolean collideWithTank(Tank tank) {
        if (this != tank && alive && tank.isAlive() && this.getRect().intersects(tank.getRect())) {
            stay();
            tank.stay();
            return true;
        }
        return false;
    }

    public void collideWithTanks(List<Tank> tanks) {
        for (Tank tank : tanks) {
            collideWithTank(tank);
        }
    }

    public boolean eat(Blood blood) {
        if (alive && blood.isAlive() && this.getRect().intersects(blood.getRect())) {
            this.life = FULL_BLOOD;
            blood.setAlive(false);
            return true;
        }
        return false;
    }

    private class BloodBar {
        public void draw(Graphics g) {
            Color c = g.getColor();
            g.setColor(bloodColor);
            g.drawRect(x, y - 5, WIDTH, 3);
            int lifeW = WIDTH * life / 100;
            g.fillRect(x, y - 5, lifeW, 3);
            g.setColor(c);
        }
    }
}
