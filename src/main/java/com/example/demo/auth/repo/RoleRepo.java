package com.example.demo.auth.repo;

import com.example.demo.auth.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
