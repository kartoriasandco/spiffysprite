package spiffysprite.convenienceclasses;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class DocumentAdapter implements DocumentListener {
    /**
     * Gives notification that there was an insert into the document.  The
     * range given by the DocumentEvent bounds the freshly inserted region.
     *
     * @param de the document event
     */
    @Override
    public void insertUpdate(DocumentEvent de) {
        changedUpdate(de);
    }

    /**
     * Gives notification that a portion of the document has been
     * removed.  The range is given in terms of what the view last
     * saw (that is, before updating sticky positions).
     *
     * @param de the document event
     */
    @Override
    public void removeUpdate(DocumentEvent de) {
        changedUpdate(de);
    }

    /**
     * Gives notification that an attribute or set of attributes changed.
     *
     * @param de the document event
     */
    @Override
    public void changedUpdate(DocumentEvent de) {

    }
}
