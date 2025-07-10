package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[100];
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
    }

    public void getTileImage() {
        try {
            //wall
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/concrete.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
