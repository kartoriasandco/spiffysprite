package spiffysprite.ui.spritegrid;

import net.miginfocom.swing.MigLayout;
import spiffysprite.models.EnhancedColour;
import spiffysprite.models.Sprite;
import spiffysprite.ui.TransparencyPanel;
import spiffysprite.ui.UIMaster;
import spiffysprite.utils.NumberUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class SpriteGrid extends JPanel {
    private static final int CELL_SIZE_PX = 24;
    private static final int INITIAL_SPRITE_HEIGHT_CELLS = 24;
    private static final int INITIAL_SPRITE_WIDTH_CELLS = 24;
    private static JPanel panelGrid;
    private static boolean mouseButtonPressed;
    private static TransparencyPanel[][] gridCells;
    private static int spriteHeightCells = 0;
    private static int spriteWidthCells = 0;

    public SpriteGrid() {
        super(new MigLayout("insets 0", "[align center]", "[align center]"));
        panelGrid = new JPanel(new MigLayout("insets 0, gapx 0, gapy 0"));
        initGrid(INITIAL_SPRITE_WIDTH_CELLS, INITIAL_SPRITE_HEIGHT_CELLS);
        this.add(panelGrid);
        mouseButtonPressed = false;
    }

    /**
     * Adds pixel cells to the grid. Minimum grid size is 2x2 cells.
     *
     * @param width width of the grid in cells
     * @param height height of the grid in cells
     * @throws IllegalArgumentException if width or height are less than 2
     */
    private void initGrid(int width, int height) throws IllegalArgumentException {
        if (!NumberUtils.inRange(width, 2)) {
            throw new IllegalArgumentException("width must be no less than 2");
        }

        if (!NumberUtils.inRange(height, 2)) {
            throw new IllegalArgumentException("height must be no less than 2");
        }

        /*
         * If we are loading a sprite that has the same dimensions as the current grid, we don't need to remove and
         * re-add the cells.
         */
        if (width == spriteWidthCells && height == spriteHeightCells) {
            return;
        }

        removeCells();
        gridCells = new TransparencyPanel[width][height];

        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                TransparencyPanel cell = new TransparencyPanel();
                gridCells[x][y] = cell;
                cell.addMouseListener(new ListenerMouseSpriteCell());
                cell.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

                String formatString = (x == (width - 1)) ?
                        String.format("width %d, height %d, wrap", CELL_SIZE_PX, CELL_SIZE_PX) :
                        String.format("width %d, height %d", CELL_SIZE_PX, CELL_SIZE_PX);

                cell.addMouseListener(new ListenerMouseSpriteCell());
                panelGrid.add(cell, formatString);
            }
        }

        spriteWidthCells = width;
        spriteHeightCells = height;
    }

    /**
     * Removes all cells from the current sprite leaving the panel blank.
     */
    private void removeCells() {
        if (gridCells == null) {
            return;
        }

        for (TransparencyPanel[] gridCellRow : gridCells) {
            for (TransparencyPanel gridCell : gridCellRow) {
                this.remove(gridCell);
            }
        }

        gridCells = null;
        UIMaster.refreshGraphics();
    }

    static boolean isMouseButtonPressed() {
        return mouseButtonPressed;
    }

    static void setMouseButtonPressed(boolean mouseButtonPressed) {
        SpriteGrid.mouseButtonPressed = mouseButtonPressed;
    }

    /**
     * Creates a Sprite object from the pixel grid.
     *
     * @return Sprite representation of the grid
     */
    public static Sprite toSprite() {
        BufferedImage image = new BufferedImage(spriteWidthCells, spriteHeightCells, BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < spriteWidthCells; ++x) {
            for (int y = 0; y < spriteHeightCells; ++y) {
                image.setRGB(x, y, gridCells[x][y].getColour().getRGB());
            }
        }

        return Sprite.fromBufferedImage(image);
    }

    /**
     * Displays a sprite in the grid. The grid is re-sized as necessary.
     *
     * @param sprite sprite to display
     * @throws IllegalArgumentException if sprite is null
     */
    public void loadSprite(Sprite sprite) throws IllegalArgumentException {
        if (sprite == null) {
            throw new IllegalArgumentException("sprite cannot be null");
        }

        int spriteWidth = sprite.getWidth();
        int spriteHeight = sprite.getHeight();

        this.initGrid(spriteWidth, spriteHeight);

        for (int x = 0; x < spriteWidth; ++x) {
            for (int y = 0; y < spriteHeight; ++y) {
                EnhancedColour colour = sprite.getColourAt(x, y);
                gridCells[x][y].setColour(colour);
            }
        }
    }
}
