package org.example.UI;

import org.example.entity.Entity;
import org.example.entity.objects.consum.OBJ_Heart;
import org.example.Game.GamePanel;
import org.example.Handler.draw.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class UserInreface {
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

    public UserInreface(GamePanel gp) {
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

        if (gamePanel.getGameState() == gamePanel.getPlayState()) {
            drawUtility(graphics2D);
            drawScrollMessage();

        } else if (gamePanel.getGameState() == gamePanel.getPauseState()) {
            drawPauseState();
        } else if (gamePanel.getGameState() == gamePanel.getDialogState()) {
            drawDialogScreen();
        } else if (gamePanel.getGameState() == gamePanel.getEndState()) {
            drawEndState();
        } else if (gamePanel.getGameState() == gamePanel.getCharacterState()) {
            drawCharacterState();
            drawInventory();
        }
    }

    public void selectEntity(int x, int y) {
        for (int i = 0; i < gamePanel.getMonsters().length; i++) {
            System.out.println(i);
            System.out.println(gamePanel.getMonsters()[i]);
            if (gamePanel.getMonsters()[i] != null) {
                System.out.println("is not null");
                if (checkDistance(gamePanel.getMonsters()[i], x, y)) {
                    System.out.println("Entity selected");
                }
            }
        }
    }
    public boolean checkDistance(Entity entity, int x, int y) {
        boolean isTrue = false;

        int xDistance = Math.abs(entity.worldX - x);
        int yDistance = Math.abs(entity.worldY - y);
        int distance = Math.max(xDistance, yDistance);

        if (distance < gamePanel.getTileSize()) {
            return true;
        }
        return isTrue;
    }

    public void drawPlayerSolidArea() {
        int screenX = gamePanel.getScreenWidth() / 2 - (gamePanel.getTileSize() / 2);
        int screenY = gamePanel.getScreenHeight() / 2 - (gamePanel.getTileSize() / 2);
        graphics2D.fillRect(screenX + gamePanel.getPlayer().solidAreaDefaultX, screenY + gamePanel.getPlayer().solidAreaDefaultY, gamePanel.getPlayer().solidAreaRectangle.width, gamePanel.getPlayer().solidAreaRectangle.height);
    }

    private void drawInventory() {

        // frame
        int frameX = gamePanel.getTileSize() * 9;
        int frameY = gamePanel.getTileSize();
        int frameHeight = gamePanel.getTileSize() * 5;
        int frameWidth = gamePanel.getTileSize() * 6;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // Slot
        int slotX, slotY;

        final int slotXStart = frameX + 20;
        final int slotYStart = frameY + 20;

        // used in drawing items
        slotX = slotXStart;
        slotY = slotYStart;

        // cursor
        int cursorX = slotXStart + (gamePanel.getTileSize() * slotCol);
        int cursorY = slotYStart + (gamePanel.getTileSize() * slotRow);

        int cursorWidth = gamePanel.getTileSize();
        int cursorHeight = gamePanel.getTileSize();

        graphics2D.setColor(Color.cyan);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        // draw playerItems
        for (int i = 0; i < gamePanel.getPlayer().inventory.size(); i++) {

            //if equiped
            if (gamePanel.getPlayer().currentShield.equals(gamePanel.getPlayer().inventory.get(i)) || gamePanel.getPlayer().currentWeapon.equals(gamePanel.getPlayer().inventory.get(i))) {
                graphics2D.setColor(new Color(240, 190, 90, 75));
                graphics2D.fillRoundRect(slotX + 2, slotY + 2, gamePanel.getTileSize() - 4, gamePanel.getTileSize() - 4, 10, 10);
            }
            graphics2D.drawImage(gamePanel.getPlayer().inventory.get(i).image, slotX, slotY, null);

            slotX += gamePanel.getTileSize();

            if (i == 4 || i == 9 || i == 14) {
                slotX = slotXStart;
                slotY += gamePanel.getTileSize();
            }
        }

        // drawing descriptionArea for inventory

        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gamePanel.getTileSize() * 3;

        //draw description

        int textX = dFrameX + 10;
        int textY = dFrameY + gamePanel.getTileSize() / 2;

        graphics2D.setFont(gabriola20);

        // Breaking description String to separate lines
        int itemIndex = getItemIndexFromSlot();
        if (itemIndex < gamePanel.getPlayer().inventory.size()) {
            //draw desc. only if item selected
            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
            for (String line : gamePanel.getPlayer().inventory.get(itemIndex).description.split("\n")) {
                graphics2D.drawString(line, textX, textY);
                textY += 20;
            }
        }
    }

    public int getItemIndexFromSlot() {
        return slotCol + (slotRow * 5);
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 1, 1, 200); // paint ->SOLID UI
        graphics2D.setColor(c);
        graphics2D.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        graphics2D.setColor(c);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    private void drawScrollMessage() {
        gamePanel.setFont(arial20_bold);

        int messageX = gamePanel.getScreenWidth() - 280;
        int messageY = gamePanel.getScreenHeight() - 280;


        for (int i = 0; i < message.size(); i++) {

            if (message.get(i) != null) {
                //draw background

                graphics2D.setColor(new Color(0, 0, 0,100));
                graphics2D.fillRoundRect(messageX - 10, messageY - 7, 420, 25, 35, 35);
                graphics2D.setColor(Color.darkGray);
                graphics2D.fillRoundRect(messageX - 10, messageY - 16, 400, 20, 35, 35);


                //draw from message and it's shadow
                graphics2D.setFont(new Font("Gabriola", Font.PLAIN, 20));
                graphics2D.drawString(message.get(i), messageX + 1, messageY - 1);
                graphics2D.setColor(new Color(170, 110, 10));
                graphics2D.drawString(message.get(i), messageX, messageY+1);

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

        final int frameX = gamePanel.getTileSize() * 2;
        final int frameY = gamePanel.getTileSize();
        final int frameWidth = gamePanel.getTileSize() * 5;
        final int frameHeight = gamePanel.getTileSize() * 10;

        graphics2D.setColor(new Color(0, 0, 0, 80));
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        graphics2D.setColor(new Color(180, 180, 77, 50));
        /* graphics2D.fillRect(frameX + 20, frameY + 10, frameWidth - 53, 200);*/

        graphics2D.setColor(new Color(140, 0, 0, 80));
        graphics2D.fillRect(frameX + 20, frameY + 15, frameWidth - 40, 190);


        int textX = frameX + 28;
        int textY = frameY + 36;
        final int lineHeight = 18;


        graphics2D.setFont(gabriola20);
        graphics2D.setColor(new Color(75, 190, 250));

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


        //values
        int tailX = (frameX + frameWidth) - 30;
        textY = frameY + 36;

        String value;

        value = String.valueOf(gamePanel.getPlayer().level);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.getPlayer().speed);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gamePanel.getPlayer().maxLife);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.getPlayer().life);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.getPlayer().strength);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.getPlayer().dexterity);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.getPlayer().exp);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.getPlayer().nextLevelExp);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.getPlayer().coin);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.getPlayer().attack);
        textX = getXForRightAlignedText(value, tailX);
        graphics2D.drawString(value, textX, textY);
        textY += lineHeight;

        graphics2D.setColor(new Color(180, 180, 77, 50));
        graphics2D.fillRect(frameX, textY, 240, 220);
        graphics2D.drawImage(gamePanel.getPlayer().right1, frameX + 5, textY, 240, 220, null);
    }

    public void drawDialogScreen() {

        int x = gamePanel.getTileSize() * 2;
        int y = gamePanel.getTileSize() * 2;

        int width = gamePanel.getScreenWidth() - (gamePanel.getTileSize() * 4);
        int height = gamePanel.getScreenHeight() - (gamePanel.getTileSize() * 9);

        drawSubWindow(x, y, width, height);

        gamePanel.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 32F));

        x += gamePanel.getTileSize();
        y += gamePanel.getTileSize();

        for (String line : currentDialog.split("\n")) {
            graphics2D.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawPlayerLife() {

        int x = 10;
        int y = gamePanel.getScreenHeight() - 56;
        int i = 0;

        while (i < gamePanel.getPlayer().maxLife / 2) {
            Color c = new Color(0, 1, 1, 140);
            graphics2D.setColor(c);
            graphics2D.fillRoundRect(x + 13, y + gamePanel.getTileSize() - 10, 16, 8, 10, 10);
            graphics2D.drawImage(heart3, x, y, null);
            i++;
            x += gamePanel.getTileSize();

        }

        x = 10;
        y = gamePanel.getScreenHeight() - 56;
        i = 0;

        while (i < gamePanel.getPlayer().life) {
            graphics2D.drawImage(heart1, x, y, null);
            i++;
            if (i < gamePanel.getPlayer().life) {
                graphics2D.drawImage(heart3, x, y, null);
            }
            i++;
            x += gamePanel.getTileSize();
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

        for (int i = 0; i < 30; i++) {
            graphics2D.drawImage(utilityTool.scaleImage(image, gamePanel.getTileSize() * 2, gamePanel.getTileSize() + 8), i * 95, 0, null);
            graphics2D.drawImage(utilityTool.scaleImage(image, gamePanel.getTileSize() * 2, gamePanel.getTileSize() + 8), i * 95, gamePanel.getScreenHeight() - gamePanel.getTileSize() - 5, null);
        }

        g.setFont(gabriola20);
        g.setColor(Color.darkGray);
        g.drawString("x: " + gamePanel.getPlayer().worldX, 5, 31);
        g.drawString("y: " + gamePanel.getPlayer().worldY, 85, 31);

        g.drawString("col: " + gamePanel.getPlayer().worldX / gamePanel.getTileSize(), gamePanel.getScreenWidth() - 200, 31);
        g.drawString("row: " + gamePanel.getPlayer().worldY / gamePanel.getTileSize(), gamePanel.getScreenHeight() - 100, 31);

        g.setFont(gabriola20);
        g.setColor(Color.gray);
        g.drawString("x: " + gamePanel.getPlayer().worldX, 4, 33);
        g.drawString("y: " + gamePanel.getPlayer().worldY, 84, 33);

        g.drawString("col: " + gamePanel.getPlayer().worldX / gamePanel.getTileSize(), gamePanel.getScreenWidth() - 201, 31);
        g.drawString("row: " + gamePanel.getPlayer().worldY / gamePanel.getTileSize(), gamePanel.getScreenHeight() - 101, 31);

        drawPlayerLife();


        //draw xp bar

        int screenX = gamePanel.getScreenWidth() - gamePanel.getTileSize() *8;
        int screenY = gamePanel.getScreenHeight() - gamePanel.getTileSize() + 2;

        double oneScale = (double) gamePanel.getTileSize() / gamePanel.getPlayer().nextLevelExp;
        double healthBarValue = oneScale * gamePanel.getPlayer().exp;


        graphics2D.setColor(new Color(0, 0, 0));
        graphics2D.fillRect(screenX-2, screenY + 3, (gamePanel.getTileSize() * 7) + 5, 34);

        graphics2D.setColor(new Color(35, 35, 35));
        graphics2D.fillRect(screenX, screenY + 5, gamePanel.getTileSize() * 7, 30);

        graphics2D.setColor(new Color(255, 110, 10, 75));
        graphics2D.fillRect(screenX, screenY + 5, (int) healthBarValue * 7, 30);

        graphics2D.setFont(gabriola20);
        graphics2D.setColor(new Color(170, 110, 10));
        graphics2D.drawString("" + gamePanel.getPlayer().exp + "/" + gamePanel.getPlayer().nextLevelExp, screenX + 160, screenY + 25);
    }

    public void drawPauseState() {

        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.ITALIC, 80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gamePanel.getScreenWidth() / 4;
        graphics2D.drawString(text, x, y);
        graphics2D.drawString("Esc - quit game", x - 46 * 3, y + gamePanel.getTileSize() * 3);

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
        int y = gamePanel.getScreenWidth() / 2;
        graphics2D.drawString(text, x, y);

    }

    public int getXForCenteredText(String text) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        int x = gamePanel.getScreenWidth() / 2 - length / 2;
        return x;

    }

    public int getXForRightAlignedText(String text, int tailX) {
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
        int x = tailX - length;
        return x;

    }
}
