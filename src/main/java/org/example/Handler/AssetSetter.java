package org.example.Handler;

import org.example.Display.GamePanel;
import org.example.Objects.OBJ_Key;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.obj[0] = new OBJ_Key();
        gamePanel.obj[0].worldX = gamePanel.tileSize * 6;
        gamePanel.obj[0].worldY = gamePanel.tileSize * 5;

        gamePanel.obj[1] = new OBJ_Key();
        gamePanel.obj[1].worldX = gamePanel.tileSize * 2;
        gamePanel.obj[1].worldY = gamePanel.tileSize * 2;
    }
}
