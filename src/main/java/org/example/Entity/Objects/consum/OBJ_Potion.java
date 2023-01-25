package org.example.Entity.Objects.consum;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;



public class OBJ_Potion extends Entity {

    public OBJ_Potion(GamePanel gamePanel) {
       super(gamePanel);
        name = "Potion";
        direction = "down";
        image = setup("/objects/potion_01h",gamePanel.tileSize,gamePanel.tileSize);
        getImage();
    }
    public void getImage() {
        up1 = setup("/objects/potion_01h",gamePanel.tileSize,gamePanel.tileSize);
        up2 = setup("/objects/potion_01h",gamePanel.tileSize,gamePanel.tileSize);
        right1 = setup("/objects/potion_01h",gamePanel.tileSize,gamePanel.tileSize);
        right2 = setup("/objects/potion_01h",gamePanel.tileSize,gamePanel.tileSize);
        left1 = setup("/objects/potion_01h",gamePanel.tileSize,gamePanel.tileSize);
        left2 = setup("/objects/potion_01h",gamePanel.tileSize,gamePanel.tileSize);
        down1 = setup("/objects/potion_01h",gamePanel.tileSize,gamePanel.tileSize);
        down2 = setup("/objects/potion_01h",gamePanel.tileSize,gamePanel.tileSize);
    }
}
