package spiffysprite.ui;

import spiffysprite.models.HSBAColour;

import javax.swing.*;
import java.awt.*;

public class ColourPicker extends JPanel {
    private static final int COLOUR_PALETTE_WIDTH_PX = 255;
    private static final int COLOUR_PALETTE_HEIGHT_PX = 100;
    private static HSBAColour activeColour = new HSBAColour(Color.WHITE);

    public ColourPicker() {
        initColourPicker();
    }

    private void initColourPicker() {
        palette = new ColourPickerPalette();

        this.add(palette);
    }

    public static HSBAColour getActiveColour() {
        return activeColour;
    }

    public static void setActiveColour(HSBAColour colour) {
        activeColour = colour;
    }

    private static ColourPickerPalette palette;
}
