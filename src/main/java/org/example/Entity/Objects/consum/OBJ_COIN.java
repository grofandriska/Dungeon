package org.example.Entity.Objects.consum;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

public class OBJ_COIN extends Entity {

    public OBJ_COIN(GamePanel gamePanel) {
        super(gamePanel);

        name = "Coin";
        direction = "down";
        image = setup("/objects/coin_02a",gamePanel.tileSize,gamePanel.tileSize);
        down1 = setup("/objects/coin_02a",gamePanel.tileSize,gamePanel.tileSize);
        description = "[" + name + "]\n" + "It's worth some .";
    }
}
