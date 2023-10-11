package org.example.entity.objects.consum;

import org.example.entity.Entity;
import org.example.Game.GamePanel;


public class OBJ_Heart extends Entity {
    public OBJ_Heart(GamePanel gamePanel) {
        super(gamePanel);

        this.name = "Heart";
        this.image = setup("/objects/Heart_full",gamePanel.getTileSize(),gamePanel.getTileSize());
        this.image2 = setup("/objects/Heart_half",gamePanel.getTileSize(),gamePanel.getTileSize());
        this.image3 = setup("/objects/Heart_empty",gamePanel.getTileSize(),gamePanel.getTileSize());
    }
}
