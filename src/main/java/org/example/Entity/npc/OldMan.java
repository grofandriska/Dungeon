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

        up1 = setup("/entities/Old Down 1",gamePanel.tileSize,gamePanel.tileSize);
        up2 = setup("/entities/Old Down 2",gamePanel.tileSize,gamePanel.tileSize);

        right1 = setup("/entities/Old Right1",gamePanel.tileSize,gamePanel.tileSize);
        right2 = setup("/entities/Old Right2",gamePanel.tileSize,gamePanel.tileSize);

        left1 = setup("/entities/Old Left2",gamePanel.tileSize,gamePanel.tileSize);
        left2 = setup("/entities/Old Left1",gamePanel.tileSize,gamePanel.tileSize);

        down1 = setup("/entities/Old Down 1",gamePanel.tileSize,gamePanel.tileSize);
        down2 = setup("/entities/Old Down 2",gamePanel.tileSize,gamePanel.tileSize);
    }

    public void setDialog() {
        dialogs[0] = "Hello Stranger! \nI am Altas the old sailor and I don't know you ! \n";
        dialogs[4] = "What's bring you here! \n!";
        dialogs[1] = "So you've come back?\nGot anything for me?";
        dialogs[2] = "Ahh! You are so good!?";
        dialogs[3] = "I am the ?";
    }

    public void speak() {
        super.speak();
    }
}
