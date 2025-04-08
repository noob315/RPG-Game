package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.titleSize;
        int entityRightCol = entityRightWorldX / gp.titleSize;
        int entityTopRow = entityTopWorldY / gp.titleSize;
        int entityBottomRow = entityBottomWorldY / gp.titleSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.titleSize;
                tileNum1 = gp.tileM.mapTilesNum[entityTopRow][entityLeftCol];
                tileNum2 = gp.tileM.mapTilesNum[entityTopRow][entityRightCol];
                if (gp.tileM.tiles[tileNum1].collision == true || gp.tileM.tiles[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.titleSize;
                tileNum1 = gp.tileM.mapTilesNum[entityBottomRow][entityLeftCol];
                tileNum2 = gp.tileM.mapTilesNum[entityBottomRow][entityRightCol];
                if (gp.tileM.tiles[tileNum1].collision == true || gp.tileM.tiles[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;

            case "left":
            entityLeftCol = (entityLeftWorldX - entity.speed) / gp.titleSize;
            tileNum1 = gp.tileM.mapTilesNum[entityTopRow][entityLeftCol];
            tileNum2 = gp.tileM.mapTilesNum[entityBottomRow][entityLeftCol];
            if (gp.tileM.tiles[tileNum1].collision == true || gp.tileM.tiles[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
                break;

            case "right":
            entityRightCol = (entityRightWorldX + entity.speed) / gp.titleSize;
            tileNum1 = gp.tileM.mapTilesNum[entityTopRow][entityRightCol];
            tileNum2 = gp.tileM.mapTilesNum[entityBottomRow][entityRightCol];
            if (gp.tileM.tiles[tileNum1].collision == true || gp.tileM.tiles[tileNum2].collision == true) {
                entity.collisionOn = true;
            }
                break;
        }
    }
}
