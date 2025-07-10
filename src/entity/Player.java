package entity;

import main.KeyHandler;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;


    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 10;
        worldY = gamePanel.tileSize * 10;
        // set default direction

        speed = 32;
        direction = "right";
    }

    public void getPlayerImage() {
        try {
            r1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/character/img.png")));
            r2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/character/img_1.png")));
            r3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/character/img_2.png")));
            r4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/character/img_3.png")));
            l1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/character/img_4.png")));
            l2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/character/img_5.png")));
            l3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/character/img_6.png")));
            l4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/character/img_7.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update player state
    public void update() {
        // Check if any keys are pressed
        if (keyHandler.upPressed || keyHandler.downPressed ||
                keyHandler.leftPressed || keyHandler.rightPressed) {

            if (keyHandler.upPressed) {
                direction = "up";}
            else if (keyHandler.downPressed) {
                direction = "down";}
            else if (keyHandler.leftPressed)
                direction = "left";
            else
                direction = "right";

            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }

        // To check which sprite to show
        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1)
                spriteNum = 2;
            else if (spriteNum == 2)
                spriteNum = 3;
            else if (spriteNum == 3)
                spriteNum = 4;
            else
                spriteNum = 1;

            spriteCounter = 0;
        }
    }

    // TODO 1: Create an animation for the character when not moving
    public void draw(Graphics2D g2) {
        BufferedImage image;

        if (direction.equals("right")) {
            lastDirection = direction;
            if (spriteNum == 1)
                image = r1;
            else if (spriteNum == 2)
                image = r2;
            else if (spriteNum == 3)
                image = r3;
            else
                image = r4;
        } else if (direction.equals("left")) {
            lastDirection = direction;
            if (spriteNum == 1)
                image = l1;
            else if (spriteNum == 2)
                image = l2;
            else if (spriteNum == 3)
                image = l3;
            else
                image = l4;
        } else {
            if (lastDirection.equals("right"))
                if (spriteNum == 1)
                    image = r1;
                else if (spriteNum == 2)
                    image = r2;
                else if (spriteNum == 3)
                    image = r3;
                else
                    image = r4;
            else
            if (spriteNum == 1)
                image = l1;
            else if (spriteNum == 2)
                image = l2;
            else if (spriteNum == 3)
                image = l3;
            else
                image = l4;

        }
//        System.out.println(direction);
        g2.drawImage(image, worldX, worldY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
