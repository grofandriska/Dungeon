package org.example.Tile;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;
    int[][] mapTileNum;
    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[30];
        mapTileNum = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
        System.out.println("loading tiles..");
        getTileImage();
        System.out.println("loading map...");
        loadMap();
        System.out.println("TileManager loaded !");
    }
    public void loadMap() {

        try {
            InputStream is = getClass().getResourceAsStream("/maps/map.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
                String line = reader.readLine();
                while (col < gamePanel.maxScreenCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gamePanel.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //imageIo loads png files
    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass1.png"));
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass2.png"));
            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Road Corner1.png"));
            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grave Haunted.png"));
            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Road Straight.png"));
            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Wall1.png"));
            tiles[6] = new Tile();
            tiles[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Road Hori.png"));
            tiles[7] = new Tile();
            tiles[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Road 3W.png"));
            tiles[10] = new Tile();
            tiles[10].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Path Hori.png"));
            tiles[11] = new Tile();
            tiles[11].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Path Straight.png"));
            tiles[12] = new Tile();
            tiles[12].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Path Corner3.png"));
            tiles[13] = new Tile();
            tiles[13].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Path Corner2.png"));
            tiles[14] = new Tile();
            tiles[14].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Path Corner4.png"));



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void draw(Graphics2D g) {
        int col = 0, row = 0, x = 0, y = 0;
        while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {

            int tileNum=mapTileNum[col][row];
            g.drawImage(tiles[tileNum].image,x,y,gamePanel.tileSize,gamePanel.tileSize,null);
            col++;
            x += gamePanel.tileSize;
            if (col == gamePanel.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gamePanel.tileSize;
            }
        }
    }
}
