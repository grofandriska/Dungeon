package org.example.Objects;

import org.example.game.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends SuperObject {

    GamePanel gamePanel;

    public OBJ_Heart(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/Heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/Heart_empty.png"));
            image = utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
            image2 = utilityTool.scaleImage(image2, gamePanel.tileSize, gamePanel.tileSize);
            image3 = utilityTool.scaleImage(image3, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
