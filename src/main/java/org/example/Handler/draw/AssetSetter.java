package org.example.Handler.draw;

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
        gamePanel.objects[0].worldX = 40 * gamePanel.tileSize;
        gamePanel.objects[0].worldY = 22 * gamePanel.tileSize;
    }

    public void setGaia() {
        gamePanel.gaia[0] = new Bird(gamePanel);
        gamePanel.gaia[0].worldX = gamePanel.tileSize * 21;
        gamePanel.gaia[0].worldY = gamePanel.tileSize * 9;

        gamePanel.gaia[1] = new Bird(gamePanel);
        gamePanel.gaia[1].worldX = gamePanel.tileSize * 10;
        gamePanel.gaia[1].worldY = gamePanel.tileSize * 9;

        gamePanel.gaia[2] = new Bird(gamePanel);
        gamePanel.gaia[2].worldX = gamePanel.tileSize * 13;
        gamePanel.gaia[2].worldY = gamePanel.tileSize * 20;

        gamePanel.gaia[3] = new Bird(gamePanel);
        gamePanel.gaia[3].worldX = gamePanel.tileSize * 44;
        gamePanel.gaia[3].worldY = gamePanel.tileSize * 15;

        gamePanel.gaia[4] = new Bird(gamePanel);
        gamePanel.gaia[4].worldX = gamePanel.tileSize * 40;
        gamePanel.gaia[4].worldY = gamePanel.tileSize * 40;

        gamePanel.gaia[5] = new Bird(gamePanel);
        gamePanel.gaia[5].worldX = gamePanel.tileSize * 2;
        gamePanel.gaia[5].worldY = gamePanel.tileSize * 2;

        gamePanel.gaia[6] = new Bird(gamePanel);
        gamePanel.gaia[6].worldX = gamePanel.tileSize * 7;
        gamePanel.gaia[6].worldY = gamePanel.tileSize * 33;

        gamePanel.gaia[7] = new Bird(gamePanel);
        gamePanel.gaia[7].worldX = gamePanel.tileSize * 20;
        gamePanel.gaia[7].worldY = gamePanel.tileSize * 27;
    }

    public void setNPC() {

        gamePanel.npc[0] = new OldMan(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize * 38;
        gamePanel.npc[0].worldY = gamePanel.tileSize * 10;

        gamePanel.npc[1] = new Soldier(gamePanel);
        gamePanel.npc[1].worldX = gamePanel.tileSize * 41;
        gamePanel.npc[1].worldY = gamePanel.tileSize * 30;

        gamePanel.npc[2] = new Soldier(gamePanel);
        gamePanel.npc[2].worldX = gamePanel.tileSize * 8;
        gamePanel.npc[2].worldY = gamePanel.tileSize * 10;

        gamePanel.npc[3] = new Soldier(gamePanel);
        gamePanel.npc[3].worldX = gamePanel.tileSize * 38;
        gamePanel.npc[3].worldY = gamePanel.tileSize * 30;

    }

    public void setMonster() {
        gamePanel.monsters[0] = new Monster_ORC(gamePanel);
        gamePanel.monsters[0].worldX = gamePanel.tileSize * 33;
        gamePanel.monsters[0].worldY = gamePanel.tileSize * 11;

        gamePanel.monsters[1] = new Monster_ORC(gamePanel);
        gamePanel.monsters[1].worldX = gamePanel.tileSize * 33;
        gamePanel.monsters[1].worldY = gamePanel.tileSize * 9;

        gamePanel.monsters[2] = new Monster_ORC(gamePanel);
        gamePanel.monsters[2].worldX = gamePanel.tileSize * 5;
        gamePanel.monsters[2].worldY = gamePanel.tileSize * 31;

        gamePanel.monsters[3] = new Monster_ORC(gamePanel);
        gamePanel.monsters[3].worldX = gamePanel.tileSize * 10;
        gamePanel.monsters[3].worldY = gamePanel.tileSize * 31;

        gamePanel.monsters[4] = new Monster_ORC(gamePanel);
        gamePanel.monsters[4].worldX = gamePanel.tileSize * 17;
        gamePanel.monsters[4].worldY = gamePanel.tileSize * 31;

        gamePanel.monsters[5] = new Monster_ORC(gamePanel);
        gamePanel.monsters[5].worldX = gamePanel.tileSize * 20;
        gamePanel.monsters[5].worldY = gamePanel.tileSize * 31;

        gamePanel.monsters[6] = new Monster_ORC(gamePanel);
        gamePanel.monsters[6].worldX = gamePanel.tileSize * 23;
        gamePanel.monsters[6].worldY = gamePanel.tileSize * 31;

        gamePanel.monsters[7] = new Monster_ORC(gamePanel);
        gamePanel.monsters[7].worldX = gamePanel.tileSize * 33;
        gamePanel.monsters[7].worldY = gamePanel.tileSize * 31;

        gamePanel.monsters[8] = new Monster_ORC(gamePanel);
        gamePanel.monsters[8].worldX = gamePanel.tileSize * 19;
        gamePanel.monsters[8].worldY = gamePanel.tileSize * 13;

        gamePanel.monsters[9] = new Monster_ORC(gamePanel);
        gamePanel.monsters[9].worldX = gamePanel.tileSize * 19;
        gamePanel.monsters[9].worldY = gamePanel.tileSize * 26;

        gamePanel.monsters[9] = new Monster_ORC(gamePanel);
        gamePanel.monsters[9].worldX = gamePanel.tileSize * 9;
        gamePanel.monsters[9].worldY = gamePanel.tileSize * 25;

        gamePanel.monsters[10] = new Monster_ORC(gamePanel);
        gamePanel.monsters[10].worldX = gamePanel.tileSize * 26;
        gamePanel.monsters[10].worldY = gamePanel.tileSize * 26;

        gamePanel.monsters[11] = new Mage(gamePanel);
        gamePanel.monsters[11].worldX = gamePanel.tileSize * 41;
        gamePanel.monsters[11].worldY = gamePanel.tileSize * 29;
    }
}
