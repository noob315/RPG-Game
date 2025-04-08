package objects;

import main.GamePanel;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
public void setObject() {
    gp.obj[0] = new Obj_Key();
    gp.obj[0].worldX = 19 * gp.titleSize;
    gp.obj[0].worldY = 22 * gp.titleSize;
}
}
