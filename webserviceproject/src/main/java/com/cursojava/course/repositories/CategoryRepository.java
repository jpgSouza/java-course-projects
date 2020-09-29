package com.cursojava.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursojava.course.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}
