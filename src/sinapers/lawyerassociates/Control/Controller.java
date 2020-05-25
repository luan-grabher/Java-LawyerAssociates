package sinapers.lawyerassociates.Control;

import Entity.Executavel;
import javax.swing.JCheckBox;
import sinapers.lawyerassociates.View.UserInputsView;

public class Controller {
    private Integer laywerCode = 0;
    private JCheckBox[] collumnsToPrint;

    public class selectLaywer extends Executavel {

        public selectLaywer() {
            nome = "Selecionando o advogado";
        }

        @Override
        public void run() {
            //Pega filtro do nome
            String lawyerSearchName = UserInputsView.getLawyerSearchName();

            //Cria Lista com advogados e codigos
            String[] laywers = new String[]{"(9) Aline", "(19) Debora"};

            laywerCode = UserInputsView.getLaywerFromArray(laywers);
        }
    }
    
    public class selectCollumnsToPrint extends Executavel {

        public selectCollumnsToPrint() {
            nome = "Selecionando colunas para imprimir";
        }

        @Override
        public void run() {
            collumnsToPrint = UserInputsView.getCollumnsToPrint();
        }
        
    }
}
