package org.example.a;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {

    GamePanel gamePanel;
    private int worldX, worldY, speed;
    private BufferedImage image;
    public Rectangle solidArea;

    {
        this.worldX = 160;
        this.worldY = 160;
        this.image = ImageLoader.setup("/NHD1", 48,48);
        this.solidArea = new Rectangle(0, 0, 48, 48);
    }

    public Player(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void movePLayer(int x, int y) {
        if (getWorldX() != gamePanel.mouse.mouseX || getWorldY() != gamePanel.mouse.mouseY) {
            if (x < getWorldX()) {
                setWorldX(getWorldX() - 1);
            }
            if (x > getWorldX()) {
                setWorldX(getWorldX() + 1);
            }
            if (y < getWorldY()) {
                setWorldY(getWorldY() - 1);
            }
            if (y > getWorldY()) {
                setWorldY(getWorldY() + 1);
            }
        }
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

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
