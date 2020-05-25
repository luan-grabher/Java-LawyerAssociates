package sinapers.lawyerassociates.Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import tpsdb.Model.Entities.Associate;
import tpsdb.Model.Tps_Model;

public class LawsuitModel {

    /**
     *  @return Os processos do advogado informado
     */
    public List<Associate> getLawsuitsAssociates(Long lawyerCode) {
        //get tables
        List<Associate> associates = Tps_Model.getAssociates();
        List<tpsdb.Model.Entities.Lawsuit> lawSuits = Tps_Model.getLawsuits();

        //Lista processos do advogado escolhido
        List<tpsdb.Model.Entities.Lawsuit> lawyerLawsuits = lawSuits.stream().filter(p -> Objects.equals(p.getAdvogado(), lawyerCode)).collect(Collectors.toList());

        //Procura objectos de associado
        return getAssociatesList(associates, lawyerLawsuits);
    }

    /**
     *  @return Os associados dos processos informados 
     */
    private List<Associate> getAssociatesList(List<Associate> associates, List<tpsdb.Model.Entities.Lawsuit> lawyerLawsuits) {
        //Procura objectos de associado
        List<Associate> lawsuitsAssociates = new ArrayList<>();
        for (tpsdb.Model.Entities.Lawsuit advogadoProcesso : lawyerLawsuits) {
            //Imprime associado
            Optional<Associate> associadoOp = associates.stream().filter(a -> Objects.equals(a.getCodigoAssociado(), advogadoProcesso.getAssociado())).findFirst();

            lawsuitsAssociates.add(associadoOp.get());
        }

        //Ordena por ordem alfabetica
        lawsuitsAssociates.sort(Comparator.comparing(Associate::getNome));
        
        return lawsuitsAssociates;
    }
}
