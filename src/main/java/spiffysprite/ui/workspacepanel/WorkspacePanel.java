package spiffysprite.ui.workspacepanel;

import net.miginfocom.swing.MigLayout;
import spiffysprite.ui.UIMaster;

import javax.swing.*;

public class WorkspacePanel extends JPanel {
    private static String workspaceFilePath;
    private static JTextField filePathTextField;
    private static JButton browseButton;

    public WorkspacePanel() {
        super(new MigLayout());
        initComponents();
        addComponents();
    }

    private void initComponents() {
        filePathTextField = new JTextField();
        browseButton = new JButton("Browse");

        this.setBorder(BorderFactory.createTitledBorder("Workspace"));
        filePathTextField.setEditable(false);
        browseButton.addMouseListener(new ListenerMouseBrowseButton());
    }

    private void addComponents() {
        this.add(browseButton, "grow 0");
        this.add(filePathTextField, "height 25px, width 100%");
    }

    static void setWorkspaceFilePath(String filePath) {
        System.out.println(SwingUtilities.isEventDispatchThread());
        workspaceFilePath = filePath;
        filePathTextField.setText(workspaceFilePath);
        UIMaster.refreshGraphics();
    }

    public static String getWorkspaceFilePath() {
        return workspaceFilePath;
    }
}
