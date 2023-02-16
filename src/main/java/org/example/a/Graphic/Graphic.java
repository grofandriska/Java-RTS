package org.example.a.Graphic;

import org.example.a.JPanel.GamePanel;
import org.example.a.Main;

import java.awt.*;

public class Graphic {

    private GamePanel gamePanel;

    private final Graphics2D g2;

    private int clickDrawCounter;

    public Graphic(GamePanel gamePanel) {
        this.clickDrawCounter = 0;
        this.gamePanel = gamePanel;
        this.g2 = (Graphics2D) Main.window.getGraphics();
    }

    public void drawDragMark(Graphics2D g2){
        if (gamePanel.getPlayer().isSelected()) {
            g2.setColor(new Color(0, 0, 0, 190));
            g2.drawRoundRect(getGamePanel().getPlayer().getWorldX() + 9, getGamePanel().getPlayer().getWorldY() + 25, 30, 30, 30, 30);
            g2.setColor(new Color(150, 10, 10, 125));
            g2.fillRoundRect(getGamePanel().getPlayer().getWorldX() + 9, getGamePanel().getPlayer().getWorldY() + 25, 30, 30, 30, 30);
        }
        if (getGamePanel().getPlayer2().isSelected()) {
            g2.setColor(new Color(0, 0, 0, 190));
            g2.drawRoundRect(getGamePanel().getPlayer2().getWorldX() + 9, getGamePanel().getPlayer2().getWorldY() + 25, 30, 30, 30, 30);
            g2.setColor(new Color(150, 90, 10, 125));
            g2.fillRoundRect(getGamePanel().getPlayer2().getWorldX() + 9, getGamePanel().getPlayer2().getWorldY() + 25, 30, 30, 30, 30);
        }
    }
    public void drawRectangle(Graphics2D g2) {
        if (gamePanel.getMouse().isMouseDragged()){
            Rectangle rectangle = gamePanel.getMouse().getDragRectangle();
            g2.setColor(new Color(0, 0, 199));
            g2.setStroke(new BasicStroke(BasicStroke.CAP_SQUARE));
            g2.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }

    public void drawUtil(Graphics2D g2) {
        //draw util
        g2.setColor(new Color(0, 0, 0, 145));
        g2.fillRoundRect(0, 0, 400, 220, 0, 0);

        g2.setColor(new Color(255, 255, 255));
        g2.setFont(new Font("arial", Font.BOLD, 20));

        g2.drawString("npc goal -x " + gamePanel.getPlayer2().getGoalX(), 20, 30);
        g2.drawString("npc goal -y " + gamePanel.getPlayer2().getGoalY(), 20, 60);
        g2.drawString("npc -x " + gamePanel.getPlayer2().getWorldX(), 250, 30);
        g2.drawString("npc -y " + gamePanel.getPlayer2().getWorldY(), 250, 60);

        g2.drawString("player goal -x " + gamePanel.getPlayer().getGoalX(), 20, 120);
        g2.drawString("player goal -y " + gamePanel.getPlayer().getGoalY(), 20, 150);
        g2.drawString("player -x " + gamePanel.getPlayer().getWorldX(), 250, 120);
        g2.drawString("player -y " + gamePanel.getPlayer().getWorldY(), 250, 150);
    }

    public void showClick(Integer x, Integer y) {
            if (clickDrawCounter != 20) {
                g2.setColor(new Color(0, 0, 0));
                g2.drawRoundRect(x, y, 15, 15, 3, 3);

                g2.setColor(new Color(0, 0, 190));
                g2.fillRoundRect(x, y, 15, 15, 3, 3);

                clickDrawCounter++;
            } else {
                clickDrawCounter = 0;
                getGamePanel().getMouse().setMouseX(null);
                getGamePanel().getMouse().setMouseY(null);
            }

        }


    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
