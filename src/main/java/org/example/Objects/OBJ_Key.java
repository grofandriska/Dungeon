package org.example.Objects;

import org.example.Entity.Entity;
import org.example.game.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends Entity {



    public OBJ_Key(GamePanel gamePanel) {
        super(gamePanel);
        name = "Key";
        down1 = setup("/objects/key_01c");
        direction = "down";
    }
}
