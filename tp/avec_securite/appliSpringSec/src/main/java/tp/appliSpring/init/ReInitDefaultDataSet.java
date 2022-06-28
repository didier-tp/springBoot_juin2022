package tp.appliSpring.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import tp.appliSpring.dao.ClientDao;
import tp.appliSpring.dao.PaysDao;
import tp.appliSpring.entity.Client;
import tp.appliSpring.entity.Compte;
import tp.appliSpring.entity.Devise;
import tp.appliSpring.entity.Pays;
import tp.appliSpring.service.CompteService;
import tp.appliSpring.service.DeviseService;

@Component
@Profile("init")
public class ReInitDefaultDataSet {

	@Autowired
	private CompteService compteService;
	
	@Autowired
	private ClientDao clientDao ;
	
	@Autowired
	DeviseService deviseService;
	
	@Autowired
	PaysDao paysDao;
	
	@PostConstruct
	public void initDataSet() {
		
		Client c1 =  clientDao.save(new Client(null,"jean","Bon"));
		
		Compte cptA = new Compte(null,"compteA",60.0);
		cptA.setClient(c1);
		compteService.sauvegarderCompte(cptA);
		
		Compte cptB =new Compte(null,"compteB",160.0);
		cptB.setClient(c1);
		compteService.sauvegarderCompte(cptB);
		
		compteService.sauvegarderCompte(new Compte(null,"compteC",250.0));
		compteService.sauvegarderCompte(new Compte(null,"compteD",300.0));
		
		Devise deviseEuro =  new Devise("EUR","euro",1.0);
		deviseService.sauvegarderDevise(deviseEuro);
		
		
		paysDao.save(new Pays("fr","France",deviseEuro));
		paysDao.save(new Pays("it","Italie",deviseEuro));
		paysDao.save(new Pays("de","Allemagne",deviseEuro));
		paysDao.save(new Pays("es","espagne",deviseEuro));	
		
		deviseService.sauvegarderDevise(new Devise("USD","dollar",1.1));
		deviseService.sauvegarderDevise(new Devise("GBP","livre",0.9));
		deviseService.sauvegarderDevise(new Devise("JPY","yen",120.0));
	}
}
