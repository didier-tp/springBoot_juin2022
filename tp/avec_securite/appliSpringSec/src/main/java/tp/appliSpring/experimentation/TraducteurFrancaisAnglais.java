package tp.appliSpring.experimentation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component("FrancaisAnglais") ok mais moins bien
@Component
@Qualifier("FrancaisAnglais")
public class TraducteurFrancaisAnglais implements Traducteur {

	@Override
	public String traduire(String message) {
		switch(message) {
		case "bonjour" :
			return "hello"; 
		case "rouge":
			return "red"; 
		default :
			return "unknown";
		}
	}

}
