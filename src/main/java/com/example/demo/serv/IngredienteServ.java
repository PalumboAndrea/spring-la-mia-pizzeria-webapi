package com.example.demo.serv;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.Ingrediente;
import com.example.demo.repo.IngredienteRepo;

@Service
public class IngredienteServ {
	
	@Autowired
	private IngredienteRepo ingredienteRepo;
	
	public List<Ingrediente> findAll() {
		
		return ingredienteRepo.findAll();
	}
	public Ingrediente save(Ingrediente ingrediente) {
		
		return ingredienteRepo.save(ingrediente);
	}
	public Optional<Ingrediente> findById(int id) {
		
		return ingredienteRepo.findById(id);
	}
	
	public void deleteIngrediente(Ingrediente ingrediente) {
		
		ingredienteRepo.delete(ingrediente);
	}

}
