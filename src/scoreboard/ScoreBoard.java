package scoreboard;

import entity.Player;
import level.LevelManager;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ScoreBoard {
    public int xPos, yPos;
    public int prevX, prevY;
    public int score;
    public int movesCount = 0;
    public int bestScore;
    public String currentLevel;

    public Font gemuno, luckiest_guy;
    Player player;
    KeyHandler keyHandler;
    LevelManager levelManager;
    GamePanel gamePanel;
    public ScoreBoard(Player player, KeyHandler keyHandler, LevelManager levelManager, GamePanel gamePanel) {
        this.keyHandler = keyHandler;
        this.player = player;
        this.levelManager = levelManager;
        this.gamePanel = gamePanel;

        try {
            InputStream is = getClass().getResourceAsStream("/fonts/gemuno.ttf");
            assert is != null;
            gemuno = Font.createFont(Font.TRUETYPE_FONT, is);

            is = getClass().getResourceAsStream("/fonts/LuckiestGuy-Regular.ttf");
            assert is != null;
            luckiest_guy = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Change so that the movements of the box are the ones should be counted.
    public int updateMoves() {
        if (keyHandler.downPressed || keyHandler.upPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if ((player.worldX != prevX) || (player.worldY != prevY)) {
                return movesCount++;
            }
        }
        return movesCount;
    }

    public void draw(Graphics2D g2) {
        String  levelText = "Level: ";
        String moves = "Moves: ";
        String bestMoves = "Best: ";

        g2.setFont(gemuno.deriveFont(Font.BOLD, 30F));

        xPos = 16;
        yPos = gamePanel.tileSize;

        g2.drawString(levelText, xPos, yPos);
        g2.drawString(currentLevel, xPos*7, yPos);


        g2.drawString(moves, xPos, yPos*2);
        g2.drawString(String.valueOf(updateMoves()), xPos*7, yPos*2);


        g2.drawString(bestMoves, xPos, yPos*3);
    }
}
