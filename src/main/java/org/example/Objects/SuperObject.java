package org.example.Objects;

import org.example.game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;


public class SuperObject {
    public String name;
    public BufferedImage image;
    public int worldX, worldY;

    public boolean collision = false;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);

    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g, GamePanel gamePanel) {

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX
                && worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX
                && worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY
                && worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

            g.drawImage(this.image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }
}
