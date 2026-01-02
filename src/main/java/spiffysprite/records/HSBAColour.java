package spiffysprite.records;

import java.awt.*;

public record HSBAColour(float hue, float saturation, float brightness, float alpha) {
    public HSBAColour {
        if (hue < 0.0f || hue > 255.0f) {
            throw new RuntimeException(String.format("Invalid hue: %f", hue));
        } else if (saturation < 0.0f || saturation > 255.0f) {
            throw new RuntimeException(String.format("Invalid saturation: %f", saturation));
        } else if (brightness < 0.0f || brightness > 255.0f) {
            throw new RuntimeException(String.format("Invalid brightness: %f", brightness));
        } else if (alpha < 0.0f || alpha > 255.0f) {
            throw new RuntimeException(String.format("Invalid alpha: %f", alpha));
        }

    }

    public int getRGB() {
        Color colour = HSBAColour.toColor(this);
        return colour.getRGB();
    }

    public static Color toColor(HSBAColour hsbaColour) {
        Color colour = new Color(Color.HSBtoRGB(hsbaColour.hue, hsbaColour.saturation, hsbaColour.brightness));
        return new Color(colour.getRed(), colour.getGreen(), colour.getBlue(), colour.getAlpha());
    }

    public static RGBAColour toRGBAColour(HSBAColour hsbaColour) {
        Color colour = toColor(hsbaColour);
        return new RGBAColour(colour.getRed(), colour.getGreen(), colour.getBlue(), colour.getAlpha());
    }

    public static HSBAColour valueOf(Color colour) {
        float[] hsbVals = new float[3];
        Color.RGBtoHSB(colour.getRed(), colour.getGreen(), colour.getBlue(), hsbVals);
        return new HSBAColour(hsbVals[0], hsbVals[1], hsbVals[2], colour.getAlpha() / 255.0f);
    }
}
