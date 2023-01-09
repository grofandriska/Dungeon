package org.example;

import org.example.Objects.OBJ_Key;
import org.example.game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gamePanel;
    Font arial40;
    Font arial80;
    BufferedImage demoImage;

    public boolean isGameFinished = false;
    public boolean messageOn = false;
    public String message;

    double playtime = 0;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    int messageCount = 0;

    public UI(GamePanel gp) {
        this.gamePanel = gp;
        arial40 = new Font("Arial", Font.PLAIN, 40);
        arial80 = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key(gamePanel);
        demoImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g) {

        g.setFont(arial40);
        g.setColor(Color.WHITE);
        g.drawImage(demoImage, gamePanel.tileSize / 2, gamePanel.tileSize / 2, null);
        g.drawString("Dungeon Adventures", 50, 60);
        g.drawString("x: " + gamePanel.player.worldX, 52, 110);
        g.drawString("y: " + gamePanel.player.worldY, 54, 150);
        g.drawString("Time: " + decimalFormat.format(playtime)  , 54, 195);

        if (isGameFinished) {

            int textLength;
            int x;
            int y;
            g.setColor(Color.YELLOW);

            String text = "You won the game !";
            textLength = ((int) g.getFontMetrics().getStringBounds(text, g).getWidth());
            x = gamePanel.screenWidth / 2 - textLength / 2;
            y = gamePanel.screenHeight / 2 - textLength / 3;
            g.drawString(text, x, y);

            text = "YOU ARE AWSOME!";
            x = gamePanel.screenWidth / 3 - textLength / 2;
            y = gamePanel.screenHeight / 2 + (gamePanel.tileSize * 3);
            g.drawString(text, x, y);
            gamePanel.gameThread = null;
        }
        if (messageOn) {
            g.setFont(g.getFont().deriveFont(30F));
            g.drawString(message, gamePanel.tileSize * 6, gamePanel.tileSize * 4);
            messageCount++;
            if (messageCount > 40) {
                messageCount = 0;
                messageOn = false;
            }
        }
        playtime += (double) 10 / 120; // 1 secondDish :)
    }
}
