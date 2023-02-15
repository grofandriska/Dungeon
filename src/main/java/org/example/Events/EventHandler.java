package org.example.Events;

import org.example.Events.model.Event;
import org.example.Game.GamePanel;
import org.example.UI.UserInreface;


public class EventHandler {
    public Event events[];

    public GamePanel gamePanel;
    public EventRectangle eventRectangle[][];
    public UserInreface Ui;
    //init fields, make events to "all tiles?!"
    public EventHandler(GamePanel gamePanel) {

        //set attributes
        this.Ui = new UserInreface(gamePanel);
        this.gamePanel = gamePanel;
        //add some event
        this.events = new Event[10];
        this.events[0] = new Event();
        this.events[1] = new Event();

        this.eventRectangle = new EventRectangle[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        int col = 0;
        int row = 0;
        //why I making all events on map ? :D
        while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
            //set event area on tile center and set its width and height
            this.eventRectangle[col][row] = new EventRectangle();
            this.eventRectangle[col][row].y = 24;
            this.eventRectangle[col][row].x = 24;
            this.eventRectangle[col][row].width = 2;
            this.eventRectangle[col][row].height = 2;
            //set it's default values?!
            this.eventRectangle[col][row].defaultRectX = this.eventRectangle[col][row].x;
            this.eventRectangle[col][row].defaultRectY = this.eventRectangle[col][row].y;
            col++;

            if (col == gamePanel.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }
    //check distance() and call methods on events
    public void checkEvent() {
        checkDistance(events[0]);
        checkDistance(events[1]);

        //welcome event
        if (events[0].canTouchEvent && !(events[0].isHappened)) {
            if (hit(19, 8, "down", events[0])) {
                welcome(gamePanel.dialogState, 1, 1);
                events[0].isHappened = true;
            }
        }
        //you are safe event
        if (events[1].canTouchEvent) {
            if (hit(11, 10, "left", events[1])) {
                heal(gamePanel.dialogState, 11, 27);
                events[1].canTouchEvent = false;
            }
        }
    }
    //do damage on tile
    public void damagePit(int gameState, int col, int row) {
        gamePanel.gameState = gameState;
        gamePanel.UI.currentDialog = "You Coward ! - 4hp";
        gamePanel.player.life -= 4;
        events[0].canTouchEvent = false;
        eventRectangle[col][row].eventHappened = true;
    }
    //welcome screen
    public void welcome(int gameState, int col, int row) {
        gamePanel.gameState = gameState;
        gamePanel.UI.currentDialog = "You wake up tired on this Isla-\nnd. You lost 4 life!  ";
        gamePanel.player.life -= 4;
        events[0].canTouchEvent = false;
        eventRectangle[col][row].eventHappened = true;
    }
    //heal event
    public void heal(int gameState, int col, int row) {
        gamePanel.gameState = gameState;
        gamePanel.UI.currentDialog = "You feel safe ! \nYou're life is restored ";
        gamePanel.player.life =gamePanel.player.maxLife;
        events[1].canTouchEvent = false;
        eventRectangle[col][row].eventHappened = true;
    }
    //check distance (check mage)
    public void checkDistance(Event event) {
        int xDistance = Math.abs(gamePanel.player.worldX - event.previousEventX);
        int yDistance = Math.abs(gamePanel.player.worldY - event.previousEventY);
        int distance = Math.max(xDistance, yDistance);

        if (distance > gamePanel.tileSize * 15) {
            event.canTouchEvent = true;
        }
    }

    //checks if player is on eventTile
    public boolean hit(int col, int row, String reqDirection, Event event) {
        boolean hit = false;

        //2 parameter for player solid area
        gamePanel.player.solidAreaRectangle.x = gamePanel.player.worldX + gamePanel.player.solidAreaRectangle.x;
        gamePanel.player.solidAreaRectangle.y = gamePanel.player.worldY + gamePanel.player.solidAreaRectangle.y;

        //2 parameter for event solid area
        this.eventRectangle[col][row].x = col * gamePanel.tileSize + this.eventRectangle[col][row].x;
        this.eventRectangle[col][row].y = row * gamePanel.tileSize + this.eventRectangle[col][row].y;

        //checks if rectangle intersects and if direction "any"
        if (gamePanel.player.solidAreaRectangle.intersects(this.eventRectangle[col][row]) && !this.eventRectangle[col][row].eventHappened) {
            if (gamePanel.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
                event.previousEventX = gamePanel.player.worldX;
                event.previousEventY = gamePanel.player.worldY;
            }
        }

        //reset values
        gamePanel.player.solidAreaRectangle.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidAreaRectangle.y = gamePanel.player.solidAreaDefaultY;
        this.eventRectangle[col][row].x = this.eventRectangle[col][row].defaultRectX;
        this.eventRectangle[col][row].y = this.eventRectangle[col][row].defaultRectY;

        return hit;
    }
}
