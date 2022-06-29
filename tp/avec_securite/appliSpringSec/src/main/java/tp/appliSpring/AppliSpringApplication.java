package tp.appliSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//NB: @SpringBootApplication est un équivalent
//de @Configuration + @EnableAutoConfiguration + @ComponentScan/current package

@SpringBootApplication
//@Import(TraducteurConfig.class) //si TraducteurConfig dans tpa et pas dans tp.appliSpring
public class AppliSpringApplication {

	public static void main(String[] args) {
		//SpringApplication.run(AppliSpringApplication.class, args);
		SpringApplication app = new SpringApplication(AppliSpringApplication.class);
		app.setAdditionalProfiles("embeddedDb","init","withSecurity");
		//ou bien java .... -Dspring.profiles.active=init,embeddedDb dans .bat ou .sh
		ConfigurableApplicationContext context = app.run(args);
		System.out.println("http://localhost:8080/appliSpring");
	}

}
