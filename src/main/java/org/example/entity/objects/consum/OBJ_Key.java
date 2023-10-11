package org.example.entity.objects.consum;

import org.example.entity.Entity;
import org.example.Game.GamePanel;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gamePanel) {
        super(gamePanel);

        //entity attribute
        this.name = "Key";
        this.description = "[" + name + "]\n" + "It's a key made from silver.\nNot any sign or mark.";
        this.image = setup("/objects/key_01c", gamePanel.getTileSize(), gamePanel.getTileSize());

        //entity static
        this.isCollisionOn = true;
        this.solidAreaRectangle.x = 0;
        this.solidAreaRectangle.y = 16;
        this.solidAreaRectangle.width = 48;
        this.solidAreaRectangle.height = 32;
        this.solidAreaDefaultX = solidAreaRectangle.x;
        this.solidAreaDefaultY = solidAreaRectangle.y;

    }
}
