package spiffysprite.ui;

import net.miginfocom.swing.MigLayout;
import spiffysprite.models.HSBAColour;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ColourPickerPalette extends JPanel {
    static final int WIDTH_PX = 256;
    static final int HEIGHT_PX = 256;
    private static BufferedImage backgroundImage;

    public ColourPickerPalette() {
        super(new MigLayout(String.format("width %d, height %d", WIDTH_PX, HEIGHT_PX)));
        backgroundImage = new BufferedImage(WIDTH_PX + 1, HEIGHT_PX, BufferedImage.TYPE_INT_RGB);
        drawPalette();
    }

    private void drawPalette() {
        final HSBAColour activeColour = ColourPicker.getActiveColour();
        final float[] hsbaVals = activeColour.getHSBAVals();
        for (int h = 0; h < WIDTH_PX; ++h) {
            for (int s = 0; s < HEIGHT_PX; ++s) {
                final float hueFloat = (float) h / 255.0f;
                final float saturationFloat = (float) s / 255.0f;
                final HSBAColour colour = new HSBAColour(hueFloat, saturationFloat, hsbaVals[2], hsbaVals[3]);
                backgroundImage.setRGB(h, (WIDTH_PX - 1 - s), colour.toRGB());
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }
}
