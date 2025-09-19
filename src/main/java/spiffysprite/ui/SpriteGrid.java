package spiffysprite.ui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class SpriteGrid extends JPanel {
    public static final int SPRITE_SIZE_CELLS = 24;
    public static final int CELL_SIZE_PX = 24;

    public SpriteGrid() {
        super(new MigLayout("insets 0, gapx 0, gapy 0"));
        initGrid();
    }

    private void initGrid() {
        for (int y = 0; y < SPRITE_SIZE_CELLS; ++y) {
            for (int x = 0; x < SPRITE_SIZE_CELLS; ++x) {
                var panel = new TransparencyPanel(CELL_SIZE_PX, CELL_SIZE_PX);
                panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                String formatString = (x == (SPRITE_SIZE_CELLS - 1)) ?
                        String.format("width %d, height %d, wrap", CELL_SIZE_PX, CELL_SIZE_PX) :
                        String.format("width %d, height %d", CELL_SIZE_PX, CELL_SIZE_PX);

                this.add(panel, formatString);
            }
        }
    }
}
