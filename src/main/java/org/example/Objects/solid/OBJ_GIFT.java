package org.example.Objects.solid;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;


public class OBJ_GIFT extends Entity {


    public OBJ_GIFT(GamePanel gamePanel) {
        super(gamePanel);
        name = "Gift";
        down1 = setup("/objects/gift_01a");
        collision = true;
    }
}
