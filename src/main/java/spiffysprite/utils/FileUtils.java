package spiffysprite.utils;

import spiffysprite.models.Palette;
import spiffysprite.models.Sprite;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public abstract class FileUtils {
    public static void savePalette(Palette palette) {

    }

    /**
     * Creates a new .PNG file containing a lossless image of the specified sprite.
     *
     * @param sprite sprite to create an image of
     * @param filePath path to save the .PNG file in
     * @param fileName name of the .PNG file to create
     */
    public static void saveImage(Sprite sprite, String filePath, String fileName) throws IOException, NullPointerException {
        File imageFile = new File(filePath, fileName);
        ImageIO.write(sprite, "png", imageFile);
    }
}
