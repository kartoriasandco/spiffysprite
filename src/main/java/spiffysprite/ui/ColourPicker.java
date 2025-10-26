package spiffysprite.ui;

import net.miginfocom.swing.MigLayout;
import spiffysprite.models.HSBAColour;

import javax.swing.*;
import java.awt.*;

public class ColourPicker extends JPanel {
    static final int COLOUR_PALETTE_WIDTH_PX = 255;
    static final int COLOUR_PALETTE_HEIGHT_PX = 100;
    private static HSBAColour activeColour = new HSBAColour(Color.WHITE);

    public ColourPicker() {
        super(new MigLayout("insets 0"));
        initColourPicker();
    }

    private void initColourPicker() {
        palette = new ColourPickerPalette();
        panelActiveColour = new TransparencyPanel();

        panelActiveColour.setBorder(BorderFactory.createEtchedBorder());
        panelActiveColour.setColour(activeColour);

        this.add(palette, "wrap");
        this.add(panelActiveColour, "width 100%");
    }

    public static HSBAColour getActiveColour() {
        return activeColour;
    }

    public static void setActiveColour(HSBAColour colour) {
        activeColour = colour;
        panelActiveColour.setColour(colour);
    }

    private static ColourPickerPalette palette;
    private static TransparencyPanel panelActiveColour;
}
