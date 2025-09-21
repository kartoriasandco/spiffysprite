package spiffysprite.ui;

import net.miginfocom.swing.MigLayout;
import spiffysprite.models.HSBAColour;
import spiffysprite.utils.ColourUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;

public class TransparencyPanel extends JPanel {
    public static final int TRANSPARENCY_BACKGROUND_SQUARE_DIMENSION_PX = 4;
    public static final HSBAColour TRANSPARENCY_BACKGROUND_COLOUR_0 = new HSBAColour(Color.WHITE);
    public static final HSBAColour TRANSPARENCY_BACKGROUND_COLOUR_1 = new HSBAColour(Color.LIGHT_GRAY);
    private final int widthPx;
    private final int heightPx;
    private final BufferedImage backgroundImage;
    private HSBAColour colour;


    public TransparencyPanel(int widthPx, int heightPx) {
        super(new MigLayout(String.format("width %d, height %d", widthPx, heightPx)));
        this.widthPx = widthPx;
        this.heightPx = heightPx;
        backgroundImage = new BufferedImage(widthPx, heightPx, BufferedImage.TYPE_4BYTE_ABGR);
        setColour(new HSBAColour(0.0f, 0.0f, 0.0f, 0.0f));
    }

    /**
     * Sets the colour of this panel.
     *
     * @param colour
     */
    public void setColour(HSBAColour colour) {
        this.colour = colour;
        for (int x = 0; x < widthPx; ++x) {
            for (int y = 0; y < heightPx; ++y) {
                final HSBAColour transparencyBackgroundColour = ColourUtils.getTransparencyBackgroundColourAt(x, y);
                final HSBAColour combinedColour = ColourUtils.combineColours(
                        transparencyBackgroundColour,
                        colour,
                        (float) this.colour.getAlpha() / 255.0f
                );

                backgroundImage.setRGB(x, y, combinedColour.toRGB());
            }
        }
    }

    @Override
    public void setBackground(Color colour) {
        this.setColour(new HSBAColour(colour));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }
}
