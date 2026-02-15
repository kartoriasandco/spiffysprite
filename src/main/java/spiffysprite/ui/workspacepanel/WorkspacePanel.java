package spiffysprite.ui.workspacepanel;

import net.miginfocom.swing.MigLayout;
import spiffysprite.ui.UIMaster;

import javax.swing.*;

public class WorkspacePanel extends JPanel {
    private static String workspaceFilePath;
    private static String fileName;
    private static JPanel panelButtons;
    private static JPanel panelTextFields;
    private static JTextField filePathTextField;
    private static JTextField fileNameTextField;
    private static JButton browseButton;
    private static JButton saveButton;

    public WorkspacePanel() {
        super(new MigLayout("fillx", "[][][grow, fill]1px[150px, fill]", ""));
        initComponents();
        addComponents();
    }

    private void initComponents() {
        filePathTextField = new JTextField();
        fileNameTextField = new JTextField();
        browseButton = new JButton("Browse for Workspace");
        saveButton = new JButton("Save Sprite");

        this.setBorder(BorderFactory.createTitledBorder("Workspace"));
        filePathTextField.setEditable(false);
        filePathTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        browseButton.addMouseListener(new ListenerMouseBrowseButton());
        saveButton.addMouseListener(new ListenerMouseSaveButton());
    }

    private void addComponents() {
        this.add(browseButton);
        this.add(saveButton);
        this.add(filePathTextField, "height 27px");
        this.add(fileNameTextField, "height 27px");
    }

    static void setWorkspaceFilePath(String filePath) {
        System.out.println(SwingUtilities.isEventDispatchThread());
        workspaceFilePath = filePath;
        filePathTextField.setText(workspaceFilePath + "/");
        UIMaster.refreshGraphics();
    }

    public static String getWorkspaceFilePath() {
        return workspaceFilePath;
    }

    public static String getFileName() { return fileName; }
}
