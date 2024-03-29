package tp.appliSpring.service;

import tp.appliSpring.entity.Compte;

import java.util.List;
//en cas d'erreur , remontées de RuntimeException (ou des cas particuliers)
public interface ServiceCompte {
    Compte rechercherCompteParNumero(int numCompte);
    List<Compte> rechercherComptesDuClient(int numClient);
    List<Compte> rechercherTousComptes();
    List<Compte> rechercherComptesAvecSoldeMini(double soldeMini);
    Compte sauvegarder(Compte compte); //saveOrUpdate
    Compte sauvegarderNouveau(Compte compte); //INSERT INTO
    Compte sauvegarderExistant(Compte compte); //UPDATE
    void supprimerCompte(int numCompte);
    void transferer(double montant , int numCptDeb , int numCptCred);//virement
}
