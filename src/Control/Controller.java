package Control;

import Entity.Executavel;
import View.UserInputsView;

public class Controller {
    private String lawyerSearchName = "";
    
    public class selectLaywer extends Executavel{

        public selectLaywer() {
            nome = "Selecionando o advogado";
        }

        @Override
        public void run() {
            lawyerSearchName = UserInputsView.getLawyerSearchName();
        }
        
        
        
    }
}
