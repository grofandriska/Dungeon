package org.example.Objects;

import org.example.Display.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;


public class SuperObject {
    public String name;
    public BufferedImage image;
    public int worldX, worldY;
    public boolean collision = false;
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
