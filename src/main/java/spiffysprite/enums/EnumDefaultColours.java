package spiffysprite.enums;

import spiffysprite.models.EnhancedColour;

public enum EnumDefaultColours {
    CLEAR(new EnhancedColour(0.0f, 0.0f, 0.0f, 0.0f)),
    BLACK(new EnhancedColour(0.0f, 1.0f, 0.0f, 1.0f)),
    WHITE(new EnhancedColour(0.0f, 0.0f, 1.0f, 1.0f)),
    LIGHT_GREY(new EnhancedColour(0.0f, 0.3f, 0.0f, 1.0f)),
    DARK_GREY(new EnhancedColour(0.0f, 0.7f, 0.0f, 1.0f)),
    RED(new EnhancedColour(0.0f, 1.0f, 1.0f, 1.0f)),
    ORANGE(new EnhancedColour(0.1f, 1.0f, 1.0f, 1.0f)),
    YELLOW(new EnhancedColour(0.16f, 1.0f, 1.0f, 1.0f)),
    GREEN(new EnhancedColour(0.35f, 1.0f, 1.0f, 1.0f)),
    TEAL(new EnhancedColour(0.5f, 1.0f, 0.82f, 1.0f)),
    BLUE(new EnhancedColour(0.66f, 1.0f, 1.0f, 1.0f)),
    PURPLE(new EnhancedColour(0.82f, 1.0f, 1.0f, 1.0f)),
    PINK(new EnhancedColour(0.86f, 0.58f, 1.0f, 1.0f));

    public final EnhancedColour EnhancedColour;

    EnumDefaultColours(EnhancedColour EnhancedColour) {
        this.EnhancedColour = EnhancedColour;
    }
}
