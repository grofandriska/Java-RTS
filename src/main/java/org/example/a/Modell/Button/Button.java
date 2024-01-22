package org.example.a.Modell.Button;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button {
    private String name;
    private BufferedImage image;
    private Rectangle solidArea;

    public Button() {
    }

    public Button(String name, BufferedImage image, Rectangle solidArea) {
        this.name = name;
        this.image = image;
        this.solidArea = solidArea;
    }

    public void setUpButtons(){

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
