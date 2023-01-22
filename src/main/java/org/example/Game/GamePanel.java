package org.example.Game;

import org.example.Entity.Entity;
import org.example.Entity.player.Player;
import org.example.Event.EventHandler;
import org.example.Handler.draw.AssetSetter;
import org.example.Handler.collision.CollisionChecker;
import org.example.Handler.input.KeyHandler;
import org.example.Sound.Sound;
import org.example.Tile.TileManager;
import org.example.UI.UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
    public int gameState;
    public final int playState = 1, pauseState = 2, dialogState = 3, endState = 4;
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

    public Entity[] objects;
    public Entity[] npc;
    public Entity[] gaia = new Entity[6];
    public Entity[] monsters;
    public Thread gameThread;
    public TileManager tileManager;
    public UI UI;

    ArrayList<Entity> entityList = new ArrayList<>();

    public GamePanel() {
        this.assetSetter = new AssetSetter(this);
        this.collisionChecker = new CollisionChecker(this);
        this.entities = new Entity[10];
        this.eventHandler = new EventHandler(this);
        this.gameThread = new Thread(this);
        this.keyHandler = new KeyHandler(this);
        this.objects = new Entity[10];
        this.player = new Player(this, keyHandler);
        this.tileManager = new TileManager(this);
        this.UI = new UI(this);
        this.music = new Sound();
        this.sound = new Sound();
        this.npc = new Entity[10];

        this.monsters = new Entity[10];


        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
        assetSetter.setNPC();
        assetSetter.setMonster();
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
            for (int i = 0; i < monsters.length; i++) {
                if (monsters[i] != null) {
                    monsters[i].update();
                }
            }
            for (int i = 0; i < entities.length; i++) {
                if (entities[i] != null) {
                    entities[i].update();
                }
            }
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
            for (int i = 0; i < gaia.length; i++) {
                if (gaia[i] != null) {
                    gaia[i].update();
                }
            }
        }
        if (gameState == pauseState) {
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);

        entityList.add(player);


        for (int i = 0; i < entities.length; i++) {
            if (entities[i] != null) {
                entityList.add(entities[i]);
            }
        }


        for (int i = 0; i < objects.length; i++) {
            if (objects[i] != null) {
                entityList.add(objects[i]);
            }
        }

        for (int i = 0; i < monsters.length; i++) {
            if (monsters[i] != null) {
                entityList.add(monsters[i]);
            }
        }
        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                entityList.add(npc[i]);
            }
        }
        for (int i = 0; i < gaia.length; i++) {
            if (gaia[i] != null) {
                entityList.add(gaia[i]);
            }
        }
        entityList.sort(Comparator.comparingInt(o -> o.worldY));

        for (int i = 0; i < entityList.size(); i++) {
            if (entityList.get(i) != null) {
                entityList.get(i).draw(g2);
            }
        }

        entityList.clear();

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
