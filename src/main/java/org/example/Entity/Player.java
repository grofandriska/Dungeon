package org.example.Entity;

import org.example.GamePanel;
import org.example.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
        setPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;

        direction = "stand";
    }

    public void setPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/NHU1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/NHU2.png"));
            up = ImageIO.read(getClass().getResourceAsStream("/player/New Human 1.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/NHD1.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/player/New Human 1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/NHD2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/NHR1.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/player/NHR1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/NHR2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/NHL1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/NHL2.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/player/New Human 1.png"));
            stand = ImageIO.read(getClass().getResourceAsStream("/player/New Human 1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyHandler.downPressed|| keyHandler.upPressed||
                keyHandler.leftPressed|| keyHandler.rightPressed){
            if (keyHandler.upPressed) {
                direction = "up";
                y -= speed;
            } else if (keyHandler.downPressed) {
                direction = "down";
                y += speed;
            } else if (keyHandler.rightPressed) {
                direction = "right";
                x += speed;
            } else if (keyHandler.leftPressed) {
                direction = "left";
                x -= speed;
            }
            spriteCounter++;
            if (spriteCounter > 12){
                if (spriteNum == 1){
                    spriteNum = 2;

                }
                else if (spriteNum == 2){
                    spriteNum =1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1){
                image = up1;}
                if (spriteNum == 2){
                image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1){
                    image = down1;}
                if (spriteNum == 2){
                    image = down2;
                }
                break;
            case "right":
                if (spriteNum == 1) {image = right1;}
                if (spriteNum == 2){ image= right2;}
                break;
            case "left":
                if (spriteNum == 1) {image = left1;}
                if (spriteNum == 2){ image = left2;}
                break;
        }
        g2.drawImage(image,x,y,gamePanel.tileSize,gamePanel.tileSize,null);
    }
}
