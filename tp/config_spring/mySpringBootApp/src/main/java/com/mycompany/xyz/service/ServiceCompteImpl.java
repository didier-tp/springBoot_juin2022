package com.mycompany.xyz.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.xyz.entity.Compte;
import com.mycompany.xyz.exception.MyGenericException;
import com.mycompany.xyz.exception.NotFoundException;
import com.mycompany.xyz.repository.RepositoryCompte;


@Service
@Transactional(/* propagation = Propagation.REQUIRED par defaut */)
public class ServiceCompteImpl implements ServiceCompte {
	
	private static Logger logger = LoggerFactory.getLogger(ServiceCompteImpl.class);
	
	private RepositoryCompte repositoryCompte;
	
    //constructeur pour injection
	public ServiceCompteImpl(RepositoryCompte repositoryCompte) {
		this.repositoryCompte = repositoryCompte;
		logger.debug("ServiceCompteImpl instance="+this.toString() );
	}

	@Override
	public Compte rechercherCompteParNumero(Long numero) {
		try {
			return repositoryCompte.findById(numero).get();
		} catch (Exception e) {
			throw new NotFoundException("compte inexistant pour numero="+numero);
		}
	}
	
	@Override
	public void transferer(double montant, long numCptDeb, long numCptCred) {
		try {
			Compte cptDeb =  repositoryCompte.findById(numCptDeb).get();
			cptDeb.setSolde(cptDeb.getSolde() - montant);
			//repositoryCompte.save(cptDeb); //instruction pas indispensable sur entité persistante
			
			Compte cptCred =  repositoryCompte.findById(numCptCred).get();
			cptCred.setSolde(cptCred.getSolde() + montant);
			//repositoryCompte.save(cptCred); //instruction pas indispensable sur entité persistante
		} catch (Exception e) {
		      throw new MyGenericException("echec virement" , e);
		}
	}

	@Override
	public List<Compte> rechercherComptesDuClient(Long numeroClient) {
		return repositoryCompte.findByClientNumero(numeroClient);
	}

	@Override
	public List<Compte> rechercherComptes() {
		return repositoryCompte.findAll();
	}

	

}
