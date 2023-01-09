package org.example.Handler;

import org.example.game.GamePanel;
import org.example.Objects.OBJ_GIFT;
import org.example.Objects.OBJ_Key;
import org.example.Objects.OBJ_Potion;

public class AssetSetter {
    GamePanel gamePanel;
    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void setObject() {
        gamePanel.obj[0] = new OBJ_Key();
        gamePanel.obj[0].worldX = gamePanel.tileSize * 22;
        gamePanel.obj[0].worldY = gamePanel.tileSize * 16;

        gamePanel.obj[1] = new OBJ_GIFT();
        gamePanel.obj[1].worldX = gamePanel.tileSize * 22;
        gamePanel.obj[1].worldY = gamePanel.tileSize * 25;

        gamePanel.obj[2] = new OBJ_Potion();
        gamePanel.obj[2].worldX = gamePanel.tileSize * 22;
        gamePanel.obj[2].worldY = gamePanel.tileSize * 21;



    }
}
