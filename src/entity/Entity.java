package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;

    public BufferedImage l1, l2, l3, l4, r1, r2, r3, r4;
    public String direction;
    public String lastDirection;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public boolean collisionOn = false;
}
