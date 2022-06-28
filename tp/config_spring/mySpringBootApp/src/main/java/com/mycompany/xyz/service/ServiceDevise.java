package com.mycompany.xyz.service;

import java.util.List;

import com.mycompany.xyz.entity.Devise;

public interface ServiceDevise {
   List<Devise> rechercherDevises();
   Devise rechercherDeviseParCode(String code); //throws MyEntityNotFoundException;
   List<Devise> rechercherDevisesParChangeMini(Double changeMini);
   double convertir(double montant, 
		            String codeDeviseSource, 
		            String codeDeviseCible)  throws NotFoundException;
   //..
   Devise createDevise(Devise d);
   void updateDevise(Devise d);
   void deleteDevise(String code);
}
