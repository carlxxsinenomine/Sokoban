package button;

import main.MouseHandler;

import java.awt.image.BufferedImage;

public class Button extends SuperButton {
    public Button(String buttonName, BufferedImage image, int width, int height, int buttonX, int buttonY) {
        this.buttonName = buttonName;
        this.image = image;
        this.width = width;
        this.height = height;
        this.buttonX = buttonX;
        this.buttonY = buttonY;
    }

    public boolean mouseOnButton(MouseHandler mouseHandler) {
        if (mouseHandler.mouseY >= buttonY &&
                mouseHandler.mouseY <= buttonY + height &&
                mouseHandler.mouseX >= buttonX &&
                mouseHandler.mouseX <= buttonX + width) {

            mouseHandler.mouseX = mouseHandler.mouseY = 0; // Reset kasi potek nareread parin after pressing Levels button then na auto pindot ung level

            return true;
        }

        return false;
    }
}
