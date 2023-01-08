package org.example.Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Potion extends SuperObject{

    public OBJ_Potion() {
        name = "Potion";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/potion_01h.png"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
