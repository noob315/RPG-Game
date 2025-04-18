package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.*;

public class Player extends Entity {
    GamePanel gp;
    Keyboard keys;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, Keyboard keys) {
        worldX = gp.titleSize * 25; // charcter starting points
        worldY = gp.titleSize * 25;
        speed = 5;
        direction = "down_stop";
        this.gp = gp;
        this.keys = keys;
        screenX = gp.windowWidth / 2 - (gp.titleSize / 2); // Always display player at the center of X
        screenY = gp.windowHeight / 2 - (gp.titleSize / 2); // Always display player at the center of Y
        solidArea = new Rectangle(8, 16, 32, 32); // top left corner coordinate: (8,16)
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            File d0_1 = new File("src/player_img/player_front_stop_1.png");
            File d0_2 = new File("src/player_img/player_front_stop_2.png");
            File d1 = new File("src/player_img/player_down_walk_1.png");
            File d2 = new File("src/player_img/player_down_walk_2.png");
            File u0_1 = new File("src/player_img/player_back_stop_1.png");
            File u0_2 = new File("src/player_img/player_back_stop_2.png");
            File u1 = new File("src/player_img/player_up_walk_1.png");
            File u2 = new File("src/player_img/player_up_walk_2.png");
            File l0_1 = new File("src/player_img/player_left_stop_1.png");
            File l0_2 = new File("src/player_img/player_left_stop_2.png");
            File l1 = new File("src/player_img/player_left_walk_1.png");
            File l2 = new File("src/player_img/player_left_walk_2.png");
            File r0_1 = new File("src/player_img/player_right_stop_1.png");
            File r0_2 = new File("src/player_img/player_right_stop_2.png");
            File r1 = new File("src/player_img/player_right_walk_1.png");
            File r2 = new File("src/player_img/player_right_walk_2.png");

            downStop1 = ImageIO.read(d0_1);
            downStop2 = ImageIO.read(d0_2);
            down1 = ImageIO.read(d1);
            down2 = ImageIO.read(d2);
            upStop1 = ImageIO.read(u0_1);
            upStop2 = ImageIO.read(u0_2);
            up1 = ImageIO.read(u1);
            up2 = ImageIO.read(u2);
            leftStop1 = ImageIO.read(l0_1);
            leftStop2 = ImageIO.read(l0_2);
            left1 = ImageIO.read(l1);
            left2 = ImageIO.read(l2);
            rightStop1 = ImageIO.read(r0_1);
            rightStop2 = ImageIO.read(r0_2);
            right1 = ImageIO.read(r1);
            right2 = ImageIO.read(r2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        boolean movingVertical = keys.upside || keys.downside;
        boolean movingHorizontal = keys.leftside || keys.rightside;
        if (keys.upside == true || keys.downside == true || keys.leftside == true || keys.rightside == true) {

            if (movingVertical) {
                if (keys.upside) {
                    direction = "up";

                } else if (keys.downside) {
                    direction = "down";

                }
            } else if (movingHorizontal) {
                if (keys.leftside) {
                    direction = "left";

                } else if (keys.rightside) {
                    direction = "right";

                }
            }
            collisionOn = false;
            gp.ColliChecker.checkTile(this);

            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            animeCount++;
            if (animeCount > 15) {
                if (animeNum == 1) {
                    animeNum = 2;
                } else if (animeNum == 2) {
                    if (direction == "up" || direction == "down") {
                        animeNum = 1;
                    } else {
                        animeNum = 3;
                    }
                } else if (animeNum == 3) {
                    animeNum = 1;
                }
                animeCount = 0;
            }

        } else {
            if (direction == "up") {
                animeNum = 1;
                direction = "up_stop";
            } else if (direction == "down") {
                animeNum = 1;
                direction = "down_stop";
            } else if (direction == "left") {
                animeNum = 1;
                direction = "left_stop";
            } else if (direction == "right") {
                animeNum = 1;
                direction = "right_stop";
            }

            animeCount++;
            if (animeCount > 20) {
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
            case "right_stop":
                if (animeNum == 1) {
                    image = rightStop1;
                }
                if (animeNum == 2) {
                    image = rightStop2;
                }
                break;
            case "left_stop":
                if (animeNum == 1) {
                    image = leftStop1;
                }
                if (animeNum == 2) {
                    image = leftStop2;
                }
                break;
            case "up":
                if (animeNum == 1 || animeNum == 3) { // handling transition from left/right 3 to up/down 1
                    image = up1;
                }
                if (animeNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (animeNum == 1 || animeNum == 3) {
                    image = down1;
                }
                if (animeNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (animeNum == 1) {
                    image = left1;
                }
                if (animeNum == 2) {
                    image = left2;
                }
                if (animeNum == 3) {
                    image = leftStop1;
                }
                break;
            case "right":
                if (animeNum == 1) {
                    image = right1;
                }
                if (animeNum == 2) {
                    image = right2;
                }
                if (animeNum == 3) {
                    image = rightStop1;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null);
    }
}
