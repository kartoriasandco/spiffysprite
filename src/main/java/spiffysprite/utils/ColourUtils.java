package spiffysprite.utils;

import spiffysprite.records.HSBAColour;
import spiffysprite.records.RGBAColour;
import spiffysprite.ui.TransparencyPanel;

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
    public static HSBAColour combineColours(HSBAColour colour0, HSBAColour colour1, float ratio) {
        if (ratio < 0.0 || ratio > 1.0) {
            throw new RuntimeException(String.format("Invalid ratio: %f", ratio));
        }

        float colour0Ratio = 1.0f - ratio;
        float colour1Ratio = ratio;
        RGBAColour rgbaColour0 = HSBAColour.toRGBAColour(colour0);
        RGBAColour rgbaColour1 = HSBAColour.toRGBAColour(colour1);

        int combinedRed = (int) ((colour1Ratio * rgbaColour0.red()) + (colour0Ratio * rgbaColour1.red()));
        int combinedGreen = (int) ((colour1Ratio * rgbaColour0.green()) + (colour0Ratio * rgbaColour1.green()));
        int combinedBlue = (int) ((colour1Ratio * rgbaColour0.blue()) + (colour0Ratio * rgbaColour1.blue()));
        return RGBAColour.toHSBAColour(new RGBAColour(combinedRed, combinedGreen, combinedBlue, 255));
    }

    /**
     * Returns one of the two colours that make up the transparency background checkerboard pattern depending on the
     * specified coordinates.
     *
     * @param x x coordinate; must be a positive integer
     * @param y y coordinate; must be a positive integer
     * @return colour of the specified point
     */
    public static HSBAColour getTransparencyBackgroundColourAt(int x, int y) {
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
}
