package tp.appliSpring.experimentation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tp.appliSpring.AppliSpringApplication;

@ExtendWith(SpringExtension.class) //si junit5/jupiter
@SpringBootTest(classes= {AppliSpringApplication.class}) 
//@SpringBootTest(classes= {AppliSpringApplication.class , TraducteurConfig.class}) si TraducteurConfig dans package tpa et pas dans tp.appliSpring
 class TestAfficheurTraducteur {
	
	
	private static Logger logger = LoggerFactory.getLogger(TestAfficheurTraducteur.class);

	@Autowired //injection de dépendance spring
	private Afficheur afficheur; //à tester
	
	@Test
	public void test1() {
		afficheur.traduireEtAfficher("rouge");
		afficheur.traduireEtAfficher("bonjour");
		//juste tester pas d'exception
	}
	

}
