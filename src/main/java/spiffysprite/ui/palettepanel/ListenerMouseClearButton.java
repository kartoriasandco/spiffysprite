package spiffysprite.ui.palettepanel;

import spiffysprite.enums.EnumDefaultColours;
import spiffysprite.ui.TransparencyPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListenerMouseClearButton extends MouseAdapter {
    private final PalettePanel palettePanel;

    public ListenerMouseClearButton(PalettePanel palettePanel) {
        this.palettePanel = palettePanel;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (palettePanel.isEditModeEnabled()) {
            TransparencyPanel selectedCell = palettePanel.getSelectedCell();
            selectedCell.setColour(EnumDefaultColours.CLEAR.EnhancedColour);
        } else {
            me.consume();
        }
    }
}
