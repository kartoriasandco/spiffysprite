package spiffysprite.ui.spritegrid;

import net.miginfocom.swing.MigLayout;
import spiffysprite.records.HSBAColour;
import spiffysprite.ui.ColourPicker;
import spiffysprite.ui.TransparencyPanel;
import spiffysprite.ui.UIMaster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class SpriteGrid extends JPanel {
    public static final int SPRITE_SIZE_CELLS = 24;
    public static final int CELL_SIZE_PX = 24;
    private boolean mouseButtonPressed;
    private TransparencyPanel[][] gridCells;

    public SpriteGrid() {
        super(new MigLayout("insets 0, gapx 0, gapy 0"));
        initGrid();
        mouseButtonPressed = false;
    }

    private void initGrid() {
        gridCells = new TransparencyPanel[SPRITE_SIZE_CELLS][SPRITE_SIZE_CELLS];

        for (int y = 0; y < SPRITE_SIZE_CELLS; ++y) {
            for (int x = 0; x < SPRITE_SIZE_CELLS; ++x) {
                TransparencyPanel cell = new TransparencyPanel();
                gridCells[x][y] = cell;
                cell.addMouseListener(new ListenerMouseSpriteCell(this));

                if (y == 0 && x == 0) {
                    cell.setBorder(BorderFactory.createLineBorder(Color.RED));
                } else {
                    cell.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                }

                String formatString = (x == (SPRITE_SIZE_CELLS - 1)) ?
                        String.format("width %d, height %d, wrap", CELL_SIZE_PX, CELL_SIZE_PX) :
                        String.format("width %d, height %d", CELL_SIZE_PX, CELL_SIZE_PX);

                cell.addMouseListener(new CellSelectedMouseAdapter(cell));
                this.add(cell, formatString);
            }
        }
    }

    boolean isMouseButtonPressed() {
        return mouseButtonPressed;
    }

    void setMouseButtonPressed(boolean mouseButtonPressed) {
        this.mouseButtonPressed = mouseButtonPressed;
    }

    BufferedImage toBufferedImage() {
        BufferedImage image = new BufferedImage(SPRITE_SIZE_CELLS, SPRITE_SIZE_CELLS, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < SPRITE_SIZE_CELLS; ++x) {
            for (int y = 0; y < SPRITE_SIZE_CELLS; ++y) {
                image.setRGB(x, y, gridCells[x][y].getColour().getRGB());
            }
        }

        return image;
    }

    private class CellSelectedMouseAdapter extends MouseAdapter {
        private final TransparencyPanel cell;
        public CellSelectedMouseAdapter(TransparencyPanel cell) {
            this.cell = cell;
        }

        @Override
        public void mouseClicked(MouseEvent me) {
            final HSBAColour colour = ColourPicker.getActiveColour();
            cell.setColour(colour);
            UIMaster.refreshGraphics();
        }
    }
}
