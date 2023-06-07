package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

	List<Pizza> findByNameContaining(String name);

}
