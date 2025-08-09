package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Box extends SuperObject {
    public OBJ_Box() {
        name = "Box";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/box.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
        isMovable = true;
        speed = 32;
    }

    public void update(GamePanel gamePanel) {
        if (!collisionOn && isMovable) {
            switch (gamePanel.player.direction) {
                case "up" -> this.worldY -= speed;
                case "down" -> this.worldY += speed;
                case "left" -> this.worldX -= speed;
                case "right" -> this.worldX += speed;
            }
        }
    }

    public void changeState() {
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/green_box.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }    }
}
