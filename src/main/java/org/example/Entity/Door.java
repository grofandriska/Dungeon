package org.example.Entity;

import org.example.Game.GamePanel;

import java.awt.*;

public class Door extends Entity {
    public Door(GamePanel gamePanel) {
        super(gamePanel);
        this.keyName = "Key";
        this.name = "door";
        this.isSolid = true;
        this.solidAreaRectangle = new Rectangle(0, 0, gamePanel.tileSize, gamePanel.tileSize);
        this.image = setup("/tiles/door1", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void draw(Graphics2D graphics2D) {
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX
                && worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX
                && worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY
                && worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {
            graphics2D.drawImage(image, screenX, screenY, null);
        }
    }

    public void open() {
    }
}
