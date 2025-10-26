package spiffysprite.enums;

import javax.swing.*;

public enum ColourSliderOrientation {
    HORIZONTAL(SwingConstants.HORIZONTAL),
    VERTICAL(SwingConstants.VERTICAL);

    public final int value;

    ColourSliderOrientation(int value) {
        this.value = value;
    }
}
