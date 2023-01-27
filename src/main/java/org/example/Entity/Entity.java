package org.example.Entity;

import org.example.Handler.draw.UtilityTool;
import org.example.Game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public abstract class Entity {

    Random random = new Random();
    public String[] dialogs;

    public String direction, name;

    public GamePanel gamePanel;
    public Rectangle attackRectangle, solidArea;
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public BufferedImage image, image2, image3, attackUp_1, attackUp_2, attackDown_1, attackDown_2, attackRight_1, attackRight_2, attackLeft_1, attackLeft_2;
    public int spriteCounter, spriteNum, maxLife, life, imageCounter, dialogIndex, invincibleCounter, type, deathCounter, worldX, worldY, solidAreaDefaultX, solidAreaDefaultY, speed;
    public boolean collisionOn, collision, invincible, dying, alive;

    public Entity(GamePanel gamePanel) {
        dying = false;
        alive = true;
        spriteCounter = 0;
        spriteNum = 1;
        imageCounter = 0;
        invincibleCounter = 0;
        direction = "down";
        invincible = false;
        collision = false;
        collisionOn = false;
        dialogs = new String[20];
        solidArea = new Rectangle(0, 0, 40, 40);
        attackRectangle = new Rectangle(0, 0, 0, 0);
        dialogs[0] = "...";
        this.gamePanel = gamePanel;
    }

    public void setDirection() {
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
        setDirection();

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
            if (this.invincibleCounter == 1) gamePanel.playSoundEffect(2);
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public int generateCriticalAttack(){
        int criticalValue = 0;
        int randomNumber = 0;
        randomNumber = random.nextInt(100) + 1;
        System.out.println(randomNumber);
        if (randomNumber > 90) {
            criticalValue =  2;
        }
        return criticalValue;
    }

    public boolean checkDistance(Entity entity) {
        boolean isTrue = false;

        int xDistance = Math.abs(gamePanel.player.worldX - entity.worldX);
        int yDistance = Math.abs(gamePanel.player.worldY - entity.worldY);
        int distance = Math.max(xDistance, yDistance);

        if (distance < gamePanel.tileSize * 2) {
            // extend
        }
        return isTrue;
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
                drawHealthBar(graphics2D);
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }

            if (dying) {
                dyingAnimation(graphics2D);
            }
            graphics2D.drawImage(image, screenX, screenY, null);

            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    public void drawHealthBar(Graphics2D graphics2D) {
        if (type == 2 || type == 3) {
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            double oneScale = (double) gamePanel.tileSize / maxLife;
            double healthBarValue = oneScale * life;

            graphics2D.setColor(new Color(35, 35, 35));
            graphics2D.fillRect(screenX - 1, screenY - 16, gamePanel.tileSize, 10);

            graphics2D.setColor(new Color(255, 0, 30));
            graphics2D.fillRect(screenX - 1, screenY - 16, (int) healthBarValue, 10);

        }
    }

    public void dyingAnimation(Graphics2D graphics2D) {

        deathCounter++;

        int i = 5;

        if (deathCounter <= 5) {
            changeAlphaForAnimation(graphics2D, 0f);
        }
        if (deathCounter > i && deathCounter <= i * 2) {
            changeAlphaForAnimation(graphics2D, 1f);
        }
        if (deathCounter > i * 2 && deathCounter <= i * 3) {
            changeAlphaForAnimation(graphics2D, 0f);
        }
        if (deathCounter > i * 3 && deathCounter <= i * 4) {
            changeAlphaForAnimation(graphics2D, 1f);
        }
        if (deathCounter > i * 4 && deathCounter <= i * 5) {
            changeAlphaForAnimation(graphics2D, 0f);
        }
        if (deathCounter > i * 5 && deathCounter <= i * 6) {
            changeAlphaForAnimation(graphics2D, 1f);
        }
        if (deathCounter > i * 6 && deathCounter <= i * 7) {
            changeAlphaForAnimation(graphics2D, 0f);
        }
        if (deathCounter > i * 7 && deathCounter <= i * 8) {
            changeAlphaForAnimation(graphics2D, 1f);
        }

        if (deathCounter > i * 8) {
            dying = false;
            alive = false;
        }
    }

    public void damageReaction(){
    }

    public void changeAlphaForAnimation(Graphics2D graphics2D, float alpha) {
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    }
    public void speak() {
        gamePanel.playSoundEffect(4);
        if (dialogs[dialogIndex] == null) {
            dialogIndex = 0;
        }
        gamePanel.UI.currentDialog = dialogs[dialogIndex];
        dialogIndex++;
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
        return image;
    }
}




