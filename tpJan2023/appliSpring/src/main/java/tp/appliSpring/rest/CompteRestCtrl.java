package tp.appliSpring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tp.appliSpring.dto.CompteDto;
import tp.appliSpring.entity.Compte;
import tp.appliSpring.service.ServiceCompte;
import tp.appliSpring.util.DtoConverter;

import java.util.List;

@RestController //composant Spring de type RestController
@RequestMapping(value="/api-bank/compte" , headers="Accept=application/json")
public class CompteRestCtrl {

   @Autowired //injection de dépendance
   private ServiceCompte serviceCompte;

   //on peut éventuellement injecter le dao en direct (c'est possible)

    //url : http://localhost:8080/appliSpring/api-bank/compte/1
    //@RequestMapping(value="/{numCompte}" , method= RequestMethod.GET)
    @GetMapping("/{numCompte}")
    public CompteDto getCompteByNum(@PathVariable("numCompte")  Integer numCompte){
        Compte compte =  serviceCompte.rechercherCompteParNumero(numCompte);
        return DtoConverter.compteToCompteDto(compte);
    }

    //url : http://localhost:8080/appliSpring/api-bank/compte
    //url : http://localhost:8080/appliSpring/api-bank/compte?soldeMini=30
    //url : http://localhost:8080/appliSpring/api-bank/compte?numClient=1
    @GetMapping("")
    public List<CompteDto> getCompteByNum(@RequestParam(value="soldeMini",required=false)  Double soldeMini,
                                       @RequestParam(value="numClient",required=false)  Integer numClient){
       if(soldeMini==null && numClient ==null)
           return  DtoConverter.compteListToCompteDtoList( serviceCompte.rechercherTousComptes() );
       else
           if(soldeMini!=null && numClient ==null)
               return DtoConverter.compteListToCompteDtoList( serviceCompte.rechercherComptesAvecSoldeMini(soldeMini) );
           else
               return null; //code améliorable ...
    }

}
