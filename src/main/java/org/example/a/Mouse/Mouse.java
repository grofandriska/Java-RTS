package org.example.a.Mouse;

import org.example.a.Graphic.Graphic;
import org.example.a.JPanel.GamePanel;
import org.example.a.Modell.Building.Building;
import org.example.a.Modell.Button.Button;
import org.example.a.Modell.Entity.Unit;
import org.example.a.Modell.ModelLoader;
import org.example.a.Sound.Sound;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Mouse implements MouseListener, MouseMotionListener {

    private boolean leftClicked;
    private boolean mouseDragged;
    private Integer mouseX, mouseY, dragStartX, dragStartY;
    private Integer screenAdjustValueX = 0, screenAdjustValueY = 0;
    private Rectangle cursor, dragRectangle;
    private GamePanel gamePanel;
    private Building building;
    private ModelLoader modelLoader;
    private Sound sound;
    private final Graphic graphic;



    public Mouse(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.graphic = new Graphic(gamePanel);
        this.sound = new Sound(gamePanel);
        this.modelLoader = new ModelLoader(gamePanel);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (gamePanel.getPlayer().getNewBuilding() != null) {
                gamePanel.getPlayer().setBuilding(false);
                gamePanel.getPlayer().setNewBuilding(null);
            }
            leftClicked = true;
            checkClick(e);
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            gamePanel.getPlayer().moveUnit(e, screenAdjustValueX, screenAdjustValueY);
            graphic.showClick(e.getX(), getMouseY());
        }
    }

    ///!!!!!!!!!!!!
    public void checkClick(MouseEvent me) {
        locateCursor(me);
        selectDeSelectUnit(me);
        selectDeSelectBuilding(me);
        cursor = null;

        locateCursorOnScreen(me);
        selectBuildingFromUI(this.cursor, me);

        this.cursor = null;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();

        if (gamePanel.getPlayer().isBuilding()) {
            gamePanel.getPlayer().getNewBuilding().setWorldX(mouseX - gamePanel.getPlayer().getNewBuilding().getImage().getWidth() / 2);
            gamePanel.getPlayer().getNewBuilding().setWorldY(mouseY - gamePanel.getPlayer().getNewBuilding().getImage().getHeight() / 2);
        }
        moveCamera();

    }


    public void moveCamera() {
        if (mouseY > gamePanel.getScreenHeight() - 100) {
            gamePanel.getPlayer().setScreenY(gamePanel.getPlayer().getScreenY() + 5);
            screenAdjustValueY += 5;
        }
        if (mouseY < 100) {
            gamePanel.getPlayer().setScreenY(gamePanel.getPlayer().getScreenY() - 5);
            screenAdjustValueY -= 5;
        }
        if (mouseX > gamePanel.getScreenWidth() - 100) {
            gamePanel.getPlayer().setScreenX(gamePanel.getPlayer().getScreenX() + 5);
            screenAdjustValueX += 5;
        }
        if (mouseX < 100) {
            gamePanel.getPlayer().setScreenX(gamePanel.getPlayer().getScreenX() - 5);
            screenAdjustValueX -= 5;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        initDrag(e);
        setDragSize(e);
        checkIfDragIntersectUnit();

    }

    public void setDragSize(MouseEvent e) {
        if (dragRectangle != null) {
            this.dragRectangle.width = e.getX() + screenAdjustValueX - this.dragRectangle.x;
            this.dragRectangle.height = e.getY() + screenAdjustValueY - this.dragRectangle.y;
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (isMouseDragged()) {
            setMouseDragged(false);
            this.dragRectangle = null;
        }
        if (gamePanel.getPlayer().isBuilding() && e.getButton() == MouseEvent.BUTTON3) {
            gamePanel.getPlayer().checkBuild(e, screenAdjustValueX, screenAdjustValueY);
        }

    }

    public void selectBuildingFromUI(Rectangle cursor, MouseEvent me) {
        setMouseXY(me);
        for (Button button : this.graphic.getButtons()) {
            if (cursor.intersects(button.getSolidArea()) && me.getButton() == MouseEvent.BUTTON1) {
                this.gamePanel.getPlayer().setBuilding(true);
                this.gamePanel.getPlayer().setNewBuilding(this.modelLoader.setMouseBuilding(Integer.parseInt(button.getName())));
                System.out.println("building selected");
            }
        }
    }

    public void initDrag(MouseEvent me) {
        if (this.dragRectangle == null && leftClicked) {
            setMouseDragged(true);
            this.dragRectangle = new Rectangle(me.getX() + screenAdjustValueX, me.getY() + screenAdjustValueY, 0, 0);
        }
    }

    public void setMouseXY(MouseEvent me) {
        this.mouseX = me.getX();
        this.mouseY = me.getY();
    }

    public void nullMouseXY() {
        this.mouseX = null;
        this.mouseY = null;
    }

    public void locateCursorOnScreen(MouseEvent me) {
        this.cursor = new Rectangle(me.getX(), me.getY(), 1, 1);
    }

    public void locateCursor(MouseEvent me) {
        this.cursor = new Rectangle(me.getX() + screenAdjustValueX, me.getY() + screenAdjustValueY, 1, 1);
        System.out.println("" + me.getX() + screenAdjustValueX + " + " + me.getY() + screenAdjustValueY);
    }

    public void selectDeSelectUnit(MouseEvent me) {
        for (Unit u : gamePanel.getUnitList()) {
            if (u.getSolidArea().intersects(cursor) && me.getButton() == MouseEvent.BUTTON1) {
                u.setSelected(true);
            }
            if (!u.getSolidArea().intersects(cursor) && me.getButton() == MouseEvent.BUTTON1) {
                u.setSelected(false);
            }
        }
    }  public void selectDeSelectBuilding(MouseEvent me) {
        for (Building b: gamePanel.getPlayer().getBuildings()){
            if (b.getSolidArea().intersects(cursor) && me.getButton() == MouseEvent.BUTTON1) {
                b.setSelected(true);
            }
            if (!(b.getSolidArea().intersects(cursor) && me.getButton() == MouseEvent.BUTTON1)) {
                b.setSelected(false);
            }
        }
    }

    public void checkIfDragIntersectUnit() {
        if (this.dragRectangle != null) {
            for (Unit unit : gamePanel.getUnitList()) {
                unit.setSelected(unit.getSolidArea().intersects(dragRectangle));
            }
        }
    }

    public boolean clickedOnScreen(MouseEvent me) {
        return me.getButton() == MouseEvent.BUTTON3 && me.getY() > 3 * 48 && me.getY() < gamePanel.getScreenHeight() - 3 * 48;
    }

    public void mouseExited(MouseEvent e) {
    }

    public Integer getScreenAdjustValueX() {
        return screenAdjustValueX;
    }

    public void setScreenAdjustValueX(Integer screenAdjustValueX) {
        this.screenAdjustValueX = screenAdjustValueX;
    }

    public Integer getScreenAdjustValueY() {
        return screenAdjustValueY;
    }

    public void setScreenAdjustValueY(Integer screenAdjustValueY) {
        this.screenAdjustValueY = screenAdjustValueY;
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


    public boolean isLeftClicked() {
        return leftClicked;
    }

    public ModelLoader getModelLoader() {
        return modelLoader;
    }

    public void setModelLoader(ModelLoader modelLoader) {
        this.modelLoader = modelLoader;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
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
