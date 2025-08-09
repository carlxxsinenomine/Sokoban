package level;

import main.GamePanel;
import main.MouseHandler;

public class LevelManager {
    public Level[] levels;
    GamePanel gamePanel;
    MouseHandler mouseHandler;
    int levelCount = 1;

    public LevelManager(GamePanel gamePanel, MouseHandler mouseHandler) {
        this.gamePanel = gamePanel;
        this.mouseHandler = mouseHandler;
        levels = new Level[45]; // Ayusin nalng latur para hinde naka hardcode ang max levels; Read from file.
        loadLevels();
    }

    public void loadLevels() {
        int levelIndex = 0;
        for (int k = gamePanel.tileSize + 5; k*2+50 < gamePanel.screenHeight; k+=gamePanel.tileSize + 5) {
            for (int i = 20; i*2+50 < gamePanel.screenWidth; i+=gamePanel.tileSize) {
                levels[levelIndex] = new Level();
                levels[levelIndex].levelNumber = levelCount;
                levels[levelIndex].levelString = String.valueOf(levelCount);
                levels[levelIndex].xPos = i * 2;
                levels[levelIndex].yPos = k * 2;
                levels[levelIndex].txtX = (levels[levelIndex].xPos + (levels[levelIndex].width/2)) - levels[levelIndex].levelString.length() * 5;
                levels[levelIndex].txtY = (levels[levelIndex].yPos) + (levels[levelIndex].height/2) + 5;
                levelIndex++;
                levelCount++;
            }
        }
    }

    public Level mouseCursorOnTile() {
        for (Level level: levels) {
            // Check if the mouse's coordinates are on  the same coordinates of level tiles
            if (mouseHandler.mouseY >= level.yPos &&
                mouseHandler.mouseY <= level.yPos + level.height &&
                mouseHandler.mouseX >= level.xPos &&
                mouseHandler.mouseX <= level.xPos + level.width) {
                // Debug this shit
//                System.out.println(level.levelNumber);
                return level; // I-return the current level;
            }
        }
        return null;
    }
}
