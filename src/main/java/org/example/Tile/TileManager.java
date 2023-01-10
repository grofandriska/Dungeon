package org.example.Tile;

import org.example.Handler.UtilityTool;
import org.example.game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tiles;
    public int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[30];
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTileImage();
        loadMap();
    }
    public void setup(int index, String imagePath, boolean collision) {
        UtilityTool utilityTool = new UtilityTool();

        try {
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath + ".png"));
            tiles[index].image = utilityTool.scaleImage(tiles[index].image, gamePanel.tileSize, gamePanel.tileSize);
            tiles[index].collision = collision;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void getTileImage() {
        setup(0,"Grass1",false);
        setup(1,"Tree",true);
        setup(2,"Grave",false);
        setup(3,"Grave Haunted",false);
        setup(4,"Path Corner1",false);
        setup(5,"Path Corner2",false);
        setup(6,"Path Corner3",false);
        setup(7,"Path Corner4",false);
        setup(8,"Path Horizontal",false);
        setup(9,"Path Stop1",false);
        setup(10,"Path Stop2",false);
        setup(11,"Path Stop3",false);
        setup(13,"Path Stop4",false);
        setup(12,"Path Straight",false);
        setup(14,"Road Corner1",false);
        setup(15,"Road Corner2",false);
        setup(16,"Road Corner3",false);
        setup(17,"Road Corner4",false);
        setup(18,"Road Hori",false);
        setup(19,"Road Stop1",false);
        setup(20,"Road Stop2",false);
        setup(21,"Road Stop3",false);
        setup(22,"Road Stop4",false);
        setup(23,"Road Straight",false);
        setup(24,"Road 3W",false);
        setup(25,"Wall1",true);
        setup(26,"Wall2",true);
        setup(27,"Wall3",true);
        setup(28,"Wall4",true);
    }
    public void loadMap() {
        try {
            int col = 0;
            int row = 0;

            InputStream input = getClass().getResourceAsStream("/maps/map.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
                String line = reader.readLine();
                while (col < gamePanel.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gamePanel.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX
                    && worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX
                    && worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY
                    && worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

                g.drawImage(tiles[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }

            worldCol++;

            if (worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
