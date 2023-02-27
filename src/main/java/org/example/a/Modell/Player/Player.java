package org.example.a.Modell.Player;

import org.example.a.JPanel.GamePanel;
import org.example.a.Modell.Building.Building;
import org.example.a.Modell.Entity.Unit;
import org.example.a.Modell.Object.Tree;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;

public class Player {

    private GamePanel gamePanel;

    private Integer wood, food, gold, stone, screenX, screenY;

    private ArrayList<Unit> unitList;
    private ArrayList<Building> buildings;

    private Building newBuilding;

    private boolean isBuilding = false, followUnit = false;

    {
        this.wood = 2000;
        this.food = 200;
        this.gold = 200;
        this.stone = 200;
        this.unitList = new ArrayList<>();
        this.buildings = new ArrayList<>();
    }

    public Player(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        screenX = 0;
        screenY = 0;
    }

    public void moveUnit(MouseEvent me, int adjX, int adjY) {

        int col = 0;
        int row = 0;

        int space = 50;
        if (gamePanel.getMouse().clickedOnScreen(me)) {
            for (Unit u : gamePanel.getUnitList()) {
                if (u.isSelected()) {
                    u.setGoals(me.getX() + (col * space) + adjX, me.getY() + (row * space) + adjY);
                    col++;
                    if (col == 3) {
                        col = 0;
                        row++;
                    }
                }
            }
        }
    }

    public void drawNewBuilding(Graphics2D g2, int x, int y) {
        if (isBuilding) {
            g2.drawImage(this.newBuilding.getImage(), x, y, null);
        }
    }

    public Integer getScreenX() {
        return screenX;
    }

    public void setScreenX(Integer screenX) {
        this.screenX = screenX;
    }

    public Integer getScreenY() {
        return screenY;
    }

    public void setScreenY(Integer screenY) {
        this.screenY = screenY;
    }

    public boolean checkBuildValue() {

        this.wood -= this.newBuilding.getWoodValue();
        this.food -= this.newBuilding.getFoodValue();
        this.stone -= this.newBuilding.getStoneValue();
        this.gold -= this.newBuilding.getGoldValue();

        if (this.wood < 0 || this.food < 0 || this.stone < 0 || this.gold < 0) {
            this.wood += this.newBuilding.getWoodValue();
            this.food += this.newBuilding.getFoodValue();
            this.stone += this.newBuilding.getStoneValue();
            this.gold += this.newBuilding.getGoldValue();
            isBuilding = false;
            return false;
        }
        isBuilding = true;
        return true;
    }

    private boolean checkBorder(int x, int y) {
        return x > 0 && y > 0 && x < gamePanel.getMap().getMapSize() * gamePanel.getTileSize() && y < gamePanel.getMap().getMapSize() * gamePanel.getTileSize();
    }

    public void checkBuild(MouseEvent me, int adjX, int adjY) {
        if (checkBuildValue() && checkBorder((me.getX() + adjX) - getNewBuilding().getImage().getWidth() / 2, me.getY() + adjY - getNewBuilding().getImage().getHeight() / 2)) {
            this.newBuilding.setWorldX((me.getX() + adjX) - getNewBuilding().getImage().getWidth() / 2);
            this.newBuilding.setWorldY((me.getY() + adjY) - getNewBuilding().getImage().getHeight() / 2);
            this.newBuilding.setSolidArea(new Rectangle(this.newBuilding.getWorldX(), this.newBuilding.getWorldY(), this.newBuilding.getImage().getWidth(), this.newBuilding.getImage().getHeight()));
            gamePanel.getObjects().removeIf(t -> t.getSolidArea().intersects(newBuilding.getSolidArea()));
            this.buildings.add(newBuilding);
            buildings.sort(Comparator.comparing(Building::getWorldY));
            this.isBuilding = false;
            this.newBuilding = null;
        }
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

    public Building getNewBuilding() {
        return newBuilding;
    }

    public void setNewBuilding(Building newBuilding) {
        this.newBuilding = newBuilding;
    }

    public boolean isBuilding() {
        return isBuilding;
    }

    public void setBuilding(boolean building) {
        isBuilding = building;
    }

    public boolean isFollowUnit() {
        return followUnit;
    }

    public void setFollowUnit(boolean followUnit) {
        this.followUnit = followUnit;
    }
}
