package org.example.Handler;

import org.example.Entity.gaia.Bird;
import org.example.Entity.monsters.Monster_ORC;
import org.example.Entity.npc.Mage;
import org.example.Entity.npc.OldMan;
import org.example.Entity.npc.Soldier;
import org.example.Game.GamePanel;
import org.example.Entity.Objects.consum.OBJ_Potion;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.objects[0] = new OBJ_Potion(gamePanel);
        gamePanel.objects[0].worldX = 5 * gamePanel.tileSize;
        gamePanel.objects[0].worldY = 6 * gamePanel.tileSize;
    }

    public void setGaia() {
        gamePanel.gaia[0] = new Bird(gamePanel);
        gamePanel.gaia[0].worldX = gamePanel.tileSize;
        gamePanel.gaia[0].worldY = gamePanel.tileSize;


        gamePanel.gaia[1] = new Bird(gamePanel);
        gamePanel.gaia[1].worldX = gamePanel.tileSize * 10;
        gamePanel.gaia[1].worldY = gamePanel.tileSize * 5;


        gamePanel.gaia[2] = new Bird(gamePanel);
        gamePanel.gaia[2].worldX = gamePanel.tileSize * 15;
        gamePanel.gaia[2].worldY = gamePanel.tileSize * 30;


        gamePanel.gaia[3] = new Bird(gamePanel);
        gamePanel.gaia[3].worldX = gamePanel.tileSize *5;
        gamePanel.gaia[3].worldY = gamePanel.tileSize * 5;


    }

    public void setNPC() {

        gamePanel.npc[0] = new OldMan(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize * 8;
        gamePanel.npc[0].worldY = gamePanel.tileSize * 7;

        gamePanel.npc[1] = new Soldier(gamePanel);
        gamePanel.npc[1].worldX = gamePanel.tileSize * 41;
        gamePanel.npc[1].worldY = gamePanel.tileSize * 30;

        gamePanel.npc[3] = new Soldier(gamePanel);
        gamePanel.npc[3].worldX = gamePanel.tileSize * 38;
        gamePanel.npc[3].worldY = gamePanel.tileSize * 30;

        gamePanel.npc[4] = new Mage(gamePanel);
        gamePanel.npc[4].worldX = gamePanel.tileSize * 38;
        gamePanel.npc[4].worldY = gamePanel.tileSize * 34;

    }

    public void setMonster() {

        gamePanel.monsters[0] = new Monster_ORC(gamePanel);
        gamePanel.monsters[0].worldX = gamePanel.tileSize * 35;
        gamePanel.monsters[0].worldY = gamePanel.tileSize * 11;

        gamePanel.monsters[1] = new Monster_ORC(gamePanel);
        gamePanel.monsters[1].worldX = gamePanel.tileSize * 34;
        gamePanel.monsters[1].worldY = gamePanel.tileSize * 11;

        gamePanel.monsters[2] = new Monster_ORC(gamePanel);
        gamePanel.monsters[2].worldX = gamePanel.tileSize * 36;
        gamePanel.monsters[2].worldY = gamePanel.tileSize * 19;

        gamePanel.monsters[3] = new Monster_ORC(gamePanel);
        gamePanel.monsters[3].worldX = gamePanel.tileSize * 27;
        gamePanel.monsters[3].worldY = gamePanel.tileSize * 10;


        gamePanel.monsters[4] = new Monster_ORC(gamePanel);
        gamePanel.monsters[4].worldX = gamePanel.tileSize * 6;
        gamePanel.monsters[4].worldY = gamePanel.tileSize * 23;

        gamePanel.monsters[5] = new Monster_ORC(gamePanel);
        gamePanel.monsters[5].worldX = gamePanel.tileSize * 8;
        gamePanel.monsters[5].worldY = gamePanel.tileSize * 23;

        gamePanel.monsters[6] = new Mage(gamePanel);
        gamePanel.monsters[6].worldX = gamePanel.tileSize * 44;
        gamePanel.monsters[6].worldY = gamePanel.tileSize * 28;

    }
}
