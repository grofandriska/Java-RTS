package org.example.a.JPanel;

import org.example.a.*;
import org.example.a.Graphic.Graphic;
import org.example.a.Graphic.ImageLoader;
import org.example.a.Map.Map;
import org.example.a.Modell.*;
import org.example.a.Modell.Building.Building;
import org.example.a.Modell.Entity.Unit_2;
import org.example.a.Modell.Entity.Unit;
import org.example.a.Modell.Object.Object;
import org.example.a.Mouse.Mouse;
import org.example.a.Sound.Sound;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    private int originalTileSize, scale, tileSize;
    private int maxScreenCol, maxScreenRow, screenWidth, screenHeight;
    private Thread gameThread;
    private Unit unit;
    private Unit_2 unit_2;
    private Mouse mouse;
    private Graphic graphic;
    private Sound sound;
    private ArrayList<Object> objects;
    private ArrayList<Unit> unitList;

    public ArrayList <Building> buildings = new ArrayList<>();
    private Map map;
    private Player player;
    private ModelLoader modelLoader;

    {
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
        graphicsDevice.setFullScreenWindow(Main.window);

        this.scale = 3;
        this.originalTileSize = 16;
        this.tileSize = this.originalTileSize * this.scale;

        this.player = new Player();

        this.screenWidth = Main.window.getWidth();
        this.screenHeight = Main.window.getHeight();

        this.maxScreenCol = this.screenWidth / this.tileSize;
        this.maxScreenRow = this.screenHeight / this.tileSize;

        this.modelLoader = new ModelLoader(this);
        this.graphic = new Graphic(this);
        this.sound = new Sound(this);
        this.unit = new Unit(this);
        this.unit_2 = new Unit_2(this);
        this.mouse = new Mouse(this);
        this.map = new Map(this);

        this.objects = new ArrayList<>();
        this.unitList = new ArrayList<>();
        this.modelLoader.setTrees(40);
    }

    public GamePanel() {
        this.setPreferredSize(new Dimension((this.maxScreenCol * this.tileSize), (int) ((this.maxScreenRow + 0.5) * this.tileSize)));
        this.setBackground(Color.ORANGE);
        this.setDoubleBuffered(true);
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouse);
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
        unit.movePLayer();
        unit_2.movePLayer();
    }

    public void paintComponent(Graphics g) {

        //setup
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(41, 24, 42));

        //draw background
        map.draw(g2);
        graphic.drawDragMark(g2);
        graphic.drawRectangle(g2);

        //draw players
        g2.drawImage(unit_2.getImage(), unit_2.getWorldX(), unit_2.getWorldY(), null);
        g2.drawImage(unit.getImage(), unit.getWorldX(), unit.getWorldY(), null);


        if (mouse.isBuilding()) {
            graphic.drawBuild(Graphic.getG2(), ImageLoader.scaleImage(mouse.getBuilding().getImage(), 255, 255), mouse.getMouseX(), mouse.getMouseY());
        }

        for (Building b:buildings) {
            g2.drawImage(b.getImage(),b.getWorldX(),b.getWorldY(),null);
        }

        for (Object ob : objects) {
            ob.draw(g2);
        }

        graphic.drawUtil(g2);
        g2.dispose();

    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }


    public ArrayList<Unit> getUnitList() {
        return unitList;
    }

    public void setUnitList(ArrayList<Unit> unitList) {
        this.unitList = unitList;
    }

    public ModelLoader getModelLoader() {
        return modelLoader;
    }

    public void setModelLoader(ModelLoader modelLoader) {
        this.modelLoader = modelLoader;
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<Object> objects) {
        this.objects = objects;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getOriginalTileSize() {
        return originalTileSize;
    }

    public void setOriginalTileSize(int originalTileSize) {
        this.originalTileSize = originalTileSize;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public void setMaxScreenCol(int maxScreenCol) {
        this.maxScreenCol = maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public void setMaxScreenRow(int maxScreenRow) {
        this.maxScreenRow = maxScreenRow;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public Thread getGameThread() {
        return gameThread;
    }

    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }

    public Graphic getGraphic() {
        return graphic;
    }

    public void setGraphic(Graphic graphic) {
        this.graphic = graphic;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Unit_2 getUnit_2() {
        return unit_2;
    }

    public void setUnit_2(Unit_2 unit_2) {
        this.unit_2 = unit_2;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }
}


