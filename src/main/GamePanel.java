package main;

import entity.Player;
import level.Level;
import level.LevelManager;
import object.OBJ_Box;
import object.OBJ_Orb;
import scoreboard.ScoreBoard;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.time.Clock;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 32; // The tiles and sprites are all 32x32px by default
    final int scale = 1; //  scale it 2x to look bigger; 64px on the screen; Ket iremove na pero you can change the value to look bigger on screen, but you'll be needing to configure the widths and heights of some sprites to not cause any error
    public final int tileSize = originalTileSize * scale; // scale to look bigger on the screen/panel

    public  boolean mainState = true;
    public boolean levelState = false;
    public  boolean gameState = false;
    public  boolean pauseState = false;

    // For the Panel Window
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 15;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // World settings
    public final int maxWorldCol = 20;
    public final int maxWorldRow = 15;

    private final int FPS = 12;

    KeyHandler keyHandler = new KeyHandler();
    MouseHandler mouseHandler = new MouseHandler();
    public UI ui = new UI(this);
    TileManager tileManager = new TileManager(this);
    public LevelManager levelManager = new LevelManager(this, mouseHandler, tileManager);
    Thread gameThread;
    public CollisionChecker oChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public Player player = new Player(this, keyHandler);
    public ScoreBoard scoreBoard = new ScoreBoard(player, keyHandler, levelManager, this);


    // For Boxes Objects
    public OBJ_Box[] BSObject = new OBJ_Box[10];

    // For Orbs Objects
    public OBJ_Orb[] OSObject = new OBJ_Orb[10];
    
    // GamePanel Constructor
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addMouseListener(mouseHandler);
        this.setFocusable(true);
    }
    public void setObject() {
        assetSetter.setBoxObject();
        assetSetter.setOrbObject();
    }

    // To start the Threading
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval =  1000000000.0 / FPS; // 0,01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint(); // Calls the paintComponent();
                delta--;
            }
        }
    }

    public void update() {
        if (gameState) {
            oChecker.check();
            player.update();
        }
    }

//    public void sleep() {
//        double time = 0;
//        while (true) {
//            time += 0.1;
//            if (time >= 100000000)
//                break;
//        }
//    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (!gameState) {
            ui.draw(g2);
            if (levelState) levelManager.onSelectLevel();
        }
        else {
            tileManager.draw(g2);

            // Draw each Orb Objects
            for (OBJ_Orb orb: OSObject) {
                if (orb != null) {
                    orb.draw(g2, this);
                }
            }
            // Draw each Box Objects
            for (OBJ_Box box : BSObject) {
                if (box != null) {
                    box.draw(g2, this);
                }
            }
            player.draw(g2);
            scoreBoard.draw(g2);
        }
        g2.dispose(); // to save some memory
    }
}
