package org.example.Entity.gaia;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

public class Bird extends Entity {

    public Bird(GamePanel gamePanel) {
        super(gamePanel);
        setBirdImage();
    }

    public void update() {
        setAction();

        collisionOn = false;

        gamePanel.collisionChecker.checkTile(this);
        gamePanel.collisionChecker.checkObject(this, false);
        gamePanel.collisionChecker.checkPlayer(this);

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
        direction = "left";
        speed = 1;
        up1 = setup("/entities/Bird 1");
        up2 = setup("/entities/Bird 5");
        up = setup("/entities/Bird 1");
        down1 = setup("/entities/Bird 2");
        down = setup("/entities/Bird 2");
        down2 = setup("/entities/Bird 4");
        right1 = setup("/entities/Bird 2");
        right = setup("/entities/Bird 2");
        right2 = setup("/entities/Bird 4");
        left1 = setup("/entities/Bird 1");
        left2 = setup("/entities/Bird 5");
        left = setup("/entities/Bird 1");
        stand = setup("/entities/Bird 3");
    }
}