package org.example.Entity;

import org.example.game.GamePanel;

public class NPC_1 extends Entity {

    public NPC_1(GamePanel gamePanel) {
        super(gamePanel);
        getImage();
        setDialog();
    }

    public void getImage() {
        direction = "left";
        speed = 1;
        up = setup("/player/Zombie 1");
        up1 = setup("/player/Zombie 1");
        up2 = setup("/player/Zombie 1");
        right = setup("/player/zombie Right 1");
        right1 = setup("/player/Zombie Right 1");
        right2 = setup("/player/Zombie Right 2");
        left = setup("/player/Zombie Left 1");
        left1 = setup("/player/Zombie Left 2");
        left2 = setup("/player/Zombie Left 1");
        down1 = setup("/entities/Old Down 1");
        down2 = setup("/entities/Old Down 2");
    }

    public void setDialog() {
        dialogs[0] = "Hello Stranger! \nWould you spare some?";
        dialogs[1] = "So you've come back?\nGot anything for me?";
        dialogs[2] = "Ahh! You are so good!?";
        dialogs[3] = "Test?";
    }

    //player @param -> for quests and logics
    public void speak(Player player) {
        super.speak(player);
    }
}
