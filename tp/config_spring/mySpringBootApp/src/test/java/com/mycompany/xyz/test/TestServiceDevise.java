package com.mycompany.xyz.test;

//pour assertTrue (res==5) au lieu de Assertions.assertTrue(res==5)
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mycompany.xyz.entity.Devise;
import com.mycompany.xyz.partial_config.ServiceAndDaoConfig;
import com.mycompany.xyz.service.ServiceDevise;

@ExtendWith(SpringExtension.class)

//@SpringBootTest
//@SpringBootTest(classes= {MySpringBootApplication.class})
@SpringBootTest(classes= {ServiceAndDaoConfig.class})
@ActiveProfiles({"embeddedDb","partial_config"})
public class TestServiceDevise {
	
	private static Logger logger = LoggerFactory.getLogger(TestServiceDevise.class);
	
	@Autowired
	private ServiceDevise serviceDevise; //à tester
	
	public void init() {
		serviceDevise.createDevise(new Devise("EUR","Euro",1.0));
		serviceDevise.createDevise(new Devise("USD","Dollar",1.1));
		serviceDevise.createDevise(new Devise("GBP","Livre",0.9));
		serviceDevise.createDevise(new Devise("JPY","Yen",123.6));
	}
	
	@Test
	public void testRechercherDevises() {
		init();
		List<Devise> listeDevises = serviceDevise.rechercherDevises();
		logger.debug("listeDevises="+listeDevises);
		assertTrue(listeDevises.size()>=4);
	}
	
	

}
