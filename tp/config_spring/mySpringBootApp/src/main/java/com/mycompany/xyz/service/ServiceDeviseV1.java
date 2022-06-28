package com.mycompany.xyz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mycompany.xyz.entity.Devise;

/*
 Version V1 (sans base de données) 
 pour premier Tp d'injection de dépendance
 */

//@Service
public class ServiceDeviseV1 implements ServiceDevise{
	
	private static Logger logger = LoggerFactory.getLogger(ServiceDeviseV1.class);
	
	
   private Map<String,Devise> mapDevises = new HashMap<>();
   
   

	public ServiceDeviseV1() {
		super();
		logger.debug("ServiceDeviseV1 instance="+this.toString() );
	}


	@Override
	public double convertir(double montant, String codeDeviseSource, String codeDeviseCible) 
			 throws NotFoundException {
		try {
			Devise deviseSource = rechercherDeviseParCode(codeDeviseSource);
			Devise deviseCible = rechercherDeviseParCode(codeDeviseCible);
			return montant * deviseCible.getChange() / deviseSource.getChange();
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("devise_not_found",e);//ameliorable en précision
		}
	}


	@Override
	public List<Devise> rechercherDevises() {
		return new ArrayList<Devise>(mapDevises.values());
	}
	
	@Override
	public List<Devise> rechercherDevisesParChangeMini(Double changeMini) {
		return rechercherDevises().stream()
			       .filter((d)->d.getChange()>=changeMini)
			       .collect(Collectors.toList());
	}

	@Override
	public Devise rechercherDeviseParCode(String code) {
			return mapDevises.get(code);
	}

	@Override
	public Devise createDevise(Devise d) {
		mapDevises.put(d.getCode(), d);
		return d;
	}

	@Override
	public void updateDevise(Devise d) {
		  mapDevises.put(d.getCode(), d);
	}

	@Override
	public void deleteDevise(String code) {
		mapDevises.remove(code);
	}

}
