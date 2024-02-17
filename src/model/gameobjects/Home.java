package model.gameobjects;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Home extends GameObject{
    public Home(int x, int y) {
        super(x, y, 15, 15);
    }

    @Override
    public void draw(Graphics graphics) {
        URL url = this.getClass().getResource("/home.png");
        Image img = new ImageIcon(url).getImage();

        int xc = getX();
        int yc = getY();
        int height = getHeight();
        int width = getWidth();

        graphics.drawImage(img, xc+getWidth(), yc+getHeight(), width, height, null);
    }
}
