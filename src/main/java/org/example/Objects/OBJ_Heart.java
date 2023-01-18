package org.example.Objects;

import org.example.Entity.Entity;
import org.example.game.GamePanel;



public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gamePanel) {
        super(gamePanel);
        name = "Heart";
        image = setup("/objects/Heart_full");
        image2 = setup("/objects/Heart_half");
        image3 = setup("/objects/Heart_empty");
    }
}
