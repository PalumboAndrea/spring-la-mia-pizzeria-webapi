package com.example.demo.serv;

import java.util.*;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Pizza;
import com.example.demo.repo.PizzaRepository;

import jakarta.transaction.Transactional;

@Service
public class PizzaService {

	@Autowired
	private PizzaRepository pizzaRepository;
	
	public List<Pizza> findAll() {
		
		return pizzaRepository.findAll();
	}
	public Pizza save(Pizza pizza) {
		
		return pizzaRepository.save(pizza);
	}
	public Optional<Pizza> findById(int id) {
		
		return pizzaRepository.findById(id);
	}
	
	public List<Pizza> findByName(String name) {
		
		return pizzaRepository.findByNameContaining(name);
	}
	
	public void deletePizza(Pizza pizza) {
		
		pizzaRepository.delete(pizza);
	}
	
	@Transactional
	public Optional<Pizza> findByIdWithOffertaSpeciale(int id) {
		
		Optional<Pizza> pizzaOpt = pizzaRepository.findById(id);
		Hibernate.initialize(pizzaOpt.get().getOfferteSpeciali());
		
		return pizzaOpt;
	}
}
