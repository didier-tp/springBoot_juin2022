package tp.appliSpring.dao;

import tp.appliSpring.entity.Compte;

public interface DaoCompte{
         Compte findById(Long numCpt);
         Compte save(Compte compte); //sauvegarde au sens saveOrUpdate
    //...
}