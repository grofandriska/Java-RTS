package org.example.a;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    public int originalTileSize = 16;
    public int scale = 3;
    public int tileSize = originalTileSize * scale;
    public int screenWidth, screenHeight;
    public int maxScreenCol, maxScreenRow;
    public Thread gameThread;
    public Player player;
    Mouse mouse = new Mouse(this);

    int worldx =10,worldy =10;

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

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                repaint();
                update();
                delta--;
            }
        }
    }

    public void update() {
        double drawInterval = 1000000000 / 60;
        double delta = 0;

        long lastTime = System.nanoTime();
        long currentTime;

        while ((this.player.getWorldX() != mouse.mouseX || this.player.getWorldY() != mouse.mouseY ) || gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                if (this.player.getWorldX() != mouse.mouseX || this.player.getWorldY() != mouse.mouseY) {
                    if (mouse.mouseX < player.getWorldX()) {
                        player.setWorldX(player.getWorldX() - 1);
                    }
                    if (mouse.mouseX > player.getWorldX()) {
                        player.setWorldX(player.getWorldX() + 1);
                    }
                    if (mouse.mouseY < player.getWorldY()) {
                        player.setWorldY(player.getWorldY() - 1);
                    }
                    if (mouse.mouseY > player.getWorldY()) {
                        player.setWorldY(player.getWorldY() + 1);
                    }
                }
                worldx++;
                worldy++;
                repaint();
                delta--;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(41, 24, 42));
        g2.drawString("OYO", player.getWorldX(), player.getWorldY());
        g2.drawString("XOX",worldx,worldy);
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


