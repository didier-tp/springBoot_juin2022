package tp.appliSpring.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.appliSpring.dto.CompteDto;
import tp.appliSpring.entity.Compte;
import tp.appliSpring.service.ServiceCompte;

@RestController
@RequestMapping(value = "/api-bank/compte" , headers = "Accept=application/json")
public class CompteRestCtrl {
	
	@Autowired //ou bien @Resource
	private ServiceCompte serviceCompte;
	
    //http://localhost:8080/appliSpring/api-bank/compte/1
	@GetMapping("/{numCompte}")
	//ancienne version: public Compte getCompteByNum(Long numCpt) {
	public CompteDto getCompteByNum(@PathVariable("numCompte") Long numCpt) {
		Compte compte = serviceCompte.rechercherCompteParNumero(numCpt);
		return new CompteDto(compte.getNumero(),compte.getLabel(),compte.getSolde());
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
