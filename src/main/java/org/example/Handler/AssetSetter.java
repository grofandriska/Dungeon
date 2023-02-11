package org.example.Handler;

import org.example.Entity.Objects.inventory.OBJ_SHIELD;
import org.example.Entity.Objects.inventory.OBJ_STAFF;
import org.example.Entity.Objects.inventory.OBJ_SWORD;
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
        gamePanel.objects[0] = new OBJ_SWORD(gamePanel);
        gamePanel.objects[0].worldX = 5 * gamePanel.tileSize;
        gamePanel.objects[0].worldY = 7 * gamePanel.tileSize;

        gamePanel.objects[1] = new OBJ_SHIELD(gamePanel);
        gamePanel.objects[1].worldX = 6 * gamePanel.tileSize;
        gamePanel.objects[1].worldY = 7 * gamePanel.tileSize;

        gamePanel.objects[2] = new OBJ_STAFF(gamePanel);
        gamePanel.objects[2].worldX = 7 * gamePanel.tileSize;
        gamePanel.objects[2].worldY = 7 * gamePanel.tileSize;
    }

    public void setGaia() {
        gamePanel.gaia[0] = new Bird(gamePanel);
        gamePanel.gaia[0].worldX = gamePanel.tileSize;
        gamePanel.gaia[0].worldY = gamePanel.tileSize;
        gamePanel.gaia[0].direction = "left";


        gamePanel.gaia[1] = new Bird(gamePanel);
        gamePanel.gaia[1].worldX = gamePanel.tileSize *3;
        gamePanel.gaia[1].worldY = gamePanel.tileSize ;
        gamePanel.gaia[1].direction = "up";

        gamePanel.gaia[2] = new Bird(gamePanel);
        gamePanel.gaia[2].worldX = gamePanel.tileSize * 49;
        gamePanel.gaia[2].worldY = gamePanel.tileSize * 30;
        gamePanel.gaia[2].direction = "right";

        gamePanel.gaia[3] = new Bird(gamePanel);
        gamePanel.gaia[3].worldX = gamePanel.tileSize *7;
        gamePanel.gaia[3].worldY = gamePanel.tileSize * 49;
        gamePanel.gaia[3].direction = "down";


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
        gamePanel.monsters[0].worldX = gamePanel.tileSize * 6;
        gamePanel.monsters[0].worldY = gamePanel.tileSize * 44;

        gamePanel.monsters[1] = new Monster_ORC(gamePanel);
        gamePanel.monsters[1].worldX = gamePanel.tileSize * 7;
        gamePanel.monsters[1].worldY = gamePanel.tileSize * 48;

        gamePanel.monsters[2] = new Monster_ORC(gamePanel);
        gamePanel.monsters[2].worldX = gamePanel.tileSize ;
        gamePanel.monsters[2].worldY = gamePanel.tileSize * 44;

        gamePanel.monsters[3] = new Monster_ORC(gamePanel);
        gamePanel.monsters[3].worldX = gamePanel.tileSize ;
        gamePanel.monsters[3].worldY = gamePanel.tileSize * 46;


        gamePanel.monsters[4] = new Monster_ORC(gamePanel);
        gamePanel.monsters[4].worldX = gamePanel.tileSize * 13;
        gamePanel.monsters[4].worldY = gamePanel.tileSize * 44;

        gamePanel.monsters[5] = new Monster_ORC(gamePanel);
        gamePanel.monsters[5].worldX = gamePanel.tileSize * 19;
        gamePanel.monsters[5].worldY = gamePanel.tileSize * 48;

        gamePanel.monsters[6] = new Monster_ORC(gamePanel);
        gamePanel.monsters[6].worldX = gamePanel.tileSize *18;
        gamePanel.monsters[6].worldY = gamePanel.tileSize * 40;

        gamePanel.monsters[7] = new Monster_ORC(gamePanel);
        gamePanel.monsters[7].worldX = gamePanel.tileSize *18;
        gamePanel.monsters[7].worldY = gamePanel.tileSize * 35;
    }
}
