package com.mycompany.xyz.test;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mycompany.xyz.MySpringBootApplication;
import com.mycompany.xyz.ex.MySpringSetEx;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes= {MySpringBootApplication.class})
@ActiveProfiles("embeddedDb")
//@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TestWithOrWithoutDirtyContext {
	
	private static Logger logger = LoggerFactory.getLogger(TestWithOrWithoutDirtyContext.class);
	
	@Autowired
	private MySpringSetEx mySpringSetEx; //à tester/utiliser
	
	@Test
	@Order(1)
	public void addAndGetData1InMySpringContext() {
		mySpringSetEx.addDataInExDataSet("data_1a");
		mySpringSetEx.addDataInExDataSet("data_1b");
		Set<String> dataSet1 = mySpringSetEx.getExDataSet();
		logger.debug("dataSet1 =  " + dataSet1); //dataSet1 =  [data_1a, data_1b]
		Assertions.assertTrue(dataSet1.size() == 2);
	}
	
	/*
	@Test
	@Order(2)
	public void addAndGetData2InMySpringContextWithoutDirtiesContextBadTest() {
		mySpringSetEx.addDataInExDataSet("data_2a");
		mySpringSetEx.addDataInExDataSet("data_2b");
		Set<String> dataSet2 = mySpringSetEx.getExDataSet();
		logger.debug("dataSet2 =  " + dataSet2); //dataSet2 =  [data_1a, data_2b, data_2a, data_1b]
		Assertions.assertTrue(dataSet2.size() == 2); //failing test:  4 != 2
	}
	*/
	
	//@DirtiesContext demande à réinitialiser le contextSpring (et tout son contenu : tous ses commposants)
	//de façon à obtenir des tests aux comportements plus "unitaires" 
	//(sans effets de bord engendrés par les tests précédents)
	//Ne pas en abuser car cela peut ralentir l'exécution d'une séquence de tests
	//Si placé sur une méthode : @DirtiesContext(methodMode = MethodMode.BEFORE_METHOD or MethodMode.AFTER_METHOD)
	//Si placé sur la classe de test : @DirtiesContext(classMode = ClassMode.BEFORE_CLASS or 
	//        ClassMode.BEFORE_EACH_TEST_METHOD or ClassMode.AFTER_EACH_TEST_METHOD or ClassMode.AFTER_CLASS)
	@Test
	@Order(2)
	@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
	public void addAndGetData2InMySpringContext() {
		mySpringSetEx.addDataInExDataSet("data_2a");
		mySpringSetEx.addDataInExDataSet("data_2b");
		Set<String> dataSet2 = mySpringSetEx.getExDataSet();
		logger.debug("dataSet2 =  " + dataSet2); //dataSet2 =  [data_2b, data_2a]
		Assertions.assertTrue(dataSet2.size() == 2); //with success
	}
    
}
