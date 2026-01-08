package spiffysprite.utils;

import spiffysprite.models.Palette;
import spiffysprite.models.Sprite;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public abstract class FileUtils {
    public static void savePalette(Palette palette) {

    }

    public static void saveImage(Sprite sprite, String filePath, String fileName) {
        try {
            File imageFile = new File(filePath, fileName);
            ImageIO.write(sprite.image(), "png", imageFile);
        } catch (IOException ioe) {
            throw new RuntimeException("IOException: " + ioe);
        }
    }
}
