package tp.appliSpring.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tp.appliSpring.entity.Compte;
import tp.appliSpring.service.ServiceCompte;

@RestController
@RequestMapping(value = "/api-bank/compte" , headers = "Accept=application/json")
public class CompteRestCtrl {
	
	@Autowired //ou bien @Resource
	private ServiceCompte serviceCompte;
	
    //http://localhost:8080/appliSpring/api-bank/compte/1
	@GetMapping("/{numCompte}")
	//future version: public CompteDto getCompteByNum(Long numCpt) {
	public Compte getCompteByNum(@PathVariable("numCompte") Long numCpt) {
		return serviceCompte.rechercherCompteParNumero(numCpt);
	}
	
	//http://localhost:8080/appliSpring/api-bank/compte
	//http://localhost:8080/appliSpring/api-bank/compte?numClient=1
	@GetMapping("")
	//future version: public CompteDto getCompteByNum(Long numCpt) {
	public List<Compte> getComptesByCriteria(@RequestParam(name="numClient",required=false)Long numClient) {
			if(numClient==null)
				return serviceCompte.rechercherTousComptes();
			else
				return serviceCompte.rechercherComptesDuClient(numClient);
	}
}
