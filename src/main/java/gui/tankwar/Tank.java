package gui.tankwar;

import java.awt.*;
import java.awt.event.KeyEvent;

import static gui.tankwar.TankClient.*;

/**
 * @author trink
 */
public class Tank {
    int xSpeed = 10, ySpeed = 10;
    int x, y, w = 50, h = 50;

    boolean bL = false, bU = false, bR = false, bD = false;

    enum Direction {
        /**
         * L(左), R(右), U(上), D(下), LU(左上), RU(右上), LD(左下), RD(右下), STOP(停止)
         */
        L, R, U, D, LU, RU, LD, RD, STOP
    }

    Direction dir = Direction.STOP;

    Color defaultTankColor = Color.MAGENTA;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(defaultTankColor);
        g.fillOval(x, y, w, h);
        g.setColor(c);

        move();
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

        int tankMaxX = GAME_WIDTH - GAME_RIGHTER - w;
        int tankMaxY = GAME_HEIGHT - GAME_FOOTER - h;
        if (x >= tankMaxX) {
            x = tankMaxX;
        } else if (x <= GAME_LEFTER) {
            x = GAME_LEFTER;
        }
        if (y >= tankMaxY) {
            y = tankMaxY;
        } else if (y <= GAME_HEADER) {
            y = GAME_HEADER;
        }
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println(keyCode);
        switch (keyCode) {
            default:
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

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            default:
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
