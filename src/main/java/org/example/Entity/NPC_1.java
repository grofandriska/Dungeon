package org.example.Entity;

import org.example.game.GamePanel;

public class NPC_1 extends Entity {

    public NPC_1(GamePanel gamePanel) {
        super(gamePanel);
        getImage();
    }
    public void getImage() {

        direction = "left";
        speed = 1;
        up = setup("/entities/Old RIght1");
        up1 = setup("/entities/Old Leff2");
        up2 = setup("/entities/Old RIght1");
        right1 = setup("/entities/Old RIght1");
        right2 = setup("/entities/Old RIght2");
        right = setup("/entities/Old RIght1");

        left = setup("/entities/Old Left1");

        left1 = setup("/entities/Old Leff2");

        left2 = setup("/entities/Old Left1");
        down1 = setup("/entities/Old Down 1");
        down2 = setup("/entities/Old Down 2");
    }
}
