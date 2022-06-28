package com.mycompany.xyz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MySpringBootApplication {

	public static void main(String[] args) {
		//SpringApplication.run(MySpringBootApplication.class, args);
		SpringApplication app = new SpringApplication(MySpringBootApplication.class);
		app.setAdditionalProfiles("initDataSet","embeddedDb");
		ConfigurableApplicationContext context = app.run(args);
		System.out.println("http://localhost:8181/mySpringBootApp/index.html");
	}

}
