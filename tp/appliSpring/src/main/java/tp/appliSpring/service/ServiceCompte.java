package tp.appliSpring.service;

import java.util.List;

import tp.appliSpring.entity.Compte;

public interface ServiceCompte {
      Compte rechercherCompteParNumero(long numero);
      List<Compte> rechercherTousComptes();
      List<Compte> rechercherComptesDuClient(long numClient);
      Compte sauvegarderCompte(Compte compte);
      void supprimerCompte(long numCpt);
      void transferer(double montant,long numCptDeb,long numCptCred);
}
