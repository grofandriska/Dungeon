package org.example.Handler.draw;

import org.example.Entity.gaia.Bird;
import org.example.Entity.monsters.Monster_ORC;
import org.example.Entity.monsters.Orc;
import org.example.Entity.npc.Mage;
import org.example.Entity.npc.OldMan;
import org.example.Entity.npc.Soldier;
import org.example.Game.GamePanel;
import org.example.Objects.consum.OBJ_Key;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.objects[0] = new OBJ_Key(gamePanel);
        gamePanel.objects[0].worldY = gamePanel.tileSize * 25;
        gamePanel.objects[0].worldX = gamePanel.tileSize * 22;
    }

    public void setNPC() {
        gamePanel.npc[0] = new OldMan(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize * 33;
        gamePanel.npc[0].worldY = gamePanel.tileSize * 10;

        gamePanel.npc[1] = new Orc(gamePanel);
        gamePanel.npc[1].worldX = gamePanel.tileSize * 26;
        gamePanel.npc[1].worldY = gamePanel.tileSize * 26;

        gamePanel.npc[2] = new Mage(gamePanel);
        gamePanel.npc[2].worldX = gamePanel.tileSize * 9;
        gamePanel.npc[2].worldY = gamePanel.tileSize * 7;

        gamePanel.npc[3] = new Soldier(gamePanel);
        gamePanel.npc[3].worldX = gamePanel.tileSize * 9;
        gamePanel.npc[3].worldY = gamePanel.tileSize * 22;

        gamePanel.npc[3] = new Bird(gamePanel);
        gamePanel.npc[3].worldX = gamePanel.tileSize * 21;
        gamePanel.npc[3].worldY = gamePanel.tileSize * 9;

    }

    public void setMonster() {
        gamePanel.monsters[0] = new Monster_ORC(gamePanel);
        gamePanel.monsters[0].worldX = gamePanel. tileSize * 26;
        gamePanel.monsters[0].worldY = gamePanel.tileSize * 25;
    }
}
