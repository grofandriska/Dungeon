package org.example.UI;

import org.example.Entity.Entity;
import org.example.Entity.Objects.consum.OBJ_Heart;
import org.example.Game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {


    BufferedImage heart3, heart2, heart1;
    GamePanel gamePanel;
    Font arial40;
    Font arial80;

    Font arial20;
    Graphics2D graphics2D;
    public String currentDialog;

    public UI(GamePanel gp) {
        this.gamePanel = gp;

        arial40 = new Font("Cambria", Font.PLAIN, 40);
        arial80 = new Font("Arial", Font.BOLD, 80);

        Entity heart = new OBJ_Heart(gamePanel);

        heart1 = heart.image;
        heart2 = heart.image2;
        heart3 = heart.image3;
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
            drawPauseState();
        }
        if (gamePanel.gameState == gamePanel.dialogState) {
            drawDialogScreen();
        }

        if (gamePanel.gameState == gamePanel.endState) {
            drawEndState();
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

    public void drawPlayerLife() {

        int x = gamePanel.tileSize / 2 + 6;
        int y = gamePanel.tileSize / 2;
        int i = 0;

        while (i < gamePanel.player.maxLife / 2) {
            graphics2D.drawImage(heart3, x, y, null);
            i++;
            x += gamePanel.tileSize;

        }

        x = gamePanel.tileSize / 2 + 6;
        y = gamePanel.tileSize / 2;
        i = 0;

        while (i < gamePanel.player.life) {
            graphics2D.drawImage(heart1, x, y, null);
            i++;
            if (i < gamePanel.player.life) {
                graphics2D.drawImage(heart3, x, y, null);
            }
            i++;
            x += gamePanel.tileSize;
        }
    }

    public void drawDamageDealt(int x, int y, int width, int height, Entity entity) {
        Color c = new Color(0, 1, 1, 140); // paint ->SOLID UI

        graphics2D.setColor(c);
        graphics2D.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);

        graphics2D.setColor(c);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.ITALIC, 40F));
        String text = "You are waked up on an island\n, you don't remember what's happened...";
        graphics2D.drawString(text, x, y);
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

    public void drawUtility(Graphics2D g) {
        drawSubWindow(20, 70, 170, 185);
        g.setFont(arial40);
        g.setColor(Color.yellow);
        g.drawString("x: " + gamePanel.player.worldX, 35, 110);
        g.drawString("y: " + gamePanel.player.worldY, 35, 145);
        g.drawString("col: " + gamePanel.player.worldX / gamePanel.tileSize, 35, 195);
        g.drawString("row: " + gamePanel.player.worldY / gamePanel.tileSize, 35, 235);
    }

    public void drawPauseState() {

        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.ITALIC, 80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gamePanel.screenWidth / 2;
        graphics2D.drawString(text, x, y);

    }

    public void drawEndState() {

        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.ITALIC, 80F));
        String text = "GAME OVER!";
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
