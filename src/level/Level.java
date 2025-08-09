package level;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Objects;

public class Level {
    BufferedImage image;
    public Font gemuno;
    public int levelNumber = 0;
    public int bestMove;
    public String levelString;
    public boolean isCompleted = false;
    public int xPos, yPos, txtX, txtY;
    public final int width = 50, height = 50;
//    public Rectangle rectangle = new Rectangle(xPos, yPos, width, height);

    public Level() {
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/gemuno.ttf");
            assert is != null;
            gemuno = Font.createFont(Font.TRUETYPE_FONT, is);
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/blue_panel.png")));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image, xPos, yPos, width, height, null);
        g2.setFont(gemuno.deriveFont(Font.BOLD, 20F));
        g2.drawString(levelString, txtX, txtY);
        g2.drawRect(xPos,yPos,width,height);
    }
}