package org.example.Game;

import org.example.Entity.Entity;
import org.example.Entity.player.Player;
import org.example.Events.EventHandler;
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
    public final int playState = 1, pauseState = 2, dialogState = 3, endState = 4,characterState = 5, originalTileSize = 16, scale = 3, FPS = 60, maxScreenCol = 16, maxScreenRow = 12;
    public final int maxWorldCol = 50, maxWorldRow = 50, tileSize = originalTileSize * scale, screenWidth = tileSize * maxScreenCol, screenHeight = tileSize * maxScreenRow;
    public ArrayList<Entity> entityList = new ArrayList<>();
    public AssetSetter assetSetter;
    public CollisionChecker collisionChecker;
    public Entity[] entities, objects, npc, monsters, gaia;
    public EventHandler eventHandler;
    public KeyHandler keyHandler;
    public Sound music, sound;

    public Player player;
    public Thread gameThread;
    public TileManager tileManager;
    public UI UI;

    public GamePanel() {
        this.gaia = new Entity[15];
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

        this.monsters = new Entity[15];

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
        assetSetter.setNPC();
        assetSetter.setGaia();
        assetSetter.setMonster();
        gameState = playState;
        playMusic(9);
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
        if (gameState == playState) {
            player.update();

            for (int i = 0; i < monsters.length; i++) {
                if (monsters[i] != null) {
                    if (monsters[i].alive && !monsters[i].dying) {
                        monsters[i].update();
                    }
                }
            }
            for (Entity item : entities) {
                if (item != null) {
                    item.update();
                }
            }
            for (Entity value : npc) {
                if (value != null) {
                    value.update();
                }
            }
            for (Entity entity : gaia) {
                if (entity != null) {
                    entity.update();
                }
            }
        }
        if (gameState == pauseState) {
        }
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);

        entityList.add(player);

        for (Entity entity : entities) {
            if (entity != null) {
                entityList.add(entity);
            }
        }
        for (Entity object : objects) {
            if (object != null) {
                entityList.add(object);
            }
        }
        for (Entity object : objects) {
            if (object != null) {
                object.draw(g2);
            }
        }

        for (Entity monster : monsters) {
            if (monster != null) {
                entityList.add(monster);
            }
        }
        for (Entity item : npc) {
            if (item != null) {
                entityList.add(item);
            }
        }
        for (Entity value : gaia) {
            if (value != null) {
                entityList.add(value);
            }
        }
        entityList.sort(Comparator.comparingInt(o -> o.worldY));

        for (Entity entity : entityList) {
            if (entity != null) {
                entity.draw(g2);
            }
        }

        entityList.clear();

        UI.draw(g2);
        g2.dispose();
    }

    public void playSoundEffect(int i) {
        sound.setFile(i);
        sound.play();
    }

    public void stopMusic() {
        music.stop();
    }
}
