package sinapers.lawyerassociates.Model;

import java.util.ArrayList;
import java.util.List;
import tpsdb.Model.Entities.Lawyer;
import tpsdb.Model.Tps_Model;

public class LawyerModel {
    public String[] filterLawyersByString(String filter){
        List<String> lawyersList = new ArrayList<>();
        
        List<Lawyer> lawyers = Tps_Model.getLawyers();
        for (Lawyer lawyer : lawyers) {
            if(lawyer.getName().contains(filter)){
                lawyersList.add("(" + lawyer.getCode() + ") " + lawyer.getName());
            }
        }
        
        return lawyersList.toArray(new String[lawyersList.size()]);
    }
}
