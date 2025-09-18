package spiffysprite.utils;

import spiffysprite.ui.TransparencyPanel;

import java.awt.*;

public abstract class ColourUtils {

    /**
     * Returns the combination of two colours. Alpha is not supported.
     *
     * @param colour0 first colour to combine
     * @param colour1 second colour to combine
     * @return combination of both colours
     */
    public static Color combineColours(Color colour0, Color colour1) {
        float[] hsbVals0 = new float[3];
        Color.RGBtoHSB(colour0.getRed(), colour0.getGreen(), colour0.getBlue(), hsbVals0);

        float[] hsbVals1 = new float[3];
        Color.RGBtoHSB(colour1.getRed(), colour1.getGreen(), colour1.getBlue(), hsbVals1);

        final float combinedHue = (hsbVals0[0] + hsbVals1[0]) / 2.0f;
        final float combinedSaturation = (hsbVals0[1] + hsbVals1[1]) / 2.0f;
        final float combinedBrightness = (hsbVals0[2] + hsbVals1[2]) / 2.0f;
        return Color.getHSBColor(combinedHue, combinedSaturation, combinedBrightness);
    }

    /**
     * Returns one of the two colours that make up the transparency background checkerboard pattern depending on the
     * specified coordinates.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return colour of the specified point
     */
    public static Color getTransparencyBackgroundColourAt(int x, int y) {
        final int dimension = TransparencyPanel.TRANSPARENCY_BACKGROUND_SQUARE_DIMENSION_PX;
        final boolean discriminator = (((x / dimension) + (y / dimension)) % 2) == 0;
        return discriminator ?
                TransparencyPanel.TRANSPARENCY_BACKGROUND_COLOUR_0 :
                TransparencyPanel.TRANSPARENCY_BACKGROUND_COLOUR_1;
    }
}
