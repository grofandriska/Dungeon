package org.example.Handler;

import org.example.entity.objects.Door;
import org.example.entity.objects.OBJ_GoldKey;
import org.example.entity.objects.inventory.OBJ_SHIELD;
import org.example.entity.objects.inventory.OBJ_STAFF;
import org.example.entity.objects.inventory.OBJ_SWORD;
import org.example.entity.npc.gaia.Bird;
import org.example.entity.npc.monsters.Monster_ORC;
import org.example.entity.npc.Mage;
import org.example.entity.npc.OldMan;
import org.example.entity.npc.Soldier;
import org.example.Game.GamePanel;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        gamePanel.getObjects()[0] = new OBJ_SWORD(gamePanel);
        gamePanel.getObjects()[0].worldX = 5 * gamePanel.getTileSize();
        gamePanel.getObjects()[0].worldY = 7 * gamePanel.getTileSize();

        gamePanel.getObjects()[1] = new OBJ_SHIELD(gamePanel);
        gamePanel.getObjects()[1].worldX = 6 * gamePanel.getTileSize();
        gamePanel.getObjects()[1].worldY = 7 * gamePanel.getTileSize();

        gamePanel.getObjects()[2] = new OBJ_STAFF(gamePanel);
        gamePanel.getObjects()[2].worldX = 7 * gamePanel.getTileSize();
        gamePanel.getObjects()[2].worldY = 7 * gamePanel.getTileSize();

        gamePanel.getObjects()[3] = new Door(gamePanel);
        gamePanel.getObjects()[3].worldX = 13 * gamePanel.getTileSize();
        gamePanel.getObjects()[3].worldY = 43 * gamePanel.getTileSize();

        gamePanel.getObjects()[4] = new Door(gamePanel);
        gamePanel.getObjects()[4].worldX = 12 * gamePanel.getTileSize();
        gamePanel.getObjects()[4].worldY = 40 * gamePanel.getTileSize();

        gamePanel.getObjects()[5] = new Door(gamePanel);
        gamePanel.getObjects()[5].worldX = 20 * gamePanel.getTileSize();
        gamePanel.getObjects()[5].worldY = 31 * gamePanel.getTileSize();
        gamePanel.getObjects()[5].keyName = "Gold key";

        gamePanel.getObjects()[6] = new Door(gamePanel);
        gamePanel.getObjects()[6].worldX = 19 * gamePanel.getTileSize();
        gamePanel.getObjects()[6].worldY = 31 * gamePanel.getTileSize();
        gamePanel.getObjects()[6].keyName = "Gold key";

        gamePanel.getObjects()[7] = new OBJ_GoldKey(gamePanel);
        gamePanel.getObjects()[7].worldX = 8 * gamePanel.getTileSize();
        gamePanel.getObjects()[7].worldY = 48 * gamePanel.getTileSize();


    }

    public void setGaia() {
        gamePanel.getGaia()[0] = new Bird(gamePanel);
        gamePanel.getGaia()[0].worldX = gamePanel.getTileSize();
        gamePanel.getGaia()[0].worldY = gamePanel.getTileSize();
        gamePanel.getGaia()[0].direction = "left";


        gamePanel.getGaia()[1] = new Bird(gamePanel);
        gamePanel.getGaia()[1].worldX = gamePanel.getTileSize() * 3;
        gamePanel.getGaia()[1].worldY = gamePanel.getTileSize();
        gamePanel.getGaia()[1].direction = "up";

        gamePanel.getGaia()[2] = new Bird(gamePanel);
        gamePanel.getGaia()[2].worldX = gamePanel.getTileSize() * 49;
        gamePanel.getGaia()[2].worldY = gamePanel.getTileSize() * 30;
        gamePanel.getGaia()[2].direction = "right";

        gamePanel.getGaia()[3] = new Bird(gamePanel);
        gamePanel.getGaia()[3].worldX = gamePanel.getTileSize() * 7;
        gamePanel.getGaia()[3].worldY = gamePanel.getTileSize() * 49;
        gamePanel.getGaia()[3].direction = "down";


    }

    public void setNPC() {

        gamePanel.getNpc()[0] = new OldMan(gamePanel);
        gamePanel.getNpc()[0].worldX = gamePanel.getTileSize() * 8;
        gamePanel.getNpc()[0].worldY = gamePanel.getTileSize() * 7;

        gamePanel.getNpc()[1] = new Soldier(gamePanel);
        gamePanel.getNpc()[1].worldX = gamePanel.getTileSize() * 41;
        gamePanel.getNpc()[1].worldY = gamePanel.getTileSize() * 30;

        gamePanel.getNpc()[3] = new Soldier(gamePanel);
        gamePanel.getNpc()[3].worldX = gamePanel.getTileSize() * 38;
        gamePanel.getNpc()[3].worldY = gamePanel.getTileSize() * 30;

        gamePanel.getNpc()[4] = new Mage(gamePanel);
        gamePanel.getNpc()[4].worldX = gamePanel.getTileSize() * 38;
        gamePanel.getNpc()[4].worldY = gamePanel.getTileSize() * 34;

    }

    public void setMonster() {

        gamePanel.getMonsters()[0] = new Monster_ORC(gamePanel);
        gamePanel.getMonsters()[0].worldX = gamePanel.getTileSize() * 6;
        gamePanel.getMonsters()[0].worldY = gamePanel.getTileSize() * 44;

        gamePanel.getMonsters()[1] = new Monster_ORC(gamePanel);
        gamePanel.getMonsters()[1].worldX = gamePanel.getTileSize() * 7;
        gamePanel.getMonsters()[1].worldY = gamePanel.getTileSize() * 48;

        gamePanel.getMonsters()[2] = new Monster_ORC(gamePanel);
        gamePanel.getMonsters()[2].worldX = gamePanel.getTileSize();
        gamePanel.getMonsters()[2].worldY = gamePanel.getTileSize() * 44;

        gamePanel.getMonsters()[3] = new Monster_ORC(gamePanel);
        gamePanel.getMonsters()[3].worldX = gamePanel.getTileSize();
        gamePanel.getMonsters()[3].worldY = gamePanel.getTileSize() * 46;


        gamePanel.getMonsters()[4] = new Monster_ORC(gamePanel);
        gamePanel.getMonsters()[4].worldX = gamePanel.getTileSize() * 13;
        gamePanel.getMonsters()[4].worldY = gamePanel.getTileSize() * 41;
        gamePanel.getMonsters()[4].direction = "up";

        gamePanel.getMonsters()[5] = new Monster_ORC(gamePanel);
        gamePanel.getMonsters()[5].worldX = gamePanel.getTileSize() * 19;
        gamePanel.getMonsters()[5].worldY = gamePanel.getTileSize() * 48;

        gamePanel.getMonsters()[6] = new Monster_ORC(gamePanel);
        gamePanel.getMonsters()[6].worldX = gamePanel.getTileSize() * 18;
        gamePanel.getMonsters()[6].worldY = gamePanel.getTileSize() * 40;

        gamePanel.getMonsters()[7] = new Monster_ORC(gamePanel);
        gamePanel.getMonsters()[7].worldX = gamePanel.getTileSize() * 18;
        gamePanel.getMonsters()[7].worldY = gamePanel.getTileSize() * 35;
    }
}
