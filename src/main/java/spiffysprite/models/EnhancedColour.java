package spiffysprite.models;

import spiffysprite.utils.ColourUtils;

import java.awt.*;
import java.util.HashMap;

public class EnhancedColour extends Color {
    private static final HashMap<Integer, EnhancedColour> COLOUR_CACHE = new HashMap<>();

    public final float hue;
    public final float saturation;
    public final float brightness;
    public final float alpha;
    public final int red;
    public final int green;
    public final int blue;
    public final int rgba;

    private EnhancedColour(float hue, float saturation, float brightness, float alpha) {
        this.hue = hue;
        this.saturation = saturation;
        this.brightness = brightness;
        this.alpha = alpha;

        int rgb = Color.HSBtoRGB(hue, saturation, brightness);
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = rgb & 0xFF;
        int a = (int) (alpha * 255 + 0.5f);

        super(r, g, b, a);

        red = r;
        green = g;
        blue = b;
        rgba = a | r | g | b;
    }

    public static EnhancedColour fromAHSB(float hue, float saturation, float brightness, float alpha)
            throws IllegalArgumentException {

        int rgba = ColourUtils.AHSBToInt(hue, saturation, brightness, alpha);
        return COLOUR_CACHE.computeIfAbsent(rgba, _ -> new EnhancedColour(hue, saturation, brightness, alpha));
    }

    public static EnhancedColour fromARGB(int red, int green, int blue, int alpha) throws IllegalArgumentException {
        int rgba = ColourUtils.ARGBToInt(red, green, blue, alpha);
        return COLOUR_CACHE.computeIfAbsent(rgba, _ -> new EnhancedColour(red, green, blue, alpha));
    }

    public static EnhancedColour fromInt(int val) {
        ColourUtils.
    }
}
