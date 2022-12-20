
package org.generation.italy.demo.api.controller;
import java.util.List;


import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

 @RestController
 @RequestMapping("/api/1/pizza")
 @CrossOrigin("*")
 public class PizzaApiController {

 	@Autowired
 	private PizzaService pizzaService;

 	@GetMapping("/all")
 	public List<Pizza> getAll() {
 		List<Pizza> allPizzas = pizzaService.findAll();
		return allPizzas ;
 	}
 	
 	@PostMapping("/create")
 	public Pizza createPizza(@Valid @RequestBody Pizza pizza){
 		
 		System.err.println(pizza);
 		Pizza newPizza= pizzaService.save(pizza);
 		System.err.println(newPizza);
 		
 		return newPizza;
 	
 	}
 	
 	@PostMapping("/update/{id}")
 	public Pizza updatePizza(@PathVariable("id") int id, @Valid @RequestBody Pizza pizza) {
 		
 		//pizza da db
 		 Pizza oldPizza = pizzaService.getPizzaById(id).get();
 		//valorizzo la nuova pizza
 		 pizza.setIngredienti(oldPizza.getIngredienti());
 		
 		 //System.err.println("id: "+ id);
 		//System.err.println("pizza: "+ pizza);
 		pizzaService.save(pizza);
 		//ritorno l'oggetto dal DB
 		
 		Pizza newPizza = pizzaService.getPizzaById(id).get();
 		return newPizza;
 	}
 	

 	
 	@GetMapping("/delete/{id}")
	public boolean deletePizza(@PathVariable("id") int id) {
		
		try {
			pizzaService.deletePizzaById(id);
		} catch(Exception e) {return false; }
		return true;
	}
 	
	@GetMapping("/search/{query}")
 	public List<Pizza> getSearchPizzaByNome(@PathVariable("query") String query) {
 		System.err.println(query);
 		List<Pizza> allPizzas = query == null 
 				? pizzaService.findAll() 
 				: pizzaService.findByNome(query);
 		return allPizzas;
 	}
 	
 }

