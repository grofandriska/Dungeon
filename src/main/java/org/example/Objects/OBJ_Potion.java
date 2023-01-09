package org.example.Objects;

import org.example.game.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Potion extends SuperObject {

    GamePanel gamePanel;

    public OBJ_Potion(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Potion";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/potion_01h.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
