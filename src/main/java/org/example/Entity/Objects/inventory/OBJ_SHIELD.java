package org.example.Entity.Objects.inventory;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

public class OBJ_SHIELD extends Entity {


    public OBJ_SHIELD(GamePanel gamePanel) {
        super(gamePanel);


        //Make ENUM PLS
        type = 5;
        name = "Shield";
        image = setup("/Pack/icons/32x32/shield_03d", gamePanel.tileSize, gamePanel.tileSize);
        direction = "down";
        down1 = setup("/Pack/icons/32x32/shield_03d", gamePanel.tileSize, gamePanel.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\n" + "A basic shield.\n" + "\n" + "def " + defenseValue;
    }
}
