package com.mycompany.xyz.partial_config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@Profile("partial_config")
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.mycompany.xyz.service"  }  )
//@ComponentScan(basePackages = { "com.mycompany.xyz.service"  } , resourcePattern = "**/ServiceDevise*.class")
@EnableJpaRepositories(basePackages = { "com.mycompany.xyz.repository" }) 
@EntityScan(basePackages = { "com.mycompany.xyz.entity"})
public class ServiceAndDaoConfig {

}

/*
 Usage:
 //@SpringBootTest(classes= {MySpringBootApplication.class})
  @SpringBootTest(classes= {ServiceAndDaoConfig.class})
  in order to load "DAO + Service" commponents only in service test
  no need of "RestController" components for internal business service tests
  ServiceAndDaoConfig is more light than all MySpringBootApplication .
 */
