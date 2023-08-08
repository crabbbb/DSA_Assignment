package utility;

import javax.swing.JOptionPane;

/**
 * @author LOH XIN JIE
 */
public class Message {

    public static void ErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "ERROR MESSAGE", JOptionPane.ERROR_MESSAGE);
    }

    public static void AlertMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "ALERT MESSAGE", JOptionPane.WARNING_MESSAGE);
    }

    public static void InformationMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "ALERT MESSAGE", JOptionPane.INFORMATION_MESSAGE);
    }
}
