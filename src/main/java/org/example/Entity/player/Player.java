package org.example.Entity.player;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;
import org.example.Handler.input.KeyHandler;
import org.example.Sound.Sound;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    KeyHandler keyHandler;
    public boolean isAttacking;
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

        direction = "up";
        speed = 2;
        maxLife = 10;
        life = maxLife;

        worldX = gamePanel.tileSize * 45;
        worldY = gamePanel.tileSize * 25;

        attackRectangle.height = 36;
        attackRectangle.width = 36;
    }


    //happens 60 times /second
    public void update() {
        if (isAttacking) {
            attacking();
        } else if (keyHandler.downPressed || keyHandler.upPressed || keyHandler.leftPressed || keyHandler.rightPressed || keyHandler.enterPressed) {

            if (keyHandler.upPressed) {
                direction = "up";
            } else if (keyHandler.downPressed) {
                direction = "down";
            } else if (keyHandler.rightPressed) {direction = "right";} else if (keyHandler.leftPressed) {direction = "left";
            }

            collisionOn = false;

            //check surround
            gamePanel.collisionChecker.checkTile(this);
            gamePanel.collisionChecker.checkBorder(this);
            gamePanel.collisionChecker.checkEntity(this, gamePanel.monsters);
            gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
            gamePanel.collisionChecker.checkEntity(this, gamePanel.entities);
            gamePanel.eventHandler.checkEvent();


            // player methods and interactions
            int objIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickupObject(objIndex);

            int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monsters);
            contactMonster(monsterIndex);

            int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
            interactNPC(npcIndex);

            if (!collisionOn && !keyHandler.enterPressed) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            gamePanel.keyHandler.enterPressed = false;

            spriteCounter++;

            if (spriteCounter > 6) {
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
        if (life <= 0) {
            gamePanel.gameState = 4;
            gamePanel.playSoundEffect(5);
        }
    }

    private void attacking() {
        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNum = 1;
        }

        if (spriteCounter <= 25 && spriteCounter > 5) {
            spriteNum = 2;

            int currentWorldX = worldX;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            int currentWorldY = worldY;

            switch (direction) {
                case "up" -> worldY -= attackRectangle.height;
                case "down" -> worldY += attackRectangle.height;
                case "left" -> worldX -= attackRectangle.width;
                case "right" -> worldX += attackRectangle.width;
            }

            solidArea.width = attackRectangle.width;
            solidArea.height = attackRectangle.height;

            int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monsters);
            damageMonster(monsterIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }

        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            isAttacking = false;
        }
    }
    private void damageMonster(int i) {
        if (i != 999) {
            if (!gamePanel.monsters[i].invincible) {
                gamePanel.monsters[i].invincible = true;
                gamePanel.monsters[i].life -= 4;
                gamePanel.playSoundEffect(2);
                if (gamePanel.monsters[i].life <= 0) {
                    gamePanel.monsters[i].dying = true;
                    gamePanel.monsters[i] = null;
                    gamePanel.playSoundEffect(3);
                }
            }
        }
    }

    private void contactMonster(int i) {
        if (i != 999) {
            if (!invincible) {
                gamePanel.playSoundEffect(6);
                life -= 1;
                invincible = true;
            }
        }
    }

    public void interactNPC(int index) {
        if (gamePanel.keyHandler.enterPressed) {
            if (index != 999) {
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.npc[index].speak();
            } else {
                isAttacking = true;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up" -> {

                if (isAttacking) {
                    tempScreenY = screenY - gamePanel.tileSize;
                    if (spriteNum == 1) {
                        image = attackUp_1;
                    }
                    if (spriteNum == 2) {
                        image = attackUp_2;
                    }
                }
                if (!isAttacking) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
            }
            case "down" -> {
                if (isAttacking) {
                    if (spriteNum == 1) {
                        image = attackDown_1;
                    }
                    if (spriteNum == 2) {
                        image = attackDown_2;
                    }
                }

                if (!isAttacking) {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
            }
            case "right" -> {
                if (isAttacking) {
                    if (spriteNum == 1) {
                        image = attackRight_1;
                    }
                    if (spriteNum == 2) {
                        image = attackRight_2;
                    }
                }

                if (!isAttacking) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
            }
            case "left" -> {
                if (isAttacking) {
                    tempScreenX = screenX - gamePanel.tileSize;
                    if (spriteNum == 1) {

                        image = attackLeft_1;
                    }
                    if (spriteNum == 2) {
                        image = attackLeft_2;
                    }
                }
                if (!isAttacking) {

                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
            }
        }
        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void pickupObject(int i) {
    }

    public void setPlayerImage() {
        up1 = setup("/player/NHU1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/player/NHU2", gamePanel.tileSize, gamePanel.tileSize);

        down1 = setup("/player/NHD1", gamePanel.tileSize, gamePanel.tileSize);

        down2 = setup("/player/NHD2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/player/NHR1", gamePanel.tileSize, gamePanel.tileSize);

        right2 = setup("/player/NHR2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/player/NHL1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/player/NHL2", gamePanel.tileSize, gamePanel.tileSize);

        attackDown_1 = setup("/player/attack 1_1", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackDown_2 = setup("/player/attack 1_2", gamePanel.tileSize, gamePanel.tileSize * 2);


        attackUp_1 = setup("/player/attack 2_1", gamePanel.tileSize, gamePanel.tileSize * 2);
        attackUp_2 = setup("/player/attack 2_2", gamePanel.tileSize, gamePanel.tileSize * 2);

        attackRight_1 = setup("/player/attack 3_1", gamePanel.tileSize * 2, gamePanel.tileSize);
        attackRight_2 = setup("/player/attack 3_2", gamePanel.tileSize * 2, gamePanel.tileSize);

        attackLeft_2 = setup("/player/attack 4_2", gamePanel.tileSize * 2, gamePanel.tileSize);
        attackLeft_1 = setup("/player/attack 4_1", gamePanel.tileSize * 2, gamePanel.tileSize);

    }
}
