package org.example.Entity.Objects.inventory;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

public class OBJ_STAFF extends Entity {
    public OBJ_STAFF(GamePanel gamePanel) {
        super(gamePanel);

        //entity attributes
        this.type = 5;
        this.name = "Staff";
        this.attackValue = 1;
        this.description = "[" + name + "]\n" + "Used for cast magic.\n" + ".Can deal melee dmg\n" + "\n" + "att " + attackValue;

        //entity static
        this.attackAreaRectangle.width = 40;
        this.attackAreaRectangle.height = 40;
        this.image = setup("/Pack/icons/32x32/staff_02ab", gamePanel.tileSize, gamePanel.tileSize);
        this.down1 = setup("/Pack/icons/32x32/staff_02ab", gamePanel.tileSize, gamePanel.tileSize);
    }
}
