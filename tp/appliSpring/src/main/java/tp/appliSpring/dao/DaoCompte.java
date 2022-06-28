package tp.appliSpring.dao;

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

    //...
}