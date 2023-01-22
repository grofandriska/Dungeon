package org.example.Entity.player;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;
import org.example.Handler.input.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    KeyHandler keyHandler;
    // public boolean hasKey = false;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
        name = "Player";
        this.keyHandler = keyHandler;
        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);
        solidArea = new Rectangle();
        solidArea.x = 10;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 30;
        setDefaultValues();
        setPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gamePanel.tileSize * 19;
        worldY = gamePanel.tileSize * 7;
        direction = "up";
        speed = 2;
        maxLife = 6;
        life = maxLife;
    }

    public void pickupObject(int i) {
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

    //happens 60 times /second
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
            gamePanel.collisionChecker.checkBorder(this);
            gamePanel.collisionChecker.checkEntity(this, gamePanel.monsters);
            gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
            gamePanel.collisionChecker.checkEntity(this, gamePanel.entities);

            int objIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickupObject(objIndex);

            int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monsters);

           /* contactMonster(monsterIndex);*/
            int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);


            gamePanel.eventHandler.checkEvent();

            interactNPC(npcIndex);

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
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    private void contactMonster(int i) {
        if (i != 999) {
            if (!invincible) {
                life -= 1;
                invincible = true;
            }
        }
    }

    public void interactNPC(int index) {
        if (index != 999) {
            if (gamePanel.keyHandler.enterPressed) {
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.npc[index].speak(this);
            }
        }
        gamePanel.keyHandler.enterPressed = false;
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

        if (invincible)g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));

        g2.drawImage(image, screenX, screenY, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));


    }

}
