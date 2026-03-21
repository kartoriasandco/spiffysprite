package spiffysprite;

import spiffysprite.ui.UIMaster;
import spiffysprite.utils.jsonparser.JSONArray;
import spiffysprite.utils.jsonparser.JSONValue;
import spiffysprite.utils.jsonparser.JSONObject;

import javax.swing.*;

public class EntryPoint {
    public static UIMaster uiMaster;
    static void main() {
        SwingUtilities.invokeLater(() -> uiMaster = new UIMaster());
    }
}
