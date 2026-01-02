package spiffysprite.ui;

import net.miginfocom.swing.MigLayout;
import spiffysprite.records.HSBAColour;
import spiffysprite.records.RGBAColour;
import spiffysprite.utils.ColourUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

public class TransparencyPanel extends JPanel {
    public static final int TRANSPARENCY_BACKGROUND_SQUARE_DIMENSION_PX = 4;
    public static final HSBAColour TRANSPARENCY_BACKGROUND_COLOUR_0 = HSBAColour.valueOf(Color.WHITE);
    public static final HSBAColour TRANSPARENCY_BACKGROUND_COLOUR_1 = HSBAColour.valueOf(Color.LIGHT_GRAY);
    private boolean isSelected = false;
    private int widthPx;
    private int heightPx;
    private BufferedImage backgroundImage;
    private HSBAColour colour;

    public TransparencyPanel() {
        this(new HSBAColour(0.0f, 0.0f, 0.0f, 0.0f));
    }

    public TransparencyPanel(HSBAColour colour) {
        super(new MigLayout());
        setColour(colour);
        this.addComponentListener(new ComponentResizedListener(this));
    }

    public HSBAColour getColour() { return this.colour; }

    /**
     * Sets the colour of this panel.
     *
     * @param colour background colour
     */
    public void setColour(HSBAColour colour) {
        this.colour = colour;
        for (int x = 0; x < widthPx; ++x) {
            for (int y = 0; y < heightPx; ++y) {
                final HSBAColour transparencyBackgroundColour = ColourUtils.getTransparencyBackgroundColourAt(x, y);
                final HSBAColour combinedColour = ColourUtils.combineColours(
                        transparencyBackgroundColour,
                        colour,
                        1.0f - this.colour.alpha()
                );

                backgroundImage.setRGB(x, y, HSBAColour.toColor(combinedColour).getRGB());
            }
        }

        UIMaster.refreshGraphics();
    }

    private void renderBackground(int widthPx, int heightPx) {
        backgroundImage = new BufferedImage(widthPx, heightPx, BufferedImage.TYPE_INT_ARGB);
        setColour(colour);
        UIMaster.refreshGraphics();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
    }

    class ComponentResizedListener extends ComponentAdapter {
        JComponent component;
        public ComponentResizedListener(JComponent component) {
            this.component = component;
        }

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
