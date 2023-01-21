package org.example.Entity;

import org.example.Entity.player.Player;
import org.example.Handler.draw.UtilityTool;
import org.example.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public abstract class Entity {

    public boolean collision = false;

    public String direction = "down";

    public String name;
    public BufferedImage up1, up2, down1, down2, right1, right2, left1,
    left2, up, down, right, stand, left, image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 40, 40);

    public GamePanel gamePanel;
    public boolean collisionOn = false;
    public int worldX, worldY;
    public int speed;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public int maxLife;
    public int life;
    public int imageCounter = 0;
    public int dialogIndex;
    public String[] dialogs = new String[20];

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void speak(Player player) {
        if (dialogs[dialogIndex] == null) {
            dialogIndex = 0;
        }
        gamePanel.UI.currentDialog = dialogs[dialogIndex];
        dialogIndex++;
    }

    public void setAction() {
        imageCounter++;
        if (imageCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) direction = "down";
            if (i >= 25 && i <= 50) direction = "left";
            if (i >= 50 && i <= 75) direction = "right";
            if (i >= 75 && i <= 100) direction = "up";
            imageCounter = 0;}
        //makes characters movement.
    }

    public void update() {
        setAction();
        collisionOn = false;

        gamePanel.collisionChecker.checkTile(this);

        gamePanel.collisionChecker.checkPlayer(this);
        gamePanel.collisionChecker.checkObject(this, false);

        gamePanel.collisionChecker.checkEntity(this,gamePanel.npc);
        gamePanel.collisionChecker.checkEntity(this,gamePanel.monsters);

        if (!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    public BufferedImage setup(String imageName) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imageName + ".png")));
            image = utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }
    public void draw(Graphics2D graphics2D) {

        BufferedImage image = null;

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX
                && worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX
                && worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY
                && worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY
        ) {
            switch (direction) {
                case "up" -> {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                case "down" -> {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                case "right" -> {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                case "left" -> {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
            }
            graphics2D.drawImage(image, screenX, screenY, null);
        }
    }
}




