package spiffysprite.ui.spritespanel;

import net.miginfocom.swing.MigLayout;
import spiffysprite.models.Sprite;
import spiffysprite.ui.TransparencyPanel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class SpriteListCellRenderer implements ListCellRenderer<SpriteListCell> {
    /**
     * Return a component that has been configured to display the specified
     * value. That component's <code>paint</code> method is then called to
     * "render" the cell.  If it is necessary to compute the dimensions
     * of a list because the list cells do not have a fixed size, this method
     * is called to generate a component on which <code>getPreferredSize</code>
     * can be invoked.
     *
     * @param list         The JList we're painting.
     * @param value        The value returned by list.getModel().getElementAt(index).
     * @param index        The cells index.
     * @param isSelected   True if the specified cell was selected.
     * @param cellHasFocus True if the specified cell has the focus.
     * @return A component whose paint() method will render the specified value.
     * @see JList
     * @see ListSelectionModel
     * @see ListModel
     */
    @Override
    public Component getListCellRendererComponent(
            JList<? extends SpriteListCell> list,
            SpriteListCell value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        if (isSelected) {
            value.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        } else {
            value.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        }

        return value;
    }
}
