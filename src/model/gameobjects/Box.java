package model.gameobjects;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Box extends CollisionObject implements Movable{

    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(int x, int y) {
        setX(getX()+x);
        setY(getY()+y);
    }

    @Override
    public void draw(Graphics graphics) {
        URL url = this.getClass().getResource("/box.png");
        Image img = new ImageIcon(url).getImage();

        int xc = getX();
        int yc = getY();
        int height = getHeight();
        int width = getWidth();

        graphics.drawImage(img, xc, yc, width, height, null);
    }
}
