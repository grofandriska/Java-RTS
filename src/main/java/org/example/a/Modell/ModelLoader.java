package org.example.a.Modell;

import org.example.a.JPanel.GamePanel;

public class ModelLoader {

    private GamePanel gamePanel;


    public ModelLoader(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObjects() {
        this.gamePanel.getObjects().add(new Tree(699, 699));
        this.gamePanel.getObjects().add(new Tree(659, 639));
        this.gamePanel.getObjects().add(new Tree(679, 519));
        this.gamePanel.getObjects().add(new Tree(399, 1699));
        this.gamePanel.getObjects().add(new Tree(1699, 299));
        this.gamePanel.getObjects().add(new Tree(299, 299));
    }
}