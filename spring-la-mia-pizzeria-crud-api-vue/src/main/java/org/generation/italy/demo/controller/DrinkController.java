package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.serv.DrinkService;
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
@RequestMapping("/drink")

public class DrinkController {
	@Autowired
	private DrinkService drinkService;
	
	@GetMapping
	public String index(Model model) {
		
		List<Drink> drinks = drinkService.findAll();
		model.addAttribute("drinks", drinks);
		
		return "drink-index";
	}
	
	@GetMapping("/{id}")
	public String getDrink(@PathVariable("id") int id, Model model) {
		
		Optional <Drink> optDrink = drinkService.getDrinkById(id);
		if(optDrink .isEmpty()) {
			System.err.println("Drink non presente con id: " + id);
		}
		Drink drink = optDrink.get();
		
		model.addAttribute("drink",drink);
		return "drink";
		
	}
	
	@GetMapping("/create")
	public String getCreateDrink(Model model) {
		
		Drink drink = new Drink();
		model.addAttribute("drink", drink);
		
		return "drink-create";
	}
	
	@PostMapping("/create")
	public String storeDrink(@Valid @ModelAttribute("drink") Drink drink, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

 		if(bindingResult.hasErrors()) {
 			
 			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());			
 			return "redirect:/drink/create";
 		}

 		try {			
 			drinkService.save(drink);
 		
 		} catch (Exception e) {
 			
 			final String msg = e.getMessage(); 			
 			System.err.println(msg);			
 			redirectAttributes.addFlashAttribute("catchError", msg);			
 			return "redirect:/drink/create";
 		}
		
		return "redirect:/drink";
	}
	
	
	
	
	@GetMapping("/update/{id}")
	public String getEditDrink(@PathVariable("id") int id, Model model) {
		
		Optional<Drink> optDrink = drinkService.getDrinkById(id);
		Drink drink = optDrink.get();
		
		model.addAttribute(drink);
		return "drink-update";
	}
	
	@PostMapping("/store")
	public String updateDrink(@Valid @ModelAttribute("drink") Drink drink,  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

if(bindingResult.hasErrors()) {
 			
 			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());			
 			return "redirect:/drink/create";
 		}

 		try { 			
 			drinkService.save(drink);
 		
 		} catch (Exception e) {			
 			
 			final String msg = e.getMessage();			
 			System.err.println(msg); 			
 			redirectAttributes.addFlashAttribute("catchError", msg);			
 			return "redirect:/drink/create";
 		}	
		
 		return "redirect:/drink";
	}
	
	
	@GetMapping("/delete/{id}")
	public String deleteDrink(@PathVariable("id") int id, Model model) {
		
		drinkService.deleteDrinkById(id);
		return "redirect:/drink";
	}
	
	
	@GetMapping("/search")
	public String getSearchDrinkByName(Model model,
			@RequestParam(name = "q", required=false)String query) {
				
			List<Drink> drinks = query == null
			? drinkService.findAll()
			: drinkService.findByNome(query);
			
			model.addAttribute("drinks", drinks);
			model.addAttribute("query", query);
			return "drink-search";
		}

		
	}

	

