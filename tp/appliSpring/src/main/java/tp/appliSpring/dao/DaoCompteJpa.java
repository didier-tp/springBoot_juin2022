package tp.appliSpring.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import tp.appliSpring.entity.Compte;

@Repository //@Component de type DAO/Repository
public class DaoCompteJpa implements DaoCompte {
	                    //initialiser entityManager via META-INF/persistence.xml si pas springBoot
	@PersistenceContext //initialiser entityManager via application.properties au sein d'un projet SpringBoot
	private EntityManager entityManager;

	@Override
	public Compte findById(Long numCpt) {
		return entityManager.find(Compte.class, numCpt);
	}

	@Override
	public Compte save(Compte compte) {
		if(compte.getNumero()==null)
			entityManager.persist(compte);//INSERT INTO
		else
			entityManager.merge(compte);//UPDATE
		return compte; //avec numero plus null (auto_incrémenté)
	}

}
