package org.example.Entity;

import org.example.GamePanel;
import org.example.KeyHandler;
import org.example.KeyHandlerTwo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player2 extends Entity{
    GamePanel gamePanel;
    KeyHandlerTwo keyHandler;

    public Player2(GamePanel gamePanel, KeyHandlerTwo keyHandler) {
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
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/New Woman.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/New Woman.png"));
            up = ImageIO.read(getClass().getResourceAsStream("/player/New Woman.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/New Woman.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/player/New Woman.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/New Woman.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/New Woman.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/player/New Woman.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/New Woman.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/New Woman.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/New Woman.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/player/New Woman.png"));
            stand = ImageIO.read(getClass().getResourceAsStream("/player/New Woman.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {
        //changing param : #direction and #x#y based on KeyHandler
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
            //change #spriteNum and #spriteCounter and player character icon
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
    //Drawing method setting player image #spriteNum
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
