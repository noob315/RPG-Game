package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;

    public BufferedImage downStop1, downStop2, down1, down2, upStop1, upStop2, up1, up2, 
    leftStop1, leftStop2, left1, left2, rightStop1, rightStop2, right1, right2;
    public String direction;

    public int animeCount = 0;
    public int animeNum = 1;

}
