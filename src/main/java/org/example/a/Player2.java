package org.example.a;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

public class Player2 {
    private GamePanel gamePanel;
    private int goalX, goalY, speed, worldX, worldY;
    private BufferedImage image;
    private Rectangle solidArea;

    {
        this.worldX = 10;
        this.worldY = 10;
        this.goalX = worldX;
        this.goalY = worldY;
        this.speed = 1;
        this.solidArea = new Rectangle(0, 0, 48, 48);
    }

    public Player2(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.image = ImageLoader.setup("/New Woman", gamePanel.getTileSize(), gamePanel.getTileSize());
    }

    public void movePLayer() {
        if ((this.worldX != this.goalX || this.worldY != this.goalY)) {
            if (this.goalX < this.worldX) {
                this.worldX -= this.speed;
            }
             if (goalX > worldX) {
                this.worldX += this.speed;
            }
            if (goalY < worldY) {
                this.worldY -= this.speed;
            }
            if (goalY > worldY) {
                this.worldY += this.speed;
            }
        }
        else generateNewGoal();
    }
    public void generateNewGoal(){
        this.goalX = ThreadLocalRandom.current().nextInt(0, gamePanel.getScreenWidth());
        this.goalY = ThreadLocalRandom.current().nextInt(0, gamePanel.getScreenHeight());
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public int getGoalX() {
        return goalX;
    }

    public void setGoalX(int goalX) {
        this.goalX = goalX;
    }

    public int getGoalY() {
        return goalY;
    }

    public void setGoalY(int goalY) {
        this.goalY = goalY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }
}
