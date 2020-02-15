package gui.tankwar;

import java.awt.*;
import java.io.File;
import java.util.Arrays;

/**
 * @author trink
 */
public class Boom {
    int x, y, step = 0;

    private boolean init = false;

    public static Toolkit toolkit = Toolkit.getDefaultToolkit();

    public static Image[] images = null;

    private boolean alive = true;

    private TankClient tc;

    static {
        images = new Image[]{
                toolkit.getImage(new File("images/tankWar/boom/0.gif").getAbsolutePath()),
                toolkit.getImage(new File("images/tankWar/boom/1.gif").getAbsolutePath()),
                toolkit.getImage(new File("images/tankWar/boom/2.gif").getAbsolutePath()),
                toolkit.getImage(new File("images/tankWar/boom/3.gif").getAbsolutePath()),
                toolkit.getImage(new File("images/tankWar/boom/4.gif").getAbsolutePath()),
                toolkit.getImage(new File("images/tankWar/boom/5.gif").getAbsolutePath()),
                toolkit.getImage(new File("images/tankWar/boom/6.gif").getAbsolutePath()),
                toolkit.getImage(new File("images/tankWar/boom/7.gif").getAbsolutePath()),
                toolkit.getImage(new File("images/tankWar/boom/8.gif").getAbsolutePath()),
                toolkit.getImage(new File("images/tankWar/boom/9.gif").getAbsolutePath()),
                toolkit.getImage(new File("images/tankWar/boom/10.gif").getAbsolutePath()),
        };
    }

    public Boom(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Boom(int x, int y, TankClient tc) {
        this(x, y);
        this.tc = tc;
    }

    public void draw(Graphics g) {
        if (!init) {
            Arrays.stream(images).forEach(image -> g.drawImage(image, -image.getWidth(null), -image.getHeight(null), null));
            init = true;
        }

        if (!alive || step == images.length) {
            alive = false;
            return;
        }

        Image image = images[step];
        g.drawImage(image, x - image.getWidth(null) / 2, y - image.getHeight(null) / 2, null);
        step++;
    }

    public boolean isAlive() {
        return alive;
    }
}
