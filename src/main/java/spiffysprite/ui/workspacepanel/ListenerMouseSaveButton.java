package spiffysprite.ui.workspacepanel;

import spiffysprite.models.Sprite;
import spiffysprite.ui.spritegrid.SpriteGrid;
import spiffysprite.utils.FileUtils;
import spiffysprite.utils.JSONUtils;
import spiffysprite.utils.jsonparser.JSONObject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListenerMouseSaveButton extends MouseAdapter {
    @Override
    public void mousePressed(MouseEvent me) {
        Sprite activeSprite = SpriteGrid.toSprite();
        JSONObject spriteJSON = JSONUtils.imageToJSON(activeSprite);
        System.out.println(spriteJSON.toPrettyString());
    }
}
