package spiffysprite.utils;

import spiffysprite.models.EnhancedColour;
import spiffysprite.ui.TransparencyPanel;

import java.awt.*;

public abstract class ColourUtils {
    /**
     * Returns the combination of two colours. Alpha is ignored. Parameter ratio determines the proportions of the two
     * colours in the final colour. A ratio of 0.5 results in an equal proportion of both colours. A ratio of 1.0
     * simply results in colour1. A ratio of 0.0 simply results in colour0.
     *
     * @param colour0 first colour to combine
     * @param colour1 second colour to combine
     * @param ratio   colour mixing ratio; must be in range [0.0, 1.0]
     * @return combination of both colours
     */
    public static EnhancedColour combineColours(EnhancedColour colour0, EnhancedColour colour1, float ratio) {
        if (ratio < 0.0 || ratio > 1.0) {
            throw new RuntimeException(String.format("Invalid ratio: %f", ratio));
        }

        float colour0Ratio = 1.0f - ratio;
        float colour1Ratio = ratio;

        int combinedRed = (int) ((colour1Ratio * colour0.red) + (colour0Ratio * colour1.red));
        int combinedGreen = (int) ((colour1Ratio * colour0.green) + (colour0Ratio * colour1.green));
        int combinedBlue = (int) ((colour1Ratio * colour0.blue) + (colour0Ratio * colour1.blue));
        return EnhancedColour.fromARGB(combinedRed, combinedGreen, combinedBlue, 255);
    }

    /**
     * Returns one of the two colours that make up the transparency background checkerboard pattern depending on the
     * specified coordinates.
     *
     * @param x x coordinate; must be a positive integer
     * @param y y coordinate; must be a positive integer
     * @return colour of the specified point
     */
    public static EnhancedColour getTransparencyBackgroundColourAt(int x, int y) {
        if (x < 0) {
            throw new RuntimeException(String.format("Invalid x: %d", x));
        }

        if (y < 0) {
            throw new RuntimeException(String.format("Invalid y: %d", y));
        }

        final int dimension = TransparencyPanel.TRANSPARENCY_BACKGROUND_SQUARE_DIMENSION_PX;
        final boolean discriminator = (((x / dimension) + (y / dimension)) % 2) == 0;
        return discriminator ?
                TransparencyPanel.TRANSPARENCY_BACKGROUND_COLOUR_0 :
                TransparencyPanel.TRANSPARENCY_BACKGROUND_COLOUR_1;
    }

    public static int[] AHSBToARGB(float hue, float saturation, float brightness, float alpha) {
        int rgb = Color.HSBtoRGB(hue, saturation, brightness);
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;
        int a = (int) (alpha * 255 + 0.5);
        return new int[] { r, g, b, a };
    }

    public static int AHSBToInt(float hue, float saturation, float brightness, float alpha) {
        int[] argbVals = AHSBToARGB(hue, saturation, brightness, alpha);
        return (argbVals[0] << 24) | (argbVals[1] << 16) | (argbVals[2] << 8) | argbVals[3];
    }

    public static int ARGBToInt(int red, int green, int blue, int alpha) {
        return alpha | red | green | blue;
    }

    public static float[] intToAHSBVals(int intVal) {
        Color.
    }
}
