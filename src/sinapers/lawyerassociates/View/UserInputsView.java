package sinapers.lawyerassociates.View;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

public class UserInputsView {

    public static String getLawyerSearchName() {
        String r = JOptionPane.showInputDialog(null, "Escreva o nome do advogado para pesquisar:", "Pesquise o advogado", JOptionPane.QUESTION_MESSAGE);

        if (r == null) {
            throw new Error("Você deve procurar ao menos pela primeira letra!");
        } else {
            return r;
        }
    }

    public static String getLaywerFromArray(String[] laywers) {
        return (String) JOptionPane.showInputDialog(null, "Escolha um advogado:", "Escolha o advogado", JOptionPane.QUESTION_MESSAGE, null, laywers, laywers[0]);
    }

    public static JCheckBox[] getCollumnsToPrint() {

        JCheckBox name = new JCheckBox("Nome");
        JCheckBox cpf = new JCheckBox("CPF");
        JCheckBox rg = new JCheckBox("RG");
        JCheckBox birthDay = new JCheckBox("Data de Nascimento");

        name.setSelected(true);
        cpf.setSelected(true);
        rg.setSelected(true);
        birthDay.setSelected(true);

        JCheckBox[] checkBoxes = new JCheckBox[]{name, cpf, rg, birthDay};

        Object[] params = {"Escolha as colunas para imprimir:", checkBoxes};

        int n = JOptionPane.showConfirmDialog(null, params, "Escolha as colunas que serão impressas:", JOptionPane.DEFAULT_OPTION);

        System.out.println("Cpf selecionado: " + cpf.getText());

        return checkBoxes;
    }
}
