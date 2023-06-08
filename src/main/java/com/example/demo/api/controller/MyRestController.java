package com.example.demo.api.controller;

import java.util.List;
import java.util.Optional;

import javax.management.modelmbean.RequiredModelMBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.controller.dto.PizzaResponseDto;
import com.example.demo.pojo.Ingrediente;
import com.example.demo.pojo.Pizza;
import com.example.demo.serv.PizzaService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class MyRestController {
	
	@Autowired
	private PizzaService pizzaService;
	
	// this will show the list of all the pizzas
	@GetMapping("/pizze")
	public ResponseEntity<List<Pizza>> getPizze() {
		
		List<Pizza> pizze = pizzaService.findAll();
		
		return new ResponseEntity<>(pizze, HttpStatus.OK);
	}
	
	// this will show the results of the name searched, even partial research
	@GetMapping("/pizze/by/name")
	public ResponseEntity<List<Pizza>> sayHello(
			@RequestParam(required = false) String name) {
		
		List<Pizza> pizze = pizzaService.findByName(name);
		
		return new ResponseEntity<>(pizze, HttpStatus.OK);
	}
	
	// this will show the details of the single pizza, searched by id
	@GetMapping("/pizze/{id}")
	public ResponseEntity<Pizza> searchPizza(
			@PathVariable int id) {
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		Pizza pizza = optPizza.get();
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	// this will create a new pizza
	@PostMapping("/pizze")
	public ResponseEntity<Pizza> storePizza(
			@RequestBody(required = true) Pizza pizza
			) {
		
		pizza = pizzaService.save(pizza);
		
		return new ResponseEntity<>(
				pizza, 
				HttpStatus.OK);	
	}
	
	@PutMapping("/update")
	public ResponseEntity<Pizza> update(
			@RequestBody Pizza pizza){

		pizza = pizzaService.save(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	// this will delete a pizza
	@DeleteMapping("/pizze/{id}")
	public ResponseEntity<Pizza> deletePizza(
			@PathVariable int id
		) {
		
		Optional<Pizza> optPizza = pizzaService.findById(id);
		
		if (optPizza.isEmpty()) {
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
		
		Pizza pizza = optPizza.get();
		
		pizza.getIngredienti().clear();
		pizzaService.save(pizza);		
		pizzaService.deletePizza(pizza);
		
		return new ResponseEntity<>(
				pizza, 
				HttpStatus.OK);	
	}
	
}
