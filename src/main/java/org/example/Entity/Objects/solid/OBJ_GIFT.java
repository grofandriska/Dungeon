package org.example.Entity.Objects.solid;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;


public class OBJ_GIFT extends Entity {

    public OBJ_GIFT(GamePanel gamePanel) {
        super(gamePanel);

        //entity attributes
        name = "Gift";
        isSolid = true;

        //entity static
        description = "[" + name + "]\n" + "Box of surprises. You can gift it\n" + "or open for yourself .";

        getImage();
    }

    public void getImage() {
        image = setup("/objects/gift_01a", gamePanel.getTileSize(), gamePanel.getTileSize());
    }
}
