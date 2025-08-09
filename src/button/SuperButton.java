package button;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperButton {
    public BufferedImage image;
    public String buttonName;
    public int buttonX, buttonY;
    public int width, height;

    public void draw(Graphics2D g2) {
        g2.drawImage(image, buttonX, buttonY, width, height, null);
    }
}
