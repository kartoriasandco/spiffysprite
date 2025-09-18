package spiffysprite.ui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

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
    }

    private void addComponents() {
        this.add(panelMaster);
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
    protected static SpriteGrid spriteGrid;
}
