package org.example.game;

import org.example.Entity.Entity;
import org.example.Entity.Player;
import org.example.EventHandler;
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
    public int gameState;
    public final int playState = 1, pauseState = 2,dialogState = 3;
    public final int originalTileSize = 16, scale = 3, FPS = 60;
    public final int maxScreenCol = 16, maxScreenRow = 12;
    public final int maxWorldCol = 50, maxWorldRow = 50;

    public final int tileSize = originalTileSize * scale;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public AssetSetter assetSetter;
    public CollisionChecker collisionChecker;
    public Entity[] entities;
    public EventHandler eventHandler;
    public KeyHandler keyHandler;
    public Sound music;

    public Player player;
    public Sound sound;

    public SuperObject[] objects;
    public Thread gameThread;
    public TileManager tileManager;
    public UI UI;

    public GamePanel() {
        this.assetSetter = new AssetSetter(this);
        this.collisionChecker = new CollisionChecker(this);
        this.entities = new Entity[10];
        this.eventHandler = new EventHandler(this);
        this.gameThread = new Thread(this);
        this.keyHandler = new KeyHandler(this);
        this.objects = new SuperObject[10];
        this.player = new Player(this, keyHandler);
        this.tileManager = new TileManager(this);
        this.UI = new UI(this);
        this.music = new Sound();
        this.sound= new Sound();


        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
        assetSetter.setNPC();
        gameState = playState;
        playMusic(0);
    }

    public void startGameThread() {
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
        //update player and npc
        if (gameState == playState) {
            player.update();
            for (Entity entity : entities) {
                if (entity != null) entity.update();
            }
        }
        if (gameState == pauseState) {
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);

        for (SuperObject superObject : objects) {
            if (superObject != null) superObject.draw(g2, this);
        }

        for (Entity entity : entities) {
            if (entity != null) entity.draw(g2);
        }

        player.draw(g2);
        UI.draw(g2);
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
