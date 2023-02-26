package org.example.a.Graphic;

import org.example.a.Collection.FontCollection;
import org.example.a.JPanel.GamePanel;
import org.example.a.Main;
import org.example.a.Modell.Building.Building;
import org.example.a.Modell.Button.Button;
import org.example.a.Modell.Entity.Unit;
import org.example.a.Modell.ModelLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Graphic {

    public ImageLoader imageLoader = new ImageLoader();
    private FontCollection fontCollection;
    private BufferedImage scrollImage;
    private GamePanel gamePanel;
    private static Graphics2D g2;
    private int clickDrawCounter;
    private ArrayList<Button> buttons;
    private List<Rectangle> buildButtons = new ArrayList<>();


    public Graphic(GamePanel gamePanel) {
        System.out.println("Creating graphics");
        this.fontCollection = new FontCollection();
        this.clickDrawCounter = 0;
        this.gamePanel = gamePanel;
        this.g2 = (Graphics2D) Main.window.getGraphics();
        this.scrollImage = imageLoader.setup("/scroll", gamePanel.getScreenWidth() + 350, 300);
        this.buttons = new ArrayList<>();
        initButtonImg();
        System.out.println("graphics created");
    }

    public void initButtonImg() {
        System.out.println("init button images");
        int x = gamePanel.getScreenWidth() - 400, y = gamePanel.getScreenHeight() - 150;
        int counter = 0;
        int buttonCounter = 0;
        for (Building building : ModelLoader.getBuildings()) {
            buttons.add(new Button("" + buttonCounter, building.getImage(), new Rectangle(x, y, 64, 64)));
            x += 68;
            counter++;
            buttonCounter++;
            if (counter == 4) {
                counter = 0;
                x -= 4 * 68;
                y += 68;
            }
        }
        System.out.println("init done");
    }

    public void drawDragMark(Graphics2D g2) {
        for (Unit u : gamePanel.getUnitList()) {
            if (u.isSelected()) {
                g2.setColor(new Color(0, 0, 0, 190));
                g2.drawRoundRect(u.getWorldX() + 9 - gamePanel.getMouse().getScreenAdjustValueX(), u.getWorldY() + 25 - gamePanel.getMouse().getScreenAdjustValueY(), 30, 30, 30, 30);

                g2.setColor(new Color(150, 10, 10, 125));
                g2.fillRoundRect(u.getWorldX() - gamePanel.getMouse().getScreenAdjustValueX() + 9, u.getWorldY() - gamePanel.getMouse().getScreenAdjustValueY() + 25, 30, 30, 30, 30);
            }
        }
    }

    public void drawRectangle(Graphics2D g2) {
        if (gamePanel.getMouse().isMouseDragged()) {
            Rectangle rectangle = gamePanel.getMouse().getDragRectangle();
            g2.setColor(new Color(0, 0, 199));
            g2.setStroke(new BasicStroke(BasicStroke.CAP_SQUARE));
            g2.drawRect(rectangle.x - gamePanel.getMouse().getScreenAdjustValueX(), rectangle.y - gamePanel.getMouse().getScreenAdjustValueY(), rectangle.width, rectangle.height);
        }
    }

    public void drawUtil(Graphics2D g2) {

        decorateScreen(g2);

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
        g2.setColor(new Color(0, 0, 0));
        g2.drawString("wood " + gamePanel.getPlayer().getWood(), 130, 45);
        g2.drawString("food " + gamePanel.getPlayer().getFood(), 250, 45);
        g2.drawString("gold " + gamePanel.getPlayer().getGold(), 370, 45);
        g2.drawString("stone " + gamePanel.getPlayer().getStone(), 490, 45);

        for (Unit u : getGamePanel().getUnitList()) {
            if (u.isSelected()) {
                g2.setColor(new Color(0, 0, 0, 90));

                /* g2.fillRoundRect(100, gamePanel.getScreenHeight() - 128, 8 * 48, 115, 0, 0);*/
                g2.drawImage(u.getImage(), 110, gamePanel.getScreenHeight() - 122, gamePanel.getTileSize() * 2, gamePanel.getTileSize() * 2, null);

                g2.setFont(fontCollection.getGabriola_25_BOLD());
                g2.setColor(Color.black);
                g2.drawString("Name: " + u.getName(), 210, gamePanel.getScreenHeight() - 120);
                g2.drawString("MaxHp: " + u.getMaxHitPoint(), 210, gamePanel.getScreenHeight() - 100);
                g2.drawString("Hp: " + u.getHitPoint(), 210, gamePanel.getScreenHeight() - 80);
                g2.drawString("Att: " + u.getAttack(), 210, gamePanel.getScreenHeight() - 60);
                g2.drawString("Def: " + u.getDefense(), 210, gamePanel.getScreenHeight() - 40);
                g2.drawString("Range: " + u.getRange(), 210, gamePanel.getScreenHeight() - 20);
            }
        }
    }

    public void drawBuild(Graphics2D g2, BufferedImage image, int x, int y) {
        g2.drawImage(image, x, y, null);
    }

    public void drawBuildBar(Graphics2D g2) {
        for (Unit u : getGamePanel().getUnitList()) {
            if (u.isSelected()) {
                int x = gamePanel.getScreenWidth() - 400, y = gamePanel.getScreenHeight() - 150;
                int counter = 0;

                for (Building building : ModelLoader.getBuildings()) {
                    g2.setColor(new Color(0, 0, 0));
                    g2.fillRect(x, y, 64, 64);
                    g2.drawImage(imageLoader.scaleImage(building.getImage(), 64, 64), x, y, null);
                    x += 68;
                    counter++;
                    if (counter == 4) {
                        counter = 0;
                        x -= 4 * 68;
                        y += 68;
                    }
                }
            }
        }
    }

    public FontCollection getFontCollection() {
        return fontCollection;
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

    public List<Rectangle> getBuildButtons() {
        return buildButtons;
    }

    public void setBuildButtons(List<Rectangle> buildButtons) {
        this.buildButtons = buildButtons;
    }
}
