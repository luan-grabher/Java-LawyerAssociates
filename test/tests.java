
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;


public class tests {

    public static void main(String[] args) {
        JCheckBox checkbox = new JCheckBox("Do not show this message again.");
        String message = "Are you sure you want to disconnect the selected products?";
        Object[] params = {message, checkbox};
        int n = JOptionPane.showConfirmDialog(null, params, "Disconnect Products", JOptionPane.YES_NO_OPTION);
        boolean dontShow = checkbox.isSelected();
    }

}
