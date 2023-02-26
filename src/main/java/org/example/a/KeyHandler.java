package org.example.a;

import org.example.a.JPanel.GamePanel;
import org.example.a.Modell.Entity.Unit;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private GamePanel gamePanel;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            for (Unit u : gamePanel.getPlayer().getUnitList()) {
                if (u.isSelected()) {
                    gamePanel.getPlayer().setScreenX(u.getWorldX());
                    gamePanel.getPlayer().setScreenY(u.getWorldY());
                    return;
                }
            }
        }
    }



    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
