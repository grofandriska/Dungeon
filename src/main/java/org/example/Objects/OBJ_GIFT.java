package org.example.Objects;

import org.example.game.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_GIFT extends SuperObject {

    GamePanel gamePanel;

    public OBJ_GIFT(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Gift";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/gift_01a.png"));
            utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        collision = true;
    }
}
