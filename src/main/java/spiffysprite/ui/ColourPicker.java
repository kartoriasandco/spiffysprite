package spiffysprite.ui;

import net.miginfocom.swing.MigLayout;
import spiffysprite.enums.EnumColourComponents;
import spiffysprite.enums.EnumDefaultColours;
import spiffysprite.enums.EnumGenericOrientation;
import spiffysprite.models.EnhancedColour;
import spiffysprite.ui.coloursliderpanel.ColourSliderPanel;
import spiffysprite.ui.palettepanel.PalettePanel;

import javax.swing.*;

public class ColourPicker extends JPanel {
    static final int WIDTH_PX = 256;
    static final int HEIGHT_PX = 50;
    private static EnhancedColour activeColour = EnumDefaultColours.RED.enhancedColour;
    private static JLabel labelActiveColour;
    private static TransparencyPanel panelActiveColour;
    private static ColourSliderPanel huePanel;
    private static ColourSliderPanel saturationPanel;
    private static ColourSliderPanel brightnessPanel;
    private static ColourSliderPanel alphaPanel;
    private static PalettePanel palettePanel;

    public ColourPicker() {
        super(new MigLayout());
        initColourPicker();
    }

    private void initColourPicker() {
        labelActiveColour = new JLabel("Active Colour:");
        panelActiveColour = new TransparencyPanel();

        var colour0 = EnhancedColour.fromAHSB(0.0f, 1.0f, 1.0f, 1.0f);
        var colour1 = EnhancedColour.fromAHSB(1.0f, 1.0f, 1.0f, 1.0f);

        System.out.printf(
                "colour0 hue: 0x%s, colour1 hue: 0x%s\n",
                Integer.toHexString(colour0.getRGB()).toUpperCase().substring(2, 4),
                Integer.toHexString(colour1.getRGB()).toUpperCase().substring(2, 4)
        );


        huePanel = new ColourSliderPanel(
                "Hue",
                EnhancedColour.fromAHSB(0.0f, 1.0f, 1.0f, 1.0f),
                EnhancedColour.fromAHSB(1.0f, 1.0f, 1.0f, 1.0f),
                EnumColourComponents.HUE,
                EnumGenericOrientation.HORIZONTAL,
                0,
                255,
                (int) (activeColour.hue * 255f),
                "HUE"
        );

        saturationPanel = new ColourSliderPanel(
                "Saturation",
                EnhancedColour.fromAHSB(0.0f, 0.0f, 1.0f, 1.0f),
                EnhancedColour.fromAHSB(0.0f, 1.0f, 1.0f, 1.0f),
                EnumColourComponents.SATURATION,
                EnumGenericOrientation.HORIZONTAL,
                0,
                255,
                (int) (activeColour.saturation * 255f)
        );

        brightnessPanel = new ColourSliderPanel(
                "Brightness",
                EnhancedColour.fromAHSB(0.0f, 1.0f, 0.0f, 1.0f),
                EnhancedColour.fromAHSB(0.0f, 1.0f, 1.0f, 1.0f),
                EnumColourComponents.BRIGHTNESS,
                EnumGenericOrientation.HORIZONTAL,
                0,
                255,
                (int) (activeColour.brightness * 255f)
        );

        alphaPanel = new ColourSliderPanel(
                "Alpha",
                EnhancedColour.fromAHSB(0.0f, 1.0f, 1.0f, 0.0f),
                EnhancedColour.fromAHSB(0.0f, 1.0f, 1.0f, 1.0f),
                EnumColourComponents.ALPHA,
                EnumGenericOrientation.HORIZONTAL,
                0,
                255,
                (int) (activeColour.alpha * 255f)
        );

        panelActiveColour.setBorder(BorderFactory.createEtchedBorder());
        panelActiveColour.setColour(activeColour);

        palettePanel = new PalettePanel();
        this.add(labelActiveColour, String.format("width %dpx, wrap", WIDTH_PX));
        this.add(panelActiveColour, String.format("height %dpx, width max(%d), wrap", HEIGHT_PX, WIDTH_PX));
        this.add(huePanel, String.format("width max(%d), wrap", WIDTH_PX));
        this.add(saturationPanel, String.format("width max(%d), wrap", WIDTH_PX));
        this.add(brightnessPanel, String.format("width max(%d), wrap", WIDTH_PX));
        this.add(alphaPanel, String.format("width max(%d), wrap", WIDTH_PX));
        this.add(palettePanel, String.format("width max(%d)", WIDTH_PX));
    }

    public static EnhancedColour getActiveColour() {
        return activeColour;
    }

    public static void setActiveColour(EnhancedColour colour) {
        activeColour = colour;
        panelActiveColour.setColour(colour);
        setHue(colour.hue);
        setSaturation(colour.saturation);
        setBrightness(colour.brightness);
        setAlpha(colour.alpha);
    }

    public static void setHue(float hue) {
        if (hue < 0.0f || hue > 1.0f) {
            throw new RuntimeException(String.format("Invalid hue: %f", hue));
        }

        activeColour = EnhancedColour.fromAHSB(hue, activeColour.saturation, activeColour.brightness, activeColour.alpha);
        panelActiveColour.setColour(activeColour);
        saturationPanel.setColours(
                EnhancedColour.fromAHSB(activeColour.hue, 0.0f, 1.0f, 1.0f),
                EnhancedColour.fromAHSB(activeColour.hue, 1.0f, 1.0f, 1.0f)
        );
        brightnessPanel.setColours(
                EnhancedColour.fromAHSB(activeColour.hue, 1.0f, 0.0f, 1.0f),
                EnhancedColour.fromAHSB(activeColour.hue, 1.0f, 1.0f, 1.0f)
        );
        alphaPanel.setColours(
                EnhancedColour.fromAHSB(activeColour.hue, 1.0f, 1.0f, 0.0f),
                EnhancedColour.fromAHSB(activeColour.hue, 1.0f, 1.0f, 1.0f)
        );
    }

    public static void setSaturation(float saturation) {
        if (saturation < 0.0f || saturation > 1.0f) {
            throw new RuntimeException(String.format("Invalid saturation: %f", saturation));
        }

        activeColour = EnhancedColour.fromAHSB(activeColour.hue, saturation, activeColour.brightness, activeColour.alpha);
        panelActiveColour.setColour(activeColour);
    }

    public static void setBrightness(float brightness) {
        if (brightness < 0.f || brightness > 1.0f) {
            throw new RuntimeException(String.format("Invalid brightness: %f", brightness));
        }

        activeColour = EnhancedColour.fromAHSB(activeColour.hue, activeColour.saturation, brightness, activeColour.alpha);
        panelActiveColour.setColour(activeColour);
    }

    public static void setAlpha(float alpha) {
        if (alpha < 0.f || alpha > 1.0f) {
            throw new RuntimeException(String.format("Invalid alpha: %f", alpha));
        }

        activeColour = EnhancedColour.fromAHSB(activeColour.hue, activeColour.saturation, activeColour.brightness, alpha);
        panelActiveColour.setColour(activeColour);
    }
}
