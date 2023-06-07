package com.example.demo.serv;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pojo.OffertaSpeciale;
import com.example.demo.repo.OffertaSpecialeRepo;

@Service
public class OffertaSpecialeServ {

	@Autowired
	private OffertaSpecialeRepo offertaSpecialeRepo;
	
	public List<OffertaSpeciale> findAll() {
		
		return offertaSpecialeRepo.findAll();
	}
	public OffertaSpeciale save(OffertaSpeciale offertaSpeciale) {
		
		return offertaSpecialeRepo.save(offertaSpeciale);
	}
	public Optional<OffertaSpeciale> findById(int id) {
		
		return offertaSpecialeRepo.findById(id);
	}
	
	public void deleteOffertaSpeciale(OffertaSpeciale offertaSpeciale) {
		
		offertaSpecialeRepo.delete(offertaSpeciale);
	}
	
}
