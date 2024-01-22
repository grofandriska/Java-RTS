package org.example.a.Modell.Building;

import org.example.a.JPanel.GamePanel;
import org.example.a.Mouse.Mouse;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Building {

    private boolean selected = false;
    private int size, hitPoint, woodValue, stoneValue, goldValue, foodValue, worldX, worldY;
    private String name;
    public GamePanel gamePanel;
    private Rectangle solidArea;
    private BufferedImage image;


    public Building() {
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Building(int size, int hitPoint, int woodValue,
                    int stoneValue, int goldValue, int foodValue,
                    String name, BufferedImage image, Rectangle solidArea, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.size = size;
        this.hitPoint = hitPoint;
        this.woodValue = woodValue;
        this.stoneValue = stoneValue;
        this.goldValue = goldValue;
        this.foodValue = foodValue;
        this.name = name;
        this.image = image;
        this.solidArea = solidArea;
    }

    public void produceUnit() {
    }

    public void levelUp() {
    }


    public void draw(Graphics2D g2) {
        int screenX = worldX - gamePanel.getPlayer().getScreenX();
        int screenY = worldY - gamePanel.getPlayer().getScreenY();
        g2.drawImage(image, screenX, screenY, null);

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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public int getWoodValue() {
        return woodValue;
    }

    public void setWoodValue(int woodValue) {
        this.woodValue = woodValue;
    }

    public int getStoneValue() {
        return stoneValue;
    }

    public void setStoneValue(int stoneValue) {
        this.stoneValue = stoneValue;
    }

    public int getGoldValue() {
        return goldValue;
    }

    public void setGoldValue(int goldValue) {
        this.goldValue = goldValue;
    }

    public int getFoodValue() {
        return foodValue;
    }

    public void setFoodValue(int foodValue) {
        this.foodValue = foodValue;
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

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }


}
