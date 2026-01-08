package spiffysprite.ui.palettepanel;

import spiffysprite.models.EnhancedColour;
import spiffysprite.ui.ColourPicker;
import spiffysprite.ui.TransparencyPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListenerMousePaletteCell extends MouseAdapter {
    private final PalettePanel palettePanel;

    public ListenerMousePaletteCell(PalettePanel palettePanel) {
        this.palettePanel = palettePanel;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        TransparencyPanel paletteCell = (TransparencyPanel) me.getSource();
        EnhancedColour colour = paletteCell.getColour();
        if (palettePanel.isEditModeEnabled()) {
            palettePanel.selectPaletteCell(paletteCell);
        } else {
            ColourPicker.setActiveColour(colour);
            ColourPicker.setHue(colour.hue);
            ColourPicker.setSaturation(colour.saturation);
            ColourPicker.setBrightness(colour.brightness);
            ColourPicker.setAlpha(colour.alpha);
        }
    }
}
