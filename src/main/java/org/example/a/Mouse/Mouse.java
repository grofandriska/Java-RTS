package org.example.a.Mouse;

import org.example.a.Graphic.Graphic;
import org.example.a.Graphic.ImageLoader;
import org.example.a.JPanel.GamePanel;
import org.example.a.Modell.Building.Building;
import org.example.a.Modell.Button;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Mouse implements MouseListener, MouseMotionListener {

    private final Graphic graphic;
    private GamePanel gamePanel;
    private Integer mouseX, mouseY;
    private Integer dragX, dragY;
    private Rectangle dragRectangle;
    private Rectangle cursor;
    private boolean mouseDragged;
    private boolean isBuilding;
    boolean leftClicked;

    private Building building;

    public Mouse(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.graphic = new Graphic(gamePanel);
    }

    public void checkDrag() {
        if (this.dragRectangle != null) {
            if (this.dragRectangle.intersects(this.gamePanel.getUnit().getSolidArea())) {
                this.gamePanel.getUnit().setSelected(true);
            }
            if (this.dragRectangle.intersects(this.gamePanel.getUnit_2().getSolidArea())) {
                this.gamePanel.getUnit_2().setSelected(true);
            }
            if (!this.gamePanel.getUnit().getSolidArea().intersects(getDragRectangle())) {
                this.gamePanel.getUnit().setSelected(false);
            }
            if (!this.gamePanel.getUnit_2().getSolidArea().intersects(getDragRectangle())) {
                this.gamePanel.getUnit_2().setSelected(false);
            }
        }
    }

    public void selectCheck(MouseEvent me) {
        this.cursor = new Rectangle(me.getX(), me.getY(), 10, 10);
        checkBuildBar(cursor);
        if (cursor.intersects(this.gamePanel.getUnit().getSolidArea()) && me.getButton() == MouseEvent.BUTTON1) {
            this.gamePanel.getSound().playSoundEffect(3);
            this.gamePanel.getUnit().setSelected(true);
        }
        if (!cursor.intersects(this.gamePanel.getUnit().getSolidArea()) && me.getButton() == MouseEvent.BUTTON1 && me.getY() > 3 * 48 && me.getY() < gamePanel.getScreenHeight() - 3 * 48) {
            gamePanel.getUnit().setSelected(false);
        }
        if (cursor.intersects(this.gamePanel.getUnit_2().getSolidArea()) && me.getButton() == MouseEvent.BUTTON1) {
            this.gamePanel.getSound().playSoundEffect(3);
            this.gamePanel.getUnit_2().setSelected(true);
        }
        if (!cursor.intersects(this.gamePanel.getUnit_2().getSolidArea()) && me.getButton() == MouseEvent.BUTTON1) {
            this.gamePanel.getUnit_2().setSelected(false);
        }
        this.cursor = null;
    }

    public void moveUnit(MouseEvent me) {
        if (me.getButton() == MouseEvent.BUTTON3 && me.getY() > 3 * 48 && me.getY() < gamePanel.getScreenHeight() - 3 * 48) {
            if (this.gamePanel.getUnit().isSelected()) {
                this.gamePanel.getUnit().setGoalX(me.getX() - 22);
                this.gamePanel.getUnit().setGoalY(me.getY() - 48);
            }
            if (this.gamePanel.getUnit_2().isSelected()) {
                this.gamePanel.getUnit_2().setGoalX(me.getX() - 22);
                this.gamePanel.getUnit_2().setGoalY(me.getY() - 68);

            }
            if (this.gamePanel.getUnit().isSelected() || this.gamePanel.getUnit_2().isSelected()) {
                this.graphic.showClick(me.getX(), me.getY());
                this.gamePanel.getSound().playSoundEffect(1);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.dragRectangle == null && leftClicked) {
            setMouseDragged(true);
            this.dragRectangle = new Rectangle(e.getX(), e.getY(), 0, 0);
            this.dragX = e.getX();
            this.dragY = e.getY();
        }
        setDragSize(e);
        checkDrag();
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
        }
        if (e.getButton() == MouseEvent.BUTTON3 && isBuilding && gamePanel.getPlayer().getWood() > building.getWoodValue()) {
            this.building.setWorldX(e.getX());
            this.building.setWorldY(e.getY());
            this.gamePanel.buildings.add(this.building);
            gamePanel.getPlayer().setWood(gamePanel.getPlayer().getWood() - building.getWoodValue());
            this.isBuilding = false;
            this.building = null;
        }
        selectCheck(e);
        moveUnit(e);
    }

    public void checkBuildBar(Rectangle rectangle) {
        for (Building building : Graphic.getButtonBuildings()) {
            if (rectangle.intersects(building.getSolidArea())) {
                this.building = building;
                isBuilding = true;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (isMouseDragged()) {
            setMouseDragged(false);
            this.dragRectangle = null;
        }
        if (leftClicked) {
            leftClicked = false;
        }
        if (isBuilding && e.getButton() == MouseEvent.BUTTON3) {
            isBuilding = false;
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

    public Integer getDragX() {
        return dragX;
    }

    public void setDragX(Integer dragX) {
        this.dragX = dragX;
    }

    public Integer getDragY() {
        return dragY;
    }

    public void setDragY(Integer dragY) {
        this.dragY = dragY;
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
