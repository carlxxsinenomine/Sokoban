package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Orb extends SuperObject {
    public OBJ_Orb() {
        name = "Orb";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/assets/orb.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
