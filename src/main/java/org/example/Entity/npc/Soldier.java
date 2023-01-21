package org.example.Entity.npc;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

public class Soldier extends Entity {
    public Soldier(GamePanel gamePanel) {
        super(gamePanel);
        getImage();
        direction = "down";
        speed = 1 ;
    }

    public void getImage() {
        up1 = setup("/entities/Soldier Up1");
        up2 = setup("/entities/Soldier Up2");
        right = setup("/entities/Soldier Right 2");
        right1 = setup("/entities/Soldier Right 1");
        right2 = setup("/entities/Soldier Right 2");
        left = setup("/entities/Zombie Left 1");
        left1 = setup("/entities/Soldier Left 1");
        left2 = setup("/entities/Soldier Left 2");
        down1 = setup("/entities/Soldier Down 1");
        down2 = setup("/entities/Soldier Down 2");
    }
}
