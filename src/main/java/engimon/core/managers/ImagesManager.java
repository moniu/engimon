package engimon.core.managers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ImagesManager {
    private final HashMap<String, BufferedImage> loadedImages;

    public ImagesManager() {
        loadedImages = new HashMap<>();
    }

    public BufferedImage loadImage(String filename) {
        if (loadedImages.containsKey(filename)) {
            return loadedImages.get(filename);
        }
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(filename));
            loadedImages.put(filename, bufferedImage);
            return bufferedImage;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
