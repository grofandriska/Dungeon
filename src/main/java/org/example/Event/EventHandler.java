package org.example.Event;

import org.example.Game.GamePanel;

public class EventHandler {

    // boolean[] events = new boolean[10];
    Event events[];
    GamePanel gamePanel;
    EventRectangle eventRectangle[][];

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        events = new Event[10];
        events[0] = new Event();

        this.eventRectangle = new EventRectangle[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        int col = 0;
        int row = 0;

        while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
            eventRectangle[col][row] = new EventRectangle();
            eventRectangle[col][row].y = 24;
            eventRectangle[col][row].x = 24;

            eventRectangle[col][row].width = 2;
            eventRectangle[col][row].height = 2;

            eventRectangle[col][row].defaultRectX = eventRectangle[col][row].x;
            eventRectangle[col][row].defaultRectY = eventRectangle[col][row].y;
            col++;

            if (col == gamePanel.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent() {
        //check if player is more than 1 tile far
        checkDistance(events[0]);
        if (events[0].canTouchEvent) {
            if (hit(20, 25, "left", events[0])) {
                damagePit(gamePanel.dialogState, 20, 25);
                events[0].canTouchEvent = false;
            }

            if (hit(20, 25, "right", events[0])) {
                //  heal(gamePanel.dialogState, 20, 25);
            }
        }
    }

    public void damagePit(int gameState, int col, int row) {
        // if (!events[0]) {
        gamePanel.gameState = gameState;
        gamePanel.UI.currentDialog = "You Coward ! - 4hp";
        gamePanel.player.life -= 4;
        //events[0] = true;eventRectangle[col][row].eventHappened = true;}
    }

    public void heal(int gameState, int col, int row) {
        // if (!events[1]) {
        gamePanel.gameState = gameState;
        gamePanel.UI.currentDialog = "You are brave ! + 4hp ";
        gamePanel.player.life += 4;
        //events[1] = true;}
    }

    // I separated into this method - even could move into Event class so arrays would gone ---
    public void checkDistance(Event event) {
        int xDistance = Math.abs(gamePanel.player.worldX - event.previousEventX);
        int yDistance = Math.abs(gamePanel.player.worldY - event.previousEventY);
        int distance = Math.max(xDistance, yDistance);

        if (distance > gamePanel.tileSize * 2) {
            event.canTouchEvent = true;
        }
    }

    //almost same as object collision check
    public boolean hit(int col, int row, String reqDirection, Event event) {

        boolean hit = false;
        //2 parameter for player solid area
        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;
        //2 parameter for event solid area
        eventRectangle[col][row].x = col * gamePanel.tileSize + eventRectangle[col][row].x;
        eventRectangle[col][row].y = row * gamePanel.tileSize + eventRectangle[col][row].y;
        //checks if rectangle intersects and if direction "any"
        if (gamePanel.player.solidArea.intersects(eventRectangle[col][row]) && !eventRectangle[col][row].eventHappened) {
            if (gamePanel.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                event.previousEventX = gamePanel.player.worldX;
                event.previousEventY = gamePanel.player.worldY;
            }
        }
        //reset values
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;

        eventRectangle[col][row].x = eventRectangle[col][row].defaultRectX;
        eventRectangle[col][row].y = eventRectangle[col][row].defaultRectY;

        return hit;
    }
}
