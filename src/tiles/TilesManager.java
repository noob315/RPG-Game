package tiles;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class TilesManager {
    GamePanel gp;
    Tiles[] tiles;
    int mapTilesNum[][];
    boolean[][] tileChanged;
    String filepath = "src/tiles/map_img/map_02.txt";
    BufferedImage backgroundImage;

    public TilesManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tiles[10];
        mapTilesNum = new int[gp.maxWorldRow][gp.maxWorldCol];
        getTilesImage();
        try {
            loadMap(filepath);
            createBackground();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getTilesImage() {
        /*
         * try {
         * tiles[0] = new Tiles();
         * File g1 = new File("src/tiles/tiles_img/grass_1.png");
         * tiles[0].image = ImageIO.read(g1);
         * 
         * 
         * tiles[1] = new Tiles();
         * File t1 = new File("src/tiles/tiles_img/tree_1.png");
         * tiles[1].image = ImageIO.read(t1);
         * 
         * tiles[2] = new Tiles();
         * File w1 = new File("src/tiles/tiles_img/water_1.png");
         * tiles[2].image = ImageIO.read(w1);
         * 
         * } catch (IOException e) {
         * e.printStackTrace();
         * }
         */

        setup(0, "grass_1");
        setup(1, "tree_1");
        setup(2, "water_1");
        setup(3, "path_1");

    }

    public void setup(int index, String imagePath) {
        UtilityTool uTool = new UtilityTool();
        try {
            tiles[index] = new Tiles();
            File background = new File("src/tiles/tiles_img/" + imagePath + ".png");
            tiles[index].image = ImageIO.read(background);
            tiles[index].image = uTool.scaleImage(tiles[index].image, gp.titleSize, gp.titleSize);

        } catch (Exception e) {
        }
    }

    private void loadMap(String filepath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filepath));
        int row = 0;

        while (scanner.hasNextLine() && row < mapTilesNum.length) {
            String line = scanner.nextLine();
            String[] values = line.trim().split("\\s+"); // Split by one or more whitespace

            for (int col = 0; col < values.length && col < mapTilesNum[row].length; col++) {
                mapTilesNum[row][col] = Integer.parseInt(values[col]);
                //tileChanged[row][col] = false;
            }
            row++;
        }
        scanner.close();
    }

    private void createBackground() {
        backgroundImage = new BufferedImage(gp.maxWorldCol * gp.titleSize, gp.maxWorldRow * gp.titleSize,
                BufferedImage.TYPE_INT_ARGB);
        drawBackground(backgroundImage); // Draw the initial background
    }

    private void drawBackground(BufferedImage backgroundImage) {
        Graphics2D g2 = backgroundImage.createGraphics();
        int WorldCol = 0;
        int WorldRow = 0;

        while (WorldCol < gp.maxWorldCol && WorldRow < gp.maxWorldRow) {
            int tileNum = mapTilesNum[WorldRow][WorldCol];
            int worldX = WorldCol * gp.titleSize;
            int worldY = WorldRow * gp.titleSize;

            g2.drawImage(tiles[tileNum].image, worldX, worldY, gp.titleSize, gp.titleSize, null);
            WorldCol++;

            if (WorldCol == gp.maxWorldCol) {
                WorldCol = 0;
                WorldRow++;
            }

        }
        g2.dispose();
    }

    public void updateTile(int WorldRow, int WorldCol, int tileNum) {
        mapTilesNum[WorldRow][WorldCol] = tileNum; // Update the tile number
        drawBackground(backgroundImage);
    }

    public void draw(Graphics2D g2) {
        int screenX = gp.player.worldX - gp.player.screenX;;
        int screenY = gp.player.worldY - gp.player.screenY;
        g2.drawImage(backgroundImage, -screenX, -screenY, null); // Draw the pre-rendered background

    }

    /*
     * For fix screen size map
     * public void draw(Graphics2D g2) {
     * int col = 0;
     * int row = 0;
     * int x = 0;
     * int y = 0;
     * 
     * while (col < gp.windowWidth && row < gp.windowHeight) {
     * int tileNum = mapTilesNum[row][col];
     * g2.drawImage(tiles[tileNum].image, x, y, gp.titleSize, gp.titleSize, null);
     * col++;
     * x += gp.titleSize;
     * 
     * if (col == gp.windowWidth) {
     * col = 0;
     * x = 0;
     * row++;
     * y += gp.titleSize;
     * }
     * }
     * }
     */
}
