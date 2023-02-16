package org.example.Entity.Objects;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

import java.awt.*;

public class Door extends Entity {
    public Door(GamePanel gamePanel) {
        super(gamePanel);
        this.keyName = "Key";
        this.name = "door";
        this.isSolid = true;
        this.solidAreaRectangle = new Rectangle(0, 0, gamePanel.getTileSize(), gamePanel.getTileSize());
        this.image = setup("/tiles/door1", gamePanel.getTileSize(), gamePanel.getTileSize());
    }

    public void draw(Graphics2D graphics2D) {
        int screenX = worldX - gamePanel.getPlayer().worldX + gamePanel.getPlayer().screenX;
        int screenY = worldY - gamePanel.getPlayer().worldY + gamePanel.getPlayer().screenY;

        if (worldX + gamePanel.getTileSize() > gamePanel.getPlayer().worldX - gamePanel.getPlayer().screenX
                && worldX - gamePanel.getTileSize() < gamePanel.getPlayer().worldX + gamePanel.getPlayer().screenX
                && worldY + gamePanel.getTileSize() > gamePanel.getPlayer().worldY - gamePanel.getPlayer().screenY
                && worldY - gamePanel.getTileSize() < gamePanel.getPlayer().worldY + gamePanel.getPlayer().screenY) {
            graphics2D.drawImage(image, screenX, screenY, null);
        }
    }

    public void open() {
    }
}
