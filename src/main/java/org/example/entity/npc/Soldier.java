package org.example.entity.npc;

import org.example.entity.Entity;
import org.example.Game.GamePanel;

public class Soldier extends Entity {
    public Soldier(GamePanel gamePanel) {
        super(gamePanel);
        getImage();
        name = "Soldier";
        direction = "down";
        speed = 1 ;
        setDialog();
    }

    public void setDialog() {
        dialogs[0] = "Be Aware!\nOrcs are everywhere!\nDo you know how to handle?!\n";
        dialogs[1] = "When you get close,\npress ENTER to attack!";
        dialogs[2] = "Blinking means entity\ncan't receive damage.";
        dialogs[3] = "Let's see how many\ncan you kill...";
        dialogs[4] = "...";
    }

    public void getImage() {
        up1 = setup("/entities/soldier/Soldier Up1",gamePanel.getTileSize(),gamePanel.getTileSize());
        up2 = setup("/entities/soldier/Soldier Up2",gamePanel.getTileSize(),gamePanel.getTileSize());

        right1 = setup("/entities/soldier/Soldier Right 1",gamePanel.getTileSize(),gamePanel.getTileSize());
        right2 = setup("/entities/soldier/Soldier Right 2",gamePanel.getTileSize(),gamePanel.getTileSize());

        left1 = setup("/entities/soldier/Soldier Left 1",gamePanel.getTileSize(),gamePanel.getTileSize());
        left2 = setup("/entities/soldier/Soldier Left 2",gamePanel.getTileSize(),gamePanel.getTileSize());
        down1 = setup("/entities/soldier/Soldier Down 1",gamePanel.getTileSize(),gamePanel.getTileSize());
        down2 = setup("/entities/soldier/Soldier Down 2",gamePanel.getTileSize(),gamePanel.getTileSize());
    }
}
