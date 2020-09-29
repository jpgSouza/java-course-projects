package com.cursojava.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursojava.course.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
