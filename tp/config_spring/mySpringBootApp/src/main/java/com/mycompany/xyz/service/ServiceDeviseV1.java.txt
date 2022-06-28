package com.mycompany.xyz.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mycompany.xyz.entity.Devise;

/*
 Version V1 (sans base de données) 
 pour premier Tp d'injection de dépendance
 */


public class ServiceDeviseV1 implements ServiceDevise{
	
   private Map<String,Devise> mapDevises = new HashMap<>();

	@Override
	public double convertir(double montant, String codeDeviseSource, String codeDeviseCible) {
		Devise deviseSource = rechercherDeviseParCode(codeDeviseSource);
		Devise deviseCible = rechercherDeviseParCode(codeDeviseCible);
		return montant * deviseCible.getChange() / deviseSource.getChange();
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
