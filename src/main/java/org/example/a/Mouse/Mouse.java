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

    boolean leftClicked = true;

    public Mouse(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.graphic = new Graphic(gamePanel);
    }

    public void checkDrag() {
        if (this.dragRectangle != null) {
            if (this.dragRectangle.intersects(this.gamePanel.getPlayer().getSolidArea())) {
                this.gamePanel.getPlayer().setSelected(true);
            }
            if (this.dragRectangle.intersects(this.gamePanel.getPlayer2().getSolidArea())) {
                this.gamePanel.getPlayer2().setSelected(true);
            }
            if (!this.gamePanel.getPlayer().getSolidArea().intersects(getDragRectangle())) {
                this.gamePanel.getPlayer().setSelected(false);
            }
            if (!this.gamePanel.getPlayer2().getSolidArea().intersects(getDragRectangle())) {
                this.gamePanel.getPlayer2().setSelected(false);
            }
        }
    }

    public void selectAndMove(MouseEvent me) {
        this.cursor = new Rectangle(me.getX(), me.getY(), 10, 10);
        if (cursor.intersects(this.gamePanel.getPlayer().getSolidArea()) && me.getButton() == MouseEvent.BUTTON1) {
            this.gamePanel.playSoundEffect(3);
            this.gamePanel.getPlayer().setSelected(true);
        }  if (!cursor.intersects(this.gamePanel.getPlayer().getSolidArea()) && me.getButton() == MouseEvent.BUTTON1) {
            gamePanel.getPlayer().setSelected(false);
        }  if (cursor.intersects(this.gamePanel.getPlayer2().getSolidArea()) && me.getButton() == MouseEvent.BUTTON1) {
            this.gamePanel.playSoundEffect(3);
            this.gamePanel.getPlayer2().setSelected(true);
        }  if (!cursor.intersects(this.gamePanel.getPlayer2().getSolidArea()) && me.getButton() == MouseEvent.BUTTON1) {
            this.gamePanel.getPlayer2().setSelected(false);
        }
        this.cursor = null;

        // move Entity to location also into Array inside JPanel
        if (me.getButton() == MouseEvent.BUTTON3) {
            if (this.gamePanel.getPlayer().isSelected()) {
                this.gamePanel.getPlayer().setGoalX(me.getX() - 22);
                this.gamePanel.getPlayer().setGoalY(me.getY() - 48);
            }
            if (this.gamePanel.getPlayer2().isSelected()) {
                this.gamePanel.getPlayer2().setGoalX(me.getX() - 22);
                this.gamePanel.getPlayer2().setGoalY(me.getY() - 68);

            }
            if (this.gamePanel.getPlayer().isSelected() || this.gamePanel.getPlayer2().isSelected()) {
                this.graphic.showClick(me.getX(), me.getY());
                this.gamePanel.playSoundEffect(1);
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
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftClicked = true;
        }
        selectAndMove(e);
    }

    public void mouseReleased(MouseEvent e) {
        if (isMouseDragged()) {
            setMouseDragged(false);
            this.dragRectangle = null;
        }
        if (leftClicked) {
            leftClicked = false;
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
