package tp.appliSpring.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.appliSpring.dto.CompteDto;
import tp.appliSpring.entity.Compte;
import tp.appliSpring.exception.NotFoundException;
import tp.appliSpring.service.CompteService;

@RestController
@RequestMapping(value="/api-bank/compte" , headers="Accept=application/json")
public class CompteRestCtrl {
	
	private CompteService compteService;
	
	
	public CompteRestCtrl(CompteService compteService) {
		super(); //injection de dépendance par construteur
		this.compteService = compteService;
	}

    
    //url = http://localhost:8080/appliSpring/api-bank/compte/1
    @GetMapping("/{numCompte}")
   	public CompteDto getCompteByNum(@PathVariable("numCompte")Long numCpt) {
   		Compte compte = compteService.compteSelonNumero(numCpt);
   		if(compte!=null) 
   			return new CompteDto(compte.getNumero(),compte.getLabel(),compte.getSolde());
   		    
   		else 
   			throw new NotFoundException("compte " + numCpt + " pas trouve" );
   		//NB: au dessus de NotFoundException il y a @ResponseStatus(HttpStatus.NOT_FOUND)
   	}
    
    /*
     variantes pour construire DTO depuis Entity:
       - constructeur de DTO
       - BeanUtils.copyProperties(source, target)
       - ou api spécialisées (ObjectMapper ou autres)
       - record de java17
     */
    
    //url = http://localhost:8080/appliSpring/api-bank/compte
    //   ou http://localhost:8080/appliSpring/api-bank/compte?numClient=1
    @GetMapping("")
   	public List<CompteDto> getComptesByCriteria(@RequestParam(value="numClient",required=false) Long numClient) {
    	List<Compte> comptes = null;
    	if(numClient==null) 
    		comptes = compteService.tousLesComptes();
    	else 
    		comptes = compteService.comptesDuClient(numClient);
    	return comptes.stream()
    			.map((Compte c) -> new CompteDto(c.getNumero(),c.getLabel(),c.getSolde()))
    			.collect(Collectors.toList());
    }
    
    //url = http://localhost:8080/appliSpring/api-bank/compte
    //appelée en mode POST avec { "label" : "compteXy" , "solde" : 50.0 } dans body de request
    //à tester avec postman (body/raw/Content-Type = application/json)
    @PostMapping("")
    public CompteDto postCompte(@RequestBody @Valid CompteDto compteDto) {
    	Compte compte = new Compte(null,compteDto.getLabel(),compteDto.getSolde());
    	Compte compteSauvegarde = this.compteService.sauvegarderCompte(compte);
    	//ameliorable via try/catch et ResponseEntity
    	return new CompteDto(compteSauvegarde.getNumero(),
    			             compteSauvegarde.getLabel(),
    			             compteSauvegarde.getSolde());
    }
	

}
