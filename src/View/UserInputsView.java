package View;

import javax.swing.JOptionPane;

public class UserInputsView {
    public static String getLawyerSearchName(){
        String r = JOptionPane.showInputDialog(null, "Escreva o nome do advogado para pesquisar:", "Pesquise o advogado", JOptionPane.QUESTION_MESSAGE);
        
        if(r == null){
            throw new Error("Você deve procurar ao menos pela primeira letra!");
        }else{
            return r;
        }
    }
}
