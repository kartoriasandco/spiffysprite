package spiffysprite.ui.workspacepanel;

import spiffysprite.models.Sprite;
import spiffysprite.ui.spritegrid.SpriteGrid;
import spiffysprite.utils.ErrorUtils;
import spiffysprite.utils.FileUtils;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ListenerMouseSaveButton extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent me) {
        Sprite activeSprite = SpriteGrid.toSprite();

        try {
            FileUtils.saveImage(activeSprite, WorkspacePanel.getWorkspaceFilePath(), WorkspacePanel.getFileName());
        } catch (IOException | NullPointerException e) {
            ErrorUtils.handleException(e);
        }
    }
}
