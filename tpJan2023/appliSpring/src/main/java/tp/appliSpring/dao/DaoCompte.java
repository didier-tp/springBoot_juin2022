package tp.appliSpring.dao;

import org.springframework.data.repository.CrudRepository;

import tp.appliSpring.entity.Compte;

import java.util.List;


/*
DaoCompte ou bien RepositoryCompte = objet spécialisé dans l'accès aux données
DAO = Data Access Object (et "Repository" = synomyme de Spring)
avec méthodes CRUD (Create , Research , Update , Delete)
 .... extends CrudRepository<TypeEntity,TypeClefPrimaire>
 --------------
 le framework Spring-data va générer automatiquent une vraie classe d'implémentation
 en analysant la structure de l'interface.
 */
public interface DaoCompte extends CrudRepository<Compte,Integer>{
  /*
  méthodes héritées de CrudRepository :
      Optional<Compte> .findById(Integer numero)
      List<Compte>   .findAll()
      Compte .save(Compte c)  au sens saveOrUpdate : INSERT INTO ou bien UPDATE
      .deleteById(Integer numero)
   */
    List<Compte> findBySoldeGreaterThanEqual(double soldeMini); //méthode de recherche selon convention de nom de méthode
    List<Compte> findByClientNumero(int numClient); //finbBy + "sous partie client" de la classe Compte
                                                          // + "sous partie numero" de la clesses Client
   List<Compte> findByNumeroDeClientQueJaime(int numClient); //sans convention de nom et avec select personnalisé
                                                             //dans @NamedQuery(name="Compte.findByNumeroDeClientQueJaime")
                                                             //placée au dessus de la classe de données Compte
}
