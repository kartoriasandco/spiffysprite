package spiffysprite.ui;

import spiffysprite.enums.EnumGenericOrientation;
import spiffysprite.models.EnhancedColour;
import spiffysprite.utils.ColourUtils;

import java.awt.image.BufferedImage;

public class GradientPanel extends CustomBackgroundPanel {
    private final EnumGenericOrientation orientation;
    private int widthPx, heightPx;
    private EnhancedColour colour0, colour1;

    public GradientPanel(EnhancedColour colour0, EnhancedColour colour1, EnumGenericOrientation orientation) {
        super();

        this.colour0 = colour0;
        this.colour1 = colour1;
        this.orientation = orientation;
    }

    public void setColours(EnhancedColour colour0, EnhancedColour colour1) {
        float hueStep = Math.abs(colour1.hue - colour0.hue) / (float) super.widthPx;
        float saturationStep = Math.abs(colour1.saturation - colour0.saturation) / (float) super.widthPx;
        float brightnessStep = Math.abs(colour1.brightness - colour0.brightness) / (float) super.widthPx;
        float alphaStep = Math.abs(colour1.alpha - colour0.alpha) / (float) super.widthPx;

        switch (orientation) {
            case HORIZONTAL -> {
                for (int x = 0; x < super.widthPx; ++x) {
                    EnhancedColour gradientColour = EnhancedColour.fromAHSB(
                            colour0.hue + (hueStep * x),
                            colour0.saturation + (saturationStep * x),
                            colour0.brightness + (brightnessStep * x),
                            colour0.alpha + (alphaStep * x)
                    );
                    for (int y = 0; y < super.heightPx; ++y) {
                        EnhancedColour transparencyBackgroundColour = ColourUtils.getTransparencyBackgroundColourAt(x, y);
                        EnhancedColour combinedColour = ColourUtils.combineColours(gradientColour,
                                transparencyBackgroundColour, gradientColour.alpha);
                        backgroundImage.setRGB(x, y, EnhancedColour.toColor(combinedColour).getRGB());
                    }
                }
            }
            case VERTICAL ->  {
                // TODO
            }
        }
    }

    @Override
    protected void renderBackground(int widthPx, int heightPx) {
        backgroundImage = new BufferedImage(widthPx, heightPx, BufferedImage.TYPE_INT_RGB);
        setColours(colour0, colour1);
        UIMaster.refreshGraphics();
    }
}
