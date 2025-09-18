package spiffysprite;

import spiffysprite.ui.UIMaster;

import javax.swing.*;

public class EntryPoint {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(UIMaster::new);
    }
}
