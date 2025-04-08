package objects;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Key extends SuperObject{
    public Obj_Key() {
        name = "Key"; 
        try {
            File k1 = new File("src/objects/obj_img/key.png");
            image = ImageIO.read(k1);

        } catch (IOException e) {
            e.printStackTrace();;
        }
    }
}
