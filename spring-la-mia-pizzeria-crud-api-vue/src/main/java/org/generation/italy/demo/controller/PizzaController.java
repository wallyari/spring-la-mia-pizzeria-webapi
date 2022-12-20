package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Ingrediente;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promozione;
import org.generation.italy.demo.serv.IngredienteService;
import org.generation.italy.demo.serv.PizzaService;
import org.generation.italy.demo.serv.PromozioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizza")
public class PizzaController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private PromozioneService promoService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	

	@GetMapping("/user")
	public String index(Model model) {
	
	List<Pizza> pizzas = pizzaService.findAll();
	model.addAttribute("pizzas", pizzas);
	
	return "index";
	}

	@GetMapping("/user/{id}")
	public String getPizza(@PathVariable("id") int id, Model model) {
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		
		
		List<Promozione> promozioni = promoService.findAll(); 
		model.addAttribute("promozioni", promozioni);
			
		
		Optional <Pizza> optPizza = pizzaService.getPizzaById(id);
		if(optPizza.isEmpty()) {
			System.err.println("Pizza non presente con id: " + id);
		}
		Pizza pizza = optPizza.get();
		
		model.addAttribute("pizza", pizza);
		return "pizza";
		
	}
	
	@GetMapping("/admin/create")
	public String createPizza(Model model) {
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		
		Pizza pizza = new Pizza();
		model.addAttribute("pizza", pizza);
		
		List<Promozione> promozioni = promoService.findAll(); 
		model.addAttribute("promozioni", promozioni);
		
		return "pizza-create";
	}
	
	@PostMapping ("/admin/create")

 	public String storePizza(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if(bindingResult.hasErrors()) {
 			
 			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());			
 			return "redirect:/pizza/user";
 		}

 		try {			
 			pizzaService.save(pizza);
 		
 		} catch (Exception e) {
 			
 			final String msg = e.getMessage(); 			
 			System.err.println(msg);			
 			redirectAttributes.addFlashAttribute("catchError", msg);			
 			return "redirect:/pizza/create";
 		}
		
		return "redirect:/pizza/user";
	}

	
	@GetMapping ("/admin/update/{id}")
	public String getEditPizza(@PathVariable("id") int id, Model model) {
		
		Optional <Pizza> optPizza = pizzaService.getPizzaById(id);
		if(optPizza.isEmpty()) {
			System.err.println("Pizza non presente con id: " + id);
		}
		Pizza pizza = optPizza.get();
		

		List<Promozione> promozioni = promoService.findAll(); 
		model.addAttribute("promozioni", promozioni);
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
 		model.addAttribute("ingredienti", ingredienti);
		
		model.addAttribute("pizza", pizza);
		return "pizza-update";
	}
	
	@PostMapping("/admin/update")
	public String updatePizza(@Valid @ModelAttribute("pizza") Pizza pizza,  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if(bindingResult.hasErrors()) {
 			
 			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());			
 			return "redirect:/drink/create";
 		}

 		try {			
 			pizzaService.save(pizza);
 		
 		} catch (Exception e) {
 			
 			final String msg = e.getMessage(); 			
 			System.err.println(msg);			
 			redirectAttributes.addFlashAttribute("catchError", msg);			
 			return "redirect:/pizza/create";
 		}
		
		return "redirect:/pizza/user";
	}
	
	@GetMapping("/admin/delete/{id}")
	public String deletePizza(@PathVariable("id") int id) {
		
		
		pizzaService.deletePizzaById(id);
		
		return "redirect:/pizza/user";
	}
	
	@GetMapping("/user/search")
	public String getSearchPizzaByName(Model model,
			@RequestParam(name = "q", required=false)String query) {
				
			List<Pizza> pizzas = query == null
			? pizzaService.findAll()
			: pizzaService.findByNome(query);
			
			model.addAttribute("pizzas", pizzas);
			model.addAttribute("query", query);
			return "pizza-search";
		}

}
