package com.mycompany.xyz.rest;

import java.util.HashMap;
import java.util.Map;

import org.mygeneric.encadreur.Encadreur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/rest/basic" , headers="Accept=application/json")
public class BasicCtrl {
	
	@Autowired(required = false)
	private Encadreur encadreur;
	
	@GetMapping("/encadrer/{chaine}")
	public Map<String,Object> encadrer(@PathVariable("chaine") String chaine) {
		if(encadreur!=null)
		  return basicMessageMapForJson(encadreur.encadrerAvecFormat(chaine));
		else 
			return basicMessageMapForJson(chaine);
	}
	
	public static Map<String,Object> basicMessageMapForJson(String s) {
		Map<String,Object> map = new HashMap<>();
		map.put("message", s);
		return map;
	}

}
