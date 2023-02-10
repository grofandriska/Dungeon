package org.example.Entity.monsters;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

import java.awt.*;
import java.util.Random;

public class Monster_ORC extends Entity {
    public Monster_ORC(GamePanel gamePanel) {
        super(gamePanel);

        //entity static
        this.name = "Orc";
        this.speed = 1;
        this.type = 2;
        this.maxLife = 10;
        this.life = maxLife;
        this.attack = 3;
        this.defense = 0;
        this.exp = 3;

        //entity attributes
        this.solidAreaRectangle = new Rectangle();
        this.solidAreaRectangle.x = 3;
        this.solidAreaRectangle.y = 5;
        this.solidAreaRectangle.height = 45;
        this.solidAreaRectangle.width = 43;
        this.solidAreaDefaultX = solidAreaRectangle.x;
        this.solidAreaDefaultY = solidAreaRectangle.y;

        setImage();
    }
    public void damageReaction() {
        if (this.life <= maxLife - 6) {
            setDirectionCounter = 0;
            this.direction = gamePanel.player.direction;
        }
    }
    public void setNewDirection() {
        setDirectionCounter++;
        if (setDirectionCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) direction = "down";
            if (i >= 25 && i <= 50) direction = "left";
            if (i >= 50 && i <= 75) direction = "right";
            if (i >= 75 && i <= 100) direction = "up";
            setDirectionCounter = 0;
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
