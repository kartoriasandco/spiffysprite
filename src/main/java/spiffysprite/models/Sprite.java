package spiffysprite.models;

import spiffysprite.enums.EnumScaleFactors;

import java.awt.image.BufferedImage;

public class Sprite extends BufferedImage {
    public Sprite(int widthPx, int heightPx) {
        super(widthPx, heightPx, BufferedImage.TYPE_INT_ARGB);
    }

    private Sprite(BufferedImage image) {
        super(image.getWidth(), image.getHeight(), image.getType());
    }

    public EnhancedColour getColourAt(int x, int y) throws IndexOutOfBoundsException {
        validateCoordinates(x, y);
        return EnhancedColour.fromInt(getRGB(x, y));
    }

    public void setColourAt(int x, int y, EnhancedColour colour) {
        validateCoordinates(x, y);
        setRGB(x, y, colour.getRGB());
    }

    /**
     * Factory method that creates a new sprite that contains a copy of the specified image.
     *
     * @param image image whose data will be copied into the new sprite
     * @return sprite containing a copy of the data in image
     */
    public static Sprite fromBufferedImage(BufferedImage image) {
        BufferedImage newImage = new BufferedImage(
                image.getColorModel(),
                image.copyData(null),
                image.isAlphaPremultiplied(),
                null
        ).getSubimage(
                0,
                0,
                image.getWidth(),
                image.getHeight()
        );

        return new Sprite(image);
    }

    /**
     * Throws an exception if the coordinates are outside the dimensions of this sprite.
     *
     * @param x x coordinate to check
     * @param y y coordinate to check
     * @throws RuntimeException if x or y coordinates are out of bounds
     */
    private void validateCoordinates(int x, int y) throws RuntimeException {
        if (x < 0 || x >= getWidth()) {
            throw new IndexOutOfBoundsException("Invalid x: " + x);
        } else if (y < 0 || y >= getHeight()) {
            throw new IndexOutOfBoundsException("Invalid y: " + y);
        }
    }
}
