package org.example.Objects.consum;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

public class OBJ_Key extends Entity {



    public OBJ_Key(GamePanel gamePanel) {
        super(gamePanel);
        name = "Key";
        down1 = setup("/objects/key_01c");
        direction = "down";
    }
}
