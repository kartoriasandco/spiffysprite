package spiffysprite.utils;

import spiffysprite.enums.EnumScaleFactors;
import spiffysprite.models.EnhancedColour;
import spiffysprite.models.Sprite;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public abstract class ImageUtils {
    /**
     * Creates a BufferedImage that contains a lossless scaled-up copy of another BufferedImage.
     *
     * @param originalImage image to scale up
     * @param scaleFactor   degree to which originalImage will be scaled up
     * @return              scaled-up copy of originalImage
     */
    public static BufferedImage scaleUpImage(BufferedImage originalImage, EnumScaleFactors scaleFactor) {
        BufferedImage scaledImage;

        scaledImage = new Sprite(
                originalImage.getWidth() * scaleFactor.value,
                originalImage.getHeight() * scaleFactor.value
        );

        for (int x = 0; x < originalImage.getWidth(); ++x) {
            for (int y = 0; y < originalImage.getHeight(); ++y) {
                EnhancedColour colour = EnhancedColour.fromInt(originalImage.getRGB(x, y));

                for (int i = 0; i < scaleFactor.value; ++i) {
                    for (int j = 0; j < scaleFactor.value; ++j) {
                        scaledImage.setRGB(
                            (x * scaleFactor.value) + i,
                            (y * scaleFactor.value) + j,
                            colour.getRGB()
                        );
                    }
                }
            }
        }

        return scaledImage;
    }

    /**
     * Creates an identical copy of a BufferedImage.
     *
     * @param image BufferedImage to clone
     * @return      a new BufferedImage that is identical to image
     */
    public static BufferedImage cloneImage (BufferedImage image) {
        return new BufferedImage(
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
    }
}
