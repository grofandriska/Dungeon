package org.example.entity.npc.monsters;

import org.example.entity.Entity;
import org.example.Game.GamePanel;

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
        setImage();
    }
    public void damageReaction() {
        if (this.life <= maxLife - 6) {
            setDirectionCounter = 0;
            this.direction = gamePanel.getPlayer().direction;
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
        up1 = setup("/entities/orc/Zombie 1", gamePanel.getTileSize(), gamePanel.getTileSize());
        up2 = setup("/entities/orc/Zombie 1", gamePanel.getTileSize(), gamePanel.getTileSize());

        right1 = setup("/entities/orc/Zombie Right 1", gamePanel.getTileSize(), gamePanel.getTileSize());
        right2 = setup("/entities/orc/Zombie Right 2", gamePanel.getTileSize(), gamePanel.getTileSize());

        left1 = setup("/entities/orc/Zombie Left 1", gamePanel.getTileSize(), gamePanel.getTileSize());
        left2 = setup("/entities/orc/Zombie Left 2", gamePanel.getTileSize(), gamePanel.getTileSize());

        down1 = setup("/entities/orc/Zombie 1", gamePanel.getTileSize(), gamePanel.getTileSize());
        down2 = setup("/entities/orc/Zombie 1", gamePanel.getTileSize(), gamePanel.getTileSize());
    }
}
