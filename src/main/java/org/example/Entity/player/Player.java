package org.example.Entity.player;

import org.example.Entity.Entity;
import org.example.Entity.Objects.consum.OBJ_Key;
import org.example.Entity.Objects.consum.OBJ_Potion;
import org.example.Entity.Objects.consum.OBJ_SpeedPotion;
import org.example.Entity.Objects.inventory.OBJ_SHIELD;
import org.example.Entity.Objects.inventory.OBJ_SWORD;
import org.example.Entity.Objects.solid.OBJ_GIFT;
import org.example.Game.GamePanel;
import org.example.Handler.input.KeyHandler;
import org.example.Sound.Sound;
import org.example.UI.UI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Player extends Entity {
    boolean isCritical = false;
    Random random = new Random();
    KeyHandler keyHandler;

    // move to Entity

    public ArrayList<Entity> inventory = new ArrayList<>();

    public final int inventorySize = 20;

    public String playerName = "Bandi";
    public boolean isAttacking;
    public  int screenX;
    public  int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
        name = "Player";

        this.speed = 1;
        this.keyHandler = keyHandler;

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

        worldX = gamePanel.tileSize * 6;
        worldY = gamePanel.tileSize * 9;

        attackRectangle.height = 36;
        attackRectangle.width = 36;

        level = 1;
        type = 3;
        speed = 2;
        maxLife = 12;
        life = maxLife;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 4;
        coi = 0;

        setItems();

        currentWeapon = inventory.get(0);
        currentShield = inventory.get(1);

        attack = getAttack();
        defense = getDefense();

    }

    public void setItems() {
        inventory.add(new OBJ_SWORD(gamePanel));
        inventory.add(new OBJ_SHIELD(gamePanel));
        inventory.add(new OBJ_SpeedPotion(gamePanel));
        inventory.add(new OBJ_SpeedPotion(gamePanel));
        inventory.add(new OBJ_Key(gamePanel));
        inventory.add(new OBJ_Key(gamePanel));
        inventory.add(new OBJ_GIFT(gamePanel));
        inventory.add(new OBJ_GIFT(gamePanel));
    }

    public void selectItem() {
        int itemIndex = gamePanel.UI.getItemIndexFromSlot();
        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);
            if (selectedItem.type == 5) {
                if (selectedItem.attackValue > 0) {
                    currentWeapon = selectedItem;
                    attack = getAttack();
                }
                if (selectedItem.defenseValue > 0) {
                    currentShield = selectedItem;
                    defense = getDefense();
                }
            }
            if (selectedItem.type == 4) {
                selectedItem.consume();
                inventory.remove(itemIndex);
            }
        }
    }

    //use in entity and npc
    public int getAttack() {
        attackRectangle = currentWeapon.attackRectangle;

        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = dexterity * currentShield.defenseValue;
    }

    public void update() {
        if (isAttacking) {
            attacking();
        } else if (keyHandler.downPressed || keyHandler.upPressed || keyHandler.leftPressed || keyHandler.rightPressed || keyHandler.enterPressed) {

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

            //check surround
            gamePanel.collisionChecker.checkTile(this);
            gamePanel.collisionChecker.checkBorder(this);
            gamePanel.collisionChecker.checkEntity(this, gamePanel.monsters);
            gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
            gamePanel.collisionChecker.checkEntity(this, gamePanel.entities);
            gamePanel.eventHandler.checkEvent();


            pickupObject(gamePanel.collisionChecker.checkObject(this, true));

            interactNPC(gamePanel.collisionChecker.checkEntity(this, gamePanel.npc));

            contactMonster(gamePanel.collisionChecker.checkEntity(this, gamePanel.monsters));

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
            gamePanel.stopMusic();
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
                int damage = generateCriticalAttack() - gamePanel.monsters[i].defense;
                if (damage < 0) {
                    damage = 0;
                }
                gamePanel.monsters[i].life -= damage;
                if (isCritical) {
                    gamePanel.UI.addMessage("*" + damage + "*" + " critical damage dealt to " + gamePanel.monsters[i].name + " !");
                } else {
                    gamePanel.UI.addMessage(damage + " damage dealt to " + gamePanel.monsters[i].name + " !");
                }
                isCritical = false;
                gamePanel.monsters[i].invincible = true;
                gamePanel.monsters[i].damageReaction();
                gamePanel.playSoundEffect(2);

                if (gamePanel.monsters[i].life <= 0) {
                    gamePanel.monsters[i].dying = true;
                    gamePanel.player.exp += gamePanel.monsters[i].exp;
                    gamePanel.UI.addMessage("Killed " + gamePanel.monsters[i].name + " !  +" + gamePanel.monsters[i].exp + " xp");
                    gamePanel.playSoundEffect(10);
                    checkLevelUp();

                    gamePanel.monsters[i] = null;
                }
            }
        }
    }

    private void checkLevelUp() {
        if (exp > nextLevelExp) {
            level++;
            nextLevelExp = nextLevelExp * 2;
            maxLife += 2;
            strength++;
            dexterity++;
            life = maxLife;
            attack = getAttack(); //reset attack value
            defense = getDefense();
            gamePanel.gameState = gamePanel.dialogState;
            gamePanel.UI.currentDialog = "Level " + level + " reached!\n" + "Your stats improved.";
        }
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

    private void contactMonster(int i) {
        if (i != 999) {
            if (!invincible) {
                int damage = gamePanel.monsters[i].attack;
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

    public void draw(Graphics2D graphics2D) {
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
            if (life >= 0) {
                drawHealthBar(graphics2D);
            }

            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        graphics2D.drawImage(image, tempScreenX, tempScreenY, null);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    // implement to NPC to make em work and so on
    public void pickupObject(int i) {
        if (i != 999) {
            String text = "";
            if (inventory.size() != inventorySize) {
                inventory.add(gamePanel.objects[i]);
                text = "You've picked up a(n)" + gamePanel.objects[i].name + "!";
            } else {
                text = "Inventory is full ,can't carry more !";
            }
            gamePanel.UI.addMessage(text);
            gamePanel.objects[i] = null;
        }
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
