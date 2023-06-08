package com.example.demo.api.controller.dto;

import org.springframework.validation.BindingResult;

import com.example.demo.pojo.Pizza;

public class PizzaResponseDto {

	Pizza pizza;
	BindingResult bindingResult;
	
	public PizzaResponseDto(Pizza pizza) {
			
		setPizza(pizza);
	}
	public PizzaResponseDto(BindingResult bindingResult) {
		
		setBindingResult(bindingResult);
	}
	public PizzaResponseDto(Pizza pizza, BindingResult bindingResult) {
		
		setPizza(pizza);
		setBindingResult(bindingResult);
	}

	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	public BindingResult getBindingResult() {
		return bindingResult;
	}
	public void setBindingResult(BindingResult bindingResult) {
		this.bindingResult = bindingResult;
	}
}
