package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.pojo.OffertaSpeciale;
import com.example.demo.pojo.Ingrediente;
import com.example.demo.pojo.Pizza;
import com.example.demo.serv.OffertaSpecialeServ;
import com.example.demo.serv.PizzaService;

import jakarta.validation.Valid;

@Controller
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping("/home")
	public String homeView(Model model) {
	
		List<Pizza> pizze = pizzaService.findAll();
		
		model.addAttribute("pizze", pizze);
		
		return "index";
	}
	
	@GetMapping("/pizze/{id}")
	public String showView(Model model,
			@PathVariable("id") int id) {
	
		Optional<Pizza> optPizza = pizzaService.findById(id);
		Pizza pizza = optPizza.get();
		
		List<OffertaSpeciale> offSpeciali = pizza.getOfferteSpeciali();
		List<Ingrediente> ingredienti = pizza.getIngredienti();

		model.addAttribute("pizza", pizza);
		model.addAttribute("offSpeciali", offSpeciali);
		model.addAttribute("ingredienti", ingredienti);
		
		return "show";
	}
	
	@PostMapping("/pizze/by/name")
	public String search(Model model,
			@RequestParam(required = false) String name) {
	
		List<Pizza> pizze = pizzaService.findByName(name);
		
		model.addAttribute("pizze", pizze);
		model.addAttribute("name", name);
		
		return "search";
	}
	
	@GetMapping("/pizze/create")
	public String createPizza(Model model) {
		
		model.addAttribute("pizza", new Pizza());
		
		return "create";
	}
	
	@PostMapping("/pizze/create")
	public String storePizza(
			Model model,
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			model.addAttribute("pizza", pizza);
			model.addAttribute("errors", bindingResult);
			
			return "create";
		}
		
		pizzaService.save(pizza);
		
		return "redirect:/home";
	}
	
	@GetMapping("/pizze/update/{id}")
	public String editPizza(
			Model model,
			@PathVariable int id
		) {
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		Pizza pizza = optPizza.get();
		model.addAttribute("pizza", pizza);
		
		return "update";
	}
	
	@PostMapping("/pizze/update/{id}")
	public String updatePizza(
			Model model,
			@PathVariable int id,
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult
		) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			model.addAttribute("pizza", pizza);
			model.addAttribute("errors", bindingResult);
			
			return "update";
		}
		
		pizza.setId(id);
		pizzaService.save(pizza);
		
		return "redirect:/home";
	}
	
	@GetMapping("/pizze/delete/{id}")
	public String deletePizza(
			@PathVariable int id
		) {
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		Pizza pizza = optPizza.get();
		pizzaService.deletePizza(pizza);
		
		return "redirect:/home";
	}
	
	
}
