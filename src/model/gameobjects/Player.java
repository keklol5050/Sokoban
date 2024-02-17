package model.gameobjects;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Player extends CollisionObject implements Movable{

    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int x, int y) {
        setX(getX()+x);
        setY(getY()+y);
    }

    @Override
    public void draw(Graphics graphics) {
        URL url = this.getClass().getResource("/player.png");
        Image img = new ImageIcon(url).getImage();
        int xc = getX();
        int yc = getY();
        int height = getHeight();
        int width = getWidth();

        graphics.drawImage(img, xc, yc, width, height, null);
    }
}
