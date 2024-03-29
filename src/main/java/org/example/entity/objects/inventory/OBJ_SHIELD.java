package org.example.entity.objects.inventory;

import org.example.entity.Entity;
import org.example.Game.GamePanel;

public class OBJ_SHIELD extends Entity {


    public OBJ_SHIELD(GamePanel gamePanel) {
        super(gamePanel);

        //entity attributes
        this.type = 5;
        this.name = "Shield";
        this.defenseValue = 1;
        this.description = "[" + name + "]\n" + "A basic shield.\n" + "\n" + "def " + defenseValue;

        //entity static
        this.image = setup("/Pack/icons/32x32/shield_03d", gamePanel.getTileSize(), gamePanel.getTileSize());
        this.down1 = setup("/Pack/icons/32x32/shield_03d", gamePanel.getTileSize(), gamePanel.getTileSize());
    }
}
