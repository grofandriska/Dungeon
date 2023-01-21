package org.example.Objects;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;



public class OBJ_Potion extends Entity {

    public OBJ_Potion(GamePanel gamePanel) {
       super(gamePanel);
        name = "Potion";
        direction = "down";
        image = setup("/objects/potion_01h.png");

    }
}
