package main;

import java.awt.*;
import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable {
    public int titleSize = 16 * 3;
    int row = 16;
    int column = 21;
    int windowWidth = column * titleSize;
    int windowHeight = row * titleSize;

    Thread thread;
    keyboard keys = new keyboard();
    Player player = new Player(this, keys);

    int FPS = 60;


    GamePanel() {
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        setBackground(Color.BLUE);
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
        player.draw(g2);
        g2.dispose();
    }
}
