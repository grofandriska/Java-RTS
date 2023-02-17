package org.example.a.Graphic;

import org.example.a.Modell.Building.Building;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ImageLoader {

    public ImageLoader() {

    }

    public static BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }

    public static BufferedImage setup(String imagePath, int width, int height) {

        BufferedImage image;
        try {
            image = ImageIO.read(Objects.requireNonNull(ImageLoader.class.getResourceAsStream(imagePath + ".png")));
            image = scaleImage(image, width, height);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

}