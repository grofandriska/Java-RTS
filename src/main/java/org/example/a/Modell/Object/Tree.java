package org.example.a.Modell.Object;

import org.example.a.Graphic.ImageLoader;
import org.example.a.JPanel.GamePanel;

import java.awt.*;

public class Tree extends Object {

    ImageLoader imageLoader = new ImageLoader();

    Rectangle solidArea;

    GamePanel gamePanel;

    public Tree(int worldX, int worldY, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        super.setImage(imageLoader.setup("/Images/Tiles/tree3", 100, 100));
        super.setName("Tree");
        super.setCollision(true);
        super.setWorldX(worldX);
        super.setWorldY(worldY);
        this.solidArea = new Rectangle(getWorldX() + getImage().getWidth()/4, getWorldY(), getImage().getWidth()/2,getImage().getHeight());

    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void draw(Graphics2D g2) {
        int screenX = getWorldX() - gamePanel.getPlayer().getScreenX();
        int screenY = getWorldY() - gamePanel.getPlayer().getScreenY();
        g2.drawImage(super.getImage(), screenX, screenY, null);
    }
}
