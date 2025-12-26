package spiffysprite.ui;

import net.miginfocom.swing.MigLayout;
import spiffysprite.enums.ColourComponents;
import spiffysprite.enums.GenericOrientation;
import spiffysprite.models.HSBAColour;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColourSliderPanel extends JPanel {
    private final JSlider slider;
    private final GradientPanel gradientPanel;
    private final JTextField textField;

    public ColourSliderPanel(
            String labelText,
            HSBAColour colour0,
            HSBAColour colour1,
            ColourComponents colourComponent,
            GenericOrientation orientation,
            int minValue,
            int maxValue,
            int initialValue
    ) {
        super(new MigLayout("insets 1px, gapy 2px"));

        slider = new JSlider(orientation.value, minValue, maxValue, initialValue);
        gradientPanel = new GradientPanel(colour0, colour1, orientation);
        textField = new JTextField(initialValue);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);

        this.setBorder(BorderFactory.createTitledBorder(labelText));
        slider.addChangeListener(new SliderValueChangedListener(colourComponent));
        gradientPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        textField.setText(Integer.toString(initialValue));

        switch (orientation) {
            case HORIZONTAL -> {
                this.add(slider, "grow, wrap");
                this.add(gradientPanel, "grow, wrap");
                this.add(textField, "grow");
            }
            case VERTICAL -> {
                this.add(slider);
                this.add(gradientPanel);
            }
        }
    }

    class SliderValueChangedListener implements ChangeListener {
        private final ColourComponents colourComponent;

        public SliderValueChangedListener(ColourComponents colourComponent) {
            this.colourComponent = colourComponent;
        }

        @Override
        public void stateChanged(ChangeEvent ce) {
            JSlider slider = (JSlider) ce.getSource();
            int sliderValue = slider.getValue();
            float colourComponentValue = (float) sliderValue / 255.0f;
            textField.setText(Integer.toString(sliderValue));

            switch (colourComponent) {
                case HUE -> {
                    ColourPicker.setHue(colourComponentValue);
                }
                case SATURATION -> {
                    ColourPicker.setSaturation(colourComponentValue);
                }
                case BRIGHTNESS -> {
                    ColourPicker.setBrightness(colourComponentValue);
                }
                case ALPHA -> {
                    ColourPicker.setAlpha(colourComponentValue);
                }
            }
        }
    }
}
