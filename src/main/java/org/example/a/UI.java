package org.example.a;

import java.awt.*;

public class UI {

    private GamePanel gamePanel;

    private final Graphics2D g2;

    private int clickDrawCounter;

    public UI(GamePanel gamePanel) {
        this.clickDrawCounter = 0;
        this.gamePanel = gamePanel;
        this.g2 = (Graphics2D) Main.window.getGraphics();
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
        if (getGamePanel().getMouse().getMouseX() != null && getGamePanel().getMouse().getMouseX() != null) {
            if (clickDrawCounter != 30) {
                g2.setColor(new Color(0, 0, 0 ));
                g2.drawRoundRect(x, y, 15, 15, 3, 3);

                g2.setColor(new Color(0, 0, 190));
                g2.fillRoundRect(x, y, 15, 15, 3, 3);
                clickDrawCounter++;
                System.out.println(clickDrawCounter);
            } else {
                clickDrawCounter = 0;
                getGamePanel().getMouse().setMouseX(null);
                getGamePanel().getMouse().setMouseY(null);
            }

        }
    }
    public GamePanel getGamePanel() {
        return gamePanel;
    }
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
