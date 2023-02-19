package org.example.a.Modell;

import org.example.a.JPanel.GamePanel;
import org.example.a.Modell.Building.Building;
import org.example.a.Modell.Entity.Unit;

import java.util.ArrayList;

public class Player {

    private GamePanel gamePanel;

    private Integer wood, food, gold, stone;

    private ArrayList<Unit> unitList;

    private ArrayList<Building> buildings;

    {
        this.wood = 2000;
        this.food = 200;
        this.gold = 200;
        this.stone = 200;
        this.unitList = new ArrayList<>();
        this.buildings = new ArrayList<>();
    }
    public Player() {
    }


    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public Integer getWood() {
        return wood;
    }

    public void setWood(Integer wood) {
        this.wood = wood;
    }

    public Integer getFood() {
        return food;
    }

    public void setFood(Integer food) {
        this.food = food;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getStone() {
        return stone;
    }

    public void setStone(Integer stone) {
        this.stone = stone;
    }

    public ArrayList<Unit> getUnitList() {
        return unitList;
    }

    public void setUnitList(ArrayList<Unit> unitList) {
        this.unitList = unitList;
    }
}
