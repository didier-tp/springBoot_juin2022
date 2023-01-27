package tp.appliSpring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CompteDto> getCompteByNum(@PathVariable("numCompte")  Integer numCompte){
        Compte compte =  serviceCompte.rechercherCompteParNumero(numCompte);
        if(compte == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        else
            return new ResponseEntity<CompteDto>(DtoConverter.compteToCompteDto(compte),HttpStatus.OK); //200
    }
/*
    @GetMapping("/{numCompte}")
    public CompteDto getCompteByNum(@PathVariable("numCompte")  Integer numCompte){
        Compte compte =  serviceCompte.rechercherCompteParNumero(numCompte);
        return DtoConverter.compteToCompteDto(compte);
    }
*/

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

    //url : http://localhost:8080/appliSpring/api-bank/compte
    //appelé par PostMan ou par angular ou autre en mode POST
    //avec dans le corps de la requête des données de ce genre
    // { "numero" : null , "label" : "compteXy" , "solde" : 100.0 }
    @PostMapping("")
    public CompteDto postCompte(@RequestBody  CompteDto compteDto){
        Compte compte =  DtoConverter.compteDtoToCompte(compteDto);
        return DtoConverter.compteToCompteDto( serviceCompte.sauvegarder(compte));
        //on retourne souvent une copie des données sauvegardées
        //en base avec la clef primaire auto-incrémentée (numero qui n'est plus null)
    }

    //url : http://localhost:8080/appliSpring/api-bank/compte
    //appelé par PostMan ou par angular ou autre en mode PUT
    //avec dans le corps de la requête des données de ce genre
    // { "numero" : 4 , "label" : "compteXyBis" , "solde" : 120.0 }
    @PutMapping("")
    public CompteDto putCompte(@RequestBody  CompteDto compteDto){
        Compte compte =  DtoConverter.compteDtoToCompte(compteDto);

        return DtoConverter.compteToCompteDto( serviceCompte.sauvegarderExistant(compte));
        //on retourne souvent une copie des données sauvegardées (mise à jour)
    }

}
