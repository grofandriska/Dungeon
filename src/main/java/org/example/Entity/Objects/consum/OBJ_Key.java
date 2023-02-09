package org.example.Entity.Objects.consum;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gamePanel) {
        super(gamePanel);

        name = "Key";
        image = setup("/objects/key_01c", gamePanel.tileSize, gamePanel.tileSize);
        collisionOn = true;

        solidArea.x = 0;
        solidArea.y = 16;

        solidArea.width = 48;
        solidArea.height = 32;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        description = "[" + name + "]\n" + "It's a key made from silver.\nNot any sign or mark.";
    }
}
