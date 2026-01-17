package spiffysprite.utils;

import spiffysprite.enums.EnumScaleFactors;
import spiffysprite.models.EnhancedColour;
import spiffysprite.models.Sprite;

import java.awt.image.BufferedImage;

public abstract class SpriteUtils {
    public static Sprite scaleSprite(Sprite originalSprite, EnumScaleFactors targetScaleFactor) {
        int scaleFactorDifference = targetScaleFactor.value - originalSprite.scaleFactor.value;
        Sprite scaledSprite;
        
        switch (scaleFactorDifference) {
            // 1x -> 4x
            case 3 -> {
                scaledSprite = new Sprite(
                        originalSprite.getWidth() * 4,
                        originalSprite.getHeight() * 4,
                        EnumScaleFactors.X4
                );

                for (int x = 0; x < originalSprite.getWidth(); ++x) {
                    for (int y = 0; y < originalSprite.getHeight(); ++y) {
                        EnhancedColour colour = EnhancedColour.fromInt(originalSprite.getRGB(x, y));

                        // For each pixel in the original image, colour 4*4 pixels in the scaled image
                        for (int i = 0; i < 4; ++i) {
                            for (int j = 0; j < 4; ++j) {
                                scaledSprite.setColourAt(
                                        (x * 4) + i,
                                        (y * 4) + j,
                                        colour
                                );
                            }
                        }
                    }
                }
            }
            // 2x -> 4x or 1x -> 2x
            case 2, 1 -> {
                scaledSprite = new Sprite(
                        originalSprite.getWidth() * 2,
                        originalSprite.getHeight() * 2,
                        EnumScaleFactors.X2
                );

                for (int x = 0; x < originalSprite.getWidth(); ++x) {
                    for (int y = 0; y < originalSprite.getHeight(); ++y) {
                        EnhancedColour colour = EnhancedColour.fromInt(originalSprite.getRGB(x, y));

                        // For each pixel in the original image, colour 2*2 pixels in the scaled image
                        for (int i = 0; i < 2; ++i) {
                            for (int j = 0; j < 2; ++j) {
                                scaledSprite.setColourAt(
                                        (x * 2) + i,
                                        (y * 2) + j,
                                        colour
                                );
                            }
                        }
                    }
                }
            }
            // 2x -> 1x or 4x -> 2x
            case -1, -2 -> {
                scaledSprite = new BufferedImage(
                        originalSprite.getWidth() / 2,
                        originalSprite.getHeight() / 2,
                        BufferedImage.TYPE_INT_ARGB
                );
            }
            // 4x -> 1x
            case -3 -> {
                scaledSprite = new BufferedImage(
                        originalSprite.getWidth() / 4,
                        originalSprite.getHeight() / 4,
                        BufferedImage.TYPE_INT_ARGB
                );
            }
            // No scaling
            case 0 -> {}

            return scaledSprite;
        }
    }
}
