package org.example.a;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {

    public int originalTileSize, scale, tileSize;
    public int maxScreenCol, maxScreenRow, screenWidth, screenHeight;
    public Thread gameThread;
    public Player player;
    public Player2 player2;
    public Mouse mouse;

    {
        scale = 3;
        originalTileSize = 16;
        tileSize = originalTileSize * scale;
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();

        graphicsDevice.setFullScreenWindow(Main.window);

        this.screenWidth = Main.window.getWidth();
        this.screenHeight = Main.window.getHeight();

        this.maxScreenCol = this.screenWidth / this.tileSize;
        this.maxScreenRow = this.screenHeight / this.tileSize;

        this.player = new Player(this);
        this.player2 = new Player2(this);
        this.mouse = new Mouse(this);


    }

    public GamePanel() {
        this.setPreferredSize(new Dimension((this.maxScreenCol * this.tileSize), ((this.maxScreenRow * this.tileSize))));
        this.setBackground(Color.ORANGE);
        this.setDoubleBuffered(true);
        this.addMouseListener(mouse);
        this.setFocusable(true);

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
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        player.movePLayer();
        player2.movePLayer();
    }

    public void paintComponent(Graphics g) {

        //setup
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(41, 24, 42));
        BufferedImage image = ImageLoader.setup("/Grass1", tileSize, tileSize);

        //draw background
        int x = 0;
        int y = 0;
        for (int i = 0; i < maxScreenRow + 1; i++) {
            for (int k = 0; k < maxScreenCol + 1; k++) {
                g2.drawImage(image, x, y, null);
                x += tileSize;
            }
            x = 0;
            y += tileSize;
        }

        //draw util 
        g2.setColor(new Color(0, 0, 0, 145));

        g2.fillRoundRect(0, 0, 400, 220, 0, 0);
     
        g2.setColor(new Color(255, 255, 255));
        g2.setFont(new Font("arial", Font.BOLD, 20));

        g2.drawString("npc goal -x " + player2.goalX , 20, 30);
        g2.drawString("npc goal -y " + player2.goalY , 20, 60);

        g2.drawString("npc -x " + player2.worldX , 250, 30);
        g2.drawString("npc -y " + player2.worldY , 250, 60);




        g2.drawString("player goal -x " + mouse.mouseX , 20, 120);
        g2.drawString("player goal -y " + mouse.mouseY , 20, 150);

        g2.drawString("player -x " + player.getWorldX() , 250, 120);
        g2.drawString("player -y " + player.getWorldY() , 250, 150);

        //draw players
        g2.drawImage(player2.image, player2.worldX, player2.worldY, null);
        g2.drawImage(player.getImage(), player.getWorldX(), player.getWorldY(), null);

        g2.dispose();
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }
}


