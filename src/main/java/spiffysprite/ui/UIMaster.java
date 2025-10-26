package spiffysprite.ui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class UIMaster extends JFrame {
    public UIMaster() {
        initComponents();
        addComponents();
        finaliseComponents();
    }

    private void initComponents() {
        this.setResizable(true);
        this.setTitle("SpiffySprite");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelMaster = new JPanel(new MigLayout());
        spriteGrid = new SpriteGrid();
        colourPicker = new ColourPicker();

        panelMaster.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void addComponents() {
        this.add(panelMaster);

        panelMaster.add(spriteGrid, "grow");
        panelMaster.add(colourPicker, String.format("width %d, height %d", ColourPicker.COLOUR_PALETTE_WIDTH_PX,
                ColourPicker.COLOUR_PALETTE_HEIGHT_PX));
    }

    private void finaliseComponents() {
        this.pack();
        this.setVisible(true);
    }

    /**
     * Repaints all components within the application. This method can only be called from the Event Dispatch thread.
     */
    static void refreshGraphics () {
        if (!SwingUtilities.isEventDispatchThread()) {
            throw new RuntimeException("Can only call refreshGraphics from Event Dispatch thread!");
        }

        panelMaster.repaint();
        panelMaster.validate();
    }

    protected static JPanel panelMaster;
    protected static JTabbedPane tabbedPaneSpriteGrid;
    protected static ColourPicker colourPicker;
    protected static SpriteGrid spriteGrid;
}
