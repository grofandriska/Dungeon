package org.example.Entity.monsters;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

import java.awt.*;

public class Monster_ORC extends Entity {
    public Monster_ORC(GamePanel gamePanel) {
        super(gamePanel);

        name = "Orc";

        speed = 2;

        type = 2;

        maxLife = 4;

        life = maxLife;

        solidArea = new Rectangle();

        solidArea.x = 3;
        solidArea.y = 5;

        solidArea.height = 45;
        solidArea.width = 43;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setImage();
    }

    public void setImage(){
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
