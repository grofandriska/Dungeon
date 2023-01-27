package org.example.Entity.npc;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Mage extends Entity {

    public int nextTeleportCounter = 0;

    public boolean canTeleport = true;

    public Mage(GamePanel gamePanel) {
        super(gamePanel);

        name = "Mage";

        type = 1;
        speed = 1;
        maxLife = 4;
        life = maxLife;

        getImage();
        setDialog();

    }

    public void getImage() {

        image = setup("/entities/mage/Mage Teleport Ready", gamePanel.tileSize, gamePanel.tileSize);
        image2 = setup("/entities/mage/Mage Teleport 2", gamePanel.tileSize, gamePanel.tileSize);

        up1 = setup("/entities/mage/Mage Up1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/entities/mage/Mage Up2", gamePanel.tileSize, gamePanel.tileSize);

        right1 = setup("/entities/mage/Mage 2", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/entities/mage/Mage 2", gamePanel.tileSize, gamePanel.tileSize);

        left1 = setup("/entities/mage/Mage 1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/entities/mage/Mage 1", gamePanel.tileSize, gamePanel.tileSize);

        down1 = setup("/entities/mage/Mage 1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/entities/mage/Mage 2", gamePanel.tileSize, gamePanel.tileSize);

    }

    public void setDialog() {
        dialogs[0] = "Be Aware!\nOrcs are everywhere!\nDo you know how to handle?!\n";
        dialogs[1] = "When you get close,\npress ENTER to attack!";
        dialogs[2] = "Blinking means entity\ncan't receive damage.";
        dialogs[3] = "Let's see how many\ncan you kill...";
        dialogs[4] = "...";
    }

    public boolean checkDistance(Entity entity) {

        int xDistance = Math.abs(gamePanel.player.worldX - entity.worldX);
        int yDistance = Math.abs(gamePanel.player.worldY - entity.worldY);

        int distance = Math.max(xDistance, yDistance);

        if (distance < gamePanel.tileSize * 3) {
            canTeleport = true;
            return true;
        }

        return false;
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

        //if not want to teleport

        if (!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
                case "teleport" -> {
                }
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

    public void setDirection() {
        imageCounter++;
        if (imageCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(120) + 1;
            if (i <= 25) direction = "down";
            canTeleport = true;
            if (i >= 25 && i <= 50) direction = "left";
            if (i >= 50 && i <= 75) direction = "right";
            if (i >= 75 && i <= 100) direction = "up";
            if (i >= 100 && i <= 120) direction = "teleport";
            {
            }
            imageCounter = 0;
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
                case "teleport" -> {
                    canTeleport = true;
                    nextTeleportCounter++;
                    if (spriteNum == 1) {
                        image = this.image;
                    }
                    if (spriteNum == 2) {
                        image = this.image2;
                    }
                    System.out.println("TeleportPower :"+nextTeleportCounter);
                    if (nextTeleportCounter == 20 && canTeleport) {
                        gamePanel.playSoundEffect(8);
                        teleport();
                        nextTeleportCounter = 0;
                        direction = "down";
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

    private void teleport() {

        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int c = 0; c < 8; c++) {
                int tempX = ((worldX / gamePanel.tileSize) - 6 + i);
                int tempY = ((worldY / gamePanel.tileSize) - 6 + c);
                if (tempX < 49 && tempY < 49 && tempX > 0 && tempY > 0) {
                    if (!(gamePanel.tileManager.mapTileNum[tempX][tempY] == 1)
                            && !(gamePanel.tileManager.mapTileNum[tempX][tempY] == 9)
                            && !(gamePanel.tileManager.mapTileNum[tempX][tempY] == 25)
                            && !(gamePanel.tileManager.mapTileNum[tempX][tempY] == 26)
                            && !(gamePanel.tileManager.mapTileNum[tempX][tempY] == 27)
                            && !(gamePanel.tileManager.mapTileNum[tempX][tempY] == 28)
                            && !(gamePanel.tileManager.mapTileNum[tempX][tempY] == 29)) {
                        x.add(tempX);
                        y.add(tempY);
                    }
                }
            }
        }

        for (int e = 0; e < x.size(); e++) {
            if (x.get(e) >= 50) {
                x.remove(e);
                y.remove(e);
            }
            if (y.get(e) >= 50) {
                x.remove(e);
                y.remove(e);
            }
        }

        int randomNum = ThreadLocalRandom.current().nextInt(0, x.size());

        spriteCounter++;

        worldX = x.get(randomNum) * gamePanel.tileSize;
        worldY = y.get(randomNum) * gamePanel.tileSize;

        System.out.print("X : " + worldX + " | Y : " + worldY);

        canTeleport = false;
    }
}