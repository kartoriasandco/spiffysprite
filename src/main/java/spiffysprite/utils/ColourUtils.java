package spiffysprite.utils;

import spiffysprite.models.HSBAColour;
import spiffysprite.ui.TransparencyPanel;

import java.awt.*;

public abstract class ColourUtils {
    /**
     * Returns the combination of two colours. Alpha is ignored. Parameter ratio determines the proportions of the two
     * colours in the final colour. A ratio of 0.5 results in an equal proportion of both colours. A ratio of 1.0
     * results in simply colour0. A ratio of 0.0 results in simply colour1.
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

        final float[] hsbVals0 = colour0.getHSBAVals();
        final float[] hsbVals1 = colour1.getHSBAVals();
        final float combinedHue = (hsbVals0[0] + hsbVals1[0]) / 2.0f;
        final float combinedSaturation = (hsbVals0[1] + hsbVals1[1]) / 2.0f;
        final float combinedBrightness = (hsbVals0[2] + hsbVals1[2]) / 2.0f;
        return new HSBAColour(combinedHue, combinedSaturation, combinedBrightness);
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
