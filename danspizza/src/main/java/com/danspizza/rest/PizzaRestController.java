package com.danspizza.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danspizza.service.PizzaService;

@RestController
@RequestMapping("/api")
public class PizzaRestController {

	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping("/getall")
	public Map<Integer, String> getPizzaDetails() {
		
		return pizzaService.findAllPizzaDetails();
		
	}
	
	
	
}
