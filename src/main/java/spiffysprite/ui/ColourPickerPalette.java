package spiffysprite.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ColourPickerPalette extends JPanel {
    static final int WIDTH_PX = 255;
    static final int HEIGHT_PX = 100;
    private static BufferedImage backgroundImage;

    public ColourPickerPalette() {
        initPalette();
    }

    private void initPalette() {
        backgroundImage = new BufferedImage(WIDTH_PX, HEIGHT_PX, BufferedImage.TYPE_INT_RGB);
        float hueStep = 1.0f / (float) WIDTH_PX;
        float saturationStep = 1.0f / (float) HEIGHT_PX;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
        super.paintComponent(g);
    }
}
