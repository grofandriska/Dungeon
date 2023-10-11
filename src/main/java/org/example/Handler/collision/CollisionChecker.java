package org.example.Handler.collision;

import org.example.Game.GamePanel;
import org.example.entity.Entity;

public class CollisionChecker {
    private GamePanel gamePanel;

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
                if (entity.worldY + entity.speed > (this.gamePanel.getTileSize() * this.gamePanel.getMaxWorldRow()) - 50)
                    entity.isCollisionOn = true;
            }
            case "right" -> {
                if (entity.worldX + entity.speed > (this.gamePanel.getTileSize() * this.gamePanel.getMaxWorldCol()) - 47)
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

        for (int i = 0; i < this.gamePanel.getObjects().length; i++) {

            if (this.gamePanel.getObjects()[i] != null) {
                entity.solidAreaRectangle.x = entity.worldX + entity.solidAreaRectangle.x;
                entity.solidAreaRectangle.y = entity.worldY + entity.solidAreaRectangle.y;
                this.gamePanel.getObjects()[i].solidAreaRectangle.x = this.gamePanel.getObjects()[i].worldX + this.gamePanel.getObjects()[i].solidAreaRectangle.x;
                this.gamePanel.getObjects()[i].solidAreaRectangle.y = this.gamePanel.getObjects()[i].worldY + this.gamePanel.getObjects()[i].solidAreaRectangle.y;
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
                if (entity.solidAreaRectangle.intersects(this.gamePanel.getObjects()[i].solidAreaRectangle)) {
                    if (this.gamePanel.getObjects()[i].isSolid) {
                        entity.isCollisionOn = true;
                    }
                    if (player) {
                        index = i;
                    }
                }
                entity.solidAreaRectangle.x = entity.solidAreaDefaultX;
                entity.solidAreaRectangle.y = entity.solidAreaDefaultY;
                this.gamePanel.getObjects()[i].solidAreaRectangle.x = this.gamePanel.getObjects()[i].solidAreaDefaultX;
                this.gamePanel.getObjects()[i].solidAreaRectangle.y = this.gamePanel.getObjects()[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity) {

        boolean contactPlayer = false;

        entity.solidAreaRectangle.x = entity.worldX + entity.solidAreaRectangle.x;
        entity.solidAreaRectangle.y = entity.worldY + entity.solidAreaRectangle.y;
        this.gamePanel.getPlayer().solidAreaRectangle.x = this.gamePanel.getPlayer().worldX + this.gamePanel.getPlayer().solidAreaRectangle.x;
        this.gamePanel.getPlayer().solidAreaRectangle.y = this.gamePanel.getPlayer().worldY + this.gamePanel.getPlayer().solidAreaRectangle.y;

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
        if (entity.solidAreaRectangle.intersects(this.gamePanel.getPlayer().solidAreaRectangle)) {
            entity.isCollisionOn = true;
            contactPlayer = true;
        }

        entity.solidAreaRectangle.x = entity.solidAreaDefaultX;
        entity.solidAreaRectangle.y = entity.solidAreaDefaultY;
        this.gamePanel.getPlayer().solidAreaRectangle.x = this.gamePanel.getPlayer().solidAreaDefaultX;
        this.gamePanel.getPlayer().solidAreaRectangle.y = this.gamePanel.getPlayer().solidAreaDefaultY;

        return contactPlayer;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidAreaRectangle.x;
        int entityRightWorldX = entity.worldX + entity.solidAreaRectangle.x + entity.solidAreaRectangle.width;
        int entityTopWorldY = entity.worldY + entity.solidAreaRectangle.y;
        int entityBottomWorldY = entity.worldY + entity.solidAreaRectangle.y + entity.solidAreaRectangle.height;

        int entityLeftCol = entityLeftWorldX / this.gamePanel.getTileSize();
        int entityRightCol = entityRightWorldX / this.gamePanel.getTileSize();
        int entityTopRow = entityTopWorldY / this.gamePanel.getTileSize();
        int entityBottomRow = entityBottomWorldY / this.gamePanel.getTileSize();

        int tileNum1, tileNum2;
        switch (entity.direction) {
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / this.gamePanel.getTileSize();
                tileNum1 = this.gamePanel.getTileManager().mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = this.gamePanel.getTileManager().mapTileNum[entityRightCol][entityTopRow];
                if ((this.gamePanel.getTileManager().tiles[tileNum1].collision || this.gamePanel.getTileManager().tiles[tileNum2].collision)) {
                    entity.isCollisionOn = true;
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.getTileSize();
                if (entityBottomRow < 50) {
                    tileNum1 = this.gamePanel.getTileManager().mapTileNum[entityLeftCol][entityBottomRow];
                    tileNum2 = this.gamePanel.getTileManager().mapTileNum[entityRightCol][entityBottomRow];
                    if ((this.gamePanel.getTileManager().tiles[tileNum1].collision || this.gamePanel.getTileManager().tiles[tileNum2].collision)) {
                        entity.isCollisionOn = true;
                    }
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / this.gamePanel.getTileSize();
                if (entityLeftCol >= 0 && entityTopRow > 0) {
                    tileNum1 = this.gamePanel.getTileManager().mapTileNum[entityLeftCol][entityTopRow];
                    tileNum2 = this.gamePanel.getTileManager().mapTileNum[entityLeftCol][entityBottomRow];
                    if ((this.gamePanel.getTileManager().tiles[tileNum1].collision || this.gamePanel.getTileManager().tiles[tileNum2].collision)) {
                        entity.isCollisionOn = true;
                    }
                }

            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / this.gamePanel.getTileSize();
                if (entityRightCol < 50) {
                    tileNum1 = this.gamePanel.getTileManager().mapTileNum[entityRightCol][entityTopRow];
                    tileNum2 = this.gamePanel.getTileManager().mapTileNum[entityRightCol][entityBottomRow];
                    if ((this.gamePanel.getTileManager().tiles[tileNum1].collision || this.gamePanel.getTileManager().tiles[tileNum2].collision) && !entity.name.equals("Bird")) {
                        entity.isCollisionOn = true;
                    }
                }
            }
        }
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}






