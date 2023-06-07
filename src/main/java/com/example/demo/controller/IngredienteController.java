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

import com.example.demo.pojo.Ingrediente;
import com.example.demo.pojo.OffertaSpeciale;
import com.example.demo.pojo.Pizza;
import com.example.demo.serv.IngredienteServ;
import com.example.demo.serv.PizzaService;

import jakarta.validation.Valid;

@Controller
public class IngredienteController {
	
	@Autowired
	private IngredienteServ ingredienteServ;
	
	@Autowired
	private PizzaService pizzaService;

	
	@GetMapping("/ingredienti")
	public String ingredientiShow(Model model) {
	
		List<Ingrediente> ingredienti = ingredienteServ.findAll();
		
		model.addAttribute("ingredienti", ingredienti);
		
		return "IngredienteIndex";
	}
	
	@GetMapping("/ingredienti/create")
	public String createIngrediente(Model model) {
		
		model.addAttribute("ingrediente", new Ingrediente());
		
		List<Pizza> pizze = pizzaService.findAll();
		model.addAttribute("pizze", pizze);
		
		return "ingredienteCreate";
	}
	
	@PostMapping("/ingredienti/create")
	public String storeIngrediente(
			Model model,
			@ModelAttribute Ingrediente ingrediente) {
		
		ingredienteServ.save(ingrediente);
		
		
		return "redirect:/ingredienti";
	}
	
	@GetMapping("/ingrediente/delete/{id}")
	public String deletePizza(
			@PathVariable int id
		) {
		
		Optional<Ingrediente> optIngrediente = ingredienteServ.findById(id);
		Ingrediente ingrediente = optIngrediente.get();
		ingredienteServ.deleteIngrediente(ingrediente);
		
		return "redirect:/home";
	}
}
