package com.example.demo.pojo;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class OffertaSpeciale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pizza pizza;

	@FutureOrPresent(message = "La data deve essere o oggi o nel futuro")
	private LocalDate startingDate;
	
	@Future(message = "La data deve essere nel futuro")
	private LocalDate endingDate;
	
	@Size(min = 3, max = 255, message = "Il titolo deve essere formato da un minimo di 3 caratteri ad un massimo di 255")
	@NotBlank(message = "Il titolo non può essere vuoto")
	private String title;
	
	@Column(name = "discount", nullable = false)
	@Range(min = 1, max = 100, message = "Inserire una percentuale di sconto compresa tra 1 e 100")
	private Integer discount;
	
	public OffertaSpeciale() { }
	public OffertaSpeciale(LocalDate startingDate, LocalDate endingDate, String title, Integer discount, Pizza pizza) {
		
		setStartingDate(startingDate);
		setEndingDate(endingDate);
		setTitle(title);
		setDiscount(discount);
		setPizza(pizza);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(LocalDate startingDate) {
		this.startingDate = startingDate;
	}
	public LocalDate getEndingDate() {
		return endingDate;
	}
	public void setEndingDate(LocalDate endingDate) {
		this.endingDate = endingDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getTitle();
	}
	
}
