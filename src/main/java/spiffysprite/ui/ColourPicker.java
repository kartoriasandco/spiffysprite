package spiffysprite.ui;

import net.miginfocom.swing.MigLayout;
import spiffysprite.enums.ColourComponents;
import spiffysprite.enums.GenericOrientation;
import spiffysprite.models.HSBAColour;

import javax.swing.*;
import java.awt.*;

public class ColourPicker extends JPanel {
    static final int WIDTH_PX = 256;
    static final int HEIGHT_PX = 50;
    private static HSBAColour activeColour = new HSBAColour(1.0f, 1.0f, 1.0f, 1.0f);
    private static JLabel labelActiveColour;
    private static TransparencyPanel panelActiveColour;
    private static ColourSliderPanel huePanel;
    private static ColourSliderPanel saturationPanel;
    private static ColourSliderPanel brightnessPanel;
    private static ColourSliderPanel alphaPanel;

    public ColourPicker() {
        super(new MigLayout());
        initColourPicker();
    }

    private void initColourPicker() {
        labelActiveColour = new JLabel("Active Colour:");
        panelActiveColour = new TransparencyPanel();
        huePanel = new ColourSliderPanel(
                "Hue",
                new HSBAColour(0.0f, 1.0f, 1.0f, 1.0f),
                new HSBAColour(1.0f, 1.0f, 1.0f, 1.0f),
                ColourComponents.HUE,
                GenericOrientation.HORIZONTAL,
                0,
                255,
                (int) (activeColour.getHue() * 255f)
        );
        saturationPanel = new ColourSliderPanel(
                "Saturation",
                new HSBAColour(0.0f, 0.0f, 1.0f, 1.0f),
                new HSBAColour(0.0f, 1.0f, 1.0f, 1.0f),
                ColourComponents.SATURATION,
                GenericOrientation.HORIZONTAL,
                0,
                255,
                (int) (activeColour.getSaturation() * 255f)
        );

        brightnessPanel = new ColourSliderPanel(
                "Brightness",
                new HSBAColour(0.0f, 1.0f, 0.0f, 1.0f),
                new HSBAColour(0.0f, 1.0f, 1.0f, 1.0f),
                ColourComponents.BRIGHTNESS,
                GenericOrientation.HORIZONTAL,
                0,
                255,
                (int) (activeColour.getBrightness() * 255f)
        );

        alphaPanel = new ColourSliderPanel(
                "Alpha",
                new HSBAColour(0.0f, 1.0f, 1.0f, 0.0f),
                new HSBAColour(0.0f, 1.0f, 1.0f, 1.0f),
                ColourComponents.ALPHA,
                GenericOrientation.HORIZONTAL,
                0,
                255,
                (int) (activeColour.getAlpha() * 255f)
        );

        panelActiveColour.setBorder(BorderFactory.createEtchedBorder());
        panelActiveColour.setColour(activeColour);

        this.add(labelActiveColour, String.format("width %dpx, wrap", WIDTH_PX));
        this.add(panelActiveColour, String.format("height %dpx, width max(%d), wrap", HEIGHT_PX, WIDTH_PX));
        this.add(huePanel, String.format("width max(%d), wrap", WIDTH_PX));
        this.add(saturationPanel, String.format("width max(%d), wrap", WIDTH_PX));
        this.add(brightnessPanel, String.format("width max(%d), wrap", WIDTH_PX));
        this.add(alphaPanel, String.format("width max(%d)", WIDTH_PX));
    }

    public static HSBAColour getActiveColour() {
        return activeColour;
    }

    public static void setActiveColour(HSBAColour colour) {
        activeColour = colour;
        panelActiveColour.setColour(colour);

        setHue(colour.getHue());
        setSaturation(colour.getSaturation());
        setBrightness(colour.getBrightness());
        setAlpha(colour.getAlpha());
    }

    public static void setHue(float hue) {
        if (hue < 0.f || hue > 1.0f) {
            throw new RuntimeException(String.format("Invalid hue: %f", hue));
        }

        float[] hsbaVals = activeColour.getHSBAVals();
        activeColour = new HSBAColour(hue, hsbaVals[1], hsbaVals[2], hsbaVals[3]);
        panelActiveColour.setColour(activeColour);
    }

    public static void setSaturation(float saturation) {
        if (saturation < 0.f || saturation > 1.0f) {
            throw new RuntimeException(String.format("Invalid saturation: %f", saturation));
        }

        float[] hsbaVals = activeColour.getHSBAVals();
        activeColour = new HSBAColour(hsbaVals[0], saturation, hsbaVals[2], hsbaVals[3]);
        panelActiveColour.setColour(activeColour);
    }

    public static void setBrightness(float brightness) {
        if (brightness < 0.f || brightness > 1.0f) {
            throw new RuntimeException(String.format("Invalid brightness: %f", brightness));
        }

        float[] hsbaVals = activeColour.getHSBAVals();
        activeColour = new HSBAColour(hsbaVals[0], hsbaVals[1], brightness, hsbaVals[3]);
        panelActiveColour.setColour(activeColour);
    }

    public static void setAlpha(float alpha) {
        if (alpha < 0.f || alpha > 1.0f) {
            throw new RuntimeException(String.format("Invalid alpha: %f", alpha));
        }

        float[] hsbaVals = activeColour.getHSBAVals();
        activeColour = new HSBAColour(hsbaVals[0], hsbaVals[1], hsbaVals[2], alpha);
        panelActiveColour.setColour(activeColour);
    }
}
