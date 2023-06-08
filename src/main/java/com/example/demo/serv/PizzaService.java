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
	public Optional<Pizza> findByIdwithSpecialOffer(Integer id) {
		Optional<Pizza> oPizza = pizzaRepository.findById(id);
		Hibernate.initialize(oPizza.get().getOfferteSpeciali());
	
	return oPizza;
	}
	
	@Transactional
	public Optional<Pizza> findByIdwithIngredients(Integer id) {
		Optional<Pizza> oPizza = pizzaRepository.findById(id);
		Hibernate.initialize(oPizza.get().getIngredienti());
	
	return oPizza;
	}
	
	@Transactional
	public Optional<Pizza> findByIdwithIngredientsAndOffer(Integer id) {
		Optional<Pizza> oPizza = pizzaRepository.findById(id);
		Hibernate.initialize(oPizza.get().getIngredienti());
		Hibernate.initialize(oPizza.get().getOfferteSpeciali());
	
	return oPizza;
	}
}
