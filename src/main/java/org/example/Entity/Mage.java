package org.example.Entity;

import org.example.game.GamePanel;

public class Mage extends Entity{
    public Mage(GamePanel gamePanel) {
        super(gamePanel);
        getImage();
        this.direction = "up";
        this.speed = 1;
    }

    public void getImage() {
        up1 = setup("/entities/Mage Up1");
        up2 = setup("/entities/Mage Up2");
        right = setup("/entities/Mage 2");
        right1 = setup("/entities/Mage 2");
        right2 = setup("/entities/Mage 2");
        left = setup("/entities/Mage 1");
        left1 = setup("/entities/Mage 1");
        left2 = setup("/entities/Mage 1");
        down1 = setup("/entities/Mage 1");
        down2 = setup("/entities/Mage 2");
    }
}
