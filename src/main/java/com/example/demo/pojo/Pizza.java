package com.example.demo.pojo;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(mappedBy = "pizza", cascade = CascadeType.REMOVE)
//	@JsonManagedReference
	private List<OffertaSpeciale> offerteSpeciali;
	
	@ManyToMany
	private List<Ingrediente> ingredienti;

	@NotBlank(message = "Il nome non può essere vuoto")
	private String name;
	@NotBlank(message = "La descrizione non può essere vuota")
	private String description;
	@NotBlank(message = "Per favore inserire una foto")
	private String imgUrl;

	@DecimalMin(value = "0.01", message = "Il prezzo deve essere maggiore di 0")
	private double price;
	
	public Pizza() { }
	public Pizza (String name, String description, String imgUrl, double price, Ingrediente... ingredienti) {
		
		setName(name);
		setDescription(description);
		setImgUrl(imgUrl);
		setPrice(price);
		setIngredienti(ingredienti);
	}
	
	public List<OffertaSpeciale> getOfferteSpeciali() {
		return offerteSpeciali;
	}
	public void setOfferteSpeciali(List<OffertaSpeciale> offerteSpeciali) {
		this.offerteSpeciali = offerteSpeciali;
	}
	@SuppressWarnings("unlikely-arg-type")
	public void removeOfferteSpeciali(List<OffertaSpeciale> offerteSpeciali) {
		getOfferteSpeciali().remove(offerteSpeciali);
	}
	
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}
	@JsonSetter
	public void setIngredienti(List<Ingrediente> ingredienti) {
		
		this.ingredienti = ingredienti;
	}
	public void setIngredienti(Ingrediente[] ingredienti) {
		setIngredienti(Arrays.asList(ingredienti)); 
	}
	public void addIngrediente(Ingrediente ingrediente) {
		getIngredienti().add(ingrediente);
	}
	public void removeIngrediente(Ingrediente ingrediente) {
		getIngredienti().remove(ingrediente);
	}
	
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getName();
	}
	
}