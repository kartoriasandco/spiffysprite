package spiffysprite.enums;

import javax.swing.*;

public enum GenericOrientation {
    HORIZONTAL(SwingConstants.HORIZONTAL),
    VERTICAL(SwingConstants.VERTICAL);

    public final int value;

    GenericOrientation(int value) {
        this.value = value;
    }
}
