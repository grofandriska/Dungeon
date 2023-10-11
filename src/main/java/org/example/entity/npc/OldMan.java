package org.example.entity.npc;

import org.example.entity.Entity;
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

        up1 = setup("/entities/oldman/Old Down 1",gamePanel.getTileSize(),gamePanel.getTileSize());
        up2 = setup("/entities/oldman/Old Down 2",gamePanel.getTileSize(),gamePanel.getTileSize());

        right1 = setup("/entities/oldman/Old Right1",gamePanel.getTileSize(),gamePanel.getTileSize());
        right2 = setup("/entities/oldman/Old Right2",gamePanel.getTileSize(),gamePanel.getTileSize());

        left1 = setup("/entities/oldman/Old Left2",gamePanel.getTileSize(),gamePanel.getTileSize());
        left2 = setup("/entities/oldman/Old Left1",gamePanel.getTileSize(),gamePanel.getTileSize());

        down1 = setup("/entities/oldman/Old Down 1",gamePanel.getTileSize(),gamePanel.getTileSize());
        down2 = setup("/entities/oldman/Old Down 2",gamePanel.getTileSize(),gamePanel.getTileSize());
    }

    public void setDialog() {
        dialogs[0] = "Hello Stranger!\nI am Princip the old sailor\nand I don't know you!";
        dialogs[1] = "Ahh so you are :"+ gamePanel.getPlayer().name;
        dialogs[2] = "Have you seen the wizard?!\nI bet you don't. He is\nuntouchable";
        dialogs[3] = "If you are fast enough\nyou can deal damage to Him!";
        dialogs[4] = "I see that your\nattack power is :"+gamePanel.getPlayer().attack+".";
        dialogs[5] = "You can preform critical attack\nbut it's random tho'.";
    }

    public void speak() {
        super.speak();
    }
}
