package tp.appliSpring.dto;

import java.util.ArrayList;
import java.util.List;

import tp.appliSpring.entity.Devise;

public class DtoUtil {

	public static List<Currency> deviseListToCurrencyList(List<Devise> listeDevise){
		List<Currency> listeCurrency = new ArrayList<>();
		for(Devise d : listeDevise) {
			listeCurrency.add(new Currency(d.getCode(),d.getNom(),d.getChange()));
		}
		return listeCurrency; 
	}

}
