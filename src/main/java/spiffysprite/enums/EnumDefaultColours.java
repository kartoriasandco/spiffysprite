package spiffysprite.enums;

import spiffysprite.models.EnhancedColour;

public enum EnumDefaultColours {
    CLEAR(EnhancedColour.fromAHSB(0.0f, 0.0f, 0.0f, 0.0f)),
    BLACK(EnhancedColour.fromAHSB(0.0f, 1.0f, 0.0f, 1.0f)),
    WHITE(EnhancedColour.fromAHSB(0.0f, 0.0f, 1.0f, 1.0f)),
    LIGHT_GREY(EnhancedColour.fromAHSB(0.0f, 0.3f, 0.0f, 1.0f)),
    DARK_GREY(EnhancedColour.fromAHSB(0.0f, 0.7f, 0.0f, 1.0f)),
    RED(EnhancedColour.fromAHSB(0.0f, 1.0f, 1.0f, 1.0f)),
    ORANGE(EnhancedColour.fromAHSB(0.1f, 1.0f, 1.0f, 1.0f)),
    YELLOW(EnhancedColour.fromAHSB(0.16f, 1.0f, 1.0f, 1.0f)),
    GREEN(EnhancedColour.fromAHSB(0.35f, 1.0f, 1.0f, 1.0f)),
    TEAL(EnhancedColour.fromAHSB(0.5f, 1.0f, 0.82f, 1.0f)),
    BLUE(EnhancedColour.fromAHSB(0.66f, 1.0f, 1.0f, 1.0f)),
    PURPLE(EnhancedColour.fromAHSB(0.82f, 1.0f, 1.0f, 1.0f)),
    PINK(EnhancedColour.fromAHSB(0.86f, 0.58f, 1.0f, 1.0f));

    public final EnhancedColour enhancedColour;

    EnumDefaultColours(EnhancedColour EnhancedColour) {
        this.enhancedColour = EnhancedColour;
    }
}
