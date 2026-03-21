package spiffysprite.ui.workspacepanel;

import spiffysprite.utils.ErrorUtils;
import spiffysprite.convenienceclasses.DocumentAdapter;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class DocumentListenerFileName extends DocumentAdapter {
    @Override
    public void changedUpdate(DocumentEvent de) {
        Document document = de.getDocument();
        try {
            WorkspacePanel.setFileName(document.getText(0, document.getLength()));
        } catch (BadLocationException ble) {
            ErrorUtils.displayException(ble);
        }
    }
}
