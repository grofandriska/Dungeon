package org.example.game;

import org.example.Entity.Entity;
import org.example.Entity.Player;
import org.example.Handler.AssetSetter;
import org.example.Handler.CollisionChecker;
import org.example.Handler.KeyHandler;
import org.example.Objects.SuperObject;
import org.example.Sound.Sound;
import org.example.Tile.TileManager;
import org.example.UI.UI;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    public final int originalTileSize = 16, scale = 3, FPS = 60;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16, maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int maxWorldCol = 50, maxWorldRow = 50;

    public KeyHandler keyHandler = new KeyHandler(this);
    Sound sound = new Sound();
    Sound music = new Sound();

    public Thread gameThread;
    public UI ui = new UI(this);
    public Entity[] entities = new Entity[10];
    public SuperObject[] target = new SuperObject[10];
    public Player player = new Player(this, keyHandler);
    public TileManager tileManager = new TileManager(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

    public final int dialogState = 3;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }
    public void setupGame() {
        assetSetter.setObject();
        assetSetter.setNPC();
        playMusic(0);
        gameState = playState;
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
        if (gameState == playState) {
            player.update();
            for (Entity entity : entities) {
                if (entity != null) entity.update();
            }
        }
        if (gameState == pauseState){
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        for (SuperObject superObject : target) {
            if (superObject != null) superObject.draw(g2, this);
        }

        for (Entity entity : entities) {
            if (entity != null) entity.draw(g2);
        }
        player.draw(g2);
        ui.draw(g2);
        ui.drawUtility(g2);
        g2.dispose();
    }
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic() {
        music.stop();
    }
    public void playSoundEffect(int i) {
        sound.setFile(i);
        sound.play();
    }
}
