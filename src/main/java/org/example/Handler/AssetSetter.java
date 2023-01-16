package org.example.Handler;

import org.example.Entity.Mage;
import org.example.Entity.NPC_1;
import org.example.Entity.Orc;
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
        gamePanel.entities[0].worldX = gamePanel.tileSize *21;
        gamePanel.entities[0].worldY =  gamePanel.tileSize *22;
        gamePanel.entities[1] = new Orc(gamePanel);
        gamePanel.entities[1].worldX = gamePanel.tileSize *22;
        gamePanel.entities[1].worldY =  gamePanel.tileSize *22;
        gamePanel.entities[2] = new Mage(gamePanel);
        gamePanel.entities[2].worldX = gamePanel.tileSize *23;
        gamePanel.entities[2].worldY =  gamePanel.tileSize *22;
    }
}
