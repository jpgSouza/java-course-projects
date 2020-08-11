package com.cursojava.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursojava.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
}
