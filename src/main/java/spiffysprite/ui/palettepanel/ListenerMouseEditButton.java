package spiffysprite.ui.palettepanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListenerMouseEditButton extends MouseAdapter {
    private final PalettePanel palettePanel;

    public ListenerMouseEditButton(PalettePanel palettePanel) {
        this.palettePanel = palettePanel;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (palettePanel.isEditModeEnabled()) {
            palettePanel.savePalette();
        } else {
            palettePanel.editPalette();
        }
    }
}
