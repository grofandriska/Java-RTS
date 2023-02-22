package org.example.a.Map;

import org.example.a.Graphic.ImageLoader;
import org.example.a.JPanel.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Map {

    private GamePanel gamePanel;

    public ImageLoader imageLoader = new ImageLoader();

    private final int maxWorldCol;
    private final int maxWorldRow;

    public Tile[] tiles;
    public Integer[][] mapTileNum;
    private int mapSize;

    public Map(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.mapSize = 80;
        this.tiles = new Tile[10];
        this.mapTileNum = new Integer[mapSize][mapSize];
        loadTileArray();
        loadMapFromTxt(mapSize);

        maxWorldCol = mapSize;
        maxWorldRow = mapSize;

    }

    public void loadTileArray() {
        setup(0, "g2", false);
    }

    public void setup(int index, String imagePath, boolean collision) {
        try {
            tiles[index] = new Tile();
            tiles[index].setImage(ImageIO.read(getClass().getResourceAsStream("/Images/Tiles/" + imagePath + ".png")));
            tiles[index].setImage(imageLoader.scaleImage(tiles[index].getImage(), tiles[index].getImageWidth(), tiles[index].getImageHeight()));
            tiles[index].setCollision(collision);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g) {

        int worldCol = 0;
        int worldRow = 0;
        while (worldCol < maxWorldCol && worldRow < maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gamePanel.getTileSize();
            int worldY = worldRow * gamePanel.getTileSize();

            int screenX = worldX - gamePanel.getPlayer().getScreenX();
            int screenY = worldY - gamePanel.getPlayer().getScreenY();

            g.drawImage(tiles[tileNum].getImage(), screenX, screenY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);
            worldCol++;

            if (worldCol == maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    public void loadMapFromTxt(int mapSize) {
        this.mapSize = mapSize;

        try {
            int col = 0;
            int row = 0;

            int cutValue = 0;

            InputStream input = getClass().getResourceAsStream("/maps/Level_01.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            while (col < mapSize && row < mapSize) {
                String line = reader.readLine();
                while (col < mapSize) {
                    String[] numbers = line.split(" ");
                    for (int i = 0; i < numbers.length; i++) {
                        for (int x = 0; x < numbers[i].length(); ) {
                            while (x < 4) {
                                if (numbers[i].startsWith("0")) {
                                    cutValue++;
                                }
                                x++;
                            }
                            numbers[i].substring(cutValue);
                            cutValue = 0;
                        }
                    }
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == mapSize) {
                    col = 0;
                    row++;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
