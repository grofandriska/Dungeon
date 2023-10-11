package org.example.entity.objects.inventory;

import org.example.entity.Entity;
import org.example.Game.GamePanel;

public class OBJ_SWORD extends Entity {

    public OBJ_SWORD(GamePanel gamePanel) {
        super(gamePanel);

        //entity attributes
        this.name = "Sword";
        this.type = 5;
        this.attackValue = 2;
        this.description = "[" + name + "]\n" + "A wooden sword,only for protection \n" + "\n" + "att " + attackValue;

        //entity static
        this.attackAreaRectangle.width = 32;
        this.attackAreaRectangle.height = 32;
        this.down1 = setup("/Pack/icons/32x32/sword_01a", gamePanel.getTileSize(), gamePanel.getTileSize());
        this.image = setup("/Pack/icons/32x32/sword_01a", gamePanel.getTileSize(), gamePanel.getTileSize());
    }
}
