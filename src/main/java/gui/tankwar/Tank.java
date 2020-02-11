package gui.tankwar;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author trink
 */
public class Tank {
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    int xSpeed = 10, ySpeed = 10;
    int x, y;

    boolean bL = false, bU = false, bR = false, bD = false;

    private TankClient tc;

    enum Direction {
        /**
         * L(左), R(右), U(上), D(下), LU(左上), RU(右上), LD(左下), RD(右下), STOP(停止)
         */
        L, R, U, D, LU, RU, LD, RD, STOP
    }

    Direction dir = Direction.STOP;
    Direction shootDir = Direction.U;

    Color defaultTankColor = Color.MAGENTA;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tank(int x, int y, TankClient tc) {
        this(x, y);
        this.tc = tc;
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(defaultTankColor);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(c);

        drawShoot(g);
        move();
    }

    public void drawShoot(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.BLACK);
        switch (shootDir) {
            default:
            case STOP:
                break;
            case L:
                g.drawLine(x + WIDTH / 2, y + HEIGHT / 2, x, y + HEIGHT / 2);
                break;
            case R:
                g.drawLine(x + WIDTH / 2, y + HEIGHT / 2, x + WIDTH, y + HEIGHT / 2);
                break;
            case U:
                g.drawLine(x + WIDTH / 2, y + HEIGHT / 2, x + WIDTH / 2, y);
                break;
            case D:
                g.drawLine(x + WIDTH / 2, y + HEIGHT / 2, x + WIDTH / 2, y + HEIGHT);
                break;
            case LU:
                g.drawLine(x + WIDTH / 2, y + HEIGHT / 2, x, y);
                break;
            case LD:
                g.drawLine(x + WIDTH / 2, y + HEIGHT / 2, x, y + HEIGHT);
                break;
            case RU:
                g.drawLine(x + WIDTH / 2, y + HEIGHT / 2, x + WIDTH, y);
                break;
            case RD:
                g.drawLine(x + WIDTH / 2, y + HEIGHT / 2, x + WIDTH, y + HEIGHT);
                break;
        }
        g.setColor(c);
    }

    public void move() {
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
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            default:
                break;
            case KeyEvent.VK_CONTROL:
            case KeyEvent.VK_SPACE:
                tc.missile = fire();
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
        return new Missile(x + WIDTH / 2 - Missile.WIDTH / 2, y + HEIGHT / 2  - Missile.HEIGHT / 2, shootDir);
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            default:
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
}
