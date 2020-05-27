package sinapers.lawyerassociates;

import Entity.Executavel;
import Executor.Execution;
import java.util.ArrayList;
import java.util.List;
import sinapers.lawyerassociates.Control.Controller;

public class SinapersLawyerAssociates {

    private static Controller controller = new Controller();

    public static void main(String[] args) {
        if (getUserInputs()) {
            createAssociatesList();
        }
    }
    
    public static boolean createAssociatesList(){
        List<Executavel> execs = new ArrayList<>();
        execs.add(controller.new setAssociatesList());
        execs.add(controller.new printAssociatesList());

        Execution exec = new Execution("Criando lista de associados");
        exec.setExecutables(execs);
        exec.runExecutables();

        exec.endExecution(false);

        return !exec.hasErrorBreak();
    }

    public static boolean getUserInputs() {
        List<Executavel> execs = new ArrayList<>();
        execs.add(controller.new defineDatabaseTables());
        execs.add(controller.new selectLaywer());
        execs.add(controller.new selectCollumnsToPrint());
        execs.add(controller.new selectSaveFolder());

        Execution exec = new Execution("Pegando Advogado");
        exec.setExecutables(execs);
        exec.runExecutables();

        exec.endExecution(false);

        return !exec.hasErrorBreak();
    }

}
