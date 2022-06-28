package com.mycompany.xyz.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.xyz.dto.ResConversion;
import com.mycompany.xyz.entity.Devise;
import com.mycompany.xyz.service.ServiceDevise;


@RestController  //@RestController est un cas particulier de @Component
//@CrossOrigin(origins = "*")
@RequestMapping(value="/devise-api-rest" , headers="Accept=application/json")
public class DeviseRestCtrl {
	
	@Autowired
	private ServiceDevise serviceDevise;
	
	//http://localhost:8181/mySpringBootApp/devise-api-rest/public/devise/EUR
	@GetMapping("/public/devise//{codeDevise}")
	public Devise getDeviseByCode(@PathVariable("codeDevise") String codeDevise) {
		return serviceDevise.rechercherDeviseParCode(codeDevise);
	}
	
	
	//http://localhost:8181/mySpringBootApp/devise-api-rest/public/devise
	//http://localhost:8181/mySpringBootApp/devise-api-rest/public/devise?changeMini=1.05
	@GetMapping("/public/devise")
	public List<Devise> getDevisesByCriteria(
			  @RequestParam(value="changeMini",required=false) Double changeMini) {
		List<Devise> devises = null;
		if(changeMini!=null)
			devises= serviceDevise.rechercherDevisesParChangeMini(changeMini);
		else
			devises = serviceDevise.rechercherDevises();
		return devises;
	}
	
	//http://localhost:8181/mySpringBootApp/devise-api-rest/public/devise/conversion?amount=200&source=EUR&target=USD
		@GetMapping("/public/devise/conversion")
		public ResConversion  getResConversion(
				@RequestParam(value="amount") Double amount,
				@RequestParam(value="source")  String source,
				@RequestParam(value="target")  String target) {
			ResConversion resConv = new  ResConversion();
			resConv.setAmount(amount);
			resConv.setSource(source);
			resConv.setTarget(target);
			resConv.setResult(serviceDevise.convertir(amount, source, target));
			return resConv;
		}

}
