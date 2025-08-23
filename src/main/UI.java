package main;

import button.Button;
import level.Level;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.Objects;

public class UI {
    public Font gemuno, luckiest_guy;
    BufferedImage image, button1, button2, button3;
    GamePanel gamePanel;
    Graphics2D g2;
    Button playButton = null, levelsButton = null, backButton = null, backMenuButton = null, exitButton = null, homeButton = null, nextButton = null;

    int xPos, yPos, buttonX, playButtonY, levelsButtonY;
    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        buttonX = (gamePanel.screenWidth/2 - 100);
        playButtonY = gamePanel.tileSize*8;
        levelsButtonY = gamePanel.tileSize*10;

        try {
            InputStream is = getClass().getResourceAsStream("/fonts/gemuno.ttf");
            assert is != null;
            gemuno = Font.createFont(Font.TRUETYPE_FONT, is);

            is = getClass().getResourceAsStream("/fonts/LuckiestGuy-Regular.ttf");
            assert is != null;
            luckiest_guy = Font.createFont(Font.TRUETYPE_FONT, is);

            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/Purple.png")));
            button1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/blue_button00.png")));
            button2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/yellow_button00.png")));
            button3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/green_button00.png")));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        playButton = new Button("play", button1, 200, 50, buttonX, playButtonY);
        levelsButton = new Button("levels", button2, 200, 50, buttonX, levelsButtonY);
        backButton = new Button("back", button3, 60, 40, 40, 10);//Change later don't hardcode para maging relative nalng posiiton instead na fixed.
        backMenuButton = new Button("back", button3, 60, 40, 40, 10);
        exitButton = new Button("exit", button2, 200, 50, buttonX, playButtonY);
        homeButton = new Button("home", button2, 200, 50, buttonX, playButtonY);
        nextButton = new Button("next", button3, 200, 50, buttonX, levelsButtonY);

        if (gamePanel.mainState) {
            // I don't know where to put this, should I put it here or in the UI class.....
            if (levelsButton.mouseOnButton(gamePanel.mouseHandler) && gamePanel.mainState) {
                gamePanel.levelState = true;
                gamePanel.mainState = false;
            }

            if (playButton.mouseOnButton(gamePanel.mouseHandler) && gamePanel.mainState) {
                gamePanel.mainState = false;
                gamePanel.gameState = true;
            } else
                drawMainScreen();
        }
        else if (gamePanel.levelState) {

            if (backButton.mouseOnButton(gamePanel.mouseHandler) && gamePanel.levelState) {
                gamePanel.levelState = false;
                gamePanel.mainState = true;
            } else
                drawLevelsScreen();
        } else if (gamePanel.pauseState) {

            if (backMenuButton.mouseOnButton(gamePanel.mouseHandler)) {
                gamePanel.pauseState = false;
                gamePanel.gameState = true;
            } else if (exitButton.mouseOnButton(gamePanel.mouseHandler)) {
                gamePanel.pauseState = false;
                gamePanel.mainState = true;
            } else
                drawMainMenu();
        } else if (gamePanel.winState) {
            if (homeButton.mouseOnButton(gamePanel.mouseHandler)) {
                gamePanel.winState = false;
                gamePanel.mainState = true;
            } else
                drawWinScreen();
        }
    }

    public void drawMainScreen() {
        drawBackground();

        g2.setFont(luckiest_guy.deriveFont(Font.BOLD, 60F));

        String titleText = "Project Sokoban";
        xPos = getXOfText(titleText);
        yPos= gamePanel.tileSize*5;

        g2.setColor(Color.DARK_GRAY);
        g2.drawString(titleText, xPos, yPos);
        g2.setFont(gemuno.deriveFont(Font.BOLD, 40F));

        String playString = "Play";
        String levelString = "Levels";
        playButton.draw(g2);

        levelsButton.draw(g2);

        xPos = getXOfText(playString);
        yPos = playButtonY + 32;

        g2.setColor(Color.DARK_GRAY);
        g2.drawString(playString, xPos, yPos);

        xPos = getXOfText(levelString);
        yPos = levelsButtonY + 32;

        g2.setColor(Color.DARK_GRAY);
        g2.drawString(levelString, xPos, yPos);
    }



    public void drawLevelsScreen() {
        drawBackground();

        String levelText = "Levels";

        g2.setFont(gemuno.deriveFont(Font.BOLD, 48F));
        g2.setColor(Color.DARK_GRAY);

        xPos = getXOfText(levelText);
        yPos = gamePanel.tileSize+15;

        g2.drawString(levelText, xPos, yPos);

        backButton.draw(g2);

        String backString = "Back";

        g2.setFont(gemuno.deriveFont(Font.BOLD, 18F));
        g2.setColor(Color.DARK_GRAY);

        xPos = backButton.width/2 + backButton.buttonX / 2;
        yPos = backButton.height - backButton.buttonY / 2;

        g2.drawString(backString, xPos, yPos);
        for(Level level: gamePanel.levelManager.levels) {
            level.draw(g2);
        }
    }

    public void drawMainMenu() {
        drawBackground();

        // Back Button
        String backText = "back";

        backMenuButton.draw(g2);

        g2.setFont(gemuno.deriveFont(Font.BOLD, 18F));

        g2.setColor(Color.DARK_GRAY);

        xPos = backButton.width/2 + backButton.buttonX / 2;
        yPos = backButton.height - backButton.buttonY / 2;
        g2.drawString(backText, xPos, yPos);

        // Main Menu text
        String mainMenuText = "Main Menu";

        g2.setFont(luckiest_guy.deriveFont(Font.BOLD, 59F));
        xPos = getXOfText(mainMenuText);
        yPos = gamePanel.screenHeight / 2 - mainMenuText.length();
        g2.drawString(mainMenuText, xPos, yPos);

        // Exit Button
        String exitText = "Exit";

        g2.setFont(gemuno.deriveFont(Font.BOLD, 30F));
        xPos = getXOfText(exitText);
        yPos = playButtonY + 32;
        exitButton.draw(g2);
        g2.drawString(exitText, xPos, yPos);
    }

    public void drawWinScreen() {
        drawBackground();
        String winText = "Goodjob!";

        g2.setFont(luckiest_guy.deriveFont(Font.BOLD, 90F));
        xPos = getXOfText(winText);
        yPos = gamePanel.tileSize * 6;
        g2.drawString(winText, xPos, yPos);

        // Home Button
        String homeText = "home";
        homeButton.draw(g2);
        g2.setFont(gemuno.deriveFont(Font.BOLD, 40F));
        xPos = getXOfText(homeText);
        yPos = playButtonY + gamePanel.tileSize;
        g2.drawString(homeText, xPos, yPos);

        // Next Button
        String nextText = "next";
        nextButton.draw(g2);
        xPos = getXOfText(nextText);
        yPos = levelsButtonY + gamePanel.tileSize;
        g2.drawString(nextText, xPos, yPos);
    }

    public void drawBackground() {
        for (int i = 0; i < gamePanel.maxWorldCol; i++)
            for (int k = 0; k < gamePanel.maxWorldRow; k++) {
                g2.drawImage(image, (i*gamePanel.tileSize), (k*gamePanel.tileSize), gamePanel.tileSize, gamePanel.tileSize, null);
            }
    }

    public int getXOfText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gamePanel.screenWidth / 2 - length / 2;
    }

}
