package org.example.Entity.monsters;

import org.example.Entity.Entity;
import org.example.Game.GamePanel;

public class Orc extends Entity {
    public Orc(GamePanel gamePanel) {
        super(gamePanel);
        getImage();
        name = "Orc";
        life = 100;
        this.direction = "up";
        this.speed = 1;
    }

    public void getImage() {
        up1 = setup("/entities/Zombie 1",gamePanel.tileSize,gamePanel.tileSize);
        up2 = setup("/entities/Zombie 1",gamePanel.tileSize,gamePanel.tileSize);
        right1 = setup("/entities/Zombie Right 1",gamePanel.tileSize,gamePanel.tileSize);
        right2 = setup("/entities/Zombie Right 2",gamePanel.tileSize,gamePanel.tileSize);
        left1 = setup("/entities/Zombie Left 1",gamePanel.tileSize,gamePanel.tileSize);
        left2 = setup("/entities/Zombie Left 2",gamePanel.tileSize,gamePanel.tileSize);
        down1 = setup("/entities/Zombie 1",gamePanel.tileSize,gamePanel.tileSize);
        down2 = setup("/entities/Zombie 1",gamePanel.tileSize,gamePanel.tileSize);
    }



}
