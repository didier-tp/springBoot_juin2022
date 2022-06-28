package tp.appliSpring.experimentation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("FrancaisAllemand")
public class TraducteurFrancaisAllemand implements Traducteur {

	@Override
	public String traduire(String message) {
		switch(message) {
		case "bonjour" :
			return "guten tag"; 
		case "rouge":
			return "rot"; 
		default :
			return "???";
		}
	}

}
