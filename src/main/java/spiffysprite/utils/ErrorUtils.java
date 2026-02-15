package spiffysprite.utils;

import net.miginfocom.swing.MigLayout;
import spiffysprite.EntryPoint;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public abstract class ErrorUtils {
    public static void handleException(Exception e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        StringBuilder sb = new StringBuilder();
        Arrays.stream(stackTrace).forEach((elem) -> sb.append(elem).append("\n"));

        JDialog dialog = new JDialog(EntryPoint.uiMaster, "Exception thrown", true);
        JLabel labelExceptionClass = new JLabel("Exception:");
        JLabel labelStackTrace = new JLabel("Stack trace:");
        JTextField textFieldExceptionClass = new JTextField(e.getClass().toString());
        JTextArea textAreaStackTrace = new JTextArea(sb.toString());
        JScrollPane scrollPaneStackTrace = new JScrollPane(textAreaStackTrace);

        textAreaStackTrace.setEditable(false);
        textFieldExceptionClass.setEditable(false);

        dialog.setLayout(new MigLayout());
        dialog.add(labelExceptionClass, "wrap");
        dialog.add(textFieldExceptionClass, "width 100%, wrap");
        dialog.add(labelStackTrace, "wrap");
        dialog.add(scrollPaneStackTrace);
        dialog.setSize(new Dimension(600, 400));
        dialog.setVisible(true);
    }
}
