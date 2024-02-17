package model.gameobjects;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Wall extends CollisionObject{
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        URL url = this.getClass().getResource("/wall.jpg");
        Image img = new ImageIcon(url).getImage();

        int xc = getX();
        int yc = getY();
        int height = getHeight();
        int width = getWidth();

        graphics.drawImage(img, xc, yc, width, height, null);
    }
}
