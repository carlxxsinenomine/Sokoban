/**
 * <h1>
 * The codes written here are ugly as fuck but who cares? I don't even know why im using HTML tags, IntelliJ just recommended it to me.
 * </h1>
 * <p>
 * I could put each logic on the same method, but I,ve decided to separate it for easy debugging.
 * </p>
 */



package main;

import entity.Entity;
import object.OBJ_Box;
import object.OBJ_Orb;

public class CollisionChecker {

    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {this.gamePanel = gamePanel;}

    public void checkTile(Entity entity) {
        int entityWX = entity.worldX;
        int entityWY = entity.worldY;

        int entityCol = entityWX/gamePanel.tileSize;
        int entityRow = entityWY/gamePanel.tileSize;

        int tileNum;

        switch (entity.direction) {
            case "up" -> {
                entityRow = (entityWY - entity.speed) / gamePanel.tileSize; //10.6
                tileNum = gamePanel.tileManager.mapTileNum[entityCol][entityRow];
                if (gamePanel.tileManager.tile[tileNum].collision)
                    entity.collisionOn = true;
            }
            case "down" -> {
                entityRow = (entityWY + entity.speed) / gamePanel.tileSize;
                tileNum = gamePanel.tileManager.mapTileNum[entityCol][entityRow];
                if (gamePanel.tileManager.tile[tileNum].collision)
                    entity.collisionOn = true;
            }
            case "left" -> {
                entityCol = (entityWX - entity.speed) / gamePanel.tileSize;
                tileNum = gamePanel.tileManager.mapTileNum[entityCol][entityRow];
                if (gamePanel.tileManager.tile[tileNum].collision)
                    entity.collisionOn = true;
            }
            case "right" -> {
                entityCol = (entityWX + entity.speed) / gamePanel.tileSize;
                tileNum = gamePanel.tileManager.mapTileNum[entityCol][entityRow];
                if (gamePanel.tileManager.tile[tileNum].collision)
                    entity.collisionOn = true;
            }
        }
    }

    // return nalng yung index nung obj then store sa Player class then gawa paraan para mas efficient yung pag detect ng collision sa object then if movable ba ito
    public int checkObject(Entity entity) {
        int index = 999;
        for (int i = 0; i < gamePanel.BSObject.length; i++) {
            if (gamePanel.BSObject[i] != null) {
                int entityWX = entity.worldX;
                int entityWY = entity.worldY;

                int entityCol = entityWX/gamePanel.tileSize;
                int entityRow = entityWY/gamePanel.tileSize;

                int objectCol = gamePanel.BSObject[i].worldX/gamePanel.tileSize;
                int objectRow = gamePanel.BSObject[i].worldY/gamePanel.tileSize;
                // modify later para hindi direvtang chinichange world coordinates
                switch (entity.direction) {
                    case "up" -> {
                        entityRow = (entityWY - entity.speed) / gamePanel.tileSize;
                        if (objectCol == entityCol && objectRow == entityRow && gamePanel.BSObject[i].collision) {// change this trash
                            entity.collisionOn = true;
                            return i;
                        }
                    }
                    case "down" -> {
                        entityRow = (entityWY + entity.speed) / gamePanel.tileSize;
                        if (objectCol == entityCol && objectRow == entityRow && gamePanel.BSObject[i].collision) { //change this
                            entity.collisionOn = true;
                            return i;
                        }
                    }
                    case "left" -> {
                        entityCol = (entityWX - entity.speed) / gamePanel.tileSize;
                        if (objectCol == entityCol && objectRow == entityRow && gamePanel.BSObject[i].collision) {//change this
                            entity.collisionOn = true;
                            return i;
                        }
                    }
                    case "right" -> {
                        entityCol = (entityWX + entity.speed) / gamePanel.tileSize;
                        if (objectCol == entityCol && objectRow == entityRow && gamePanel.BSObject[i].collision) {//change this
                            entity.collisionOn = true;
                            return i;
                        }
                    }
                }
            }
        }
        return index;
    }

    public void boxToWall(Entity entity) {
        for (int i = 0; i < gamePanel.BSObject.length; i++) { // I could use the foreach but nnahhhh maybe nxt time
            if (gamePanel.BSObject[i] != null) {
                int tileNum;
                int objectCol = gamePanel.BSObject[i].worldX / gamePanel.tileSize;
                int objectRow = gamePanel.BSObject[i].worldY / gamePanel.tileSize;
                switch (entity.direction) {
                    case "up" -> {
                        objectRow = (gamePanel.BSObject[i].worldY - entity.speed) / gamePanel.tileSize;
                        tileNum = gamePanel.tileManager.mapTileNum[objectCol][objectRow];
                        if (gamePanel.tileManager.tile[tileNum].collision)
                            gamePanel.BSObject[i].collisionOn = true;
                    }
                    case "down" -> {
                        objectRow = (gamePanel.BSObject[i].worldY + entity.speed) / gamePanel.tileSize;
                        tileNum = gamePanel.tileManager.mapTileNum[objectCol][objectRow];
                        if (gamePanel.tileManager.tile[tileNum].collision)
                            gamePanel.BSObject[i].collisionOn = true;
                    }
                    case "left" -> {
                        objectCol = (gamePanel.BSObject[i].worldX - entity.speed) / gamePanel.tileSize;
                        tileNum = gamePanel.tileManager.mapTileNum[objectCol][objectRow];
                        if (gamePanel.tileManager.tile[tileNum].collision)
                            gamePanel.BSObject[i].collisionOn = true;
                    }
                    case "right" -> {
                        objectCol = (gamePanel.BSObject[i].worldX + entity.speed) / gamePanel.tileSize;
                        tileNum = gamePanel.tileManager.mapTileNum[objectCol][objectRow];
                        if (gamePanel.tileManager.tile[tileNum].collision)
                            gamePanel.BSObject[i].collisionOn = true;
                    }
                }
            }
        }
    }

    public void boxToBox(Entity entity) {
        // @objective: to check if a box collides with another box object

        // get hold of the box the user has collision on
        // get the box's coordinates
        // check whether the box's preceding coordinates has a box object base on the box's direction
        for (OBJ_Box box: gamePanel.BSObject) {
            if (box != null) {
                int boxX = box.worldX;
                int boxY = box.worldY;
                switch (entity.direction) {
                    case "up" -> {
                        boxY -= box.speed;
                        if (isBoxFront(boxX, boxY))
                            box.collisionOn = true;
                    }
                    case "down" -> {
                        boxY += box.speed;
                        if (isBoxFront(boxX, boxY))
                            box.collisionOn = true;
                    }
                    case "left" -> {
                        boxX -= box.speed;
                        if (isBoxFront(boxX, boxY))
                            box.collisionOn = true;
                    }
                    case "right" -> {
                        boxX += box.speed;
                        if (isBoxFront(boxX, boxY))
                            box.collisionOn = true;
                    }
                }
            }
        }
    }

    public void check() {
        for (OBJ_Box box: gamePanel.BSObject) {
            if (box != null) {
                for (OBJ_Orb orb: gamePanel.OSObject) {
                    if (orb != null) {
                        int bCol = box.worldX / gamePanel.tileSize;
                        int bRow = box.worldY / gamePanel.tileSize;
                        int oCol = orb.worldX / gamePanel.tileSize;
                        int oRow = orb.worldY / gamePanel.tileSize;

                        if (bCol == oCol && bRow == oRow) {//might try the intersect() later but for now this method works so imma keep it as it is
                            box.changeState();
                            box.isMovable = false;
                        }
                    }
                }
            }
        }
    }

    public boolean isBoxFront(int x, int y) {
        for (OBJ_Box box: gamePanel.BSObject) {
            if (box != null) {
                if (box.worldX == x && box.worldY == y)
                    return true;
                }
            }
        return false;
    }
}
