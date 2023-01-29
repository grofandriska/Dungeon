package org.example.Entity.Objects.inventory;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

public class OBJ_SWORD extends Entity {

    public OBJ_SWORD(GamePanel gamePanel) {
        super(gamePanel);
        name = "Sword";
        image = setup("/Pack/icons/32x32/sword_01a",16,16);
        attackValue = 2;
    }
}
