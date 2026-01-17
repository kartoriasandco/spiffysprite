package spiffysprite.models;

import spiffysprite.enums.EnumScaleFactors;

import java.awt.image.BufferedImage;

public class Sprite extends BufferedImage {
    public final EnumScaleFactors scaleFactor;

    public Sprite(int widthPx, int heightPx, EnumScaleFactors scaleFactor) {
        super(widthPx, heightPx, BufferedImage.TYPE_INT_ARGB);
        this.scaleFactor = scaleFactor;
    }

    public Sprite(BufferedImage image, EnumScaleFactors scaleFactor) {
        super(image.getWidth(), image.getHeight(), image.getType());
        this.scaleFactor = scaleFactor;
    }

    public EnhancedColour getColourAt(int x, int y) throws IndexOutOfBoundsException {
        validateCoordinates(this, x, y);
        return EnhancedColour.fromInt(getRGB(x, y));
    }

    public void setColourAt(int x, int y, EnhancedColour colour) {
        validateCoordinates(this, x, y);
        setRGB(x, y, colour.getRGB());
    }

    private static void validateCoordinates(Sprite sprite, int x, int y) {
        if (x < 0 || x >= sprite.getWidth()) {
            throw new IndexOutOfBoundsException("Invalid x: " + x);
        } else if (y < 0 || y >= sprite.getHeight()) {
            throw new IndexOutOfBoundsException("Invalid y: " + y);
        }
    }
}
