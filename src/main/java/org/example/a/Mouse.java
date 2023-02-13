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
//public void selectAndMove (MouseEvent me){
//    Rectangle cursor = new Rectangle(me.getX(),me.getY(),6,6);
//
//    If(cursor.intersects(gamePamel.getPlayer().getSolidAre() && me.getClick(Button1)){
//       cursor = null;
//       gamePanel.getPlayer().setIsClicked(true);
//    } else {gamePanel.getPlayer().setIsClicked(false);}
//.   if (me.get(MouseButton2) && gamePanel.getPlayer().getIsClicked()){
//.   gamePanel.getPlayer().setGoalX(me.getX());
//.   gamePanel.getPlayer().setGoalY(me.getY());
//.   }





    public void mousePressed(MouseEvent e) {
       selectAndMove(e);
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
