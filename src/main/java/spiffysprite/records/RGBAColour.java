package spiffysprite.records;

import java.awt.*;

public record RGBAColour(int red, int green, int blue, int alpha) {
    public RGBAColour {
        if (red < 0 || red > 255) {
            throw new RuntimeException(String.format("Invalid red: %d", red));
        } else if (green < 0 || green > 255) {
            throw new RuntimeException(String.format("Invalid green: %d", green));
        } else if (blue < 0 || blue > 255) {
            throw new RuntimeException(String.format("Invalid blue: %d", blue));
        } else if (alpha < 0 || alpha > 255) {
            throw new RuntimeException(String.format("Invalid alpha: %d", alpha));
        }

    }

    public RGBAColour(Color colour) {
        this(colour.getRed(), colour.getGreen(), colour.getBlue(), colour.getAlpha());
    }

    public RGBAColour(HSBAColour hsbaColour) {
        this(HSBAColour.toColor(hsbaColour));
    }

    public static Color toColor(RGBAColour rgbaColour) {
        return new Color(rgbaColour.red, rgbaColour.green, rgbaColour.blue,rgbaColour.alpha);
    }

    public static HSBAColour toHSBAColour(RGBAColour rgbColour) {
        float[] hsbVals = new float[3];
        Color.RGBtoHSB(rgbColour.red, rgbColour.green, rgbColour.blue, hsbVals);
        return new HSBAColour(hsbVals[0], hsbVals[1], hsbVals[2], (float) rgbColour.alpha / 255.0f);
    }
}
