package spiffysprite.records;

import java.awt.*;
import java.awt.image.BufferedImage;

public record Sprite(BufferedImage image){

    public HSBAColour[] toCellArray() {
        HSBAColour[] cellArray = new HSBAColour[image.getHeight() * image.getWidth()];

        for (int x = 0; x < image.getWidth(); ++x) {
            for (int y = 0; y < image.getHeight(); ++y) {
                cellArray[x * y] = HSBAColour.valueOf(new Color image.getRGB(x, y));
            }
        }

        return cellArray;
    }
}
