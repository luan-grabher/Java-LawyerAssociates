package sinapers.lawyerassociates.Control;

import Entity.Warning;
import Entity.Executavel;
import java.io.File;
import java.util.List;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import sinapers.lawyerassociates.Model.LawsuitModel;
import sinapers.lawyerassociates.Model.LawyerModel;
import sinapers.lawyerassociates.View.LawyerAssociatesView;
import sinapers.lawyerassociates.View.UserInputsView;
import tpsdb.Model.Entities.Associate;
import tpsdb.Model.Tps_Model;

public class Controller {

    private String laywer = "";
    private Integer laywerCode = 0;
    private Map<String, JCheckBox> collumnsToPrint;
    private List<Associate> associates;
    
    private LawyerModel lawyerModel = new LawyerModel();
    
    private File saveFolder;
    
    public class defineDatabaseTables extends Executavel{

        public defineDatabaseTables() {
            name = "Buscando dados do banco de dados";
        }

        @Override
        public void run() {
            Tps_Model.setAssociates(); //Associados
            Tps_Model.setLawyers(); // Advogados
            Tps_Model.setLawsuits(); // Processos dos advogados            
        }
        
    }
    
    public class selectLaywer extends Executavel {

        public selectLaywer() {
            name = "Selecionando o advogado";
        }

        @Override
        public void run() {
            //Pega filtro do nome
            String lawyerSearchName = UserInputsView.getLawyerSearchName();

            //Cria Lista com advogados e codigos
            String[] lawyersList = lawyerModel.filterLawyersByString(lawyerSearchName);

            laywer = UserInputsView.getLawyerFromArray(lawyersList);
            laywerCode = Integer.valueOf(laywer.replaceAll("[^0-9]", ""));
        }
    }

    public class selectCollumnsToPrint extends Executavel {

        public selectCollumnsToPrint() {
            name = "Selecionando colunas para imprimir";
        }

        @Override
        public void run() {
            collumnsToPrint = UserInputsView.getCollumnsToPrint();
        }

    }
    
    public class selectSaveFolder extends Executavel{

        public selectSaveFolder() {
            name = "Selecionando a pasta para salvar o arquivo";
        }

        @Override
        public void run() {
            JOptionPane.showMessageDialog(null, "Escolha a pasta onde o arquivo será salvo:");
            saveFolder = Selector.Pasta.selecionar();
        }
        
    }

    public class setAssociatesList extends Executavel {

        public setAssociatesList() {
            name = "Definindo lista de associados do advogado selecionado";
        }

        @Override
        public void run() {
            associates = LawsuitModel.getLawsuitsAssociates(Long.valueOf(laywerCode));
        }

    }
    
    public class printAssociatesList extends Executavel {

        public printAssociatesList() {
            name = "Criando arquivo com lista de associados";
        }

        @Override
        public void run() {
            LawyerAssociatesView view = new LawyerAssociatesView(associates, collumnsToPrint, laywer, saveFolder);
            
            view.createFile();
            
            throw new Warning("Arquivo salvo em: " + saveFolder.getAbsolutePath());
        }
        
    }
}
