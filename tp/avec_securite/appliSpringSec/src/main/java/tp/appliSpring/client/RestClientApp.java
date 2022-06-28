package tp.appliSpring.client;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import tp.appliSpring.dto.Currency;
import tp.appliSpring.dto.LoginRequest;
import tp.appliSpring.dto.LoginResponse;

public class RestClientApp {
	
	public static String token="?";

	public static void main(String[] args) {
		postLoginForToken();
		posterNouvelleDevise();

	}
	
	private static void postLoginForToken() {
		WebClient.Builder builder = WebClient.builder();
		String baseUrl="http://localhost:8080/appliSpring/api-bank";
		WebClient webClient = builder
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
		
		
		LoginRequest loginRequest = new LoginRequest("admin1","pwd1");
		
		//envoyer cela via un appel en POST
		Mono<LoginResponse> reactiveStream = webClient.post().uri("/public/login")
				.body(Mono.just(loginRequest), LoginRequest.class)
				.retrieve()
	            .bodyToMono(LoginResponse.class)
	            .onErrorReturn(new LoginResponse("admin1",false,"login failed",null));
		LoginResponse loginResponse = reactiveStream.block();
		
		System.out.println("loginResponse=" + loginResponse.toString());
		if(loginResponse.getOk())
			 token = loginResponse.getToken();
		}

	private static void posterNouvelleDevise() {
		WebClient.Builder builder = WebClient.builder();
		String baseUrl="http://localhost:8080/appliSpring/api-bank";
		WebClient webClient = builder
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();
		
		//créer une instance du DTO Currency
		//avec les valeurs 
		//{ "code" : "DDK" , "name" : "couronne danoise" , "rate" : 7.77 }
		
		Currency currencyDDK = new Currency("DDK","couronne danoise" , 7.77);
		
		//envoyer cela via un appel en POST
		Mono<Currency> reactiveStream = webClient.post().uri("/devise")
				.body(Mono.just(currencyDDK), Currency.class)
				.retrieve()
	            .bodyToMono(Currency.class)
	            .onErrorReturn(new Currency("?","not saved !!",0.0));
		Currency savedCurrency = reactiveStream.block();
		
		System.out.println("savedCurrency=" + savedCurrency.toString());
		}

}
