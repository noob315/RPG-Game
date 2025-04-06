package main;

import java.awt.*;
import javax.swing.JPanel;

import entity.Player;
import tiles.TilesManager;

public class GamePanel extends JPanel implements Runnable {

    public int titleSize = 16 * 3;
    int row = 14;
    int column = 18;
    public int windowWidth = column * titleSize;
    public int windowHeight = row * titleSize;

    Thread thread;
    Keyboard keys = new Keyboard();
    Player player = new Player(this, keys);
    TilesManager tileM = new TilesManager(this);

    int FPS = 60;


    GamePanel() {
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        setBackground(Color.black);
        addKeyListener(keys);
        setFocusable(true);

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
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
