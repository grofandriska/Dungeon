package org.example.Entity.player;

import org.example.Entity.Entity;
import org.example.Entity.Objects.consum.OBJ_Key;
import org.example.Entity.Objects.consum.OBJ_SpeedPotion;
import org.example.Entity.Objects.inventory.OBJ_SHIELD;
import org.example.Entity.Objects.inventory.OBJ_SWORD;
import org.example.Entity.Objects.solid.OBJ_GIFT;
import org.example.Game.GamePanel;
import org.example.Handler.input.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Player extends Entity {
    //dependency
    public Random random;
    public KeyHandler keyHandler;

    //player attributes
    public int screenY, screenX;
    boolean isAttacking, isCritical = false;
    public final int inventorySize;
    public ArrayList<Entity> inventory = new ArrayList<>();

    //constructor
    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
        this.random = new Random();
        this.keyHandler = keyHandler;

        //player attributes
        this.type = 3;
        this.inventorySize = 20;

        //solid area
        this.solidAreaRectangle = new Rectangle();
        this.solidAreaRectangle.x = 10;
        this.solidAreaRectangle.y = 16;
        this.solidAreaRectangle.width = 30;
        this.solidAreaRectangle.height = 30;
        this.solidAreaDefaultX = solidAreaRectangle.x;
        this.solidAreaDefaultY = solidAreaRectangle.y;

        setDefaultValues();
        setPlayerImage();
    }

    //init fields
    public void setDefaultValues() {
        //player stats
        this.name = "Player_1";
        this.speed = 3;
        this.level = 1;
        this.maxLife = 12;
        this.life = maxLife;
        this.strength = 1;
        this.dexterity = 1;
        this.exp = 0;
        this.nextLevelExp = 4;
        this.coin = 0;

        //player attributes
        this.worldX = gamePanel.getTileSize() * 13;
        this.worldY = gamePanel.getTileSize() * 45;
        this.attackAreaRectangle.height = 36;
        this.attackAreaRectangle.width = 36;

        setInventory();

    }

    //stack up inventory and set some field
    public void setInventory() {
        inventory.add(new OBJ_SWORD(gamePanel));
        inventory.add(new OBJ_SHIELD(gamePanel));
        inventory.add(new OBJ_SpeedPotion(gamePanel));
        inventory.add(new OBJ_SpeedPotion(gamePanel));
        inventory.add(new OBJ_Key(gamePanel));
        inventory.add(new OBJ_Key(gamePanel));
        inventory.add(new OBJ_GIFT(gamePanel));
        inventory.add(new OBJ_GIFT(gamePanel));

        this.currentWeapon = inventory.get(0);
        this.currentShield = inventory.get(1);

        this.attack = getAttack();
        this.defense = getDefense();
    }

    //TODO :refactor if statements * separate logic
    public void setOrConsumeItemFromInventory() {
        //itemIndex
        int itemIndex = gamePanel.getUserInterface().getItemIndexFromSlot();

        //if size is in inventory
        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);

            //if type == inventory type
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

            //if consumable
            if (selectedItem.type == 4) {
                selectedItem.consume();
                inventory.remove(itemIndex);
            }
        }
    }

    //check surround, change images, set counters
    public void update() {
        //attack "state"
        if (isAttacking) attacking();
            //if moving key pressed
        else if (keyHandler.isDownPressed() || keyHandler.isUpPressed() || keyHandler.isLeftPressed() || keyHandler.isRightPressed() || keyHandler.isEnterPressed()) {
            if (keyHandler.isUpPressed()) direction = "up";
            else if (keyHandler.isDownPressed()) direction = "down";
            else if (keyHandler.isRightPressed()) direction = "right";
            else if (keyHandler.isLeftPressed()) direction = "left";

            //check surrounding
            this.isCollisionOn = false;
            gamePanel.getEventHandler().checkEvent();
            gamePanel.getCollisionChecker().checkBorder(this);
            gamePanel.getCollisionChecker().checkTile(this);
            gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getNpc());
            gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getMonsters());

            pickupObject(gamePanel.getCollisionChecker().checkObject(this, true));
            interactNPC(gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getNpc()));
            contactMonster(gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getMonsters()));
            //move player on map
            if (!isCollisionOn && !keyHandler.isEnterPressed()) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }
            gamePanel.getKeyHandler().setEnterPressed(false);
            //setting image when moving
            setMoveImageCounter++;
            if (setMoveImageCounter > 6) {
                if (spriteImageNumber == 1) {
                    spriteImageNumber = 2;
                } else if (spriteImageNumber == 2) {
                    spriteImageNumber = 1;
                }
                setMoveImageCounter = 0;
            }
        }
        //setting invincible timer
        if (isInvincible) {
            setInvincibleCounter++;
            if (setInvincibleCounter > 60) {
                isInvincible = false;
                setInvincibleCounter = 0;
            }
        }
        //die logic
        if (life <= 0) {
            gamePanel.setGameState(4);
            gamePanel.stopMusic();
            gamePanel.playSoundEffect(5);
        }
    }

    private void attacking() {

        //set image variable
        this.setMoveImageCounter++;
        if (this.setMoveImageCounter <= 5) {
            this.spriteImageNumber = 1;
        }
        if (this.setMoveImageCounter <= 25 && this.setMoveImageCounter > 5) {
            this.spriteImageNumber = 2;
        }
        if (this.setMoveImageCounter > 25 && this.setMoveImageCounter < 35) {
            this.spriteImageNumber = 1;
        }
        if (this.setMoveImageCounter > 35 && this.setMoveImageCounter < 45) {
            this.spriteImageNumber = 2;
        }

        //save player proper position since attack image 64 x 32
        int currentWorldX = this.worldX;
        int currentWorldY = this.worldY;
        int currentSolidAreaWidth = this.solidAreaRectangle.width;
        int currentSolidAreaHeight = this.solidAreaRectangle.height;

        //set new position
        switch (direction) {
            case "up" -> this.worldY -= this.attackAreaRectangle.height;
            case "down" -> this.worldY += this.attackAreaRectangle.height;
            case "left" -> this.worldX -= this.attackAreaRectangle.width;
            case "right" -> this.worldX += this.attackAreaRectangle.width;
        }

        //set player solid area for the new location ?!
        this.solidAreaRectangle.width = this.attackAreaRectangle.width;
        this.solidAreaRectangle.height = this.attackAreaRectangle.height;

        //get monster index and do damage
        int monsterIndex = gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getMonsters());
        damageMonster(monsterIndex);

        //change back player position
        this.worldX = currentWorldX;
        this.worldY = currentWorldY;
        this.solidAreaRectangle.width = currentSolidAreaWidth;
        this.solidAreaRectangle.height = currentSolidAreaHeight;

        //reset image and counter
        if (this.setMoveImageCounter > 45) {
            this.setMoveImageCounter = 0;
            this.isAttacking = false;
        }
    }

    //calculate damage and update entity
    private void damageMonster(int i) {
        if (i != 999) {
            //calculate damage
            if (!gamePanel.getMonsters()[i].isInvincible) {
                int damage = generateCriticalAttack() - gamePanel.getMonsters()[i].defense;
                if (damage < 0) {
                    damage = 0;
                }
                //set entity attributes
                gamePanel.getMonsters()[i].life -= damage;
                gamePanel.getMonsters()[i].isInvincible = true;
                gamePanel.getMonsters()[i].damageReaction();
                //add scroll message
                if (isCritical) {
                    gamePanel.getUserInterface().addMessage("*" + damage + "*" + " critical damage dealt to " + gamePanel.getMonsters()[i].name + " !");
                } else {
                    gamePanel.getUserInterface().addMessage(damage + " damage dealt to " + gamePanel.getMonsters()[i].name + " !");
                }
                //reset and playSE
                isCritical = false;
                gamePanel.playSoundEffect(2);
                //check if entity life <= 0 and level up
                if (gamePanel.getMonsters()[i].life <= 0) {
                    gamePanel.getMonsters()[i].isDying = true;
                    gamePanel.getPlayer().exp += gamePanel.getMonsters()[i].exp;
                    gamePanel.getUserInterface().addMessage("Killed " + gamePanel.getMonsters()[i].name + " !  +" + gamePanel.getMonsters()[i].exp + " xp");
                    gamePanel.playSoundEffect(10);
                    gamePanel.getMonsters()[i] = null;
                    checkLevelUp();
                }
            }
        }
    }

    //levelUp ,set new attributes
    private void checkLevelUp() {
        if (exp > nextLevelExp) {

            //change attributes
            level++;
            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();
            nextLevelExp *= 2;
            maxLife += 2;
            life += 1;
            exp = 0;

            // set to dialog
            gamePanel.setGameState(gamePanel.getDialogState());
            gamePanel.getUserInterface().currentDialog = "Level " + level + " reached!\n" + "Your stats improved.";
        }
    }

    //more damage if random
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

    //TODO :implement
    private void contactMonster(int i) {
        if (i != 999) {
            if (!isInvincible) {
                int damage = gamePanel.getMonsters()[i].attack;
            }
        }
    }

    //call npc methods - speak so far
    public void interactNPC(int index) {
        if (gamePanel.getKeyHandler().isEnterPressed()) {
            if (index != 999) {
                gamePanel.setGameState( gamePanel.getDialogState());
                gamePanel.getNpc()[index].speak();
            } else {
                isAttacking = true;
            }
        }
    }

    //set image file, draw player
    public void draw(Graphics2D graphics2D) {
        //variables
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        //set image 1 or 2 based on direction
        switch (direction) {
            case "up" -> {
                if (isAttacking) {
                    tempScreenY = screenY - gamePanel.getTileSize();
                    if (spriteImageNumber == 1) {
                        image = attackUp_1;
                    }
                    if (spriteImageNumber == 2) {
                        image = attackUp_2;
                    }
                }
                if (!isAttacking) {
                    if (spriteImageNumber == 1) {
                        image = up1;
                    }
                    if (spriteImageNumber == 2) {
                        image = up2;
                    }
                }
            }
            case "down" -> {
                if (isAttacking) {
                    if (spriteImageNumber == 1) {
                        image = attackDown_1;
                    }
                    if (spriteImageNumber == 2) {
                        image = attackDown_2;
                    }
                }

                if (!isAttacking) {
                    if (spriteImageNumber == 1) {
                        image = down1;
                    }
                    if (spriteImageNumber == 2) {
                        image = down2;
                    }
                }
            }
            case "right" -> {
                if (isAttacking) {
                    if (spriteImageNumber == 1) {
                        image = attackRight_1;
                    }
                    if (spriteImageNumber == 2) {
                        image = attackRight_2;
                    }
                }

                if (!isAttacking) {
                    if (spriteImageNumber == 1) {
                        image = right1;
                    }
                    if (spriteImageNumber == 2) {
                        image = right2;
                    }
                }
            }
            case "left" -> {
                if (isAttacking) {
                    tempScreenX = screenX - gamePanel.getTileSize();
                    if (spriteImageNumber == 1) {

                        image = attackLeft_1;
                    }
                    if (spriteImageNumber == 2) {
                        image = attackLeft_2;
                    }
                }
                if (!isAttacking) {
                    if (spriteImageNumber == 1) {
                        image = left1;
                    }
                    if (spriteImageNumber == 2) {
                        image = left2;
                    }
                }
            }
        }

        //set invincible state
        if (isInvincible) {
            if (life >= 0) {
                drawHealthBar(graphics2D);
            }
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        //draw character and reset blink
        graphics2D.drawImage(image, tempScreenX, tempScreenY, null);
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    //add item to inventory
    public void pickupObject(int i) {
        if (i != 999) {
            // if not door
            if (!gamePanel.getObjects()[i].name.equals("door")) {
                String text = "";
                if (inventory.size() != inventorySize) {
                    inventory.add(gamePanel.getObjects()[i]);
                    text = "You've picked up a(n)" + gamePanel.getObjects()[i].name + "!";
                } else {
                    text = "Inventory is full ,can't carry more !";
                }
                gamePanel.getUserInterface().addMessage(text);
                gamePanel.getObjects()[i] = null;
            }
            // if  door
            else if (gamePanel.getObjects()[i].name.equals("door") && gamePanel.getPlayer().keyHandler.isoPressed()) {

                gamePanel.getPlayer().keyHandler.setoPressed(false);
                String keyName = gamePanel.getObjects()[i].keyName;
                boolean isOpen = false;

                for (int t = 0; t < this.inventory.size(); t++) {
                    if (inventory.get(t).name.equals(keyName)) {
                        inventory.remove(t);
                        gamePanel.getObjects()[i] = null;
                        isOpen = true;
                        break;
                    }
                }
                if (!isOpen) {
                    gamePanel.getUserInterface().addMessage("you need a(n) " + keyName + " for this door");
                }
            }
        }
    }

    //calculate (new) attack value
    public int getAttack() {
        if (currentWeapon != null) {
            attackAreaRectangle = currentWeapon.attackAreaRectangle;
            return attack = strength * currentWeapon.attackValue;
        }
        return 1;
    }

    //calculate (new) deffense value
    public int getDefense() {
        if (currentShield != null) {
            return defense = dexterity * currentShield.defenseValue;
        }
        return 0;
    }

    //load image files
    public void setPlayerImage() {
        up1 = setup("/player/NHU1", gamePanel.getTileSize(), gamePanel.getTileSize());
        up2 = setup("/player/NHU2", gamePanel.getTileSize(), gamePanel.getTileSize());

        down1 = setup("/player/NHD1", gamePanel.getTileSize(), gamePanel.getTileSize());

        down2 = setup("/player/NHD2", gamePanel.getTileSize(), gamePanel.getTileSize());
        right1 = setup("/player/NHR1", gamePanel.getTileSize(), gamePanel.getTileSize());

        right2 = setup("/player/NHR2", gamePanel.getTileSize(), gamePanel.getTileSize());
        left1 = setup("/player/NHL1", gamePanel.getTileSize(), gamePanel.getTileSize());
        left2 = setup("/player/NHL2", gamePanel.getTileSize(), gamePanel.getTileSize());

        attackDown_1 = setup("/player/attack 1_1", gamePanel.getTileSize(), gamePanel.getTileSize() * 2);
        attackDown_2 = setup("/player/attack 1_2", gamePanel.getTileSize(), gamePanel.getTileSize() * 2);


        attackUp_1 = setup("/player/attack 2_1", gamePanel.getTileSize(), gamePanel.getTileSize() * 2);
        attackUp_2 = setup("/player/attack 2_2", gamePanel.getTileSize(), gamePanel.getTileSize() * 2);

        attackRight_1 = setup("/player/attack 3_1", gamePanel.getTileSize() * 2, gamePanel.getTileSize());
        attackRight_2 = setup("/player/attack 3_2", gamePanel.getTileSize() * 2, gamePanel.getTileSize());

        attackLeft_2 = setup("/player/attack 4_2", gamePanel.getTileSize() * 2, gamePanel.getTileSize());
        attackLeft_1 = setup("/player/attack 4_1", gamePanel.getTileSize() * 2, gamePanel.getTileSize());

    }
}
