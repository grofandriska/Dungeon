package org.example;

import org.example.Entity.Player;
import org.example.Entity.Player2;
import org.example.Tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;// final scale on modern widescreen monitors 48px
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;//760 px
    final int screenHeight = tileSize * maxScreenRow;//576 px
    int FPS = 60;
    KeyHandler keyHandler = new KeyHandler();

    KeyHandlerTwo keyHandlerTwo = new KeyHandlerTwo();
    Thread gameThread;

    TileManager tileManager = new TileManager(this);

    Player player = new Player(this,keyHandler);

    Player2 player2 = new Player2(this,keyHandlerTwo);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.addKeyListener(keyHandlerTwo);
        this.setFocusable(true);
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
                 remainingTime = remainingTime/100000;

                if (remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            }catch (InterruptedException e){
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
        player.draw(g2);
        player2.draw(g2);
        g2.dispose();
    }
}
