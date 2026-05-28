package spiffysprite.enums;

public enum EnumWorkspaceSubdirectories {
    SPRITES("/sprites"),
    PALETTES("/palettes"),
    LAYERS("/layers");

    private final String value;

    EnumWorkspaceSubdirectories(String value) { this.value = value; }

    @Override
    public String toString() { return value; }
}
