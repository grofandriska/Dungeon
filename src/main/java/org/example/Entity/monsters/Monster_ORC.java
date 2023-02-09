package org.example.Entity.monsters;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

import java.awt.*;
import java.util.Random;

public class Monster_ORC extends Entity {
    public Monster_ORC(GamePanel gamePanel) {
        super(gamePanel);
        name = "Orc";

        speed = 1;
        type = 2;
        maxLife = 10;
        life = maxLife;
        attack = 3;
        defense = 0;
        exp = 3;

        solidArea = new Rectangle();
        solidArea.x = 3;
        solidArea.y = 5;
        solidArea.height = 45;
        solidArea.width = 43;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setImage();
    }

    public void damageReaction() {
        if (this.life <= maxLife - 6) {
            imageCounter = 0;
            this.direction = gamePanel.player.direction;
        }
    }
    public void setDirection() {
        imageCounter++;
        if (imageCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) direction = "down";
            if (i >= 25 && i <= 50) direction = "left";
            if (i >= 50 && i <= 75) direction = "right";
            if (i >= 75 && i <= 100) direction = "up";
            imageCounter = 0;
        }
        //makes characters movement.
    }
    public void setImage() {
        up1 = setup("/entities/orc/Zombie 1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/entities/orc/Zombie 1", gamePanel.tileSize, gamePanel.tileSize);

        right1 = setup("/entities/orc/Zombie Right 1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/entities/orc/Zombie Right 2", gamePanel.tileSize, gamePanel.tileSize);

        left1 = setup("/entities/orc/Zombie Left 1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/entities/orc/Zombie Left 2", gamePanel.tileSize, gamePanel.tileSize);

        down1 = setup("/entities/orc/Zombie 1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/entities/orc/Zombie 1", gamePanel.tileSize, gamePanel.tileSize);
    }
}
