package sinapers.lawyerassociates.Control;

import Entity.Aviso;
import Entity.Executavel;
import java.io.File;
import java.util.List;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import sinapers.lawyerassociates.Model.LawsuitModel;
import sinapers.lawyerassociates.View.LawyerAssociatesView;
import sinapers.lawyerassociates.View.UserInputsView;
import tpsdb.Model.Entities.Associate;

public class Controller {

    private String laywer = "";
    private Integer laywerCode = 0;
    private Map<String, JCheckBox> collumnsToPrint;
    private List<Associate> associates;
    
    private File saveFolder;
    
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

            laywer = UserInputsView.getLaywerFromArray(laywers);
            laywerCode = Integer.valueOf(laywer.replaceAll("[^0-9]", ""));
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
    
    public class selectSaveFolder extends Executavel{

        public selectSaveFolder() {
            nome = "Selecionando a pasta para salvar o arquivo";
        }

        @Override
        public void run() {
            JOptionPane.showMessageDialog(null, "Escolha a pasta onde o arquivo será salvo:");
            saveFolder = Selector.Pasta.selecionar();
        }
        
    }

    public class setAssociatesList extends Executavel {

        public setAssociatesList() {
            nome = "Definindo lista de associados do advogado selecionado";
        }

        @Override
        public void run() {
            associates = LawsuitModel.getLawsuitsAssociates(Long.valueOf(laywerCode));
        }

    }
    
    public class printAssociatesList extends Executavel {

        public printAssociatesList() {
            nome = "Criando arquivo com lista de associados";
        }

        @Override
        public void run() {
            LawyerAssociatesView view = new LawyerAssociatesView(associates, collumnsToPrint, laywer, saveFolder);
            
            view.createFile();
            
            throw new Aviso("Arquivo salvo em: " + saveFolder.getAbsolutePath());
        }
        
    }
}
