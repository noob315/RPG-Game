package main;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class App {
    public static void main(String[] args) throws Exception {
        
        int titleSize = 16 * 3;
        int row = 16;
        int column = 21;
        int windowWidth = column * titleSize;
        int windowHeight = row * titleSize;

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D game");
        window.setLocationRelativeTo(null); //window set in center 
        window.setSize(windowWidth,windowHeight);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setVisible(true);
    }
}

class GamePanel extends JPanel {
    int titleSize = 16 * 3;
    int row = 16;
    int column = 21;
    int windowWidth = column * titleSize;
    int windowHeight = row * titleSize;

    GamePanel() {
        setPreferredSize(new Dimension(windowWidth, windowHeight));
        setBackground(Color.BLACK);
    }
}