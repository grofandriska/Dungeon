package org.example.Entity.Objects.inventory;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

public class OBJ_SWORD extends Entity {

    public OBJ_SWORD(GamePanel gamePanel) {
        super(gamePanel);
        name = "Sword";
        image = setup("/Pack/icons/32x32/sword_01a", gamePanel.tileSize, gamePanel.tileSize);
        direction = "down";
        down1 = setup("/Pack/icons/32x32/sword_01a", gamePanel.tileSize, gamePanel.tileSize);
        attackValue = 2;
        description = "[" + name + "]\n" + "A wooden sword,only for protection \n" + "\n" + "att " + attackValue;
        type = 5;
        attackRectangle.width = 32;
        attackRectangle.height = 32;
    }
}
