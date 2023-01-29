package org.example.UI;

import org.example.Entity.Entity;
import org.example.Entity.Objects.consum.OBJ_Heart;
import org.example.Game.GamePanel;
import org.example.Handler.draw.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {
    BufferedImage heart3, heart2, heart1, image;
    GamePanel gamePanel;
    Font arial40;
    Font arial80;

    Font arial20, arial10, arial20_bold;
    Graphics2D graphics2D;

    UtilityTool utilityTool = new UtilityTool();
    public String currentDialog;

    public UI(GamePanel gp) {
        this.gamePanel = gp;
        arial10 = new Font("Arial", Font.PLAIN, 10);
        arial20 = new Font("Arial", Font.PLAIN, 20);
        arial20_bold = new Font("Arial", Font.HANGING_BASELINE, 20);
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
        if (gamePanel.gameState == gamePanel.characterState) {
            drawCharacterState();
        }
    }

    private void drawCharacterState() {

        //please load it from array

        final int frameX = gamePanel.tileSize * 2;
        final int frameY = gamePanel.tileSize;
        final int frameWidth = gamePanel.tileSize * 5;
        final int frameHeight = gamePanel.tileSize * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        int textX = frameX + 20;
        int textY = frameY + gamePanel.tileSize;
        final int lineHeight = 36;

        graphics2D.setFont(arial20_bold);


        graphics2D.drawString("level ", textX, textY);
        textY += lineHeight;

        graphics2D.drawString("speed ", textX, textY);
        textY += lineHeight;

        graphics2D.drawString("max life ", textX, textY);
        textY += lineHeight;

        graphics2D.drawString("hp ", textX, textY);
        textY += lineHeight;

        graphics2D.drawString("strength ", textX, textY);
        textY += lineHeight;

        graphics2D.drawString("dexterity ", textX, textY);
        textY += lineHeight;

        graphics2D.drawString("experience ", textX, textY);
        textY += lineHeight;

        graphics2D.drawString("next level ", textX, textY);
        textY += lineHeight;

        graphics2D.drawString("coins ", textX, textY);
        textY += lineHeight;


        //values
        int tailX = (frameX + frameWidth) - 30;
        textY = frameY + gamePanel.tileSize;

        String value;

        value = String.valueOf(gamePanel.player.level);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.player.speed);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.player.maxLife);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.player.life);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.player.strength);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.player.dexterity);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.player.exp);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.player.nextLevelExp);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value,textX,textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.player.coi);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value,textX,textY);
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

        int x = 10;
        int y = gamePanel.tileSize * gamePanel.maxScreenRow - 50;
        int i = 0;

        while (i < gamePanel.player.maxLife / 2) {
            graphics2D.drawImage(heart3, x, y, null);
            i++;
            x += gamePanel.tileSize;

        }

        x = 10;
        y = gamePanel.tileSize * gamePanel.maxScreenRow - 50;
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
        /* drawSubWindow(20, 70, 170, 185);*/
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + "Road Hori" + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //background
        Color c = new Color(0, 1, 1, 140);
        graphics2D.setColor(c);
        graphics2D.fillRoundRect(0, 10, 900, 40, 10, 10);

        for (int i = 0; i < 50; i++) {
            graphics2D.drawImage(image, i * 30, 5, null);
        }

        g.setFont(arial20);
        g.setColor(Color.yellow);
        g.drawString("x: " + gamePanel.player.worldX, 5, 28);
        g.drawString("y: " + gamePanel.player.worldY, 85, 28);
        g.drawString("col: " + gamePanel.player.worldX / gamePanel.tileSize, 630, 28);
        g.drawString("row: " + gamePanel.player.worldY / gamePanel.tileSize, 700, 28);

        drawPlayerLife();
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

    public int getXForRightAlignedText(String text, int tailX) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        int x = tailX - length;
        return x;

    }
}
