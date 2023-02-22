package org.example.a.Modell.Object;

import org.example.a.Graphic.ImageLoader;
import org.example.a.JPanel.GamePanel;

import java.awt.*;

public class Tree extends Object{

    ImageLoader imageLoader = new ImageLoader();

    GamePanel gamePanel;

    public Tree(int worldX, int worldY,GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        super.setImage(imageLoader.setup("/Images/Tiles/tree",100,100));
        super.setName("Tree");
        super.setCollision(true);
        super.setWorldX(worldX);
        super.setWorldY(worldY);
    }

    public void draw(Graphics2D g2){
        int screenX = getWorldX() - gamePanel.getPlayer().getScreenX();
        int screenY = getWorldY() - gamePanel.getPlayer().getScreenY();
        g2.drawImage(super.getImage(),screenX,screenY,null);

    }
}
