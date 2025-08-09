package scoreboard;

import entity.Player;
import level.LevelManager;
import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class ScoreBoard {
    public int xPos, yPos;
    public int prevX, prevY;
    public int score;
    public int movesCount = 0;
    public int bestScore;

    Player player;
    KeyHandler keyHandler;
    LevelManager levelManager;
    public ScoreBoard(Player player, KeyHandler keyHandler, LevelManager levelManager) {
        this.keyHandler = keyHandler;
        this.player = player;
        this.levelManager = levelManager;
    }

    public void updateMoves(Graphics2D g2) {
        if (keyHandler.downPressed || keyHandler.upPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if ((player.worldX != prevX) || (player.worldY != prevY)) {
                movesCount++;
            }
        }
    }

    public void updateLevel(Graphics2D g2) {

    }
}
