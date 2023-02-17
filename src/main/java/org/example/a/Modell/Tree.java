package org.example.a.Modell;

import org.example.a.Graphic.ImageLoader;

import java.awt.*;

public class Tree extends Object{


    public Tree(int worldX, int worldY) {
        super.setImage(ImageLoader.setup("/tiles/tree",64,88));
        super.setName("Tree");
        super.setCollision(true);
        super.setWorldX(worldX);
        super.setWorldY(worldY);
    }

    public void draw(Graphics2D g2){
        g2.drawImage(super.getImage(),getWorldX(),getWorldY(),null);

    }
}
