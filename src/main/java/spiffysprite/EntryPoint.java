package spiffysprite;

import spiffysprite.ui.UIMaster;

import javax.swing.*;

public class EntryPoint {
    public static UIMaster uiMaster;
    static void main() {
        SwingUtilities.invokeLater(() -> uiMaster = new UIMaster());
    }
}
