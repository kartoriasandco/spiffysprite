package spiffysprite.ui.spritegrid;

import spiffysprite.records.HSBAColour;
import spiffysprite.ui.ColourPicker;
import spiffysprite.ui.TransparencyPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListenerMouseSpriteCell extends MouseAdapter {
    private final SpriteGrid spriteGrid;

    public ListenerMouseSpriteCell(SpriteGrid spriteGrid) {
        this.spriteGrid = spriteGrid;
    }

    @Override
    public void mousePressed(MouseEvent me) {
        spriteGrid.setMouseButtonPressed(true);
        HSBAColour activeColour = ColourPicker.getActiveColour();
        TransparencyPanel spriteCell = (TransparencyPanel) me.getSource();
        spriteCell.setColour(activeColour);

        super.mousePressed(me);
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        spriteGrid.setMouseButtonPressed(false);
        super.mouseReleased(me);
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        if (spriteGrid.isMouseButtonPressed()) {
            HSBAColour activeColour = ColourPicker.getActiveColour();
            TransparencyPanel spriteCell = (TransparencyPanel) me.getSource();
            spriteCell.setColour(activeColour);
        }

        super.mouseEntered(me);
    }
}
