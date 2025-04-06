package tiles;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TilesManager {
    GamePanel gp;
    Tiles[] tiles;
    int mapTilesNum[][];
    String filepath = "src/tiles/map_img/map_01.txt";

    public TilesManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tiles[10];
        mapTilesNum = new int[gp.windowHeight][gp.windowWidth];
        getTilesImage();
        try {
            loadMap(filepath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getTilesImage() {
        try {
            tiles[0] = new Tiles();
            File g1 = new File("src/tiles/tiles_img/grass_1.png");
            tiles[0].image = ImageIO.read(g1);

            tiles[1] = new Tiles();
            File t1 = new File("src/tiles/tiles_img/tree_1.png");
            tiles[1].image = ImageIO.read(t1);

            tiles[2] = new Tiles();
            File w1 = new File("src/tiles/tiles_img/water_1.png");
            tiles[2].image = ImageIO.read(w1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadMap(String filepath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filepath));
        int row = 0;

        while (scanner.hasNextLine() && row < mapTilesNum.length) {
            String line = scanner.nextLine();
            String[] values = line.trim().split("\\s+"); //Split by one or more whitespace

            for (int col = 0; col < values.length && col < mapTilesNum[row].length; col++) {
                mapTilesNum[row][col] = Integer.parseInt(values[col]);
            }
            row++;
        }
        scanner.close();
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.windowWidth && row < gp.windowHeight) {
            int tileNum = mapTilesNum[row][col];
            g2.drawImage(tiles[tileNum].image, x, y, gp.titleSize, gp.titleSize, null);
            col++;
            x += gp.titleSize;

            if (col == gp.windowWidth) {
                col = 0;
                x = 0;
                row++;
                y += gp.titleSize;
            }
        }
    }
}
