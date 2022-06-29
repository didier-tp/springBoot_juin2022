package tp.appliSpring.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import tp.appliSpring.service.ServiceCompte;

@RestController
@RequestMapping(value = "/api-bank/compte" , headers = "Accept=application/json")
public class CompteRestCtrl {
	
	@Autowired //ou bien @Resource
	private ServiceCompte serviceCompte;
	/*
    //http://localhost:8080/appliSpring/api-bank/compte/1
	@GetMapping("/{numCompte}")
	//ancienne version: public Compte getCompteByNum(Long numCpt) {
	public CompteDto getCompteByNum(@PathVariable("numCompte") Long numCpt) {
		Compte compte = serviceCompte.rechercherCompteParNumero(numCpt);
		return new CompteDto(compte.getNumero(),compte.getLabel(),compte.getSolde());
	}*/
	
	/*
	//http://localhost:8080/appliSpring/api-bank/compte/1
		@GetMapping("/{numCompte}")
		//ancienne version: public Compte getCompteByNum(Long numCpt) {
		public ResponseEntity<?> getCompteByNum(@PathVariable("numCompte") Long numCpt) {
			Compte compte = serviceCompte.rechercherCompteParNumero(numCpt);
			if(compte!=null)
			   return new ResponseEntity<CompteDto> (new CompteDto(compte.getNumero(),compte.getLabel(),compte.getSolde()),
					                                HttpStatus.OK);
			else 
			    return new ResponseEntity<Message>(new Message("compte pas trouve pour numero = " + numCpt) ,
			    		                           HttpStatus.NOT_FOUND);//404
		}
	*/
	
	//http://localhost:8080/appliSpring/api-bank/compte/1
		@GetMapping("/{numCompte}")
		//ancienne version: public Compte getCompteByNum(Long numCpt) {
		public CompteDto getCompteByNum(@PathVariable("numCompte") Long numCpt) {
			Compte compte = serviceCompte.rechercherCompteParNumero(numCpt);
			if(compte==null) 
				throw new NotFoundException("compte pas trouve pour numero = " + numCpt);
			    //avec @ResponseStatus(HttpStatus.NOT_FOUND=404) au dessus de la classe NotFoundException
			else
			   return new CompteDto(compte.getNumero(),compte.getLabel(),compte.getSolde());
		}
		
	//http://localhost:8080/appliSpring/api-bank/compte 
    //avec { "numero" : null , "label" : "compteXy" , "solde" : 90.0 }
    // mais pas { "numero" : null , "label" : "c" , "solde" : -90.0 }
	@PostMapping("")	
	public CompteDto postCompte(@Valid @RequestBody CompteDto compteDto) {
		Compte compte = new Compte(compteDto.getNumero(),
				                   compteDto.getLabel(),
				                   compteDto.getSolde());
		Compte compteSauvegarde = serviceCompte.sauvegarderCompte(compte);
		return new CompteDto(compteSauvegarde.getNumero(),
				             compteSauvegarde.getLabel(),
				             compteSauvegarde.getSolde());
		//on renvoie une copie des données insérées en base
		//avec la clef primaire souvent auto-incrémentée
	}
	
	//http://localhost:8080/appliSpring/api-bank/compte
	//http://localhost:8080/appliSpring/api-bank/compte?numClient=1
	@GetMapping("")
	//ancienne version: public Compte getCompteByNum(Long numCpt) {
	public List<CompteDto> getComptesByCriteria(@RequestParam(name="numClient",required=false)Long numClient) {
		List<Compte> listeCompte = null;	
		if(numClient==null)
				listeCompte= serviceCompte.rechercherTousComptes();
			else
				listeCompte= serviceCompte.rechercherComptesDuClient(numClient);
		return listeCompte.stream()
				.map((compte)->new CompteDto(compte.getNumero(),compte.getLabel(),compte.getSolde()))
				.collect(Collectors.toList());
	}
}
