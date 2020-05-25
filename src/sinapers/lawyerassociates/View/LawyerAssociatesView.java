package sinapers.lawyerassociates.View;

import java.io.File;
import java.util.List;
import javax.swing.JCheckBox;
import tpsdb.Model.Entities.Associate;

public class LawyerAssociatesView {
    private List<Associate> associates;
    private JCheckBox[] collumnsToPrint;
    private String lawyer;
    private File saveFolder;
    
    

    public LawyerAssociatesView(List<Associate> associates, JCheckBox[] collumnsToPrint, String lawyer, File saveFolder) {
        this.associates = associates;
        this.collumnsToPrint = collumnsToPrint;
        this.lawyer = lawyer;
        this.saveFolder = saveFolder;
    }   
    
    public void createFile(){
        
    }
    
}
