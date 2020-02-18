package gui.snake;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author trink
 */
public class Snake {
    private Node head;
    private Node tail;
    private Yard yard;

    private int size;

    private boolean alive = true;

    public Snake(Yard yard) {
        Node n = new Node(30, 20, Dir.R);
        head = n;
        tail = n;
        size = 1;
        this.yard = yard;
    }

    public void addToTail() {
        Node node;
        switch (tail.dir) {
            default:
            case L:
                node = new Node(tail.row, tail.col + 1, tail.dir);
                break;
            case U:
                node = new Node(tail.row + 1, tail.col, tail.dir);
                break;
            case R:
                node = new Node(tail.row, tail.col - 1, tail.dir);
                break;
            case D:
                node = new Node(tail.row - 1, tail.col, tail.dir);
                break;
        }
        tail.next = node;
        node.prev = tail;
        tail = node;
        size += 1;
    }

    public void addToHead() {
        Node node;
        switch (head.dir) {
            default:
            case L:
                node = new Node(head.row, head.col - 1, head.dir);
                break;
            case U:
                node = new Node(head.row - 1, head.col, head.dir);
                break;
            case R:
                node = new Node(head.row, head.col + 1, head.dir);
                break;
            case D:
                node = new Node(head.row + 1, head.col, head.dir);
                break;
        }
        node.next = head;
        head.prev = node;
        head = node;
        size += 1;
    }

    public void draw(Graphics g) {
        if (size < 0) {
            return;
        }
        move();
        for (Node n = head; n != null; n = n.next) {
            n.draw(g);
        }
    }

    public void move() {
        if (!alive) {
            return;
        }
        addToHead();
        deleteFromTail();
        checkDead();
    }

    public void checkDead() {
        if (head.row < 0 || head.col < 0 || head.row >= Yard.ROWS || head.col >= Yard.COLS) {
            alive = false;
            yard.stop();
        }
        for (Node n = head.next; size > 1 && n != null; n = n.next) {
            if ((head.row == n.row) && (head.col == n.col)) {
                yard.stop();
            }
        }
    }

    public void deleteFromTail() {
        if (tail == null) {
            return;
        }
        tail = tail.prev;
        tail.next = null;
    }

    public void eat(Egg egg) {
        if (this.getRect().intersects(egg.getRect())) {
            egg.reAppear();
            yard.setScore(yard.getScore() + 5);
            this.addToHead();
        }
    }

    public Rectangle getRect() {
        return new Rectangle(Yard.BLOCK_SIZE * head.col, Yard.BLOCK_SIZE * head.row, head.w, head.h);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                if (head.dir != Dir.R) {
                    head.dir = Dir.L;
                }
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                if (head.dir != Dir.L) {
                    head.dir = Dir.R;
                }
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                if (head.dir != Dir.D) {
                    head.dir = Dir.U;
                }
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                if (head.dir != Dir.U) {
                    head.dir = Dir.D;
                }
                break;
            default:
                break;
        }
    }

    static class Node {
        int w = Yard.BLOCK_SIZE;
        int h = Yard.BLOCK_SIZE;
        int row, col;
        Dir  dir  = Dir.L;
        Node next = null;
        Node prev = null;

        Node(int row, int col, Dir dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }

        void draw(Graphics g) {
            Color c = g.getColor();
            g.setColor(Color.BLACK);
            g.fillRect(Yard.GAME_LEFTER + Yard.BLOCK_SIZE * col, Yard.GAME_HEADER + Yard.BLOCK_SIZE * row, w, h);
            g.setColor(c);
        }
    }
}
