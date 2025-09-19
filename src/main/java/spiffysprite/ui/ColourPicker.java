package spiffysprite.ui;

import javax.swing.*;
import java.awt.*;

public class ColourPicker extends JPanel {
    private static final int COLOUR_PALETTE_WIDTH_PX = 255;
    private static final int COLOUR_PALETTE_HEIGHT_PX = 100;
    private static Color activeColour;

    public ColourPicker() {
        initColourPicker();
    }

    private void initColourPicker() {

    }

    private void initColourPalette() {

    }

    public static Color getActiveColour() {
        return activeColour;
    }

    public static void setActiveColour(Color colour) {
        activeColour = colour;
    }

    private static JPanel colourPalette;
}
