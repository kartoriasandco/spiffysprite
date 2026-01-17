package spiffysprite.ui.coloursliderpanel;

import spiffysprite.enums.EnumColourComponents;
import spiffysprite.models.EnhancedColour;
import spiffysprite.ui.ColourPicker;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ListenerChangeSlider implements ChangeListener { ;
    private final EnumColourComponents colourComponent;
    private final JTextField valueTextField;

    public ListenerChangeSlider(EnumColourComponents colourComponent, JTextField valueTextField) {
        this.colourComponent = colourComponent;
        this.valueTextField = valueTextField;
    }

    @Override
    public void stateChanged(ChangeEvent ce) {
        EnhancedColour activeColour = ColourPicker.getActiveColour();
        JSlider slider = (JSlider) ce.getSource();
        int sliderValue = slider.getValue();
        float colourComponentValue = (float) sliderValue / 255.0f;
        valueTextField.setText(Integer.toString(sliderValue));

        EnhancedColour newColour = switch (colourComponent) {
            case HUE -> EnhancedColour.fromAHSB(
                colourComponentValue,
                activeColour.saturation,
                activeColour.brightness,
                activeColour.alpha
            );
            case SATURATION -> EnhancedColour.fromAHSB(
                activeColour.hue,
                colourComponentValue,
                activeColour.brightness,
                activeColour.alpha
            );
            case BRIGHTNESS -> EnhancedColour.fromAHSB(
                activeColour.hue,
                activeColour.saturation,
                colourComponentValue,
                activeColour.alpha
            );
            case ALPHA -> EnhancedColour.fromAHSB(
                activeColour.hue,
                activeColour.saturation,
                activeColour.brightness,
                colourComponentValue
            );
        };

        ColourPicker.setActiveColour(newColour);
    }
}
