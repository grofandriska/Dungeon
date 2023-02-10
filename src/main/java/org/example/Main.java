package org.example;

import org.example.Game.GamePanel;

import javax.swing.*;

public class Main {

    public static JFrame window = new JFrame();
    public static void main(String[] args) {

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Dungeon Adventures : Untold Story");
        window.setUndecorated(true);

        GamePanel gamePanel = new GamePanel();


        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();

        gamePanel.startGameThread();
    }
}