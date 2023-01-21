package org.example.Entity.npc;

import org.example.Entity.Entity;
import org.example.Entity.player.Player;
import org.example.Game.GamePanel;

public class OldMan extends Entity {

    public OldMan(GamePanel gamePanel) {
        super(gamePanel);
        name = "Old man";
        getImage();
        setDialog();
    }
    public void getImage() {
        direction = "left";
        speed = 1;
        up = setup("/entities/Old Down 1");
        up1 = setup("/entities/Old Down 1");
        up2 = setup("/entities/Old Down 2");
        right = setup("/entities/Old Right1");
        right1 = setup("/entities/Old Right1");
        right2 = setup("/entities/Old Right2");
        left = setup("/entities/Old Left1");
        left1 = setup("/entities/Old Left2");
        left2 = setup("/entities/Old Left1");
        down1 = setup("/entities/Old Down 1");
        down2 = setup("/entities/Old Down 2");
    }
    public void setDialog() {
        dialogs[0] = "Hello Stranger! \nWould you spare some?";
        dialogs[1] = "So you've come back?\nGot anything for me?";
        dialogs[2] = "Ahh! You are so good!?";
        dialogs[3] = "Test?";
    }
    public void speak(Player player) {
        super.speak(player);
    }
}