package spiffysprite.ui.palettepanel;

import net.miginfocom.swing.MigLayout;
import spiffysprite.models.EnhancedColour;
import spiffysprite.ui.TransparencyPanel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

import static spiffysprite.ui.palettepanel.EnumPalettePanelStrings.*;

public class PalettePanel extends JPanel {
    private static final int PALETTE_ROWS = 4;
    private static final int PALETTE_COLUMNS = 8;
    public static final int MAXIMUM_PALETTE_COLOURS = PALETTE_COLUMNS * PALETTE_ROWS;
    private static final Dimension FAVOURITE_COLOUR_PANEL_DIMENSION = new Dimension(32, 32);
    private static final Border SELECTED_BORDER = BorderFactory.createBevelBorder(BevelBorder.LOWERED);
    private static final Border DEFAULT_BORDER = BorderFactory.createEtchedBorder();
    private ArrayList<EnhancedColour> paletteColours;
    private ArrayList<TransparencyPanel> paletteColoursPanels;
    private TransparencyPanel selectedCell;
    private JButton buttonClearSelectedCell;
    private JButton buttonEditPalette;
    private JButton buttonSaveActiveColour;
    private JPanel panelButtons;
    private int selectedPanelIndex;
    private boolean isEditModeEnabled;

    public PalettePanel() {
        super(new MigLayout("gapy 0, gapx 0"));
        isEditModeEnabled = false;
        paletteColours = new ArrayList<>();
        paletteColoursPanels = new ArrayList<>();
        initComponents();
    }

    private void initComponents() {
        this.setBorder(BorderFactory.createTitledBorder(BORDER_TITLE.text));

        for (int i = 0; i < PALETTE_ROWS; ++i) {
            for (int j = 0; j < PALETTE_COLUMNS; ++j) {
                TransparencyPanel panel = new TransparencyPanel(EnhancedColour.fromAHSB(0.0f, 0.0f, 0.0f, 0.0f));
                paletteColoursPanels.add(panel);
                panel.setBorder(DEFAULT_BORDER);
                panel.addMouseListener(new ListenerMousePaletteCell(this));

                String formatString = (j == PALETTE_COLUMNS - 1) ?
                        String.format("width %fpx, height %fpx, wrap", FAVOURITE_COLOUR_PANEL_DIMENSION.getWidth(),
                                FAVOURITE_COLOUR_PANEL_DIMENSION.getHeight()) :
                        String.format("width %fpx, height %fpx", FAVOURITE_COLOUR_PANEL_DIMENSION.getWidth(),
                                FAVOURITE_COLOUR_PANEL_DIMENSION.getHeight());

                this.add(panel, formatString);
            }
        }

        buttonClearSelectedCell = new JButton(CLEAR_SELECTED_CELL.text);
        buttonEditPalette = new JButton(EDIT_PALETTE_BUTTON.text);
        buttonSaveActiveColour = new JButton(SAVE_ACTIVE_COLOUR.text);
        panelButtons = new JPanel(new MigLayout("insets 0, gapy 0, gapx 0"));

        buttonClearSelectedCell.setEnabled(false);
        buttonClearSelectedCell.addMouseListener(new ListenerMouseClearButton(this));
        buttonSaveActiveColour.setEnabled(false);
        buttonSaveActiveColour.addMouseListener(new ListenerMouseSaveButton(this));
        buttonEditPalette.addMouseListener(new ListenerMouseEditButton(this));

        this.add(panelButtons, "span, width 100%");
        panelButtons.add(buttonSaveActiveColour, "width 50%");
        panelButtons.add(buttonClearSelectedCell, "width 50%, wrap");
        panelButtons.add(buttonEditPalette, "span, width 100%");
    }

    void selectPaletteCell(TransparencyPanel cell) {
        if (selectedCell != null) {
            selectedCell.setBorder(DEFAULT_BORDER);
        }

        cell.setBorder(SELECTED_BORDER);
        selectedCell = cell;
    }

    void editPalette() {
        isEditModeEnabled = true;
        buttonClearSelectedCell.setEnabled(true);
        buttonSaveActiveColour.setEnabled(true);
        buttonEditPalette.setText(SAVE_PALETTE_BUTTON.text);
        selectedCell = paletteColoursPanels.get(0);
        selectedCell.setBorder(SELECTED_BORDER);
    }

    void savePalette() {
        isEditModeEnabled = false;
        buttonClearSelectedCell.setEnabled(false);
        buttonSaveActiveColour.setEnabled(false);
        buttonEditPalette.setText(EDIT_PALETTE_BUTTON.text);
        selectedCell.setBorder(DEFAULT_BORDER);
        selectedCell = null;
    }

    void saveActiveColour(EnhancedColour colour) {
        if (isEditModeEnabled) {
            selectedCell.setColour(colour);
        } else {
            throw new RuntimeException("Colour cannot be saved to palette while editing is not enabled");
        }
    }

    TransparencyPanel getSelectedCell() {
        return selectedCell;
    }

    boolean isEditModeEnabled() {
        return isEditModeEnabled;
    }
}
