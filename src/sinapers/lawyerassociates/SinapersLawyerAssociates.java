package sinapers.lawyerassociates;

import Entity.Executavel;
import Executor.Execution;
import java.util.ArrayList;
import java.util.List;
import sinapers.lawyerassociates.Control.Controller;

public class SinapersLawyerAssociates {
    private static Controller controller = new Controller();
    
    public static void main(String[] args) {                
        if(getUserInputs()){
            
        }        
    }
    
    public static boolean getUserInputs(){
        List<Executavel> execs = new ArrayList<>();
        execs.add(controller.new selectLaywer());        
        execs.add(controller.new selectCollumnsToPrint());
        execs.add(controller.new selectSaveFolder());
        execs.add(controller.new setAssociatesList());
        execs.add(controller.new printAssociatesList());
        
        Execution exec =  new Execution("Pegar filtro de advogados");       
        exec.setExecutables(execs);
        exec.runExecutables();
        
        exec.endExecution(false);
        
        return exec.hasErrorBreak();
    }
    
    
}
