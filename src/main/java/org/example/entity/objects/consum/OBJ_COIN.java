package org.example.entity.objects.consum;

import org.example.entity.Entity;
import org.example.Game.GamePanel;

public class OBJ_COIN extends Entity {

    public OBJ_COIN(GamePanel gamePanel) {
        super(gamePanel);

        name = "Coin";
        direction = "down";
        image = setup("/objects/coin_02a",gamePanel.getTileSize(),gamePanel.getTileSize());
        down1 = setup("/objects/coin_02a",gamePanel.getTileSize(),gamePanel.getTileSize());
        description = "[" + name + "]\n" + "It's worth some .";
    }
}
