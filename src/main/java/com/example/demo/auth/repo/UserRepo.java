package com.example.demo.auth.repo;

import java.util.Optional;

import com.example.demo.auth.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

	public Optional<User> findByUsername(String username);
}
