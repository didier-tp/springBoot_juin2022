package com.mycompany.xyz.test;

import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.mycompany.xyz.repository.RepositoryDevise;

//NB: cette classe associée au mini-profile "mock-dao" 
//n'est utile que pour la classe TestServiceDeviseWithDaoMockV1 
//et n'est plus nécessaire pour la V2 qui utilise @MockBean à la place de @Autowired

@Configuration
public class WithMockDaoConfig {

	 private static final Logger logger = LoggerFactory.getLogger(WithMockDaoConfig.class);

	    @Bean()
	    @Profile("mock-dao")
	    @Primary //for overriding default spring-data-jpa dao
	    public RepositoryDevise daoDeviseMock() {
	        logger.info("Mocking: {}", RepositoryDevise.class);
	        return Mockito.mock(RepositoryDevise.class);
	    }

}
