package spiffysprite.utils;

import spiffysprite.enums.EnumScaleFactors;
import spiffysprite.models.EnhancedColour;
import spiffysprite.models.Sprite;

import java.awt.image.BufferedImage;

public abstract class SpriteUtils {
    public static BufferedImage scaleSprite(Sprite sprite, EnumScaleFactors targetScaleFactor) {
        int scaleFactorDifference = targetScaleFactor.value - sprite.scaleFactor().value;
        BufferedImage originalImage = sprite.image();

        BufferedImage scaledImage;
        switch (scaleFactorDifference) {
            // 1x -> 4x
            case 3 -> {
                scaledImage = new BufferedImage(
                        originalImage.getWidth() * 4,
                        originalImage.getHeight() * 4,
                        BufferedImage.TYPE_INT_ARGB
                );

                for (int x = 0; x < originalImage.getWidth(); ++x) {
                    for (int y = 0; y < originalImage.getHeight(); ++y) {
                        EnhancedColour colour = originalImage.getR
                    }
                }
            }
            // 2x -> 4x or 1x -> 2x
            case 2, 1 -> {
                scaledImage = new BufferedImage(
                        originalImage.getWidth() * 2,
                        originalImage.getHeight() * 2,
                        BufferedImage.TYPE_INT_ARGB
                );
            }
            // 2x -> 1x or 4x -> 2x
            case -1, -2 -> {
                scaledImage = new BufferedImage(
                        originalImage.getWidth() / 2,
                        originalImage.getHeight() / 2,
                        BufferedImage.TYPE_INT_ARGB
                );
            }
            // 4x -> 1x
            case -3 -> {
                scaledImage = new BufferedImage(
                        originalImage.getWidth() / 4,
                        originalImage.getHeight() / 4,
                        BufferedImage.TYPE_INT_ARGB
                );
            }
            // No scaling
            case 0 -> {

            }
        }
    }
}
