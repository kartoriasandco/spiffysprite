package spiffysprite.ui;

import spiffysprite.enums.ColourSliderOrientation;

import javax.swing.*;

public class ColourSlider extends JPanel {
    private JSlider slider;
    private TransparencyPanel colourPanel;

    public ColourSlider(ColourSliderOrientation orientation, int minValue, int maxValue, int interval, int initialValue,
                        JComponent container) {
        slider = new JSlider(orientation.value, minValue, maxValue, initialValue);
        slider.setMajorTickSpacing(interval);
    }
}
