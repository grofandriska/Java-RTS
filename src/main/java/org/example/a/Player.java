package org.example.a;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {

    private boolean isSelected ;
    private GamePanel gamePanel;
    private int worldX, worldY, speed,goalX,goalY;
    private BufferedImage image;
    private Rectangle solidArea;

    {
        this.isSelected = false;
        this.worldX = 160;
        this.worldY = 160;
        this.goalX = this.worldX;
        this.goalY = this.worldY;
        this.image = ImageLoader.setup("/NHD1", 48,48);
        this.solidArea = new Rectangle(this.worldX, this.worldY, 48, 48);
    }

    public Player(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void movePLayer() {
        if (getWorldX() != this.goalX || getWorldY() != this.goalY) {
            gamePanel.getUi().showClick(gamePanel.getMouse().getMouseX(),gamePanel.getMouse().getMouseY());
            if (getGoalX() < getWorldX()) {
                setWorldX(getWorldX() - 1);
                getSolidArea().x = getWorldX();
            }
            if (getGoalX() > getWorldX()) {
                setWorldX(getWorldX() + 1);
                getSolidArea().x = getWorldX();
            }
            if (getGoalY() < getWorldY()) {
                setWorldY(getWorldY() - 1);
                getSolidArea().y = getWorldY();
            }
            if (getGoalY() > getWorldY()) {
                setWorldY(getWorldY() + 1);
                getSolidArea().y = getWorldY();
            }
        }
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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
