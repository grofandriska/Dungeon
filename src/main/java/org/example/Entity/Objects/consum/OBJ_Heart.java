package org.example.Entity.Objects.consum;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;


public class OBJ_Heart extends Entity {
    public OBJ_Heart(GamePanel gamePanel) {
        super(gamePanel);

        this.name = "Heart";
        this.image = setup("/objects/Heart_full",gamePanel.tileSize,gamePanel.tileSize);
        this.image2 = setup("/objects/Heart_half",gamePanel.tileSize,gamePanel.tileSize);
        this.image3 = setup("/objects/Heart_empty",gamePanel.tileSize,gamePanel.tileSize);
    }
}
