package org.example.Entity;

import org.example.Entity.Objects.consum.OBJ_Key;
import org.example.Game.GamePanel;

import java.awt.*;

public class OBJ_GoldKey extends OBJ_Key {


    public OBJ_GoldKey(GamePanel gamePanel) {
        super(gamePanel);
        this.name = "Gold key";
        this.image = setup("/Pack/icons/32x32/key_01d",gamePanel.tileSize,gamePanel.tileSize);
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
}
