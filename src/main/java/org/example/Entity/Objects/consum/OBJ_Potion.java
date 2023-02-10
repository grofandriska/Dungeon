package org.example.Entity.Objects.consum;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;


public class OBJ_Potion extends Entity {

    public OBJ_Potion(GamePanel gamePanel) {
        super(gamePanel);

        //entity attributes
        this.type = 4;
        this.name = "Potion";
        this.description = "[" + name + "]\n" + "It's a potion of speed. Use it in a hurry";

        getImage();
    }

    public void getImage() {
        this.up1 = setup("/objects/potion_01h", gamePanel.tileSize, gamePanel.tileSize);
        this.up2 = setup("/objects/potion_01h", gamePanel.tileSize, gamePanel.tileSize);
        this.right1 = setup("/objects/potion_01h", gamePanel.tileSize, gamePanel.tileSize);
        this.right2 = setup("/objects/potion_01h", gamePanel.tileSize, gamePanel.tileSize);
        this.left1 = setup("/objects/potion_01h", gamePanel.tileSize, gamePanel.tileSize);
        this.left2 = setup("/objects/potion_01h", gamePanel.tileSize, gamePanel.tileSize);
        this.down1 = setup("/objects/potion_01h", gamePanel.tileSize, gamePanel.tileSize);
        this.down2 = setup("/objects/potion_01h", gamePanel.tileSize, gamePanel.tileSize);
        this.image = setup("/objects/potion_01h", gamePanel.tileSize, gamePanel.tileSize);
    }

}
