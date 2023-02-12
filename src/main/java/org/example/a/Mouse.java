package org.example.a;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Mouse implements MouseListener {


    int mouseX ;
    int mouseY;

    boolean isPressed = false;

    GamePanel gamePanel;

    Mouse(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        mouseX=gamePanel.player.getWorldX();
        mouseY=gamePanel.player.getWorldY();

    }

    public void mousePressed(MouseEvent e) {
        isPressed = true;
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("mouse released at point:"
                + e.getX() + " " + e.getY());
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("mouse exited through point:"
                + e.getX() + " " + e.getY());
    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("mouse entered at point:"
                + e.getX() + " " + e.getY());
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("mouse clicked at point:"
                + e.getX() + " "
                + e.getY() + "mouse clicked :" + e.getClickCount());
    }
}