package org.example.a;

import java.awt.*;
import java.awt.event.*;

class Mouse implements MouseListener {
    private Integer mouseX,mouseY;
    private GamePanel gamePanel;

    Mouse(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void selectAndMove(MouseEvent me) {
        Rectangle cursor = new Rectangle(me.getX(), me.getY(), 10, 10);
        if (cursor.intersects(gamePanel.getPlayer().getSolidArea()) && me.getButton() == MouseEvent.BUTTON1) {
            gamePanel.playSoundEffect(3);
            gamePanel.getPlayer().setSelected(true);
            cursor = null;
        } else if (!cursor.intersects(gamePanel.getPlayer().getSolidArea()) && me.getButton() == MouseEvent.BUTTON1) {
            gamePanel.getPlayer().setSelected(false);
            cursor = null;
        } else if (me.getButton() == MouseEvent.BUTTON3 && gamePanel.getPlayer().isSelected()) {
            mouseX = me.getX();
            mouseY = me.getY();
            gamePanel.playSoundEffect(1);
            gamePanel.getPlayer().setGoalX(me.getX() - 22);
            gamePanel.getPlayer().setGoalY(me.getY() - 22);

        }
    }


    public void mousePressed(MouseEvent e) {
        selectAndMove(e);
    }

    public void mouseReleased(MouseEvent e) {

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
}
