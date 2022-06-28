package com.mycompany.xyz.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.xyz.entity.Devise;
import com.mycompany.xyz.repository.RepositoryDevise;


@Service 
@Transactional
public class ServiceDeviseV2 implements ServiceDevise{
	
	private static Logger logger = LoggerFactory.getLogger(ServiceDeviseV2.class);
	
	//@Autowired
	private RepositoryDevise repositoryDevise;
	
	
	//injection de dépendance par constructeur
	public ServiceDeviseV2(RepositoryDevise repositoryDevise) {
		this.repositoryDevise=repositoryDevise;
		logger.debug("ServiceDeviseV2 instance="+this.toString() 
		    + " using repositoryDevise="+repositoryDevise.getClass().getName());
	}
	

	@Override
	public double convertir(double montant, String codeDeviseSource, String codeDeviseCible)
	 throws NotFoundException {
		try {
			Devise deviseSource = repositoryDevise.findById(codeDeviseSource).get();
			Devise deviseCible = repositoryDevise.findById(codeDeviseCible).get();
			return montant * deviseCible.getChange() / deviseSource.getChange();
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("devise_not_found",e);//ameliorable en précision
		}
	}


	@Override
	public List<Devise> rechercherDevises() {
		return repositoryDevise.findAll();
	}
	
	@Override
	public List<Devise> rechercherDevisesParChangeMini(Double changeMini) {
		return repositoryDevise.findByChangeGreaterThanEqual(changeMini);
	}

	@Override
	public Devise rechercherDeviseParCode(String code) {
		try {
			return repositoryDevise.findById(code).get();//retourne exception si Optional empty
			                                                                        //.orElse(null)
		} catch (Exception e) {
			//e.printStackTrace();
			throw new RuntimeException("devise pas trouvée pour code="+code);
		}
	}

	@Override
	public Devise createDevise(Devise d) {
		if(repositoryDevise.existsById(d.getCode()))
			throw new RuntimeException("devise déjà existante avec code="+d.getCode());
		else return  repositoryDevise.save(d);
	}

	@Override
	public void updateDevise(Devise d) {
		if(repositoryDevise.existsById(d.getCode()))
			repositoryDevise.save(d);
		else
			throw new RuntimeException("devise inexistante (pas modifiable) pour code="+d.getCode());
	}

	@Override
	public void deleteDevise(String code) {
		if(repositoryDevise.existsById(code))
		    repositoryDevise.deleteById(code);
		else
			throw new RuntimeException("devise inexistante (pas supprimable) pour code="+code);
			
	}

}
