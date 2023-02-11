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
                if (entity.worldY + entity.speed < 0) entity.isCollisionOn = true;
            }
            case "left" -> {
                if (entity.worldX + entity.speed < 0) entity.isCollisionOn = true;
            }
            case "down" -> {
                if (entity.worldY + entity.speed > (gamePanel.tileSize * gamePanel.maxWorldRow) - 50)
                    entity.isCollisionOn = true;
            }
            case "right" -> {
                if (entity.worldX + entity.speed > (gamePanel.tileSize * gamePanel.maxWorldCol) - 47)
                    entity.isCollisionOn = true;
            }
        }
    }

    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {

                entity.solidAreaRectangle.x = entity.worldX + entity.solidAreaRectangle.x;
                entity.solidAreaRectangle.y = entity.worldY + entity.solidAreaRectangle.y;

                target[i].solidAreaRectangle.x = target[i].worldX + target[i].solidAreaRectangle.x;
                target[i].solidAreaRectangle.y = target[i].worldY + target[i].solidAreaRectangle.y;

                switch (entity.direction) {
                    case "up" -> {
                        entity.solidAreaRectangle.y -= entity.speed;
                    }
                    case "down" -> {
                        entity.solidAreaRectangle.y += entity.speed;
                    }
                    case "left" -> {
                        entity.solidAreaRectangle.x -= entity.speed;
                    }
                    case "right" -> {
                        entity.solidAreaRectangle.x += entity.speed;
                    }
                }

                if (entity.solidAreaRectangle.intersects(target[i].solidAreaRectangle)) {
                    if (target[i] != entity) {
                        entity.isCollisionOn = true;
                        index = i;
                    }
                }
                entity.solidAreaRectangle.x = entity.solidAreaDefaultX;
                entity.solidAreaRectangle.y = entity.solidAreaDefaultY;
                target[i].solidAreaRectangle.x = target[i].solidAreaDefaultX;
                target[i].solidAreaRectangle.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < gamePanel.objects.length; i++) {

            if (gamePanel.objects[i] != null) {
                entity.solidAreaRectangle.x = entity.worldX + entity.solidAreaRectangle.x;
                entity.solidAreaRectangle.y = entity.worldY + entity.solidAreaRectangle.y;
                gamePanel.objects[i].solidAreaRectangle.x = gamePanel.objects[i].worldX + gamePanel.objects[i].solidAreaRectangle.x;
                gamePanel.objects[i].solidAreaRectangle.y = gamePanel.objects[i].worldY + gamePanel.objects[i].solidAreaRectangle.y;
                switch (entity.direction) {
                    case "up" -> {
                        entity.solidAreaRectangle.y -= entity.speed;

                    }
                    case "down" -> {
                        entity.solidAreaRectangle.y += entity.speed;

                    }
                    case "left" -> {
                        entity.solidAreaRectangle.x -= entity.speed;

                    }
                    case "right" -> {
                        entity.solidAreaRectangle.x += entity.speed;

                    }
                }
                if (entity.solidAreaRectangle.intersects(gamePanel.objects[i].solidAreaRectangle)) {
                    if (gamePanel.objects[i].isSolid) {
                        entity.isCollisionOn = true;
                    }
                    if (player) {
                        index = i;
                    }
                }
                entity.solidAreaRectangle.x = entity.solidAreaDefaultX;
                entity.solidAreaRectangle.y = entity.solidAreaDefaultY;
                gamePanel.objects[i].solidAreaRectangle.x = gamePanel.objects[i].solidAreaDefaultX;
                gamePanel.objects[i].solidAreaRectangle.y = gamePanel.objects[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity) {

        boolean contactPlayer = false;

        entity.solidAreaRectangle.x = entity.worldX + entity.solidAreaRectangle.x;
        entity.solidAreaRectangle.y = entity.worldY + entity.solidAreaRectangle.y;
        gamePanel.player.solidAreaRectangle.x = gamePanel.player.worldX + gamePanel.player.solidAreaRectangle.x;
        gamePanel.player.solidAreaRectangle.y = gamePanel.player.worldY + gamePanel.player.solidAreaRectangle.y;
        switch (entity.direction) {
            case "up" -> {
                entity.solidAreaRectangle.y -= entity.speed;
            }
            case "down" -> {
                entity.solidAreaRectangle.y += entity.speed;
            }
            case "left" -> {
                entity.solidAreaRectangle.x -= entity.speed;
            }
            case "right" -> {
                entity.solidAreaRectangle.x += entity.speed;
            }
        }
        if (entity.solidAreaRectangle.intersects(gamePanel.player.solidAreaRectangle)) {
            entity.isCollisionOn = true;
            contactPlayer = true;
        }

        entity.solidAreaRectangle.x = entity.solidAreaDefaultX;
        entity.solidAreaRectangle.y = entity.solidAreaDefaultY;
        gamePanel.player.solidAreaRectangle.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidAreaRectangle.y = gamePanel.player.solidAreaDefaultY;

        return contactPlayer;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidAreaRectangle.x;
        int entityRightWorldX = entity.worldX + entity.solidAreaRectangle.x + entity.solidAreaRectangle.width;
        int entityTopWorldY = entity.worldY + entity.solidAreaRectangle.y;
        int entityBottomWorldY = entity.worldY + entity.solidAreaRectangle.y + entity.solidAreaRectangle.height;

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
                if ((gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision ) ) {
                    entity.isCollisionOn = true;
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                if (entityBottomRow < 50) {
                    tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                    tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                    if ((gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision) ) {
                        entity.isCollisionOn = true;
                    }
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                if (entityLeftCol >=0 && entityTopRow > 0) {
                    tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                    tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                    if ((gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision)  ) {
                        entity.isCollisionOn = true;
                    }
                }

            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                if (entityRightCol < 50) {
                    tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                    tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                    if ((gamePanel.tileManager.tiles[tileNum1].collision || gamePanel.tileManager.tiles[tileNum2].collision)&& !entity.name.equals("Bird") ) {
                        entity.isCollisionOn = true;
                    }
                }
            }
        }
    }
}






