package spiffysprite.ui.spritespanel;

import spiffysprite.models.Sprite;
import spiffysprite.utils.FileUtils;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

import static spiffysprite.enums.EnumFileExtensions.*;
import static spiffysprite.enums.EnumWorkspaceSubdirectories.*;

public class SpritesPanel extends JPanel {
    //private static JList<SpriteListCell> listSprites;
    private static JList<String> listSprites;
    private static JScrollPane scrollPaneSprites;

    public SpritesPanel() {
        populateSpriteList();
        initComponents();
        addComponents();
    }

    private void initComponents() {
        listSprites = new JList<>();
        //listSprites.setCellRenderer(new SpriteListCellRenderer());
        //scrollPaneSprites = new JScrollPane(listSprites);
    }

    private void addComponents() {
        //this.add(scrollPaneSprites);
        this.add(listSprites);
    }

    private void populateSpriteList() {
        ArrayList<File> listFiles = FileUtils.getFilesFromWorkspace(PNG, SPRITES);
        ArrayList<String> fileNameList = new ArrayList<>();
        listFiles.forEach((file) -> fileNameList.add(file.getName()));
        listSprites = new JList<>(fileNameList.toArray(new String[]{}));
    }
}
