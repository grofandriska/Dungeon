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
    //dependency

    public GamePanel gamePanel;
    public Random random = new Random();


    //entity stats

    public String direction, name;
    public int level, strength, dexterity, attack, defense, exp, nextLevelExp, coin;
    public Entity currentWeapon, currentShield;
    public int attackValue, defenseValue;


    //entity static
    public String[] dialogs;

    public String description = " no info ";
    public Rectangle attackAreaRectangle, solidAreaRectangle;
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;

    public BufferedImage image, image2, image3, attackUp_1, attackUp_2, attackDown_1, attackDown_2, attackRight_1, attackRight_2, attackLeft_1, attackLeft_2;
    public int setMoveImageCounter, spriteImageNumber, maxLife, life, setDirectionCounter, dialogIndex, setInvincibleCounter, type, deathCounter, worldX, worldY, solidAreaDefaultX, solidAreaDefaultY, speed;
    public boolean isCollisionOn, isSolid, isInvincible, isDying, isAlive,isCritical;

    //constructor
    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        this.spriteImageNumber = 1;

        this.setMoveImageCounter = 0;
        this.setDirectionCounter = 0;
        this.setInvincibleCounter = 0;

        this.direction = "down";

        this.isDying = false;
        this.isAlive = true;
        this.isInvincible = false;
        this.isSolid = false;
        this.isCollisionOn = false;

        this.dialogs = new String[20];
        this.dialogs[0] = "...";

        this.solidAreaRectangle = new Rectangle(0, 0, gamePanel.tileSize, gamePanel.tileSize);
        this.attackAreaRectangle = new Rectangle(0, 0, gamePanel.tileSize, gamePanel.tileSize);

        this.attack = getAttack();

    }

    //change entity(npc) direction
    public void setNewDirection() {
        setDirectionCounter++;
        if (setDirectionCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) direction = "down";
            if (i >= 25 && i <= 50) direction = "left";
            if (i >= 50 && i <= 75) direction = "right";
            if (i >= 75 && i <= 100) direction = "up";
            setDirectionCounter = 0;
        }
    }

    //for objects
    public void consume() {
        //implement
    }

    public void update() {

        setNewDirection();

        isCollisionOn = false;

        //check surrounds

        gamePanel.collisionChecker.checkBorder(this);

        gamePanel.collisionChecker.checkTile(this);

        gamePanel.collisionChecker.checkObject(this, false);

        gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);

        gamePanel.collisionChecker.checkEntity(this, gamePanel.monsters);

        boolean contactPLayer = gamePanel.collisionChecker.checkPlayer(this);


        //
        if (contactPLayer && this.type == 2) {
            if (!gamePanel.player.isInvincible) {
                int damage = attack - gamePanel.player.defense;
                if (damage < 0) {
                    damage = 0;
                }
                gamePanel.playSoundEffect(6);
                gamePanel.player.life -= damage;
                gamePanel.player.isInvincible = true;
            }
        }


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


        if (isInvincible) {
            setInvincibleCounter++;
            if (setInvincibleCounter > 60) {
                isInvincible = false;
                setInvincibleCounter = 0;
            }
        }
    }


    public void damageReaction() {
    }

    public int getAttack() {
        if (currentWeapon != null) {
            attackAreaRectangle = currentWeapon.attackAreaRectangle;
            return attack = strength * currentWeapon.attackValue;
        }
        return 1;
    }

    public int generateCriticalAttack() {
        int attackValue = attack;
        int criticalValue;

        int randomNumber = random.nextInt(100) + 1;

        if (randomNumber > 80) {
            criticalValue = 2;
        } else {
            return attackValue;
        }
        isCritical = true;
        return attackValue + criticalValue;
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
                    if (spriteImageNumber == 1) {
                        image = up1;
                    }
                    if (spriteImageNumber == 2) {
                        image = up2;
                    }
                }
                case "down" -> {
                    if (spriteImageNumber == 1) {
                        image = down1;
                    }
                    if (spriteImageNumber == 2) {
                        image = down2;
                    }
                }
                case "right" -> {
                    if (spriteImageNumber == 1) {
                        image = right1;
                    }
                    if (spriteImageNumber == 2) {
                        image = right2;
                    }
                }
                case "left" -> {
                    if (spriteImageNumber == 1) {
                        image = left1;
                    }
                    if (spriteImageNumber == 2) {
                        image = left2;
                    }
                }
            }


            if (isInvincible) {
                drawHealthBar(graphics2D);
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }

            if (isDying) {
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
            isDying = false;
            isAlive = false;
        }
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




