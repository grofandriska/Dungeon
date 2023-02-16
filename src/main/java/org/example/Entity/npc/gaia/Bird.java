package org.example.Entity.npc.gaia;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

public class Bird extends Entity {

    //constructor
    public Bird(GamePanel gamePanel) {
        super(gamePanel);
        this.direction = "left";
        this.speed = 1;
        this.name = "Bird";
        setBirdImage();
    }
    //check tile and border and change image counter
    public void update() {
        setNewDirection();
        isCollisionOn = false;

        gamePanel.getCollisionChecker().checkBorder(this);

        if (!isCollisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }

        setMoveImageCounter++;

        if (setMoveImageCounter > 12) {
            if (spriteImageNumber == 1) {
                spriteImageNumber = 2;
            } else if (spriteImageNumber == 2) {
                spriteImageNumber = 1;
            }
            setMoveImageCounter = 0;
        }
    }
    //load image files
    public void setBirdImage() {
        up1 = setup("/entities/bird/Bird 1",gamePanel.getTileSize(),gamePanel.getTileSize());
        up2 = setup("/entities/bird/Bird 5",gamePanel.getTileSize(),gamePanel.getTileSize());
        down1 = setup("/entities/bird/Bird 2",gamePanel.getTileSize(),gamePanel.getTileSize());
        down2 = setup("/entities/bird/Bird 4",gamePanel.getTileSize(),gamePanel.getTileSize());
        right1 = setup("/entities/bird/Bird 2",gamePanel.getTileSize(),gamePanel.getTileSize());
        right2 = setup("/entities/bird/Bird 4",gamePanel.getTileSize(),gamePanel.getTileSize());
        left1 = setup("/entities/bird/Bird 1",gamePanel.getTileSize(),gamePanel.getTileSize());
        left2 = setup("/entities/bird/Bird 5",gamePanel.getTileSize(),gamePanel.getTileSize());
    }
}