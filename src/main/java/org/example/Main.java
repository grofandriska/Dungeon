package org.example;

import org.example.Display.GamePanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Initializing JFrame");
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Dungeon Adventures : Untold Story");
        window.setResizable(false);

        System.out.println("Initializing GamePanel");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        System.out.println("Starting Game!");
        gamePanel.setupAsset();

        gamePanel.startGameThread();
    }
}