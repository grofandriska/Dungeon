package org.example.entity.objects.consum;

import org.example.entity.Entity;
import org.example.Game.GamePanel;

public class OBJ_SpeedPotion extends Entity {

    public OBJ_SpeedPotion(GamePanel gamePanel) {
        super(gamePanel);

        //entity attributes
        this.name = "Speed Potion";
        this.type = 4;

        //entity static
        this.image = setup("/objects/potion_01h", gamePanel.getTileSize(), gamePanel.getTileSize());
        this.description = "[" + name + "]\n" + "It's a potion of speed. \nUse it if you are in a hurry!";
    }

    public void consume() {
        gamePanel.getPlayer().speed += 1;
        gamePanel.getUserInterface().addMessage(name + " consumed. you are faster now");
    }
}
