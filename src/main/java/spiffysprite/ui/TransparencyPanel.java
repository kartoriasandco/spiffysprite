package spiffysprite.ui;

import net.miginfocom.swing.MigLayout;
import spiffysprite.utils.ColourUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;

public class TransparencyPanel extends JPanel {
    public static final int TRANSPARENCY_BACKGROUND_SQUARE_DIMENSION_PX = 4;
    public static final Color TRANSPARENCY_BACKGROUND_COLOUR_0 = Color.WHITE;
    public static final Color TRANSPARENCY_BACKGROUND_COLOUR_1 = Color.LIGHT_GRAY;
    private final int widthPx;
    private final int heightPx;
    private final BufferedImage backgroundImage;
    private Color colour;


    public TransparencyPanel(int widthPx, int heightPx) {
        super(new MigLayout(String.format("width %d, height %d", widthPx, heightPx)));
        this.widthPx = widthPx;
        this.heightPx = heightPx;
        backgroundImage = new BufferedImage(widthPx, heightPx, BufferedImage.TYPE_4BYTE_ABGR);
        setColour(0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Sets the colour of this panel.
     *
     * @param h
     * @param s
     * @param b
     * @param a
     */
    public void setColour(float h, float s, float b, float a) {
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

        final float[] hsbComponents = {h, s, b};
        this.colour = new Color(ColorSpace.getInstance(ColorSpace.CS_sRGB), hsbComponents, a);

        for (int x = 0; x < widthPx; ++x) {
            for (int y = 0; y < heightPx; ++y) {
                final Color transparencyBackgroundColour = ColourUtils.getTransparencyBackgroundColourAt(x, y);
                final Color combinedColour = ColourUtils.combineColours(
                        transparencyBackgroundColour,
                        this.colour,
                        (float) this.colour.getAlpha() / 255.0f
                );

                backgroundImage.setRGB(x, y, combinedColour.getRGB());
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }
}
