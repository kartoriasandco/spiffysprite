package spiffysprite.ui.spritespanel;

import spiffysprite.models.Sprite;
import spiffysprite.ui.TransparencyPanel;

import javax.swing.*;

public class SpriteListCell extends JPanel {
    private JLabel labelName;
    private TransparencyPanel panelSpriteImage;
    private String name;
    private final Sprite sprite;

    public SpriteListCell(String name, Sprite sprite) {
        this.name = name;
        this.sprite = sprite;
        initComponents();
        addComponents();
    }

    private void initComponents() {
        labelName = new JLabel(name);
        panelSpriteImage = new TransparencyPanel();
    }

    private void addComponents() {
        this.add(labelName);
        this.add(panelSpriteImage);
    }

    public String getName() {
        return name;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setName(String name) {
        this.name = name;
    }
}
