package main;

import javax.swing.*;

public class Main extends Object {
    public static void main(String[] arguments) {
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Project Sokoban");

        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setObject(); // Set the objects first b4 starting the thread
        gamePanel.startGameThread();
    }
}
