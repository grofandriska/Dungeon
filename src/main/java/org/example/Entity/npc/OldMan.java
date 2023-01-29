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
        dialogs[0] = "Hello Stranger!\nI am Princip the old sailor\nand I don't know you!";
        dialogs[1] = "Ahh so you are :"+ gamePanel.player.playerName;
        dialogs[2] = "Have you seen the wizard?!\nI bet you don't. He is\nuntouchable";
        dialogs[3] = "If you are fast enough\nyou can deal damage to Him!";
        dialogs[4] = "I see that your\nattack power is :"+gamePanel.player.attack+".";
        dialogs[5] = "You can preform critical attack\nbut it's random tho'.";
    }

    public void speak() {
        super.speak();
    }
}
