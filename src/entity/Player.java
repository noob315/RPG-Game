package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.*;

public class Player extends Entity {
    GamePanel gp;
    keyboard keys;

    public Player(GamePanel gp, keyboard keys) {
        x = 150;
        y = 150;
        speed = 5;
        direction = "down_stop";
        this.gp = gp;
        this.keys = keys;

        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            File d0_1 = new File("src/player_img/player_front_stop1.png");
            File d0_2 = new File("src/player_img/player_front_stop2.png");
            File d1 = new File("src/player_img/player_down_walk_1.png");
            File d2 = new File("src/player_img/player_down_walk_2.png");
            File u0_1 = new File("src/player_img/player_back_stop1.png");
            File u0_2 = new File("src/player_img/player_back_stop2.png");
            File u1 = new File("src/player_img/player_up_walk_1.png");
            File u2 = new File("src/player_img/player_up_walk_2.png");

            downStop1 = ImageIO.read(d0_1);
            downStop2 = ImageIO.read(d0_2);
            down1 = ImageIO.read(d1);
            down2 = ImageIO.read(d2);
            upStop1 = ImageIO.read(u0_1);
            upStop2 = ImageIO.read(u0_2);
            up1 = ImageIO.read(u1);
            up2 = ImageIO.read(u2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keys.upside == true || keys.downside == true || keys.leftside == true || keys.rightside == true) {
            if (keys.upside == true) {
                direction = "up";
                y -= speed;
            } else if (keys.downside == true) {
                direction = "down";
                y += speed;
            } else if (keys.leftside == true) {
                direction = "left";
                x -= speed;
            } else if (keys.rightside == true) {
                direction = "right";
                x += speed;
            }
            animeCount++;
            if (animeCount > 10) {
                if (animeNum == 1) {
                    animeNum = 2;
                } else if (animeNum == 2) {
                    animeNum = 1;
                }
                animeCount = 0;
            }
        } else {
            if (direction == "up"){
                direction = "up_stop";
            }else if (direction == "down") {
                direction = "down_stop";
            }

            animeCount++;
            if (animeCount > 15) {
                if (animeNum == 1) {
                    animeNum = 2;
                } else if (animeNum == 2) {
                    animeNum = 1;
                }
                animeCount = 0;
            }

        }


    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "down_stop":
                if (animeNum == 1) {
                    image = downStop1;
                }
                if (animeNum == 2) {
                    image = downStop2;
                }
                break;
            case "up_stop":
                if (animeNum == 1) {
                    image = upStop1;
                }
                if (animeNum == 2) {
                    image = upStop2;
                }
                break;
            case "up":
                if (animeNum == 1) {
                    image = up1;
                }
                if (animeNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (animeNum == 1) {
                    image = down1;
                }
                if (animeNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                image = downStop1;
                break;
            case "right":
                image = downStop1;
                break;
        }

        g2.drawImage(image, x, y, gp.titleSize, gp.titleSize, null);
    }
}
