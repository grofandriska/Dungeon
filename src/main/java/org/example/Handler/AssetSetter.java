package org.example.Handler;

import org.example.Display.GamePanel;
import org.example.Objects.OBJ_GIFT;
import org.example.Objects.OBJ_Key;

public class AssetSetter {
    GamePanel gamePanel;
    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void setObject() {
        gamePanel.obj[0] = new OBJ_Key();
        gamePanel.obj[0].worldX = gamePanel.tileSize * 22;
        gamePanel.obj[0].worldY = gamePanel.tileSize * 20;

        gamePanel.obj[1] = new OBJ_GIFT();
        gamePanel.obj[1].worldX = gamePanel.tileSize * 21;
        gamePanel.obj[1].worldY = gamePanel.tileSize * 20;



    }
}
