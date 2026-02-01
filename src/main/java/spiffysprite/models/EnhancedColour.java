package spiffysprite.models;

import spiffysprite.utils.ColourUtils;

import java.awt.*;
import java.util.HashMap;

public class EnhancedColour extends Color {
    private static final HashMap<String, EnhancedColour> COLOUR_CACHE = new HashMap<>();

    public final float hue;
    public final float saturation;
    public final float brightness;
    public final float alpha;
    public final int red;
    public final int green;
    public final int blue;
    public final int hsba;

    private EnhancedColour(float hue, float saturation, float brightness, float alpha) {
        this.hue = hue;
        this.saturation = saturation;
        this.brightness = brightness;
        this.alpha = alpha;

        int[] rgbaVals = ColourUtils.HSBAToRGBA(hue, saturation, brightness, alpha);
        int r = rgbaVals[0];
        int g = rgbaVals[1];
        int b = rgbaVals[2];
        int a = rgbaVals[3];

        //System.out.printf("h: %f, s: %f, b: %f, a: %f\n", hue, saturation, brightness, alpha);
        if (alpha > 1.0f) {
            System.out.println("break here!");
        }

        super(r, g, b, a);

        red = r;
        green = g;
        blue = b;
        hsba = ColourUtils.HSBAToInt(hue, saturation, brightness, alpha);
    }

    private EnhancedColour(int red, int green, int blue, int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;

        super(red, green, blue, alpha);

        float[] hsbaVals = ColourUtils.RGBAToHSBA(red, green, blue, alpha);
        hue = hsbaVals[0];
        saturation = hsbaVals[1];
        brightness = hsbaVals[2];
        this.alpha = hsbaVals[3];
        hsba = ColourUtils.HSBAToInt(hue, saturation, brightness, hsbaVals[3]);
    }

    /**
     * Creates a new EnhancedColour from a set of hue, saturation, brightness, and alpha values. Argument values must
     * be in the range [0.0, 1.0].
     *
     * @param hue hue value; must be in [0.0, 1.0]
     * @param saturation saturation value; must be in [0.0, 1.0]
     * @param brightness brightness value; must be in [0.0, 1.0]
     * @param alpha alpha value; must be in [0.0, 1.0]
     * @return new EnhancedColour with the specified AHSB values
     * @throws IllegalArgumentException when an argument value is outside the allowed range
     */
    public static EnhancedColour fromAHSB(float hue, float saturation, float brightness, float alpha)
            throws IllegalArgumentException {

        int hsba = ColourUtils.HSBAToInt(hue, saturation, brightness, alpha);
        return COLOUR_CACHE.computeIfAbsent(
                "0x" + Integer.toHexString(hsba).toUpperCase(),
                _ -> new EnhancedColour(hue, saturation, brightness, alpha)
        );
    }

    /**
     * Creates a new EnhancedColour from a set of red, green, blue, and alpha values. Argument values must be in the
     * range [0, 255].
     *
     * @param red red value; must be in [0, 255]
     * @param green green value; must be in [0, 255]
     * @param blue blue value; must be in [0, 255]
     * @param alpha alpha value; must be in [0, 255]
     * @return new EnhancedColour with the specified ARGB properties
     * @throws IllegalArgumentException when an argument value is outside the allowed range
     */
    public static EnhancedColour fromARGB(int red, int green, int blue, int alpha) throws IllegalArgumentException {
        int hsba = ColourUtils.RGBAToInt(red, green, blue, alpha);
        return COLOUR_CACHE.computeIfAbsent(
                "0x" + Integer.toHexString(hsba).toUpperCase(),
                _ -> new EnhancedColour(red, green, blue, alpha)
        );
    }

    /**
     * Creates a new EnhancedColour from an integer. Argument value must be in the range [0, 4,294,967,295]. Maximum
     * value is equal to 0xFFFFFFFF. 
     *
     * @param val integer representation of the desired colour; ust be in [0, 4,294,967,295]
     * @return new EnhancedColour representation of the specified integer
     * @throws IllegalArgumentException when the argument value is outside the allowed range
     */
    public static EnhancedColour fromInt(int val) throws IllegalArgumentException {
        int[] argbVals = ColourUtils.intToRGBA(val);
        String key = "0x" + Integer.toHexString(val).toUpperCase();
        System.out.println("fromInt key: " + key);
        return COLOUR_CACHE.computeIfAbsent(
                "0x" + Integer.toHexString(val).toUpperCase(),
                _ -> new EnhancedColour(
                    argbVals[0],
                    argbVals[1],
                    argbVals[2],
                    argbVals[3]
            )
        );
    }

    /**
     * Creates a new EnhancedColour from a java.awt.Color.
     *
     * @param colour java.awt.Color representation of the desired colour
     * @return new EnhancedColour representation of the specified java.awt.Color
     */
    public static EnhancedColour fromColor(Color colour) {
        return fromARGB(colour.getRed(), colour.getGreen(), colour.getBlue(), colour.getAlpha());
    }

    @Override
    public String toString() {
        return String.format(
                "EnhancedColour[alp=0x%s,hue=0x%s,sat=0x%s,bri=0x%s,red=%d,grn=%d,blu=%d]",
                Integer.toHexString((int) alpha * 255).toUpperCase(),
                Integer.toHexString((int) hue * 255).toUpperCase(),
                Integer.toHexString((int) saturation * 255).toUpperCase(),
                Integer.toHexString((int) brightness * 255).toUpperCase(),
                red,
                green,
                blue
        );
    }
}
