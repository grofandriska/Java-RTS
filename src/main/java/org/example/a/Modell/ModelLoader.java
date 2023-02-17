package org.example.a.Modell;

import org.example.a.Graphic.ImageLoader;
import org.example.a.JPanel.GamePanel;
import org.example.a.Modell.Building.Building;
import org.example.a.Modell.Object.Object;
import org.example.a.Modell.Object.Tree;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

public class ModelLoader {

    private GamePanel gamePanel;

    private static ArrayList<Building> buildings;

    public ModelLoader(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        buildings = new ArrayList<>();
        setBuildings();
    }

    public void setTrees(int size) {

        int counter = 0;

        while (counter != size) {

            int goalX = ThreadLocalRandom.current().nextInt(0, gamePanel.getScreenWidth());
            int goalY = ThreadLocalRandom.current().nextInt(3 * 48, gamePanel.getScreenHeight() - 3 * 48);

            this.gamePanel.getObjects().add(new Tree(goalX, goalY));
            counter++;
        }
        gamePanel.getObjects().sort(Comparator.comparing(Object::getWorldY));

    }

    public void setUnits() {

    }

    public void setBuildings() {

        Building archery = new Building(10, 500, 1150, 0, 0, 0, "Archery",
                ImageLoader.setup("/Images/Building/archery_range", 305, 222),
                new Rectangle(0, 0, 0, 0));

        Building barracks = new Building(10, 500, 150, 0, 0, 0, "Barracks",
                ImageLoader.setup("/Images/Building/barracks", 315, 250),
                new Rectangle(0, 0, 0, 0));


        Building blacksmith = new Building(10, 500, 150, 0, 0, 0, "Blacksmith",
                ImageLoader.setup("/Images/Building/blacksmith", 305, 259),
                new Rectangle(0, 0, 0, 0));


        Building castle = new Building(10, 500, 150, 0, 0, 0, "Castle",
                ImageLoader.setup("/Images/Building/castle", 500, 422),
                new Rectangle(0, 0, 0, 0));


        Building house1 = new Building(10, 500, 150, 0, 0, 0, "House 1",
                ImageLoader.setup("/Images/Building/house1", 178, 141),
                new Rectangle(0, 0, 0, 0));


        Building house2 = new Building(10, 500, 150, 0, 0, 0, "House 2",
                ImageLoader.setup("/Images/Building/house1b", 175, 140),
                new Rectangle(0, 0, 0, 0));


        Building stable = new Building(10, 500, 150, 0, 0, 0, "Stable",
                ImageLoader.setup("/Images/Building/stable", 318, 252),
                new Rectangle(0, 0, 0, 0));


        buildings.add(archery);
        buildings.add(barracks);
        buildings.add(blacksmith);
        buildings.add(castle);
        buildings.add(house1);
        buildings.add(house2);
        buildings.add(stable);

    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public static ArrayList<Building> getBuildings() {
        return buildings;
    }

    public static void setBuildings(ArrayList<Building> buildings) {
        ModelLoader.buildings = buildings;
    }
}