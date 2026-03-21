package spiffysprite.enums;

public enum EnumFileExtensions {
    PNG("png");

    private final String value;

    EnumFileExtensions(String value) { this.value = value; }

    @Override
    public String toString() { return value; }
}
