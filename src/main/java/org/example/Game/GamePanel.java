package org.example.Game;

import org.example.entity.Entity;
import org.example.entity.player.Player;
import org.example.Events.EventHandler;
import org.example.Handler.AssetSetter;
import org.example.Handler.collision.CollisionChecker;
import org.example.Handler.input.KeyHandler;
import org.example.Main;
import org.example.Sound.Sound;
import org.example.Tile.TileManager;
import org.example.UI.UserInreface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private int gameState;
    private final int playState = 1, pauseState = 2, dialogState = 3, endState = 4, characterState = 5,
            originalTileSize = 16, scale = 3, FPS = 60,
            maxScreenCol = 16, maxScreenRow = 12;
    private final int maxWorldCol = 50;
    private final int maxWorldRow = 50;

    private final int tileSize = originalTileSize * scale;
    private int screenWidth;
    private int screenHeight;
    private ArrayList<Entity> entityList = new ArrayList<>();
    private AssetSetter assetSetter;
    private CollisionChecker collisionChecker;
    private Entity[] entities, objects, npc, monsters, gaia;
    private EventHandler eventHandler;
    private KeyHandler keyHandler;
    private Sound music, sound;

    private Player player;
    private Thread gameThread;
    private TileManager tileManager;
    private UserInreface userInterface;

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
        this.userInterface = new UserInreface(this);
        this.music = new Sound();
        this.sound = new Sound();
        this.npc = new Entity[10];

        this.monsters = new Entity[15];
        this.addKeyListener(keyHandler);
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

    }

    public void setupGame() {

        assetSetter.setObject();
        assetSetter.setNPC();
        assetSetter.setGaia();
        assetSetter.setMonster();
        gameState = playState;
        playMusic(0);

        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();

        graphicsDevice.setFullScreenWindow(Main.window);

        screenWidth = Main.window.getWidth();
        screenHeight = Main.window.getHeight();

        player.screenX = screenWidth / 2;
        player.screenY = screenHeight / 2;

        System.out.println(screenWidth);
        System.out.println(screenHeight);

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));

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
                    if (monsters[i].isAlive && !monsters[i].isDying) {
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
        userInterface.draw(g2);
        g2.dispose();
    }

    public void playSoundEffect(int i) {
        sound.setFile(i);
        sound.play();
    }

    public void stopMusic() {
        music.stop();
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public int getPlayState() {
        return playState;
    }

    public int getPauseState() {
        return pauseState;
    }

    public int getDialogState() {
        return dialogState;
    }

    public int getEndState() {
        return endState;
    }

    public int getCharacterState() {
        return characterState;
    }

    public int getOriginalTileSize() {
        return originalTileSize;
    }

    public int getScale() {
        return scale;
    }

    public int getFPS() {
        return FPS;
    }

    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public ArrayList<Entity> getEntityList() {
        return entityList;
    }

    public void setEntityList(ArrayList<Entity> entityList) {
        this.entityList = entityList;
    }

    public AssetSetter getAssetSetter() {
        return assetSetter;
    }

    public void setAssetSetter(AssetSetter assetSetter) {
        this.assetSetter = assetSetter;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public void setCollisionChecker(CollisionChecker collisionChecker) {
        this.collisionChecker = collisionChecker;
    }

    public Entity[] getEntities() {
        return entities;
    }

    public void setEntities(Entity[] entities) {
        this.entities = entities;
    }

    public Entity[] getObjects() {
        return objects;
    }

    public void setObjects(Entity[] objects) {
        this.objects = objects;
    }

    public Entity[] getNpc() {
        return npc;
    }

    public void setNpc(Entity[] npc) {
        this.npc = npc;
    }

    public Entity[] getMonsters() {
        return monsters;
    }

    public void setMonsters(Entity[] monsters) {
        this.monsters = monsters;
    }

    public Entity[] getGaia() {
        return gaia;
    }

    public void setGaia(Entity[] gaia) {
        this.gaia = gaia;
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public void setKeyHandler(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }

    public Sound getMusic() {
        return music;
    }

    public void setMusic(Sound music) {
        this.music = music;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Thread getGameThread() {
        return gameThread;
    }

    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public void setTileManager(TileManager tileManager) {
        this.tileManager = tileManager;
    }

    public UserInreface getUserInterface() {
        return userInterface;
    }

    public void setUserInterface(UserInreface userInterface) {
        this.userInterface = userInterface;
    }
}
