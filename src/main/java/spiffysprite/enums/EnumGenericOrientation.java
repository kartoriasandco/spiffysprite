package spiffysprite.enums;

import javax.swing.*;

public enum EnumGenericOrientation {
    HORIZONTAL(SwingConstants.HORIZONTAL),
    VERTICAL(SwingConstants.VERTICAL);

    public final int value;

    EnumGenericOrientation(int value) {
        this.value = value;
    }
}
