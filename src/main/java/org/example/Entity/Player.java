package org.example.Entity;

import org.example.Handler.UtilityTool;
import org.example.game.GamePanel;
import org.example.Handler.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.BufferOverflowException;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;
    public boolean hasKey = false;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {

        this.gamePanel = gamePanel;
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

    public BufferedImage setup(String imageName) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;
        try {
           image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
           image = utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;

    }

    public void setPlayerImage() {

        up1 = setup("NHU1");
        up2 = setup("NHU2");
        up = setup("NHU1");
        down1 = setup("NHD1");
        down = setup("NHD2");
        down2 = setup("NHD2");
        right1 = setup("NHR1");
        right = setup("NHR1");
        right2 = setup("NHR2");
        left1 = setup("NHL1");
        left2 = setup("NHL2");
        left = setup("NHL1");
        stand = setup("NHU1");
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

    public void pickupObject(int i) {

        if (i != 999) {

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
                gamePanel.obj[i] = null;
            }
        }

    }
}
