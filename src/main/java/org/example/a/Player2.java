package org.example.a;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

public class Player2 {
    GamePanel gamePanel;
    int goalX, goalY, speed, worldX, worldY;
    public BufferedImage image;
    public Rectangle solidArea;

    {
        solidArea = new Rectangle(0, 0, 48, 48);
        speed = 1;
        worldX = 10;
        worldY = 10;
        goalX = 200;
        goalY = 150;
    }

    public Player2(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        image = ImageLoader.setup("/New Woman", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void movePLayer() {
        if ((worldX != goalX || worldY != goalY)) {
            if (goalX < worldX) {
                worldX -= speed;
            }
             if (goalX > worldX) {
                worldX += speed;
            }
            if (goalY < worldY) {
                worldY -= speed;
            }
            if (goalY > worldY) {
                worldY += speed;
            }
        }
        else generateNewGoal();
    }
    public void generateNewGoal(){
        goalX = ThreadLocalRandom.current().nextInt(0, gamePanel.screenWidth);
        goalY = ThreadLocalRandom.current().nextInt(0, gamePanel.screenHeight);
    }
}
