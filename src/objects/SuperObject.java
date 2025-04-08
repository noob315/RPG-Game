package objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {

    public String name;
    public boolean collsion = false;
    public int worldX, worldY;
    public BufferedImage image;

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.titleSize > gp.player.worldX - gp.player.screenX &&
           worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
           worldY + gp.titleSize > gp.player.worldY - gp.player.screenY &&
           worldY - gp.titleSize < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);
        }
    }
}
