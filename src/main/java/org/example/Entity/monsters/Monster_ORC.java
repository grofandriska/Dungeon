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

        maxLife = 100;

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
        up1 = setup("/entities/orc/Zombie 1",gamePanel.tileSize,gamePanel.tileSize);
        up2 = setup("/entities/orc/Zombie 1",gamePanel.tileSize,gamePanel.tileSize);

        right1 = setup("/entities/orc/Zombie Right 1",gamePanel.tileSize,gamePanel.tileSize);
        right2 = setup("/entities/orc/Zombie Right 2",gamePanel.tileSize,gamePanel.tileSize);

        left1 = setup("/entities/orc/Zombie Left 1",gamePanel.tileSize,gamePanel.tileSize);
        left2 = setup("/entities/orc/Zombie Left 2",gamePanel.tileSize,gamePanel.tileSize);

        down1 = setup("/entities/orc/Zombie 1",gamePanel.tileSize,gamePanel.tileSize);
        down2 = setup("/entities/orc/Zombie 1",gamePanel.tileSize,gamePanel.tileSize);
    }
}
