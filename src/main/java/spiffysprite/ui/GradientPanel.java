package spiffysprite.ui;

import spiffysprite.enums.GenericOrientation;
import spiffysprite.models.HSBAColour;
import spiffysprite.utils.ColourUtils;

import java.awt.image.BufferedImage;

public class GradientPanel extends CustomBackgroundPanel {
    private final GenericOrientation orientation;
    private int widthPx, heightPx;
    private HSBAColour colour0, colour1;

    public GradientPanel(HSBAColour colour0, HSBAColour colour1, GenericOrientation orientation) {
        super();

        this.colour0 = colour0;
        this.colour1 = colour1;
        this.orientation = orientation;
    }

    public void setColours(HSBAColour colour0, HSBAColour colour1) {
        float[] hsbaVals0 = colour0.getHSBAVals();
        float[] hsbaVals1 = colour1.getHSBAVals();
        float hueStep = Math.abs(hsbaVals1[0] - hsbaVals0[0]) / (float) super.widthPx;
        float saturationStep = Math.abs(hsbaVals1[1] - hsbaVals0[1]) / (float) super.widthPx;
        float brightnessStep = Math.abs(hsbaVals1[2] - hsbaVals0[2]) / (float) super.widthPx;
        float alphaStep = Math.abs(hsbaVals1[3] - hsbaVals0[3]) / (float) super.widthPx;

        switch (orientation) {
            case HORIZONTAL -> {
                for (int x = 0; x < super.widthPx; ++x) {
                    HSBAColour gradientColour = new HSBAColour(
                            hsbaVals0[0] + (hueStep * x),
                            hsbaVals0[1] + (saturationStep * x),
                            hsbaVals0[2] + (brightnessStep * x),
                            hsbaVals0[3] + (alphaStep * x)
                    );
                    for (int y = 0; y < super.heightPx; ++y) {
                        HSBAColour transparencyBackgroundColour = ColourUtils.getTransparencyBackgroundColourAt(x, y);
                        HSBAColour combinedColour = ColourUtils.combineColours(gradientColour,
                                transparencyBackgroundColour, gradientColour.getAlpha());
                        backgroundImage.setRGB(x, y, combinedColour.toRGB());
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
