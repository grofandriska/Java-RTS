package org.example.a.Modell.Object;

import org.example.a.Graphic.ImageLoader;

import java.awt.*;

public class Tree extends Object{

    ImageLoader imageLoader = new ImageLoader();

    public Tree(int worldX, int worldY) {
        super.setImage(imageLoader.setup("/Images/Tiles/tree",100,100));
        super.setName("Tree");
        super.setCollision(true);
        super.setWorldX(worldX);
        super.setWorldY(worldY);
    }

    public void draw(Graphics2D g2){
        g2.drawImage(super.getImage(),getWorldX(),getWorldY(),null);

    }
}
