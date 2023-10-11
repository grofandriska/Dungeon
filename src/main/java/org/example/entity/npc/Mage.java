package org.example.entity.npc;

import org.example.entity.Entity;
import org.example.Game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Mage extends Entity {

    //entity static
    public int nextTeleportCounter = 0;
    public boolean canTeleport = true;

    //constructor
    public Mage(GamePanel gamePanel) {
        super(gamePanel);

        this.name = "Mage";
        this.type = 2;
        this.speed = 1;
        this.maxLife = 4;
        this.life = maxLife;
        this.dexterity = 1;
        this.attack = 1;

        getImage();
        setDialog();

    }
    public void setDialog() {

    }
    //check if entity is in distance
    public boolean checkDistance(Entity entity,int distanceGoal) {

        int xDistance = Math.abs(this.worldX - entity.worldX);
        int yDistance = Math.abs(this.worldY - entity.worldY);

        int distance = Math.max(xDistance, yDistance);

        if (distance < distanceGoal) {
            canTeleport = true;
            return true;
        }
        return false;
    }
    //check surroundings and set counters
    public void update() {
        setNewDirection();

        isCollisionOn = false;

        //check surrounds
        gamePanel.getCollisionChecker().checkBorder(this);
        gamePanel.getCollisionChecker().checkTile(this);
        gamePanel.getCollisionChecker().checkObject(this, false);
        gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getNpc());
        gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getMonsters());

        //if collision do damage to player -> will be magic here :)
        boolean contactPLayer = gamePanel.getCollisionChecker().checkPlayer(this);
        if (contactPLayer && this.type == 2) {
            if (!gamePanel.getPlayer().isInvincible) {
                gamePanel.getPlayer().life -= 2;
                gamePanel.getPlayer().isInvincible = true;
            }
        }

        //move or teleport ()
        if (!isCollisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
                case "teleport" -> {}
            }
        }

        //if player close
        if (checkDistance(gamePanel.getPlayer(),gamePanel.getTileSize() * 3)){
            direction = "teleport";
        }

        //set image counter
        setMoveImageCounter++;
        if (setMoveImageCounter > 12) {
            if (spriteImageNumber == 1) {
                spriteImageNumber = 2;
            } else if (spriteImageNumber == 2) {
                spriteImageNumber = 1;
            }
            setMoveImageCounter = 0;
        }

        //set invincible counter and play sound
        if (isInvincible) {
            if (this.setInvincibleCounter == 1) gamePanel.playSoundEffect(2);
            setInvincibleCounter++;
            if (setInvincibleCounter > 60) {
                isInvincible = false;
                setInvincibleCounter = 0;
            }
        }
    }
    //set direction or set to teleport
    public void setNewDirection() {
        setDirectionCounter++;
        if (setDirectionCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(120) + 1;
            if (i <= 25) direction = "down";
            if (i >= 25 && i <= 50) direction = "left";
            if (i >= 50 && i <= 75) direction = "right";
            if (i >= 75 && i <= 100) direction = "up";
            if (i >= 100 && i <= 120) direction = "teleport";
            setDirectionCounter = 0;
        }
    }
    //draw and set image
    public void draw(Graphics2D graphics2D) {
        //variables
        BufferedImage image = null;
        int screenX = worldX - gamePanel.getPlayer().worldX + gamePanel.getPlayer().screenX;
        int screenY = worldY - gamePanel.getPlayer().worldY + gamePanel.getPlayer().screenY;
        //if logic - is on draw screen ? then do the procedure
        if (worldX + gamePanel.getTileSize() > gamePanel.getPlayer().worldX - gamePanel.getPlayer().screenX
                && worldX - gamePanel.getTileSize() < gamePanel.getPlayer().worldX + gamePanel.getPlayer().screenX
                && worldY + gamePanel.getTileSize() > gamePanel.getPlayer().worldY - gamePanel.getPlayer().screenY
                && worldY - gamePanel.getTileSize() < gamePanel.getPlayer().worldY + gamePanel.getPlayer().screenY)
        {
            //set image counter
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
                case "teleport" -> {
                    drawTeleportBar(graphics2D);
                    canTeleport = true;
                    nextTeleportCounter++;
                    if (spriteImageNumber == 1) {
                        image = this.image;
                    }
                    if (spriteImageNumber == 2) {
                        image = this.image2;
                    }
                    if (nextTeleportCounter == 15 && canTeleport) {
                        gamePanel.playSoundEffect(8);
                        teleport();
                        nextTeleportCounter = 0;
                        direction = "down";
                    }
                }
            }

            //change draw based on attributes
            if (isInvincible) {
                drawHealthBar(graphics2D);
                graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            }
            if (isDying) {
                dyingAnimation(graphics2D);
            }

            //draw and reset
            graphics2D.drawImage(image, screenX, screenY, null);
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }
    //save valid teleport locations and move there
    private void teleport() {
        //x and y chords synchronized
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();

        //set i and c for how big the area it checks
        for (int i = 0; i < 8; i++) {
            for (int c = 0; c < 8; c++) {
                int tempX = ((this.worldX / gamePanel.getTileSize()) - 6 + i);
                int tempY = ((this.worldY / gamePanel.getTileSize()) - 6 + c);
                //check if not on the map
                if (tempX < 49 && tempY < 49 && tempX > 0 && tempY > 0) {
                    //check if tile is solid or not
                    if (!(gamePanel.getTileManager().mapTileNum[tempX][tempY] == 1)
                            && !(gamePanel.getTileManager().mapTileNum[tempX][tempY] == 9)
                            && !(gamePanel.getTileManager().mapTileNum[tempX][tempY] == 25)
                            && !(gamePanel.getTileManager().mapTileNum[tempX][tempY] == 26)
                            && !(gamePanel.getTileManager().mapTileNum[tempX][tempY] == 27)
                            && !(gamePanel.getTileManager().mapTileNum[tempX][tempY] == 28)
                            && !(gamePanel.getTileManager().mapTileNum[tempX][tempY] == 29))
                    {
                        x.add(tempX);
                        y.add(tempY);
                    }
                }
            }
        }
        //check again if any place is not valid it's checked tho '
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
        this.setMoveImageCounter++;

        //set to new chords randomly
        this.worldX = x.get(randomNum) * gamePanel.getTileSize();
        this.worldY = y.get(randomNum) * gamePanel.getTileSize();

        // prevent start new teleport cycle
        this.canTeleport = false;
    }
    //draw counter when teleport
    public void drawTeleportBar(Graphics2D graphics2D) {
            //set values
            int screenX = this.worldX - gamePanel.getPlayer().worldX + gamePanel.getPlayer().screenX;
            int screenY = this.worldY - gamePanel.getPlayer().worldY + gamePanel.getPlayer().screenY;
            double oneScale = (double) gamePanel.getTileSize() / 20;
            double healthBarValue = oneScale * this.nextTeleportCounter;

            //draw scale
            graphics2D.setColor(new Color(35, 35, 35));
            graphics2D.fillRect(screenX - 1, screenY - 32, gamePanel.getTileSize(), 10);

            graphics2D.setColor(new Color(25, 50, 255));
            graphics2D.fillRect(screenX - 1, screenY - 32, (int) healthBarValue, 10);

    }
    //load image files
    public void getImage() {

        image = setup("/entities/mage/Mage Teleport Ready", gamePanel.getTileSize(), gamePanel.getTileSize());
        image2 = setup("/entities/mage/Mage Teleport 2", gamePanel.getTileSize(), gamePanel.getTileSize());

        up1 = setup("/entities/mage/Mage Up1", gamePanel.getTileSize(), gamePanel.getTileSize());
        up2 = setup("/entities/mage/Mage Up2", gamePanel.getTileSize(), gamePanel.getTileSize());

        right1 = setup("/entities/mage/Mage 2", gamePanel.getTileSize(), gamePanel.getTileSize());
        right2 = setup("/entities/mage/Mage 2", gamePanel.getTileSize(), gamePanel.getTileSize());

        left1 = setup("/entities/mage/Mage 1", gamePanel.getTileSize(), gamePanel.getTileSize());
        left2 = setup("/entities/mage/Mage 1", gamePanel.getTileSize(), gamePanel.getTileSize());

        down1 = setup("/entities/mage/Mage 1", gamePanel.getTileSize(), gamePanel.getTileSize());
        down2 = setup("/entities/mage/Mage 2", gamePanel.getTileSize(), gamePanel.getTileSize());

    }
}