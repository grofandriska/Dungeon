package org.example.Objects;

import org.example.Entity.Entity;
import org.example.game.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends Entity {



    public OBJ_Key(GamePanel gamePanel) {
        super(gamePanel);

        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key_01c.png"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
