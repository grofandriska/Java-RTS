package org.example.a.Map;

import org.example.a.JPanel.GamePanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Map {

    private GamePanel gamePanel;

    public Tile[] tiles;
    public Integer[][] mapTileNum;
    private int mapSize;

    public Map(GamePanel gamePanel, Tile[] tiles, Integer[][] mapTileNum, int mapSize) {
        this.gamePanel = gamePanel;
        this.tiles = tiles;
        this.mapTileNum = mapTileNum;
        this.mapSize = mapSize;
    }

    public void loadMapFromTxt(int mapSize) {
        this.mapSize = mapSize;

        try {
            int col = 0;
            int row = 0;

            int cutValue = 0;

            InputStream input = getClass().getResourceAsStream("/maps/Level_01.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            //TODO: set the screenRow and ScreenCol to screenWidth/Height divided by tileSize
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
