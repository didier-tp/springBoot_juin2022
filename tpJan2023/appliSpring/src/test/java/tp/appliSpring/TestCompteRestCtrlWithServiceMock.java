package tp.appliSpring;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import tp.appliSpring.entity.Compte;
import tp.appliSpring.rest.CompteRestCtrl;
import tp.appliSpring.service.ServiceCompte;

@ExtendWith(SpringExtension.class) //si junit5/jupiter
@WebMvcTest(CompteRestCtrl.class)
//NB: @WebMvcTest without security and without service layer , service must be mocked !!!
public class TestCompteRestCtrlWithServiceMock {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ServiceCompte compteService; //not real implementation but mock to configure .
	
	@BeforeEach
	public void reInitMock() {
		//vérification que le service injecté est bien un mock		
		assertTrue(Mockito.mockingDetails(compteService).isMock());
		//reinitialisation du mock(de scope=Singleton par defaut) sur aspects stub et spy 
		Mockito.reset(compteService);
	}

	
	@Test //à lancer sans le profile withSecurity
	public void testComptesAvecSoldeMiniWithMockOfCompteService(){
	//préparation du mock (qui sera utilisé en arrière plan du contrôleur rest à tester):
	List<Compte> comptes = new ArrayList<>();
	comptes.add(new Compte(1,"compteA",140.0));
	comptes.add(new Compte(2,"compteB",170.0));
	Mockito.when(compteService.rechercherComptesAvecSoldeMini(80.0)).thenReturn(comptes);
	try {
		MvcResult mvcResult = 
		mvc.perform(get("/api-bank/compte?soldeMini=80")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(2) ))
		.andExpect(jsonPath("$[0].label", is("compteA") ))
		.andExpect(jsonPath("$[1].solde", is(170.0) ))
		.andReturn();
		System.out.println(">>>>>>>>> jsonResult="+mvcResult.getResponse().getContentAsString());
	} catch (Exception e) {
		System.err.println(e.getMessage());
	}
	}
}
