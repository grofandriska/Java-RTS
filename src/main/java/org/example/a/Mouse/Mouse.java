package org.example.a.Mouse;

import org.example.a.Graphic.Graphic;
import org.example.a.JPanel.GamePanel;

import java.awt.*;
import java.awt.event.*;

public class Mouse implements MouseListener, MouseMotionListener {

    private final Graphic graphic;
    private GamePanel gamePanel;
    private Integer mouseX, mouseY;
    private Integer dragX, dragY;
    private Rectangle dragRectangle;
    private Rectangle cursor;
    private boolean mouseDragged;

    public Mouse(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.graphic = new Graphic(gamePanel);
    }

    public void checkDrag() {
        if (dragRectangle != null) {
            if (dragRectangle.intersects(gamePanel.getPlayer().getSolidArea())) {
                gamePanel.getPlayer().setSelected(true);
            }
            if (dragRectangle.intersects(gamePanel.getPlayer2().getSolidArea())) {
                gamePanel.getPlayer2().setSelected(true);
            }
            if (!gamePanel.getPlayer().getSolidArea().intersects(getDragRectangle())) {
                gamePanel.getPlayer().setSelected(false);
            }
            if (!gamePanel.getPlayer2().getSolidArea().intersects(getDragRectangle())) {
                gamePanel.getPlayer2().setSelected(false);
            }
        }
    }

    public void selectAndMove(MouseEvent me) {
        cursor = new Rectangle(me.getX(), me.getY(), 10, 10);
        if (cursor.intersects(gamePanel.getPlayer().getSolidArea()) && me.getButton() == MouseEvent.BUTTON1) {
            gamePanel.playSoundEffect(3);
            gamePanel.getPlayer().setSelected(true);
        }
        if (!cursor.intersects(gamePanel.getPlayer().getSolidArea()) && me.getButton() == MouseEvent.BUTTON1) {
            gamePanel.getPlayer().setSelected(false);
        }
        // move Entity to location also into Array
        if (me.getButton() == MouseEvent.BUTTON3) {
            if (gamePanel.getPlayer().isSelected()) {
                gamePanel.getPlayer().setGoalX(me.getX() - 22);
                gamePanel.getPlayer().setGoalY(me.getY() - 48);
                if (gamePanel.getPlayer2().isSelected()) {
                    gamePanel.getPlayer2().setGoalX(me.getX() - 22);
                    gamePanel.getPlayer2().setGoalY(me.getY() - 68);
                    gamePanel.playSoundEffect(1);
                }
                graphic.showClick(me.getX(),me.getY());
            }
        }
        cursor = null;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragRectangle == null) {
            dragX = e.getX();
            dragY = e.getY();
            dragRectangle = new Rectangle(e.getX(), e.getY(), 0, 0);
        }
        setMouseDragged(true);
        setDragSize(e);
        checkDrag();
    }

    public void setDragSize(MouseEvent e) {
        dragRectangle.width = e.getX() - dragRectangle.x;
        dragRectangle.height = e.getY() - dragRectangle.y;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3 && gamePanel.getPlayer().isSelected()) {
            gamePanel.getGraphic().showClick(e.getX(),e.getY());
        }
        selectAndMove(e);
    }

    public void mouseReleased(MouseEvent e) {
        if (isMouseDragged()) {
            setMouseDragged(false);
            dragRectangle = null;
        }
    }


    public void mouseExited(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {

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
