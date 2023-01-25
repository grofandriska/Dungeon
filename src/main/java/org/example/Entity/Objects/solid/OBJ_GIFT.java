package org.example.Entity.Objects.solid;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;




public class OBJ_GIFT extends Entity {

    public OBJ_GIFT(GamePanel gamePanel) {

        super(gamePanel);
        name = "Gift";
        collision = true;
        collisionOn = true;//?
        getImage();
    }

    public void getImage() {
        up1 = setup("/objects/gift_01a",gamePanel.tileSize,gamePanel.tileSize);
        up2 = setup("/objects/gift_01a",gamePanel.tileSize,gamePanel.tileSize);
        right1 = setup("/objects/gift_01a",gamePanel.tileSize,gamePanel.tileSize);
        right2 = setup("/objects/gift_01a",gamePanel.tileSize,gamePanel.tileSize);
        left1 = setup("/objects/gift_01a",gamePanel.tileSize,gamePanel.tileSize);
        left2 = setup("/objects/gift_01a",gamePanel.tileSize,gamePanel.tileSize);
        down1 = setup("/objects/gift_01a",gamePanel.tileSize,gamePanel.tileSize);
        down2 = setup("/objects/gift_01a",gamePanel.tileSize,gamePanel.tileSize);
    }

}
