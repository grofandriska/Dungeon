package org.example.Entity.npc;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

public class Mage extends Entity {
    public Mage(GamePanel gamePanel) {
        super(gamePanel);
        type = 1;
        name = "Mage";
        speed = 1;
        maxLife = 4;
        life = maxLife;

        getImage();
        setDialog();

    }

    public void getImage() {

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
        dialogs[0] = "Be Aware! \nOrcs are everywhere!\n Do you know how to handle?!\n";
        dialogs[1] = "When you get close,\n press ENTER to attack!";
        dialogs[2] = "Blinking means entity\ncan't receive damage.";
        dialogs[3] = "Let's see how many \ncan you kill ...";
        dialogs[4] = "...";
    }
    public void checkDistance(Entity entity) {
        boolean isTrue = false;

        int xDistance = Math.abs(gamePanel.player.worldX - entity.worldX);
        int yDistance = Math.abs(gamePanel.player.worldY - entity.worldY);
        int distance = Math.max(xDistance, yDistance);
        if (distance < gamePanel.tileSize * 3){
            teleport();
        }

    }

    private void teleport() {

    }
}