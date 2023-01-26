package org.example.Entity.npc;

import org.example.Entity.Entity;
import org.example.Entity.player.Player;
import org.example.Game.GamePanel;

public class OldMan extends Entity {

    public OldMan(GamePanel gamePanel) {
        super(gamePanel);

        name = "Old man";
        type = 1;
        life = maxLife;
        maxLife = 2;

        getImage();
        setDialog();
    }

    public void getImage() {
        direction = "left";
        speed = 1;

        up1 = setup("/entities/oldman/Old Down 1",gamePanel.tileSize,gamePanel.tileSize);
        up2 = setup("/entities/oldman/Old Down 2",gamePanel.tileSize,gamePanel.tileSize);

        right1 = setup("/entities/oldman/Old Right1",gamePanel.tileSize,gamePanel.tileSize);
        right2 = setup("/entities/oldman/Old Right2",gamePanel.tileSize,gamePanel.tileSize);

        left1 = setup("/entities/oldman/Old Left2",gamePanel.tileSize,gamePanel.tileSize);
        left2 = setup("/entities/oldman/Old Left1",gamePanel.tileSize,gamePanel.tileSize);

        down1 = setup("/entities/oldman/Old Down 1",gamePanel.tileSize,gamePanel.tileSize);
        down2 = setup("/entities/oldman/Old Down 2",gamePanel.tileSize,gamePanel.tileSize);
    }

    public void setDialog() {
        dialogs[0] = "Hello Stranger!\nI am Altas the old sailor\nand I don't know you!";
        dialogs[1] = "If you loose your health\n the game is over!";
        dialogs[2] = "Please come back later";
        dialogs[3] = "...";
        dialogs[4] = "I am just an old man!";
    }

    public void speak() {
        super.speak();
    }
}
