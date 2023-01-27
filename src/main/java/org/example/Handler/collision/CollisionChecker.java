package org.example.Handler.collision;

import org.example.Game.GamePanel;
import org.example.Entity.Entity;

public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkBorder(Entity entity) {
        switch (entity.direction) {
            case "up" -> {
                if (entity.worldY + entity.speed < 0) entity.collisionOn = true;
            }
            case "left" -> {
                if (entity.worldX + entity.speed < 0) entity.collisionOn = true;
            }
            case "down" -> {
                if (entity.worldY + entity.speed > (gamePanel.tileSize * gamePanel.maxWorldRow) - 50)
                    entity.collisionOn = true;
            }
            case "right" -> {
                if (entity.worldX + entity.speed > (gamePanel.tileSize * gamePanel.maxWorldCol) - 47)
                    entity.collisionOn = true;
            }
        }
    }

    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {

                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up" -> {
                        entity.solidArea.y -= entity.speed;
                    }
                    case "down" -> {
                        entity.solidArea.y += entity.speed;
                    }
                    case "left" -> {
                        entity.solidArea.x -= entity.speed;
                    }
                    case "right" -> {
                        entity.solidArea.x += entity.speed;
                    }
                }

                if (entity.solidArea.intersects(target[i].solidArea)) {
                    if (target[i] != entity) {
                        entity.collisionOn = true;
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < gamePanel.objects.length; i++) {

            if (gamePanel.objects[i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                gamePanel.objects[i].solidArea.x = gamePanel.objects[i].worldX + gamePanel.objects[i].solidArea.x;
                gamePanel.objects[i].solidArea.y = gamePanel.objects[i].worldY + gamePanel.objects[i].solidArea.y;
                switch (entity.direction) {
                    case "up" -> {
                        entity.solidArea.y -= entity.speed;

                    }
                    case "down" -> {
                        entity.solidArea.y += entity.speed;

                    }
                    case "left" -> {
                        entity.solidArea.x -= entity.speed;

                    }
                    case "right" -> {
                        entity.solidArea.x += entity.speed;

                    }
                }
                if (entity.solidArea.intersects(gamePanel.objects[i].solidArea)) {
                    if (gamePanel.objects[i].collision) {
                        entity.collisionOn = true;
                    }
                    if (player) {
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gamePanel.objects[i].solidArea.x = gamePanel.objects[i].solidAreaDefaultX;
                gamePanel.objects[i].solidArea.y = gamePanel.objects[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity) {

        boolean contactPlayer = false;

        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;
        switch (entity.direction) {
            case "up" -> {
                entity.solidArea.y -= entity.speed;
            }
            case "down" -> {
                entity.solidArea.y += entity.speed;
            }
            case "left" -> {
                entity.solidArea.x -= entity.speed;
            }
            case "right" -> {
                entity.solidArea.x += entity.speed;
            }
        }
        if (entity.solidArea.intersects(gamePanel.player.solidArea)) {
            entity.collisionOn = true;
            contactPlayer = true;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;

        return contactPlayer;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;
        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if ((gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) && !entity.name.equals("Bird")) {
                    entity.collisionOn = true;
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                if (entityBottomRow<50){
                    tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                    tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                    if ((gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) && !entity.name.equals("Bird")) {
                        entity.collisionOn = true;
                    }
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if ((gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) && !entity.name.equals("Bird")) {
                    entity.collisionOn = true;
                }
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;

                if (entityRightCol < gamePanel.maxWorldRow) {
                    tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                    tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];

                    if ((gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) && !entity.name.equals("Bird")) {
                        entity.collisionOn = true;
                    }

                }
            }
        }
    }
}






