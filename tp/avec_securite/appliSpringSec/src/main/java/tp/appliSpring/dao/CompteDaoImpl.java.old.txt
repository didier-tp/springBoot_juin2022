package tp.appliSpring.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import tp.appliSpring.entity.Compte;

@Transactional //pour entityManager.getTransaction().begin/commit/rollback automatiques
//@Component
@Repository //@Repository est une sorte de @Component (spécialisé "DAO")
public class CompteDaoImpl implements CompteDao {
	
	@PersistenceContext() //pour initialiser le entityManager
	//à partir de META-INF/persistence.xml sur un projet JEE/EJB ou Spring ancien
	//ou bien à partir de application.properties sur un projet springBoot
	private EntityManager entityManager;

	@Override
	public Optional<Compte> findById(Long numero) {
		return Optional.of(entityManager.find(Compte.class, numero));
	}

	@Override
	public void deleteById(Long numero) {
		Compte compte = entityManager.find(Compte.class, numero);
        entityManager.remove(compte);
	}

	@Override
	public Compte save(Compte compte) {
		//save() au sens saveorUpdate().
		if(compte.getNumero()==null) {
			entityManager.persist(compte); //INSERT INTO avec auto_increment
			//après le .persist() compte.getNumero() n'est plus null si auto_increment
		}else {
			entityManager.merge(compte); //Update SQL
		}
		return compte;
	}

	@Override
	public List<Compte> findAll() {
		/*
		return entityManager.createQuery("SELECT c FROM Compte c", Compte.class)
				            .getResultList();
	   */
		return entityManager.createNamedQuery("Compte.findAll", Compte.class)
	            .getResultList();
	}

}
