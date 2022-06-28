package com.mycompany.xyz.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.xyz.dto.Virement;
import com.mycompany.xyz.entity.Compte;
import com.mycompany.xyz.service.ServiceCompte;


@RestController
//@CrossOrigin(origins = "*")
@RequestMapping(value="/bank-api-rest" , headers="Accept=application/json")
public class CompteRestCtrl {
	
   @Autowired
	private ServiceCompte serviceCompte;
   
   private static Logger logger = LoggerFactory.getLogger(CompteRestCtrl.class);
	
	public CompteRestCtrl() {
	super();
	logger.debug("CompteRestCtrl instance="+this.toString() );
	}


	//http://localhost:8181/mySpringBootApp/bank-api-rest/public/compte/1
	@GetMapping("/public/compte/{numCompte}")
	public Compte getCompteByNum(@PathVariable("numCompte") Long numCompte) {
		return serviceCompte.rechercherCompteParNumero(numCompte);
	}
	
	
	//http://localhost:8181/mySpringBootApp/bank-api-rest/public/compte
	//http://localhost:8181/mySpringBootApp/bank-api-rest/public/compte?numClient=1
	@GetMapping("/public/compte")
	public List<Compte> getComptesByCriteria(
			  @RequestParam(value="numClient",required=false) Long numClient) {
		List<Compte> comptes = null;
		if(numClient!=null)
			comptes = serviceCompte.rechercherComptesDuClient(numClient);
		else
			comptes = serviceCompte.rechercherComptes();
		return comptes;
	}
	
	//http://localhost:8181/mySpringBootApp/bank-api-rest/public/virement en mode POST
	//avec { "montant" : 50 , "numCptDeb" : 1 , "numCptCred" : 2 }
	@PostMapping("/public/virement")
	public Virement postVirement( @RequestBody Virement virement) {
		/*try {*/
			serviceCompte.transferer(virement.getMontant(), 
					                                          virement.getNumCptDeb(),
					                                          virement.getNumCptCred());
			virement.message="virement bien effectué";
	/*	} catch (Exception e) {
			virement.message="echec virement";
			e.printStackTrace();
		}*/
		return virement;
	}
	
	

}
