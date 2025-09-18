package spiffysprite.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TransparencyPanel extends JPanel {
    public static final int TRANSPARENCY_BACKGROUND_SQUARE_DIMENSION_PX = 2;
    public static final Color TRANSPARENCY_BACKGROUND_COLOUR_0 = Color.WHITE;
    public static final Color TRANSPARENCY_BACKGROUND_COLOUR_1 = Color.LIGHT_GRAY;
    private BufferedImage backgroundImage;
    private Color colour;

    public TransparencyPanel(int widthPx, int heightPx) {
        backgroundImage = new BufferedImage(widthPx, heightPx, BufferedImage.TYPE_4BYTE_ABGR);
        colour = new Color(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public setColour(float h, float s, float b, float a) {
        if (h < 0.0 || h > 1.0) {
            throw new RuntimeException(String.format("Invalid hue: %f", h));
        }

        if (s < 0.0 || s > 1.0) {
            throw new RuntimeException(String.format("Invalid saturation: %f", s));
        }

        if (b < 0.0 || b > 1.0) {
            throw new RuntimeException(String.format("Invalid brightness: %f", b));
        }

        if (a < 0.0 || a > 1.0) {
            throw new RuntimeException(String.format("Invalid alpha: %a", a));
        }

        colour = new Color(h, s, b, a);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
