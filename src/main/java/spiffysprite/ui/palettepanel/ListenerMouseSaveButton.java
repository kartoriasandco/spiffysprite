package spiffysprite.ui.palettepanel;

import spiffysprite.models.EnhancedColour;
import spiffysprite.ui.ColourPicker;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListenerMouseSaveButton extends MouseAdapter {
    private final PalettePanel palettePanel;

    public ListenerMouseSaveButton(PalettePanel palettePanel) {
        this.palettePanel = palettePanel;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        JButton button = (JButton) me.getSource();
        if (button.isEnabled()) {
            EnhancedColour activeColour = ColourPicker.getActiveColour();
            palettePanel.saveActiveColour(activeColour);
        } else {
            me.consume();
        }
    }
}
