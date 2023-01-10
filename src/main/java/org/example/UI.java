package org.example;

import org.example.Objects.OBJ_Key;
import org.example.game.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gamePanel;
    Font arial40;
    Font arial80;
    //BufferedImage demoImage;
    Graphics2D graphics2D;
    public boolean isGameFinished = false;
    public boolean messageOn = false;
    public String message;
    double playtime = 0;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");
    int messageCount = 0;

    public UI(GamePanel gp) {
        this.gamePanel = gp;
        arial40 = new Font("Arial", Font.PLAIN, 40);
        arial80 = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key(gamePanel);
        //demoImage = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }
    public void drawUtility(Graphics2D g) {

        g.setFont(arial40);
        g.setColor(Color.WHITE);
       // g.drawImage(demoImage, gamePanel.tileSize / 2, gamePanel.tileSize / 2, null);
        g.drawString("Dungeon Adventures", 50, 60);
        g.drawString("x: " + gamePanel.player.worldX, 52, 110);
        g.drawString("y: " + gamePanel.player.worldY, 54, 150);
        g.drawString("Time: " + decimalFormat.format(playtime)  , 54, 195);
        playtime += (double) 10 / 120; // 1 secondDish :)
    }

    public void draw(Graphics2D g) {
        this.graphics2D = g;
        g.setFont(arial40);
        g.setColor(Color.white);

        if (gamePanel.gameState == gamePanel.playState){

        }
        if (gamePanel.gameState == gamePanel.pauseState){
            pauseState();
        }
    }
    public void pauseState(){
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.ITALIC,80F));
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gamePanel.screenWidth / 2;
        graphics2D.drawString(text,x,y);
    }
    public  int  getXForCenteredText(String text){
        int length = (int) graphics2D.getFontMetrics().getStringBounds(text,graphics2D).getWidth();
        int x = gamePanel.screenWidth / 2 - length /2;
        return x;

    }
}
