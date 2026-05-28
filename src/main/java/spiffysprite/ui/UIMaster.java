package spiffysprite.ui;

import net.miginfocom.swing.MigLayout;
import spiffysprite.ui.spritegrid.SpriteGrid;
import spiffysprite.ui.spritespanel.SpritesPanel;
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
        panelMaster = new JPanel(new MigLayout("", "[grow][]", ""));
        spriteGrid = new SpriteGrid();
        scrollPaneSpriteGrid = new JScrollPane(spriteGrid);
        colourPicker = new ColourPicker();
        workspacePanel = new WorkspacePanel();
        scrollPaneColourPicker = new JScrollPane(colourPicker);
        spritesPanel = new SpritesPanel();

        panelMaster.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        scrollPaneColourPicker.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneColourPicker.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    private void addComponents() {
        this.add(panelMaster);

        panelMaster.add(workspacePanel, "width 100%, dock north");
        panelMaster.add(scrollPaneSpriteGrid, "growprio 100, shrinkprio 100");
        panelMaster.add(scrollPaneColourPicker, "shrinkprio 0, height 100%, dock east");
        panelMaster.add(spritesPanel, "dock west");
    }

    private void finaliseComponents() {
        this.pack();
        this.setVisible(true);
    }

    /**
     * Repaints all components within the application.
     */
    public static void refreshGraphics () {
        SwingUtilities.invokeLater(() -> {
            panelMaster.repaint();
            panelMaster.validate();
        });
    }

    protected static JPanel panelMaster;
    protected static JScrollPane scrollPaneColourPicker;
    protected static JTabbedPane tabbedPaneSpriteGrid;
    protected static ColourPicker colourPicker;
    protected static JScrollPane scrollPaneSpriteGrid;
    protected static SpriteGrid spriteGrid;
    protected static WorkspacePanel workspacePanel;
    protected static SpritesPanel spritesPanel;
}
