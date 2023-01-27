package org.example.Entity.gaia;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

public class Bird extends Entity {

    public Bird(GamePanel gamePanel) {
        super(gamePanel);
        direction = "left";
        speed = 1;
        name = "Bird";
        setBirdImage();
    }

    public void update() {
        setDirection();

        collisionOn = false;

        gamePanel.collisionChecker.checkBorder(this);
        gamePanel.collisionChecker.checkTile(this);

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

    public void setBirdImage() {
        up1 = setup("/entities/bird/Bird 1",gamePanel.tileSize,gamePanel.tileSize);
        up2 = setup("/entities/bird/Bird 5",gamePanel.tileSize,gamePanel.tileSize);
        down1 = setup("/entities/bird/Bird 2",gamePanel.tileSize,gamePanel.tileSize);
        down2 = setup("/entities/bird/Bird 4",gamePanel.tileSize,gamePanel.tileSize);
        right1 = setup("/entities/bird/Bird 2",gamePanel.tileSize,gamePanel.tileSize);
        right2 = setup("/entities/bird/Bird 4",gamePanel.tileSize,gamePanel.tileSize);
        left1 = setup("/entities/bird/Bird 1",gamePanel.tileSize,gamePanel.tileSize);
        left2 = setup("/entities/bird/Bird 5",gamePanel.tileSize,gamePanel.tileSize);
    }
}