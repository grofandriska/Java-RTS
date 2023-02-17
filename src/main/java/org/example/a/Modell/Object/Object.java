package org.example.a.Modell.Object;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Object {

    private String name;

    private BufferedImage image;

    private boolean collision;

    private int worldX, worldY;

    public Object() {
    }

    public void draw(Graphics2D g2){
        System.out.println("implement");

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
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
}
