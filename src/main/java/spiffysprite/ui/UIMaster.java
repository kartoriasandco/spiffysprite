package spiffysprite.ui;

import net.miginfocom.swing.MigLayout;
import spiffysprite.ui.spritegrid.SpriteGrid;
import spiffysprite.ui.workspacepanel.WorkspacePanel;
import spiffysprite.utils.ErrorUtils;

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
        workspacePanel = new WorkspacePanel();
        scrollPaneColourPicker = new JScrollPane(colourPicker);

        panelMaster.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        scrollPaneColourPicker.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneColourPicker.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    private void addComponents() {
        this.add(panelMaster);

        panelMaster.add(workspacePanel, "width 100%, dock north");
        panelMaster.add(spriteGrid, "growprio 100");
        panelMaster.add(scrollPaneColourPicker, "dock east");
    }

    private void finaliseComponents() {
        this.pack();
        this.setVisible(true);
    }

    /**
     * Repaints all components within the application. This method can only be called from the Event Dispatch thread.
     */
    public static void refreshGraphics () {
        if (!SwingUtilities.isEventDispatchThread()) {
            throw new RuntimeException("Can only call refreshGraphics from Event Dispatch thread!");
        }

        panelMaster.repaint();
        panelMaster.validate();
    }

    protected static JPanel panelMaster;
    protected static JScrollPane scrollPaneColourPicker;
    protected static JTabbedPane tabbedPaneSpriteGrid;
    protected static ColourPicker colourPicker;
    protected static SpriteGrid spriteGrid;
    protected static WorkspacePanel workspacePanel;
}
