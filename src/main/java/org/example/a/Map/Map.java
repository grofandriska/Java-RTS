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

    public Tile[] tiles;
    public Integer[][] mapTileNum;
    private int mapSize;

    public Map(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.mapSize = 40;
        this.tiles = new Tile[10];
        this.mapTileNum = new Integer[mapSize][mapSize];
        loadTileArray();
        loadMapFromTxt(mapSize);

    }

    public void loadTileArray() {
        setup(0, "g2", false);
    }

    public void setup(int index, String imagePath, boolean collision) {
        try {
            tiles[index] = new Tile();
            tiles[index].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath + ".png")));
            tiles[index].setImage(ImageLoader.scaleImage(tiles[index].getImage(), tiles[index].getImageWidth(), tiles[index].getImageHeight()));
            tiles[index].setCollision(collision);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g) {
        int worldCol = 0;
        int worldRow = 0;

        int screenX = 0;
        int screenY = 0;

        int counter = 0;

        /*if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX
                    && worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX
                    && worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY
                    && worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {


            }*/
        while (worldCol < gamePanel.getMaxScreenCol() && worldRow < gamePanel.getMaxScreenRow()+1) {
            int tileNum = mapTileNum[worldCol][worldRow];

            g.drawImage(tiles[tileNum].getImage(), screenX, screenY,tiles[tileNum].getImageWidth(), tiles[tileNum].getImageHeight(), null);
            g.setColor(new Color(0, 0, 0));
            g.drawString(String.valueOf(counter), screenX + 20, screenY + 20);
            g.setColor(new Color(255, 0, 0));
            g.drawString(String.valueOf(counter), screenX + 19, screenY + 20);

            counter++;
            worldCol++;
            screenX += 48;

            if (worldCol == mapSize) {
                worldCol = 0;
                screenX = 0;
                screenY += 48;
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
