package spiffysprite.models;

import spiffysprite.enums.EnumScaleFactors;

import java.awt.image.BufferedImage;

public class Sprite extends BufferedImage {
    public final EnumScaleFactors scaleFactor;

    public Sprite(int widthPx, int heightPx, EnumScaleFactors scaleFactor) {
        super(widthPx, heightPx, BufferedImage.TYPE_INT_ARGB);
        this.scaleFactor = scaleFactor;
    }

    public int getHSBAColourAt(int x, int y) throws IndexOutOfBoundsException {
        if (x < 0 || x >= getWidth()) {
            throw new IndexOutOfBoundsException("Invalid x: " + x);
        } else if (y < 0 || y >= getHeight()) {
            throw new IndexOutOfBoundsException("Invalid y: " + y);
        }

        return EnhancedColour.valueOf(getRGB(x, y));
    }
}
