package org.example.a;

import org.example.a.GamePanel;

import javax.swing.*;

public class Main {
    public static JFrame window = new JFrame();

    public static void main(String[] args) {

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Explore");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}