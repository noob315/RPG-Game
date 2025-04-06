package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) throws Exception {

        int titleSize = 16 * 3;
        int row = 14;
        int column = 18;
        int windowWidth = column * titleSize;
        int windowHeight = row * titleSize;

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D game");
        window.setLocationRelativeTo(null); // window set in center
        window.setSize(windowWidth, windowHeight);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setVisible(true);
        gamePanel.gameThread();
    }
}

