package com.cursojava.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursojava.course.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
