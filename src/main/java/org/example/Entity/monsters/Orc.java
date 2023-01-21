package org.example.Entity.monsters;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

public class Orc extends Entity {
    public Orc(GamePanel gamePanel) {
        super(gamePanel);
        getImage();
        name = "Orc";
        this.direction = "up";
        this.speed = 1;
    }

    public void getImage() {
        up1 = setup("/entities/Zombie 1");
        up2 = setup("/entities/Zombie 1");
        right = setup("/entities/Zombie Right 2");
        right1 = setup("/entities/Zombie Right 1");
        right2 = setup("/entities/Zombie Right 2");
        left = setup("/entities/Zombie Left 1");
        left1 = setup("/entities/Zombie Left 1");
        left2 = setup("/entities/Zombie Left 2");
        down1 = setup("/entities/Zombie 1");
        down2 = setup("/entities/Zombie 1");
    }



}
