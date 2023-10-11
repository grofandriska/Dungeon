package org.example.entity.objects;

import org.example.entity.objects.consum.OBJ_Key;
import org.example.Game.GamePanel;

import java.awt.*;

public class OBJ_GoldKey extends OBJ_Key {


    public OBJ_GoldKey(GamePanel gamePanel) {
        super(gamePanel);
        this.name = "Gold key";
        this.image = setup("/Pack/icons/32x32/key_01d",gamePanel.getTileSize(),gamePanel.getTileSize());
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
}
