package org.example.a.Graphic;

import org.example.a.FontCollection;
import org.example.a.JPanel.GamePanel;
import org.example.a.Main;
import org.example.a.Modell.Building.Building;
import org.example.a.Modell.Button;
import org.example.a.Modell.ModelLoader;
import org.example.a.Mouse.Mouse;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Graphic {

    private FontCollection fontCollection;
    private BufferedImage scrollImage;
    private GamePanel gamePanel;
    private static Graphics2D g2;
    private int clickDrawCounter;
    private ArrayList<Button> buttons;
    private static ArrayList<Building> buttonBuildings;

    public Graphic(GamePanel gamePanel) {
        this.fontCollection = new FontCollection();
        this.clickDrawCounter = 0;
        this.gamePanel = gamePanel;
        this.g2 = (Graphics2D) Main.window.getGraphics();
        this.scrollImage = ImageLoader.setup("/scroll", gamePanel.getScreenWidth() + 350, 300);
        this.buttons = new ArrayList<>();
        buttonBuildings = new ArrayList<>();
    }

    public void drawDragMark(Graphics2D g2) {
        if (gamePanel.getUnit().isSelected()) {
            g2.setColor(new Color(0, 0, 0, 190));
            g2.drawRoundRect(getGamePanel().getUnit().getWorldX() + 9, getGamePanel().getUnit().getWorldY() + 25, 30, 30, 30, 30);
            g2.setColor(new Color(150, 10, 10, 125));
            g2.fillRoundRect(getGamePanel().getUnit().getWorldX() + 9, getGamePanel().getUnit().getWorldY() + 25, 30, 30, 30, 30);
        }
        if (getGamePanel().getUnit_2().isSelected()) {
            g2.setColor(new Color(0, 0, 0, 190));
            g2.drawRoundRect(getGamePanel().getUnit_2().getWorldX() + 5, getGamePanel().getUnit_2().getWorldY() + 25, 30, 30, 30, 30);
            g2.setColor(new Color(150, 90, 10, 125));
            g2.fillRoundRect(getGamePanel().getUnit_2().getWorldX() + 5, getGamePanel().getUnit_2().getWorldY() + 25, 30, 30, 30, 30);
        }
    }

    public void drawRectangle(Graphics2D g2) {
        if (gamePanel.getMouse().isMouseDragged()) {
            Rectangle rectangle = gamePanel.getMouse().getDragRectangle();
            g2.setColor(new Color(0, 0, 199));
            g2.setStroke(new BasicStroke(BasicStroke.CAP_SQUARE));
            g2.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
    }

    public void drawUtil(Graphics2D g2) {

        decorateScreen(g2);
        drawBuildBar(g2);
        //draw util
       /* g2.setColor(new Color(0, 0, 0, 145));
        g2.fillRoundRect(0, gamePanel.getScreenHeight() - 50, gamePanel.getScreenWidth(), 300, 0, 0);

        g2.setColor(new Color(255, 255, 255));
        g2.setFont(new Font("arial", Font.BOLD, 20));

        g2.drawString("npc goalX -> " + gamePanel.getPlayer2().getGoalX() + "||  ", 0, gamePanel.getScreenHeight() - 22);
        g2.drawString("npc goalY -> " + gamePanel.getPlayer2().getGoalY()+ "||  ", gamePanel.getTileSize() * 4, gamePanel.getScreenHeight() - 22);
        g2.drawString("npc X -> " + gamePanel.getPlayer2().getWorldX()+ "||  ", gamePanel.getTileSize() * 8, gamePanel.getScreenHeight() - 22);
        g2.drawString("npc Y -> " + gamePanel.getPlayer2().getWorldY()+ "||  ", gamePanel.getTileSize() * 12, gamePanel.getScreenHeight() - 22);

        g2.drawString("player goalX -> " + gamePanel.getPlayer().getGoalX()+ "||  ", gamePanel.getTileSize() * 16, gamePanel.getScreenHeight() - 22);
        g2.drawString("player goalY -> " + gamePanel.getPlayer().getGoalY()+ "||  ", gamePanel.getTileSize() * 20, gamePanel.getScreenHeight() - 22);
        g2.drawString("player X -> " + gamePanel.getPlayer().getWorldX()+ "||  ", gamePanel.getTileSize() * 24, gamePanel.getScreenHeight() - 22);
        g2.drawString("player Y -> " + gamePanel.getPlayer().getWorldY()+ "||  ", gamePanel.getTileSize() * 28, gamePanel.getScreenHeight() - 22);*/
    }

    public void showClick(Integer x, Integer y) {
        if (clickDrawCounter != 60000) {
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

    public void decorateScreen(Graphics2D g2) {
        g2.drawImage(scrollImage, -165, gamePanel.getScreenHeight() - 215, null);
        g2.drawImage(scrollImage, -165, -125, null);

        g2.setFont(fontCollection.getGabriola_25_BOLD());
        g2.setColor(new Color(0,0,0));
        g2.drawString("wood " + gamePanel.getPlayer().getWood(), 130, 45);
        g2.drawString("food " + gamePanel.getPlayer().getFood(), 250, 45);
        g2.drawString("gold " + gamePanel.getPlayer().getGold(), 370, 45);
        g2.drawString("stone " + gamePanel.getPlayer().getStone(), 490, 45);

        if (gamePanel.getUnit().isSelected()) {
            g2.setColor(new Color(0, 0, 0, 90));

            /* g2.fillRoundRect(100, gamePanel.getScreenHeight() - 128, 8 * 48, 115, 0, 0);*/
            g2.drawImage(gamePanel.getUnit().getImage(), 110, gamePanel.getScreenHeight() - 122, gamePanel.getTileSize() * 2, gamePanel.getTileSize() * 2, null);

            g2.setFont(fontCollection.getGabriola_25_BOLD());
            g2.setColor(Color.black);
            g2.drawString("Name: " + gamePanel.getUnit().getName(), 210, gamePanel.getScreenHeight() - 120);
            g2.drawString("MaxHp: " + gamePanel.getUnit().getMaxHitPoint(), 210, gamePanel.getScreenHeight() - 100);
            g2.drawString("Hp: " + gamePanel.getUnit().getHitPoint(), 210, gamePanel.getScreenHeight() - 80);
            g2.drawString("Att: " + gamePanel.getUnit().getAttack(), 210, gamePanel.getScreenHeight() - 60);
            g2.drawString("Def: " + gamePanel.getUnit().getDefense(), 210, gamePanel.getScreenHeight() - 40);
            g2.drawString("Range: " + gamePanel.getUnit().getRange(), 210, gamePanel.getScreenHeight() - 20);
        }
    }

    public void drawBuild(Graphics2D g2, BufferedImage image, int x, int y) {

        g2.drawImage(image, x, y, null);
    }

    public void drawBuildBar(Graphics2D g2) {

        if (gamePanel.getUnit().isSelected()) {

            ArrayList<Building> buildings = ModelLoader.getBuildings();
            int x = (gamePanel.getScreenWidth() / 2) + 340;
            int y = gamePanel.getScreenHeight() - 145;

            int space = 5;
            int colCounter = 0;


            for (Building building : buildings) {

                g2.setColor(new Color(0, 0, 0, 50));
                g2.fillRect(x, y, 64, 64);
                g2.drawImage(building.getImage(), x, y, 64, 64, null);

                building.getSolidArea().x = x;
                building.getSolidArea().y = y;
                building.getSolidArea().height = 64;
                building.getSolidArea().width = 64;


                x += 64 + space;
                colCounter++;
                if (colCounter == 3) {
                    x = (gamePanel.getScreenWidth() / 2) + 364;
                    y += 64 + space;
                }
            }
            buttonBuildings = buildings;
        }
    }

    public FontCollection getFontCollection() {
        return fontCollection;
    }

    public static ArrayList<Building> getButtonBuildings() {
        return buttonBuildings;
    }

    public void setButtonBuildings(ArrayList<Building> buttonBuildings) {
        Graphic.buttonBuildings = buttonBuildings;
    }

    public void setFontCollection(FontCollection fontCollection) {
        this.fontCollection = fontCollection;
    }


    public BufferedImage getScrollImage() {
        return scrollImage;
    }

    public void setScrollImage(BufferedImage scrollImage) {
        this.scrollImage = scrollImage;
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public void setButtons(ArrayList<Button> buttons) {
        this.buttons = buttons;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public static Graphics2D getG2() {
        return g2;
    }

    public int getClickDrawCounter() {
        return clickDrawCounter;
    }

    public void setClickDrawCounter(int clickDrawCounter) {
        this.clickDrawCounter = clickDrawCounter;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
