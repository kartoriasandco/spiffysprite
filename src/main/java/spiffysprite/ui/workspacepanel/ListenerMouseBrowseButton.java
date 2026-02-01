package spiffysprite.ui.workspacepanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListenerMouseBrowseButton extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent me) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnState = fileChooser.showOpenDialog(null);
        if (returnState == JFileChooser.APPROVE_OPTION) {
            WorkspacePanel.setWorkspaceFilePath(fileChooser.getSelectedFile().getPath());
        }
    }
}
