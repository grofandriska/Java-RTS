package org.example.a;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {

    GamePanel gamePanel ;
    private int worldX = 10;
    private int worldY = 10;
    private int speed = 30;
    private BufferedImage image;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);

    public Player(GamePanel gamePanel) {
        this.gamePanel= gamePanel;
    }
    public void movePLayer(int x, int y) {
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
