package spiffysprite.ui.coloursliderpanel;

import net.miginfocom.swing.MigLayout;
import spiffysprite.enums.EnumColourComponents;
import spiffysprite.enums.EnumGenericOrientation;
import spiffysprite.records.HSBAColour;
import spiffysprite.ui.GradientPanel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class ColourSliderPanel extends JPanel {
    private final JSlider slider;
    private final GradientPanel gradientPanel;
    private final JTextField textField;

    public ColourSliderPanel(
            String labelText,
            HSBAColour colour0,
            HSBAColour colour1,
            EnumColourComponents colourComponent,
            EnumGenericOrientation orientation,
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
        slider.addChangeListener(new ListenerChangeSlider(colourComponent, textField));
        gradientPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        textField.setText(Integer.toString(initialValue));

        switch (orientation) {
            case HORIZONTAL -> {
                this.add(slider, "width 100%, wrap");
                this.add(gradientPanel, "grow, wrap");
                this.add(textField, "grow");
            }
            case VERTICAL -> {
                this.add(slider);
                this.add(gradientPanel);
            }
        }
    }

    public void setColours(HSBAColour colour0, HSBAColour colour1) {
        gradientPanel.setColours(colour0, colour1);
    }
}
