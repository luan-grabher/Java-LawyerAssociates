package sinapers.lawyerassociates.View;

import java.util.HashMap;
import java.util.Map;
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

    public static String getLawyerFromArray(String[] laywers) {
        return (String) JOptionPane.showInputDialog(null, "Escolha um advogado:", "Escolha o advogado", JOptionPane.QUESTION_MESSAGE, null, laywers, laywers[0]);
    }

    public static Map<String, JCheckBox> getCollumnsToPrint() {

        JCheckBox name = new JCheckBox("Nome");
        JCheckBox cpf = new JCheckBox("CPF");
        JCheckBox rg = new JCheckBox("RG");
        JCheckBox birthDay = new JCheckBox("Data de Nascimento");
        JCheckBox deathDay = new JCheckBox("Data de Óbito");
        JCheckBox idCountry = new JCheckBox("Matricula Estado");
        JCheckBox idIpe = new JCheckBox("Matricula IPE");

        name.setSelected(true);
        cpf.setSelected(true);
        rg.setSelected(true);
        birthDay.setSelected(true);
        deathDay.setSelected(false);
        idCountry.setSelected(false);
        idIpe.setSelected(false);

        JCheckBox[] checkBoxes = new JCheckBox[]{name, cpf, rg, birthDay, deathDay,idCountry, idIpe};
        
        Map<String, JCheckBox> checkBoxMap = new HashMap<>();
        checkBoxMap.put("name", name);
        checkBoxMap.put("cpf", cpf);
        checkBoxMap.put("rg", rg);
        checkBoxMap.put("birthDay", birthDay);
        checkBoxMap.put("deathDay", deathDay);
        checkBoxMap.put("idCountry", idCountry);
        checkBoxMap.put("idIpe", idIpe);

        Object[] params = {"Escolha as colunas para imprimir:", checkBoxes};

        int n = JOptionPane.showConfirmDialog(null, params, "Escolha as colunas que serão impressas:", JOptionPane.DEFAULT_OPTION);

        return checkBoxMap;
    }
}
