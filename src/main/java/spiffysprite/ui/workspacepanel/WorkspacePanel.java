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
    private static JButton exportImageButton;
    private static JButton saveDataButton;

    public WorkspacePanel() {
        super(new MigLayout("fillx", "[][fill, grow]", ""));
        initComponents();
        addComponents();
    }

    private void initComponents() {
        panelButtons = new JPanel(new MigLayout());
        panelTextFields = new JPanel(new MigLayout("fillx", "[fill, grow]1px[150px, fill]", ""));
        filePathTextField = new JTextField();
        fileNameTextField = new JTextField();
        browseButton = new JButton("Browse for Workspace");
        exportImageButton = new JButton("Export as PNG");
        saveDataButton = new JButton("Save Sprite");

        this.setBorder(BorderFactory.createTitledBorder("Workspace"));
        filePathTextField.setEditable(false);
        filePathTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        fileNameTextField.getDocument().addDocumentListener(new DocumentListenerFileName());
        browseButton.addMouseListener(new ListenerMouseBrowseButton());
        exportImageButton.addMouseListener(new ListenerMouseExportButton());
        saveDataButton.addMouseListener(new ListenerMouseSaveButton());
    }

    private void addComponents() {
        this.add(panelButtons);
        this.add(panelTextFields);
        panelButtons.add(browseButton, "span 2, wrap");
        panelButtons.add(exportImageButton);
        panelButtons.add(saveDataButton);
        panelTextFields.add(filePathTextField, "height 27px");
        panelTextFields.add(fileNameTextField, "height 27px");
    }

    static void setWorkspaceFilePath(String filePath) {
        workspaceFilePath = filePath;
        filePathTextField.setText(workspaceFilePath + "/");
        UIMaster.refreshGraphics();
    }

    public static String getWorkspaceFilePath() {
        return workspaceFilePath;
    }

    public static String getFileName() { return fileName; }

    public static void setFileName(String newFileName) { fileName = newFileName; }
}
