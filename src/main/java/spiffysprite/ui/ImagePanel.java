package spiffysprite.ui;

import java.awt.image.BufferedImage;

public class ImagePanel extends CustomBackgroundPanel {
    private final BufferedImage image;

    public ImagePanel(BufferedImage image) {
        this.image = image;
        super(image);
    }
}
