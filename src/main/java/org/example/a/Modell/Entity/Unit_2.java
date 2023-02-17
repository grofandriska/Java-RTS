package org.example.a.Modell.Entity;

import org.example.a.Graphic.ImageLoader;
import org.example.a.JPanel.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

public class Unit_2 {
    private GamePanel gamePanel;
    private boolean isSelected = false;
    private int goalX, goalY, speed, worldX, worldY;
    private BufferedImage image;
    private Rectangle solidArea;

    {
        this.worldX = 44;
        this.worldY = 500;
        this.goalX = worldX + 50;
        this.goalY = worldY + 50;
        this.speed = 1;
        this.solidArea = new Rectangle(worldX, worldY, 48, 48);
    }

    public Unit_2(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.image = ImageLoader.setup("/New Woman", gamePanel.getTileSize(), gamePanel.getTileSize());
    }

    public void movePLayer() {
        if ((this.worldX != this.goalX || this.worldY != this.goalY && this.goalY < gamePanel.getScreenHeight() - 3 * 48 && gamePanel.getScreenHeight() > 3 * 48)) {
            if (this.goalX < this.worldX) {
                this.worldX -= this.speed;
                this.solidArea.x = worldX;
            }
            if (goalX > worldX) {
                this.worldX += this.speed;
                this.solidArea.x = worldX;
            }
            if (goalY < worldY) {
                this.worldY -= this.speed;
                this.solidArea.y = worldY;
            }
            if (goalY > worldY) {
                this.worldY += this.speed;
                this.solidArea.y = worldY;
            }
        } else generateNewGoal();
    }

    public void generateNewGoal() {
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
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
