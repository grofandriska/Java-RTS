package org.example.a;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class GamePanel extends JPanel implements Runnable {

    public int originalTileSize = 16;
    public int scale = 3;
    public int tileSize = originalTileSize * scale;
    public int screenWidth, screenHeight;
    public int maxScreenCol, maxScreenRow;
    public Thread gameThread;
    public Player player;
    public Player2 player2 = new Player2(this);
    Mouse mouse = new Mouse(this);

    {
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();

        graphicsDevice.setFullScreenWindow(Main.window);

        this.screenWidth = Main.window.getWidth();
        this.screenHeight = Main.window.getHeight();

        this.maxScreenCol = this.screenWidth / this.tileSize;
        this.maxScreenRow = this.screenHeight / this.tileSize;

        this.player = new Player(this);
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / 60;
        double delta = 0;

        long lastTime = System.nanoTime();
        long currentTime;

        //while player move
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        player.movePLayer(mouse.mouseX, mouse.mouseY);
        player2.movePLayer();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(41, 24, 42));
        g2.drawString("OYO", player.getWorldX(), player.getWorldY());
        g2.drawString("XOX", player2.worldX, player2.worldY);
        g2.dispose();
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(this.maxScreenCol * this.tileSize, this.maxScreenRow * this.tileSize));
        this.setBackground(Color.ORANGE);
        this.setDoubleBuffered(true);
        this.addMouseListener(mouse);
        this.setFocusable(true);
    }
}


