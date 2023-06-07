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

import com.example.demo.pojo.OffertaSpeciale;
import com.example.demo.pojo.Pizza;
import com.example.demo.serv.OffertaSpecialeServ;
import com.example.demo.serv.PizzaService;

import jakarta.validation.Valid;

@Controller
public class OffertaSpecialeController {

	@Autowired
	private OffertaSpecialeServ offertaSpecialeServ;
	
	@Autowired
	private PizzaService pizzaService;
	
	
	@GetMapping("/offertaSpeciale/{id}")
	public String showView(Model model,
			@PathVariable("id") int id) {
	
		Optional<OffertaSpeciale> optOffSpeciale = offertaSpecialeServ.findById(id);
		OffertaSpeciale offertaSpeciale = optOffSpeciale.get();
	
		model.addAttribute("offertaSpeciale", offertaSpeciale);
		
		
		return "OffertaSpecialeShow";
	}
	
	@GetMapping("/OffertaSpeciale/create")
	public String createOffertaSpeciale(Model model) {
		
		List<Pizza> pizze = pizzaService.findAll();
		
		model.addAttribute("offertaSpeciale", new OffertaSpeciale());
		model.addAttribute("pizze", pizze);
		
		return "createOffertaSpeciale";
	}
	
	@PostMapping("/OffertaSpeciale/create")
	public String storeOffertaSpeciale(
			Model model,
			@Valid @ModelAttribute OffertaSpeciale offertaSpeciale,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			model.addAttribute("offertaSpeciale", offertaSpeciale);
			model.addAttribute("errors", bindingResult);
			
			return "createOffertaSpeciale";
		}
		
		offertaSpecialeServ.save(offertaSpeciale);
		
		return "redirect:/home";
	}
	
	@GetMapping("/OffertaSpeciale/update/{id}")
	public String editPizza(
			Model model,
			@PathVariable int id
		) {
		
		List<Pizza> pizze = pizzaService.findAll();
		Optional<Pizza> currentPizzaServ = pizzaService.findById(id);
		Pizza currentPizza = currentPizzaServ.get();
		Optional<OffertaSpeciale> optOffertaSpeciale = offertaSpecialeServ.findById(id);
		OffertaSpeciale offertaSpeciale = optOffertaSpeciale.get();
		
		model.addAttribute("currentPizza", currentPizza);
		model.addAttribute("pizze", pizze);
		model.addAttribute("offertaSpeciale", offertaSpeciale);
		
		return "updateOffertaSpeciale";
	}
	
	@PostMapping("/OffertaSpeciale/update/{id}")
	public String updatePizza(
			Model model,
			@PathVariable int id,
			@Valid @ModelAttribute OffertaSpeciale offertaSpeciale,
			BindingResult bindingResult
		) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			List<Pizza> pizze = pizzaService.findAll();
			model.addAttribute("pizze", pizze);
			model.addAttribute("offertaSpeciale", offertaSpeciale);
			model.addAttribute("errors", bindingResult);
			
			return "updateOffertaSpeciale";
		}
		
		offertaSpeciale.setId(id);
		offertaSpecialeServ.save(offertaSpeciale);
		
		return "redirect:/home";
	}
	
	@GetMapping("/OffertaSpeciale/delete/{id}")
	public String deletePizza(
			@PathVariable int id
		) {
		
		Optional<OffertaSpeciale> optOffertaSpeciale = offertaSpecialeServ.findById(id);
		OffertaSpeciale offertaSpeciale = optOffertaSpeciale.get();
		offertaSpecialeServ.deleteOffertaSpeciale(offertaSpeciale);
		
		return "redirect:/home";
	}
	
	
}
