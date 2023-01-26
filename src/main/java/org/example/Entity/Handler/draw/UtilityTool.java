package org.example.Entity.Handler.draw;

import java.awt.*;
import java.awt.image.BufferedImage;


/***
 * This class utilises zhe drawing procedure @param 1 :image @param 2 :width @param 3: height
 *
 */

public class UtilityTool {

    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }
}
