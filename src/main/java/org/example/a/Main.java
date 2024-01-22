package org.example.a;

import org.example.a.JPanel.GamePanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static JFrame window = new JFrame();
    public static Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();

    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();


        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Explore");

        window.add(gamePanel);
        window.pack();
        window.setVisible(true);
        gamePanel.startGameThread();

    }
}