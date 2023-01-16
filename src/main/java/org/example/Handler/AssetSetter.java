package org.example.Handler;

import org.example.Entity.Bird;
import org.example.Entity.NPC_1;
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
    }
    public void setNPC(){
        gamePanel.entities[0] = new NPC_1(gamePanel);
        gamePanel.entities[0].worldX = gamePanel.tileSize *22;
        gamePanel.entities[0].worldY =  gamePanel.tileSize *24;

        gamePanel.entities[1] = new Bird(gamePanel);
        gamePanel.entities[1].worldY =gamePanel.tileSize * 23;
        gamePanel.entities[1].worldX =gamePanel.tileSize * 25;
    }
}
