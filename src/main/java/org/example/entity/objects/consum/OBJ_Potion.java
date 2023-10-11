package org.example.entity.objects.consum;

import org.example.entity.Entity;
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
        this.up1 = setup("/objects/potion_01h", gamePanel.getTileSize(), gamePanel.getTileSize());
        this.up2 = setup("/objects/potion_01h", gamePanel.getTileSize(), gamePanel.getTileSize());
        this.right1 = setup("/objects/potion_01h", gamePanel.getTileSize(), gamePanel.getTileSize());
        this.right2 = setup("/objects/potion_01h", gamePanel.getTileSize(), gamePanel.getTileSize());
        this.left1 = setup("/objects/potion_01h", gamePanel.getTileSize(), gamePanel.getTileSize());
        this.left2 = setup("/objects/potion_01h", gamePanel.getTileSize(), gamePanel.getTileSize());
        this.down1 = setup("/objects/potion_01h", gamePanel.getTileSize(), gamePanel.getTileSize());
        this.down2 = setup("/objects/potion_01h", gamePanel.getTileSize(), gamePanel.getTileSize());
        this.image = setup("/objects/potion_01h", gamePanel.getTileSize(), gamePanel.getTileSize());
    }

}
