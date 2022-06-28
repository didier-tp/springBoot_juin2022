package com.mycompany.xyz.test;

import static org.mockito.ArgumentMatchers.anyString;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mycompany.xyz.entity.Devise;
import com.mycompany.xyz.repository.RepositoryDevise;
import com.mycompany.xyz.service.ServiceDeviseV2;

@ExtendWith(MockitoExtension.class) //for JUnit 5
public class TestAlgorithmiePurementUnitaire {
	
	//QUOI, des Mocks presque partout, mais de qui se moque-t-on ?
	
	private static Logger logger = LoggerFactory.getLogger(TestAlgorithmiePurementUnitaire.class);

	@InjectMocks
	/* @InjectMocks pour demander à "Mockito" (sans spring) de :
    - créer une instance normale de cette classe (ici new ServiceDeviseV2(...))
    - d'injecter le ou les @Mock(s) de cette classe de test dans la classe ServiceDeviseV2() via un constructeur adéquat
    */
	private ServiceDeviseV2 serviceDevise; //à partiellement tester d'un point de vue purement algorithmique 
	                                     //et sans s'appuyer sur le contexe spring
	
	@Mock /* @Mock pour demander à "Mockito" (sans spring) de :
	           - créer ultérieurement un Mock de l'interface 
	           - NB: il faudra appeler MockitoAnnotations.openMocks(this); 
	                  pour initialiser tous les mocks de this préfixés par @Mock
	           */
	private RepositoryDevise daoDeviseMock; //mock à utiliser
	

	@BeforeEach
	public void reInitMock() {
		//Mockito.initMocks(this); in old Junit 4
		MockitoAnnotations.openMocks(this); //with JUnit5/Jupiter
		/* MockitoAnnotations.openMocks(this) permet de créer des instances de chaque mock 
		   préfixé par @Mock au sein de this .
		   ce qui revient au même que d'écrire :
		      this.daoDeviseMock = Mockito.mock(RepositoryDevise.class);
		      this.mock2=Mockito.mock(Interface_ouClasse2.class);
		      s'il n'y avait pas d'utilisation de @Mock 
		 */
	}
	
	@Test
	public void testConvertir() {
		double montant=100;
		String codeDeviseSource="EUR";
		String codeDeviseCible="USD";
		double montantConverti=-1;
		//1.préparation du mock en arrière plan:
		Mockito.when(daoDeviseMock.findById(codeDeviseSource))
		       .thenReturn(Optional.of(new Devise("EUR","Euro",1.0)));
		Mockito.when(daoDeviseMock.findById(codeDeviseCible))
	           .thenReturn(Optional.of(new Devise("USD","Dollar",1.1)));
		//2.appel de la méthode convertir sur le service et test retour
		montantConverti = serviceDevise.convertir(montant, codeDeviseSource, codeDeviseCible);
		logger.debug("montantConverti="+montantConverti);
		Assertions.assertEquals(montant * 1.1 , montantConverti, 0.000001);
		//3.verif service appelant 2 fois deviseDao.findById() via aspect spy de Mockito:
		Mockito.verify(daoDeviseMock, Mockito.times(2)).findById(anyString());
	}	
	
	@Test()
	public void testConvertirAvecDeviseInconnue() {
		double montant=100;
		String codeDeviseSource="EUR";
		String codeDeviseCibleInconnu="C??";
		double montantConverti=-1;
		//1.préparation du mock en arrière plan:
		Mockito.when(daoDeviseMock.findById(codeDeviseSource))
		       .thenReturn(Optional.of(new Devise("EUR","Euro",1.0)));
		Mockito.when(daoDeviseMock.findById(codeDeviseCibleInconnu))
	           .thenReturn(Optional.empty());
		//2.appel de la méthode convertir sur le service et test exception en retour
		try {
		  montantConverti = serviceDevise.convertir(montant, codeDeviseSource, codeDeviseCibleInconnu);
		  Assertions.fail("une exception aurait normalement du remonter");
		}catch(Exception ex) {
			logger.debug("exception normalement attendue="+ex.getMessage());
			Assertions.assertTrue(ex.getClass().getSimpleName().equals("NotFoundException"));
		}
	}
		
		
}
