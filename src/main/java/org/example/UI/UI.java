package org.example.UI;

import org.example.Entity.Entity;
import org.example.Entity.Objects.consum.OBJ_Heart;
import org.example.Game.GamePanel;
import org.example.Handler.draw.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class UI {
    BufferedImage heart3, heart2, heart1, image;
    GamePanel gamePanel;
    Font cambria40;
    Font arial80;

    Font arial20, arial10, arial20_bold, arial21, gabriola20;
    Graphics2D graphics2D;

    UtilityTool utilityTool = new UtilityTool();

    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public String currentDialog;

    public int slotCol = 0, slotRow = 0;

    public UI(GamePanel gp) {
        this.gamePanel = gp;
        arial10 = new Font("Arial", Font.PLAIN, 10);
        arial21 = new Font("Arial", Font.PLAIN, 21);
        arial20_bold = new Font("Arial", Font.HANGING_BASELINE, 20);
        gabriola20 = new Font("Gabriola", Font.PLAIN, 24);
        cambria40 = new Font("Cambria", Font.PLAIN, 40);
        arial80 = new Font("Arial", Font.BOLD, 80);

        Entity heart = new OBJ_Heart(gamePanel);

        heart1 = heart.image;
        heart2 = heart.image2;
        heart3 = heart.image3;
    }

    public void draw(Graphics2D g) {
        this.graphics2D = g;
        g.setFont(cambria40);
        g.setColor(Color.white);

        if (gamePanel.gameState == gamePanel.playState) {
            drawUtility(graphics2D);
            drawScrollMessage();

        }
        else if (gamePanel.gameState == gamePanel.pauseState) {
            drawPauseState();
        }
       else if (gamePanel.gameState == gamePanel.dialogState) {
            drawDialogScreen();
        }

        else if (gamePanel.gameState == gamePanel.endState) {
            drawEndState();
        }
        else if (gamePanel.gameState == gamePanel.characterState) {
            drawCharacterState();
            drawInventory();
        }
    }

    public void drawPlayerSolidArea() {
        int screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        int screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);
        graphics2D.fillRect(screenX + gamePanel.player.solidAreaDefaultX, screenY + gamePanel.player.solidAreaDefaultY, gamePanel.player.solidArea.width, gamePanel.player.solidArea.height);
    }

    private void drawInventory() {

        // frame
        int frameX = gamePanel.tileSize * 9;
        int frameY = gamePanel.tileSize;
        int frameHeight = gamePanel.tileSize * 5;
        int frameWidth = gamePanel.tileSize * 6;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // Slot
        int slotX, slotY;

        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;

        // used in drawing items
        slotX = slotXStart;
        slotY = slotYStart;

        // cursor
        int cursorX = slotXStart + (gamePanel.tileSize * slotCol);
        int cursorY = slotYStart + (gamePanel.tileSize * slotRow);

        int cursorWidth = gamePanel.tileSize;
        int cursorHeight = gamePanel.tileSize;

        graphics2D.setColor(Color.cyan);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        // draw playerItems
        for (int i = 0; i < gamePanel.player.inventory.size(); i++) {

            //if equiped
            if (gamePanel.player.currentShield.equals(gamePanel.player.inventory.get(i)) || gamePanel.player.currentWeapon.equals(gamePanel.player.inventory.get(i))) {
                graphics2D.setColor(new Color(240, 190, 90, 75));
                graphics2D.fillRoundRect(slotX + 2, slotY + 2, gamePanel.tileSize - 4, gamePanel.tileSize - 4, 10, 10);
            }
            graphics2D.drawImage(gamePanel.player.inventory.get(i).image, slotX, slotY, null);

            slotX += gamePanel.tileSize;

            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXStart;
                slotY += gamePanel.tileSize;
            }
        }

        // drawing descriptionArea for inventory

        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gamePanel.tileSize * 3;

        //draw description

        int textX = dFrameX + 10;
        int textY = dFrameY + gamePanel.tileSize / 2;

        graphics2D.setFont(gabriola20);

        // Breaking description String to separate lines
        int itemIndex = getItemIndexFromSlot();
        if (itemIndex < gamePanel.player.inventory.size()) {
            //draw desc. only if item selected
            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
            for (String line : gamePanel.player.inventory.get(itemIndex).description.split("\n")) {
                graphics2D.drawString(line, textX, textY);
                textY += 20;
            }
        }
    }

    public int getItemIndexFromSlot() {
        return slotCol + (slotRow * 5);
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

    private void drawScrollMessage() {
        gamePanel.setFont(arial20_bold);

        int messageX = gamePanel.tileSize * 11;
        int messageY = (gamePanel.tileSize * 11) + 12;


        for (int i = 0; i < message.size(); i++) {

            if (message.get(i) != null) {
                //draw background

                graphics2D.setColor(new Color(70, 70, 70, 140));
                graphics2D.fillRoundRect(messageX - 10, messageY - 16, 400, 20, 35, 35);


                //draw from message and it's shadow
                graphics2D.setFont(new Font("Gabriola", Font.PLAIN, 20));
                graphics2D.setColor(Color.darkGray);
                graphics2D.drawString(message.get(i), messageX + 1, messageY - 1);

                graphics2D.setColor(Color.gray);
                graphics2D.drawString(message.get(i), messageX, messageY);

                // messageCounter++
                int counter = messageCounter.get(i) + 1;

                //set counter to the array
                messageCounter.set(i, counter);

                //set to nextLine
                messageY += 24;

                // if more than 2 lines, delete previous old
                if (message.size() > 2) {
                    message.remove(0);
                }
                if (messageCounter.get(i) > 120) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    private void drawCharacterState() {

        //please load it from array

        final int frameX = gamePanel.tileSize * 2;
        final int frameY = gamePanel.tileSize;
        final int frameWidth = gamePanel.tileSize * 5;
        final int frameHeight = gamePanel.tileSize * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        int textX = frameX + 28;
        int textY = frameY + 36;
        final int lineHeight = 18;


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
        graphics2D.drawString("attack ", textX, textY);
        textY += lineHeight;

        graphics2D.drawImage(gamePanel.player.right1, frameX + 20, textY, null);
        //values
        int tailX = (frameX + frameWidth) - 30;
        textY = frameY + 36;

        String value;

        value = String.valueOf(gamePanel.player.level);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.speed);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.player.maxLife);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.player.life);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.player.strength);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.player.dexterity);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.player.exp);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.player.nextLevelExp);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.player.coi);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.player.attack);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;


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
        int y = gamePanel.tileSize * gamePanel.maxScreenRow - 55;
        int i = 0;

        while (i < gamePanel.player.maxLife / 2) {
            Color c = new Color(0, 1, 1, 140);
            graphics2D.setColor(c);
            graphics2D.fillRoundRect(x + 13, y + gamePanel.tileSize - 10, 16, 8, 10, 10);
            graphics2D.drawImage(heart3, x, y, null);
            i++;
            x += gamePanel.tileSize;

        }

        x = 10;
        y = gamePanel.tileSize * gamePanel.maxScreenRow - 55;
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

    public void drawUtility(Graphics2D g) {
        /* drawSubWindow(20, 70, 170, 185);*/
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + "row3" + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //background
        Color c = new Color(0, 1, 1, 140);
        graphics2D.setColor(c);
        graphics2D.fillRoundRect(-10, 10, 900, 30, 10, 10);

        for (int i = 0; i < 10; i++) {
            graphics2D.drawImage(utilityTool.scaleImage(image, gamePanel.tileSize * 2, gamePanel.tileSize + 8), i * 95, 0, null);
            graphics2D.drawImage(utilityTool.scaleImage(image, gamePanel.tileSize * 2, gamePanel.tileSize + 8), i * 95, 520, null);
        }

        g.setFont(gabriola20);
        g.setColor(Color.darkGray);
        g.drawString("x: " + gamePanel.player.worldX, 5, 31);
        g.drawString("y: " + gamePanel.player.worldY, 85, 31);
        g.drawString("col: " + gamePanel.player.worldX / gamePanel.tileSize, 630, 31);
        g.drawString("row: " + gamePanel.player.worldY / gamePanel.tileSize, 700, 31);

        g.setFont(gabriola20);
        g.setColor(Color.gray);
        g.drawString("x: " + gamePanel.player.worldX, 4, 33);
        g.drawString("y: " + gamePanel.player.worldY, 84, 33);
        g.drawString("col: " + gamePanel.player.worldX / gamePanel.tileSize, 629, 31);
        g.drawString("row: " + gamePanel.player.worldY / gamePanel.tileSize, 699, 31);

        drawPlayerLife();
    }

    public void drawPauseState() {

        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.ITALIC, 80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gamePanel.screenWidth / 2;
        graphics2D.drawString(text, x, y);

    }

    // for scrollMessage
    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
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
