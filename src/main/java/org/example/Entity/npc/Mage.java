package org.example.Entity.npc;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

public class Mage extends Entity {
    public Mage(GamePanel gamePanel) {
        super(gamePanel);
        type =2;
        name = "Mage";
        speed = 1;
        maxLife = 4;
        life = maxLife;

        getImage();
    }
    public void getImage() {
        up1 = setup("/entities/mage/Mage Up1",gamePanel.tileSize,gamePanel.tileSize);
        up2 = setup("/entities/mage/Mage Up2",gamePanel.tileSize,gamePanel.tileSize);

        right1 = setup("/entities/mage/Mage 2",gamePanel.tileSize,gamePanel.tileSize);
        right2 = setup("/entities/mage/Mage 2",gamePanel.tileSize,gamePanel.tileSize);

        left1 = setup("/entities/mage/Mage 1",gamePanel.tileSize,gamePanel.tileSize);
        left2 = setup("/entities/mage/Mage 1",gamePanel.tileSize,gamePanel.tileSize);

        down1 = setup("/entities/mage/Mage 1",gamePanel.tileSize,gamePanel.tileSize);
        down2 = setup("/entities/mage/Mage 2",gamePanel.tileSize,gamePanel.tileSize);
    }
}
