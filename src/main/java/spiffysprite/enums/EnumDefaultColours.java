package spiffysprite.enums;

import spiffysprite.records.HSBAColour;

public enum EnumDefaultColours {
    CLEAR(new HSBAColour(0.0f, 0.0f, 0.0f, 0.0f)),
    BLACK(new HSBAColour(0.0f, 1.0f, 0.0f, 1.0f)),
    WHITE(new HSBAColour(0.0f, 0.0f, 1.0f, 1.0f)),
    LIGHT_GREY(new HSBAColour(0.0f, 0.3f, 0.0f, 1.0f)),
    DARK_GREY(new HSBAColour(0.0f, 0.7f, 0.0f, 1.0f)),
    RED(new HSBAColour(0.0f, 1.0f, 1.0f, 1.0f)),
    ORANGE(new HSBAColour(0.1f, 1.0f, 1.0f, 1.0f)),
    YELLOW(new HSBAColour(0.16f, 1.0f, 1.0f, 1.0f)),
    GREEN(new HSBAColour(0.35f, 1.0f, 1.0f, 1.0f)),
    TEAL(new HSBAColour(0.5f, 1.0f, 0.82f, 1.0f)),
    BLUE(new HSBAColour(0.66f, 1.0f, 1.0f, 1.0f)),
    PURPLE(new HSBAColour(0.82f, 1.0f, 1.0f, 1.0f)),
    PINK(new HSBAColour(0.86f, 0.58f, 1.0f, 1.0f));

    public final HSBAColour hsbaColour;

    EnumDefaultColours(HSBAColour hsbaColour) {
        this.hsbaColour = hsbaColour;
    }
}
