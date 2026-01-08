package spiffysprite.models;

import spiffysprite.ui.palettepanel.PalettePanel;

import java.util.ArrayList;

public record Palette(ArrayList<EnhancedColour> colours) {
    public Palette(ArrayList<EnhancedColour> colours) {
        if (colours == null) {
            throw new RuntimeException("Null palette");
        } else if (colours.size() != PalettePanel.MAXIMUM_PALETTE_COLOURS) {
            throw new RuntimeException("Number of palette elements must match PalettePanel.MAXIMUM_PALETTE_COLOURS");
        }

        this.colours = colours;
    }

    public ArrayList<Integer> toRGBList() {
        ArrayList<Integer> rgbList = new ArrayList<>();
        colours.forEach((colour) -> rgbList.add(colour.getRGB()));
        return rgbList;
    }
}
