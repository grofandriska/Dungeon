package org.example.Objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_GIFT extends SuperObject{

    public OBJ_GIFT() {
        name = "Gift";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/gift_01a.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        collision = true;
    }
}
