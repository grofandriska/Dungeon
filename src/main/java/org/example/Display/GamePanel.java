package org.example.Display;

import org.example.Entity.Player;
import org.example.Handler.AssetSetter;
import org.example.Handler.CollisionChecker;
import org.example.Handler.KeyHandler;
import org.example.Objects.SuperObject;
import org.example.Sound.Sound;
import org.example.Tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    public final int
            originalTileSize = 16, scale = 3, FPS = 60,
            tileSize = originalTileSize * scale,
            maxScreenCol = 16, maxScreenRow = 12, screenWidth = tileSize * maxScreenCol,
            screenHeight = tileSize * maxScreenRow,
            maxWorldCol = 50, maxWorldRow = 50;
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    Sound sound = new Sound();
    public SuperObject[] obj = new SuperObject[10];
    public AssetSetter assetSetter = new AssetSetter(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyHandler);
    public TileManager tileManager = new TileManager(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

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

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) obj[i].draw(g2, this);
        }
        player.draw(g2);
        g2.dispose();
    }
    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic() {
        sound.stop();
    }
    public void playSoundEffect(int i) {
        sound.setFile(i);
        sound.play();
    }
}
