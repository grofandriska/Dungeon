package org.example.Entity.Objects.inventory;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

public class OBJ_STAFF extends Entity {
    public OBJ_STAFF(GamePanel gamePanel) {
        super(gamePanel);
        type = 5;
        name = "Staff";
        image = setup("/Pack/icons/32x32/staff_02ab", gamePanel.tileSize, gamePanel.tileSize);
        direction = "down";
        down1 = setup("/Pack/icons/32x32/staff_02ab", gamePanel.tileSize, gamePanel.tileSize);
        attackValue = 1;
        description = "[" + name + "]\n" + "Used for cast magic.\n" + ".Can deal melee dmg\n" + "\n" + "att " + attackValue;
        attackRectangle.width = 40;
        attackRectangle.height = 40;
    }
}
