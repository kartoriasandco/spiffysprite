package spiffysprite.models;

import java.awt.*;

public class HSBAColour {
    private final Color colour;
    private final float[] hsbaVals = new float[4];

    public HSBAColour(float h, float s, float b, float a) {
        if (h < 0.0 || h > 1.0) {
            throw new RuntimeException(String.format("Invalid hue: %f", h));
        }

        if (s < 0.0 || s > 1.0) {
            throw new RuntimeException(String.format("Invalid saturation: %f", s));
        }

        if (b < 0.0 || b > 1.0) {
            throw new RuntimeException(String.format("Invalid brightness: %f", b));
        }

        if (a < 0.0 || a > 1.0) {
            throw new RuntimeException(String.format("Invalid alpha: %a", a));
        }

        final Color noAlphaColour = new Color(Color.HSBtoRGB(h, s, b));
        colour = new Color(noAlphaColour.getRed(), noAlphaColour.getGreen(), noAlphaColour.getBlue(), (int) (a * 255));
        hsbaVals[0] = h;
        hsbaVals[1] = s;
        hsbaVals[2] = b;
        hsbaVals[3] = a;
    }

    public HSBAColour(float h, float s, float b) {
        this(h, s, b, 1.0f);
    }

    public HSBAColour(int r, int g, int b, int a) {
        if (r < 0 || r > 255) {
            throw new RuntimeException(String.format("Invalid red: %d", r));
        }

        if (g < 0 || g > 255) {
            throw new RuntimeException(String.format("Invalid green: %d", g));
        }

        if (b < 0 || b > 255) {
            throw new RuntimeException(String.format("Invalid blue: %d", b));
        }

        if (a < 0 || a > 255) {
            throw new RuntimeException(String.format("Invalid alpha: %d", a));
        }

        colour = new Color(r, g, b, a);

        final float[] hsbVals = new float[3];
        Color.RGBtoHSB(r, g, b, hsbVals);
        hsbaVals[0] = hsbVals[0];
        hsbaVals[1] = hsbVals[1];
        hsbaVals[2] = hsbVals[2];
        hsbaVals[3] = (float) a / 255f;
    }

    public HSBAColour(int r, int g, int b) {
        this(r, g, b, 255);
    }

    public HSBAColour(int rgb) {
        colour = new Color(rgb);

        final float[] hsbVals = new float[3];
        Color.RGBtoHSB(colour.getRed(), colour.getGreen(), colour.getBlue(), hsbVals);
        hsbaVals[0] = hsbVals[0];
        hsbaVals[1] = hsbVals[1];
        hsbaVals[2] = hsbVals[2];
        hsbaVals[3] = (float) colour.getAlpha() / 255f;
    }

    public HSBAColour(Color colour) {
        this(colour.getRGB());
    }

    public Color toColor() {
        return colour;
    }

    public int toRGB() {
        return colour.getRGB();
    }

    public float[] getHSBAVals() {
        return hsbaVals;
    }

    public int getRed() {
        return colour.getRed();
    }

    public int getGreen() {
        return colour.getGreen();
    }

    public int getBlue() {
        return colour.getBlue();
    }

    public int getAlpha() {
        return colour.getAlpha();
    }
}
