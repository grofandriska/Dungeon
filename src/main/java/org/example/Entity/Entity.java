package org.example.Entity;

import org.example.Events.model.Event;
import org.example.Handler.draw.UtilityTool;
import org.example.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public abstract class Entity {
    public String direction;
    public String[] dialogs;

    public String name;
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public BufferedImage image, image2, image3;
    public BufferedImage attackUp_1, attackUp_2, attackDown_1, attackDown_2, attackRight_1, attackRight_2, attackLeft_1, attackLeft_2;
    public Rectangle solidArea;

    public GamePanel gamePanel;
    public boolean collisionOn;
    public Rectangle attackRectangle;

    public boolean alive = true;
    public boolean dying = false;

    int dyingCounter;
    public boolean collision;
    public int worldX, worldY;
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public int speed;
    public int spriteCounter;
    public int spriteNum;
    public int maxLife;
    public int life;
    public int imageCounter;
    public int dialogIndex;
    public boolean invincible;
    public int invincibleCounter;
    public int type; // 0 for player, 1 npc, 2 monster ****change to Enum if possible

    public Entity(GamePanel gamePanel) {
        spriteCounter = 0;
        spriteNum = 1;
        imageCounter = 0;
        invincibleCounter = 0;
        invincible = false;
        direction = "down";
        dialogs = new String[20];
        collision = false;
        collisionOn = false;
        solidArea = new Rectangle(0, 0, 40, 40);
        attackRectangle = new Rectangle(0, 0, 0, 0);
        this.dialogs[0] = "...";
        this.gamePanel = gamePanel;
    }

    public void speak() {
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
            imageCounter = 0;
        }
        //makes characters movement.
    }

    public void update() {
        setAction();

        collisionOn = false;

        gamePanel.collisionChecker.checkBorder(this);

        gamePanel.collisionChecker.checkTile(this);

        gamePanel.collisionChecker.checkObject(this, false);

        gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);

        gamePanel.collisionChecker.checkEntity(this, gamePanel.monsters);

        boolean contactPLayer = gamePanel.collisionChecker.checkPlayer(this);

        //player class contact monster would come here
        if (contactPLayer && this.type == 2) {
            if (!gamePanel.player.invincible) {
                gamePanel.player.life -= 2;
                gamePanel.player.invincible = true;
            }
        }

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
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = utilityTool.scaleImage(image, width, height);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("setup image:" + imagePath + " ... done");
        return image;
    }

    public void checkDistance(Entity entity) {
        boolean isTrue = false;

        int xDistance = Math.abs(gamePanel.player.worldX - entity.worldX);
        int yDistance = Math.abs(gamePanel.player.worldY - entity.worldY);
        int distance = Math.max(xDistance, yDistance);

        if (distance < gamePanel.tileSize * 2) {
            // extend
        }

    }

    public void draw(Graphics2D graphics2D) {

        BufferedImage image = null;

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX
                && worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX
                && worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY
                && worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
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

            if (invincible) {
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }

            if (dying) {
                dyingAnimation(graphics2D);
            }
            graphics2D.drawImage(image, screenX, screenY, null);

            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    private void dyingAnimation(Graphics2D graphics2D) {

        dyingCounter++;

        int i = 5;

        if (dyingCounter <= 5) {changeAlphaForAnimation(graphics2D, 0f);}
        if (dyingCounter > i && dyingCounter <= i*2) {changeAlphaForAnimation(graphics2D, 1f);}
        if (dyingCounter > i*2&& dyingCounter <= i*3) {changeAlphaForAnimation(graphics2D, 0f);}
        if (dyingCounter > i*3&& dyingCounter <= i*4) {changeAlphaForAnimation(graphics2D, 1f);}
        if (dyingCounter > i*4&& dyingCounter <= i*5) {changeAlphaForAnimation(graphics2D, 0f);}
        if (dyingCounter > i*5&& dyingCounter <= i*6) {changeAlphaForAnimation(graphics2D, 1f);}
        if (dyingCounter > i*6&& dyingCounter <= i*7) {changeAlphaForAnimation(graphics2D, 0f);}
        if (dyingCounter > i*7&& dyingCounter <= i*8) {changeAlphaForAnimation(graphics2D, 1f);}

        if (dyingCounter > i*8) {dying = false; alive = false;}
    }

    public void changeAlphaForAnimation(Graphics2D graphics2D, float alpha) {
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }
}




