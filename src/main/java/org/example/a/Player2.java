package org.example.a;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

public class Player2 {

    GamePanel gamePanel;
    public int worldX = 10;
    public int worldY = 10;

    public int speed = 30;

    int goalX = 200, goalY = 200;

    public BufferedImage image;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);

    public Player2(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void movePLayer() {
        if (worldX != goalX || worldY != goalY) {
            if (goalX < worldX) {
                worldX -= 1;
            }
            if (goalX > worldX) {
                worldX += 1;
            }
            if (goalY < worldY) {
                worldY -= 1;
            }
            if (goalY > worldY) {
                worldY += 1;
            }


        }
        if (worldX == goalX || worldY == goalY) {
            goalX = ThreadLocalRandom.current().nextInt(0, gamePanel.screenWidth);
            goalY = ThreadLocalRandom.current().nextInt(0, gamePanel.screenHeight);
        }
    }
}
