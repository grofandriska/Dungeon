package org.example.UI;

import org.example.Objects.OBJ_Heart;
import org.example.Objects.SuperObject;
import org.example.game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {


    BufferedImage heart3, heart2, heart1;
    GamePanel gamePanel;
    Font arial40;
    Font arial80;
    Graphics2D graphics2D;
    public String currentDialog;

    public UI(GamePanel gp) {
        this.gamePanel = gp;
        arial40 = new Font("Cambria", Font.PLAIN, 40);
        arial80 = new Font("Arial", Font.BOLD, 80);

        SuperObject heart = new OBJ_Heart(gamePanel);
        heart1 = heart.image;
        heart2 = heart.image2;
        heart3 = heart.image3;
    }

    public void drawUtility(Graphics2D g) {
        g.setFont(arial40);
        g.setColor(Color.BLACK);
        g.drawString("x: " + gamePanel.player.worldX, 35, 110);
        g.drawString("y: " + gamePanel.player.worldY, 35, 150);
    }

    public void draw(Graphics2D g) {
        this.graphics2D = g;
        g.setFont(arial40);
        g.setColor(Color.white);

        if (gamePanel.gameState == gamePanel.playState) {
            drawPlayerLife();
            drawUtility(graphics2D);

        }
        if (gamePanel.gameState == gamePanel.pauseState) {
            pauseState();
        }
        if (gamePanel.gameState == gamePanel.dialogState) {
            drawDialogScreen();
        }
    }

    public void drawPlayerLife() {

        int x = gamePanel.tileSize / 2;
        int y = gamePanel.tileSize / 2;
        int i = 0;

        while (i < gamePanel.player.maxLife / 2) {
            graphics2D.drawImage(heart3, x, y, null);
            i++;
            x += gamePanel.tileSize;

        }

        x = gamePanel.tileSize / 2;
        y = gamePanel.tileSize / 2;
        i = 0;

        while (i < gamePanel.player.life) {
            graphics2D.drawImage(heart1, x, y, null);
            i++;
            if (i < gamePanel.player.life) {
                graphics2D.drawImage(heart3, x, y, null);
            }
            i++;
            x+= gamePanel.tileSize;
        }
    }

    public void drawDialogScreen() {
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize * 2;
        int width = gamePanel.screenWidth - (gamePanel.tileSize * 4);
        int height = gamePanel.screenHeight - (gamePanel.tileSize * 9);

        drawSubWindow(x, y, width, height);

        gamePanel.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 32F));
        x += gamePanel.tileSize;
        y += gamePanel.tileSize;
        for (String line : currentDialog.split("\n")) {
            graphics2D.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 1, 1, 140); // paint ->SOLID UI
        graphics2D.setColor(c);
        graphics2D.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        graphics2D.setColor(c);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public void pauseState() {
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.ITALIC, 80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gamePanel.screenWidth / 2;
        graphics2D.drawString(text, x, y);
    }

    public int getXForCenteredText(String text) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        int x = gamePanel.screenWidth / 2 - length / 2;
        return x;

    }
}
