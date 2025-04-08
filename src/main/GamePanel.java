package main;

import java.awt.*;
import javax.swing.JPanel;
import entity.Player;
import objects.AssetSetter;
import objects.SuperObject;
import tiles.TilesManager;

public class GamePanel extends JPanel implements Runnable {
    //screen size setting
    public int titleSize = 16 * 3;
    int row = 14;
    int column = 18;
    public int windowWidth = column * titleSize;
    public int windowHeight = row * titleSize;
    //world map size setting
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = maxWorldCol * titleSize;
    public final int worldHeight = maxWorldRow * titleSize;

    Thread thread;
    Keyboard keys = new Keyboard();
    public CollisionChecker ColliChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keys);
    TilesManager tileM = new TilesManager(this);
    public SuperObject obj[] = new SuperObject[10]; //Number of objects display on the screen

    int FPS = 60; //60 frames per second

    public int gameState;
    public final int playState = 1;
    public final int stopState = 0;


    GamePanel() {
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        setBackground(Color.black);
        addKeyListener(keys);
        setFocusable(true);

    }
    public void setupGame() {
        aSetter.setObject();
    }
    public void gameThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        double interval = 1000000000/FPS; //draw the sreen per 0.01666s
        double nextTime = System.nanoTime() + interval; //current time + 0.01666s

        while (thread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0) {   //if update and repaint have no remaining time left
                    remainingTime = 0;     //no need to sleep 
                }

                Thread.sleep((long) remainingTime);   
                nextTime += interval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        long drawstart = 0;
        drawstart = System.nanoTime();
        tileM.draw(g2);
        for (int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }
        player.draw(g2);
        long drawend = System.nanoTime();
        long passed = drawend - drawstart;
        System.out.println("time : " + passed);
        g2.dispose();
    }
}
