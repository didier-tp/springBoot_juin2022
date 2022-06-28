package tp.appliSpring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tp.appliSpring.entity.Compte;

public interface DaoCompte extends JpaRepository<Compte,Long>{
    /*
     par héritage de JpaRepository ou CrudRepository on a les 
     principales méthodes suivantes:
     Optional<Compte> findById(Long numCpt)
      Iterable<Compte>  findAll()
                        save(Compte)
                        deleteById(...)
     */
     
    //methode de recherche complémentaire selon convention de nom de méthode
	List<Compte>  findBySoldeGreaterThanEqual(double soldeMini);
	List<Compte>  findByClientId(long numClient);
	             //Compte comporte client d'où findByClient
	             //et Commpte.client comporte id d'où findByClientId
	
}