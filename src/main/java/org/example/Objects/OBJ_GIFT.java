package org.example.Objects;

import org.example.Entity.Entity;
import org.example.game.GamePanel;


public class OBJ_GIFT extends Entity {


    public OBJ_GIFT(GamePanel gamePanel) {
        super(gamePanel);
        name = "Gift";
        down1 = setup("/objects/gift_01a");
        collision = true;
    }
}
