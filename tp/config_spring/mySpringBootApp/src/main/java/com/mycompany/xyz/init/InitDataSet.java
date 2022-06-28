package com.mycompany.xyz.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.mycompany.xyz.entity.Client;
import com.mycompany.xyz.entity.Compte;
import com.mycompany.xyz.entity.Devise;
import com.mycompany.xyz.repository.RepositoryClient;
import com.mycompany.xyz.repository.RepositoryCompte;
import com.mycompany.xyz.service.ServiceDevise;

@Component
@Profile({"initDataSet"})
public class InitDataSet {
	
  @Autowired
	private ServiceDevise serviceDevise;
   
  @Autowired
   private RepositoryClient repositoryClient;
   
  @Autowired
   private RepositoryCompte repositoryCompte;
	
	@PostConstruct
	public void init() {
		//repositoryClient.save(new Client(1L,"client1"));
		Client client1 = repositoryClient.save(new Client(null,"client1"));
		Client client2 = repositoryClient.save(new Client(null,"client2"));
		
		Compte compteA = new Compte(null,"compte A" , 100.0);
		compteA.setClient(client1);		compteA = repositoryCompte.save(compteA);
		Compte compteB = new Compte(null,"compte B" , 50.0);
		compteB.setClient(client1);		compteB = repositoryCompte.save(compteB);
		
		Compte compteC = new Compte(null,"compte C" , 60.0);
		compteC.setClient(client2);		compteC = repositoryCompte.save(compteC);
		Compte compteD = new Compte(null,"compte D" , 70.0);
		compteD.setClient(client2);		compteD = repositoryCompte.save(compteD);
		System.out.println("client2="+client2);  System.out.println("compteD="+compteD); 
		
		serviceDevise.createDevise(new Devise("EUR","Euro",1.0));
		serviceDevise.createDevise(new Devise("USD","Dollar",1.1));
		serviceDevise.createDevise(new Devise("GBP","Livre",0.9));
		serviceDevise.createDevise(new Devise("JPY","Yen",123.6));
		
		//...
	}
	

}
