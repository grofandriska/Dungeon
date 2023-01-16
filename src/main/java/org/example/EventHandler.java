package org.example;

import org.example.game.GamePanel;

import java.awt.*;

public class EventHandler {

    boolean[] events = new boolean[10];
    GamePanel gamePanel;

    Rectangle eventRectangle;

    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        this.eventRectangle = new Rectangle();

        eventRectangle.x = 24;
        eventRectangle.y = 24;

        eventRectangle.width = 2;
        eventRectangle.height = 2;

        eventRectDefaultX = eventRectangle.x;
        eventRectDefaultY = eventRectangle.y;
    }

    public void checkEvent() {
        if (hit(20, 25, "left")) {
            damagePit(gamePanel.dialogState);
        }

        if (hit(20, 25, "right")) {
            heal(gamePanel.dialogState);
        }
    }

    public void damagePit(int gameState) {
        if (!events[0]) {
            gamePanel.gameState = gameState;
            gamePanel.ui.currentDialog = "You Coward ! - 4hp";
            gamePanel.player.life -= 4;
            events[0] = true;
        }
    }

    public void heal(int gameState) {
        if (!events[1]) {
            gamePanel.gameState = gameState;
            gamePanel.ui.currentDialog = "You are brave ! + 4hp ";
            gamePanel.player.life += 4;
            events[1] = true;
        }

    }

    //almost same as object collision check
    public boolean hit(int eventCol, int eventRow, String reqDirection) {

        boolean hit = false;

        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;

        eventRectangle.x = eventCol * gamePanel.tileSize + eventRectangle.x;
        eventRectangle.y = eventRow * gamePanel.tileSize + eventRectangle.y;

        if (gamePanel.player.solidArea.intersects(eventRectangle)) {
            if (gamePanel.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
        eventRectangle.x = eventRectDefaultX;
        eventRectangle.y = eventRectDefaultY;

        return hit;
    }
}
