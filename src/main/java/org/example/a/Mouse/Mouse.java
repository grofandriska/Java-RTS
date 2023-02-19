package org.example.a.Mouse;

import org.example.a.Graphic.Graphic;
import org.example.a.Graphic.ImageLoader;
import org.example.a.JPanel.GamePanel;
import org.example.a.Modell.Building.Building;
import org.example.a.Modell.Button;
import org.example.a.Modell.Entity.Unit;
import org.example.a.Modell.ModelLoader;
import org.example.a.Sound.Sound;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Mouse implements MouseListener, MouseMotionListener {

    private final Graphic graphic;
    private GamePanel gamePanel;
    private Integer mouseX, mouseY, dragStartX, dragStartY;
    private Rectangle cursor, dragRectangle;
    private boolean mouseDragged, isBuilding;
    boolean leftClicked;
    private Building building;

    public ModelLoader modelLoader;

    Sound sound;

    public Mouse(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.graphic = new Graphic(gamePanel);
        this.sound = new Sound(gamePanel);
        this.modelLoader = new ModelLoader(gamePanel);
    }

    public void checkIfDragIntersectUnit() {
        if (this.dragRectangle != null) {
            for (Unit unit : gamePanel.getUnitList()) {
                unit.setSelected(unit.getSolidArea().intersects(dragRectangle));
            }
        }
    }

    public void selectCheck(MouseEvent me) {
        this.cursor = new Rectangle(me.getX(), me.getY(), 1, 1);
        checkBuildBar(new Rectangle(me.getX(), me.getY(), 1, 1));
        for (Unit u : gamePanel.getUnitList()) {
            if (u.getSolidArea().intersects(cursor) && me.getButton() == MouseEvent.BUTTON1) {
                u.setSelected(true);
            }
        }
        for (Unit u : gamePanel.getUnitList()) {
            if (!u.getSolidArea().intersects(cursor) && me.getButton() == MouseEvent.BUTTON1) {
                u.setSelected(false);
            }
        }

        this.cursor = null;
    }

    public boolean rightClickedOnScreen(MouseEvent me) {
        return me.getButton() == MouseEvent.BUTTON3
                && me.getY() > 3 * 48
                && me.getY() < gamePanel.getScreenHeight() - 3 * 48;
    }

    public void moveUnit(MouseEvent me) {
        int col = 0;
        int row = 0;
        int unitSize = 50;

        if (rightClickedOnScreen(me)) {
            for (Unit u : gamePanel.getUnitList()) {
                if (u.isSelected()) {
                    u.setGoals(me.getX() + (col * unitSize), me.getY() + (row * unitSize));
                    col++;
                    if (col == 3) { /*col == unitCounter / 2*/
                        col = 0;
                        row++;
                    }
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.dragRectangle == null && leftClicked) {
            setMouseDragged(true);
            this.dragRectangle = new Rectangle(e.getX(), e.getY(), 0, 0);
            this.dragStartX = e.getX();
            this.dragStartY = e.getY();
        }
        setDragSize(e);
        checkIfDragIntersectUnit();
    }

    public void setDragSize(MouseEvent e) {
        if (dragRectangle != null) {
            this.dragRectangle.width = e.getX() - this.dragRectangle.x;
            this.dragRectangle.height = e.getY() - this.dragRectangle.y;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftClicked = true;
            selectCheck(e);
        }
        moveUnit(e);
    }

    public void checkBuildBar(Rectangle rectangle) {
        System.out.println(graphic.getButtons().size());
        for (Button button : graphic.getButtons()) {
            if (rectangle.intersects(button.getSolidArea()) && leftClicked) {
                this.building = modelLoader.setMouseBuilding(Integer.parseInt(button.getName()));
                isBuilding = true;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (isMouseDragged()) {
            setMouseDragged(false);
            this.dragRectangle = null;
        }
        if (this.leftClicked) {
            this.leftClicked = false;
        }
        if (this.isBuilding && e.getButton() == MouseEvent.BUTTON3 && building.checkIfHasValue()) {
            gamePanel.getPlayer().setWood(gamePanel.getPlayer().getWood() - building.getWoodValue());
            this.building.setWorldX(e.getX());
            this.building.setWorldY(e.getY());
            ArrayList<Building> newSize = gamePanel.getPlayer().getBuildings();
            newSize.add(this.building);
            gamePanel.getPlayer().setBuildings(newSize);
            this.building = null;
            this.isBuilding = false;
        }
        if (this.isBuilding && e.getButton() == MouseEvent.BUTTON3 && !building.checkIfHasValue()) {
            isBuilding = false;
            this.building = null;
        }
    }


    public void mouseExited(MouseEvent e) {
    }

    public Building getBuilding() {
        return building;
    }


    public void setBuilding(Building building) {
        this.building = building;
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {

    }

    public Graphic getGraphic() {
        return graphic;
    }

    public boolean isBuilding() {
        return isBuilding;
    }

    public void setBuilding(boolean building) {
        isBuilding = building;
    }


    public boolean isLeftClicked() {
        return leftClicked;
    }

    public void setLeftClicked(boolean leftClicked) {
        this.leftClicked = leftClicked;
    }

    public Integer getMouseX() {
        return mouseX;
    }

    public void setMouseX(Integer mouseX) {
        this.mouseX = mouseX;
    }

    public Integer getMouseY() {
        return mouseY;
    }

    public void setMouseY(Integer mouseY) {
        this.mouseY = mouseY;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public Integer getDragStartX() {
        return dragStartX;
    }

    public void setDragStartX(Integer dragStartX) {
        this.dragStartX = dragStartX;
    }

    public Integer getDragStartY() {
        return dragStartY;
    }

    public void setDragStartY(Integer dragStartY) {
        this.dragStartY = dragStartY;
    }

    public Rectangle getDragRectangle() {
        return dragRectangle;
    }

    public void setDragRectangle(Rectangle dragRectangle) {
        this.dragRectangle = dragRectangle;
    }

    public Rectangle getCursor() {
        return cursor;
    }

    public void setCursor(Rectangle cursor) {
        this.cursor = cursor;
    }

    public boolean isMouseDragged() {
        return mouseDragged;
    }

    public void setMouseDragged(boolean mouseDragged) {
        this.mouseDragged = mouseDragged;
    }
}
