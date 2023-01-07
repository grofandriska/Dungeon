package org.example.Display;

import org.example.Entity.Player;
import org.example.Entity.Player2;
import org.example.Handler.AssetSetter;
import org.example.Handler.CollisionChecker;
import org.example.Handler.KeyHandler;
import org.example.Handler.KeyHandlerTwo;
import org.example.Objects.SuperObject;
import org.example.Tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;// final scale on modern widescreen monitors 48px
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;//760 px
    public final int screenHeight = tileSize * maxScreenRow;//576 px
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    //Use this variables pls
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    int FPS = 60;
    KeyHandler keyHandler = new KeyHandler();
    KeyHandlerTwo keyHandlerTwo = new KeyHandlerTwo();
    Thread gameThread;
    public TileManager tileManager = new TileManager(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public AssetSetter assetSetter = new AssetSetter(this);
    public Player player = new Player(this, keyHandler);
    public Player2 player2 = new Player2(this, keyHandlerTwo);

    public SuperObject[] obj = new SuperObject[10];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addKeyListener(keyHandlerTwo);
        this.setFocusable(true);
    }

    public void setupAsset() {
        assetSetter.setObject();
    }

    //start this class on a Thread
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // The engine of the game, managing the sequence of @update and @repaint (@paintcomponent())
    @Override
    public void run() {

        long drawInterval = 1000000000 / FPS;
        long nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 100000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //updating player
    public void update() {
        player2.update();
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        for (SuperObject superObject : obj) {
            if (superObject != null) superObject.draw(g2, this);
        }
        player.draw(g2);
        player2.draw(g2);
        g2.dispose();
    }
}
