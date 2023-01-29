package org.example.Entity.Objects.inventory;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

public class OBJ_SHIELD extends Entity {


    public OBJ_SHIELD(GamePanel gamePanel) {
        super(gamePanel);
        name = "Shield";
        image = setup("/Pack/icons/32x32/shield_03d", 16, 16);
        defenseValue = 1;
    }
}
