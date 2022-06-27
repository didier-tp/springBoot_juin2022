package tp.appliSpring.dao;

import java.util.HashMap;
import java.util.Map;

import tp.appliSpring.entity.Compte;

public class DaoCompteSimu implements DaoCompte{
	
	private long compteur=0;
	private Map<Long,Compte> mapComptes = new HashMap<>();

	@Override
	public Compte findById(Long numCpt) {
		return mapComptes.get(numCpt); //compte selon clef
	}

	@Override
	public Compte save(Compte compte) {
		compte.setNumero(++compteur);  //simule auto_incrémentation
		mapComptes.put(compte.getNumero(),compte); //ajoute dans la table d'association
		              //une association entre numero de commpte et le compte complet
		return compte;
	}

}
