package main;

import object.OBJ_Box;
import object.OBJ_Orb;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setBoxObject() {
        // Set Box
        gamePanel.BSObject[0] = new OBJ_Box();
        gamePanel.BSObject[0].name = "box1";
        gamePanel.BSObject[0].worldX = 9 * gamePanel.tileSize;
        gamePanel.BSObject[0].worldY = 10 * gamePanel.tileSize;

        gamePanel.BSObject[1] = new OBJ_Box();
        gamePanel.BSObject[1].name = "box1";
        gamePanel.BSObject[1].worldX = 6 * gamePanel.tileSize;
        gamePanel.BSObject[1].worldY = 10 * gamePanel.tileSize;

        for (OBJ_Box box: gamePanel.BSObject) {
            if (box == null) return;
            gamePanel.boxCount++;
        }
//        gamePanel.BSObject[2] = new OBJ_Box();
//        gamePanel.BSObject[2].worldX = 8 * gamePanel.tileSize;
//        gamePanel.BSObject[2].worldY = 9 * gamePanel.tileSize;
    }

    public void setOrbObject() {
        gamePanel.OSObject[0] = new OBJ_Orb();
        gamePanel.OSObject[0].worldX = 12 * gamePanel.tileSize;
        gamePanel.OSObject[0].worldY = 6 * gamePanel.tileSize;

        gamePanel.OSObject[1] = new OBJ_Orb();
        gamePanel.OSObject[1].worldX = 12 * gamePanel.tileSize;
        gamePanel.OSObject[1].worldY = 7 * gamePanel.tileSize;
    }
}
