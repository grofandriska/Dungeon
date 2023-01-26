package org.example.Entity.npc;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

public class Soldier extends Entity {
    public Soldier(GamePanel gamePanel) {
        super(gamePanel);
        getImage();
        name = "Soldier";
        direction = "down";
        speed = 1 ;
    }

    public void getImage() {
        up1 = setup("/entities/soldier/Soldier Up1",gamePanel.tileSize,gamePanel.tileSize);
        up2 = setup("/entities/soldier/Soldier Up2",gamePanel.tileSize,gamePanel.tileSize);

        right1 = setup("/entities/soldier/Soldier Right 1",gamePanel.tileSize,gamePanel.tileSize);
        right2 = setup("/entities/soldier/Soldier Right 2",gamePanel.tileSize,gamePanel.tileSize);

        left1 = setup("/entities/soldier/Soldier Left 1",gamePanel.tileSize,gamePanel.tileSize);
        left2 = setup("/entities/soldier/Soldier Left 2",gamePanel.tileSize,gamePanel.tileSize);
        down1 = setup("/entities/soldier/Soldier Down 1",gamePanel.tileSize,gamePanel.tileSize);
        down2 = setup("/entities/soldier/Soldier Down 2",gamePanel.tileSize,gamePanel.tileSize);
    }
}
