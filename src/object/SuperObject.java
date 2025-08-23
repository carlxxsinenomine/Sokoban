package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public int speed;
    public boolean collision = false;
    public boolean collisionOn = false;
    public boolean isMovable = false;
    public boolean shouldCount = true;
    public int worldX, worldY;

    public void draw(Graphics2D g2, GamePanel gamePanel) {
        g2.drawImage(image, worldX, worldY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
