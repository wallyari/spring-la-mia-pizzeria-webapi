
package org.generation.italy.demo.api.controller;
import java.util.List;
import org.generation.italy.demo.pojo.Ingrediente;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



 @RestController
 @RequestMapping("/api/1/ingredienti")
 @CrossOrigin("*")
 public class IngredienteApiController {
	 
	 @Autowired
	 private PizzaService pizzaService;
 	
	 @GetMapping("/by/pizza/{id}")
 	public List<Ingrediente> getIngredientiByPizzaId
 	(@PathVariable("id")int id)
 	{
 		Pizza pizza = pizzaService.getPizzaById(id).get();
 		
		 return pizza.getIngredienti();
 	}
 	

 }