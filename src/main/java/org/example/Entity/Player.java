package org.example.Entity;

import org.example.Handler.UtilityTool;
import org.example.game.GamePanel;
import org.example.Handler.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    KeyHandler keyHandler;
    // public boolean hasKey = false;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);

        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        solidArea = new Rectangle();

        solidArea.x = 10;
        solidArea.y = 16;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.width = 35;
        solidArea.height = 35;

        setDefaultValues();
        setPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gamePanel.tileSize * 20;
        worldY = gamePanel.tileSize * 20;
        speed = 2;
        direction = "up";

    }

    public void pickupObject(int i) {

       /* if (i != 999) {

            if (gamePanel.obj[i].name == "Key") {
                hasKey = true;
                gamePanel.obj[i] = null;
                gamePanel.ui.showMessage("you got a key");

            } else if (gamePanel.obj[i].name == "Gift" && hasKey) {
                gamePanel.obj[i] = null;
                gamePanel.ui.isGameFinished = true;
            } else if (gamePanel.obj[i].name == "Gift" && !hasKey) {
                gamePanel.ui.showMessage("You Need A Key!");
            } else if (gamePanel.obj[i].name == "Potion") {
                gamePanel.playSoundEffect(1);
                gamePanel.ui.showMessage("Glup .. Glup.. O...o...");
                this.speed = speed / 2;
                gamePanel.obj[i] = null;*/


    }

    public void setPlayerImage() {

        up1 = setup("/player/NHU1");
        up2 = setup("/player/NHU2");
        up = setup("/player/NHU1");
        down1 = setup("/player/NHD1");
        down = setup("/player/NHD2");
        down2 = setup("/player/NHD2");
        right1 = setup("/player/NHR1");
        right = setup("/player/NHR1");
        right2 = setup("/player/NHR2");
        left1 = setup("/player/NHL1");
        left2 = setup("/player/NHL2");
        left = setup("/player/NHL1");
        stand = setup("/player/NHU1");
    }

    public void update() {
        if (keyHandler.downPressed || keyHandler.upPressed || keyHandler.leftPressed || keyHandler.rightPressed) {

            if (keyHandler.upPressed) {
                direction = "up";
            } else if (keyHandler.downPressed) {
                direction = "down";
            } else if (keyHandler.rightPressed) {
                direction = "right";
            } else if (keyHandler.leftPressed) {
                direction = "left";
            }

            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);

            int objIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickupObject(objIndex);

            int npcIntdex = gamePanel.collisionChecker.checkEntity(this, gamePanel.entities);
            interactNPC(npcIntdex);

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
    }

    public void interactNPC(int index){
        if (index != 999){
            System.out.println("you Re ie");
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

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
        g2.drawImage(image, screenX, screenY, null);
    }


}
