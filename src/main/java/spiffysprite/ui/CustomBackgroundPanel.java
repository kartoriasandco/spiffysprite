package spiffysprite.ui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

public class CustomBackgroundPanel extends JPanel {
    protected int heightPx, widthPx;
    protected MigLayout migLayout;
    protected BufferedImage backgroundImage;

    public CustomBackgroundPanel() {
        super();
        this.addComponentListener(new ComponentResizedListener());
    }

    public CustomBackgroundPanel(MigLayout migLayout) {
        super(migLayout);
        this.migLayout = migLayout;
        this.addComponentListener(new ComponentResizedListener());
    }

    protected void renderBackground(int widthPx, int heightPx) {
        backgroundImage = new BufferedImage(widthPx, heightPx, BufferedImage.TYPE_INT_RGB);
        UIMaster.refreshGraphics();
    }

    protected void renderBackground(BufferedImage image) {
        backgroundImage = image;
        UIMaster.refreshGraphics();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }

    class ComponentResizedListener extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent ce) {
            super.componentResized(ce);
            Component c = ce.getComponent();
            widthPx = c.getWidth();
            heightPx = c.getHeight();
            renderBackground(widthPx, heightPx);
        }
    }
}