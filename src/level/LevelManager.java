package level;

import main.GamePanel;
import main.MouseHandler;
import tile.TileManager;

public class LevelManager {
    public Level[] levels;
    GamePanel gamePanel;
    MouseHandler mouseHandler;
    TileManager tileManager;
    int levelCount = 1;



    public LevelManager(GamePanel gamePanel, MouseHandler mouseHandler, TileManager tileManager) {
        this.gamePanel = gamePanel;
        this.mouseHandler = mouseHandler;
        this.tileManager = tileManager;
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

    // So here's what im thinking, im considering renaming this method chooseLevel() then call the loadMap() from the
    // TileManager class
    public void onSelectLevel() {
        for (Level level: levels) {
            // Check if the mouse's coordinates are on  the same coordinates of level tiles
            if (mouseHandler.mouseY >= level.yPos &&
                mouseHandler.mouseY <= level.yPos + level.height &&
                mouseHandler.mouseX >= level.xPos &&
                mouseHandler.mouseX <= level.xPos + level.width) {
                System.out.println(level.levelString);
//                mouseHandler.mouseX = mouseHandler.mouseY = 0;
                gamePanel.levelState = false;
                gamePanel.gameState = true;

                tileManager.loadMap("/maps/" + level.levelString + ".txt");
            }
        }
    }
}
