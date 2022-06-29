package tp.appliSpring.rest;

import tp.appliSpring.entity.Compte;
import tp.appliSpring.service.ServiceCompte;

//...
public class CompteRestCtrl {
	
	//...
	private ServiceCompte serviceCommpte;
	
    //http://localhost:8080/appliSpring/api-bank/compte/1
	//....
	//future version: public CompteDto getCompteByNum(Long numCpt) {
	public Compte getCompteByNum(Long numCpt) {
		
	}
	
	//http://localhost:8080/appliSpring/api-bank/compte
	//http://localhost:8080/appliSpring/api-bank/compte?numClient=1
	//....
	//future version: public CompteDto getCompteByNum(Long numCpt) {
	public List<Compte> getComptesByCriteria(Long numClient) {
			
	}
}
